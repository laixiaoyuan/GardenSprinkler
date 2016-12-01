package ui;

import system.SprinklerSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lexie on 11/17/16.
 */
public class UI extends JFrame{

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
        // add action listener

        statusPanel.showGroupStatus(mySystem.getGroupStatus());
        statusPanel.showIndividualStatus(northGroup, mySystem.getSprinklerStatus(northGroup));
        statusPanel.showIndividualStatus(southGroup, mySystem.getSprinklerStatus(southGroup));
        statusPanel.showIndividualStatus(eastGroup, mySystem.getSprinklerStatus(eastGroup));
        statusPanel.showIndividualStatus(westGroup, mySystem.getSprinklerStatus(westGroup));
        // add action listener

        configPanel.createEachScheduleShowPanel(northGroup, mySystem.getSchedule(northGroup));
        configPanel.createEachScheduleShowPanel(southGroup, mySystem.getSchedule(southGroup));
        configPanel.createEachScheduleShowPanel(eastGroup, mySystem.getSchedule(eastGroup));
        configPanel.createEachScheduleShowPanel(westGroup, mySystem.getSchedule(westGroup));
        // add action listener


        consumPanel.createBarChartByGroup("SYSTEM", mySystem.getSysWCData());
        consumPanel.createBarChartByGroup(northGroup, mySystem.getGroupWCData(northGroup));
        consumPanel.createBarChartByGroup(southGroup, mySystem.getGroupWCData(southGroup));
        consumPanel.createBarChartByGroup(eastGroup, mySystem.getGroupWCData(eastGroup));
        consumPanel.createBarChartByGroup(westGroup, mySystem.getGroupWCData(westGroup));
        // add action listener

    }

//    class AddConfigListener implements ActionListener {
//
//    }

//    public JPanel getOverviewPanel() {
//        return overviewPanel;
//    }
//
//    public JPanel getStatusPanel() {
//        return statusPanel;
//    }
//
//    public JPanel getConfigPanel() {
//        return configPanel;
//    }
//
//    public JPanel getConsumPanel() {
//        return consumPanel;
//    }

    class SysStatusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton)e.getSource();
            String status = btn.getText();
            if (status.equals("TURN OFF")) {
                ((JLabel)btn.getParent().getComponent(1)).setText("OFF");
                btn.setText("TURN ON");
                mySystem.setSysStatus(false);
            }
            else {
                ((JLabel)btn.getParent().getComponent(1)).setText("ON");
                btn.setText("TURN OFF");
                mySystem.setSysStatus(true);
            }
        }
    }

    class SysTempListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton)e.getSource();
            JLabel tempDisplay = (JLabel)btn.getParent().getComponent(1);
            int curTemp = Integer.valueOf(tempDisplay.getText());
            String btnString = btn.getText();
            if (btnString.equals("+")) {
                curTemp++;
            }
            else {
                curTemp--;
            }
            tempDisplay.setText("" + curTemp);
            mySystem.setCurrSysTemp(curTemp);
        }
    }




    public static void main(String[] args) {
        new UI().initialize();
    }

}
