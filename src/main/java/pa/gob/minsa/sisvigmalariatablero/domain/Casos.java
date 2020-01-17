package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "casoshistoricos", catalog = "sisvig_tablero")
public class Casos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String id;
	private Date fecha;
	private Localidad localidad;
	private String tipoBusqueda;
	private Integer semana;
	private Integer anio;
	private String especie;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UniqueID", updatable = false, nullable = false)
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "fis", nullable = false)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@ManyToOne(optional=true)
	@JoinColumn(name="ID1_Loc_det")
	public Localidad getLocalidad() {
		return localidad;
	}


	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}


	@Column(name = "Busqueda_AP", nullable = false)
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}


	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	@Column(name = "semana", nullable = false)
	public Integer getSemana() {
		return semana;
	}


	public void setSemana(Integer semana) {
		this.semana = semana;
	}

	@Column(name = "anio", nullable = false)
	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}


	@Column(name = "Especie", nullable = false)
	public String getEspecie() {
		return especie;
	}


	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	
	
	

	

}
