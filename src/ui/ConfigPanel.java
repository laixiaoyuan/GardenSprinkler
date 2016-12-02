package ui;

import system.Schedule;
import system.SprinklerGroup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Lexie on 11/17/16.
 */
class ConfigPanel extends JPanel {

    private JPanel sysTempLimitPanel;
    private JPanel northGroupPanel;
    private JPanel southGroupPanel;
    private JPanel eastGroupPanel;
    private JPanel westGroupPanel;
    private JPanel addSchedulePanel;
    private JPanel notePanel;

    Font fontBig = new Font("Georgia", Font.BOLD, 22);
    Font fontSmall = new Font("Georgia", Font.PLAIN, 18);

    public ConfigPanel() {
        super();

        sysTempLimitPanel = createSysTempLimitPanel();
        northGroupPanel = createconfigPanel("Group North");
        southGroupPanel = createconfigPanel("Group South");
        eastGroupPanel = createconfigPanel("Group East ");
        westGroupPanel = createconfigPanel("Group West ");
        notePanel = createNotePanel();
        addSchedulePanel = createAddSchedulePanel();

        JScrollPane northScroll = new JScrollPane(northGroupPanel);
        JScrollPane southScroll = new JScrollPane(southGroupPanel);
        JScrollPane eastScroll = new JScrollPane(eastGroupPanel);
        JScrollPane westScroll = new JScrollPane(westGroupPanel);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(sysTempLimitPanel);
        add(northScroll);
        add(southScroll);
        add(eastScroll);
        add(westScroll);
        add(addSchedulePanel);
        add(notePanel);
    }

    private JPanel createSysTempLimitPanel() {

        JLabel tempHead = new JLabel("System Temperature limit");
        tempHead.setFont(fontSmall);
        JLabel upperHead = new JLabel("Upper");
        upperHead.setFont(fontSmall);
        JTextField upperLimit = new JTextField(3);
        upperLimit.setFont(fontSmall);
        upperLimit.setForeground(Color.decode("#3e5266"));
        JLabel tempUnit1 = new JLabel("℉");
        JLabel lowerHead = new JLabel("Lower");
        lowerHead.setFont(fontSmall);
        JTextField lowerLimit = new JTextField(3);
        lowerLimit.setFont(fontSmall);
        lowerLimit.setForeground(Color.decode("#3e5266"));
        JLabel tempUnit2 = new JLabel("℉");

        JButton save = new JButton("Update");
        save.setFont(fontSmall);
        save.setForeground(Color.decode("#3e5266"));

        JPanel newLinePanel = new JPanel();
        newLinePanel.add(tempHead);
        newLinePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        newLinePanel.add(upperHead);
        newLinePanel.add(upperLimit);
        newLinePanel.add(tempUnit1);
        newLinePanel.add(lowerHead);
        newLinePanel.add(lowerLimit);
        newLinePanel.add(tempUnit2);
        newLinePanel.add(Box.createRigidArea(new Dimension(10, 0)));

        newLinePanel.add(save);
        return newLinePanel;
    }

    private JPanel createconfigPanel(String groupName) {
        JPanel group = new JPanel();
        group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
        Border border = new TitledBorder(new EtchedBorder(), groupName);
        group.setBorder(border);
        ((javax.swing.border.TitledBorder) group.getBorder()).setTitleFont(fontBig);

        JPanel buttonPanel = new JPanel();
        JLabel volumeHeader = new JLabel("set volume*");
        volumeHeader.setFont(fontSmall);
        JComboBox volumeComboBox = createVolume();

        JButton saveBtn = new JButton("Save Schedule");
        saveBtn.setFont(fontSmall);
        saveBtn.setForeground(Color.decode("#3e5266"));
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setFont(fontSmall);
        refreshBtn.setForeground(Color.decode("#3e5266"));

        buttonPanel.add(volumeHeader);
        buttonPanel.add(volumeComboBox);
        buttonPanel.add(saveBtn);
        buttonPanel.add(refreshBtn);

        group.add(buttonPanel);

        return group;
    }

