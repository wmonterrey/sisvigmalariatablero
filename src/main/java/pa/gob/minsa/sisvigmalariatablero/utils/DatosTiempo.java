package pa.gob.minsa.sisvigmalariatablero.utils;

public class DatosTiempo {
	
	private String periodo;
	private Integer anio;
	private Integer dato1;
	private Integer dato2;
	private Integer dato3;
	private Integer dato4;
	
	
	
	public DatosTiempo(String periodo) {
		super();
		this.periodo = periodo;
	}
	
	
	
	public DatosTiempo(String periodo, Integer anio) {
		super();
		this.periodo = periodo;
		this.anio = anio;
	}
	
	



	public DatosTiempo(String periodo, Integer anio, Integer dato1) {
		super();
		this.periodo = periodo;
		this.anio = anio;
		this.dato1 = dato1;
	}



	public DatosTiempo(String periodo, Integer anio, Integer dato1, Integer dato2, Integer dato3) {
		super();
		this.periodo = periodo;
		this.anio = anio;
		this.dato1 = dato1;
		this.dato2 = dato2;
		this.dato3 = dato3;
	}



	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getDato1() {
		return dato1;
	}
	public void setDato1(Integer dato1) {
		this.dato1 = dato1;
	}
	public Integer getDato2() {
		return dato2;
	}
	public void setDato2(Integer dato2) {
		this.dato2 = dato2;
	}
	public Integer getDato3() {
		return dato3;
	}
	public void setDato3(Integer dato3) {
		this.dato3 = dato3;
	}



	public Integer getDato4() {
		return dato4;
	}



	public void setDato4(Integer dato4) {
		this.dato4 = dato4;
	}
	
	
	

}
