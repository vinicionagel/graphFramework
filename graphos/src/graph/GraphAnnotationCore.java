package graph;

import enuns.AlgoritmosEnum;
import enuns.ColorEnum;
import enuns.LayoutGraph;
import graphCore.BeanGraph;

import java.awt.Color;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GraphAnnotationCore {
	
	private Class<?> classe;
	private Graph graph;
	
	public GraphAnnotationCore(Class<?> classe, Graph graph) {
		super();
		this.classe = classe;
		//getValuesFromAnnotationsClass();
	}
	/**
	 * obtem valores da classe anotada.
	 */
	public BeanGraph getValuesFromAnnotationsClass(){
		BeanGraph beanGraph = new BeanGraph();
		for (Annotation annotation: classe.getAnnotations()) {
			Class<? extends Annotation> type = annotation.annotationType();
			for (Method method: type.getDeclaredMethods()){
				try {
					switch (method.getName()) {
					case "algortimo":{
						beanGraph.setAlgoritmo(AlgoritmosEnum.getAlgoritmo((AlgoritmosEnum)method.invoke(annotation, null)));
						break;
					} case "vertexInicial":{
						beanGraph.setInicialVertex(Integer.parseInt((String)method.invoke(annotation, null)));
						break;
					} case "colorSolutionEdge":{
						beanGraph.setColorSolution(ColorEnum.getColor((ColorEnum)method.invoke(annotation, null)));
						break;
					} case "colorVertice":{
						beanGraph.setColorVertice(ColorEnum.getColor((ColorEnum)method.invoke(annotation, null)));
						break;
					} case "layout":{
						beanGraph.setLayout((LayoutGraph)method.invoke(annotation, null));
						break;
					} case "directed":{
						beanGraph.setDirected((boolean)(method.invoke(annotation, null)));
					}case "vertexFinal":{
						if (method.invoke(annotation, null) instanceof String){
							if ((method.invoke(annotation, null) != null) && (!String.valueOf((method.invoke(annotation, null))).isEmpty())){
								beanGraph.setFinalVertex(Integer.parseInt((String)method.invoke(annotation, null)));
							}
						}
						break;
					}
					default:
						break;
					}
				} catch (IllegalAccessException | IllegalArgumentException| InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return beanGraph;
	}
	
	
	
	
}
