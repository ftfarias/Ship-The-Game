package ship.ui.map;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import ship.domain.ship.Ship;
import ship.infra.observer.ObservableEvent;
import ship.ui.services.PositionService;
import ship.ui.template.DefaultSwingView;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MapSwingView extends DefaultSwingView implements MapView {

    private final MapController controller;
    private final Ship ship; // model

    public MapSwingView(MapController mapController, Ship ship) {
        assert ship != null;
        assert mapController != null;
        this.controller = mapController;
        this.ship = ship;
        createView();
        ship.registerObserver(this);
        ship.getUniverse().registerObserver(this);
    }

    @Override
    protected void createView() {
        super.createView();
        viewFrame.setLocation(PositionService.getPositionForMap());
    }

    @Override
    protected void initComponents() {
        viewFrame.setPreferredSize(new Dimension(500, 500));
        JPanel mapPanel = new MapPanel(ship);

        viewFrame.setContentPane(mapPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void update(Object object, ObservableEvent event) {
        if ((event == ObservableEvent.SHIP_ADDED)
                || (event == ObservableEvent.SHIP_REMOVED)
                || (event == ObservableEvent.SHIP_MOVED)
                || (event == ObservableEvent.TIME_ELAPSED)
                || (event == ObservableEvent.SHIP_SRS_DETECTED_OBJECT)) {
            viewFrame.repaint();
        }
    }
}
