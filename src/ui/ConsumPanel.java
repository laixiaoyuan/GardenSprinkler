package ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Lexie on 11/17/16.
 */
class ConsumPanel extends JPanel {
    JPanel groupSelectionPanel;
    JPanel barChartPanel;
    BarChart barChart;
    Font fontBig = new Font("Georgia", Font.PLAIN, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);
    int height;
    int width;

    int[] sysConsum;
    int[] northConsum;
    int[] southConsum;
    int[] eastConsum;
    int[] westConsum;


    public ConsumPanel() {
        super();
        setLayout(new BorderLayout());
        height = getHeight();
        width = getWidth();
        groupSelectionPanel = createGroupSelection();
        barChartPanel = createBarChartPanel();

        add(groupSelectionPanel, BorderLayout.NORTH);
        add(barChartPanel, BorderLayout.CENTER);
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
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(fontBig);

        JPanel panel = new JPanel();
        panel.setBorder(new CompoundBorder(new LineBorder(Color.decode("#ebf5ff")), new EmptyBorder(10, 10, 10, 10)));
        panel.add(systemButton);
        panel.add(northButton);
        panel.add(southButton);
        panel.add(eastButton);
        panel.add(westButton);
        panel.add(refreshButton);

        return panel;
    }

    private JPanel createBarChartPanel() {
        // code below is just for test
        sysConsum = new int[]{2201, 3401, 2501, 1101, 2301, 1501, 2301};
        northConsum = new int[]{1201, 401, 501, 101, 301, 501, 301};
        southConsum = new int[]{201, 401, 50, 110, 30, 50, 30};
        eastConsum = new int[]{120, 40, 50, 10, 30, 50, 30};
        westConsum = new int[]{20, 40, 150, 10, 30, 50, 30};
        // code above is just for test

        BarChart sysBarChart = createBarChartByGroup("SYSTEM", sysConsum);
        BarChart northBarChart = createBarChartByGroup("NORTH", northConsum);
        BarChart southBarChart = createBarChartByGroup("SOUTH", southConsum);
        BarChart eastBarChart = createBarChartByGroup("EAST", eastConsum);
        BarChart westBarChart = createBarChartByGroup("WEST", westConsum);

        JPanel cardPanel = new JPanel();
        CardLayout cards = new CardLayout();
        cardPanel.setLayout(cards);

        cardPanel.add(sysBarChart, "SYSTEM");
        cardPanel.add(northBarChart, "NORTH");
        cardPanel.add(southBarChart, "SOUTH");
        cardPanel.add(eastBarChart, "EAST");
        cardPanel.add(westBarChart, "WEST");

        cards.show(cardPanel, "SYSTEM");

        return cardPanel;
    }

    private BarChart createBarChartByGroup(String name, int[] consumption) {
        BarChart barChart = new BarChart(getHighWaterVolumeWarning(name));
        for(int i = 0; i < consumption.length; i++) {
            barChart.addBar(consumption[i]);
        }
        barChart.setBorder(new CompoundBorder(new LineBorder(Color.decode("#ebf5ff")), new EmptyBorder(10, 10, 10, 10)));

        return barChart;
    }

    private int getHighWaterVolumeWarning(String name) {
        switch (name) {
            case "SYSTEM":
                return 2500;
            default:
                return 500;
        }
    }



    public void saveConsumptionArrayToLocal(String name, int[] consumArray) {
        switch (name) {
            case "SYSTEM":
                sysConsum = consumArray;
                break;
            case "NORTH":
                northConsum = consumArray;
                break;
            case "SOUTH":
                southConsum = consumArray;
                break;
            case "EAST":
                eastConsum = consumArray;
                break;
            case "WEST":
                westConsum = consumArray;
                break;
            default:
                System.out.println("Invalid name input");
                break;
        }
    }

    public JPanel getCardPanelByName(String name) {
        switch (name) {
            case "SYSTEM":
                return (JPanel)barChartPanel.getComponent(0);
            case "NORTH":
                return (JPanel)barChartPanel.getComponent(1);
            case "SOUTH":
                return (JPanel)barChartPanel.getComponent(2);
            case "EAST":
                return (JPanel)barChartPanel.getComponent(3);
            case "WEST":
                return (JPanel)barChartPanel.getComponent(4);
            default:
                System.out.println("Input name invalid");
                return null;
        }
    }


    public void addGetGroupConsumListener(String name, ActionListener listener) {
        ((JButton)groupSelectionPanel.getComponent(0)).addActionListener(listener);
    }

    public void addRefreshConsumListener(ActionListener listener) {
        ((JButton)groupSelectionPanel.getComponent(5)).addActionListener(listener);
    }


}
