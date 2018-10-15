package graphCore;

import java.awt.Color;

import enuns.AlgoritmosEnum;
import enuns.LayoutGraph;

public class BeanGraph {
	private Color colorVertice;
	private Color colorSolution;
	private int inicialVertex;
	private int finalVertex;
	private String algoritmo;
	private LayoutGraph layout;
	private boolean directed;
	
	
	public BeanGraph() {
	}
	
	public BeanGraph(Color colorVertice, Color colorSolution,
			int inicialVertex, String algoritmo, int finalVertex) {
		super();
		this.colorVertice = colorVertice;
		this.colorSolution = colorSolution;
		this.inicialVertex = inicialVertex;
		this.finalVertex = finalVertex;
	}
	public Color getColorVertice() {
		return colorVertice;
	}
	public void setColorVertice(Color colorVertice) {
		this.colorVertice = colorVertice;
	}
	public Color getColorSolution() {
		return colorSolution;
	}
	public void setColorSolution(Color colorSolution) {
		this.colorSolution = colorSolution;
	}
	public int getInicialVertex() {
		return inicialVertex;
	}
	public void setInicialVertex(int inicialVertex) {
		this.inicialVertex = inicialVertex;
	}

	public LayoutGraph getLayout() {
		return layout;
	}

	public void setLayout(LayoutGraph layout) {
		this.layout = layout;
	}
	
	
	public String getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}
	
	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}
	
	public int getFinalVertex() {
		return finalVertex;
	}

	public void setFinalVertex(int finalVertex) {
		this.finalVertex = finalVertex;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BeanGraph [colorVertice=");
		builder.append(colorVertice);
		builder.append(", colorSolution=");
		builder.append(colorSolution);
		builder.append(", inicialVertex=");
		builder.append(inicialVertex);
		builder.append(", algoritmo=");
		builder.append(algoritmo);
		builder.append(", layout=");
		builder.append(layout);
		builder.append("]");
		return builder.toString();
	}
		
	
}
