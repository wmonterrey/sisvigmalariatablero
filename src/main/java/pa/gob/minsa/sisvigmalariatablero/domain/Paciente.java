package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "tbl_persona", catalog = "sisvigdb")
public class Paciente implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer ident;
	private String numeroIdent;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Date fechaNacimiento;
	private Integer tipoEdad;
	private Sexo sexo;
	private Etnia etnia;
	private boolean asegurado = false;
	
	private Pais nacionalidad;
	
	private Pais paisResidencia;
	private Corregimiento corregResidencia;
	private Region regionResidencia;
	private Localidad localidadResidencia; 
	
	private String dirReferencia;
	private String calle;
	private String casa;
	private String dirResidenciaFueraPais;
	
	
	
	
	
	

	@Id
	@Column(name = "tipo_identificacion", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	@Id
	@Column(name = "numero_identificacion", nullable = false, length=30)
	public String getNumeroIdent() {
		return numeroIdent;
	}


	public void setNumeroIdent(String numeroIdent) {
		this.numeroIdent = numeroIdent;
	}

	@Column(name = "primer_nombre", nullable = false, length=45)
	public String getPrimerNombre() {
		return primerNombre;
	}


	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}


	@Column(name = "segundo_nombre", nullable = true, length=45)
	public String getSegundoNombre() {
		return segundoNombre;
	}


	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	@Column(name = "primer_apellido", nullable = false, length=45)
	public String getPrimerApellido() {
		return primerApellido;
	}


	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	@Column(name = "segundo_apellido", nullable = true, length=45)
	public String getSegundoApellido() {
		return segundoApellido;
	}


	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	@Column(name = "fecha_nacimiento", nullable = false)
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	
	@Column(name = "tipo_edad", nullable = false)
	public Integer getTipoEdad() {
		return tipoEdad;
	}


	public void setTipoEdad(Integer tipoEdad) {
		this.tipoEdad = tipoEdad;
	}


	@ManyToOne(optional=true)
	@JoinColumn(name="id_pais_nacimiento")
	public Pais getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	@ManyToOne(optional=true)
	@JoinColumn(name="sexo")
	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	
	@ManyToOne(optional=true)
	@JoinColumn(name="id_etnia")
	public Etnia getEtnia() {
		return etnia;
	}


	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}


	

	@ManyToOne(optional=true)
	@JoinColumn(name="id_pais")
	public Pais getPaisResidencia() {
		return paisResidencia;
	}


	public void setPaisResidencia(Pais paisResidencia) {
		this.paisResidencia = paisResidencia;
	}


	@ManyToOne(optional=true)
	@JoinColumn(name="id_corregimiento")
	public Corregimiento getCorregResidencia() {
		return corregResidencia;
	}


	public void setCorregResidencia(Corregimiento corregResidencia) {
		this.corregResidencia = corregResidencia;
	}
	
	

	@ManyToOne(optional=true)
	@JoinColumn(name="id_region")
	public Region getRegionResidencia() {
		return regionResidencia;
	}


	public void setRegionResidencia(Region regionResidencia) {
		this.regionResidencia = regionResidencia;
	}


	@ManyToOne(optional=true)
	@JoinColumn(name="id_localidad")
	public Localidad getLocalidadResidencia() {
		return localidadResidencia;
	}


	public void setLocalidadResidencia(Localidad localidadResidencia) {
		this.localidadResidencia = localidadResidencia;
	}

	@Column(name = "dir_referencia", nullable = true, length=150)
	public String getDirReferencia() {
		return dirReferencia;
	}


	public void setDirReferencia(String dirReferencia) {
		this.dirReferencia = dirReferencia;
	}
	
	
	

	@Column(name = "dir_calle", nullable = true, length=40)
	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column(name = "dir_casa", nullable = true, length=10)
	public String getCasa() {
		return casa;
	}
	

	public void setCasa(String casa) {
		this.casa = casa;
	}

	@Column(name = "dir_residencia", nullable = true, length=500)
	public String getDirResidenciaFueraPais() {
		return dirResidenciaFueraPais;
	}


	public void setDirResidenciaFueraPais(String dirResidenciaFueraPais) {
		this.dirResidenciaFueraPais = dirResidenciaFueraPais;
	}
	
	

	@Column(name = "asegurado", nullable = true)
	public boolean getAsegurado() {
		return asegurado;
	}


	public void setAsegurado(boolean asegurado) {
		this.asegurado = asegurado;
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
		if (!(other instanceof Paciente))
			return false;
		
		Paciente castOther = (Paciente) other;

		return (this.getIdent().equals(castOther.getIdent())&&this.getNumeroIdent().equals(castOther.getNumeroIdent()));
	}
	

}
