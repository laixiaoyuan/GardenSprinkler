package ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Lexie on 11/17/16.
 */
class OverviewPanel extends JPanel{

    JPanel controlPanel;
    JLabel system;
    JLabel status;
    JButton statusSwitch;
    JLabel temperature;
    JLabel curTemp;
    JComboBox tempUnit;
    JButton higherTemp;
    JButton lowerTemp;
    Font fontBig = new Font("Georgia", Font.BOLD, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);

    public OverviewPanel() {
        super();

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(0, 1));
        createControlPanel();
        controlPanel.setBorder(new CompoundBorder(new LineBorder(Color.decode("#ebf5ff")), new EmptyBorder(10, 10, 10, 10)));
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
        system.setFont(fontSmall);
        status = new JLabel("ON");
        status.setFont(fontSmall);
        status.setForeground(Color.decode("#3e5266"));
        statusSwitch = new JButton("TURN OFF");
        statusSwitch.setFont(fontSmall);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(system);
        panel.add(status);
        panel.add(statusSwitch);

        TitledBorder border = new TitledBorder(new EtchedBorder(), "Status");
        panel.setBorder(border);
        ((javax.swing.border.TitledBorder) panel.getBorder()).setTitleFont(fontBig);

        return panel;
    }

    private JPanel createTemp() {
        temperature = new JLabel("Current Temperature: ");
        temperature.setFont(fontSmall);
        curTemp = new JLabel("70");
        curTemp.setFont(fontSmall);
        curTemp.setForeground(Color.decode("#3e5266"));
        tempUnit = createTempUnit();
        tempUnit.setFont(fontSmall);
        higherTemp = new JButton("+");
        higherTemp.setFont(fontSmall);
        lowerTemp = new JButton("-");
        lowerTemp.setFont(fontSmall);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(temperature);
        panel.add(curTemp);
        panel.add(tempUnit);
        panel.add(higherTemp);
        panel.add(lowerTemp);
        panel.setBorder(new TitledBorder(new EtchedBorder(), "Temperature"));
        ((javax.swing.border.TitledBorder) panel.getBorder()).setTitleFont(fontBig);

        return panel;
    }
    private JComboBox createTempUnit() {
        String[] tempUnit = {"℉", "℃"};
        JComboBox comboBox = new JComboBox(tempUnit);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
    }

}
