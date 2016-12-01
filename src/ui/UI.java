package ui;

import javax.swing.*;
import java.awt.*;

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

    public UI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.height / 2;
        width = screenSize.width / 2;

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

    }

    public JPanel getOverviewPanel() {
        return overviewPanel;
    }

    public JPanel getStatusPanel() {
        return statusPanel;
    }

    public JPanel getConfigPanel() {
        return configPanel;
    }

    public JPanel getConsumPanel() {
        return consumPanel;
    }


}
