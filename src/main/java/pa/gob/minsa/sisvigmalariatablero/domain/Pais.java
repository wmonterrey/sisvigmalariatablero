package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;




@Entity
@Table(name = "cat_pais", catalog = "sisvigdb")
public class Pais implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer ident;
	private String name;
	
	

	@Id
	@Column(name = "id_pais", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	
	@Column(name = "nombre_pais", nullable = false, length=100)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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
		if (!(other instanceof Pais))
			return false;
		
		Pais castOther = (Pais) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
