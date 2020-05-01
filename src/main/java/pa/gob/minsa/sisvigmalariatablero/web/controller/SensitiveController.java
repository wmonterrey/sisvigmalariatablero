package pa.gob.minsa.sisvigmalariatablero.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
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
import pa.gob.minsa.sisvigmalariatablero.service.UsuarioService;
import pa.gob.minsa.sisvigmalariatablero.service.VisitsService;


/**
 * Controlador web de peticiones relacionadas a exportar datos personales
 * 
 * 
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/sensitive/*")
public class SensitiveController {
	private static final Logger logger = LoggerFactory.getLogger(SensitiveController.class);

	@Resource(name="exportService")
	private ExportService exportService;
	
	@Resource(name = "ouService")
    private OUService ouService;
	
	@Resource(name = "catalogosService")
    private CatalogosService catalogosService;
	
	@Resource(name="dashboardPortadaService")
	private DashboardPortadaService dashboardPortadaService;
	
	@Resource(name="visitsService")
	private VisitsService visitsService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
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
    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"exportsensitivepage");
    	return "export/sensitive";
	}
    
    @RequestMapping(value = "/casos/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MalariaCasoReporte> obtenerCasos(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		,@RequestParam(value = "oulevel", required = true) String oulevel
    		,@RequestParam(value = "ouname", required = false, defaultValue="") String ouname
    		) throws ParseException{
        logger.info("Obteniendo casos confirmados por periodo");
        String nombreCompleto;
        Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        
        List<Object[]> datos = exportService.getDatosCasosPersonalInfo(desde, hasta,oulevel,ouname);
        List<MalariaCasoReporte> casosReporte = new ArrayList<MalariaCasoReporte>();
        
        if (datos == null){
        	logger.debug(new Date() + " - casos - Nulo");
        }
        else {
        	for(Object[] caso: datos) {
        		MalariaCasoReporte mcr = new MalariaCasoReporte();
        		nombreCompleto="";
        		mcr.setId(Integer.valueOf(caso[0].toString()));
        		if(caso[1]!=null) mcr.setIdNotic(Integer.valueOf(caso[1].toString()));
        		if(caso[2]!=null) mcr.setNumIdent(caso[2].toString());
        		if(caso[3]!=null) nombreCompleto = nombreCompleto + caso[3].toString() + " ";
        		if(caso[4]!=null) nombreCompleto = nombreCompleto + caso[4].toString() + " ";
        		if(caso[5]!=null) nombreCompleto = nombreCompleto + caso[5].toString() + " ";
        		if(caso[6]!=null) nombreCompleto = nombreCompleto + caso[6].toString();
        		mcr.setNombre(nombreCompleto);
        		
        		if(caso[7]!=null) mcr.setFechaNacimiento(caso[7].toString().substring(0, 10));
        		if(caso[8]!=null) mcr.setSexo(caso[8].toString());
        		if(caso[9]!=null) {
        			if(caso[9].toString().equals("c")) {
        				mcr.setEstado("confirmado");
        			}
        			else if(caso[9].toString().equals("s")) {
        				mcr.setEstado("sospechoso");
        			}
        			else if(caso[9].toString().equals("d")) {
        				mcr.setEstado("descartado");
        			}
        			else {
        				mcr.setEstado("desconocido");
        			}
        		}
        		if(caso[10]!=null) mcr.setFechaNotificacion(caso[10].toString());
        		if(caso[11]!=null) {
        			if(this.ouService.getUnidadNotificadora(Integer.valueOf(caso[11].toString()))!=null) {
        				mcr.setUnidadNotificadora(this.ouService.getUnidadNotificadora(Integer.valueOf(caso[11].toString())).getName());
        			}
        			else {
        				mcr.setUnidadNotificadora(caso[11].toString());
        			}
        		}
        		if(caso[12]!=null) mcr.setFis(caso[12].toString());
        		if(caso[13]!=null) mcr.setFechaMuestra(caso[13].toString());
        		if(caso[14]!=null) mcr.setSemana(caso[14].toString());
        		if(caso[15]!=null) mcr.setAnio(caso[15].toString());
        		if(caso[16]!=null) {
        			if(caso[16].toString().equals("a")) {
        				mcr.setTipoBusqueda("activa");
        			}
        			else if(caso[16].toString().equals("p")) {
        				mcr.setTipoBusqueda("pasiva");
        			}
        			else if(caso[16].toString().equals("e")) {
        				mcr.setTipoBusqueda("encuesta");
        			}
        			else if(caso[16].toString().equals("r")) {
        				mcr.setTipoBusqueda("reactiva");
        			}
        			else {
        				mcr.setTipoBusqueda("desconocido");
        			}
        		}
        		if(caso[17]!=null) mcr.setPdr(caso[17].toString().equals("1")?"Si":"No");
        		if(caso[18]!=null && caso[17].toString().equals("1")) {
        			if(caso[18].toString().equals("p")) {
        				mcr.setPdrResultado("positivo");
        			}
        			else if(caso[18].toString().equals("n")) {
        				mcr.setPdrResultado("negativo");
        			}
        			else if(caso[18].toString().equals("i")) {
        				mcr.setPdrResultado("invalido");
        			}
        			else {
        				mcr.setPdrResultado("desconocido");
        			}
        		}
        		if(caso[19]!=null) {
        			if(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[19].toString()))!=null) {
        				mcr.setPdrParasito(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[19].toString())).getName());
        			}
        			else {
        				mcr.setPdrParasito(caso[19].toString());
        			}
        		}
        		if(caso[20]!=null) mcr.setGgFecha(caso[20].toString());
        		if(caso[21]!=null) {
        			if(caso[21].toString().equals("P")) {
        				mcr.setGgRes("positivo");
        			}
        			else if(caso[21].toString().equals("N")) {
        				mcr.setGgRes("negativo");
        			}
        			else {
        				mcr.setGgRes("desconocido");
        			}
        		}
        		if(caso[22]!=null) {
        			mcr.setGgEsp(caso[22].toString());
        			if(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[22].toString()))!=null) {
        				mcr.setGgEsp(this.catalogosService.getTipoPlasmodium(Integer.valueOf(caso[22].toString())).getName());
        			}
        			else {
        				mcr.setGgEsp(caso[22].toString());
        			}
        		}
        		
        		
        		
        		if(caso[23]!=null) {
        			Localidad localidadMuestra = this.ouService.getLocalidad(Integer.valueOf(caso[23].toString()));
        			if(localidadMuestra!=null) {
        				mcr.setRegionMuestra(localidadMuestra.getCorregimiento().getDistrito().getRegion().getName());
        				mcr.setDistritoMuestra(localidadMuestra.getCorregimiento().getDistrito().getName());
        				mcr.setCorregimientoMuestra(localidadMuestra.getCorregimiento().getName());
        				mcr.setLocalidadMuestra(localidadMuestra.getName());
        			}
        			else {
        				mcr.setLocalidadMuestra(caso[23].toString());
        			}
        		}
        		
        		if(caso[24]!=null) {
        			if(this.catalogosService.getPais(Integer.valueOf(caso[24].toString()))!=null) {
        				mcr.setPaisResidencia(this.catalogosService.getPais(Integer.valueOf(caso[24].toString())).getName());
        			}
        			else {
        				mcr.setPaisResidencia(caso[24].toString());
        			}
        		}
        		
        		if(caso[25]!=null) {
        			Localidad localidadResidencia = this.ouService.getLocalidad(Integer.valueOf(caso[25].toString()));
        			if(localidadResidencia!=null) {
        				mcr.setRegionResidencia(localidadResidencia.getCorregimiento().getDistrito().getRegion().getName());
        				mcr.setDistritoResidencia(localidadResidencia.getCorregimiento().getDistrito().getName());
        				mcr.setCorregimientoResidencia(localidadResidencia.getCorregimiento().getName());
        				mcr.setLocalidadResidencia(localidadResidencia.getName());
        			}
        			else {
        				mcr.setLocalidadResidencia(caso[25].toString());
        			}
        		}
        		
        		if(caso[26]!=null) mcr.setFuncionario(caso[26].toString());
        		if(caso[27]!=null) mcr.setClave(caso[27].toString());
        		if(caso[28]!=null) mcr.setInv(caso[28].toString());
        		if(caso[29]!=null) mcr.setClas(caso[29].toString());
        		if(caso[30]!=null) {
        			if(caso[30].toString().equals("a")) {
        				mcr.setTipoCaso("autoctono");
        			}
        			else if(caso[30].toString().equals("i")) {
        				mcr.setTipoCaso("importado");
        			}
        			else {
        				mcr.setGgRes("desconocido");
        			}
        		}
        		if(caso[31]!=null) {
        			if(caso[31].toString().equals("r")) {
        				mcr.setOrigenInf("residencia");
        			}
        			else if(caso[31].toString().equals("v")) {
        				mcr.setOrigenInf("viaje");
        			}
        			else {
        				mcr.setOrigenInf("desconocido");
        			}
        		}
        		if(caso[32]!=null) {
        			if(caso[32].toString().equals("true")) {
        				mcr.setTx("Si");
        			}
        			else {
        				mcr.setTx("No");
        			}
        		}
        		if(caso[33]!=null) mcr.setFtx(caso[33].toString());
        		if(caso[34]!=null) {
        			if(caso[34].toString().equals("true")) {
        				mcr.setTxcomp("Si");
        			}
        			else {
        				mcr.setTxcomp("No");
        			}
        		}        		
        		if(caso[35]!=null) mcr.setCausaincomp(caso[35].toString());
        		
        		if(caso[36]!=null) {
        			Localidad localidadViaje = this.ouService.getLocalidad(Integer.valueOf(caso[36].toString()));
        			if(localidadViaje!=null) {
        				mcr.setLocalidadViaje(localidadViaje.getName());
        			}
        			else {
        				mcr.setLocalidadViaje(caso[36].toString());
        			}
        		}
        		
        		if(caso[37]!=null) mcr.setFocoMuestra(caso[37].toString());
        		if(caso[38]!=null) mcr.setFocoResidencia(caso[38].toString());
        		if(caso[39]!=null) mcr.setFocoInfeccion(caso[39].toString());
        		
        		casosReporte.add(mcr);
        	}
        }
        return casosReporte;
    }
    
    
	
}
