package ship.ui.render;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import ship.domain.ship.Ship;
import ship.infra.ui.CoordinatesProjection;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ArrowShipRenderImpl implements ShipRender {

    private static final int SIZE = 6;

    @Override
    public void render(CoordinatesProjection coordinatesProjection, Graphics2D g2d, Ship ship, Color color) {
        int x = coordinatesProjection.convertX(ship.getCurrentPosition().getX());
        int y = coordinatesProjection.convertY(ship.getCurrentPosition().getY());
        g2d.rotate(ship.getBearingInRad(),x,y);

//        g2d.fillRect(x-SIZE, y-SIZE, SIZE * 2, SIZE * 2);
//        g2d.fillRect(x-(SIZE/2), y-(SIZE*2), SIZE, SIZE);

        g2d.fillOval(x - SIZE, y - SIZE, SIZE * 2, SIZE * 2);
        g2d.fillOval(x-(SIZE/2), y-(SIZE*2), SIZE, SIZE * 2);

        g2d.rotate(ship.getBearingInRad()*-1,x,y);

        //g2d.fillRect(x-SIZE, y-(SIZE), SIZE / 2, SIZE /2);
//        g2d.fillOval(x - SIZE, y - SIZE, SIZE * 2, SIZE * 2);
        g2d.setColor(color);
        // g2d.fill3DRect(x+3, y+3, 30, 30, true);
        g2d.drawString(ship.getName(), x + 15, y + 25);
        g2d.drawString(ship.getCurrentPosition().toString(), x + 15, y + 40);
        g2d.drawString("[" + ship.getDestination().toString() + "]", x + 15, y + 55);
    }
}
