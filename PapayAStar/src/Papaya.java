

public class Papaya {

	private double peso;
	private String id;
	private double diasAlm;

	public Papaya (double d, String ident, double e) {
		this.peso=d;
		this.id=ident;
		this.diasAlm=e;
	}
	
	public Papaya() {
		// TODO Auto-generated constructor stub
	}

	public void setPeso(double pso) {
		this.peso=pso;
	}
	
	public double getPeso() {
		return this.peso;
	}
	
	public void setId(String ident) {
		this.id=ident;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setDiasAlm(double dias) {
		this.diasAlm=dias;
	}
	
	public double getDiasAlm() {
		return this.diasAlm;
	}

}
