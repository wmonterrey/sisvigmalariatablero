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
 * Servicio para la exporrtacion
 * 
 * @author William Aviles
 * 
 **/

@Service("exportService")
@Transactional
public class ExportService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa datos de casos confirmados por periodo
	 * 
	 * @return lista de objetos
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosCasos(Long desde, Long hasta,String oulevel,String ouname) {
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
		
		String sqlQuery = "SELECT sisvigdb.malaria_casos.id, sisvigdb.notic_form.id_notic, sisvigdb.tbl_persona.fecha_nacimiento, sisvigdb.tbl_persona.sexo, "
				+ "sisvigdb.malaria_casos.estado as estado, sisvigdb.notic_form.fecha_notificacion, "
				+ "sisvigdb.notic_form.id_un AS unidad_notif, sisvigdb.malaria_casos.fecha_inicio_sintomas, sisvigdb.malaria_casos.pdr_fecha, semana_epi.semana, semana_epi.anio, sisvigdb.malaria_casos.tipo_busqueda, "
				+ "sisvigdb.malaria_casos.prueba_rapida, sisvigdb.malaria_casos.pdr_resultado, sisvigdb.malaria_casos.pdr_parasito, "
				+ "sisvigdb.malaria_casos.lab_fecha, sisvigdb.malaria_casos.lab_resultado, sisvigdb.malaria_casos.lab_parasito, sisvigdb.malaria_casos.pdr_muestra_id_localidad, "
				+ "sisvigdb.notic_form.per_id_pais,sisvigdb.tbl_persona.id_localidad, sisvigdb.malaria_casos.funcionario_nombre, sisvigdb.malaria_casos.funcionario_clave, "
				+ "if(sisvigdb.malaria_investigaciones.id>0,'Si','No') AS investigacion_realizada, if(sisvigdb.malaria_investigaciones.tipo_caso IS NOT NULL AND sisvigdb.malaria_investigaciones.origen_infeccion IS NOT null,'Si','No') AS clasificado, "
				+ "sisvigdb.malaria_investigaciones.tipo_caso, sisvigdb.malaria_investigaciones.origen_infeccion, Max(sisvigdb.malaria_tratamientos.tratamiento_realizado) AS tx_iniciado, "
				+ "Min(sisvigdb.malaria_tratamientos.fecha) AS fechainiciotx, Max(sisvigdb.malaria_tratamientos.tratamiento_completado) AS completo, Max(sisvigdb.malaria_tratamientos.causa_suspension) AS causa " 
				+ "FROM sisvigdb.semana_epi, ((sisvigdb.malaria_casos LEFT JOIN sisvigdb.notic_form ON sisvigdb.malaria_casos.id_notificacion_general = sisvigdb.notic_form.id_notic) "
				+ "LEFT JOIN sisvigdb.tbl_persona ON (sisvigdb.notic_form.numero_identificacion = sisvigdb.tbl_persona.numero_identificacion) AND (sisvigdb.notic_form.tipo_identificacion = sisvigdb.tbl_persona.tipo_identificacion) "
				+ "LEFT JOIN sisvigdb.malaria_tratamientos ON sisvigdb.malaria_casos.id = sisvigdb.malaria_tratamientos.id_caso "
				+ "LEFT JOIN sisvigdb.malaria_investigaciones ON sisvigdb.malaria_casos.id = sisvigdb.malaria_investigaciones.id_caso) "
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.semana_epi.fecha_fin >= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.malaria_casos.eliminado = 0 and CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END between :fechaInicio and :fechaFinal "
				+ sqlQueryRegionWhere 
				+ "GROUP BY sisvigdb.malaria_casos.id"+
				";";
		
		Query query = session.createSQLQuery(sqlQuery);
		
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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosCasosPersonalInfo(Long desde, Long hasta,String oulevel,String ouname) {
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
		
		String sqlQuery = "SELECT sisvigdb.malaria_casos.id, sisvigdb.notic_form.id_notic, sisvigdb.malaria_casos.paciente_numero_id, sisvigdb.tbl_persona.primer_nombre, sisvigdb.tbl_persona.segundo_nombre, sisvigdb.tbl_persona.primer_apellido, sisvigdb.tbl_persona.segundo_apellido, sisvigdb.tbl_persona.fecha_nacimiento, sisvigdb.tbl_persona.sexo, "
				+ "sisvigdb.malaria_casos.estado as estado, sisvigdb.notic_form.fecha_notificacion, "
				+ "sisvigdb.notic_form.id_un AS unidad_notif, sisvigdb.malaria_casos.fecha_inicio_sintomas, sisvigdb.malaria_casos.pdr_fecha, semana_epi.semana, semana_epi.anio, sisvigdb.malaria_casos.tipo_busqueda, "
				+ "sisvigdb.malaria_casos.prueba_rapida, sisvigdb.malaria_casos.pdr_resultado, sisvigdb.malaria_casos.pdr_parasito, "
				+ "sisvigdb.malaria_casos.lab_fecha, sisvigdb.malaria_casos.lab_resultado, sisvigdb.malaria_casos.lab_parasito, sisvigdb.malaria_casos.pdr_muestra_id_localidad, "
				+ "sisvigdb.notic_form.per_id_pais,sisvigdb.tbl_persona.id_localidad, sisvigdb.malaria_casos.funcionario_nombre, sisvigdb.malaria_casos.funcionario_clave, "
				+ "if(sisvigdb.malaria_investigaciones.id>0,'Si','No') AS investigacion_realizada, if(sisvigdb.malaria_investigaciones.tipo_caso IS NOT NULL AND sisvigdb.malaria_investigaciones.origen_infeccion IS NOT null,'Si','No') AS clasificado, "
				+ "sisvigdb.malaria_investigaciones.tipo_caso, sisvigdb.malaria_investigaciones.origen_infeccion, Max(sisvigdb.malaria_tratamientos.tratamiento_realizado) AS tx_iniciado, "
				+ "Min(sisvigdb.malaria_tratamientos.fecha) AS fechainiciotx, Max(sisvigdb.malaria_tratamientos.tratamiento_completado) AS completo, Max(sisvigdb.malaria_tratamientos.causa_suspension) AS causa " 
				+ "FROM sisvigdb.semana_epi, ((sisvigdb.malaria_casos LEFT JOIN sisvigdb.notic_form ON sisvigdb.malaria_casos.id_notificacion_general = sisvigdb.notic_form.id_notic) "
				+ "LEFT JOIN sisvigdb.tbl_persona ON (sisvigdb.notic_form.numero_identificacion = sisvigdb.tbl_persona.numero_identificacion) AND (sisvigdb.notic_form.tipo_identificacion = sisvigdb.tbl_persona.tipo_identificacion) "
				+ "LEFT JOIN sisvigdb.malaria_tratamientos ON sisvigdb.malaria_casos.id = sisvigdb.malaria_tratamientos.id_caso "
				+ "LEFT JOIN sisvigdb.malaria_investigaciones ON sisvigdb.malaria_casos.id = sisvigdb.malaria_investigaciones.id_caso) "
				+ "INNER JOIN sisvigdb.malaria_localidades ON sisvigdb.malaria_casos.pdr_muestra_id_localidad = sisvigdb.malaria_localidades.id "
				+ "INNER JOIN sisvigdb.cat_corregimiento ON sisvigdb.malaria_localidades.id_corregimiento = sisvigdb.cat_corregimiento.id_corregimiento "
				+ "INNER JOIN sisvigdb.cat_distrito ON sisvigdb.cat_corregimiento.id_distrito = sisvigdb.cat_distrito.id_distrito "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.semana_epi.fecha_fin >= CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END "
				+ "AND sisvigdb.malaria_casos.eliminado = 0 and CASE WHEN sisvigdb.malaria_casos.fecha_inicio_sintomas IS NOT NULL then sisvigdb.malaria_casos.fecha_inicio_sintomas ELSE sisvigdb.malaria_casos.pdr_fecha END between :fechaInicio and :fechaFinal "
				+ sqlQueryRegionWhere 
				+ "GROUP BY sisvigdb.malaria_casos.id"+
				";";
		
		Query query = session.createSQLQuery(sqlQuery);
		
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
