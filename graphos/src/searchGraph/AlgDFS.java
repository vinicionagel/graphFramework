package searchGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import searchGraphCore.Color;
import searchGraphCore.DFSATribute;
import graph.Graph;
import graph.Vertex;

/**
 * 
 * @author Roland e Vinicio.
 * Classe que representa a busca em profundidade
 */
public class AlgDFS {
	/**
	 * grafo
	 */
	private Graph g;
	
	private String idFinal;
	/**
	 * Hash map do atributo
	 */
	private Map<String, DFSATribute> d = new HashMap<String, DFSATribute>();
	/**
	 * tempo
	 */
	private int tempo;
	private boolean found;
	/**
	 * @param g grafo que vai ser usado
	 * @param idInicial idInicial do vertice
	 * @param idFinal idFinal do vertice
	 */
	public AlgDFS(Graph g,String idInicial, String idFinal) {
		this.idFinal = idFinal;
		this.g = g;
		this.g.getSolucao().add(idInicial);
		DFS(idInicial);
		System.out.println(g.getSolucao());
		//imprimirCaminho(idInicial, idFinal);
	}
	/**
	 * Método responsavel pela busca em profundidade
	 */
	private void DFS(String idInicial){
		for (Entry<String, Vertex> v: g.getGraphVertex().entrySet()){
			DFSATribute atributo = new DFSATribute();
			atributo.setCor(Color.BRANCO);
			atributo.setAntecessor(null);
			d.put(v.getKey(), atributo);
		}
		DFSVisit(g.getGraphVertex().get(idInicial));
		if (!found){
			Thread t = new Thread(new Runnable(){
		        public void run(){
		            JOptionPane.showMessageDialog(null, "Não existe caminho de s para v");
		        }
		    });
		  t.start();
		} 
		
	}
	/**
	 * Método que vai visitando os vertices e colocando o tempo de abertura e fechamento
	 * @param u vértice atual
	 */
	private void DFSVisit(Vertex u){
		tempo++;
		DFSATribute x = d.get(u.getId());
		x.setCor(Color.CINZA);
		x.setTempoDescoberta(tempo);
		d.put(u.getId(),x);
		
			for (Vertex v: u.getAdjacentList()){
				if (!found){
					if (d.get(v.getId()).getCor().equals(Color.BRANCO)){
						if (v.getId().equals(idFinal) || u.getAdjacentList().contains(g.getGraphVertex().get(idFinal))){
							g.getSolucao().add(idFinal);
							found = true;
							break;
						}
						this.g.getSolucao().add(v.getId());
						DFSATribute an = d.get(v.getId());
						an.setAntecessor(u);
						//System.out.println(an);
						d.put(v.getId(),an);
						DFSVisit(v);
					}
			}
		} 
		DFSATribute an = d.get(u.getId());
		//System.out.println(d);
		an.setCor(Color.PRETO);
		an.setTempoFechamento(++tempo);
		//System.out.println(d);
		d.put(u.getId(), an);
	}
	
	
	/**
	 * Imprimi o caminho junto com o tempo de abertura/fechamento.
	 * @param idInicial
	 * @param idFinal
	 */
//	private void imprimirCaminho(String idInicial, String idFinal){
		//System.out.println(idInicial);
		//System.out.println(d.get(idInicial).getAntecessor().getId());
		//System.out.println(idInicial);
		//System.out.println(d);
		//this.g.getSolucao().add(idInicial);
		//this.g.getSolucao().add(idFinal);
		//if (idFinal.equals(this.idFinal)){
		//	System.out.print("->"+idFinal+" Tempo abertura "
		//			+d.get(idFinal).getTempoDescoberta()+" Tempo de Fechamento "+d.get(idFinal).getTempoFechamento()+"\n");
		//}else{
			//bfsAtribute.get(idFinal) == null || bfsAtribute.get(idFinal).getAntecessor() == null)
			/*if (d.get(idFinal) == null || d.get(idFinal).getAntecessor() == null){
				Thread t = new Thread(new Runnable(){
			        public void run(){
			            JOptionPane.showMessageDialog(null, "Não existe caminho de s para v");
			        }
			    });
			  t.start();
			}else{*/
				//System.out.println(d.get(idInicial).getAntecessor().getId());
				//System.out.println(d.get(idFinal).getAntecessor().getId());
				//imprimirCaminho(d.get(idInicial).getAntecessor().getId(),idFinal);
				//System.out.print("->"+idFinal+" Tempo abertura "
				//+d.get(idFinal).getTempoDescoberta()+" Tempo de Fechamento "+d.get(idFinal).getTempoFechamento()+"\n");
			//}
		//}
	
}
