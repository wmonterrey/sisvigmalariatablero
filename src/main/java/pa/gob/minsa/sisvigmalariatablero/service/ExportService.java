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
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
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
		
		Query query = session.createSQLQuery("SELECT malaria_casos.estado, malaria_casos.fiebre_30d, malaria_casos.sintomas, malaria_casos.fecha_inicio_sintomas, "
				+ "malaria_casos.esta_embarazada, malaria_casos.esta_lactando, malaria_casos.prueba_rapida, malaria_casos.pdr_fecha,  malaria_casos.pdr_resultado, "
				+ "malaria_casos.pdr_parasito, malaria_casos.viaje_30d, malaria_casos.lab_resultado, malaria_casos.lab_fecha, malaria_casos.lab_parasito, malaria_casos.tratamiento_iniciado, "
				+ "malaria_casos.clasificado FROM sisvigdb.semana_epi, sisvigdb.malaria_casos "
				+ "WHERE sisvigdb.semana_epi.fecha_inicio <= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and sisvigdb.semana_epi.fecha_fin >= CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END "
				+ "and malaria_casos.eliminado = 0 " 
				+ "and CASE WHEN malaria_casos.fecha_inicio_sintomas IS NOT NULL then malaria_casos.fecha_inicio_sintomas ELSE malaria_casos.pdr_fecha END  between :fechaInicio and :fechaFinal "
				+ sqlQueryRegionWhere +
				";");
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
