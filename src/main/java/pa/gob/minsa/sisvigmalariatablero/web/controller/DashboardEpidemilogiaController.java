package pa.gob.minsa.sisvigmalariatablero.web.controller;

import java.text.ParseException;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pa.gob.minsa.sisvigmalariatablero.service.DashboardEpidemiologiaService;
import pa.gob.minsa.sisvigmalariatablero.service.MessageResourceService;
import pa.gob.minsa.sisvigmalariatablero.utils.DatosCanal;

@Controller
@RequestMapping("/view/epid/*")
public class DashboardEpidemilogiaController {
	
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	@Resource(name="dashboardEpidemiologiaService")
	private DashboardEpidemiologiaService dashboardEpidemiologiaService;
	
    private static final Logger logger = LoggerFactory.getLogger(DashboardEpidemilogiaController.class);

    @RequestMapping(value = "/canal/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<DatosCanal> obtenerCanal(@RequestParam(value = "anio", required = true) Integer anio,
    		@RequestParam(value = "oulevel", required = true) String oulevel,
    		@RequestParam(value = "ouname", required = false, defaultValue="") String ouname
    		
    		) throws ParseException {
        logger.info("Obteniendo canales por semana");
        
        List<DatosCanal> datos = dashboardEpidemiologiaService.getDatosCanales(anio,oulevel,ouname);
        return datos;
    }
    
    @RequestMapping(value = "/carga/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object[]> obtenerCarga(@RequestParam(value = "oulevel", required = true) String oulevel,
    		@RequestParam(value = "ouname", required = false, defaultValue="") String ouname) {
        logger.info("Obteniendo casos confirmados por periodo");
        
        List<Object[]> datos = dashboardEpidemiologiaService.getDatosCarga(oulevel,ouname);
        return datos;
    }    
    
}
