import graph.GraphAlgorithms;

import javax.swing.*;
import java.awt.*;

public class MainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::createGUI);
    }

    private static void createGUI() {
        JButton printButton = new JButton("生成图");
        JButton printButton2 = new JButton("生成有向图");

        JFrame frame = new JFrame("图形界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLayout(new GridLayout(5, 1));

        // 创建一个固定尺寸的包装面板
        JPanel fixedSizePanel = new JPanel();
        fixedSizePanel.setLayout(new BoxLayout(fixedSizePanel, BoxLayout.X_AXIS));

        // 创建选择顶点数的面板
        JPanel vertexPanel = new JPanel(new BorderLayout());
        vertexPanel.setPreferredSize(new Dimension(100, 60));
        JLabel vertexLabel = new JLabel("选择顶点数");
        vertexPanel.add(vertexLabel, BorderLayout.NORTH);
        JSpinner vertexSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        vertexSpinner.setPreferredSize(new Dimension(100, 50));
        vertexPanel.add(vertexSpinner);
        vertexPanel.setMaximumSize(vertexPanel.getPreferredSize());
        vertexPanel.setMinimumSize(vertexPanel.getPreferredSize());
        fixedSizePanel.add(vertexPanel);

        // 创建选择边数的面板
        JPanel edgePanel = new JPanel(new BorderLayout());
        edgePanel.setPreferredSize(new Dimension(100, 60));
        JLabel edgeLabel = new JLabel("选择边数");
        edgePanel.add(edgeLabel, BorderLayout.NORTH);
        JSpinner edgeSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        edgeSpinner.setSize(100, 50);
        edgePanel.add(edgeSpinner, BorderLayout.CENTER);
        edgePanel.setMaximumSize(edgePanel.getPreferredSize());
        edgePanel.setMinimumSize(edgePanel.getPreferredSize());
        fixedSizePanel.add(edgePanel);

        // 创建选择顶点对的面板
        JPanel edgePairsPanel = new JPanel(new BorderLayout());
        JLabel edgePairsLabel = new JLabel("输入顶点对 (格式: V1-V2, V3-V4, ...)");
        edgePairsPanel.add(edgePairsLabel, BorderLayout.NORTH);
        JTextArea edgePairsTextArea = new JTextArea(3, 20);
        JScrollPane edgePairsScrollPane = new JScrollPane(edgePairsTextArea);
        edgePairsPanel.add(edgePairsScrollPane, BorderLayout.CENTER);
        edgePairsPanel.setSize(100, 50);

        // 创建打印按钮面板
        JPanel printButtonPanel = new JPanel();
        printButtonPanel.add(printButton);
        // ...
        printButtonPanel.add(printButton);
        printButtonPanel.add(printButton2);

        printButton.addActionListener(e -> {
            int vertexCount = (int) vertexSpinner.getValue();
            int edgeCount = (int) edgeSpinner.getValue();
            String edgePairs = edgePairsTextArea.getText();
            String[] pairs = edgePairs.split(", ");

            int[][] edges = new int[edgeCount][3];

            for(int i = 0; i < edgeCount; ++i){
                String[] pair = pairs[i].split("-");
                int src = Integer.parseInt(pair[0]);
                int dst = Integer.parseInt(pair[1]);

                edges[i][0] = src;
                edges[i][1] = dst;
                edges[i][2] = 1;
            }
            GraphAlgorithms new_graph = new GraphAlgorithms(edges, vertexCount, false);
            new_graph.show_graph();
        });

        printButton2.addActionListener(e -> {
            int vertexCount = (int) vertexSpinner.getValue();
            int edgeCount = (int) edgeSpinner.getValue();
            String edgePairs = edgePairsTextArea.getText();
            String[] pairs = edgePairs.split(", ");

            int[][] edges = new int[edgeCount][3];

            for(int i = 0; i < edgeCount; ++i){
                String[] pair = pairs[i].split("-");
                int src = Integer.parseInt(pair[0]);
                int dst = Integer.parseInt(pair[1]);

                edges[i][0] = src;
                edges[i][1] = dst;
                edges[i][2] = 1;
            }
            GraphAlgorithms new_graph = new GraphAlgorithms(edges, vertexCount, true);
            new_graph.show_graph();
        });

        JLabel outputLabel = new JLabel("输出结果");
        // 创建输出标签面板
        JPanel outputPanel = new JPanel();
        outputPanel.add(outputLabel);

        // 将面板添加到框架
        frame.add(fixedSizePanel);
        frame.add(edgePairsPanel);
        frame.add(printButtonPanel);
        // ...
        frame.setVisible(true);
    }
}
