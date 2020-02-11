package pa.gob.minsa.sisvigmalariatablero.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pa.gob.minsa.sisvigmalariatablero.domain.Localidad;
import pa.gob.minsa.sisvigmalariatablero.domain.MalariaCasoReporte;
import pa.gob.minsa.sisvigmalariatablero.service.CatalogosService;
import pa.gob.minsa.sisvigmalariatablero.service.DashboardPortadaService;
import pa.gob.minsa.sisvigmalariatablero.service.ExportService;
import pa.gob.minsa.sisvigmalariatablero.service.OUService;


/**
 * Controlador web de peticiones relacionadas a exportar
 * 
 * 
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/export/*")
public class ExportController {
	private static final Logger logger = LoggerFactory.getLogger(ExportController.class);

	@Resource(name="exportService")
	private ExportService exportService;
	
	@Resource(name = "ouService")
    private OUService ouService;
	
	@Resource(name = "catalogosService")
    private CatalogosService catalogosService;
	
	@Resource(name="dashboardPortadaService")
	private DashboardPortadaService dashboardPortadaService;
	
	/**
     * Custom handler for showing the export.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String showExportPage(Model model) {
    	logger.info("showing Export page ..");
    	List<Integer> anios = this.dashboardPortadaService.getAniosDB();
    	model.addAttribute("anios", anios);
    	return "export/export";
	}
    
    @RequestMapping(value = "/casos/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MalariaCasoReporte> obtenerCasos(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		,@RequestParam(value = "oulevel", required = true) String oulevel
    		,@RequestParam(value = "ouname", required = false, defaultValue="") String ouname
    		) throws ParseException{
        logger.info("Obteniendo casos confirmados por periodo");
        
        Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        
        List<Object[]> datos = exportService.getDatosCasos(desde, hasta,oulevel,ouname);
        List<MalariaCasoReporte> casosReporte = new ArrayList<MalariaCasoReporte>();
        
        if (datos == null){
        	logger.debug(new Date() + " - casos - Nulo");
        }
        else {
        	for(Object[] caso: datos) {
        		MalariaCasoReporte mcr = new MalariaCasoReporte();
        		mcr.setId(Integer.valueOf(caso[0].toString()));
        		if(caso[1]!=null) mcr.setIdNotic(Integer.valueOf(caso[1].toString()));
        		if(caso[2]!=null) mcr.setFechaNacimiento(caso[2].toString().substring(0, 10));
        		if(caso[3]!=null) mcr.setSexo(caso[3].toString());
        		if(caso[4]!=null) {
        			if(caso[4].toString().equals("c")) {
        				mcr.setEstado("confirmado");
        			}
        			else if(caso[4].toString().equals("s")) {
        				mcr.setEstado("sospechoso");
        			}
        			else if(caso[4].toString().equals("d")) {
        				mcr.setEstado("descartado");
        			}
        			else {
        				mcr.setEstado("desconocido");
        			}
        		}
        		if(caso[5]!=null) mcr.setFechaNotificacion(caso[5].toString());
        		if(caso[6]!=null) {
        			if(this.ouService.getUnidadNotificadora(Integer.valueOf(caso[6].toString()))!=null) {
        				mcr.setUnidadNotificadora(this.ouService.getUnidadNotificadora(Integer.valueOf(caso[6].toString())).getName());
        			}
        			else {
        				mcr.setUnidadNotificadora(caso[6].toString());
        			}
        		}
        		if(caso[7]!=null) mcr.setFis(caso[7].toString());
        		if(caso[8]!=null) mcr.setFechaMuestra(caso[8].toString());
        		if(caso[9]!=null) mcr.setSemana(caso[9].toString());
        		if(caso[10]!=null) mcr.setAnio(caso[10].toString());
        		if(caso[11]!=null) {
        			if(caso[11].toString().equals("a")) {
        				mcr.setTipoBusqueda("activa");
        			}
        			else if(caso[11].toString().equals("p")) {
        				mcr.setTipoBusqueda("pasiva");
        			}
        			else if(caso[11].toString().equals("e")) {
        				mcr.setTipoBusqueda("encuesta");
        			}
        			else if(caso[11].toString().equals("r")) {
        				mcr.setTipoBusqueda("reactiva");
        			}
        			else {
        				mcr.setTipoBusqueda("desconocido");
        			}
        		}
        		if(caso[12]!=null) mcr.setPdr(caso[12].toString().equals("1")?"Si":"No");
        		if(caso[13]!=null && caso[12].toString().equals("1")) {
        			if(caso[13].toString().equals("p")) {
        				mcr.setPdrResultado("positivo");
        			}
        			else if(caso[13].toString().equals("n")) {
        				mcr.setPdrResultado("negativo");
        			}
        			else if(caso[13].toString().equals("i")) {
        				mcr.setPdrResultado("invalido");
        			}
        			else {
        				mcr.setPdrResultado("desconocido");
        			}
        		}
        		if(caso[14]!=null) {
        			if(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[14].toString()))!=null) {
        				mcr.setPdrParasito(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[14].toString())).getName());
        			}
        			else {
        				mcr.setPdrParasito(caso[14].toString());
        			}
        		}
        		if(caso[15]!=null) mcr.setGgFecha(caso[15].toString());
        		if(caso[16]!=null) {
        			if(caso[16].toString().equals("P")) {
        				mcr.setGgRes("positivo");
        			}
        			else if(caso[16].toString().equals("N")) {
        				mcr.setGgRes("negativo");
        			}
        			else {
        				mcr.setGgRes("desconocido");
        			}
        		}
        		if(caso[17]!=null) {
        			mcr.setGgEsp(caso[17].toString());
        			if(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[17].toString()))!=null) {
        				mcr.setGgEsp(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[17].toString())).getName());
        			}
        			else {
        				mcr.setGgEsp(caso[17].toString());
        			}
        		}
        		
        		
        		
        		if(caso[18]!=null) {
        			Localidad localidadMuestra = this.ouService.getLocalidad(Integer.valueOf(caso[18].toString()));
        			if(localidadMuestra!=null) {
        				mcr.setRegionMuestra(localidadMuestra.getCorregimiento().getDistrito().getRegion().getName());
        				mcr.setDistritoMuestra(localidadMuestra.getCorregimiento().getDistrito().getName());
        				mcr.setCorregimientoMuestra(localidadMuestra.getCorregimiento().getName());
        				mcr.setLocalidadMuestra(localidadMuestra.getName());
        			}
        			else {
        				mcr.setLocalidadMuestra(caso[18].toString());
        			}
        		}
        		
        		if(caso[19]!=null) {
        			if(this.catalogosService.getPais(Integer.valueOf(caso[19].toString()))!=null) {
        				mcr.setPaisResidencia(this.catalogosService.getPais(Integer.valueOf(caso[19].toString())).getName());
        			}
        			else {
        				mcr.setPaisResidencia(caso[19].toString());
        			}
        		}
        		
        		if(caso[20]!=null) {
        			Localidad localidadResidencia = this.ouService.getLocalidad(Integer.valueOf(caso[20].toString()));
        			if(localidadResidencia!=null) {
        				mcr.setRegionResidencia(localidadResidencia.getCorregimiento().getDistrito().getRegion().getName());
        				mcr.setDistritoResidencia(localidadResidencia.getCorregimiento().getDistrito().getName());
        				mcr.setCorregimientoResidencia(localidadResidencia.getCorregimiento().getName());
        				mcr.setLocalidadResidencia(localidadResidencia.getName());
        			}
        			else {
        				mcr.setLocalidadResidencia(caso[20].toString());
        			}
        		}
        		
        		if(caso[21]!=null) mcr.setFuncionario(caso[21].toString());
        		if(caso[22]!=null) mcr.setClave(caso[22].toString());
        		if(caso[23]!=null) mcr.setInv(caso[23].toString());
        		if(caso[24]!=null) mcr.setClas(caso[24].toString());
        		if(caso[25]!=null) {
        			if(caso[25].toString().equals("a")) {
        				mcr.setTipoCaso("autoctono");
        			}
        			else if(caso[25].toString().equals("i")) {
        				mcr.setTipoCaso("importado");
        			}
        			else {
        				mcr.setGgRes("desconocido");
        			}
        		}
        		if(caso[26]!=null) {
        			if(caso[26].toString().equals("r")) {
        				mcr.setOrigenInf("residencia");
        			}
        			else if(caso[26].toString().equals("v")) {
        				mcr.setOrigenInf("viaje");
        			}
        			else {
        				mcr.setOrigenInf("desconocido");
        			}
        		}
        		if(caso[27]!=null) {
        			if(caso[27].toString().equals("true")) {
        				mcr.setTx("Si");
        			}
        			else {
        				mcr.setTx("No");
        			}
        		}
        		if(caso[28]!=null) mcr.setFtx(caso[28].toString());
        		if(caso[29]!=null) {
        			if(caso[29].toString().equals("true")) {
        				mcr.setTxcomp("Si");
        			}
        			else {
        				mcr.setTxcomp("No");
        			}
        		}        		
        		if(caso[30]!=null) mcr.setCausaincomp(caso[30].toString());
        		
        		casosReporte.add(mcr);
        	}
        }
        return casosReporte;
    }
    
    
	
}
