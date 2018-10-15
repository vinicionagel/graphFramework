package graphCore;

import graph.Vertex;

/**
 * 
 * @author Roland e Vinicio.
 *
 */
public interface GraphInterface {
	
	public int getOrder();
	public int getSize();
	public int V();
	public int E();
	public void addVertex(String id);
	public void addVertex(int id);
	public void addEdge(Vertex vi, Vertex vj, double valor);
	public void addEdge(String vi, String vj, double valor);
	public void addEdge(int vi, int vj, double valor);
	public boolean adjacent(Vertex vi, Vertex vj);
	public void removeVertex(String id);
	public void removeEdge(Vertex vi, Vertex vj);
	public void getValueFromAnnotedVertexEdge(Object refer);
	
}
