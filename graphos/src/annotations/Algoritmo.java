package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import enuns.AlgoritmosEnum;
import enuns.ColorEnum;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Algoritmo {
	
	AlgoritmosEnum algortimo();
	String vertexInicial();
	/**
	 * para BFS E DFS
	 * @return
	 */
	String vertexFinal();
	ColorEnum colorSolutionEdge() default ColorEnum.BLACK;
}
