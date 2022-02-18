
import java.util.Comparator;


public class ComparadorOrdenador implements Comparator<Nodo>{

	@Override
	public int compare(Nodo nodo1, Nodo nodo2) {
	
		int compara= Double.compare(nodo1.getFuncionEvaluacion(),nodo2.getFuncionEvaluacion());
					 
		return compara;
			
	}
}
