package ship.ui.powergrid;

import ship.domain.ship.powergrid.BasicPowerGrid;
import ship.ui.template.DefaultController;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicPowerGridController extends DefaultController implements  PowerGridController  {
    private BasicPowerGrid powerGrid;
    private BasicPowerGridView view;

    public BasicPowerGridController(BasicPowerGrid powerGrid) {
        this.powerGrid = powerGrid;
        this.view = new BasicPowerGridView(this, powerGrid);
    }
}
