package arvorePrim;

import graph.Vertex;

public class AtributePrimDijkstra  implements Comparable<AtributePrimDijkstra>{
	
	private Vertex antecessor;
	private Double valor;
	private Vertex vertex;

	
	public AtributePrimDijkstra(Vertex antecessor,Double valor) {
		this.antecessor = antecessor;
		this.valor = valor;
	}
	
	public AtributePrimDijkstra(Vertex vertice ,Vertex antecessor,Double valor) {
		this.antecessor = antecessor;
		this.valor = valor;
		this.vertex = vertice;
	}
	
	public Vertex getAntecessor() {
		return antecessor;
	}
	public void setAntecessor(Vertex antecessor) {
		this.antecessor = antecessor;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Vertex getVertex() {
		return vertex;
	}

	public void setVertex(Vertex vertex) {
		this.vertex = vertex;
	}
	
	@Override
	public int compareTo(AtributePrimDijkstra other) {
		if (this.valor < other.valor) {
            return -1;
        }
		if (this.valor > other.valor) {
            return 1;
        }
		return 0;
	}
}
