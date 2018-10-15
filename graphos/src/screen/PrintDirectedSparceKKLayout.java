package screen;

import java.util.HashSet;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graphCore.BeanGraph;

public class PrintDirectedSparceKKLayout extends ScreenGeneric {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrintDirectedSparceKKLayout(DirectedSparseMultigraph<?, ?> graph, final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new KKLayout(graph), solucao, beanGraph);
	}
	
	public PrintDirectedSparceKKLayout(UndirectedSparseGraph<?, ?> graph, final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new KKLayout(graph), solucao, beanGraph);
	}
}
