package ui;

import system.Schedule;
import system.SprinklerSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

/**
 * Created by Lexie on 11/17/16.
 */
public class UI extends JFrame {

    int height;
    int width;
    JTabbedPane tabbedPane;
    Container contentPane;

    OverviewPanel overviewPanel;
    StatusPanel statusPanel;
    ConfigPanel configPanel;
    ConsumPanel consumPanel;
    MapPanel mapPanel;

    String northGroup = "NORTH";
    String southGroup = "SOUTH";
    String eastGroup = "EAST";
    String westGroup = "WEST";

    SprinklerSystem mySystem;


    public UI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.height;
        width = screenSize.width;

        tabbedPane = new JTabbedPane();
        overviewPanel = new OverviewPanel();
        statusPanel = new StatusPanel();
        configPanel = new ConfigPanel();
        consumPanel = new ConsumPanel();
        mapPanel = new MapPanel();

        tabbedPane.setFont(new Font("Georgia", Font.PLAIN, 24));
        tabbedPane.addTab("Overview", overviewPanel);
        tabbedPane.addTab("Status", statusPanel);
        tabbedPane.addTab("Configuration", configPanel);
        tabbedPane.addTab("Water Consumption", consumPanel);
        tabbedPane.addTab("Map", mapPanel);

        contentPane = this.getContentPane();
        contentPane.add(tabbedPane);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HummingBee Home Garden Sprinkler System");

        setVisible(true);

        mySystem = new SprinklerSystem();

    }

    public void initialize() {
        overviewPanel.showSysStatus(mySystem.getSysStatus());
        overviewPanel.showSysTempValue(mySystem.getSysTemp());
        overviewPanel.addSysStatusListener(new SysStatusListener());
        overviewPanel.addSysTempChangeListener(new SysTempListener());

        statusPanel.addRefreshListener(new RefreshStatusListener());
        statusPanel.showGroupStatus(mySystem.getGroupStatus());
        statusPanel.showIndividualStatus(northGroup, mySystem.getSprinklerStatus(northGroup));
        statusPanel.showIndividualStatus(southGroup, mySystem.getSprinklerStatus(southGroup));
        statusPanel.showIndividualStatus(eastGroup, mySystem.getSprinklerStatus(eastGroup));
        statusPanel.showIndividualStatus(westGroup, mySystem.getSprinklerStatus(westGroup));
        statusPanel.addGroupStatusListener(northGroup, new GroupStatusListener());
        statusPanel.addGroupStatusListener(southGroup, new GroupStatusListener());
        statusPanel.addGroupStatusListener(eastGroup, new GroupStatusListener());
        statusPanel.addGroupStatusListener(westGroup, new GroupStatusListener());
        statusPanel.addIndividualStatusListener(northGroup, new IndividualStatusListener());
        statusPanel.addIndividualStatusListener(southGroup, new IndividualStatusListener());
        statusPanel.addIndividualStatusListener(eastGroup, new IndividualStatusListener());
        statusPanel.addIndividualStatusListener(westGroup, new IndividualStatusListener());

        configPanel.createEachScheduleShowPanel(northGroup, mySystem.getSchedule(northGroup));
        configPanel.createEachScheduleShowPanel(southGroup, mySystem.getSchedule(southGroup));
        configPanel.createEachScheduleShowPanel(eastGroup, mySystem.getSchedule(eastGroup));
        configPanel.createEachScheduleShowPanel(westGroup, mySystem.getSchedule(westGroup));
        configPanel.addAddConfigListener(new AddConfigListener());
        // add action listener


        consumPanel.createBarChartByGroup("SYSTEM", mySystem.getSysWCData());
        consumPanel.createBarChartByGroup(northGroup, mySystem.getGroupWCData(northGroup));
        consumPanel.createBarChartByGroup(southGroup, mySystem.getGroupWCData(southGroup));
        consumPanel.createBarChartByGroup(eastGroup, mySystem.getGroupWCData(eastGroup));
        consumPanel.createBarChartByGroup(westGroup, mySystem.getGroupWCData(westGroup));
        // add action listener

    }

    class SysStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String status = btn.getText();
            if (status.equals("TURN OFF")) {
                ((JLabel) btn.getParent().getComponent(1)).setText("OFF");
                btn.setText("TURN ON");
                mySystem.setSysStatus(false);
            } else {
                ((JLabel) btn.getParent().getComponent(1)).setText("ON");
                btn.setText("TURN OFF");
                mySystem.setSysStatus(true);
            }
        }
    }

    class SysTempListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            JLabel tempDisplay = (JLabel) btn.getParent().getComponent(1);
            int curTemp = Integer.valueOf(tempDisplay.getText());
            String btnString = btn.getText();
            if (btnString.equals("+")) {
                curTemp++;
            } else {
                curTemp--;
            }
            tempDisplay.setText("" + curTemp);
            mySystem.setCurrSysTemp(curTemp);
        }
    }

    class RefreshStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
