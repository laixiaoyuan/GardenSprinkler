package ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by Lexie on 11/17/16.
 */
class StatusPanel extends JPanel {

    private JTree tree;
    private JPanel panel;
    private JTextArea sprinklerName;
    private JTextArea status;
    private JTextArea function;



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
        return jTree;
    }

    private JPanel showStatusPanel() {
        Font font = new Font("Georgia", Font.BOLD, 14);

        JLabel head = new JLabel("Sprinkler: ");
        head.setFont(font);

        sprinklerName = new JTextArea("1N");
        sprinklerName.setFont(font);
        sprinklerName.setForeground(Color.BLUE);

        JLabel statusHead = new JLabel("Status: ");
        statusHead.setFont(font);

        status = new JTextArea("ON");
        status.setFont(font);
        status.setForeground(Color.BLUE);

        JLabel funcHead = new JLabel("Functional: ");
        funcHead.setFont(font);

        function = new JTextArea("Functional");
        function.setFont(font);
        function.setForeground(Color.BLUE);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(Box.createRigidArea(new Dimension(5, 0)));
        panel.add(head);
        panel.add(sprinklerName);
        panel.add(Box.createRigidArea(new Dimension(5, 0)));
        panel.add(statusHead);
        panel.add(status);
        panel.add(Box.createRigidArea(new Dimension(5, 0)));
        panel.add(funcHead);
        panel.add(function);

        return panel;
    }
}
