package ui;

import javax.swing.*;
import java.awt.*;

public class TabbedPanel {
    public TabbedPanel() {
        JFrame frame = new JFrame("HummingBee Home Garden Sprinkler System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        System.out.println(width);
        frame.setSize(width * 3 / 4, height * 3 / 4);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Georgia", Font.PLAIN, 20));
        tabbedPane.addTab("Overview", new OverviewPanel());
        tabbedPane.addTab("Status", new StatusPanel());
        tabbedPane.addTab("Configuration", new ConfigPanel());
        tabbedPane.addTab("Water Consumption", new ConsumPanel());
        tabbedPane.addTab("Map", new MapPanel());

        frame.getContentPane().add(tabbedPane);
        frame.setVisible(true);


    }
}