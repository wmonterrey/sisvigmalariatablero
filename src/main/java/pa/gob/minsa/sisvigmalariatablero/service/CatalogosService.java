package pa.gob.minsa.sisvigmalariatablero.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pa.gob.minsa.sisvigmalariatablero.domain.Etnia;
import pa.gob.minsa.sisvigmalariatablero.domain.Pais;
import pa.gob.minsa.sisvigmalariatablero.domain.SemanaEpidemiologica;
import pa.gob.minsa.sisvigmalariatablero.domain.Sexo;
import pa.gob.minsa.sisvigmalariatablero.domain.TipoIdentidad;
import pa.gob.minsa.sisvigmalariatablero.domain.TipoPlasmodium;
import pa.gob.minsa.sisvigmalariatablero.domain.UnidadNotificadora;


/**
 * 
 * @author William Aviles
 * 
 **/

@Service("catalogosService")
@Transactional
public class CatalogosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * 
	 * @return una lista de <code>TipoIdentidad</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<TipoIdentidad> getTipoIdentidads() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TipoIdentidad ti where ti.status = 1");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una TipoIdentidad
	 * @param id Identificador de  TipoIdentidad 
	 * @return una <code>TipoIdentidad</code>
	 */

	public TipoIdentidad getTipoIdentidad(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TipoIdentidad s where " +
				"s.ident =:ident");
		query.setParameter("ident",ident);
		TipoIdentidad ti = (TipoIdentidad) query.uniqueResult();
		return ti;
	}
	
	/**
	 * 
	 * @return una lista de <code>TipoPlasmodium</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<TipoPlasmodium> getTipoPlasmodiumPDR() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TipoPlasmodium tp where tp.pdr = '1'");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * 
	 * @return una lista de <code>TipoPlasmodium</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<TipoPlasmodium> getTipoPlasmodiumLab() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM TipoPlasmodium tp where tp.lab = '1'");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa una TipoPlasmodium
	 * @param id Identificador de  TipoPlasmodium 
	 * @return una <code>TipoPlasmodium</code>
	 */

	public TipoPlasmodium getTipoPlasmodium(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM TipoPlasmodium tp where " +
				"tp.ident =:ident");
		query.setParameter("ident",ident);
		TipoPlasmodium tp = (TipoPlasmodium) query.uniqueResult();
		return tp;
	}
	
	/**
	 * 
	 * @return una lista de <code>Sexo</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Sexo> getSexos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Sexo");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Sexo
	 * @param id Identificador de  Sexo 
	 * @return una <code>Sexo</code>
	 */

	public Sexo getSexo(String ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Sexo s where " +
				"s.ident =:ident");
		query.setParameter("ident",ident);
		Sexo sexo = (Sexo) query.uniqueResult();
		return sexo;
	}
	
	
	/**
	 * 
	 * @return una lista de <code>Etnia</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Etnia> getEtnias() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Etnia e where e.ident <= 6");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Etnia
	 * @param id Identificador de  Etnia 
	 * @return una <code>Etnia</code>
	 */

	public Etnia getEtnia(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Etnia s where " +
				"s.ident =:ident");
		query.setParameter("ident",ident);
		Etnia etnia = (Etnia) query.uniqueResult();
		return etnia;
	}
	
	/**
	 * Regresa una UnidadNotificadora
	 * @param id Identificador de  UnidadNotificadora 
	 * @return una <code>Etnia</code>
	 */

	public UnidadNotificadora getUnidadNotificadora(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UnidadNotificadora un where " +
				"un.ident =:ident");
		query.setParameter("ident",ident);
		UnidadNotificadora un = (UnidadNotificadora) query.uniqueResult();
		return un;
	}
	
	/**
	 * 
	 * @return una lista de <code>Pais</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Pais> getPaises() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Pais");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Pais
	 * @param id Identificador de  Pais 
	 * @return una <code>Pais</code>
	 */

	public Pais getPais(Integer ident) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Pais s where " +
				"s.ident =:ident");
		query.setParameter("ident",ident);
		Pais pais = (Pais) query.uniqueResult();
		return pais;
	}
	
	/**
	 * Regresa una Semana
	 * @param semana anio
	 * @return una <code>SemanaEpidemiologica</code>
	 */

	public SemanaEpidemiologica  getSemanaEpidemiologica(Integer semana, Integer anio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SemanaEpidemiologica s where " +
				"s.semana =:semana and s.anio=:anio");
		query.setParameter("semana",semana);
		query.setParameter("anio",anio);
		SemanaEpidemiologica semanaE = (SemanaEpidemiologica) query.uniqueResult();
		return semanaE;
	}
	
	
	
	/**
	 * Regresa una Semana
	 * @param semana anio
	 * @return una <code>SemanaEpidemiologica</code>
	 */
	
	public SemanaEpidemiologica  getSemanaEpidemiologicaFecha(Date fecha) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SemanaEpidemiologica s where " +
				"s.fechaIni <=:fecha and s.fechaFin>=:fecha");
		query.setParameter("fecha",fecha);
		SemanaEpidemiologica semanaE = (SemanaEpidemiologica) query.uniqueResult();
		return semanaE;
	}
	
	

}
