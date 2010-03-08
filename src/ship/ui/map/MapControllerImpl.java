package ship.ui.map;

import ship.domain.ship.Ship;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;
import ship.ui.template.DefaultController;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MapControllerImpl extends DefaultController implements MapController, Observer<Ship> {
    private Ship ship;
    private MapView view;

    public MapControllerImpl(Ship ship) {
        this.ship = ship;
        view = new MapSwingView(this, ship);
        ship.registerObserver(this);
    }

    @Override
    public void update(Ship object, ObservableEvent event) {
        if ((event == ObservableEvent.SHIP_REMOVED) ||
            (event == ObservableEvent.SHIP_SRS_DETECTED_OBJECT)) {
            view.refresh();
            return;
        }
    }


}
