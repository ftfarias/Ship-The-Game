package ship.ui.map;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.text.Position;
import ship.domain.ship.Ship;
import ship.ui.services.PositionService;
import ship.ui.template.DefaultSwingView;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MapSwingView extends DefaultSwingView implements MapView {

    private MapController controller;

    private Ship ship; // model

    public MapSwingView(MapController mapController, Ship ship) {
        assert ship != null;
        assert mapController != null;
        this.controller = mapController;
        this.ship = ship;
        createView();
    }

    @Override
    protected void createView() {
        super.createView();
        viewFrame.setLocation(PositionService.getPositionForMap());
    }

    @Override
    protected void createControls() {
        viewFrame.setPreferredSize(new Dimension(500, 500));
        JPanel mapPanel = new MapPanel(ship);
        viewFrame.setContentPane(mapPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void refresh() {
        viewFrame.repaint();
    }

}
