package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "cat_provincia", catalog = "sisvigdb")
public class Provincia implements Serializable, Auditable{
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
	
	

	@Id
	@Column(name = "id_provincia", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	
	@Column(name = "nombre_provincia", nullable = false, length=100)
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
		if (!(other instanceof Provincia))
			return false;
		
		Provincia castOther = (Provincia) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
