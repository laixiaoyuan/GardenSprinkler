package ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by Lexie on 11/17/16.
 */
class StatusPanel extends JPanel {

    private JButton refreshBtn;

    private JPanel masterPaneNorth;
    private JPanel masterPaneSouth;
    private JPanel masterPaneEast;
    private JPanel masterPaneWest;

    Font fontBig = new Font("Georgia", Font.BOLD, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);

    public StatusPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel refreshPanel = new JPanel();
        refreshPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        refreshBtn = new JButton("Refresh");
        refreshPanel.setPreferredSize(refreshBtn.getSize());
        refreshPanel.add(refreshBtn);

        add(refreshPanel);

        masterPaneNorth = createPanel();
        masterPaneEast = createPanel();
        masterPaneSouth = createPanel();
        masterPaneWest = createPanel();

        JScrollPane scrollPaneNorth = new JScrollPane(masterPaneNorth);
        JScrollPane scrollPaneSouth = new JScrollPane(masterPaneSouth);
        JScrollPane scrollPaneEast = new JScrollPane(masterPaneEast);
        JScrollPane scrollPaneWest = new JScrollPane(masterPaneWest);

        add(scrollPaneNorth);
        add(scrollPaneSouth);
        add(scrollPaneEast);
        add(scrollPaneWest);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel northGroupPanel = new JPanel();
        JLabel groupDirection = new JLabel("direction");
        JLabel groupStatus = new JLabel("status");
        JButton groupBtn = new JButton("satusChange");
        northGroupPanel.add(groupDirection);
        northGroupPanel.add(groupStatus);
        northGroupPanel.add(groupBtn);

        panel.add(northGroupPanel);
        return panel;
    }

    public void showGroupStatus(Map<String, Boolean> groupStatusMap) {
        for (Map.Entry<String, Boolean> entry : groupStatusMap.entrySet()) {
            JPanel masterPanel = getPanelBasedOnName(entry.getKey());
            switch (entry.getKey()) {
                case "NORTH":
                    ((JLabel)masterPanel.getComponent(0)).setText("North Group");
                    break;
                case "SOUTH":
                    ((JLabel)masterPanel.getComponent(0)).setText("South Group");
                    break;
                case "EAST":
                    ((JLabel)masterPanel.getComponent(0)).setText("East Group");
                    break;
                case "WEST":
                    ((JLabel)masterPanel.getComponent(0)).setText("West Group");
                    break;
                default:
                    System.out.println("No matching group names");
            }
            ((JLabel)masterPanel.getComponent(1)).setText(entry.getValue() ? "ON" : "OFF");
            ((JButton)masterPanel.getComponent(2)).setText(entry.getValue() ? "DISABLE" : "ENABLE");
        }
    }

    private JPanel getPanelBasedOnName(String name) {
        switch (name) {
            case "NORTH":
                return masterPaneNorth;
            case "SOUTH":
                return masterPaneSouth;
            case "EAST":
                return masterPaneEast;
            case "WEST":
                return masterPaneWest;
            default:
                System.out.println("No matching group names");
                return new JPanel();
        }
    }

    public void showIndividualStatus(String groupName, Map<String, Boolean[]> northEachStatus) {
        JPanel panel = getPanelBasedOnName(groupName);
        for (Map.Entry<String, Boolean[]> entry : northEachStatus.entrySet()) {
            JPanel individualPanel = createIndividualPanel(entry.getKey(), entry.getValue());
            panel.add(individualPanel);
        }
    }

    private JPanel createIndividualPanel(String sprinklerID, Boolean[] sprinklerStatusMap) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JLabel sprinklerName = new JLabel();
        sprinklerName.setText("" + sprinklerID);
        sprinklerName.setFont(fontSmall);

        JLabel sprinklerCurrentlyOn = new JLabel();
        sprinklerCurrentlyOn.setText(sprinklerStatusMap[0] ? "ON" : "NOT ON");
        sprinklerCurrentlyOn.setFont(fontSmall);

        JLabel sprinklerFunctional = new JLabel();
        sprinklerFunctional.setText(sprinklerStatusMap[1] ? "FUNCTIONING OK" : "FUNCTIONING NOT OK");
        sprinklerFunctional.setFont(fontSmall);

        JButton sprinklerStatusChange = new JButton();
        sprinklerStatusChange.setText(sprinklerStatusMap[0] ? "DISABLE" : "ENABLE");
        sprinklerStatusChange.setFont(fontSmall);

        panel.add(sprinklerName);
        panel.add(sprinklerCurrentlyOn);
        panel.add(sprinklerFunctional);
        panel.add(sprinklerStatusChange);
        return panel;
    }

    public void addIndividualStatusListener(String groupName, ActionListener listener) {
        JPanel panel = (JPanel)getPanelBasedOnName(groupName).getComponent(1);

        for (int i = 0; i < panel.getComponentCount(); i++) {
            JPanel individualPanel = (JPanel)panel.getComponent(i);
            ((JButton)individualPanel.getComponent(3)).addActionListener(listener);
        }
    }
    public void addGroupStatusListener(String groupName, ActionListener listener) {
        JPanel panel = (JPanel)getPanelBasedOnName(groupName).getComponent(0);
        ((JButton)panel.getComponent(2)).addActionListener(listener);
    }
}
