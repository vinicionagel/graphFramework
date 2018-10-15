package graph;



import dijkstra.Dijkstra;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import enuns.AlgoritmosEnum;
import graphCore.BeanGraph;
import graphCore.GraphInterface;

import java.awt.Container;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;

import org.apache.commons.collections15.Factory;

import annotations.EdgeAnnotation;
import annotations.VertexAnnotaion;
import arvorePrim.AlgoritmoPrim;
import screen.PrintDirectedSparceFRLayout;
import screen.PrintDirectedSparceKKLayout;
import screen.PrintDirectedSparceSpringLayout;
import screen.PrintDirectedSparseCircleLayout;
import screen.PrintTree;
import screen.ScreenGeneric;
import searchGraph.AlgBFS;
import searchGraph.AlgDFS;

/**
 * 
 * @author Roland e Vinicio.
 * Classe que representa o grafo
 */
public class Graph implements GraphInterface { 

	private Map<String,Vertex> graphVertex = new HashMap<String,Vertex>();
	private ArrayList<Edge> graphEdgsList = new ArrayList<>(); 
	private Forest<String, Integer> graphTree = new DelegateForest<>();
	private DirectedSparseMultigraph directedSparseGraph = new DirectedSparseMultigraph();
	private UndirectedSparseMultigraph undirectSparseGraph = new UndirectedSparseMultigraph();
	private HashSet<String> solucao = new HashSet<String>();
	private Class<?> classe;
	private BeanGraph beanGraph;
	private boolean directed;
	
	public Graph(boolean directed) {
		this.directed = directed;
	}
	/**
	 * 
	 * @param objeto refererencia do objeto criado.
	 * @param classe do objeto.
	 */
	public Graph(Class<?> classe, Object referencia) {
		this.classe = classe;
		GraphAnnotationCore graphsAnnotation = new GraphAnnotationCore(classe,this);
		beanGraph = graphsAnnotation.getValuesFromAnnotationsClass();
		directed = beanGraph.isDirected();
		getValueFromAnnotedVertexEdge(referencia);
		runAlgoritmo(beanGraph.getAlgoritmo());
		imprimirGrafo(beanGraph.getLayout().getLayout());
	}
	
	
	protected void runAlgoritmo(String algoritmo){
		if (AlgoritmosEnum.BFS.getDescription().equals(algoritmo)){
			new AlgBFS(this, String.valueOf(beanGraph.getInicialVertex()), String.valueOf(beanGraph.getFinalVertex()));
		} else if (AlgoritmosEnum.DFS.getDescription().equals(algoritmo)){
			new AlgDFS(this, String.valueOf(beanGraph.getInicialVertex()), String.valueOf(beanGraph.getFinalVertex()));
		} else if (AlgoritmosEnum.DIJSTRA.getDescription().equals(algoritmo)){
			new Dijkstra(this, this.getGraphVertex().get(String.valueOf(beanGraph.getInicialVertex())),this.getGraphVertex().get(String.valueOf(beanGraph.getFinalVertex())));
		} else if (AlgoritmosEnum.ALGORITMO_PRIM.getDescription().equals(algoritmo)){
			new AlgoritmoPrim(this, this.getGraphVertex().get((String.valueOf(beanGraph.getInicialVertex()))));
		}
		
	}
	
