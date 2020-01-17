package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "cat_tipo_identidad", catalog = "sisvigdb")
public class TipoIdentidad implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer ident;
	private String name;
	private Integer status;
	
	
	
	

	public TipoIdentidad() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TipoIdentidad(Integer ident, String name, Integer status) {
		super();
		this.ident = ident;
		this.name = name;
		this.status = status;
	}


	@Id
	@Column(name = "id_tipo_identidad", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	
	@Column(name = "nombre_tipo", nullable = false, length=100)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
		if (!(other instanceof TipoIdentidad))
			return false;
		
		TipoIdentidad castOther = (TipoIdentidad) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
