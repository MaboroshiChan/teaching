package graph;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;


import java.util.PriorityQueue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.ext.JGraphXAdapter;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.util.mxConstants;

import javax.swing.*;

public class GraphAlgorithms {

    private int[][] edges; // store edges passing in
    private int[][] adj_matrix; // adjacent matrix used for edge length inquery solely
    private HashSet<Integer>[] adj_list; // adjacent list used for traversing.
    private int numVertices; // #vertex
    private int[] dist = null; // minimum distance from source in Dijsktra algorithm
    private Integer[] prev = null; // previous vertex of a vertex in shortest path.
    private Graph<Integer, DefaultEdge> graph_vis;
    private JFrame graph_pic;
    private boolean is_directed;

    final int PERMENENT = 2;
    final int TEMPORARY = 1;
    final int UNMARKED = 0;
    final int INFINITY = 2 << 10;

    private Optional<Boolean> is_connected = Optional.empty();

    interface Traverse {
        void apply(int top, boolean[] visited);
    }

    int[][] get_matrix() {
        return adj_matrix;
    }

    HashSet<Integer>[] get_list() {
        return adj_list;
    }



    public GraphAlgorithms(int[][] edges, int size, boolean direct) { // initialization
        this.numVertices = size;
        this.edges = edges;
        adj_matrix = make_adjacent_matrix(edges, direct); // initialize matrix
        adj_list = make_graph(edges, size, direct); // initialize adjacent list
        make_vis();
        System.out.println("size = " + size);
        is_connected = Optional.of(this.isConnected());
    }

    public int[] degrees(){
        int[] degrees = new int[numVertices];
        for(int i = 0;i < numVertices;++i){
            degrees[i] = 0;
            int ii = i;
            adj_list[i].forEach((x)->{
                degrees[ii] += 1;
            });
        }
        return degrees;
    }

    /**
     * make adjacent list
     * 
     * @param edges
     * @param size
     * @param direct is the graph direct or undirect
     * @return
     */
    private HashSet<Integer>[] make_graph(int[][] edges, int size, boolean direct) {
        HashSet<Integer>[] res = new HashSet[size];
        this.is_directed = direct;

        for (int i = 0; i < size; ++i) {
            res[i] = new HashSet<>();

        }
        for (int e = 0; e < edges.length; ++e) {
            int v1 = edges[e][0];
            int v2 = edges[e][1];

            res[v1].add(v2);
            if (!direct) {
                res[v2].add(v1);
            }
        }

        return res;
    }

    private void make_vis(){
        graph_vis = new SimpleGraph<>(DefaultEdge.class);
        for(int i = 0; i <numVertices;++i){
            graph_vis.addVertex(i);
        }

        for (int e = 0; e < edges.length; ++e) {
            int v1 = edges[e][0];
            int v2 = edges[e][1];
            graph_vis.addEdge(v1, v2);
        }
    }

    private void setEdgeStyle(JGraphXAdapter<Integer, DefaultEdge> graphAdapter) {
        if(!this.is_directed){
            mxStylesheet stylesheet = graphAdapter.getStylesheet();
            Map<String, Object> edgeStyle = new HashMap<>(stylesheet.getDefaultEdgeStyle());
            edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
            stylesheet.setDefaultEdgeStyle(edgeStyle);
        }
    }

    public void show_graph(){
        // Create a JGraphXAdapter and visualize the graph using JGraphX

        graph_pic = new JFrame("显示图");
        graph_pic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graph_pic.setSize(600, 800);
        graph_pic.setLayout(new GridLayout(3, 1));

        JGraphXAdapter<Integer, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph_vis);
        setEdgeStyle(graphAdapter);

        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        JButton euler_button = new JButton("显示欧拉圈");
        JButton ham_button = new JButton("显示哈密顿圈");
        JButton conneted_components = new JButton("显示连通分量");

        JPanel printButtonPanel = new JPanel();
        printButtonPanel.add(euler_button);
        printButtonPanel.add(ham_button);
        printButtonPanel.add(conneted_components);
        graph_pic.add(printButtonPanel, BorderLayout.SOUTH);

