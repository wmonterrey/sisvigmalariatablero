package pa.gob.minsa.sisvigmalariatablero.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la seccion de vigilancia del dashboard
 * 
 * @author William Aviles
 * 
 **/

@Service("dashboardVigilanciaService")
@Transactional
public class DashboardVigilanciaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	

	
	
	/**
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosViajes(Long desde, Long hasta,String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Sql query (SQL)
		
		String sqlQueryRegionWhere = "";
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_distrito.id_region =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_distrito.id_provincia =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_corregimiento.id_distrito =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_localidades.id_corregimiento =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_casos.pdr_muestra_id_localidad =:ouname ";
		}
		
		Query query = session.createSQLQuery("SELECT sisvigdb.malaria_investigaciones.viaje_30d as hist, Count(malaria_casos.id) AS total " + 
				"FROM sisvigdb.semana_epi, (sisvigdb.malaria_casos LEFT JOIN sisvigdb.malaria_investigaciones ON sisvigdb.malaria_casos.id = sisvigdb.malaria_investigaciones.id_caso) "
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and sisvigdb.semana_epi.fecha_fin >= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and malaria_casos.eliminado = 0 AND malaria_casos.estado ='confirmado' " 
				+ "and CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END  between :fechaInicio and :fechaFinal "
				+ sqlQueryRegionWhere +
				" GROUP BY sisvigdb.malaria_investigaciones.viaje_30d;");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parámetro región
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro provincia
		else if(oulevel.equals("province.samp")) {	
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		return query.list();
	}
	
	
	/**
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosClasificacion(Long desde, Long hasta, String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Sql query (SQL)
		
		String sqlQueryRegionWhere = "";
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_distrito.id_region =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_distrito.id_provincia =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_corregimiento.id_distrito =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_localidades.id_corregimiento =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_casos.pdr_muestra_id_localidad =:ouname ";
		}
		
		Query query = session.createSQLQuery("SELECT If(ISNULL(tipo_caso) Or ISNULL(origen_infeccion),0,1) AS clas, Count(malaria_casos.id) AS total " + 
				"FROM sisvigdb.semana_epi, (sisvigdb.malaria_casos LEFT JOIN sisvigdb.malaria_investigaciones ON sisvigdb.malaria_casos.id = sisvigdb.malaria_investigaciones.id_caso) "
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and sisvigdb.semana_epi.fecha_fin >= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and malaria_casos.eliminado = 0 AND malaria_casos.estado ='confirmado' " 
				+ "and CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END  between :fechaInicio and :fechaFinal "
				+ sqlQueryRegionWhere +
				" GROUP BY If(ISNULL(malaria_investigaciones.tipo_caso) Or ISNULL(malaria_investigaciones.origen_infeccion),0,1);");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parámetro región
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro provincia
		else if(oulevel.equals("province.samp")) {	
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		return query.list();
	}
	
	
	/**
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosConfPDR(Long desde, Long hasta, String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Sql query (SQL)
		
		String sqlQueryRegionWhere = "";
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_distrito.id_region =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_distrito.id_provincia =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.cat_corregimiento.id_distrito =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_localidades.id_corregimiento =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_casos.pdr_muestra_id_localidad =:ouname ";
		}
		
		Query query = session.createSQLQuery("SELECT malaria_casos.lab_resultado AS conf, Count(malaria_casos.id) AS total " + 
				"FROM sisvigdb.semana_epi, (sisvigdb.malaria_casos LEFT JOIN sisvigdb.malaria_investigaciones ON sisvigdb.malaria_casos.id = sisvigdb.malaria_investigaciones.id_caso) "
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and sisvigdb.semana_epi.fecha_fin >= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and malaria_casos.prueba_rapida = 1 and malaria_casos.eliminado = 0 AND malaria_casos.estado ='confirmado' " 
				+ "and CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END  between :fechaInicio and :fechaFinal "
				+ sqlQueryRegionWhere +
				" GROUP BY malaria_casos.lab_resultado;");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parámetro región
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro provincia
		else if(oulevel.equals("province.samp")) {	
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		return query.list();
	}
	
}
