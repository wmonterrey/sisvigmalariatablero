package pa.gob.minsa.sisvigmalariatablero.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controlador web de peticiones relacionadas a exportar
 * 
 * 
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/map/*")
public class MapController {
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);

	
	/**
     * Custom handler for showing the export.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/map1/", method = RequestMethod.GET)
	public String showMapPage(Model model) {
    	logger.info("showing Export page ..");
    	return "mapas/mapa";
	}
    
    
    /**
     * Custom handler for showing the export.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/map2/", method = RequestMethod.GET)
	public String showMap2Page(Model model) {
    	logger.info("showing Export page ..");
    	return "mapas/mapa2";
	}
    
    
	
}
