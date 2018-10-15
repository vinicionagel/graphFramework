package graph;

import graphCore.EdgeInterface;
/**
 * 
 * @author Roland e Vinicio.
 * Classe que representa a aresta
 */
public class Edge implements EdgeInterface, Comparable<Edge> {
	private Vertex vi;
	private Vertex vj;
	private Double valor;
	
	/**
	 * @param vi 
	 * @param vj
	 */
	public Edge(Vertex vi, Vertex vj, Double valor) {
		this.vi = vi;
		this.vj = vj;
		this.valor = valor;
	}
	
	public Edge(String vi, String vj, Double valor) {
		this.vi = new Vertex(vi);
		this.vj = new Vertex(vj);
		this.valor = valor;
	}
	
	public Edge(int vi, int vj, Double valor) {
		this.vi = new Vertex(vi);
		this.vj = new Vertex(vj);
		this.valor = valor;
	}
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@Override
	public Vertex getVj() {
		return this.vj;
	}

	@Override
	public Vertex getVi() {
		return this.vi;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + vi.hashCode() + vj.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Edge)){
			return false;
		}
		final Edge other = (Edge) obj;
		if (getVi() == null || getVj() == null){
			return false;
		}
		if ((getVi().equals(other.vi) && getVj().equals(other.getVj()))){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[vi=").append(vi).append(", vj=").append(vj)
				.append("]");
		return builder.toString();
	}

	@Override
	public int compareTo(Edge other) {
		if (this.valor < other.valor) {
            return -1;
        }
		if (this.valor > other.valor) {
            return 1;
        }
		return 0;
	}	
}
