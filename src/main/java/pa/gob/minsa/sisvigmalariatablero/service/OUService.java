package pa.gob.minsa.sisvigmalariatablero.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pa.gob.minsa.sisvigmalariatablero.domain.Corregimiento;
import pa.gob.minsa.sisvigmalariatablero.domain.Distrito;
import pa.gob.minsa.sisvigmalariatablero.domain.Localidad;
import pa.gob.minsa.sisvigmalariatablero.domain.Provincia;
import pa.gob.minsa.sisvigmalariatablero.domain.Region;
import pa.gob.minsa.sisvigmalariatablero.domain.UnidadNotificadora;



/**
 * 
 * @author William Aviles
 * 
 **/

@Service("ouService")
@Transactional
public class OUService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todas las provincias
	 * 
	 * @return una lista de <code>Provincias</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Provincia> getProvincias() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Provincia");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Provincia
	 * @param id Identificador de la Provincia 
	 * @return una <code>Provincia</code>
	 */

	public Provincia getProvincia(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Provincia prov where " +
				"prov.ident =:ident");
		query.setParameter("ident",ident);
		Provincia provincia = (Provincia) query.uniqueResult();
		return provincia;
	}
	
	
	/**
	 * Regresa todas las regiones
	 * 
	 * @return una lista de <code>Region</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Region> getRegiones() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Region region where region.provincia in (Select ident from Provincia) ");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Region
	 * @param id Identificador de la region 
	 * @return un <code>Area</code>
	 */

	public Region getRegion(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Region region where " +
				"region.ident =:ident");
		query.setParameter("ident",ident);
		Region region = (Region) query.uniqueResult();
		return region;
	}
	
	/**
	 * Regresa todas los distritos
	 * 
	 * @return una lista de <code>Ditrito</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Distrito> getDistritos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Distrito dist order by dist.region.ident, dist.name");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa un Distrito
	 * @param id Identificador del Distrito 
	 * @return un <code>Distrito</code>
	 */

	public Distrito getDistrito(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Distrito dist where " +
				"dist.ident =:ident");
		query.setParameter("ident",ident);
		Distrito distrito = (Distrito) query.uniqueResult();
		return distrito;
	}
	
	/**
	 * Regresa todos los corregimientos
	 * 
	 * @return una lista de <code>Corregimiento</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Corregimiento> getCorregimientos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Corregimiento corr order by corr.name");
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Corregimiento> getCorregimientos(String filtro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Corregimiento c where lower(c.name) like :filtro ORDER BY c.name");
		query.setParameter("filtro", "%" +filtro.toLowerCase() + "%");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa un Corregimiento
	 * @param id Identificador del Corregimiento 
	 * @return un <code>Corregimiento</code>
	 */

	public Corregimiento getCorregimiento(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Corregimiento corr where " +
				"corr.ident =:ident");
		query.setParameter("ident",ident);
		Corregimiento corregimiento = (Corregimiento) query.uniqueResult();
		return corregimiento;
	}
	
	/**
	 * Regresa todas las localidades
	 * 
	 * @return una lista de <code>Localidad</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Localidad> getLocalidades() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Localidad loc where loc.corregimiento in (Select ident from Corregimiento) ORDER BY loc.name");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todas las localidades con un filtro
	 * 
	 * @return una lista de <code>Localidad</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Localidad> getLocalidades(String filtro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Localidad loc where ((loc.corregimiento in (Select ident from Corregimiento)) and "
				+ "(lower(loc.name) like :filtro)) ORDER BY loc.name");
		query.setParameter("filtro", "%" +filtro.toLowerCase() + "%");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Localidad
	 * @param id Identificador de la Localidad 
	 * @return un <code>Localidad</code>
	 */

	public Localidad getLocalidad(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Localidad loc where " +
				"loc.ident =:ident");
		query.setParameter("ident",ident);
		Localidad localidad = (Localidad) query.uniqueResult();
		return localidad;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UnidadNotificadora> getUnidadesNotificadoras(String filtro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UnidadNotificadora un where ((un.corregimiento in (Select ident from Corregimiento)) and "
				+ "(lower(un.name) like :filtro)) ORDER BY un.name");
		query.setParameter("filtro", "%" +filtro.toLowerCase() + "%");
		// Retrieve all
		return  query.list();
	}

}
