package ui;

import system.Sprinkler;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Lexie on 11/17/16.
 */
class StatusPanel extends JPanel {

    private JTree tree;
    private JLabel sprinklerName;
    private JLabel status;
    private JLabel function;

    private JPanel masterPaneNorth;
    private JLabel groupNorth;
    private JLabel northStatus;
    private JButton northGroupBtn;

    private JPanel masterPaneSouth;
    private JLabel groupSouth;
    private JLabel southStatus;
    private JButton southGroupBtn;

    private JPanel masterPaneEast;
    private JLabel groupEast;
    private JLabel eastStatus;
    private JButton eastGroupBtn;

    private JPanel masterPaneWest;
    private JLabel groupWest;
    private JLabel westStatus;
    private JButton westGroupBtn;

    Font fontBig = new Font("Georgia", Font.BOLD, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);

    public StatusPanel() {
        super();
        setLayout(new GridLayout(0, 1));

        createNorthPanel();
        createSouthPanel();
        createEastPanel();
        createWestPanel();

        JScrollPane scrollPaneNorth = new JScrollPane(masterPaneNorth);
        JScrollPane scrollPaneSouth = new JScrollPane(masterPaneSouth);
        JScrollPane scrollPaneEast = new JScrollPane(masterPaneEast);
        JScrollPane scrollPaneWest = new JScrollPane(masterPaneWest);

        add(scrollPaneNorth);
        add(scrollPaneSouth);
        add(scrollPaneEast);
        add(scrollPaneWest);
    }

    private void createNorthPanel() {
        masterPaneNorth = new JPanel();
        masterPaneNorth.setLayout(new BoxLayout(masterPaneNorth, BoxLayout.Y_AXIS));

        JPanel northGroupPanel = new JPanel();
        groupNorth = new JLabel();
        northStatus = new JLabel();
        northGroupBtn = new JButton();
        northGroupPanel.add(groupNorth);
        northGroupPanel.add(northStatus);
        northGroupPanel.add(northGroupBtn);

        masterPaneNorth.add(northGroupPanel);
    }

    private void createSouthPanel() {
        masterPaneSouth = new JPanel();
        masterPaneSouth.setLayout(new BoxLayout(masterPaneSouth, BoxLayout.Y_AXIS));

        JPanel southGroupPanel = new JPanel();
        groupSouth = new JLabel();
        southStatus = new JLabel();
        southGroupBtn = new JButton();
        southGroupPanel.add(groupSouth);
        southGroupPanel.add(southStatus);
        southGroupPanel.add(southGroupBtn);

        masterPaneSouth.add(southGroupPanel);
    }

    private void createEastPanel() {
        masterPaneEast = new JPanel();
        masterPaneEast.setLayout(new BoxLayout(masterPaneEast, BoxLayout.Y_AXIS));

        JPanel eastGroupPanel = new JPanel();
        groupEast = new JLabel();
        eastStatus = new JLabel();
        eastGroupBtn = new JButton();
        eastGroupPanel.add(groupEast);
        eastGroupPanel.add(eastStatus);
        eastGroupPanel.add(eastGroupBtn);

        masterPaneEast.add(eastGroupPanel);
    }

    private void createWestPanel() {
        masterPaneWest = new JPanel();
        masterPaneWest.setLayout(new BoxLayout(masterPaneWest, BoxLayout.Y_AXIS));

        JPanel westGroupPanel = new JPanel();
        groupWest = new JLabel();
        westStatus = new JLabel();
        westGroupBtn = new JButton();
        westGroupPanel.add(groupWest);
        westGroupPanel.add(eastStatus);
        westGroupPanel.add(eastGroupBtn);

        masterPaneWest.add(westGroupPanel);
    }

    public void showGroupStatus(Map<String, Boolean> groupStatusMap) {
        for (Map.Entry<String, Boolean> entry : groupStatusMap.entrySet()) {
            switch (entry.getKey()) {
                case "NORTH":
                    groupNorth.setText("North Group");
                    northStatus.setText(entry.getValue() ? "ON" : "OFF");
                    northGroupBtn.setText(entry.getValue() ? "DISABLE" : "ENABLE");
                    break;
                case "SOUTH":
                    groupSouth.setText("South Group");
                    southStatus.setText(entry.getValue() ? "ON" : "OFF");
                    southGroupBtn.setText(entry.getValue() ? "DISABLE" : "ENABLE");
                    break;
                case "EAST":
                    groupEast.setText("East Group");
                    eastStatus.setText(entry.getValue() ? "ON" : "OFF");
                    eastGroupBtn.setText(entry.getValue() ? "DISABLE" : "ENABLE");
                    break;
                case "WEST":
                    groupWest.setText("West Group");
                    westStatus.setText(entry.getValue() ? "ON" : "OFF");
                    westGroupBtn.setText(entry.getValue() ? "DISABLE" : "ENABLE");
                    break;
                default:
                    System.out.println("No matching group names");
            }
        }
    }

    public void showNorthIndividualStatus(Map<String, Boolean[]> northEachStatus) {
        for (Map.Entry<String, Boolean[]> entry : northEachStatus.entrySet()) {
            JPanel individualPanel = createIndividualPanel(entry.getKey(), entry.getValue());
            masterPaneNorth.add(individualPanel);
        }
    }

