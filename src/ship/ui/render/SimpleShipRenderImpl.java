package ship.ui.render;

import java.awt.Color;
import java.awt.Graphics2D;
import ship.domain.ship.Ship;
import ship.infra.ui.CoordinatesProjection;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class SimpleShipRenderImpl implements ShipRender {
    private static final int SIZE = 5;

    @Override
    public void render(CoordinatesProjection coordinatesProjection, Graphics2D g2d, Ship ship, Color color) {
        int x = coordinatesProjection.convertX(ship.getCurrentPosition().getX());
        int y = coordinatesProjection.convertY(ship.getCurrentPosition().getY());
        g2d.fillOval(x-SIZE, y-SIZE, SIZE*2, SIZE*2);
        g2d.setColor(color);
        // g2d.fill3DRect(x+3, y+3, 30, 30, true);
        g2d.drawString(ship.getName(), x + 15, y + 25);
        g2d.drawString(ship.getCurrentPosition().toString(), x + 15, y + 40);
        g2d.drawString("["+ship.getDestination().toString()+"]" , x + 15, y + 55);

    }

}
