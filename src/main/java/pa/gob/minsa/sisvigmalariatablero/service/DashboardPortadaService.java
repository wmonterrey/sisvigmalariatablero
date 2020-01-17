package pa.gob.minsa.sisvigmalariatablero.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pa.gob.minsa.sisvigmalariatablero.utils.DatosTiempo;

/**
 * Servicio para la portada del dashboard
 * 
 * @author William Aviles
 * 
 **/

@Service("dashboardPortadaService")
@Transactional
public class DashboardPortadaService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa años con registros en la base de datos
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getAniosDB() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("SELECT mc.anioEpi FROM MalariaCaso mc where mc.anioEpi >= 2018 GROUP BY mc.anioEpi order by mc.anioEpi desc");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa datos de casos confirmados por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<DatosTiempo> getDatosCasosxPeriodo(Integer anio, String oulevel,String ouname,String timeview) {
		List<DatosTiempo> resultadoF = new ArrayList<DatosTiempo>();
		List<Integer> resultadosTemp = new ArrayList<Integer>();
		String sqlQueryTiempoVista = "";
		String sqlQueryTiempoWhere = "";
		String sqlQueryRegionWhere = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//Inicializa calendario
		Calendar c = Calendar.getInstance();
		if(anio==2018) {
			c.set(anio, 0, 1, 0, 0, 0); 
			c.add(Calendar.DATE, -1); //Firt day of epi year
		}
		else if(anio==2019) {
			c.set(anio, 0, 1, 0, 0, 0); 
			c.add(Calendar.DATE, -2);//Firt day of epi year
		}else {
			c.set(anio, 0, 1, 0, 0, 0); //Firt day of year
		}
		/**
		 * Regresa datos de positivos de SISVIG
		 * 
		 */
		if(timeview.equals("week.samp")) {
			sqlQueryTiempoVista = "SELECT se.semana";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}else if(timeview.equals("month.samp")) {
			sqlQueryTiempoVista = "SELECT month(mc.pdrfecha)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}else if(timeview.equals("day.samp")) {
			sqlQueryTiempoVista = "SELECT mc.pdrfecha";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}
		
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
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(sqlQueryTiempoVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and mc.estado=:estado ");
		query.setParameter("anio", anio);
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
		// Retrieve all
		resultadosTemp.addAll(query.list());

		//Si quiere verse por semana inicializar la lista
		if(timeview.equals("week.samp")) {
			for (int i = 0 ; i < 53 ; i++){
				DatosTiempo dato = new DatosTiempo(String.valueOf(i+1),anio,0,0,0);
				resultadoF.add(dato);
			}
		}
		//Si quiere verse por mes inicializar la lista
		else if(timeview.equals("month.samp")) {
			for (int i = 0 ; i < 12 ; i++){
				DatosTiempo dato = new DatosTiempo(String.valueOf(i+1),anio,0,0,0);
				resultadoF.add(dato);
			}
		}
		//Si quiere verse por día inicializar la lista
		else if(timeview.equals("day.samp")) {
			for (int i = 0 ; i < 367 ; i++){
				DatosTiempo dato = new DatosTiempo(sdf.format(c.getTime()),anio,0,0,0);
				resultadoF.add(dato);
				c.add(Calendar.DATE, 1);
			}
		}
		
		//Agregar los datos al resultado final
		for(DatosTiempo dato:resultadoF) {
			for(Object semana:resultadosTemp) {
				if(timeview.equals("day.samp")) semana=semana.toString().substring(0, 10);
				if(dato.getPeriodo().equals(semana.toString())) {
					dato.setDato1(dato.getDato1()+1);
				}
			}
		}
		return resultadoF;
	}
	
	
	/**
	 * Regresa datos de muestras por período
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<DatosTiempo> getDatosMuestrasxPeriodo(Integer anio, String oulevel,String ouname,String timeview) {
		List<DatosTiempo> resultadoF = new ArrayList<DatosTiempo>();
		List<Integer> resultadosTemp = new ArrayList<Integer>();
		String sqlQueryTiempoVista = "";
		String sqlQueryTiempoWhere = "";
		String sqlQueryRegionWhere = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query;
		
		//Inicializa calendario
		Calendar c = Calendar.getInstance();
		if(anio==2018) {
			c.set(anio, 0, 1, 0, 0, 0); 
			c.add(Calendar.DATE, -1); //Firt day of epi year
		}
		else if(anio==2019) {
			c.set(anio, 0, 1, 0, 0, 0); 
			c.add(Calendar.DATE, -2);//Firt day of epi year
		}else {
			c.set(anio, 0, 1, 0, 0, 0); //Firt day of year
		}
		
		/**
		 * Regresa datos de muestras históricos
		 * 
		 */
		//Define en el query la vista de tiempo
		if(timeview.equals("week.samp")) {
			sqlQueryTiempoVista = "SELECT se.semana";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.fecha and se.fechaFin >= mc.fecha and se.anio=:anio";
		}else if(timeview.equals("month.samp")) {
			sqlQueryTiempoVista = "SELECT month(mc.fecha)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.fecha and se.fechaFin >= mc.fecha and se.anio=:anio";
		}else if(timeview.equals("day.samp")) {
			sqlQueryTiempoVista = "SELECT mc.fecha";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.fecha and se.fechaFin >= mc.fecha and se.anio=:anio";
		}
		//País
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.ident =:ouname ";
		}
		
		
		
		// Create a Hibernate query (HQL)
		query = session.createQuery(sqlQueryTiempoVista 
				+ " FROM Muestras mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " )");
		query.setParameter("anio", anio);
		//Parámetro region
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
		
		// Retrieve all
		resultadosTemp.addAll(query.list());
		//Si quiere verse por semana inicializar la lista
		if(timeview.equals("week.samp")) {
			for (int i = 0 ; i < 53 ; i++){
				DatosTiempo dato = new DatosTiempo(String.valueOf(i+1),anio,0,0,0);
				resultadoF.add(dato);
			}
		}
		//Si quiere verse por mes inicializar la lista
		else if(timeview.equals("month.samp")) {
			for (int i = 0 ; i < 12 ; i++){
				DatosTiempo dato = new DatosTiempo(String.valueOf(i+1),anio,0,0,0);
				resultadoF.add(dato);
			}
		}
		//Si quiere verse por día inicializar la lista
		else if(timeview.equals("day.samp")) {
			for (int i = 0 ; i < 367 ; i++){
				DatosTiempo dato = new DatosTiempo(sdf.format(c.getTime()),anio,0,0,0);
				resultadoF.add(dato);
				c.add(Calendar.DATE, 1);
			}
		}
		//Agregar los valores
		for(DatosTiempo dato:resultadoF) {
			for(Object semana:resultadosTemp) {
				if(timeview.equals("day.samp")) semana=semana.toString().substring(0, 10);
				if(dato.getPeriodo().equals(semana.toString())) {
					dato.setDato1(dato.getDato1()+1);
				}
			}
		}
		
		
		/**
		 * Regresa datos de muestras de SISVIG
		 * 
		 */
		if(timeview.equals("week.samp")) {
			sqlQueryTiempoVista = "SELECT se.semana";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}else if(timeview.equals("month.samp")) {
			sqlQueryTiempoVista = "SELECT month(mc.pdrfecha)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}else if(timeview.equals("day.samp")) {
			sqlQueryTiempoVista = "SELECT mc.pdrfecha";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}
		//País
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Por región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Por provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Por distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.ident =:ouname ";
		}
		//Por corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.ident =:ouname ";
		}
		//Por localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.ident =:ouname ";
		}
				
		// Create a Hibernate query (HQL)
		query = session.createQuery(sqlQueryTiempoVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and (mc.pruebaRapida=1 or mc.labResultado is not null)");
		query.setParameter("anio", anio);
		query.setParameter("eliminado", false);
		
		//parámetro región
		if(oulevel.equals("region.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//parámetro provincia
		else if(oulevel.equals("province.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//parámetro distrito
		else if(oulevel.equals("district.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//parámetro corregimiento
		else if(oulevel.equals("correg.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		//parámetro localidad
		else if(oulevel.equals("local.samp")) {
			query.setParameter("ouname", Integer.valueOf(ouname));
		}
		
		// Retrieve all
		resultadosTemp.clear();
		resultadosTemp.addAll(query.list());
		
		//Ya las listas estan inicializadas, solo agregar los datos de muestras de SISVIG
		
		for(DatosTiempo dato:resultadoF) {
			for(Object semana:resultadosTemp) {
				if(timeview.equals("day.samp")) semana=semana.toString().substring(0, 10);
				if(dato.getPeriodo().equals(semana.toString())) {
					dato.setDato1(dato.getDato1()+1);
				}
			}
		}
		
		return resultadoF;
	}
	

	@SuppressWarnings("unchecked")
	public Integer getLocalidadescBusqueda(Integer anio, String oulevel,String ouname,String timeview) {
		String sqlQueryTiempoVista = "";
		String sqlQueryTiempoWhere = "";
		String sqlQueryRegionWhere = "";
		List<Integer> localidadesHistoricas = new ArrayList<Integer>();
		List<Integer> localidadesSisvig = new ArrayList<Integer>();
		Session session = sessionFactory.getCurrentSession();
		
		//Muestras históricas
		//País
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.distrito.ident =:ouname ";
		}
		//Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.corregimiento.ident =:ouname ";
		}
		//Localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.localidad.ident =:ouname ";
		}
		
		Query query = session.createQuery("Select mc.localidad.ident FROM Muestras mc where year(mc.fecha)=:anio " + sqlQueryRegionWhere
				+ "group by mc.localidad.ident");
		query.setParameter("anio", anio);
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
		
		
		localidadesHistoricas = query.list();
		
		//Muestras sisvig
		
		if(timeview.equals("week.samp")) {
			sqlQueryTiempoVista = "SELECT mc.pdrMuestraLocalidad.ident ";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}else if(timeview.equals("month.samp")) {
			sqlQueryTiempoVista = "SELECT mc.pdrMuestraLocalidad.ident ";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}else if(timeview.equals("day.samp")) {
			sqlQueryTiempoVista = "SELECT mc.pdrMuestraLocalidad.ident ";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
		}
		//País
		if(oulevel.equals("ALL")) {
			sqlQueryRegionWhere="";
		}
		//Región
		else if(oulevel.equals("region.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident =:ouname ";
		}
		//Provincia
		else if(oulevel.equals("province.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.region.provincia.ident =:ouname ";
		}
		//Distrito
		else if(oulevel.equals("district.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.distrito.ident =:ouname ";
		}
		//Corregimiento
		else if(oulevel.equals("correg.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.corregimiento.ident =:ouname ";
		}	
		//Localidad
		else if(oulevel.equals("local.samp")) {
			sqlQueryRegionWhere = " and mc.pdrMuestraLocalidad.ident =:ouname ";
		}		
		
		query = session.createQuery(sqlQueryTiempoVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and (mc.pruebaRapida=1 or mc.labResultado is not null) group by mc.pdrMuestraLocalidad.ident");
		query.setParameter("anio", anio);
		query.setParameter("eliminado", false);
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
		
		//Total de muestras en SISVIG
		localidadesSisvig = query.list();
		
		for(Integer otraloc:localidadesSisvig) {
			if(!localidadesHistoricas.contains(otraloc)) {
				localidadesHistoricas.add(otraloc);
			}
		}
		
		//Regresa muestras historicas mas SISVIG
		return localidadesHistoricas.size();
		
	}
	
	
	/**
	 * Regresa datos de casos confirmados por OU
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
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.region.name, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere="";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.region.name";
		}
		//Por región
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
		}
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setParameter("anio", anio);
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
		// Retrieve all
		resultadosTemp.addAll(query.list());

		return resultadosTemp;
	}	
	
	
	/**
	 * Regresa datos de casos confirmados por provincia
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosCasosxProv(Integer anio, String oulevel,String ouname) {
		
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
		//Por región
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
		}
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setParameter("anio", anio);
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
		// Retrieve all
		resultadosTemp.addAll(query.list());

		return resultadosTemp;
	}	
	
	/**
	 * Regresa datos de casos confirmados por provincia
	 * 
	 * @return lista de objetos
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDatosCasosxRegion(Integer anio, String oulevel,String ouname) {
		
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
			sqlQueryRegionVista = "SELECT mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident, count(mc.id)";
			sqlQueryTiempoWhere = "se.fechaIni <= mc.pdrfecha and se.fechaFin >= mc.pdrfecha and se.anio=:anio";
			sqlQueryRegionWhere="";
			sqlQueryGroupBY = "group by mc.pdrMuestraLocalidad.corregimiento.distrito.region.ident";
		}
		//Por región
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
		}
		
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(sqlQueryRegionVista 
				+ " FROM MalariaCaso mc, SemanaEpidemiologica se where " + sqlQueryTiempoWhere + sqlQueryRegionWhere
				+ " and mc.eliminado=:eliminado and mc.estado=:estado " + sqlQueryGroupBY);
		query.setParameter("anio", anio);
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
		// Retrieve all
		resultadosTemp.addAll(query.list());

		return resultadosTemp;
	}	
	
	
}
