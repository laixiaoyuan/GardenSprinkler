package ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

/**
 * Created by Lexie on 11/17/16.
 */
public class StatusPanel extends JPanel {

    private JTree tree;

    public StatusPanel() {
        super();
        tree = createTree();
        Icon imageIcon = new ImageIcon("ui/sprinkler.png");
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setLeafIcon(imageIcon);
        tree.setCellRenderer(renderer);

        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        add(scrollPane);
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
}
