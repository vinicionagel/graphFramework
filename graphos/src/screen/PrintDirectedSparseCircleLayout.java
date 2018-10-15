package screen;

import java.util.HashSet;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import graphCore.BeanGraph;

public class PrintDirectedSparseCircleLayout extends ScreenGeneric {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * Para grafos dirigiso
     * @param graph
     * @param solucao
     * @param beanGraph
     */
	public PrintDirectedSparseCircleLayout(DirectedSparseMultigraph<?, ?> graph, final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new CircleLayout(graph),solucao,beanGraph);
	}
	/**
	 * Para grafos não dirigidos
	 * @param graph
	 * @param solucao
	 * @param beanGraph
	 */
	public PrintDirectedSparseCircleLayout(UndirectedSparseMultigraph<?, ?> graph, final HashSet<String> solucao, BeanGraph beanGraph) {
		super(new CircleLayout(graph),solucao,beanGraph);
	}
	
}
