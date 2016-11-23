package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lexie on 11/17/16.
 */
class ConsumPanel extends JPanel {
    JPanel groupSelectionPanel;
    JPanel barChartPanel;
    Font fontBig = new Font("Georgia", Font.PLAIN, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);


    public ConsumPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        groupSelectionPanel = createGroupSelection();
        barChartPanel = createBarChart();
        add(groupSelectionPanel);
        add(barChartPanel);
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
        panel.add(systemButton);
        panel.add(northButton);
        panel.add(southButton);
        panel.add(eastButton);
        panel.add(westButton);
        return panel;
    }
    private JPanel createBarChart() {
        JPanel panel = new JPanel();
        return panel;

    }



}
