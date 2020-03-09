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
 * Servicio para la seccion de manejo de caso del dashboard
 * 
 * @author William Aviles
 * 
 **/

@Service("dashboardManejoService")
@Transactional
public class DashboardManejoService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	

	
	
	/**
	 * Regresa datos de casos confirmados por periodo
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosTratamiento(Long desde, Long hasta,String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Sql query (SQL)
		
		String sqlQueryRegionWhere = "";
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por region
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
		//Por foco
		else if(oulevel.equals("foci.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_casos.pdr_muestra_id_localidad IN (SELECT sisvig_tablero.focilocalities.locality FROM sisvig_tablero.focilocalities WHERE sisvig_tablero.focilocalities.foco =:ouname AND sisvig_tablero.focilocalities.pasive='0') ";
		}			
		
		Query query = session.createSQLQuery("select realizado, COUNT(ident) FROM (SELECT sisvigdb.malaria_casos.id AS ident, Max(sisvigdb.malaria_tratamientos.tratamiento_realizado) AS realizado " + 
				"FROM sisvigdb.semana_epi, sisvigdb.malaria_casos LEFT JOIN sisvigdb.malaria_tratamientos ON sisvigdb.malaria_casos.id = sisvigdb.malaria_tratamientos.id_caso " 
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.semana_epi.fecha_fin >= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND malaria_casos.eliminado = 0 and sisvigdb.malaria_casos.estado='confirmado' "
				+ "and CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END between :fechaInicio and :fechaFinal " 
				+ sqlQueryRegionWhere +
				" GROUP BY sisvigdb.malaria_casos.id) AS pivote GROUP BY realizado;");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parametro region
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro provincia
		else if(oulevel.equals("province.samp")) {	
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro foco
		else if(oulevel.equals("foci.samp")) {
			query.setParameter("ouname", ouname);
		}			
		return query.list();
	}
	
	
	/**
	 * Regresa datos de casos confirmados por periodo
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosTratamientoCompleto(Long desde, Long hasta,String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Sql query (SQL)
		
		String sqlQueryRegionWhere = "";
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por region
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
		//Por foco
		else if(oulevel.equals("foci.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_casos.pdr_muestra_id_localidad IN (SELECT sisvig_tablero.focilocalities.locality FROM sisvig_tablero.focilocalities WHERE sisvig_tablero.focilocalities.foco =:ouname AND sisvig_tablero.focilocalities.pasive='0') ";
		}			
		
		Query query = session.createSQLQuery("select completo, COUNT(ident) FROM (SELECT sisvigdb.malaria_casos.id AS ident, Max(sisvigdb.malaria_tratamientos.tratamiento_completado) AS completo " + 
				"FROM sisvigdb.semana_epi, sisvigdb.malaria_casos LEFT JOIN sisvigdb.malaria_tratamientos ON sisvigdb.malaria_casos.id = sisvigdb.malaria_tratamientos.id_caso " 
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.semana_epi.fecha_fin >= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND malaria_casos.eliminado = 0 and sisvigdb.malaria_casos.estado='confirmado' "
				+ "and CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END between :fechaInicio and :fechaFinal " 
				+ sqlQueryRegionWhere +
				" GROUP BY sisvigdb.malaria_casos.id) AS pivote GROUP BY completo;");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parametro region
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro provincia
		else if(oulevel.equals("province.samp")) {	
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro foco
		else if(oulevel.equals("foci.samp")) {
			query.setParameter("ouname", ouname);
		}			
		return query.list();
	}


	/**
	 * Regresa datos de casos confirmados por periodo
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosDiasDx(Long desde, Long hasta,String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Sql query (SQL)
		
		String sqlQueryRegionWhere = "";
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por region
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
		//Por foco
		else if(oulevel.equals("foci.samp")) {
			sqlQueryRegionWhere = " and sisvigdb.malaria_casos.pdr_muestra_id_localidad IN (SELECT sisvig_tablero.focilocalities.locality FROM sisvig_tablero.focilocalities WHERE sisvig_tablero.focilocalities.foco =:ouname AND sisvig_tablero.focilocalities.pasive='0') ";
		}			
		
		Query query = session.createSQLQuery("select if(dias<=2,'0-2',if(dias<=7,'3-7','8 +')) AS grupo, count(ident) FROM "
				+ "(SELECT sisvigdb.malaria_casos.id AS ident, sisvigdb.malaria_casos.fecha_inicio_sintomas, if(sisvigdb.malaria_casos.prueba_rapida=1,sisvigdb.malaria_casos.pdr_fecha,sisvigdb.malaria_casos.lab_fecha) AS fechadx, "
				+ "if(sisvigdb.malaria_casos.prueba_rapida=1,sisvigdb.malaria_casos.pdr_fecha,sisvigdb.malaria_casos.lab_fecha) - sisvigdb.malaria_casos.fecha_inicio_sintomas AS dias FROM sisvigdb.semana_epi, sisvigdb.malaria_casos "
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.semana_epi.fecha_fin >= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END  "
				+ "and eliminado = 0 and "
				+ "CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END between :fechaInicio and :fechaFinal "
				+ "AND (malaria_casos.fiebre_30d=1 OR malaria_casos.sintomas Is Not NULL) "
				+ sqlQueryRegionWhere 
				+ "AND (if(prueba_rapida=1,pdr_fecha,lab_fecha) Is NOT NULL)) "
				+ " AS lista "
				+ "GROUP BY if(dias<=2,'0-2',if(dias<=7,'3-7','8 +'))");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parametro region
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro provincia
		else if(oulevel.equals("province.samp")) {	
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parametro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//Parámetro foco
		else if(oulevel.equals("foci.samp")) {
			query.setParameter("ouname", ouname);
		}			
		return query.list();
	}
	
	
}
