package pa.gob.minsa.sisvigmalariatablero.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pa.gob.minsa.sisvigmalariatablero.service.DashboardManejoService;
import pa.gob.minsa.sisvigmalariatablero.service.MessageResourceService;

@Controller
@RequestMapping("/view/casos/*")
public class DashboardManejoController {
	
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	@Resource(name="dashboardManejoService")
	private DashboardManejoService dashboardManejoService;
	
	private static final Logger logger = LoggerFactory.getLogger(DashboardManejoController.class);
	
    
    @RequestMapping(value = "/trat/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object[]> obtenerDatosTratamientos(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		,@RequestParam(value = "oulevel", required = true) String oulevel
    		,@RequestParam(value = "ouname", required = false, defaultValue="") String ouname) throws ParseException {
    	
    	logger.info("Obteniendo casos confirmados por periodo");
        
    	Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        //desde = formatter.parse("2018-12-31").getTime();
        //hasta = formatter.parse("2019-12-31").getTime();
        
        List<Object[]> datos = dashboardManejoService.getDatosTratamiento(desde,hasta,oulevel,ouname);
        return datos;
    }
    
    @RequestMapping(value = "/tratcomp/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object[]> obtenerDatosTratamientosCompleto(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		,@RequestParam(value = "oulevel", required = true) String oulevel
    		,@RequestParam(value = "ouname", required = false, defaultValue="") String ouname) throws ParseException {
        
    	Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        //desde = formatter.parse("2018-12-31").getTime();
        //hasta = formatter.parse("2019-12-31").getTime();
        
        List<Object[]> datos = dashboardManejoService.getDatosTratamientoCompleto(desde,hasta,oulevel,ouname);
        return datos;
    }
    
    @RequestMapping(value = "/diasdx/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object[]> obtenerDatosDiasDx(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		,@RequestParam(value = "oulevel", required = true) String oulevel
    		,@RequestParam(value = "ouname", required = false, defaultValue="") String ouname) throws ParseException {
        
    	Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        //desde = formatter.parse("2018-12-31").getTime();
        //hasta = formatter.parse("2019-12-31").getTime();
        
        List<Object[]> datos = dashboardManejoService.getDatosDiasDx(desde,hasta,oulevel,ouname);
        return datos;
    }


    
}
