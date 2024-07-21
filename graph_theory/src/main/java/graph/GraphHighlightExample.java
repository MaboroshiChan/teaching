package graph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.ext.JGraphXAdapter;

import javax.swing.*;
import java.awt.*;

public class GraphHighlightExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraphHighlightExample::createAndShowGui);
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Graph Highlight Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graph<String, DefaultEdge> graph = createGraph();
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph);
        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        frame.add(graphComponent, BorderLayout.CENTER);

        // Apply a layout to the graph
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // Find the path using JGraphT
        DijkstraShortestPath<String, DefaultEdge> dijkstra = new DijkstraShortestPath<>(graph);
        GraphPath<String, DefaultEdge> path = dijkstra.getPath("A", "E");

        // Highlight the path using JGraphX
        for (DefaultEdge edge : path.getEdgeList()) {
            graphAdapter.setCellStyle("strokeColor=red;strokeWidth=2", new Object[]{graphAdapter.getEdgeToCellMap().get(edge)});
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Graph<String, DefaultEdge> createGraph() {
        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "A");

        return graph;
    }
}
