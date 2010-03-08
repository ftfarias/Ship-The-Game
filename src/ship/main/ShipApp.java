package ship.main;

import ship.application.ShipGame;
import ship.domain.ship.powergrid.BasicPowerGrid;
import ship.ui.GameController;
import ship.ui.GameControllerImpl;
import ship.ui.map.MapController;
import ship.ui.map.MapControllerImpl;
import ship.ui.powergrid.BasicPowerGridController;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipApp {
    

    public static void main(String[] args) {
        ShipGame game = new ShipGame();
        game.initialize();
        GameController gameController = new GameControllerImpl(game);
        MapController mapController = new MapControllerImpl(game.getPlayerShip());
        new BasicPowerGridController((BasicPowerGrid) game.getPlayerShip().getPowerGrid());
    }
}
