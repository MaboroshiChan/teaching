package graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.util.mxConstants;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphVisualization {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphVisualization::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graph<String, DefaultEdge> graph = createSampleGraph();
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);
        setEdgeStyle(graphAdapter);


        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        frame.add(graphComponent, BorderLayout.CENTER);

        // Apply a layout to the graph
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void setEdgeStyle(JGraphXAdapter<String, DefaultEdge> graphAdapter) {
        mxStylesheet stylesheet = graphAdapter.getStylesheet();
        Map<String, Object> edgeStyle = new HashMap<>(stylesheet.getDefaultEdgeStyle());
        edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        stylesheet.setDefaultEdgeStyle(edgeStyle);
    }

    private static Graph<String, DefaultEdge> createSampleGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        graph.addVertex("0");
        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");
        graph.addVertex("7");
        graph.addVertex("8");
        graph.addVertex("9");

        graph.addEdge("0", "1");
        graph.addEdge("1", "2");
        graph.addEdge("2", "3");
        graph.addEdge("3", "4");
        graph.addEdge("4", "5");
        graph.addEdge("5", "6");
        graph.addEdge("6", "7");
        graph.addEdge("7", "8");
        graph.addEdge("8", "9");
        graph.addEdge("9", "0");

        return graph;
    }
}
