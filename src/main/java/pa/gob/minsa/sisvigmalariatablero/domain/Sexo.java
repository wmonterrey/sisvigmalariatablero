package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "cat_sexo", catalog = "sisvigdb")
public class Sexo implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private String ident;
	private String name;
	
	

	@Id
	@Column(name = "id_sexo", nullable = false)
	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}

	
	@Column(name = "descripcion", nullable = false, length=100)
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
		if (!(other instanceof Sexo))
			return false;
		
		Sexo castOther = (Sexo) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
