package ship.main;

import ship.application.ShipGame;
import ship.ui.GameController;
import ship.ui.GameControllerImpl;
import ship.ui.map.MapController;
import ship.ui.map.MapControllerImpl;

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
    }




}
