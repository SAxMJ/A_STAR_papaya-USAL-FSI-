import java.util.ArrayList;

public class Bandeja {

	private ArrayList<Papaya> contenidoPapayas;
	private double pesoBandeja;  //H
	private double precioBandeja; //G
	
	
	
	public Bandeja (ArrayList<Papaya> listaPapayas, float pso, float prec) {
		this.contenidoPapayas=listaPapayas;
		this.pesoBandeja=pso;
		this.precioBandeja=prec;
	}
	
	public Bandeja() {
	}

	public ArrayList<Papaya> getContenidoPapayas() {
		return contenidoPapayas;
	}
	
	public void setContenidoPapayas(ArrayList<Papaya> listaPapayas) {
		this.contenidoPapayas=listaPapayas;
	}
	
	public double getPesoBandeja() {
		return pesoBandeja;
	}
	
	public void setPesoBandeja(double peso) {
		this.pesoBandeja=peso;
	}
	
	public double getPrecioBandeja() {
		return precioBandeja;
	}
	
	public void setPrecioBandeja(double precio) {
		this.precioBandeja=precio;
	}
}
