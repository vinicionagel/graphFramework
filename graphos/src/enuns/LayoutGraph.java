package enuns;

import screen.PrintDirectedSparceFRLayout;
import screen.PrintDirectedSparceKKLayout;
import screen.PrintDirectedSparceSpringLayout;
import screen.PrintDirectedSparseCircleLayout;
import screen.ScreenGeneric;

public enum LayoutGraph {

	FR_LAYOUT(PrintDirectedSparceFRLayout.class),
	KK_LAYOUT(PrintDirectedSparceKKLayout.class), 
	SPRING_LAYOUT(PrintDirectedSparceSpringLayout.class), 
	CIRCLE_LAYOUT(PrintDirectedSparseCircleLayout.class);
	
	private Class<? extends ScreenGeneric> layout;
	
	private LayoutGraph(Class<? extends ScreenGeneric> layout) {
		this.layout = layout;
	}

	public Class<? extends ScreenGeneric> getLayout() {
		return layout;
	}
	
	
	
}