    public void showSouthIndividualStatus(Map<String, Boolean[]> southEachStatus) {
        for (Map.Entry<String, Boolean[]> entry : southEachStatus.entrySet()) {
            JPanel individualPanel = createIndividualPanel(entry.getKey(), entry.getValue());
            masterPaneSouth.add(individualPanel);
        }
    }

    public void showEastIndividualStatus(Map<String, Boolean[]> eastEachStatus) {
        for (Map.Entry<String, Boolean[]> entry : eastEachStatus.entrySet()) {
            JPanel individualPanel = createIndividualPanel(entry.getKey(), entry.getValue());
            masterPaneEast.add(individualPanel);
        }
    }

    public void showWestIndividualStatus(Map<String, Boolean[]> westEachStatus) {
        for (Map.Entry<String, Boolean[]> entry : westEachStatus.entrySet()) {
            JPanel individualPanel = createIndividualPanel(entry.getKey(), entry.getValue());
            masterPaneWest.add(individualPanel);
        }
    }

    private JPanel createIndividualPanel(String sprinklerID, Boolean[] sprinklerStatusMap) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));

        JLabel sprinklerName = new JLabel("" + sprinklerID);
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

//    private JScrollPane showSprinklerPanel() {
//        tree = createTree();
//        Icon imageIcon = new ImageIcon("ui/sprinkler.png");
//        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
//        renderer.setLeafIcon(imageIcon);
//        tree.setCellRenderer(renderer);
//
//        JScrollPane scrollPane = new JScrollPane(tree);
//        scrollPane.setPreferredSize(new Dimension(200, 200));
//        return scrollPane;
//    }
//
//    private JTree createTree() {
//        Font font = new Font("Georgia", Font.BOLD, 18);
//
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("All");
//
//        DefaultMutableTreeNode southGroup = new DefaultMutableTreeNode("South");
//        southGroup.add(new DefaultMutableTreeNode("1S"));
//        southGroup.add(new DefaultMutableTreeNode("2S"));
//        southGroup.add(new DefaultMutableTreeNode("3S"));
//        southGroup.add(new DefaultMutableTreeNode("4S"));
//
//        DefaultMutableTreeNode northGroup = new DefaultMutableTreeNode("North");
//        northGroup.add(new DefaultMutableTreeNode("1N"));
//        northGroup.add(new DefaultMutableTreeNode("2N"));
//        northGroup.add(new DefaultMutableTreeNode("3N"));
//        northGroup.add(new DefaultMutableTreeNode("4N"));
//
//        DefaultMutableTreeNode eastGroup = new DefaultMutableTreeNode("East");
//        eastGroup.add(new DefaultMutableTreeNode("1E"));
//        eastGroup.add(new DefaultMutableTreeNode("2E"));
//        eastGroup.add(new DefaultMutableTreeNode("3E"));
//        eastGroup.add(new DefaultMutableTreeNode("4E"));
//
//        DefaultMutableTreeNode westGroup = new DefaultMutableTreeNode("West");
//        westGroup.add(new DefaultMutableTreeNode("1W"));
//        westGroup.add(new DefaultMutableTreeNode("2W"));
//        westGroup.add(new DefaultMutableTreeNode("3W"));
//        westGroup.add(new DefaultMutableTreeNode("4W"));
//
//        root.add(southGroup);
//        root.add(northGroup);
//        root.add(eastGroup);
//        root.add(westGroup);
//        JTree jTree = new JTree(root);
//        jTree.setShowsRootHandles(true);
//        jTree.setFont(font);
//        return jTree;
//    }
//
//    private JPanel showStatusPanel() {
//        Font font = new Font("Georgia", Font.BOLD, 20);
//
//        JPanel panel1 = new JPanel();
//        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
//        JLabel head = new JLabel("Sprinkler: ");
//        head.setFont(font);
//        sprinklerName = new JLabel("1N");
//        sprinklerName.setFont(font);
//        sprinklerName.setForeground(Color.decode("#3e5266"));
//        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
//        panel1.add(head);
//        panel1.add(sprinklerName);
//
//        JPanel panel2 = new JPanel();
//        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
//        JLabel statusHead = new JLabel("Status: ");
//        statusHead.setFont(font);
//        status = new JLabel("ON");
//        status.setFont(font);
//        status.setForeground(Color.decode("#3e5266"));
//        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
//        panel2.add(statusHead);
//        panel2.add(status);
//
//        JPanel panel3 = new JPanel();
//        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
//        JLabel funcHead = new JLabel("Functional: ");
//        funcHead.setFont(font);
//        function = new JLabel("Functional");
//        function.setFont(font);
//        function.setForeground(Color.decode("#3e5266"));
//        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
//        panel3.add(funcHead);
//        panel3.add(function);
//
//        JPanel panel4 = new JPanel();
//        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
//        JButton enableButton = new JButton("ENABLE");
//        enableButton.setFont(font);
//        JButton disableButton = new JButton("DISABLE");
//        disableButton.setFont(font);
//        panel4.add(enableButton);
//        panel4.add(disableButton);
//
//        JPanel masterPanel = new JPanel();
//        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
//        masterPanel.add(panel1);
//        masterPanel.add(panel2);
//        masterPanel.add(panel3);
//        masterPanel.add(panel4);
//
//        return masterPanel;
//    }

    public void setSprinklerList(List<Sprinkler> sprinklerList) {

    }

//    public String getSprinklerID() {
//
//    }

    public void setSprinklerStatus(boolean sprinklerStatus) {

    }

    public void addSprinklerStatusListener(ActionListener listener) {

    }
}
