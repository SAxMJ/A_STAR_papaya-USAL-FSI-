import java.util.ArrayList;

public class Nodo {

	
	private Bandeja bandeja;
	private Nodo nodoAntecesor;
	private ArrayList<Nodo> nodosSucesores;
	private ArrayList<Papaya> listaProduccionNodo;
	private double profundidad;
	private double heuristica;
	private double funcionEvaluacion;
	
	public Nodo (Nodo nodoAnt, ArrayList<Nodo> nodosSuc,ArrayList<Papaya> listaProd ,int prof, double heu, double funcEv, Bandeja bandeja) {
		this.setNodoAntecesor(nodoAnt);
		this.setNodosSucesores(nodosSuc);
		this.setListaProduccionNodo(listaProd);
		this.setProfundidad(prof);
		this.setHeuristica(heu);
		this.setFuncionEvaluacion(funcEv);
		this.setBandeja(bandeja);
	}

	public Nodo() {
		
	}

	public void setListaProduccionNodo(ArrayList<Papaya> listaProd) {
		this.listaProduccionNodo=listaProd;
	}
	
	public ArrayList<Papaya> getListaProduccionNodo() {
		return listaProduccionNodo;
	}

	public Nodo getNodoAntecesor() {
		return nodoAntecesor;
	}

	public void setNodoAntecesor(Nodo nodoAntecesor) {
		this.nodoAntecesor = nodoAntecesor;
	}

	public ArrayList<Nodo> getNodosSucesores() {
		return nodosSucesores;
	}

	public void setNodosSucesores(ArrayList<Nodo> nodosSucesores) {
		this.nodosSucesores = nodosSucesores;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}

	public double getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(double heuristica) {
		this.heuristica = heuristica;
	}

	public double getFuncionEvaluacion() {
		return funcionEvaluacion;
	}

	public void setFuncionEvaluacion(double funcionEvaluacion) {
		this.funcionEvaluacion = funcionEvaluacion;
	}
	
	
	public Bandeja getBandeja() {
		return bandeja;
	}

	public void setBandeja(Bandeja band) {
		this.bandeja = band;
	}
}
