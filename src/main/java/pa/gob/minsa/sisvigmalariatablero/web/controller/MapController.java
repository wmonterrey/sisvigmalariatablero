package pa.gob.minsa.sisvigmalariatablero.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import pa.gob.minsa.sisvigmalariatablero.service.MapService;


/**
 * Controlador web de peticiones relacionadas a mapas
 * 
 * 
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/map/*")
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);

	@Resource(name="mapService")
	private MapService mapService;
	
	/**
     * Custom handler for showing the export.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String showMapPage(Model model) {
    	logger.info("showing Map page ..");
    	return "mapas/mapa";
	}
    
    @RequestMapping(value = "/datosmapa/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<List<Object[]>> obtenerCasosxProvincia(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		) throws ParseException {
        logger.info("Obteniendo casos confirmados por provincia");
        
        Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        //desde = formatter.parse("2018-12-31").getTime();
        //hasta = formatter.parse("2019-12-31").getTime();
        
        List<List<Object[]>> datos = mapService.getDatosCasosxOU(desde,hasta);
        return datos;
    }
	
}
