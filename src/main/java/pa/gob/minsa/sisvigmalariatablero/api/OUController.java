package pa.gob.minsa.sisvigmalariatablero.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import pa.gob.minsa.sisvigmalariatablero.domain.Corregimiento;
import pa.gob.minsa.sisvigmalariatablero.domain.Distrito;
import pa.gob.minsa.sisvigmalariatablero.domain.Foco;
import pa.gob.minsa.sisvigmalariatablero.domain.ItemCorregimiento;
import pa.gob.minsa.sisvigmalariatablero.domain.ItemLocalidad;
import pa.gob.minsa.sisvigmalariatablero.domain.ItemUnidad;
import pa.gob.minsa.sisvigmalariatablero.domain.Localidad;
import pa.gob.minsa.sisvigmalariatablero.domain.Provincia;
import pa.gob.minsa.sisvigmalariatablero.domain.Region;
import pa.gob.minsa.sisvigmalariatablero.domain.ResultCorregimientos;
import pa.gob.minsa.sisvigmalariatablero.domain.ResultLocalidades;
import pa.gob.minsa.sisvigmalariatablero.domain.ResultUnidades;
import pa.gob.minsa.sisvigmalariatablero.domain.UnidadNotificadora;
import pa.gob.minsa.sisvigmalariatablero.service.FocoService;
import pa.gob.minsa.sisvigmalariatablero.service.OUService;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author William Aviles
 **/
@Controller
@RequestMapping("/api/*")
public class OUController {

    private static final Logger logger = LoggerFactory.getLogger(OUController.class);

    @Resource(name = "ouService")
    private OUService ouService;
    @Resource(name = "focoService")
    private FocoService focoService;
    
    /**
     * Retorna provincias. Acepta una solicitud GET para JSON
     * @return Provincia JSON
     */
    @RequestMapping(value = "provincias", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Provincia> getProvincia(){
        logger.info("Descargando toda la informacion de los datos de las provincias");
        
        List<Provincia> provincias = ouService.getProvincias();
        if (provincias == null){
        	logger.debug(new Date() + " - provincias - Nulo");
        }
        return  provincias;
    }

    /**
     * Retorna regiones. Acepta una solicitud GET para JSON
     * @return Region JSON
     */
    @RequestMapping(value = "regiones", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Region> getRegiones(){
        logger.info("Descargando toda la informacion de los datos de las regiones");
        
        List<Region> regiones = ouService.getRegiones();
        if (regiones == null){
        	logger.debug(new Date() + " - regiones - Nulo");
        }
        return  regiones;
    }
    
    /**
     * Retorna distritos. Acepta una solicitud GET para JSON
     * @return Distrito JSON
     */
    @RequestMapping(value = "distritos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Distrito> getDistrito(){
        logger.info("Descargando toda la informacion de los datos de los distritos");
        
        List<Distrito> distritos = ouService.getDistritos();
        if (distritos == null){
        	logger.debug(new Date() + " - distritos - Nulo");
        }
        return  distritos;
    }
    
    /**
     * Retorna corregimientos. Acepta una solicitud GET para JSON
     * @return Distrito JSON
     */
    @RequestMapping(value = "corregimientos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Corregimiento> getCorregimientos(){
        logger.info("Descargando toda la informacion de los datos de los corregimientos");
        
        List<Corregimiento> corregimientos = ouService.getCorregimientos();
        if (corregimientos == null){
        	logger.debug(new Date() + " - corregimientos - Nulo");
        }
        return  corregimientos;
    }
    
    /**
     * Retorna focos. Acepta una solicitud GET para JSON
     * @return Distrito JSON
     */
    @RequestMapping(value = "focos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Foco> getFocos(){
        logger.info("Descargando toda la informacion de los datos de los focos");
        
        List<Foco> focos = this.focoService.getActiveFocos();
        if (focos == null){
        	logger.debug(new Date() + " - focos - Nulo");
        }
        return  focos;
    }
    
    /**
     * Retorna localidades. Acepta una solicitud GET para JSON
     * @return Localidad JSON
     */
    @RequestMapping(value = "todaslocalidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Localidad> getLocalidades(){
        logger.info("Descargando toda la informacion de los datos de las localidades");
        
        List<Localidad> localidades = ouService.getLocalidades();
        if (localidades == null){
        	logger.debug(new Date() + " - localidades - Nulo");
        }
        return  localidades;
    }
    
    /**
     * Retorna localidades. Acepta una solicitud GET para JSON
     * @return Localidad JSON
     */
    @RequestMapping(value = "localidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResultLocalidades getLocalidades(@RequestParam(value = "filtro", required = true) String filtro){
        logger.info("Descargando toda la informacion de los datos de las localidades");
        ResultLocalidades results = new ResultLocalidades();
        List<Localidad> localidades = ouService.getLocalidades(filtro);
        List<ItemLocalidad> items = new ArrayList<ItemLocalidad>();
        for(Localidad loc:localidades) {
        	ItemLocalidad item = new ItemLocalidad();
        	item.setId(loc.getIdent());
        	item.setText(loc.getName());
        	item.setLocalidad(loc);
        	items.add(item);
        }
        results.setTotal(localidades.size());
        results.setItems(items);
        return  results;
    }
    
    /**
     * Retorna localidades. Acepta una solicitud GET para JSON
     * @return Localidad JSON
     */
    @RequestMapping(value = "unidades", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResultUnidades getUnidades(@RequestParam(value = "filtro", required = true) String filtro){
        logger.info("Descargando toda la informacion de los datos de las unidades");
        ResultUnidades results = new ResultUnidades();
        List<UnidadNotificadora> unidades = ouService.getUnidadesNotificadoras(filtro);
        List<ItemUnidad> items = new ArrayList<ItemUnidad>();
        for(UnidadNotificadora uni:unidades) {
        	ItemUnidad item = new ItemUnidad();
        	item.setId(uni.getIdent());
        	item.setText(uni.getName());
        	item.setUnidad(uni);
        	items.add(item);
        }
        results.setTotal(unidades.size());
        results.setItems(items);
        return  results;
    }
    
    
    @RequestMapping(value = "corregimientosfilt", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody ResultCorregimientos getCorregimientos(@RequestParam(value = "filtro", required = true) String filtro){
        logger.info("Descargando toda la informacion de los datos de las unidades");
        ResultCorregimientos results = new ResultCorregimientos();
        List<Corregimiento> corregimientos = ouService.getCorregimientos(filtro);
        List<ItemCorregimiento> items = new ArrayList<ItemCorregimiento>();
        for(Corregimiento corr:corregimientos) {
        	ItemCorregimiento item = new ItemCorregimiento();
        	item.setId(corr.getIdent());
        	item.setText(corr.getName());
        	item.setCorregimiento(corr);
        	items.add(item);
        }
        results.setTotal(corregimientos.size());
        results.setItems(items);
        return  results;
    }
    

}
