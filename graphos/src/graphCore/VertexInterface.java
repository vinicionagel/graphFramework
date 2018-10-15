package graphCore;

import graph.Vertex;

import java.util.ArrayList;
/**
 * 
 * @author Roland e Vinicio.
 *
 */
public interface VertexInterface {
	public String getId();
	public int getDegree();
	public boolean isAdjacent(Vertex vj);
	public ArrayList<Vertex> adj(); 
}




