package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Lexie on 11/19/16.
 */
public class MapPanel extends JPanel {

    public MapPanel() {
        super();
        draw();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5.0f));



        double startX = 100;
        double startY = 100;
        double width = 45;
        double height = 45;
        double gap = 60;

        Color color = Color.GREEN;
        g2d.setPaint(color);

        double curX1 = startX;
        for (int i = 0; i < 4; i++) {
            curX1 += gap;
            g2d.draw(new Ellipse2D.Double(curX1, startY, width, height));
        }

        double curY1 = startY;
        for (int i = 0; i < 4; i++) {
            curY1 += gap;
            g2d.draw(new Ellipse2D.Double(startX, curY1, width, height));
        }

        double curX2 = startX;
        for (int i = 0; i < 4; i++) {
            curX2 += gap;
            g2d.draw(new Ellipse2D.Double(curX2, curY1 + gap, width, height));
        }

        double curY2 = startY;
        for (int i = 0; i < 4; i++) {
            curY2 += gap;
            g2d.draw(new Ellipse2D.Double(curX1 + gap, curY2, width, height));
        }


        double stringStartY = 50;
        String string = "Sprinkler Location";
        g2d.setPaint(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString(string, (int)startX, (int)stringStartY);


    }

    public void draw() {

        repaint();
    }


}
