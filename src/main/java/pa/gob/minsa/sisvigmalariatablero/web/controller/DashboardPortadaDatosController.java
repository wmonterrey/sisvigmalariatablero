package pa.gob.minsa.sisvigmalariatablero.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pa.gob.minsa.sisvigmalariatablero.service.DashboardPortadaService;
import pa.gob.minsa.sisvigmalariatablero.service.MessageResourceService;
import pa.gob.minsa.sisvigmalariatablero.utils.DatosTiempo;

@Controller
@RequestMapping("/view/portada/*")
public class DashboardPortadaDatosController {
	
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	@Resource(name="dashboardPortadaService")
	private DashboardPortadaService dashboardPortadaService;
	
    private static final Logger logger = LoggerFactory.getLogger(DashboardPortadaDatosController.class);

    @RequestMapping(value = "/casos/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<List<DatosTiempo>> obtenerCasosxPeriodo(@RequestParam(value = "anio", required = true) Integer anio,
    		@RequestParam(value = "oulevel", required = true) String oulevel,
    		@RequestParam(value = "ouname", required = false, defaultValue="") String ouname,
    		@RequestParam(value = "timeview", required = false, defaultValue="") String timeview
    		) throws ParseException {
        logger.info("Obteniendo casos confirmados por periodo");
        
        List<DatosTiempo> datos = dashboardPortadaService.getDatosCasosxPeriodo(anio,oulevel,ouname,timeview);
        List<DatosTiempo> datos2 = dashboardPortadaService.getDatosCasosxPeriodo(anio-1,oulevel,ouname,timeview);
        
        
        List<List<DatosTiempo>> aList =  
                new ArrayList<List<DatosTiempo>>(2);
        
        aList.add(datos);
        aList.add(datos2);
        
        return aList;
    }
    
    
    @RequestMapping(value = "/muestras/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<List<DatosTiempo>> obtenerMuestrasxPeriodo(@RequestParam(value = "anio", required = true) Integer anio,
    		@RequestParam(value = "oulevel", required = true) String oulevel,
    		@RequestParam(value = "ouname", required = false, defaultValue="") String ouname,
    		@RequestParam(value = "timeview", required = false, defaultValue="") String timeview
    		) throws ParseException {
        logger.info("Obteniendo muestras por periodo");
        
        List<DatosTiempo> datos = dashboardPortadaService.getDatosMuestrasxPeriodo(anio,oulevel,ouname,timeview);
        List<DatosTiempo> datos2 = dashboardPortadaService.getDatosMuestrasxPeriodo(anio-1,oulevel,ouname,timeview);
        
        
        List<List<DatosTiempo>> aList =  
                new ArrayList<List<DatosTiempo>>(2);
        
        aList.add(datos);
        aList.add(datos2);
        return aList;
    }
    
    @RequestMapping(value = "/locbusq/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Integer obtenerLocalidadescBusq(@RequestParam(value = "anio", required = true) Integer anio,
    		@RequestParam(value = "oulevel", required = true) String oulevel,
    		@RequestParam(value = "ouname", required = false, defaultValue="") String ouname,
    		@RequestParam(value = "timeview", required = false, defaultValue="") String timeview
    		) throws ParseException {
        logger.info("Obteniendo muestras por periodo");
        
        Integer total = dashboardPortadaService.getLocalidadescBusqueda(anio,oulevel,ouname,timeview);
        return total;
    }
    
    @RequestMapping(value = "/regiones/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object[]> obtenerCasosxRegion(@RequestParam(value = "anio", required = true) Integer anio,
    		@RequestParam(value = "oulevel", required = true) String oulevel,
    		@RequestParam(value = "ouname", required = false, defaultValue="") String ouname
    		) throws ParseException {
        logger.info("Obteniendo casos confirmados por region");
        
        List<Object[]> datos = dashboardPortadaService.getDatosCasosxOU(anio,oulevel,ouname);
        return datos;
    }
    
    
}
