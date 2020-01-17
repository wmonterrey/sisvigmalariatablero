package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "malaria_investigaciones", catalog = "sisvigdb")
public class MalariaInvestigacion implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer id;
	private Integer idCaso;
	
	private Integer viaje30d;
	private String tipoCaso;
	private String origenInfeccion;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "id_caso", nullable = true)
	public Integer getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Integer idCaso) {
		this.idCaso = idCaso;
	}
	
	@Column(name = "viaje_30d", nullable = true)
	public Integer getViaje30d() {
		return viaje30d;
	}


	public void setViaje30d(Integer viaje30d) {
		this.viaje30d = viaje30d;
	}

	
	@Column(name="tipo_caso", nullable = true, columnDefinition="enum('autoctono','importado')")
	public String getTipoCaso() {
		return tipoCaso;
	}


	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}

	@Column(name="origen_infeccion", nullable = true, columnDefinition="enum('residencia','viaje')")
	public String getOrigenInfeccion() {
		return origenInfeccion;
	}


	public void setOrigenInfeccion(String origenInfeccion) {
		this.origenInfeccion = origenInfeccion;
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getIdCaso().toString();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MalariaInvestigacion))
			return false;
		
		MalariaInvestigacion castOther = (MalariaInvestigacion) other;

		return (this.getId()==castOther.getId());
	}
	
	

}
