import java.util.ArrayList;

public class Cobot {
	
	
	 public static void main(String[] args) {
		
		Controlador controller=new Controlador();
		ArrayList<Nodo> grafo=new ArrayList<Nodo>();
		
		ArrayList<Papaya> listaProduccion=new ArrayList<Papaya>();
		listaProduccion=controller.addPapayasAlmacen();
		
		roboCobot(listaProduccion,grafo);
	
		
	 }
	 
	 private static void roboCobot(ArrayList<Papaya> papayasDisponibles, ArrayList<Nodo> grafo ) {
			
		 	int contadorDebuger=0;
			Controlador controller=new Controlador();
			
			ArrayList<Nodo> listaAbiertos=new ArrayList<Nodo>();
			//2 Crear una lista llamada CERRADOS que inicialmente estará vacía.
			ArrayList<Nodo> listaCerrados=new ArrayList<Nodo>();
			
			
			ArrayList<Papaya> listaProduccion=new ArrayList<Papaya>();
			Nodo primerNodoAbiertos;
			
			Bandeja bandeja=new Bandeja(null,0,0);
			
			//Inicializamos la lista de produccion con todas las papayas del almacen
			listaProduccion.addAll(papayasDisponibles);
			
			//1 Creamos el nodo origen o raiz con sus parámetros correspondientes
			Nodo source = new Nodo(null,null,listaProduccion,0,0,0,bandeja);
			
			//Añadimos el nodo origen a la lista de abiertos y al grafo
			listaAbiertos.add(source);
			//grafo.add(source);
			
			while(true) {
				 
				 if(listaAbiertos.isEmpty()) {
					 System.out.println("FALLO: La lista de abiertos se encuentra vacía");
					 controller.MostrarGrafo(grafo);
					 return;
				 }
				 
				 else {
					 
					 //Recogemos el primer nodo de la lista de abiertos (borramos de abiertos y metemos en cerrados)
					 primerNodoAbiertos=listaAbiertos.get(0);
					 listaAbiertos.remove(0);
					 listaCerrados.add(primerNodoAbiertos);
		
					 
					 if(primerNodoAbiertos.getBandeja().getPesoBandeja()>=2){
						 System.out.println("NODO: "+primerNodoAbiertos+" Contador: "+contadorDebuger+" EvaluacionF: "+primerNodoAbiertos.getFuncionEvaluacion()+" PesoActual: "+primerNodoAbiertos.getBandeja().getPesoBandeja() +" PrecioActual: "+primerNodoAbiertos.getBandeja().getPrecioBandeja());                                      
						 ArrayList<Papaya>papayitas=new ArrayList<Papaya>();
						 papayitas=primerNodoAbiertos.getBandeja().getContenidoPapayas();
						 
						 for(int i=0; i<papayitas.size(); i++) {
							 System.out.println(papayitas.get(i).getId()); 
							 listaProduccion.remove(papayitas.get(i));
						 }
						 primerNodoAbiertos.getBandeja().setPrecioBandeja( primerNodoAbiertos.getBandeja().getPrecioBandeja()+0.30);
						 grafo.add(primerNodoAbiertos);
						 break;
					 }
					 
					 //Vamos a calcular los posibles sucesores de nodo y los meteremos en abiertos
					 controller.ExpandirGrafo(primerNodoAbiertos,listaAbiertos,listaCerrados);
					 controller.ordenarAbiertos(listaAbiertos);
					 contadorDebuger++;
					 System.out.println("NODO: "+primerNodoAbiertos+" Contador: "+contadorDebuger+" EvaluacionF: "+primerNodoAbiertos.getFuncionEvaluacion()+" PesoActual: "+primerNodoAbiertos.getBandeja().getPesoBandeja() +" PrecioActual: "+primerNodoAbiertos.getBandeja().getPrecioBandeja());                                      			 
					 
					 
				 }
			 }
			 
			 if(!listaProduccion.isEmpty()) {
				 roboCobot(listaProduccion,grafo);
			 }
			 else {
				 controller.MostrarGrafo(grafo);
			 }
	 }

}