    // get a list of Schedule by groupName and show on the UI
    public void createEachScheduleShowPanel(String groupName, List<Schedule> scheduleList) {
        JPanel parentPanel = getPanelBasedOnName(groupName);
        for (int i = 0; i < scheduleList.size(); i++) {
            JLabel scheduleID = new JLabel();
            scheduleID.setText(scheduleList.get(i).getID());
            scheduleID.setVisible(false);

            String day = transferScheduleDayFromIntToString(scheduleList.get(i).getDay());
            int startHour = scheduleList.get(i).getStartHour();
            int startMin = scheduleList.get(i).getStartMin();
            int endHour = scheduleList.get(i).getEndHour();
            int endMin = scheduleList.get(i).getEndMin();

            JTextArea scheduleDisplay = new JTextArea();
            scheduleDisplay.setText(day + " " + startHour + ":" + startMin + " to " + endHour + ":" + endMin);
            scheduleDisplay.setFont(fontSmall);
            scheduleDisplay.setEditable(false);

            JPanel individualSchedulePanel = new JPanel();
            individualSchedulePanel.add(scheduleID);
            individualSchedulePanel.add(scheduleDisplay);
            JButton deleteBtn = new JButton("Delete");
            deleteBtn.setForeground(Color.decode("#3e5266"));
            individualSchedulePanel.add(deleteBtn);

            parentPanel.add(individualSchedulePanel);
        }
    }





    public String transferScheduleDayFromIntToString(int day) {
        switch(day) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                System.out.println("Schedule day is not valid");
                return null;
        }
    }



    private JPanel createAddSchedulePanel() {

        JLabel groupNameHead = new JLabel("Group");
        groupNameHead.setFont(fontSmall);
        JComboBox groupNameCombo = createGroupNameCombo();

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
        JButton addBtn = new JButton("Add");
        addBtn.setFont(fontSmall);
        addBtn.setForeground(Color.decode("#3e5266"));

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(groupNameHead);
        panel.add(groupNameCombo);
        panel.add(Box.createRigidArea(new Dimension(10, 0)));
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
        panel.add(addBtn);

        Border border = new TitledBorder(new EtchedBorder(), "Add Schedule");
        panel.setBorder(border);
        ((javax.swing.border.TitledBorder) panel.getBorder()).setTitleFont(fontSmall);

        return panel;
    }

    private JPanel createNotePanel() {
        JLabel note = new JLabel("*Volume is set by gallon per minute per sprinkler");
        note.setFont(new Font("Georgia", Font.PLAIN, 14));
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(note);
        return panel;
    }

    public JPanel getPanelBasedOnName(String name) {
        switch (name) {
            case "SYSTEMTEMP":
                return sysTempLimitPanel;
            case "NORTH":
                return northGroupPanel;
            case "SOUTH":
                return southGroupPanel;
            case "EAST":
                return eastGroupPanel;
            case "WEST":
                return westGroupPanel;
            case "ADD":
                return addSchedulePanel;
            default:
                System.out.println("No matching group names");
                return new JPanel();
        }
    }
    private JComboBox createGroupNameCombo() {
        String[] groupName = {"NORTH", "SOUTH", "EAST", "WEST"};
        JComboBox comboBox = new JComboBox(groupName);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
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

    private JComboBox createVolume() {
        String[] volume = {"10", "20", "30", "40", "50", "60"};
        JComboBox comboBox = new JComboBox(volume);
        comboBox.setForeground(Color.decode("#3e5266"));
        comboBox.setFont(fontSmall);
        comboBox.setEditable(false);
        return comboBox;
    }

    public void addUpdateTempConfigListener(String panelName, ActionListener listener) {
        ((JButton)getPanelBasedOnName(panelName).getComponent(9)).addActionListener(listener);
    }

    public void addAddConfigListener(ActionListener listener) {
        ((JButton)getPanelBasedOnName("ADD").getComponent(15)).addActionListener(listener);

    }

    public void addSaveScheduleListener(String groupName, ActionListener listener) {
        JPanel individualPanel = (JPanel)getPanelBasedOnName(groupName).getComponent(0);
        ((JButton)individualPanel.getComponent(2)).addActionListener(listener);
    }

    public void addRefreshScheduleListener(String groupName, ActionListener listener) {
        JPanel individualPanel = (JPanel)getPanelBasedOnName(groupName).getComponent(0);
        ((JButton)individualPanel.getComponent(3)).addActionListener(listener);
    }

    public void addDeleteConfigListener(String groupName, ActionListener listener) {
        JPanel panel = getPanelBasedOnName(groupName);
        for (int i = 1; i < panel.getComponentCount(); i++) {
            JPanel individualPanel = (JPanel)panel.getComponent(i);
            ((JButton)individualPanel.getComponent(2)).addActionListener(listener);
        }
    }

}
