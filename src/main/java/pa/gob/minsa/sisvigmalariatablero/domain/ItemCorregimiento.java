package pa.gob.minsa.sisvigmalariatablero.domain;

public class ItemCorregimiento {

	private Integer id;
	private String text;
	private Corregimiento corregimiento;
	
	
	
	public ItemCorregimiento() {
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
	public Corregimiento getCorregimiento() {
		return corregimiento;
	}
	public void setCorregimiento(Corregimiento corregimiento) {
		this.corregimiento = corregimiento;
	}
	

	

}
