package pa.gob.minsa.sisvigmalariatablero.domain;

public class ItemUnidad {

	private Integer id;
	private String text;
	private UnidadNotificadora unidad;
	
	
	
	public ItemUnidad() {
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
	public UnidadNotificadora getUnidad() {
		return unidad;
	}
	public void setUnidad(UnidadNotificadora unidad) {
		this.unidad = unidad;
	}
	

	

}
