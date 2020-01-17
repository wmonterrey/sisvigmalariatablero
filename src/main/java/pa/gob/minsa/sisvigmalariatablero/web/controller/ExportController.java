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


import pa.gob.minsa.sisvigmalariatablero.service.ExportService;


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
	
	/**
     * Custom handler for showing the export.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String showExportPage(Model model) {
    	logger.info("showing Export page ..");
    	return "export/export";
	}
    
    @RequestMapping(value = "/casos/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Object[]> obtenerCasos(@RequestParam(value = "fechaInicio", required = true) String fechaInicio
    		,@RequestParam(value = "fechaFin", required = true) String fechaFin
    		,@RequestParam(value = "oulevel", required = true) String oulevel
    		,@RequestParam(value = "ouname", required = false, defaultValue="") String ouname
    		) throws ParseException {
        logger.info("Obteniendo casos confirmados por periodo");
        
        Long desde = null;
        Long hasta = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        if (!fechaInicio.matches("")) desde = formatter.parse(fechaInicio).getTime();
        if (!fechaFin.matches("")) hasta = formatter.parse(fechaFin).getTime();
        
        List<Object[]> datos = exportService.getDatosCasos(desde, hasta,oulevel,ouname);
        return datos;
    }
    
    
	
}
