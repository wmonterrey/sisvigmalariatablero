package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "cat_distrito", catalog = "sisvigdb")
public class Distrito implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer ident;
	private String name;
	private String codRefMinsa;
	private Provincia provincia;
	private Region region;
	
	

	@Id
	@Column(name = "id_distrito", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	
	@Column(name = "nombre_distrito", nullable = false, length=100)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "cod_ref_minsa", nullable = false, length=10)
	public String getCodRefMinsa() {
		return codRefMinsa;
	}


	public void setCodRefMinsa(String codRefMinsa) {
		this.codRefMinsa = codRefMinsa;
	}
	
	

	@ManyToOne(optional=false)
	@JoinColumn(name="id_provincia")
	public Provincia getProvincia() {
		return provincia;
	}


	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="id_region")
	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}
	
	
	@Override
	public String toString(){
		return this.getName();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Distrito))
			return false;
		
		Distrito castOther = (Distrito) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
