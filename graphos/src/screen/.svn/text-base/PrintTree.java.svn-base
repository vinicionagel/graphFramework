package screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.PolarPoint;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.util.Animator;
import edu.uci.ics.jung.visualization.util.LabelWrapper;
import java.awt.Paint;
import org.apache.commons.collections15.Transformer;

public class PrintTree extends JApplet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1001L;
	
	Forest<String, Integer> graph;
    Factory<DirectedGraph<String, Integer>> graphFactory = new Factory<DirectedGraph<String, Integer>>() {
        @Override
        public DirectedGraph<String, Integer> create() {
            return new DirectedSparseMultigraph<>();
        }
    };
    Factory<Tree<String, Integer>> treeFactory = new Factory<Tree<String, Integer>>() {
        @Override
        public Tree<String, Integer> create() {
            return new DelegateTree<>(graphFactory);
        }
    };
    
    VisualizationViewer<String, Integer> vv;
    VisualizationServer.Paintable rings;    
    TreeLayout<String, Integer> treeLayout;
    RadialTreeLayout<String, Integer> radialLayout;
    /**
     * 
     * @param graph
     * @param solucao é para pintar de verdinho ou a cor que será feita por annotation
     */
    public PrintTree(Forest<String, Integer> graph, final HashSet<String> solucao) {
        this.graph = graph;                    
                       
        treeLayout = new TreeLayout<>(graph);
        radialLayout = new RadialTreeLayout<>(graph);
        radialLayout.setSize(new Dimension(600, 600));        
        vv = new VisualizationViewer<>(treeLayout, new Dimension(600, 600));
        vv.setBackground(Color.white);        
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line()); 
        vv.getRenderContext().setVertexLabelTransformer(new LabelWrapper(10));  //Numero de caracteres da label do vertice      
        vv.setVertexToolTipTransformer(new ToStringLabeller());
        vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));                      
        
        Transformer<String,Paint> vertexColor = new Transformer<String,Paint>() {
            @Override
            public Paint transform(String i) {
                if(solucao.contains(i)){
                    return Color.GREEN;
                } else {
                    return Color.LIGHT_GRAY;
                }                
            }
        };        
        
        vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
        
        rings = new Rings();

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

        JToggleButton radial = new JToggleButton("Radial");
        radial.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    LayoutTransition<String, Integer> lt = new LayoutTransition<>(vv, treeLayout, radialLayout);
                    Animator animator = new Animator(lt);
                    animator.start();
                    vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
                    vv.addPreRenderPaintable(rings);
                } else {
                    LayoutTransition<String, Integer> lt = new LayoutTransition<>(vv, radialLayout, treeLayout);
                    Animator animator = new Animator(lt);
                    animator.start();
                    vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
                    vv.removePreRenderPaintable(rings);
                }
                vv.repaint();
            }
        });

        JPanel scaleGrid = new JPanel(new GridLayout(1, 0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(radial);
        controls.add(scaleGrid);
        controls.add(modeBox);

        content.add(controls, BorderLayout.SOUTH);
    }

    class Rings implements VisualizationServer.Paintable {

        Collection<Double> depths;

        public Rings() {
            depths = getDepths();
        }

        private Collection<Double> getDepths() {
            Set<Double> depth = new HashSet<>();
            Map<String, PolarPoint> polarLocations = radialLayout.getPolarLocations();
            for (String v : graph.getVertices()) {
                PolarPoint pp = polarLocations.get(v);
                depth.add(pp.getRadius());
            }
            return depth;
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.lightGray);
            Graphics2D g2d = (Graphics2D) g;
            Point2D center = radialLayout.getCenter();

            Ellipse2D ellipse = new Ellipse2D.Double();
            for (double d : depths) {
                ellipse.setFrameFromDiagonal(center.getX() - d, center.getY() - d, center.getX() + d, center.getY() + d);
                Shape shape = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).transform(ellipse);
                g2d.draw(shape);
            }
        }

        @Override
        public boolean useTransform() {
            return true;
        }
    }
}