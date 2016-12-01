package ui;

import system.SprinklerGroup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Lexie on 11/17/16.
 */
class ConfigPanel extends JPanel {

    private JPanel masterPanel;
    private JPanel northGroupPanel1;
    private JPanel southGroupPanel1;
    private JPanel eastGroupPanel1;
    private JPanel westGroupPanel1;
    private JPanel notePanel;
    Font fontBig = new Font("Georgia", Font.BOLD, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);

    public ConfigPanel() {
        super();
        northGroupPanel1 = createconfigPanel("Group North");
        southGroupPanel1 = createconfigPanel("Group South");
        eastGroupPanel1 = createconfigPanel("Group East ");
        westGroupPanel1 = createconfigPanel("Group West ");
        notePanel = createNotePanel();

        masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayout(0, 1));
        masterPanel.add(northGroupPanel1);
        masterPanel.add(southGroupPanel1);
        masterPanel.add(eastGroupPanel1);
        masterPanel.add(westGroupPanel1);
        masterPanel.add(notePanel);

        add(masterPanel);
    }

    private JPanel createconfigPanel(String groupName) {
        JPanel panel1 = createPanel1();
        JPanel panel2 = createPanel2();
        JPanel group = new JPanel(new GridLayout(0, 1));
        group.add(panel1);
        group.add(panel2);
        Border border = new TitledBorder(new EtchedBorder(), groupName);
        group.setBorder(border);
        ((javax.swing.border.TitledBorder) group.getBorder()).setTitleFont(fontBig);
        return group;
    }

    private JPanel createPanel1() {

        JLabel dateHead = new JLabel("Day");
        dateHead.setFont(fontSmall);
        JComboBox dateCombo = createDate();
        JLabel timeHead = new JLabel("Time");
        timeHead.setFont(fontSmall);
        JComboBox startHourCombo = createHour();
        JLabel timeLabel1 = new JLabel(":");
        timeLabel1.setFont(fontSmall);
        JComboBox startMinCombo = createMin();
        JLabel to = new JLabel("to");
        to.setFont(fontSmall);
        JComboBox endHourCombo = createHour();
        JLabel timeLabel2 = new JLabel(":");
        timeLabel2.setFont(fontSmall);
        JComboBox endMinCombo = createMin();

        JLabel volumeHeader = new JLabel("Volume*");
        volumeHeader.setFont(fontSmall);
        JComboBox volume = createVolume();

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(dateHead);
        panel.add(dateCombo);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(timeHead);
        panel.add(startHourCombo);
        panel.add(timeLabel1);
        panel.add(startMinCombo);
        panel.add(to);
        panel.add(endHourCombo);
        panel.add(timeLabel2);
        panel.add(endMinCombo);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
        panel.add(volumeHeader);
        panel.add(volume);
        return panel;
    }

    private JPanel createPanel2() {

        JLabel tempHead = new JLabel("Temperature limit -");
        tempHead.setFont(fontSmall);
        JLabel upperHead = new JLabel("Upper");
        upperHead.setFont(fontSmall);
        JTextField upperLimit = new JTextField(3);
        upperLimit.setFont(fontSmall);
        upperLimit.setForeground(Color.decode("#3e5266"));
        JComboBox tempCombo1 = createTempUnit();
        JLabel lowerHead = new JLabel("Lower");
        lowerHead.setFont(fontSmall);
        JTextField lowerLimit = new JTextField(3);
        lowerLimit.setFont(fontSmall);
        lowerLimit.setForeground(Color.decode("#3e5266"));
        JComboBox tempCombo2 = createTempUnit();



        JButton save = new JButton("Save");
        save.setFont(fontSmall);
        save.setForeground(Color.decode("#3e5266"));

        JPanel newLinePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newLinePanel.add(tempHead);
        newLinePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        newLinePanel.add(upperHead);
        newLinePanel.add(upperLimit);
        newLinePanel.add(tempCombo1);
        newLinePanel.add(lowerHead);
        newLinePanel.add(lowerLimit);
        newLinePanel.add(tempCombo2);
        newLinePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        newLinePanel.add(save);
        return newLinePanel;
    }

    private JPanel createNotePanel() {
        JLabel note = new JLabel("*Volume is set by gallon per minute per sprinkler");
        note.setFont(new Font("Georgia", Font.PLAIN, 14));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(note);
        return panel;
    }

    private JComboBox createDate() {
        String[] date = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        JComboBox comboBox = new JComboBox(date);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
    }
    private JComboBox createHour() {
        String[] hour = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        JComboBox comboBox = new JComboBox(hour);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
    }
    private JComboBox createMin() {
        String[] minute = {"00", "15", "30", "45"};
        JComboBox comboBox = new JComboBox(minute);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;

    }
    private JComboBox createTempUnit() {
        String[] tempUnit = {"℉", "℃"};
        JComboBox comboBox = new JComboBox(tempUnit);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
    }

    private JComboBox createVolume() {
        String[] volume = {"10", "20", "30", "40", "50", "60"};
        JComboBox comboBox = new JComboBox(volume);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
    }

    public void getConfiguration(SprinklerGroup goupName) {
        // get weekly schedule
    }

    public void addSelectedSpinklerListener(ActionListener listener) {

    }

    public void addSaveConfiglistener(ActionListener listener) {

    }
}
