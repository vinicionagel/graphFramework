package screen;

import java.util.HashSet;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import graphCore.BeanGraph;

public class PrintDirectedSparceFRLayout extends ScreenGeneric {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrintDirectedSparceFRLayout(DirectedSparseMultigraph<?, ?> graph, final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new FRLayout(graph), solucao, beanGraph);
	}
	
	public PrintDirectedSparceFRLayout(UndirectedSparseMultigraph<?, ?> graph, final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new FRLayout(graph), solucao, beanGraph);
	}
}
	
