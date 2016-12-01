package ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Lexie on 11/17/16.
 */
class ConsumPanel extends JPanel {
    JPanel groupSelectionPanel;
    JPanel barChartPanel;
    private JPanel cardPanel;
    BarChart barChart;
    Font fontBig = new Font("Georgia", Font.PLAIN, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);

    public ConsumPanel() {
        super();
        setLayout(new BorderLayout());

        cardPanel = new JPanel();
        CardLayout cards = new CardLayout();
        cardPanel.setLayout(cards);

        groupSelectionPanel = createGroupSelection();

        add(groupSelectionPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
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
//
//    private JPanel createBarChartPanel() {
//
//
//
//        return cardPanel;
//    }

    public void createBarChartByGroup(String name, int[] consumption) {
        reverseArray(consumption);
        System.out.println("CONSUMPTION 0" + consumption[0]);
        BarChart barChart = new BarChart(getHighWaterVolumeWarning(name));
        for(int i = 0; i < consumption.length; i++) {
            barChart.addBar(consumption[i]);
        }
        barChart.setBorder(new CompoundBorder(new LineBorder(Color.decode("#ebf5ff")), new EmptyBorder(10, 10, 10, 10)));

        cardPanel.add(barChart, name);

        if (name.equals("SYSTEM")) {
            ((CardLayout)cardPanel.getLayout()).show(cardPanel, name);
        }
    }

    private int getHighWaterVolumeWarning(String name) {
        switch (name) {
            case "SYSTEM":
                return 2500;
            default:
                return 500;
        }
    }

//    public void saveConsumptionArrayToLocal(String name, int[] consumArray) {
//        if(consumArray == null) {
//            System.out.println("saveConsumptionArrayToLocal NULL");
//        }
//        Collections.reverse(Arrays.asList(consumArray));
//        switch (name) {
//            case "SYSTEM":
//                System.arraycopy(consumArray, 0, sysConsum, 0, consumArray.length);
//                System.out.println("Length: " + sysConsum.length);
//                break;
//            case "NORTH":
//                System.arraycopy(consumArray, 0, northConsum, 0, consumArray.length);
//                break;
//            case "SOUTH":
//                System.arraycopy(consumArray, 0, southConsum, 0, consumArray.length);
//                break;
//            case "EAST":
//                System.arraycopy(consumArray, 0, eastConsum, 0, consumArray.length);
//                break;
//            case "WEST":
//                System.arraycopy(consumArray, 0, westConsum, 0, consumArray.length);
//                break;
//            default:
//                System.out.println("Invalid name input");
//                break;
//        }
//    }

    private void reverseArray(int[] array){
        for(int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
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
