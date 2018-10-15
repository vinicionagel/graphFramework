package graph;

import graphCore.VertexInterface;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * c
 * Classe que representa vertíce
 */
public class Vertex implements VertexInterface{
	
	private String id;
	private ArrayList<Edge> edgesList = new ArrayList<>();
	private HashSet<Vertex> adjacentList = new HashSet<>();
	
	public Vertex(String id) {
		this.id = id;
	}
	
	public Vertex(int id) {
		this.id = String.valueOf(id);
	}
	
	public Vertex(long id) {
		this.id = String.valueOf(id);
	}
	/**
	 * @return retorna lista de arestas do vertice
	 */
	public ArrayList<Edge> getedgesList() {
		return edgesList;
	}
	
	/**
	 * @return retorna o id do vertice
	 */
	@Override
	public String getId() {
		return this.id;
	}
	
	/**
	 * @return retorna o grau do vertice
	 */
	@Override
	public int getDegree() {
		return edgesList.size();
	}
	
	/**
	 * @param vertice vj
	 * @return retorna true se o vertice for adjacentListe
	 */
	@Override
	public boolean isAdjacent(Vertex vj) {
		return adjacentList.contains(vj);
	}
	
	/**
	 * @deprecated
	 * @return retorna a lista dos adjacentListes
	 */
	@Override
	public ArrayList<Vertex> adj() {
		ArrayList<Vertex> adjacentListes = new ArrayList<>();
		for (Edge edge: getedgesList()){
			if (edge.getVi().equals(this)){
				adjacentListes.add(edge.getVj());
			}
		}
		return adjacentListes;
	}
	
	
	/**
	 * Método para adjacentListes sem percorrer.
	 * @return lista de adjacentListes
	 */
	public HashSet<Vertex> getAdjacentList() {
		return adjacentList;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vertex [id=").append(id).append("] ");
		return builder.toString();
	}
	
}
