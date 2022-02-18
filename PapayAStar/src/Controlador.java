import java.util.ArrayList;


public class Controlador {

	
	public ArrayList<Papaya> addPapayasAlmacen() {
		
		
		Papaya p1=new Papaya(0.273,  "1400001",  1.1); 
		Papaya p2=new Papaya(0.405,  "1400002",  1);
		Papaya p3=new Papaya(0.517,  "1400003",  1.1); 
		Papaya p4=new Papaya(0.533,  "1400004",  1.7); 
		Papaya p5=new Papaya(0.358,  "1400005",  1.5); 
		Papaya p6=new Papaya(0.562,  "1400006",  1.9); 
		Papaya p7=new Papaya(0.322,  "1400007",  2.4); 
		Papaya p8=new Papaya(0.494,  "1400008",  1.8); 
		Papaya p9=new Papaya(0.39,   "1400009",  1.6); 
		Papaya p10=new Papaya(0.281,  "1400010",  2.2); 
		Papaya p11=new Papaya(0.395,  "1400011",  2); 
		Papaya p12=new Papaya(0.407,  "1400012",  2); 
		Papaya p13=new Papaya(0.329,  "1400013",  3); 
		Papaya p14=new Papaya(0.629,  "1400014",  2.7); 
		Papaya p15=new Papaya(0.417,  "1400015",  1.2); 
		Papaya p16=new Papaya(0.278,  "1400016",  1.4); 
		Papaya p17=new Papaya(0.583,  "1400017",  2.2); 
		Papaya p18=new Papaya(0.598,  "1400018",  1.9); 
		Papaya p19=new Papaya(0.271,  "1400019",  1.6); 
		Papaya p20=new Papaya(0.265,  "1400020",  2.1);
		
		//Introducimos las papayas ordenadas por peso
		ArrayList<Papaya> almacenOrdPso=new ArrayList<Papaya>();
		{
			
			almacenOrdPso.add(p1);
			almacenOrdPso.add(p2);
			almacenOrdPso.add(p3);  
			almacenOrdPso.add(p4);
			almacenOrdPso.add(p5); 
			almacenOrdPso.add(p6);
			almacenOrdPso.add(p7);
			almacenOrdPso.add(p8);
			almacenOrdPso.add(p9);
			almacenOrdPso.add(p10);
			almacenOrdPso.add(p11);
			almacenOrdPso.add(p12);
			almacenOrdPso.add(p13);
			almacenOrdPso.add(p14);
			almacenOrdPso.add(p15);
			almacenOrdPso.add(p16);
			almacenOrdPso.add(p17);
			almacenOrdPso.add(p18);
			almacenOrdPso.add(p19);
			almacenOrdPso.add(p20);
		}			
			return almacenOrdPso;
	}
	
	
	public void ExpandirGrafo(Nodo nodoPadre, ArrayList<Nodo> listaAbiertos, ArrayList<Nodo> listaCerrados) {
		
		//G -> Es el coste real del camino recorrido para llegar desde el nodo origen precio(0) al nodo actual
		//H -> Es el valor heurístico del nodo a evaluar desde el nodo actual hasta el nodo destino peso(2)
		ArrayList<Nodo> listaSucesores = new ArrayList<Nodo>();
		ArrayList<Papaya>listaTemporal= new ArrayList<Papaya>();
		listaTemporal=nodoPadre.getListaProduccionNodo();
		Double heuristica;
		
		//De la lista de producción que tiene cada nodo padre lo que haremos será comprobar el peso actual y el de las papayas de tal forma que solo cogeremos las que podrían meterse en bandeja
		for(int i=0; i<nodoPadre.getListaProduccionNodo().size(); i++) {
			
			Nodo nodoSuc=new Nodo();
			Papaya papayaAComprobar=new Papaya();
			Bandeja newBandeja= new Bandeja();
			ArrayList<Papaya> listaProduccionNewNodo=new ArrayList<Papaya>();
			ArrayList<Papaya> listaPapayasBandeja=new ArrayList<Papaya>();
			
			
			//Recogemos la papaya correspondiente de la lista de producción y al peso que tenía la bandeja del anterior nodo le sumamos el peso de la nueva papaya
			papayaAComprobar=listaTemporal.get(i);
			
			newBandeja.setPesoBandeja(nodoPadre.getBandeja().getPesoBandeja()+papayaAComprobar.getPeso());
			//El precio que tendrá la bandeja será el correspondiente al que teniamos en el nodo padre mas el agregado por la nueva papaya
			newBandeja.setPrecioBandeja(nodoPadre.getBandeja().getPrecioBandeja() + (papayaAComprobar.getPeso() * 2 + 0.05 * papayaAComprobar.getDiasAlm() + 0.10) );
			
			if(nodoPadre.getBandeja().getPesoBandeja()>0) {
				listaPapayasBandeja.addAll(nodoPadre.getBandeja().getContenidoPapayas());
				listaPapayasBandeja.add(papayaAComprobar);
				newBandeja.setContenidoPapayas(listaPapayasBandeja);
			}
			else {
				listaPapayasBandeja.add(papayaAComprobar);
				newBandeja.setContenidoPapayas(listaPapayasBandeja);
			}
			//La lista de producción del nuevo nodo será la del padre menos la papaya que hemos cogido ahora
			listaProduccionNewNodo.addAll(nodoPadre.getListaProduccionNodo());
			listaProduccionNewNodo.remove(i);
			
			
			heuristica=CalcularHeuristica(newBandeja.getPesoBandeja());
			
			nodoSuc.setProfundidad(newBandeja.getPrecioBandeja()); //G
			nodoSuc.setHeuristica(heuristica); //H
			nodoSuc.setFuncionEvaluacion(nodoSuc.getProfundidad()+nodoSuc.getHeuristica()); //F(n)=G(n)+H(n)
			nodoSuc.setBandeja(newBandeja);
			nodoSuc.setListaProduccionNodo(listaProduccionNewNodo);
			nodoSuc.setNodoAntecesor(nodoPadre);
			
		
			if(!nodoEsAntecesorDelPadre(nodoSuc,nodoPadre)) {
			
				if (nodoEstaEnAbiertos(nodoSuc,listaAbiertos,nodoPadre)) { //nuevoSucesor, listas, padre
					//Añadimos el nuevo nodo a la lista de sucesores del padre
					listaSucesores.add(nodoSuc);
				} 
				else if(nodoEstaEnCerrados(nodoSuc,listaCerrados,nodoPadre)) {
					
					listaSucesores.add(nodoSuc);
					 
					Nodo posibleNuevoPadre = null;
					
					//Buscamos el posible padre
					for (Nodo n : listaCerrados) {
						if (n.getBandeja().getPesoBandeja()==nodoSuc.getBandeja().getPesoBandeja() && n.getBandeja().getPrecioBandeja()>nodoSuc.getBandeja().getPrecioBandeja()) {
							posibleNuevoPadre = n.getNodoAntecesor();
						}
					}
					
					//Calculamos los antecesores
					calcularNodosAntecesores(nodoSuc, nodoPadre, posibleNuevoPadre);
				}
				
				else {
					
					//Añadimos el nodo a la lista de abiertos
					listaSucesores.add(nodoSuc);
					listaAbiertos.add(nodoSuc);
				}
			}
		}	
	}
	
	
	public void ordenarAbiertos(ArrayList<Nodo> listaAbiertos) {
		
		listaAbiertos.sort(new ComparadorOrdenador());
	}
	
