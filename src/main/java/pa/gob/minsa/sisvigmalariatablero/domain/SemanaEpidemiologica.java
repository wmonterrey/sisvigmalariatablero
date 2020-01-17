package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "semana_epi", catalog = "sisvigdb")
public class SemanaEpidemiologica implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer semana;
	private Integer anio;
	private Date fechaIni;
	private Date fechaFin;
	
	
	

	@Id
	@Column(name = "semana", nullable = false)
	public Integer getSemana() {
		return semana;
	}


	public void setSemana(Integer semana) {
		this.semana = semana;
	}
	
	

	
	@Id
	@Column(name = "anio", nullable = false)
	public Integer getAnio() {
		return anio;
	}


	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	
	@Column(name = "fecha_inicio", nullable = false)
	public Date getFechaIni() {
		return fechaIni;
	}


	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	@Column(name = "fecha_fin", nullable = false)
	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getSemana().toString();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SemanaEpidemiologica))
			return false;
		
		SemanaEpidemiologica castOther = (SemanaEpidemiologica) other;

		return (this.getSemana().equals(castOther.getSemana()) && this.getAnio().equals(castOther.getAnio()));
	}

}