        JPanel outputPanel = new JPanel();
        JLabel outputLabel = new JLabel(); // 添加一个标签来显示结果
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));

        int[] vertexDegrees = this.degrees();
        int connectedComponentsCount = this.findConnectedComponents().size();
        boolean isConnected = this.isConnected();

        String outputText = "<html>顶点度: " + Arrays.toString(vertexDegrees) + ", " +
                "连通分支数: " + connectedComponentsCount + ", " +
                "连通性: " + (isConnected ? "连通" : "非连通") + "</html>";
        outputLabel.setText(outputText);

        // 按了显示欧拉圈的按钮会打印出欧拉圈
        euler_button.addActionListener(e2 -> this.visualize_path(this.findEulerianPath(), graph_pic));
        ham_button.addActionListener(e3 -> this.visualize_path(this.findHamiltonianPath(), graph_pic));
        // 按了显示连通分量的按钮会打印出连通分量的数量
        conneted_components.addActionListener(e4 -> {
            var connected_components = this.findConnectedComponents();
            // visualize connected components
            this.visualize_paths(connected_components, graph_pic);
        });

        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        outputPanel.add(graphComponent);
        outputPanel.add(outputLabel);
        graph_pic.add(outputPanel, BorderLayout.CENTER);
        graph_pic.setVisible(true);
    }

    /**
     * make adjacent matrix
     * 
     * @param edges
     * @param direct
     * @return adjacent matrix
     */
    private int[][] make_adjacent_matrix(int[][] edges, boolean direct) {
        int[][] matrix = new int[numVertices][numVertices];
        for (int i = 0; i < edges.length; ++i) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            matrix[v1][v2] = edges[i][2];
            if (!direct) {
                matrix[v2][v1] = edges[i][2];
            }
        }
        return matrix;
    }

    /**
     * perform BFS
     * 
     * @param func callback function when visiting a vertex.
     */
    void bfs(Traverse func) {
        Queue<Integer> queue = new LinkedList<>(); // queue for bfs
        boolean[] visited = new boolean[this.numVertices];
        Arrays.fill(visited, false);
        for (int v = 0; v < numVertices; ++v) {
            if (visited[v])
                continue;
            visited[v] = true;
            queue.add(v);
            while (!queue.isEmpty()) {
                int top = queue.poll();
                // do sometthing
                func.apply(top, visited);
                HashSet<Integer> adjcents = this.adj_list[top]; // retrieve adjacent list
                for (int w : adjcents) {
                    if (visited[w])
                        continue;
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    /**
     * Helper function for dfs, used for recursion.
     * 
     * @param top     currently visiting vertex
     * @param visited array of visited or unvisited vertices
     * @param func    callback function.
     */
    void dfs_helper(int top, boolean[] visited, Traverse func) {
        visited[top] = true;
        HashSet<Integer> adj = this.adj_list[top]; // retrieve adjacent list.
        func.apply(top, visited); // apply callback function
        for (int x : adj) {
            if (!visited[x]) {
                dfs_helper(x, visited, func);
            }
        }
    }

    /**
     * DFS search from vertex 0
     * 
     * @param func callback function
     */
    void dfs(Traverse func) {
        boolean[] visited = new boolean[this.numVertices];
        Arrays.fill(visited, false);
        for (int v = 0; v < this.numVertices; ++v) {
            if (visited[v])
                continue;
            dfs_helper(v, visited, func);
        }
    }

    /**
     * topological sort
     * 
     * @param v       starting vertex
     * @param result  result of topological sort
     * @param visited visited or unvisited vertex.
     * @throws Exception
     */
    void topo_dfs(int v, LinkedList<Integer> result, int[] visited) throws Exception {
        if (visited[v] == PERMENENT) { // if the current vertex has permanent mark, stop dfs here then.
            return;
        }
        if (visited[v] == TEMPORARY) { // if the current vertex is temporary, then the graph has a cycle
            throw new Exception("The graph has cycle. Cannot perform topological sort.");
        }
        visited[v] = TEMPORARY;
        var adj = this.adj_list[v];
        for (int m : adj) {
            topo_dfs(m, result, visited);
        }
        visited[v] = PERMENENT;
        result.addFirst(v);
    }

    /**
     * topological sort main function starting at vertex A.
     * 
     * @return
     * @throws Exception
     */
    LinkedList<Integer> topological_sort() throws Exception {
        LinkedList<Integer> sorted = new LinkedList<>();
        int[] visited = new int[this.numVertices];
        for (int v = 0; v < this.numVertices; ++v) {
            if (visited[v] == 0) {
                topo_dfs(v, sorted, visited);
            }
        }
        return sorted;
    }

    /**
     * Dijkstra algorithm starting at source vertex.
     * 
     * @param source
     */
    void djikstra(int source) {
        int[] dist = new int[this.numVertices];
        Integer[] prev = new Integer[this.numVertices];
        Arrays.fill(dist, this.INFINITY);
        Arrays.fill(prev, null);

        LinkedList<Integer> pq = new LinkedList<>();

        for (int v = 0; v < this.numVertices; ++v) {
            pq.add(v);
        }

        dist[source] = 0;
        while (!pq.isEmpty()) {
            pq.sort(new Comparator<Integer>() {

                @Override
                public int compare(Integer o1, Integer o2) {

                    return dist[o1] - dist[o2];
                }

            });

            int u = pq.poll();
            var neighbours = this.adj_list[u];
            for (int v : neighbours) {

                int alt = dist[u] + this.adj_matrix[u][v];
                // if(v == 3){
                // System.out.println("u = " + u + " dist[u]: " + dist[u] + " dist[u][v]: " +
                // this.adj_matrix[u][v]);
                // }
                if (alt < dist[v]) {
                    dist[v] = alt;
                    prev[v] = u;
                }
            }
        }

        this.prev = prev;
        this.dist = dist;
        System.out.println("Dijkstra result");
        System.out.println("previous node array " + Arrays.toString(prev));
        System.out.println("shortest path to node i " + Arrays.toString(dist));
        System.out.println("");
    }

    /**
     * Data structure of an Edge
     * `src`---->`end`, with cost or length or value or distance `dist`.
     */
    class Edge {
        public int src; // source
        public int end; // end
        public int dist;

        Edge(int _src, int _end, int _dist) {
            src = _src;
            end = _end;
            dist = _dist;
        }

    }

    /**
     * Prim algorithm. Implement according to pseudo-code written on Wikipedia.
     * 
     * @param source
     * @return an ArrayList of minimum spanning tree.
     */
    ArrayList<Edge> prim(int source) {

        // Initialization
        ArrayList<Edge> result = new ArrayList<>(); // result arraylist
        ArrayList<Integer> vertices = new ArrayList<>(this.numVertices); // initialize V_new

        boolean[] visited = new boolean[numVertices];
        Arrays.fill(visited, false);

        PriorityQueue<Edge> pq = new PriorityQueue<>(edges.length, new Comparator<Edge>() { // priority queue for
                                                                                            // selecting
                                                                                            // edges with minimum cost.
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.dist - e2.dist;
            }

        });

        vertices.add(source); // add source new vertex to V_new
        visited[source] = true; // mark this vertex

        var adj = this.adj_list[source]; // insert all edges connected to source.
        for (int e : adj) {
            pq.add(new Edge(source, e, this.adj_matrix[source][e]));
        }
        // end of Initialization.

        while (vertices.size() < this.numVertices) {
            Edge min_e = pq.poll(); // take the min
            int u = min_e.end;
            vertices.add(u); // add new v to V_new
            visited[u] = true;
            result.add(min_e);
            var e_adj = this.adj_list[u];
            e_adj.forEach(x -> { // add neibourhoods of u if they are not in V_new.

                if (!visited[x]) {
                    pq.add(new Edge(u, x, this.adj_matrix[u][x]));
                }
            });

        }
        int total_cost = 0; // computing total_cost of this minimum spanning tree.
        for (int r = 0; r < result.size(); ++r) {
            var get_r = result.get(r);
            total_cost += get_r.dist;
        }

        System.out.println("");
        System.out.println("prime total cost = " + total_cost);

        return result;
    }

    // union find
    int find(int[] parent, int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]]; // path compression by halving
            p = parent[p];
        }
        return p;
    }

    void Union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }

    /**
     * kruskal algorithm with starting vertex A
     * 
     * @return Edges of minimum spanning tree
     */
    ArrayList<Edge> kruskal() {
        int[] parent = new int[numVertices]; // initialize parent set for union find.
        for (int i = 0; i < numVertices; ++i) {
            parent[i] = i;
        }

        Edge[] es = new Edge[this.edges.length]; // initialize Array of all edges for greedy selection.
        for (int i = 0; i < es.length; ++i) {
            es[i] = new Edge(this.edges[i][0], this.edges[i][1], this.edges[i][2]);
        }
        Arrays.sort(es, new Comparator<Edge>() { // sort according to the cost of each edge.

            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.dist - e2.dist;
            }

        });

        ArrayList<Edge> res = new ArrayList<>(); // result storing edges of minimum spanning tree
        // end of initialization.
        // Main loop
        for (int e = 0; e < es.length; ++e) { //
            int src = es[e].src; // selecting the smallest edge
            int end = es[e].end;
            if (find(parent, src) != find(parent, end)) { // check if two ends are in the same connected component.
                res.add(es[e]);  // if so then add it would create a cycle. 
                Union(parent, src, end); 
            }
        }
        int total_dist = 0;
        for (Edge e : res) {
            total_dist += e.dist;
        }
        System.out.println("kruskal total dist = " + total_dist);

        return res;
    }
    /**
     * 是否连通
     * @return
     */
    public boolean isConnected() {
        boolean[] visited = new boolean[numVertices];

        // start DFS from vertex 0
        DFS(0, visited);

        // check if all vertices are visited
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    private void DFS(int v, boolean[] visited) {
        visited[v] = true;

        for (int i : this.adj_list[v]) {
            if (!visited[i]) {
                DFS(i, visited);
            }
        }
    }
    /**
     * 连通分量
     * @return
     */
    public List<List<Integer>> findConnectedComponents() {
        List<List<Integer>> components = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                DFS(i, visited, component);
                components.add(component);
            }
        }

        return components;
    }

    private void DFS(int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);

        for (int i : this.adj_list[v]) {
            if (!visited[i]) {
                DFS(i, visited, component);
            }
        }
    }

    public boolean isEulerian() {
        for (int i = 0; i < numVertices; i++) {
            if (this.adj_list[i].size() % 2 != 0) {
                return false;
            }
        }
        return this.is_connected.get();
    }

    public List<Integer> findEulerianPath() {
        // Count vertices with odd degree
        int startVertex = -1;
        int oddDegreeVertices = 0;
        HashSet<Integer>[] adjList = new HashSet[this.adj_list.length];

        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = new HashSet<>(this.adj_list[i]);
            if (adj_list[i].size() % 2 != 0) {
                oddDegreeVertices++;
                if (startVertex == -1) {
                    startVertex = i;
                }
            }
        }

        // If there are more than two vertices with odd degree, there is no Eulerian path
        if (oddDegreeVertices > 2) {
            return null;
        }

        // If there are exactly two vertices with odd degree, start from the first one, otherwise start from vertex 0
        if (startVertex == -1) {
            startVertex = 0;
        }

        List<Integer> path = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            while (!adjList[currentVertex].isEmpty()) {
                int nextVertex = adjList[currentVertex].iterator().next();
                adjList[currentVertex].remove(nextVertex);
                adjList[nextVertex].remove(currentVertex);
                stack.push(currentVertex);
                currentVertex = nextVertex;
            }
            path.add(currentVertex);
        }

        return path;
    }

    public List<Integer> findHamiltonianPath() {
        if (adj_list == null || adj_list.length == 0) {
            return null;
        }

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            path.add(i);
            visited[i] = true;

            if (findHamiltonianPathHelper(path, visited, 1)) {
                return path;
            }

            path.remove(path.size() - 1);
            visited[i] = false;
        }
        return null;
    }

    private boolean findHamiltonianPathHelper(List<Integer> path, boolean[] visited, int position) {
        if (position == adj_list.length) {
            return true;
        }

        int currentVertex = path.get(position - 1);
        for (Integer neighbor : adj_list[currentVertex]) {
            if (!visited[neighbor]) {
                path.add(neighbor);
                visited[neighbor] = true;

                if (findHamiltonianPathHelper(path, visited, position + 1)) {
                    return true;
                }

                path.remove(path.size() - 1);
                visited[neighbor] = false;
            }
        }

        return false;
    }

    public void visualize_path(List<Integer> path, JFrame frame) {
        // Create a JGraphXAdapter and visualize the graph using JGraphX
        JGraphXAdapter<Integer, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph_vis);
        setEdgeStyle(graphAdapter);

        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // Highlight the path
        for (int i = 0; i < path.size() - 1; i++) {
            Integer source = path.get(i);
            Integer target = path.get(i + 1);
            DefaultEdge edge = graph_vis.getEdge(source, target);
            graphAdapter.setCellStyle("strokeColor=blue;strokeWidth=3", new Object[]{graphAdapter.getEdgeToCellMap().get(edge)});
            graphAdapter.setCellStyle("fillColor=blue", new Object[]{graphAdapter.getVertexToCellMap().get(source), graphAdapter.getVertexToCellMap().get(target)});
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        frame.getContentPane().remove(1);
        frame.getContentPane().add(graphComponent);
        frame.setVisible(true);
    }

    /**
     * visualize paths with different colors using JGraphX and JFrame
     */
    public void visualize_paths(List<List<Integer>> paths, JFrame frame) {
        // Create a JGraphXAdapter and visualize the graph using JGraphX
        JGraphXAdapter<Integer, DefaultEdge> graphAdapter = new JGraphXAdapter<>(graph_vis);
        setEdgeStyle(graphAdapter);

        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        // define colors
        String[] colors = {"red", "green", "blue", "yellow", "orange", "pink", "purple", "cyan", "magenta", "gray", "darkGray", "lightGray"};

        // Highlight the paths
        for (int i = 0; i < paths.size(); i++) {
            List<Integer> path = paths.get(i);
            for (int j = 0; j < path.size() - 1; j++) {
                Integer source = path.get(j);
                Integer target = path.get(j + 1);
                DefaultEdge edge = graph_vis.getEdge(source, target);
                graphAdapter.setCellStyle("strokeColor=" + colors[i % colors.length] + ";strokeWidth=3", new Object[]{graphAdapter.getEdgeToCellMap().get(edge)});
                graphAdapter.setCellStyle("fillColor=" + colors[i % colors.length], new Object[]{graphAdapter.getVertexToCellMap().get(source), graphAdapter.getVertexToCellMap().get(target)});
            }
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        frame.getContentPane().remove(1);
        frame.getContentPane().add(graphComponent);
        frame.setVisible(true);
    }

}
