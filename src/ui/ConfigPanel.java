package ui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by Lexie on 11/17/16.
 */
class ConfigPanel extends JPanel {

    private JPanel masterPanel;
    private JPanel northGroupPanel1;
    private JPanel southGroupPanel1;
    private JPanel eastGroupPanel1;
    private JPanel westGroupPanel1;

    public ConfigPanel() {
        super();
        northGroupPanel1 = createconfigPanel("Group North");
        southGroupPanel1 = createconfigPanel("Group South");
        eastGroupPanel1 = createconfigPanel("Group East ");
        westGroupPanel1 = createconfigPanel("Group West ");

        masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(0, 1));
        masterPanel.add(northGroupPanel1);
        masterPanel.add(southGroupPanel1);
        masterPanel.add(eastGroupPanel1);
        masterPanel.add(westGroupPanel1);

        add(masterPanel);
    }

    private JPanel createconfigPanel(String groupName) {
        JPanel panel1 = createPanel1();
        JPanel panel2 = createPanel2();
        JPanel group = new JPanel(new GridLayout(0, 1));
        group.add(panel1);
        group.add(panel2);
        group.setBorder(new TitledBorder(new EtchedBorder(), groupName));
        return group;
    }

    private JPanel createPanel1() {
        Font font = new Font("Georgia", Font.BOLD, 14);

        JLabel dateHead = new JLabel("Day");
        JComboBox dateCombo = createDate();
        JLabel timeHead = new JLabel("Time");
        JComboBox startHourCombo = createHour();
        JLabel timeLabel1 = new JLabel(":");
        JComboBox startMinCombo = createMin();
        JLabel to = new JLabel("to");
        JComboBox endHourCombo = createHour();
        JLabel timeLabel2 = new JLabel(":");
        JComboBox endMinCombo = createMin();

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(dateHead);
        panel.add(dateCombo);
        panel.add(Box.createRigidArea(new Dimension(5, 0)));
        panel.add(timeHead);
        panel.add(startHourCombo);
        panel.add(timeLabel1);
        panel.add(startMinCombo);
        panel.add(to);
        panel.add(endHourCombo);
        panel.add(timeLabel2);
        panel.add(endMinCombo);

        return panel;
    }

    private JPanel createPanel2() {
        Font font = new Font("Georgia", Font.BOLD, 14);

        JLabel tempHead = new JLabel("Temperature limit");
        JLabel upperHead = new JLabel("Upper:");
        JTextField upperLimit = new JTextField(3);
        upperLimit.setFont(font);
        upperLimit.setForeground(Color.BLUE);
        JComboBox tempCombo1 = createTempUnit();
        JLabel lowerHead = new JLabel("Lower: ");
        JTextField lowerLimit = new JTextField(3);
        lowerLimit.setFont(font);
        lowerLimit.setForeground(Color.BLUE);
        JComboBox tempCombo2 = createTempUnit();

        JButton save = new JButton("Save");
        save.setFont(font);

        JPanel newLinePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newLinePanel.add(tempHead);
        newLinePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        newLinePanel.add(upperHead);
        newLinePanel.add(upperLimit);
        newLinePanel.add(tempCombo1);
        newLinePanel.add(lowerHead);
        newLinePanel.add(lowerLimit);
        newLinePanel.add(tempCombo2);
        newLinePanel.add(Box.createRigidArea(new Dimension(5, 0)));
        newLinePanel.add(save);
        return newLinePanel;
    }

    private JComboBox createDate() {
        String[] date = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        JComboBox comboBox = new JComboBox(date);
        comboBox.setForeground(Color.BLUE);
        comboBox.setFont(new Font("Georgia", Font.BOLD, 14));
        comboBox.setEditable(false);
        return comboBox;
    }
    private JComboBox createHour() {
        String[] hour = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        JComboBox comboBox = new JComboBox(hour);
        comboBox.setForeground(Color.BLUE);
        comboBox.setFont(new Font("Georgia", Font.BOLD, 14));
        comboBox.setEditable(false);
        return comboBox;
    }
    private JComboBox createMin() {
        String[] minute = {"00", "15", "30", "45"};
        JComboBox comboBox = new JComboBox(minute);
        comboBox.setForeground(Color.BLUE);
        comboBox.setFont(new Font("Georgia", Font.BOLD, 14));
        comboBox.setEditable(false);
        return comboBox;

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
