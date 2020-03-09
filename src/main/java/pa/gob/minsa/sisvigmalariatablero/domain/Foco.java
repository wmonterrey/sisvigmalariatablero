package pa.gob.minsa.sisvigmalariatablero.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



/**
 * 
 * Foco
 * 
 *  
 * @author      William Aviles
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "foci", catalog = "sisvig_tablero", uniqueConstraints={@UniqueConstraint(columnNames = {"code","pasive"})})
public class Foco extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ident;
	private String code;
	private String name;
	
	
	public Foco() {
		super();
	}



	@Id
    @Column(name = "id", nullable = false, length = 50)
	public String getIdent() {
		return ident;
	}


	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	@Column(name = "code", nullable = false, length = 100)
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}


	@Column(name = "name", nullable = false, length = 500)
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
		return this.getCode();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Foco))
			return false;
		
		Foco castOther = (Foco) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
