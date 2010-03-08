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
public interface ShipRender {

    public void render(CoordinatesProjection coordinatesProjection, Graphics2D g2d, Ship ship, Color color);

}
