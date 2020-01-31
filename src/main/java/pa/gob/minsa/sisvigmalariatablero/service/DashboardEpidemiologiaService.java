package pa.gob.minsa.sisvigmalariatablero.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.math3.stat.StatUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pa.gob.minsa.sisvigmalariatablero.utils.DatosCanal;

/**
 * Servicio para la seccion de epidemiología del dashboard
 * 
 * @author William Aviles
 * 
 **/

@Service("dashboardEpidemiologiaService")
@Transactional
public class DashboardEpidemiologiaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	

	
	
	/**
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<DatosCanal> getDatosCanales(Integer anio, String oulevel,String ouname) {
		List<Object[]> resultadosHistoricos = new ArrayList<Object[]>();
		List<Object[]> resultadosSisvig = new ArrayList<Object[]>();
		List<DatosCanal> resultadosCanal = new ArrayList<DatosCanal>();
		
		String sqlQueryRegionWhere = "";
		
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.ident =:ouname ";
		}
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		//Todos los datos historicos
		Query query = session.createQuery("SELECT cas.semana, cas.anio, count(cas.semana) as total "
				+ " from Casos cas " + sqlQueryRegionWhere +" group by cas.semana, cas.anio");
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
		resultadosHistoricos = query.list();
		
		//Datos de SISVIG A partir de 2018
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.ident =:ouname ";
		}
		
		//Datos de sisvig desde 2018
		query = session.createQuery("SELECT se.semana, se.anio, count(se.semana) as total "
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio>=:anio and mc.estado=:estado and mc.eliminado=:eliminado "
				+ sqlQueryRegionWhere + " group by se.semana, se.anio");
		
		query.setParameter("anio", 2018);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
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
				
				
		resultadosSisvig = query.list();
		
		//Agregar sisvig a los historicos
		for(Object[] dato: resultadosSisvig) {
			resultadosHistoricos.add(dato);
		}
		
		//inicializar la lista de datos del canal
		for (int i = 0 ; i < 53 ; i++){
				DatosCanal dato = new DatosCanal(i+1,anio,0,0,0,0);
				resultadosCanal.add(dato);
		}
		
		
		//llenar datos del canal
		for (DatosCanal dato:resultadosCanal){
			double[] datos = {0.00,0.00,0.00,0.00,0.00,0.00,0.00};
			double q1,q2,q3;
			int konteo = 0;
			for(int j = anio - 7; j<anio; j++) {
				for(Object[] dat: resultadosHistoricos) {
					if(Integer.valueOf(dat[0].toString())==dato.getSemana() && Integer.valueOf(dat[1].toString())==j){
						datos[konteo]= Double.valueOf(dat[2].toString());
						konteo++;
					}
				}
			}
			q1 = (double) StatUtils.percentile(datos, 25.0);
			q2 = (double) StatUtils.percentile(datos, 50.0);
			q3 = (double) StatUtils.percentile(datos, 75.0);
			dato.setQ1(q1);
			dato.setQ2(q2);
			dato.setQ3(q3);
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
		
		//Datos de SISVIG anio seleccionado
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.ident =:ouname ";
		}
		
		//Datos de sisvig actual

		query = session.createQuery("SELECT se.semana, se.anio, count(se.semana) as total "
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio and mc.estado=:estado and mc.eliminado=:eliminado "
				+ sqlQueryRegionWhere + " group by se.semana, se.anio");
		
		query.setParameter("anio", anio);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		resultadosSisvig.clear();
		
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
				
		resultadosSisvig = query.list();
		
		//Agregar los datos al resultado canal
		for(DatosCanal dato:resultadosCanal) {
			for(Object[] semana:resultadosSisvig) {
				if(dato.getSemana()==Integer.valueOf(semana[0].toString())) {
					dato.setCasos(Integer.valueOf(semana[2].toString()));
				}
			}
		}
		
		return resultadosCanal;
	}
	
	/**
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosCarga(String oulevel,String ouname) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		String sqlQueryRegionWhere = "";
		List<Object[]> resultadosHistoricos = new ArrayList<Object[]>();
		List<Object[]> resultadosSisvig = new ArrayList<Object[]>();
		
		//Datos historicos
		
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " where cas.localidad.ident =:ouname ";
		}
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("Select cas.anio, count(anio),"
				+ " SUM( CASE cas.tipoBusqueda WHEN 'Activa' THEN 1 ELSE 0 END ) AS Activa,"
				+ " SUM( CASE cas.tipoBusqueda WHEN 'Pasiva' THEN 1 ELSE 0 END ) AS Pasiva, "
				+ " 0 AS Reactiva, "
				+ " 0 AS Encuesta, "
				+ " SUM( CASE WHEN cas.tipoBusqueda IS NULL THEN 1 ELSE 0 END ) AS NDTB, "
				+ " SUM( CASE cas.especie WHEN 'V' THEN 1 ELSE 0 END ) AS Vivax, "
				+ " SUM( CASE cas.especie WHEN 'F' THEN 1 ELSE 0 END ) AS Falciparum, "
				+ " 0 AS PAN, "
				+ " 0 AS Otro, "
				+ " SUM( CASE WHEN cas.especie IS NULL THEN 1 ELSE 0 END ) AS NDE "
				+ " FROM Casos cas "+ sqlQueryRegionWhere +"group by cas.anio");
		
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
		//Poner todos los datos historicos en un arreglo
		resultadosHistoricos = query.list();
		
		
		
		//Datos de SISVIG A partir de 2018
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.ident =:ouname ";
		}
		
		//Datos de sisvig desde 2018
		query = session.createQuery("SELECT se.anio, count(se.anio) as total, "
				+ " SUM( CASE mc.tipoBusqueda WHEN 'activa' THEN 1 ELSE 0 END ) AS Activa, "
				+ " SUM( CASE mc.tipoBusqueda WHEN 'pasiva' THEN 1 ELSE 0 END ) AS Pasiva, "
				+ " SUM( CASE mc.tipoBusqueda WHEN 'reactiva' THEN 1 ELSE 0 END ) AS Reactiva, "
				+ " SUM( CASE mc.tipoBusqueda WHEN 'encuesta' THEN 1 ELSE 0 END ) AS Encuesta, "
				+ " SUM( CASE WHEN mc.tipoBusqueda IS NULL THEN 1 ELSE 0 END ) AS NDTB, "
				+ " SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END = 2 THEN 1 ELSE 0 END) AS Vivax, "
				+ " SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END = 1 THEN 1 ELSE 0 END) AS Falciparum, "
				+ " SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END = 15 THEN 1 ELSE 0 END) AS PAN, "
				+ " COUNT(mc.id) - SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END = 2 THEN 1 ELSE 0 END) "
				+ " -SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END = 1 THEN 1 ELSE 0 END) "
				+ " -SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END = 15 THEN 1 ELSE 0 END) "
				+ " -SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END IS NULL THEN 1 ELSE 0 END) AS Otro, "
				+ " SUM( CASE WHEN CASE WHEN mc.labPlasmodium IS NOT NULL then mc.labPlasmodium ELSE mc.pdrParasito END IS NULL THEN 1 ELSE 0 END) AS SDE "
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio>=:anio and mc.estado=:estado and mc.eliminado=:eliminado "
				+ sqlQueryRegionWhere + " group by se.anio");
		
		query.setParameter("anio", 2018);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
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
				
				
		resultadosSisvig = query.list();
		
		//Agregar sisvig a los historicos
		for(Object[] dato: resultadosSisvig) {
			resultadosHistoricos.add(dato);
		}
		
		return resultadosHistoricos;
		
	}
	
	/**
	 * Regresa datos de casos confirmados por provincia
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosCasosxOU(Integer anio, String oulevel,String ouname) {
		
		List<Object[]> resultadosTemp = new ArrayList<Object[]>();
		String sqlQueryRegionVista = "";
		String sqlQueryTiempoWhere = "";
		String sqlQueryRegionWhere = "";
		String sqlQueryGroupBY = "";
		
		/**
		 * Regresa datos de positivos de SISVIG
		 * 
		 */
		
		if(oulevel.equals("ALL")) {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.provincia.ident, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere="";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.provincia.ident";
		}
		//No implementado otras regiones asi que repite
		else {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.provincia.ident, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere="";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.provincia.ident";
		}
		/*Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.name, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident =:ouname ";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.name";
		}
		//Por Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.name, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.provincia.ident =:ouname ";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.name";
		}
		//Por Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.name, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.ident =:ouname ";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.name";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.name, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.ident =:ouname ";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.name";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.name, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.ident =:ouname ";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.name";
		}*/
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setParameter("anio", anio);
		query.setParameter("eliminado", false);
		query.setParameter("estado", "confirmado");
		
		/*Parámetro región
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
		}*/
		// Retrieve all
		resultadosTemp.addAll(query.list());

		return resultadosTemp;
	}	
	
	
}
