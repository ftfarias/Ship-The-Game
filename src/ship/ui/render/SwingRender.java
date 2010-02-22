package ship.ui.render;

import ship.domain.ship.Ship;
import ship.domain.universe.Universe;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class SwingRender implements Render {

    private static final int GRID_SIZE = 30;
    private static final int GRID_WIDTH = 11;
    private static final int GRID_HEIGHT = 11;
    private JFrame frame;
    private SwingRenderPanel panel;
    private Universe universe;
    private Ship playerShip;

    @Override
    public void render() {
        frame.repaint();
    }

    public SwingRender(Universe universe, Ship playerShip) {
        this.universe = universe;
        this.playerShip = playerShip;
        panel = new SwingRenderPanel();
        openInJFrame(panel, 500, 500, "Ship, the Game", Color.BLACK);
        render();
    }

    private void openInJFrame(Container content,
            int width,
            int height,
            String title,
            Color bgColor) {
        frame = new JFrame(title);
        frame.setBackground(bgColor);
        content.setBackground(bgColor);
        frame.setSize(width, height);
        frame.setContentPane(content);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    protected void paint(Graphics2D g2d) {
        drawGrid(g2d);
        g2d.translate(GRID_SIZE / 2, GRID_SIZE / 2);
    }

    protected void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        for (int c = 0; c < GRID_WIDTH + 1; c++) {
            g2d.drawLine(convertX(0), convertY(c), convertX(GRID_HEIGHT), convertY(c));
            //g2d.drawLine(0, c, 10, c);
        }
        for (int c = 0; c < GRID_HEIGHT + 1; c++) {
            g2d.drawLine(convertX(c), convertY(0), convertX(c), convertY(GRID_WIDTH));
            //g2d.drawLine(0, c, 10, c);
        }
    }

    protected int convertX(double x) {
        return (int) Math.round(x * GRID_SIZE);
    }

    protected int convertY(double y) {
        return (int) Math.round(y * GRID_SIZE);
    }

    public static void main(String[] args) {
        SwingRender swingRender = new SwingRender(null, null);
    }
}
