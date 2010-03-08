package ship.ui.services;

import java.awt.GraphicsEnvironment;
import java.awt.Point;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class PositionService {

    public static Point getPositionForMap() {
        Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        center.setLocation(center.x-250, center.y-250);
        return center;
    }

    

}
