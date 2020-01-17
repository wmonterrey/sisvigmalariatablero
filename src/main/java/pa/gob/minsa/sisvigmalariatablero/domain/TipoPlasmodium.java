package pa.gob.minsa.sisvigmalariatablero.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;



@Entity
@Table(name = "malaria_tipo_parasitos", catalog = "sisvigdb")
public class TipoPlasmodium implements Serializable, Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private Integer ident;
	private String name;
	private Integer pdr;
	private Integer lab;
	private Integer idEvento;
	private String estado;
	
	
	

	@Id
	@Column(name = "id", nullable = false)
	public Integer getIdent() {
		return ident;
	}


	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	
	@Column(name = "plasmodium", nullable = false, length=500)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Column(name = "es_pdr", nullable = false)
	public Integer getPdr() {
		return pdr;
	}


	public void setPdr(Integer pdr) {
		this.pdr = pdr;
	}

	@Column(name = "es_lab", nullable = false)
	public Integer getLab() {
		return lab;
	}


	public void setLab(Integer lab) {
		this.lab = lab;
	}

	@Column(name = "id_evento", nullable = false)
	public Integer getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}

	@Column(name="estado", columnDefinition="enum('confirmado','descartado','sospechoso')")
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
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
		if (!(other instanceof TipoPlasmodium))
			return false;
		
		TipoPlasmodium castOther = (TipoPlasmodium) other;

		return (this.getIdent().equals(castOther.getIdent()));
	}
	

}
