package graph;

import javax.swing.*;
import java.awt.*;

public class FixedSizePanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FixedSizePanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        // 创建一个包装 vertexPanel 的固定尺寸的容器
        JPanel wrapperPanel = new JPanel(null);
        wrapperPanel.setPreferredSize(new Dimension(100, 60));
        wrapperPanel.setMaximumSize(wrapperPanel.getPreferredSize());
        wrapperPanel.setMinimumSize(wrapperPanel.getPreferredSize());

        JPanel vertexPanel = new JPanel(new BorderLayout());
        vertexPanel.setBounds(0, 0, 100, 60);
        JLabel vertexLabel = new JLabel("选择顶点数");
        vertexPanel.add(vertexLabel, BorderLayout.NORTH);
        JSpinner vertexSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        vertexSpinner.setPreferredSize(new Dimension(100, 50));
        vertexPanel.add(vertexSpinner);

        wrapperPanel.add(vertexPanel);
        mainPanel.add(wrapperPanel, BorderLayout.NORTH); // 将包装后的 vertexPanel 添加到主面板

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
