package ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Lexie on 11/17/16.
 */
class ConsumPanel extends JPanel {
    JPanel groupSelectionPanel;
//    JPanel barChartPanel;
    BarChart barChart;
    Font fontBig = new Font("Georgia", Font.PLAIN, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);
    int height;
    int width;


    public ConsumPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        height = getHeight();
        width = getWidth();
        groupSelectionPanel = createGroupSelection();
//        barChartPanel = createBarChart();
        barChart = createBarChart();
        add(groupSelectionPanel);
        add(barChart);
    }



    private JPanel createGroupSelection() {
        JButton systemButton = new JButton("System");
        systemButton.setFont(fontBig);
        JButton northButton = new JButton("North");
        northButton.setFont(fontBig);
        JButton southButton = new JButton("South");
        southButton.setFont(fontBig);
        JButton eastButton = new JButton("East");
        eastButton.setFont(fontBig);
        JButton westButton = new JButton("West");
        westButton.setFont(fontBig);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBorder(new CompoundBorder(new LineBorder(Color.decode("#ebf5ff")), new EmptyBorder(10, 10, 10, 10)));
        panel.add(systemButton);
        panel.add(northButton);
        panel.add(southButton);
        panel.add(eastButton);
        panel.add(westButton);

        return panel;
    }
    private BarChart createBarChart() {
        int[] consumption = {40, 60, 40, 30, 80, 100, 60};
        BarChart barChart = new BarChart();
//        barChart.setPreferredSize(new Dimension(800, 800));
        for(int i = 0; i < consumption.length; i++) {
            barChart.addBar(consumption[i]);
        }
        barChart.setBorder(new CompoundBorder(new LineBorder(Color.decode("#ebf5ff")), new EmptyBorder(10, 10, 10, 10)));
//        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(500, 500));
//        panel.add(barChart);
//        panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
//        panel.setVisible(true);
//        return panel;
        return barChart;

    }



}
