package pa.gob.minsa.sisvigmalariatablero.domain;

public class ItemLocalidad {

	private Integer id;
	private String text;
	private Localidad localidad;
	
	
	
	public ItemLocalidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	

	

}
