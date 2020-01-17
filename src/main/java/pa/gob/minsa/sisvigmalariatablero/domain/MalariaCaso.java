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

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "malaria_casos", catalog = "sisvigdb")
public class MalariaCaso implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer id;
	
	private Integer ident;
	private String numeroIdent;
	private NoticForm idNotic;
	private String edadCumplida;
	
	private String estado;

	private Integer regionSalud;
	
	private Integer fiebre30d;
	private Integer viaje30d;
	
	private Date fis;
	private Date fechaNotificacion;
	
	private UnidadNotificadora idUn;
	
	private Integer pruebaRapida;
	private String pdrResultado;
	
	private Integer pdrParasito;
	private Date pdrfecha;
	
	private Integer pdrMuestraRegion;
	private Integer pdrMuestraCorregimiento;
	private Localidad pdrMuestraLocalidad;
	
	private String labResultado;
	private Date labFecha;
	private Integer labPlasmodium;
	
	
	private String funcionarioNombre;
	private String funcionarioClave;
	
	private String tipoBusqueda;
	
	private Integer semanaEpi;
	private Integer anioEpi;
	
	private boolean cerrado = false;
	private boolean eliminado;
	
	private Date fechaActualizacion;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "paciente_tipo_id", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	@Column(name = "paciente_numero_id", nullable = false, length=30)
	public String getNumeroIdent() {
		return numeroIdent;
	}


	public void setNumeroIdent(String numeroIdent) {
		this.numeroIdent = numeroIdent;
	}
	
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_notificacion_general")
	public NoticForm getIdNotic() {
		return idNotic;
	}

	public void setIdNotic(NoticForm idNotic) {
		this.idNotic = idNotic;
	}


	@Column(name="estado", nullable = true, columnDefinition="enum('sospechoso','confirmado')")
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	@Column(name="id_region_salud", nullable = true)
	public Integer getRegionSalud() {
		return regionSalud;
	}


	public void setRegionSalud(Integer regionSalud) {
		this.regionSalud = regionSalud;
	}


	@Column(name="edad_cumplida", nullable = true, length=20)
	public String getEdadCumplida() {
		return edadCumplida;
	}


	public void setEdadCumplida(String edadCumplida) {
		this.edadCumplida = edadCumplida;
	}


	@Column(name = "fiebre_30d", nullable = true)
	public Integer getFiebre30d() {
		return fiebre30d;
	}


	public void setFiebre30d(Integer fiebre30d) {
		this.fiebre30d = fiebre30d;
	}
	
	

	@Column(name = "viaje_30d", nullable = true)
	public Integer getViaje30d() {
		return viaje30d;
	}


	public void setViaje30d(Integer viaje30d) {
		this.viaje30d = viaje30d;
	}


	@Column(name = "fecha_inicio_sintomas", nullable = true)
	public Date getFis() {
		return fis;
	}


	public void setFis(Date fis) {
		this.fis = fis;
	}

	
	@Column(name = "fecha_notificacion", nullable = false)
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}


	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_un")
	public UnidadNotificadora getIdUn() {
		return idUn;
	}
	
	public void setIdUn(UnidadNotificadora idUn) {
		this.idUn = idUn;
	}
	
	@Column(name = "prueba_rapida", nullable = false)
	public Integer getPruebaRapida() {
		return pruebaRapida;
	}


	public void setPruebaRapida(Integer pruebaRapida) {
		this.pruebaRapida = pruebaRapida;
	}
	
	

	@Column(name="pdr_resultado", nullable = true, columnDefinition="enum('invalido','positivo','negativo')")
	public String getPdrResultado() {
		return pdrResultado;
	}


	public void setPdrResultado(String pdrResultado) {
		this.pdrResultado = pdrResultado;
	}
	
	
	
	@Column(name = "pdr_parasito", nullable = true)
	public Integer getPdrParasito() {
		return pdrParasito;
	}


	public void setPdrParasito(Integer pdrParasito) {
		this.pdrParasito = pdrParasito;
	}

	@Column(name = "pdr_fecha", nullable = false)
	public Date getPdrfecha() {
		return pdrfecha;
	}


	public void setPdrfecha(Date pdrfecha) {
		this.pdrfecha = pdrfecha;
	}

	@Column(name = "pdr_muestra_region", nullable = true)	
	public Integer getPdrMuestraRegion() {
		return pdrMuestraRegion;
	}


	public void setPdrMuestraRegion(Integer pdrMuestraRegion) {
		this.pdrMuestraRegion = pdrMuestraRegion;
	}

	@Column(name = "pdr_muestra_corregimiento", nullable = true)
	public Integer getPdrMuestraCorregimiento() {
		return pdrMuestraCorregimiento;
	}


	public void setPdrMuestraCorregimiento(Integer pdrMuestraCorregimiento) {
		this.pdrMuestraCorregimiento = pdrMuestraCorregimiento;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="pdr_muestra_id_localidad")
	public Localidad getPdrMuestraLocalidad() {
		return pdrMuestraLocalidad;
	}


	public void setPdrMuestraLocalidad(Localidad pdrMuestraLocalidad) {
		this.pdrMuestraLocalidad = pdrMuestraLocalidad;
	}

	@Column(name="lab_resultado", nullable = true, columnDefinition="enum('POSITIVO','NEGATIVO')")
	public String getLabResultado() {
		return labResultado;
	}


	public void setLabResultado(String labResultado) {
		this.labResultado = labResultado;
	}
	
	
	@Column(name = "lab_parasito", nullable = true)
	public Integer getLabPlasmodium() {
		return labPlasmodium;
	}


	public void setLabPlasmodium(Integer labPlasmodium) {
		this.labPlasmodium = labPlasmodium;
	}


	@Column(name = "lab_fecha", nullable = true)
	public Date getLabFecha() {
		return labFecha;
	}


	public void setLabFecha(Date labFecha) {
		this.labFecha = labFecha;
	}

	@Column(name = "funcionario_nombre", nullable = false, length=150)
	public String getFuncionarioNombre() {
		return funcionarioNombre;
	}


	public void setFuncionarioNombre(String funcionarioNombre) {
		this.funcionarioNombre = funcionarioNombre;
	}

	@Column(name = "funcionario_clave", nullable = false, length=150)
	public String getFuncionarioClave() {
		return funcionarioClave;
	}


	public void setFuncionarioClave(String funcionarioClave) {
		this.funcionarioClave = funcionarioClave;
	}

	@Column(name="tipo_busqueda", columnDefinition="enum('activa','pasiva','encuesta','reactiva')")
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}


	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	@Column(name = "semana_epi", nullable = true)
	public Integer getSemanaEpi() {
		return semanaEpi;
	}


	public void setSemanaEpi(Integer semanaEpi) {
		this.semanaEpi = semanaEpi;
	}

	@Column(name = "anio_epi", nullable = true)
	public Integer getAnioEpi() {
		return anioEpi;
	}


	public void setAnioEpi(Integer anioEpi) {
		this.anioEpi = anioEpi;
	}
	
	

	@Column(name = "cerrado", nullable = false)
	public boolean getCerrado() {
		return cerrado;
	}


	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}
	
	

	@Column(name = "eliminado", nullable = false)
	public boolean isEliminado() {
		return eliminado;
	}


	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}


	@Column(name = "fecha_actualizacion", nullable = false)
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}


	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getNumeroIdent();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MalariaCaso))
			return false;
		
		MalariaCaso castOther = (MalariaCaso) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	
	

}