//            int size = statusPanel.getComponentCount();
//            System.out.println("Size: " + size);
//            for (int i = size - 1; i >= 0; i--) {
//                System.out.println("size: " + statusPanel.getComponentCount());
//                statusPanel.remove(statusPanel.getComponent(0));
//            }
            JPanel parent = (JPanel)statusPanel.getParent();
            parent.revalidate();
            parent.repaint();
////            resetStatusPanel();
            statusPanel = resetStatusPanel();
            statusPanel.showGroupStatus(mySystem.getGroupStatus());
            statusPanel.showIndividualStatus(northGroup, mySystem.getSprinklerStatus(northGroup));
            statusPanel.showIndividualStatus(southGroup, mySystem.getSprinklerStatus(southGroup));
            statusPanel.showIndividualStatus(eastGroup, mySystem.getSprinklerStatus(eastGroup));
            statusPanel.showIndividualStatus(westGroup, mySystem.getSprinklerStatus(westGroup));
            statusPanel.revalidate();
            statusPanel.repaint();

        }
    }

    private StatusPanel resetStatusPanel() {
        StatusPanel newStatusPanel = new StatusPanel();
        return newStatusPanel;
    }

    class GroupStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String status = btn.getText();
            String groupName = btn.getName();
            if (status.equals("ENABLE")) {
                ((JLabel) btn.getParent().getComponent(1)).setText("ON");
                btn.setText("DISABLE");
                mySystem.setGroupStatus(groupName, true);
            } else {
                ((JLabel) btn.getParent().getComponent(1)).setText("NOT ON");
                btn.setText("ENABLE");
                mySystem.setGroupStatus(groupName, false);
            }
        }
    }

    class IndividualStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            String status = btn.getText();
            String sprinklerID = btn.getName();
            if (status.equals("ENABLE")) {
                ((JLabel) btn.getParent().getComponent(1)).setText("ON");
                btn.setText("DISABLE");
                mySystem.setSprinklerStatus(sprinklerID, true);
            } else {
                ((JLabel) btn.getParent().getComponent(1)).setText("NOT ON");
                btn.setText("ENABLE");
                mySystem.setSprinklerStatus(sprinklerID, false);
            }
        }
    }

    class AddConfigListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            JPanel addPanel = (JPanel)btn.getParent();
            String groupName = ((JComboBox)addPanel.getComponent(1)).getSelectedItem().toString();
            int day = transferScheduleDayFromStringToInt(((JComboBox)addPanel.getComponent(4)).getSelectedItem().toString());
            int startHour = Integer.parseInt(((JComboBox)addPanel.getComponent(7)).getSelectedItem().toString());
            int startMin = Integer.parseInt(((JComboBox)addPanel.getComponent(9)).getSelectedItem().toString());
            int endHour = Integer.parseInt(((JComboBox)addPanel.getComponent(11)).getSelectedItem().toString());
            int endMin = Integer.parseInt(((JComboBox)addPanel.getComponent(13)).getSelectedItem().toString());

            mySystem.addSchedule(groupName, day, startHour, startMin, endHour, endMin);
            JOptionPane.showMessageDialog(null, "New schedule added");
        }
    }

    private Integer transferScheduleDayFromStringToInt (String day) {
        switch (day) {
            case "Sunday":
                return 1;
            case "Monday":
                return 2;
            case "Tuesday":
                return 3;
            case "Wednesday":
                return 4;
            case "Thursday":
                return 5;
            case "Friday":
                return 6;
            case "Saturday":
                return 7;
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        new UI().initialize();
    }


}
