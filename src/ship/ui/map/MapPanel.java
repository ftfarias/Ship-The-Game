package ship.ui.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Set;
import javax.swing.JPanel;
import ship.domain.ship.Ship;
import ship.infra.ui.CoordinatesProjection;
import ship.ui.render.ShipRender;
import ship.ui.render.SimpleShipRenderImpl;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MapPanel extends JPanel {

    private CoordinatesProjection coordinatesProjection;
    private Ship ship;
    private final double sensorRadius;
    private ShipRender shipRender = new SimpleShipRenderImpl();

    public MapPanel(Ship ship) {
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        this.ship = ship;
        this.sensorRadius = ship.getShortSensorRange();
    }

    @Override
    public void paintComponent(Graphics g) {
        clear(g);
        Graphics2D g2d = (Graphics2D) g;
        //coordinatesProjection = new CoordinatesProjection(new Position(0, 0), radius, g2d);
        coordinatesProjection = new CoordinatesProjection(ship.getCurrentPosition(), sensorRadius*2, g2d);
        setBackground(new Color(40, 40, 40));
        g2d.fillOval(0, 0, g2d.getClipBounds().width, g2d.getClipBounds().height);
        drawGrid(g2d);
        drawUniverse(g2d);
        drawPlayerShip(g2d);
    }

    protected void drawGrid(Graphics2D g2d) {

        int lowLimitX = calcLowLimit(ship.getCurrentPosition().getX());
        int highLimitX = calcHighLimit(ship.getCurrentPosition().getX());


        int lowLimitY = calcLowLimit(ship.getCurrentPosition().getY());
        int highLimitY = calcHighLimit(ship.getCurrentPosition().getY());

        g2d.setColor(new Color(80, 80, 80));
        for (int c = lowLimitX; c <= highLimitX; c++) {
//        for (int c = 0; c < radius + 1; c++) {
            g2d.drawLine(x(c), y(lowLimitY), x(c), y(highLimitY));
            g2d.drawString(String.valueOf(c), x(c)+2, 15);
        }

        for (int c = lowLimitY; c <= highLimitY; c++) {
            g2d.drawLine(x(lowLimitX), y(c), x(highLimitX), y(c));
            g2d.drawString(String.valueOf(c), 5, y(c)-2);
        }

    }

    protected int x(double x) {
        return coordinatesProjection.convertX(x);
    }

    protected int y(double y) {
        return coordinatesProjection.convertY(y);
    }


    protected int calcLowLimit(double position) {
        return (int) (Math.round(position - sensorRadius)-1);
    }

    protected int calcHighLimit(double position) {
        return (int) (Math.round(position + sensorRadius)+1);
    }


    protected void clear(Graphics g) {
        super.paintComponent(g);
    }

    private void drawPlayerShip(Graphics2D g2d) {
        shipRender.render(coordinatesProjection, g2d, ship, Color.WHITE);
    }

    private void drawUniverse(Graphics2D g2d) {
        Set<Object> objects = ship.getShortSensorScanResults();
        for (Object obj:objects) {
            if (obj instanceof Ship) {
                Ship aShip = (Ship) obj;
                shipRender.render(coordinatesProjection, g2d, aShip, Color.BLUE);
            }
        }
    }
}
