package arvorePrim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import graph.Graph;
import graph.Vertex;

public class AlgoritmoPrim {
	
	private Graph graph;
	
	private ArrayList<AtributePrimDijkstra> fila = new ArrayList<>();
	
	private HashMap<Vertex,AtributePrimDijkstra> chaveAntecessor = new HashMap<>();
	
	private boolean filaContem(Vertex v){
		for (AtributePrimDijkstra e: fila){
			if (v.getId()==e.getVertex().getId())
				return true;
		} 
		return false;
	}
	
	private void imprimeArvore(){
		for (Entry<Vertex,AtributePrimDijkstra> entry:chaveAntecessor.entrySet()){
			String idv = entry.getKey().getId();
			String pi;
			if (entry.getValue().getAntecessor()!=null)
				pi = entry.getValue().getAntecessor().getId();
			else
				pi = "-1";
			double custo = entry.getValue().getValor();
			graph.getSolucao().add(pi+" "+idv+" "+custo);
		}
	}
	
	public AlgoritmoPrim(Graph g, Vertex inicial) {
		this.graph = g;
		algoritmoPrim(g.getGraphVertex().get(String.valueOf(graph.getBeanGraph().getInicialVertex())));
	}
	
	private void algoritmoPrim(Vertex inicial){
		for (Entry<String, Vertex> u: graph.getGraphVertex().entrySet()){
			AtributePrimDijkstra elemento = new AtributePrimDijkstra(null,Double.POSITIVE_INFINITY);
			chaveAntecessor.put(u.getValue(),elemento);
			elemento.setVertex(u.getValue());
			fila.add(elemento);
		}
		chaveAntecessor.get(inicial).setValor(0d);
		chaveAntecessor.get(inicial).setAntecessor(null);
		while (!fila.isEmpty()){
			Collections.sort(fila);
			inicial = fila.get(0).getVertex();
			fila.remove(0);
			for (Vertex v : inicial.getAdjacentList()){
				double chave = chaveAntecessor.get(v).getValor();
			    double custoAresta = graph.adjacentAux(inicial, v).getValor();
				if ((custoAresta < chave)&&(filaContem(v))){
						chaveAntecessor.get(v).setAntecessor(inicial);
						chaveAntecessor.get(v).setValor(custoAresta);
				}
			}
		}
		this.imprimeArvore();
	}
}
