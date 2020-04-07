package pa.gob.minsa.sisvigmalariatablero.web.controller;

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

import pa.gob.minsa.sisvigmalariatablero.service.DashboardPortadaService;
import pa.gob.minsa.sisvigmalariatablero.service.UsuarioService;
import pa.gob.minsa.sisvigmalariatablero.service.VisitsService;


/**
 * Controlador web de peticiones relacionadas a los dashboards
 * 
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/dashboard/*")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Resource(name="dashboardPortadaService")
	private DashboardPortadaService dashboardPortadaService;
	@Resource(name="visitsService")
	private VisitsService visitsService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	/**
     * Custom handler for showing the epid dashboard.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/epid/", method = RequestMethod.GET)
	public String showEpidemiologyDashboard(Model model) {
    	logger.info("showing Epidemiology Dashboard...");
    	List<Integer> anios = this.dashboardPortadaService.getAniosDB();
    	model.addAttribute("anios", anios);
    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"epidpage");
    	return "dashboards/epid";
	}
    
    /**
     * Custom handler for showing the vigilancia dashboard.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/vig/", method = RequestMethod.GET)
	public String showSurveillanceDashboard(Model model) {
    	logger.info("showing Surveillance Dashboard...");
    	List<Integer> anios = this.dashboardPortadaService.getAniosDB();
    	model.addAttribute("anios", anios);
    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"vigpage");
    	return "dashboards/vig";
	}
    
    /**
     * Custom handler for showing the manejo de casos dashboard.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/casemgm/", method = RequestMethod.GET)
	public String showCaseManagementDashboard(Model model) {
    	logger.info("showing Cases Dashboard...");
    	List<Integer> anios = this.dashboardPortadaService.getAniosDB();
    	model.addAttribute("anios", anios);
    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"casemgmpage");
    	return "dashboards/casemgm";
	}
	
}
