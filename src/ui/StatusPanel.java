package ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by Lexie on 11/17/16.
 */
class StatusPanel extends JPanel {

    private JTree tree;
    private JPanel panel;
    private JLabel sprinklerName;
    private JLabel status;
    private JLabel function;



    public StatusPanel() {
        super();
        JScrollPane leftPanel = showSprinklerPanel();
        JPanel rightPanel = showStatusPanel();
        panel = new JPanel(new BorderLayout());
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);
        add(panel);

    }

    private JScrollPane showSprinklerPanel() {
        tree = createTree();
        Icon imageIcon = new ImageIcon("ui/sprinkler.png");
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setLeafIcon(imageIcon);
        tree.setCellRenderer(renderer);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        return scrollPane;
    }

    private JTree createTree() {
        Font font = new Font("Georgia", Font.BOLD, 18);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("All");

        DefaultMutableTreeNode southGroup = new DefaultMutableTreeNode("South");
        southGroup.add(new DefaultMutableTreeNode("1S"));
        southGroup.add(new DefaultMutableTreeNode("2S"));
        southGroup.add(new DefaultMutableTreeNode("3S"));
        southGroup.add(new DefaultMutableTreeNode("4S"));

        DefaultMutableTreeNode northGroup = new DefaultMutableTreeNode("North");
        northGroup.add(new DefaultMutableTreeNode("1N"));
        northGroup.add(new DefaultMutableTreeNode("2N"));
        northGroup.add(new DefaultMutableTreeNode("3N"));
        northGroup.add(new DefaultMutableTreeNode("4N"));

        DefaultMutableTreeNode eastGroup = new DefaultMutableTreeNode("East");
        eastGroup.add(new DefaultMutableTreeNode("1E"));
        eastGroup.add(new DefaultMutableTreeNode("2E"));
        eastGroup.add(new DefaultMutableTreeNode("3E"));
        eastGroup.add(new DefaultMutableTreeNode("4E"));

        DefaultMutableTreeNode westGroup = new DefaultMutableTreeNode("West");
        westGroup.add(new DefaultMutableTreeNode("1W"));
        westGroup.add(new DefaultMutableTreeNode("2W"));
        westGroup.add(new DefaultMutableTreeNode("3W"));
        westGroup.add(new DefaultMutableTreeNode("4W"));

        root.add(southGroup);
        root.add(northGroup);
        root.add(eastGroup);
        root.add(westGroup);
        JTree jTree = new JTree(root);
        jTree.setShowsRootHandles(true);
        jTree.setFont(font);
        return jTree;
    }

    private JPanel showStatusPanel() {
        Font font = new Font("Georgia", Font.BOLD, 20);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel head = new JLabel("Sprinkler: ");
        head.setFont(font);
        sprinklerName = new JLabel("1N");
        sprinklerName.setFont(font);
        sprinklerName.setForeground(Color.decode("#3e5266"));
        panel1.add(Box.createRigidArea(new Dimension(5, 0)));
        panel1.add(head);
        panel1.add(sprinklerName);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel statusHead = new JLabel("Status: ");
        statusHead.setFont(font);
        status = new JLabel("ON");
        status.setFont(font);
        status.setForeground(Color.decode("#3e5266"));
        panel2.add(Box.createRigidArea(new Dimension(5, 0)));
        panel2.add(statusHead);
        panel2.add(status);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel funcHead = new JLabel("Functional: ");
        funcHead.setFont(font);
        function = new JLabel("Functional");
        function.setFont(font);
        function.setForeground(Color.decode("#3e5266"));
        panel3.add(Box.createRigidArea(new Dimension(5, 0)));
        panel3.add(funcHead);
        panel3.add(function);

        JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton enableButton = new JButton("ENABLE");
        enableButton.setFont(font);
        JButton disableButton = new JButton("DISABLE");
        disableButton.setFont(font);
        panel4.add(enableButton);
        panel4.add(disableButton);

        JPanel masterPanel = new JPanel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        masterPanel.add(panel1);
        masterPanel.add(panel2);
        masterPanel.add(panel3);
        masterPanel.add(panel4);

        return masterPanel;
    }
}
