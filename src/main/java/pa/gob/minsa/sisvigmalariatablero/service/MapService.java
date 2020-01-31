package pa.gob.minsa.sisvigmalariatablero.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para mapas
 * 
 * @author William Aviles
 * 
 **/

@Service("mapService")
@Transactional
public class MapService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa datos de casos confirmados por OU
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<List<Object[]>> getDatosCasosxOU(Long desde, Long hasta) {
		
		List<List<Object[]>> resultadosTotales = new ArrayList<List<Object[]>>();
		String sqlQueryRegionVista = "";
		String sqlQueryTiempoWhere = "";
		String sqlQueryGroupBY = "";
		
		/**
		 * Regresa datos de positivos de SISVIG
		 * 
		 */
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query;
		
		sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident, count(mc.id)";
		sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and mc.pdrfecha between :fechaInicio and :fechaFinal";
		sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident";
		Timestamp timeStampInicio = new Timestamp(desde);
		Timestamp timeStampFinal = new Timestamp(hasta);
		
		//Datos por region
		query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere 
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setTimestamp("fechaInicio", timeStampInicio);
		query.setTimestamp("fechaFinal", timeStampFinal);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
		// Retrieve all
		resultadosTotales.add(query.list());
		
		sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.ident, count(mc.id)";
		sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and mc.pdrfecha between :fechaInicio and :fechaFinal";
		sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.ident";
		
		
		//Datos por distrito
		query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere 
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setTimestamp("fechaInicio", timeStampInicio);
		query.setTimestamp("fechaFinal", timeStampFinal);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
		// Retrieve all
		resultadosTotales.add(query.list());
		
		sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.ident, count(mc.id)";
		sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and mc.pdrfecha between :fechaInicio and :fechaFinal";
		sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.ident";
		
		
		//Datos por corregimiento
		query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere 
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setTimestamp("fechaInicio", timeStampInicio);
		query.setTimestamp("fechaFinal", timeStampFinal);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
		// Retrieve all
		resultadosTotales.add(query.list());
		
		sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.ident, count(mc.id)";
		sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and mc.pdrfecha between :fechaInicio and :fechaFinal";
		sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.ident";
		
		
		//Datos por localidad
		query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere 
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setTimestamp("fechaInicio", timeStampInicio);
		query.setTimestamp("fechaFinal", timeStampFinal);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
		// Retrieve all
		resultadosTotales.add(query.list());



		return resultadosTotales;
	}	
	
	
}
