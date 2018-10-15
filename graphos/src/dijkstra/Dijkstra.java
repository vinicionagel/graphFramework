package dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import arvorePrim.AtributePrimDijkstra;
import graph.Graph;
import graph.Vertex;

public class Dijkstra {
	
	private Graph graph;
	
	private Vertex inicial;
	
	private Vertex finals;
	
	private ArrayList<AtributePrimDijkstra> fila = new ArrayList<>();
	
	private HashMap<Vertex,AtributePrimDijkstra> custoAntecessor = new HashMap<>();
	
	private HashSet<Vertex> calculados = new HashSet<>();
	
	public Dijkstra(Graph graph, Vertex inicial, Vertex finals) {
		this.graph = graph;
		this.inicial = inicial;
		this.finals = finals;
		inicializa();
		dijkstra();
	}
	
	public Dijkstra(Graph graph, Vertex inicial) {
		this.graph = graph;
		this.inicial = inicial;
		inicializa();
		dijkstra();
	}
	
	private void inicializa(){
		for (Entry<String, Vertex> u: graph.getGraphVertex().entrySet()){
			AtributePrimDijkstra elemento = new AtributePrimDijkstra(null,Double.POSITIVE_INFINITY);
			custoAntecessor.put(u.getValue(),elemento);
			elemento.setVertex(u.getValue());
			fila.add(elemento);
		}
		custoAntecessor.get(inicial).setValor(0d);
		custoAntecessor.get(inicial).setAntecessor(null);
	}
	
	private void dijkstra(){
		while (!fila.isEmpty()){
			Collections.sort(fila);
			inicial = fila.get(0).getVertex();
			calculados.add(inicial);
			fila.remove(0);
			if (inicial.getAdjacentList().contains(finals)){
				relaxa(finals);
				fila.removeAll(fila);
				break;
			}
			for (Vertex v : inicial.getAdjacentList()){
				relaxa(v);
				if (finals != null && finals.equals(v)){
					fila.removeAll(fila);
				}
			}
		}
	}
	
	private void relaxa(Vertex adj){
		double custoAresta = graph.adjacentAux(inicial, adj).getValor();
		if (custoAntecessor.get(adj).getValor() > custoAntecessor.get(inicial).getValor()+custoAresta){
			graph.getSolucao().add(inicial.getId()+" "+adj.getId()+" "+custoAresta);
			custoAntecessor.get(adj).setValor(custoAntecessor.get(inicial).getValor()+custoAresta);
			custoAntecessor.get(adj).setAntecessor(inicial);
		}
	}
	
}
