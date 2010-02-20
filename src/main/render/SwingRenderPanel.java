package main.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class SwingRenderPanel extends JPanel {
    private SwingRender swingRender;

    public SwingRenderPanel() {
        setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2d = (Graphics2D) g;
        swingRender.paint(g2d);
//        g2d.translate(401, 401);
    }

    protected void clear(Graphics g) {
        super.paintComponent(g);
    }
}
