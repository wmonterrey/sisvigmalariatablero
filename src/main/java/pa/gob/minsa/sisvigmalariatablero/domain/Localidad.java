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
@Table(name = "malaria_localidades", catalog = "sisvigdb")
public class Localidad implements Serializable, Auditable{
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
	private Corregimiento corregimiento;
	
	

	@Id
	@Column(name = "id", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	
	@Column(name = "nombre", nullable = false, length=100)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "codigo", nullable = false, length=10)
	public String getCodRefMinsa() {
		return codRefMinsa;
	}


	public void setCodRefMinsa(String codRefMinsa) {
		this.codRefMinsa = codRefMinsa;
	}

	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_corregimiento")
	public Corregimiento getCorregimiento() {
		return corregimiento;
	}


	public void setCorregimiento(Corregimiento corregimiento) {
		this.corregimiento = corregimiento;
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
		if (!(other instanceof Localidad))
			return false;
		
		Localidad castOther = (Localidad) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
