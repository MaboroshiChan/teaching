import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import graph.GraphAlgorithms;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入顶点数 numVertices: ");
        int numVertices = scanner.nextInt();

        System.out.println("请输入边数 numEdges: ");
        int numEdges = scanner.nextInt();

        int[][] edges = new int[numEdges][3];

        System.out.println("请输入 " + numEdges + " 条边，每行输入 a b 表示顶点 a 和顶点 b 之间的边: ");
        for (int i = 0; i < numEdges; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            edges[i] = new int[]{a, b, 1};
        }
        JFrame frame = new JFrame();

        GraphAlgorithms new_graph = new GraphAlgorithms(edges, numVertices, false);
        new_graph.show_graph();


        System.out.println("打印每个顶点的度数: ");

        var degrees = new_graph.degrees();
        for(int i = 0; i < degrees.length-1;++i){
            System.out.print(i + "->" + degrees[i] + ", ");
        }
        System.out.println(degrees.length - 1 + "->" + degrees[degrees.length - 1]);

        System.out.println("连通图吗？" + new_graph.isConnected());

        var hasEuler = new_graph.isEulerian();
        System.out.println("有欧拉圈? " + hasEuler);
        if(hasEuler){
            List<Integer> eulerian = new_graph.findEulerianPath();
            System.out.println("打印欧拉圈顶点：");
            System.out.println(eulerian.toString());
        }

        var ha = new_graph.findHamiltonianPath();
        if (ha != null){
            System.out.println("有哈密顿路径: " + ha.toString());
        }else{
            System.out.println("没有哈密顿路径");
        }

        String two_components = "0-1, 1-2, 2-3, 3-0, 4-5, 5-6, 6-7, 7-4";

    }
}