	private double CalcularHeuristica(double valorPeso) {
		if(2-valorPeso>=0) {
			return (2.0-valorPeso);
		}
		else {
			return -(2.0-valorPeso);
		}
	}
	
	
	
	public void MostrarGrafo(ArrayList<Nodo> grafo) {
		
		int contador=1;
		while(grafo.size()>0) {
			System.out.println("\n\n////////////////////////////////////////////");
			System.out.println("       BANDEJA "+contador+" CREADA");
			System.out.println("////////////////////////////////////////////");
			Nodo nodo=new Nodo();
			nodo=grafo.get(0);
			grafo.remove(0);
			MostrarNodos(nodo);
			contador++;
			System.out.println("////////////////////////////////////////////");
			System.out.println("////////////////////////////////////////////");
		}
	}
	
	
	/*
	​Si ​nodo sucesor ya se encuentra en la lista abierta 
	​Si ​g del nodo sucesor < al g del mismo nodo de la lista abierta 
	Actualizar atributos del nodo de la lista abierta con atributos del mismo nodo 
	de la lista vecinos
	*/
	private boolean nodoEstaEnAbiertos(Nodo nodoSuc, ArrayList<Nodo> listaAbiertos, Nodo nodoPadre) {
		
		for (Nodo n : listaAbiertos) {
			if (n.getBandeja().getPesoBandeja()==nodoSuc.getBandeja().getPesoBandeja()){
				
				//Si el coste del nodo sucesor es menor que el que ya había en la lista de abiertos
				if(nodoSuc.getProfundidad()<n.getProfundidad()) {
					//System.out.println("ESTO OCURRE");
					n.setNodoAntecesor(nodoSuc.getNodoAntecesor());
				}
				return true;
			}
		}
		return false;
	}
	
