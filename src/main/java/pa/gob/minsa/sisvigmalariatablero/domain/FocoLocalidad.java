package pa.gob.minsa.sisvigmalariatablero.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import pa.gob.minsa.sisvigmalariatablero.domain.audit.Auditable;

/**
 * Simple objeto de dominio que representa la relacion de las localidades para un foco
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "focilocalities", catalog = "sisvig_tablero")
public class FocoLocalidad extends BaseMetaData implements Auditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FocoLocalidadId focoLocalidadId;
	private Foco foco;
	private Localidad locality;
	
	public FocoLocalidad() {
	}
	
	public FocoLocalidad(FocoLocalidadId focoLocalidadId, Date recordDate, String recordUser) {
		super(recordDate, recordUser);
		this.focoLocalidadId = focoLocalidadId;
	}
	
	public FocoLocalidad(FocoLocalidadId focoLocalidadId, Foco foco, Localidad localidad
			, String recordUser, Date recordDate) {
		super(recordDate, recordUser);
		this.focoLocalidadId = focoLocalidadId;
		this.foco = foco;
		this.locality = localidad;
	}
	
	@Id
	public FocoLocalidadId getFocoLocalidadId() {
		return focoLocalidadId;
	}
	public void setFocoLocalidadId(FocoLocalidadId focoLocalidadId) {
		this.focoLocalidadId = focoLocalidadId;
	}
	
	@ManyToOne
	@JoinColumn(name="foco", insertable = false, updatable = false)
	@ForeignKey(name = "FL_FOCOS_FK")
	public Foco getFoco() {
		return foco;
	}
	
	public void setFoco(Foco foco) {
		this.foco = foco;
	}
	@ManyToOne
	@JoinColumn(name="locality", insertable = false, updatable = false)
	@ForeignKey(name = "FL_LOC_FK")
	public Localidad getLocalidad() {
		return locality;
	}
	public void setLocalidad(Localidad localidad) {
		this.locality = localidad;
	}
	
	@Override
	public String toString(){
		return locality.getName();
	}
	@Override
	public boolean isFieldAuditable(String fieldname) {
		//Campos no auditables en la tabla
		if(fieldname.matches("recordDate")||fieldname.matches("recordUser")){
			return false;
		}		
		return true;
	}

}
