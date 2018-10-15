package screen;

import java.util.HashSet;

import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import graphCore.BeanGraph;

public class PrintDirectedSparceSpringLayout extends ScreenGeneric {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PrintDirectedSparceSpringLayout(DirectedSparseMultigraph<?, ?> graph, 
			final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new SpringLayout2(graph), solucao, beanGraph);
	}
	
	public PrintDirectedSparceSpringLayout(UndirectedSparseGraph<?, ?> graph, 
			final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new SpringLayout2(graph), solucao, beanGraph);
	}
	
}
