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
@Table(name = "notic_form", catalog = "sisvigdb")
public class NoticForm implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	
	private Integer idNotic;
	private Integer perAsegurado;
	private Integer ident;
	private String numeroIdent;
	
	private Integer perEdad;
	private Integer perTipoEdad;
	
	private Pais perIdPais;
	
	private Integer perContagio;
	private Pais idPaisContagio;
	private Integer idCorregimientoContagio;
	
	private Integer idDiagnostico;
	private Integer estadoDiagnostico;
	
	private Date fis;
	private Integer semanaEpi;
	private Integer anioEpi;
	
	private UnidadNotificadora idUn;
	
	private String nombreReporta;
	private Integer idCargo = 0;
	private String nombreRegistra;
	private String institucionRegistra;
	
	private Date fechaFormulario;
	private Date fechaNotificacion;
	
	private Integer idEventoNotificacion=0;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_notic", updatable = false, nullable = false)
	public Integer getIdNotic() {
		return idNotic;
	}


	public void setIdNotic(Integer idNotic) {
		this.idNotic = idNotic;
	}

	@Column(name = "tipo_identificacion", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	@Column(name = "numero_identificacion", nullable = false, length=30)
	public String getNumeroIdent() {
		return numeroIdent;
	}


	public void setNumeroIdent(String numeroIdent) {
		this.numeroIdent = numeroIdent;
	}

	

	@Column(name = "per_asegurado", nullable = false)
	public Integer getPerAsegurado() {
		return perAsegurado;
	}

	public void setPerAsegurado(Integer perAsegurado) {
		this.perAsegurado = perAsegurado;
	}

	@Column(name = "per_edad", nullable = false)
	public Integer getPerEdad() {
		return perEdad;
	}


	public void setPerEdad(Integer perEdad) {
		this.perEdad = perEdad;
	}

	@Column(name = "per_tipo_edad", nullable = false)
	public Integer getPerTipoEdad() {
		return perTipoEdad;
	}


	public void setPerTipoEdad(Integer perTipoEdad) {
		this.perTipoEdad = perTipoEdad;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="per_id_pais")
	public Pais getPerIdPais() {
		return perIdPais;
	}


	public void setPerIdPais(Pais perIdPais) {
		this.perIdPais = perIdPais;
	}

	@Column(name = "per_contagio", nullable = true)
	public Integer getPerContagio() {
		return perContagio;
	}


	public void setPerContagio(Integer perContagio) {
		this.perContagio = perContagio;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="id_pais_contagio")
	public Pais getIdPaisContagio() {
		return idPaisContagio;
	}


	public void setIdPaisContagio(Pais idPaisContagio) {
		this.idPaisContagio = idPaisContagio;
	}
	
	
	@Column(name = "id_corregimiento_contagio", nullable = false)
	public Integer getIdCorregimientoContagio() {
		return idCorregimientoContagio;
	}


	public void setIdCorregimientoContagio(Integer idCorregimientoContagio) {
		this.idCorregimientoContagio = idCorregimientoContagio;
	}


	@Column(name = "id_diagnostico1", nullable = false)
	public Integer getIdDiagnostico() {
		return idDiagnostico;
	}


	public void setIdDiagnostico(Integer idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}

	@Column(name = "estado_diag1", nullable = false)
	public Integer getEstadoDiagnostico() {
		return estadoDiagnostico;
	}


	public void setEstadoDiagnostico(Integer estadoDiagnostico) {
		this.estadoDiagnostico = estadoDiagnostico;
	}

	@Column(name = "fecha_inicio_sintomas", nullable = true)
	public Date getFis() {
		return fis;
	}


	public void setFis(Date fis) {
		this.fis = fis;
	}

	@Column(name = "semana_epi", nullable = true)
	public Integer getSemanaEpi() {
		return semanaEpi;
	}


	public void setSemanaEpi(Integer semanaEpi) {
		this.semanaEpi = semanaEpi;
	}

	@Column(name = "anio", nullable = true)
	public Integer getAnioEpi() {
		return anioEpi;
	}


	public void setAnioEpi(Integer anioEpi) {
		this.anioEpi = anioEpi;
	}

	@ManyToOne(optional=true)
	@JoinColumn(name="id_un")
	public UnidadNotificadora getIdUn() {
		return idUn;
	}


	public void setIdUn(UnidadNotificadora idUn) {
		this.idUn = idUn;
	}

	@Column(name = "nombre_reporta", nullable = false, length=45)
	public String getNombreReporta() {
		return nombreReporta;
	}


	public void setNombreReporta(String nombreReporta) {
		this.nombreReporta = nombreReporta;
	}

	@Column(name = "id_cargo", nullable = false)
	public Integer getIdCargo() {
		return idCargo;
	}


	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	@Column(name = "nombre_registra", nullable = false, length=45)
	public String getNombreRegistra() {
		return nombreRegistra;
	}


	public void setNombreRegistra(String nombreRegistra) {
		this.nombreRegistra = nombreRegistra;
	}

	
	@Column(name = "institucion_registra", nullable = false, length=45)
	public String getInstitucionRegistra() {
		return institucionRegistra;
	}


	public void setInstitucionRegistra(String institucionRegistra) {
		this.institucionRegistra = institucionRegistra;
	}

	@Column(name = "fecha_formulario", nullable = false)
	public Date getFechaFormulario() {
		return fechaFormulario;
	}


	public void setFechaFormulario(Date fechaFormulario) {
		this.fechaFormulario = fechaFormulario;
	}

	@Column(name = "fecha_notificacion", nullable = false)
	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}


	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	
	
	@Column(name = "id_evento_padre", nullable = false)
	public Integer getIdEventoNotificacion() {
		return idEventoNotificacion;
	}


	public void setIdEventoNotificacion(Integer idEventoNotificacion) {
		this.idEventoNotificacion = idEventoNotificacion;
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
		if (!(other instanceof NoticForm))
			return false;
		
		NoticForm castOther = (NoticForm) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
