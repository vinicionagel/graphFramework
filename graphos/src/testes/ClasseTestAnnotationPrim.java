package testes;

import java.util.ArrayList;

import java.util.List;

import enuns.AlgoritmosEnum;
import enuns.ColorEnum;
import enuns.LayoutGraph;
import annotations.Algoritmo;
import annotations.ColorGraph;
import annotations.EdgeAnnotation;
import annotations.LayoutChoise;
import annotations.VertexAnnotaion;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
@LayoutChoise(layout=LayoutGraph.CIRCLE_LAYOUT, directed=true)
@ColorGraph(colorVertice=ColorEnum.PINK)
@Algoritmo(algortimo=AlgoritmosEnum.DIJSTRA,vertexInicial="1",colorSolutionEdge=ColorEnum.RED,vertexFinal="3")
public class ClasseTestAnnotationPrim {
	
	@EdgeAnnotation
	private List<Edge> edges = new ArrayList<>();
	@VertexAnnotaion
	private List<Vertex> vertices = new ArrayList<>();
	
	public static void main(String[] args) {
		ClasseTestAnnotationPrim x = new ClasseTestAnnotationPrim();
		for (int i = 1; i<14; i++){
			x.vertices.add(new Vertex(i));
		}
		x.vertices.add(new Vertex(16));
		x.edges.add(new Edge(16, 1, 1D));
		x.edges.add(new Edge(1, 16, 1D));
		x.edges.add(new Edge(1, 2, 1D));
		x.edges.add((new Edge(1, 2, 4D)));
		x.edges.add((new Edge(1, 2, 6D)));
		x.edges.add((new Edge(1, 8, 8D)));
		x.edges.add((new Edge(2, 8, 11D)));
		x.edges.add((new Edge(2, 8, 11D)));
		x.edges.add((new Edge(1, 7, 1D)));
		x.edges.add((new Edge(2, 8, 3D)));
		x.edges.add((new Edge(2, 3, 8D)));
		x.edges.add((new Edge(3, 4, 7D)));
		x.edges.add((new Edge(3, 4, 1D)));
		x.edges.add((new Edge(3, 9, 2D)));
		x.edges.add((new Edge(3, 6, 4D)));
		x.edges.add((new Edge(4, 5, 9D)));
		x.edges.add((new Edge(4, 6, 14D)));
		x.edges.add((new Edge(6, 5, 10D)));
		x.edges.add((new Edge(7, 6, 2D)));
		x.edges.add((new Edge(8, 7, 10D)));
		x.edges.add((new Edge(8, 7, 19D)));
		x.edges.add((new Edge(7, 9, 6D)));
		x.edges.add((new Edge(7, 9, 5D)));
		x.edges.add((new Edge(8, 9, 4D)));
		x.edges.add((new Edge(8, 9, 20D)));
		x.edges.add((new Edge(1, 10, 20D)));
		x.edges.add((new Edge(10, 9, 12D)));
		x.edges.add((new Edge(10,3, 23D)));
		x.edges.add((new Edge(10,10, 23D)));
		x.edges.add((new Edge(10,7, 11D)));
		x.edges.add((new Edge(11,10, 11D)));
		x.edges.add((new Edge(12,11, 11D)));
		x.edges.add((new Edge(13,10, 11D)));
		x.edges.add((new Edge(10, 1, 20D)));
		x.edges.add((new Edge(9,4, 11D)));
		x.edges.add((new Edge(12,3, 11D)));
		x.edges.add((new Edge(3,12, 11D)));
		x.edges.add((new Edge(13,11, 11D)));
		
		Graph xk = new Graph(x.getClass(), x);
		
	}
	
	

}
