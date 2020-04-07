package pa.gob.minsa.sisvigmalariatablero.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import pa.gob.minsa.sisvigmalariatablero.domain.Foco;
import pa.gob.minsa.sisvigmalariatablero.domain.FocoLocalidad;
import pa.gob.minsa.sisvigmalariatablero.domain.FocoLocalidadId;
import pa.gob.minsa.sisvigmalariatablero.domain.Localidad;
import pa.gob.minsa.sisvigmalariatablero.domain.audit.AuditTrail;
import pa.gob.minsa.sisvigmalariatablero.service.AuditTrailService;
import pa.gob.minsa.sisvigmalariatablero.service.FocoService;
import pa.gob.minsa.sisvigmalariatablero.service.MessageResourceService;
import pa.gob.minsa.sisvigmalariatablero.service.OUService;
import pa.gob.minsa.sisvigmalariatablero.service.UsuarioService;
import pa.gob.minsa.sisvigmalariatablero.service.VisitsService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador web de peticiones
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/foci/*")
public class FocosController {
	private static final Logger logger = LoggerFactory.getLogger(FocosController.class);
	@Resource(name="focoService")
	private FocoService focoService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="ouService")
	private OUService ouService;
	
	@Resource(name="visitsService")
	private VisitsService visitsService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
    
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEntities(Model model) throws ParseException { 	
    	logger.debug("Mostrando Focos en JSP");
    	List<Foco> focos = focoService.getFocos();
    	model.addAttribute("focos", focos);
    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"focipage");
    	return "foci/list";
	}
	
	/**
     * Custom handler for adding.
     * @param model Modelo enlazado a la vista
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/newEntity/", method = RequestMethod.GET)
	public String addEntity(Model model) {
    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"newfocipage");
    	return "foci/enterForm";
	}
    
    /**
     * Custom handler for displaying.
     *
     * @param ident the ID to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/{ident}/")
    public ModelAndView showEntity(@PathVariable("ident") String ident) {
    	ModelAndView mav;
    	Foco foco = this.focoService.getFoco(ident);
        if(foco==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("foci/viewForm");
        	mav.addObject("foco",foco);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(ident);
            mav.addObject("bitacora",bitacora);
            List<Localidad> localidadesSeleccionadas = focoService.getLocalidadesFoco(ident);
            mav.addObject("localidadesSeleccionadas",localidadesSeleccionadas);
            this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"viewpage");
        }
        return mav;
    }
    
    /**
     * Custom handler for editing.
     * @param model Modelo enlazado a la vista
     * @param ident the ID to edit
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/editEntity/{ident}/", method = RequestMethod.GET)
	public String editEntity(@PathVariable("ident") String ident, Model model) {
		Foco foco = this.focoService.getFoco(ident);
		if(foco!=null){
			model.addAttribute("foco",foco);
			List<Localidad> localidades = ouService.getLocalidades();
	    	model.addAttribute("localidades", localidades);
	    	List<Localidad> localidadesSeleccionadas = focoService.getLocalidadesFoco(ident);
	    	model.addAttribute("localidadesSeleccionadas", localidadesSeleccionadas);
	    	this.visitsService.saveUserPages(this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()),new Date(),"editfocipage");
			return "foci/enterForm";
		}
		else{
			return "403";
		}
	}
	
    /**
     * Custom handler for saving.
     * 
     * @param ident Identificador unico
     * @param code codigo
     * @param name nombre
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping( value="/saveEntity/", method=RequestMethod.POST)
	public ResponseEntity<String> processEntity( @RequestParam(value="ident", required=false, defaultValue="" ) String ident
	        , @RequestParam( value="code", required=true ) String code
	        , @RequestParam( value="name", required=true ) String name
	        )
	{
    	try{
			Foco foco = new Foco();
			//Si el ident viene en blanco es nuevo
			if (ident.equals("")){
				//Crear nuevo
				ident = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				foco.setIdent(ident);
				foco.setCode(code);
				foco.setName(name);
				foco.setRecordUser(SecurityContextHolder.getContext().getAuthentication().getName());
				foco.setRecordDate(new Date());
				//Guardar nuevo
				this.focoService.saveFoco(foco);
			}
			//Si el ident no viene en blanco hay que editar
			else{
				//Recupera de la base de datos
				foco = focoService.getFoco(ident);
				foco.setCode(code);
				foco.setName(name);
				//Actualiza
				this.focoService.saveFoco(foco);
			}
			return createJsonResponse(foco);
    	}
		catch (DataIntegrityViolationException e){
			String message = e.getMostSpecificCause().getMessage();
			Gson gson = new Gson();
		    String json = gson.toJson(message);
		    return createJsonResponse(json);
		}
		catch(Exception e){
			Gson gson = new Gson();
		    String json = gson.toJson(e.toString());
		    return createJsonResponse(json);
		}
    	
	}
    
    /**
     * Custom handler for disabling.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/disableEntity/{ident}/")
    public String disableEntity(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Foco foco = this.focoService.getFoco(ident);
    	if(foco!=null){
    		foco.setPasive('1');
    		this.focoService.saveFoco(foco);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", foco.getName());
    		redirecTo = "redirect:/foci/"+foco.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for enabling.
     *
     * @param ident the ID to enable
     * @param redirectAttributes
     * @return a String
     */
    @RequestMapping("/enableEntity/{ident}/")
    public String enableEntity(@PathVariable("ident") String ident, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Foco foco = this.focoService.getFoco(ident);
    	if(foco!=null){
    		foco.setPasive('0');
    		this.focoService.saveFoco(foco);
    		redirectAttributes.addFlashAttribute("entidadHabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", foco.getName());
    		redirecTo = "redirect:/foci/"+foco.getIdent()+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Custom handler for disabling a locality.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/disableLoc/{ident}/{locality}/")
    public String disableLocalidad(@PathVariable("ident") String ident, 
    		@PathVariable("locality") Integer locality,
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		
		FocoLocalidad floc = focoService.getFocoLocalidad(ident, locality);
    	if(floc!=null){
    		floc.setPasive('1');
    		this.focoService.saveFocoLocalidad(floc);
    		redirectAttributes.addFlashAttribute("entidadDeshabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", floc.getLocalidad().getName());
    		redirecTo = "redirect:/foci/"+ident+"/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for adding a locality.
     *
     * @param ident the ID to disable
     * @param redirectAttributes 
     * @return a String
     */
    @RequestMapping("/addLocalidad/{ident}/{locality}/")
    public String agregarLocalidad(@PathVariable("ident") String ident, 
    		@PathVariable("locality") Integer locality,
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		
		FocoLocalidad floc = focoService.getFocoLocalidad(ident, locality);
    	if(floc!=null){
    		floc.setPasive('0');
    		this.focoService.saveFocoLocalidad(floc);
    		redirectAttributes.addFlashAttribute("entidadHabilitada", true);
    		redirectAttributes.addFlashAttribute("nombreEntidad", floc.getLocalidad().getName());
    		redirecTo = "redirect:/foci/"+ident+"/";
    	}
    	else{
    		Localidad loc = this.ouService.getLocalidad(locality);
    		if(loc!=null) {
    			floc = new FocoLocalidad(new FocoLocalidadId(ident,locality),new Date(),SecurityContextHolder.getContext().getAuthentication().getName());
    			this.focoService.saveFocoLocalidad(floc);
    			redirectAttributes.addFlashAttribute("entidadHabilitada", true);
        		redirectAttributes.addFlashAttribute("nombreEntidad", loc.getName());
    			redirecTo = "redirect:/foci/"+ident+"/";
    		}
    		else {
    			redirecTo = "403";
    		}
    	}
    	return redirecTo;	
    }
	
    private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
	
}
