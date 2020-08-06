package pa.gob.minsa.sisvigmalariatablero.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pa.gob.minsa.sisvigmalariatablero.users.model.Authority;
import pa.gob.minsa.sisvigmalariatablero.users.model.Rol;
import pa.gob.minsa.sisvigmalariatablero.users.model.UserAccess;
import pa.gob.minsa.sisvigmalariatablero.users.model.UserSistema;

/**
 * Servicio para el objeto UserSistema
 * 
 * @author William Aviles
 * 
 **/

@Service("usuarioService")
@Transactional
public class UsuarioService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los usuarios
	 * 
	 * @return una lista de <code>UserSistema</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<UserSistema> getUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UserSistema");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los usuarios activos
	 * 
	 * @return una lista de <code>UserSistema</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<UserSistema> getActiveUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UserSistema us where us.enabled is true");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa un Usuario
	 * @param username Nombre del usuario. 
	 * @return un <code>UserSistema</code>
	 */

	public UserSistema getUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username =:username");
		query.setParameter("username",username);
		UserSistema user = (UserSistema) query.uniqueResult();
		return user;
	}
	
	/**
	 * Regresa un Usuario
	 * @param username Nombre del usuario. 
	 * @param email Email del usuario. 
	 * @return un <code>UserSistema</code>
	 */

	public UserSistema getUser(String username,String email) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username =:username and u.email =:email and u.enabled is true");
		query.setParameter("username",username);
		query.setParameter("email",email);
		UserSistema user = (UserSistema) query.uniqueResult();
		return user;
	}
	
	
	
	/**
	 * Verifica Credenciales
	 * @param username Nombre del usuario. 
	 * @return boolean
	 */

	public Boolean checkCredential(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username =:username");
		query.setParameter("username",username);
		UserSistema user = (UserSistema) query.uniqueResult();
		return user.getCredentialsNonExpired();
	}
	
	/**
	 * Verifica si tiene que cambiar contrasena
	 * @param username Nombre del usuario. 
	 * @return boolean
	 */

	public Boolean checkChangeCredential(String username) {
		// Retrieve session from Hibernate
		if(!username.equals("")) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.username =:username");
		query.setParameter("username",username);
		UserSistema user = (UserSistema) query.uniqueResult();
		return user.getChangePasswordNextLogin();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Guarda un usuario
	 * @param user El usuario. 
	 * 
	 */
	public void saveUser(UserSistema user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}
	
	/**
	 * Regresa los UserAccess
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>UserAccess</code>
	 */

	@SuppressWarnings("unchecked")
	public List<UserAccess> getUserAccess(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserAccess u where " +
				"u.usuario.username = '" + username + "' order by u.loginDate DESC");
		return query.list();
	}
	
	/**
	 * Regresa todos los roles de usuarios
	 * 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Rol> getRoles() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rol");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Guarda un rol del usuario
	 * @param rol El rol a guardar 
	 * 
	 */
	public void saveRoleUser(Authority rol) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rol);
	}
	
	/**
	 * Regresa todos los roles de usuarios
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Authority> getRolesUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth " +
				"where auth.authId.username =:username and auth.pasive ='0'");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los roles que no tenga el usuario
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Rol> getRolesNoTieneUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rol roles " +
				"where roles.authority not in (select auth.authId.authority from Authority auth where auth.authId.username =:username)");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un rol del usuario
	 * @param username Nombre del usuario.
	 * @param rol Nombre del usuario.  
	 * @return un <code>Authority</code>
	 */

	public Authority getRolUsuario(String username, String rol) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth " +
				"where auth.authId.username =:username and auth.authId.authority =:rol");
		query.setParameter("username",username);
		query.setParameter("rol",rol);
		Authority rolUsuario = (Authority) query.uniqueResult();
		// Retrieve 
		return  rolUsuario;
	}
	
	/**
	 * Regresa todos los roles del usuario
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Authority> getRolesUsuarioTodos(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Authority auth " +
				"where auth.authId.username =:username");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getActividadUsuario(Long desde, Long hasta){
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT users.username, users.completeName, enabled as habilitado, MAX(lastAccess) AS ultimo, "
				+ "COUNT(accesses.username) AS total, avg(If(ISNULL(logoutdate),RAND()*(2.2-2)+2 + 1,TIMESTAMPDIFF(MINUTE, loginDate, logoutdate))) as tiempo "
				+ "FROM users INNER JOIN accesses ON users.username=accesses.username " + 
				"WHERE DATE(loginDate) between :fechaInicio and :fechaFinal GROUP BY users.username;");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getInactividadUsuario(Long desde, Long hasta){
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("select users.username, users.completeName, enabled, lastAccess FROM users "
				+ "WHERE username NOT IN (SELECT username FROM accesses WHERE DATE(loginDate) BETWEEN :fechaInicio and :fechaFinal);");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPaginasVisitadas(Long desde, Long hasta){
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT messages.es,  COUNT(visitKey) as total FROM pagesvisited inner JOIN messages on pagesvisited.visitKey = messages.messageKey "
				+ "WHERE DATE(visitDate) between :fechaInicio and :fechaFinal GROUP BY messages.es;");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getActividadDia(Long desde, Long hasta){
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(loginDate) as fecha, COUNT(accesses.username) AS total "
				+ "FROM accesses " + 
				"WHERE DATE(loginDate) between :fechaInicio and :fechaFinal GROUP BY DATE(loginDate);");
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPaginasVisitadas(String user, Long desde, Long hasta){
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT messages.es,  COUNT(visitKey) as total FROM pagesvisited inner JOIN messages on pagesvisited.visitKey = messages.messageKey "
				+ "WHERE username=:user and DATE(visitDate) between :fechaInicio and :fechaFinal GROUP BY messages.es;");
		query.setParameter("user", user);
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		// Retrieve all
		return  query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getActividadDia(String user, Long desde, Long hasta){
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createSQLQuery("SELECT DATE(loginDate) as fecha, COUNT(accesses.username) AS total, avg(If(ISNULL(logoutdate),RAND()*(2.2-2)+2 + 1,TIMESTAMPDIFF(MINUTE, loginDate, logoutdate))) as tiempo "
				+ "FROM accesses " + 
				"WHERE username=:user and DATE(loginDate) between :fechaInicio and :fechaFinal GROUP BY DATE(loginDate);");
		query.setParameter("user", user);
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		// Retrieve all
		return  query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getUserAccess(String username, Long desde, Long hasta) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select username, loginDate, If(ISNULL(logoutdate),RAND()*(2.2-2)+2,TIMESTAMPDIFF(MINUTE, loginDate, logoutdate)) as tiempo FROM accesses where username=:user and DATE(loginDate) between :fechaInicio and :fechaFinal order by loginDate DESC");
		query.setParameter("user", username);
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		return query.list();
	}
	


}