	private boolean nodoEstaEnCerrados(Nodo nodoSuc, ArrayList<Nodo> listaCerrados, Nodo nodoPadre) {
		
		for(Nodo n: listaCerrados) {
			if (n.getBandeja().getPesoBandeja()==nodoSuc.getBandeja().getPesoBandeja() && n.getBandeja().getPrecioBandeja()>nodoSuc.getBandeja().getPrecioBandeja()) {
				return true;
			}
		}
		
		return false;
		
	}
	
	//Calculamos los antecesores de nodos cuando un nodo sucesor se encuentra dentro de cerrados
	private void calcularNodosAntecesores(Nodo nodoSuc, Nodo nodoPadre, Nodo posibleNuevoPadre){
		
			if (posibleNuevoPadre.getProfundidad()<nodoPadre.getProfundidad()) {
				nodoSuc.setNodoAntecesor(posibleNuevoPadre);
				
				Papaya pap=new Papaya();
				pap=nodoSuc.getBandeja().getContenidoPapayas().get(nodoSuc.getBandeja().getContenidoPapayas().size()-1);
				nodoSuc.setProfundidad(posibleNuevoPadre.getBandeja().getPrecioBandeja()+pap.getPeso() * 2 + 0.05 * pap.getDiasAlm() + 0.10);

				System.out.println("ESTO OCURRE");
				
			}
			
			for (Nodo n : nodoSuc.getNodosSucesores()) {
				calcularNodosAntecesores(n, n.getNodoAntecesor(), posibleNuevoPadre);
			}
	}
	
	private boolean nodoEsAntecesorDelPadre(Nodo nodoSuc, Nodo nodoPadre) {
		
		Nodo antecesor = nodoPadre.getNodoAntecesor();
		Nodo temporal;
		
		while (antecesor != null) {
			if(nodoSuc.getHeuristica()==antecesor.getHeuristica() && nodoSuc.getProfundidad()==antecesor.getProfundidad()) {
				System.out.println("ESTO OCURRE");
				return true;
			}
			
			temporal = antecesor.getNodoAntecesor();
			antecesor = temporal;
		}
		
		return false;
		
	}
	
	private void MostrarNodos(Nodo nodo) {
		
		Bandeja bandeja;
		ArrayList<Papaya>  papayas;
		
		if(nodo.getNodoAntecesor()!=null) {
			
			bandeja= nodo.getBandeja();
			papayas=bandeja.getContenidoPapayas();
			
			MostrarNodos(nodo.getNodoAntecesor());
			
			System.out.println("\n\nNODO: BANDEJA----->(PESO: "+bandeja.getPesoBandeja()+" PRECIO: "+bandeja.getPrecioBandeja()+" )");
			System.out.println("PAPAYAS INTRODUCIDAS: ");
			
			if(bandeja.getPrecioBandeja()>0) {
				for(int j=0; j<papayas.size(); j++) {
					System.out.println("P( "+papayas.get(j).getPeso()+", "+papayas.get(j).getId()+", "+papayas.get(j).getDiasAlm()+" )");
				}
			}
		}
		//Es el nodo Source
		else {
			bandeja= nodo.getBandeja();
			papayas=bandeja.getContenidoPapayas();
			
			System.out.println("\n\nNODO ORIGEN: BANDEJA----->(PESO: "+bandeja.getPesoBandeja()+" PRECIO: "+bandeja.getPrecioBandeja()+" )");
			System.out.println("PAPAYAS INTRODUCIDAS: NINGUNA ");
		}
	}
	
}