	public void getValueFromAnnotedVertexEdge(Object refer){
		List<Vertex> vertices = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		for(Field field : classe.getDeclaredFields()){
			VertexAnnotaion vertexAnnoted = field.getAnnotation(VertexAnnotaion.class);
			EdgeAnnotation edgeAnnotation = field.getAnnotation(EdgeAnnotation.class);
			if (vertexAnnoted != null){
				field.setAccessible(true);
				try {
					vertices = (List<Vertex>) field.get(refer);
					populaVertexList(vertices);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			if (edgeAnnotation != null){
				field.setAccessible(true);
				try {
					edges = (List<Edge>) field.get(refer);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		populaEdgesList(edges);
	}
	
	public void populaVertexList(List<Vertex> vertices){
		for (Vertex v : vertices){
			this.addVertex(v.getId());
		}
	}
	
	public void populaEdgesList(List<Edge> edges){
		for (Edge e:edges){
			this.addEdge(e.getVi(), e.getVj(), e.getValor());
		}
	}

	@Override
	public void addEdge(Vertex vi, Vertex vj, double valor) {
		Edge edge = new Edge(vi, vj, valor);
		if (directed){
			directedSparseGraph.addEdge(createStringEdge(vi.getId(),vj.getId(), valor), vi.getId(), vj.getId());
		} else {
			undirectSparseGraph.addEdge(createStringEdge(vi.getId(),vj.getId(), valor), vi.getId(), vj.getId());
		}
		addEdgeAuxDirectedSparseGraph(edge);
	}
	
	@Override
	public void addVertex(String id) {
		graphVertex.put(id, new Vertex(id));
		if (directed){
			directedSparseGraph.addVertex(id);
		} else {
			undirectSparseGraph.addVertex(id);
		}
	}
	
	
	@Override
	public void addVertex(int id) {
		String idS = String.valueOf(id); 
		graphVertex.put(idS, new Vertex(idS));
		if (directed){
			directedSparseGraph.addVertex(idS);
		} else {
			undirectSparseGraph.addVertex(idS);
		}
		
	}

	@Override
	public void addEdge(int vi, int vj, double valor) {
		String idSVi = String.valueOf(vi);
		String idSVj = String.valueOf(vj);
		Edge edge = new Edge(this.getGraphVertex().get(idSVi), this.getGraphVertex().get(idSVj), valor);
		addEdgeAuxDirectedSparseGraph(edge);
	}
	
	/**
	 * @return retorna quantidade de vertices
	 */
	@Override
	public int getOrder() {
		return graphVertex.size();
	}
	
	/**
	 * @return retorna quantidade de arestas
	 */
	@Override
	public int getSize() {
		return graphEdgsList.size();
	}
	
	/**
	 * @return retorna o numero de vertices
	 */
	@Override
	public int V() {
		return graphVertex.size();
	}	
	
	/**
	 * @return retorna o numero de arestas
	 */
	@Override
	public int E() {
		return graphEdgsList.size();
	}
	/**
	 * Método para adicionar vertice ao grafo
	 */
	
	
	/**
	 * Método para adicionar vertice ao grafo
	 */
	
	/**
	 * <li>Método auxiliar para contrucao de aresta</li>
	 * @param vi
	 * @param vj
	 * @return string do identificador de aresta
	 */
	private String createStringEdge(String vi, String vj, double valor){
		StringBuilder builder = new StringBuilder();
		builder.append(edgeFactory.create()).append(" ").append(vi).
		append(" ").append(vj).append(" ").append(valor);
		return builder.toString();
	}
	
	/**
	 * Método responsavel por adicionar aresta,
	 * Se não for direcional adiciona aresta de volta.
	 * @param edge
	 * @param vi
	 * @param vj
	 */
	private void addEdgeAuxDirectedSparseGraph(Edge edge){
		graphEdgsList.add(edge);
		Vertex vi = this.getGraphVertex().get(edge.getVi().getId());
		Vertex vj = this.getGraphVertex().get(edge.getVj().getId());
		vi.getedgesList().add(edge);
		vj.getedgesList().add(edge);
		vi.getAdjacentList().add(vj);
		if (!directed){
			vj.getAdjacentList().add(vi);
		}
	}
	
	/**
	 * Método para adicionar arestas ao grafo, 
	 * necessario já ter dois vertices adicionados.
	 * @param vi, vj 
	 */
	@Override
	public void addEdge(String vi, String vj, double valor) {
		Edge edge = new Edge(this.getGraphVertex().get(vi), this.getGraphVertex().get(vj), valor);
		if (directed){
			directedSparseGraph.addEdge(createStringEdge(vi,vj,valor), vi, vj);
		} else {
			undirectSparseGraph.addEdge(createStringEdge(vi,vj, valor), vi, vj);
		}
		addEdgeAuxDirectedSparseGraph(edge);
	}
	
	/**
	 * retorna true se for adjacent ou false se não for.
	 */
	@Override
	public boolean adjacent(Vertex vi, Vertex vj) {
		return !(adjacentAux(vi,vj) == null);
	}
	
	/**
	 * método auxiliar para retornar a aresta pelos vertices
	 * @param vi
	 * @param vj
	 * @return
	 */
	public Edge adjacentAux(Vertex vi, Vertex vj){
		Collections.sort(graphEdgsList);
		for (Edge edge: graphEdgsList){
			if ((edge.getVi().equals(vi) && edge.getVj().equals(vj)) 
					|| ((edge.getVi().equals(vj)) && edge.getVj().equals(vi))){
				return edge;
			}
		}
		return null;
	}
	
	/**
	 * Método para deletar vertice. 
	 * Deletado se existir suas arestas 
	 * @param id do vertice
	 */
	@Override
	public void removeVertex(String id) {
		Vertex vertex = new Vertex(id);
		for (Vertex vj: getGraphVertex().get(id).getAdjacentList()){
			if (!vj.equals(vertex)){
				getGraphVertex().get(vj.getId()).getedgesList().remove(new Edge(vertex, vj, 0D));
				getGraphVertex().get(vj.getId()).getAdjacentList().remove(vertex);
			}
		}
		getGraphEdgsList().removeAll(getGraphVertex().get(id).getedgesList());
		graphVertex.remove(id);
	}
	
	/**
	 * deleta aresta se exister 
	 * @param vi, vj 
	 */
	@Override
	public void removeEdge(Vertex vi, Vertex vj) {
		Edge edge = new Edge(vi, vj, 0D);
		vi.getedgesList().remove(edge);
		vj.getedgesList().remove(edge);
		vi.getAdjacentList().remove(vj);
		vj.getAdjacentList().remove(vi);
		graphEdgsList.remove(edge);
	}
	
	/**
	 * @return hashMap de vertices
	 */
	public Map<String, Vertex> getGraphVertex() {
		return graphVertex;
	}
	
	/**
	 * @return lista de arestas.
	 */
	public ArrayList<Edge> getGraphEdgsList() {
		return graphEdgsList;
	}	
	/**
	 * Método do JUNG
	 */
    Factory<String> edgeFactory = new Factory<String>() {
        int i = 0;
        
        @Override
        public String create() {
            return i++ +"" ;
        }
    };
    /**
	 * Método do JUNG
	 */
    Factory<String> vertexFactory = new Factory<String>() {
        int i = 0;

        @Override
        public String create() {
            return "V" + i++;
        }
    };
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Graph [graphVertex=");
		builder.append("\n");
		for (Entry<String, Vertex> entry : getGraphVertex().entrySet()) {
			builder.append(entry.getKey());
			builder.append(":");
			builder.append(entry.getValue().getAdjacentList());
			builder.append("\n");
		}
		return builder.toString();
	}
	
	/**
	 * <li> Método para imprimir somente em forma de árvore com todas as validacoes </li>
	 */
    protected void imprimirArvore(){
        JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content.add(new PrintTree(graphTree,new HashSet<String>()));            
        frame.pack();
        frame.setVisible(true);            
    }

	/**
	 * <li> Imprimi grafo dependendo o layout que está na annotation</li>
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
    private void imprimirGrafo(Class<? extends ScreenGeneric> classe){
    	JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        Constructor<? extends ScreenGeneric> constr;
        try {
        	if (directed){
        		constr = classe.getConstructor(directedSparseGraph.getClass(),solucao.getClass(),beanGraph.getClass());
        	} else {
        		constr = classe.getConstructor(undirectSparseGraph.getClass(),solucao.getClass(),beanGraph.getClass());
        	}
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	content.add(constr.newInstance(directed ? directedSparseGraph : undirectSparseGraph,solucao,beanGraph));
        } catch (Exception ex){
        	ex.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);  
    }
    
    protected void imprimirGrafoCircle(){
    	JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (directed){
        	content.add(new PrintDirectedSparseCircleLayout(directedSparseGraph,solucao, beanGraph));
        } else {
        	content.add(new PrintDirectedSparseCircleLayout(undirectSparseGraph,solucao, beanGraph));
        }
        frame.pack();
        frame.setVisible(true);  
    }
    /**
	 * <li> Imprimi grafo de forma Livre </li>
	 */
    protected void imprimirGrafoLivre(){
    	JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (directed){
        	content.add(new PrintDirectedSparceFRLayout(directedSparseGraph,solucao, beanGraph));
        } else {
        	content.add(new PrintDirectedSparceFRLayout(undirectSparseGraph,solucao, beanGraph));
        }
        frame.pack();
        frame.setVisible(true);  
    }
    
    /**
	 * <li> Imprimi grafo de forma KKLayout </li>
	 */
    protected void imprimirGrafoKKLayout(){
    	JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (directed){
        	content.add(new PrintDirectedSparceKKLayout(directedSparseGraph,solucao, beanGraph));
        } else {
        	content.add(new PrintDirectedSparceFRLayout(undirectSparseGraph,solucao, beanGraph));
        }
        frame.pack();
        frame.setVisible(true);  
    }
    /**
	 * <li> Imprimi grafo de forma SpringLayout </li>
	 */
    protected void imprimirGrafoSpringLayout(){
    	JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (directed){
        	content.add(new PrintDirectedSparceSpringLayout(directedSparseGraph,solucao, beanGraph));
        } else {
        	content.add(new PrintDirectedSparceFRLayout(undirectSparseGraph,solucao, beanGraph));
        }
        frame.pack();
        frame.setVisible(true);  
    }
    
	public HashSet<String> getSolucao() {
		return solucao;
	}

	public void setSolucao(HashSet<String> solucao) {
		this.solucao = solucao;
	}


	public BeanGraph getBeanGraph() {
		return beanGraph;
	}

	public void setBeanGraph(BeanGraph beanGraph) {
		this.beanGraph = beanGraph;
	}
	
	public boolean isDirected() {
		return directed;
	}
	public void setDirected(boolean directed) {
		this.directed = directed;
	}
   
}

