package ship.ui.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import ship.domain.ship.Ship;
import ship.domain.universe.Position;
import ship.domain.universe.Universe;
import ship.infra.ui.CoordinatesProjection;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MapPanel extends JPanel {

    private CoordinatesProjection coordinatesProjection;
    private Universe universe;
    private Ship ship;
    private final int radius;

    public MapPanel(Universe universe, Ship ship, int radius) {
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        this.universe = universe;
        this.ship = ship;
        this.radius = radius;
    }

    @Override
    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2d = (Graphics2D) g;
        //coordinatesProjection = new CoordinatesProjection(new Position(0, 0), radius, g2d);
        coordinatesProjection = new CoordinatesProjection(ship.getCurrentPosition(), radius, g2d);
        setBackground(new Color(20, 20, 20));
        g2d.fillOval(0, 0, g2d.getClipBounds().width, g2d.getClipBounds().height);
        drawGrid(g2d);
        drawPlayerShip(g2d, ship);

    }

    protected void drawGrid(Graphics2D g2d) {

        int lowLimitX = (int) (Math.round(ship.getCurrentPosition().getX()) - radius / 2) ;
        int highLimitX = (int) (Math.round(ship.getCurrentPosition().getX()) + radius / 2) ;

        int lowLimitY = (int) (Math.round(ship.getCurrentPosition().getY()) - radius / 2) ;
        int highLimitY = (int) (Math.round(ship.getCurrentPosition().getY()) + radius / 2)     ;

   //     System.out.println("Hih Y" + highLimitY);

        g2d.setColor(new Color(80, 80, 80));
        for (int c = lowLimitX; c <= highLimitX; c++) {
//        for (int c = 0; c < radius + 1; c++) {
            g2d.drawLine(x(c), y(lowLimitX), x(c), y(highLimitX));
            g2d.drawString(String.valueOf(c), x(c)+2, 15);
        }

        for (int c = lowLimitY; c <= highLimitY; c++) {
            g2d.drawLine(x(lowLimitY), y(c), x(highLimitY), y(c));
            g2d.drawString(String.valueOf(c), 5, y(c)-2);
        }

    }

    protected int x(double x) {
        return coordinatesProjection.convertX(x);
    }

    protected int y(double y) {
        return coordinatesProjection.convertY(y);
    }

    protected void clear(Graphics g) {
        super.paintComponent(g);
    }

    private void drawPlayerShip(Graphics2D g2d, Ship ship) {
        g2d.setColor(Color.BLUE);
        drawShip(g2d, ship);
    }

    private void drawShip(Graphics2D g2d, Ship ship) {
        int x = x(ship.getCurrentPosition().getX());
        int y = y(ship.getCurrentPosition().getY());
        g2d.fillOval(x, y, 10, 10);
        g2d.setColor(Color.WHITE);
        // g2d.fill3DRect(x+3, y+3, 30, 30, true);
        g2d.drawString(ship.getName(), x + 15, y + 25);
        g2d.drawString(ship.getCurrentPosition().toString(), x + 15, y + 40);
    }
}
