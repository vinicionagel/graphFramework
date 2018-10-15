package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.util.LabelWrapper;
import graphCore.BeanGraph;

public class ScreenGeneric extends JApplet {
	
	public ScreenGeneric() {
		
	}
	
	
	public ScreenGeneric(Layout visua,final HashSet<String> solucao, final BeanGraph beanGraph){
		final VisualizationViewer vv = new VisualizationViewer(visua,new Dimension(600, 600));
		vv.setBackground(Color.white);
		vv.getRenderContext().setVertexLabelTransformer(new LabelWrapper(10));
		vv.getRenderContext().setEdgeLabelTransformer(new LabelWrapper(10));
		vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
		Transformer<String,Paint> vertexColor = new Transformer<String,Paint>() {
            @Override
            public Paint transform(String i) {
            	if (solucao.contains(i)){
            		return Color.MAGENTA;
            	}
            	if (beanGraph != null){
            		return beanGraph.getColorVertice();
            	}
        		return Color.BLACK;
            }
        };
        Transformer<String,Paint> arrowCollor = new Transformer<String,Paint>() {
	        @Override
	        public Paint transform(String i) {
	            if(solucao.contains(i.substring(2, i.length()).trim())){
	            	if (beanGraph != null){
	            		return beanGraph.getColorSolution();
	            	}
	                return Color.BLUE;
	            } else {
	                return Color.LIGHT_GRAY;
	            }                
	        }
	    };
	    vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
    	vv.getRenderContext().setEdgeDrawPaintTransformer(arrowCollor);
    	Container content = getContentPane();
    	
    	final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        content.add(panel);

        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        vv.setGraphMouse(graphMouse);

        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(graphMouse.getModeListener());
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        final ScalingControl scaler = new CrossoverScalingControl();
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });
        JPanel controls = new JPanel();
        controls.add(setScaleGrid("Zoom",plus,minus));
        controls.add(setScaleGrid("Selecao",modeBox));
        content.add(controls, BorderLayout.SOUTH);
	}
	
	private JPanel setScaleGrid(String title, Component ...comp){
		JPanel scaleGrid = new JPanel(new GridLayout(1, 0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder(title));
        for (Component c: comp){
        	scaleGrid.add(c);
        }
		return scaleGrid;
	}
}
