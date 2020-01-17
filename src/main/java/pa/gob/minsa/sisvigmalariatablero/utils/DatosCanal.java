package pa.gob.minsa.sisvigmalariatablero.utils;

public class DatosCanal {
	
	private Integer semana;
	private Integer anio;
	private Integer casos;
	private double q1;
	private double q2;
	private double q3;
	
	
	
	
	public DatosCanal(Integer semana, Integer anio) {
		super();
		this.semana = semana;
		this.anio = anio;
	}
	
	
	
	public DatosCanal(Integer semana, Integer anio, Integer casos, double q1, double q2, double q3) {
		super();
		this.semana = semana;
		this.anio = anio;
		this.casos = casos;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
	}



	public Integer getSemana() {
		return semana;
	}
	public void setSemana(Integer semana) {
		this.semana = semana;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public double getQ1() {
		return q1;
	}
	public void setQ1(double q1) {
		this.q1 = q1;
	}
	public double getQ2() {
		return q2;
	}
	public void setQ2(double q2) {
		this.q2 = q2;
	}
	public double getQ3() {
		return q3;
	}
	public void setQ3(double q3) {
		this.q3 = q3;
	}
	public Integer getCasos() {
		return casos;
	}
	public void setCasos(Integer casos) {
		this.casos = casos;
	}
	
	
	
	
	

}
