package ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Lexie on 11/17/16.
 */
class OverviewPanel extends JPanel{

    JPanel controlPanel;

    JLabel system;
    JTextArea status;
    JButton statusSwitch;

    JLabel temperature;
    JTextField curTemp;
    JComboBox tempUnit;
//    JButton higherTemp;
//    JButton lowerTemp;


    public OverviewPanel() {
        super();
        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1));
        createControlPanel();
        add(controlPanel);

    }

    public void createControlPanel() {
        JPanel systemStatusPanel = createSysStatus();
        JPanel curTempPanel = createTemp();

        controlPanel.add(systemStatusPanel);
        controlPanel.add(curTempPanel);

    }

    public JPanel createSysStatus() {
        system = new JLabel("System Status: ");
        system.setFont(new Font("Georgia", Font.PLAIN, 16));
        status = new JTextArea("ON");
        status.setFont(new Font("Georgia", Font.PLAIN, 16));
        statusSwitch = new JButton("TURN OFF");
        statusSwitch.setFont(new Font("Georgia", Font.PLAIN, 16));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(system);
        panel.add(status);
        panel.add(statusSwitch);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Status"));

        return panel;
    }

    private JPanel createTemp() {
        temperature = new JLabel("Current Temperature: ");
        temperature.setFont(new Font("Georgia", Font.PLAIN, 16));
        curTemp = new JTextField("70", 3);
        curTemp.setFont(new Font("Georgia", Font.PLAIN, 16));
        tempUnit = createTempUnit();
//        higherTemp = new JButton("+");
//        lowerTemp = new JButton("-");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(temperature);
        panel.add(curTemp);
        panel.add(tempUnit);
//        panel.add(higherTemp);
//        panel.add(lowerTemp);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Temperature"));

        return panel;
    }
    private JComboBox createTempUnit() {
        String[] tempUnit = {"℉", "℃"};
        JComboBox comboBox = new JComboBox(tempUnit);
        comboBox.setForeground(Color.BLUE);
        comboBox.setFont(new Font("Georgia", Font.BOLD, 14));
        comboBox.setEditable(false);
        return comboBox;
    }

}
