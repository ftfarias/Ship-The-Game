package ship.ui.map;

import ship.domain.ship.Ship;
import ship.ui.template.DefaultController;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MapControllerImpl extends DefaultController implements MapController {
    private Ship ship;
    private MapView view;

    public MapControllerImpl(Ship ship) {
        this.ship = ship;
        view = new MapSwingView(this, ship);
    }

}
