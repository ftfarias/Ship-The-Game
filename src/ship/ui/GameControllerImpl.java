package ship.ui;

import ship.application.ShipGame;
import ship.domain.ship.powergrid.BasicPowerGrid;
import ship.domain.universe.Position;
import ship.ui.computer.ComputerControllerImpl;
import ship.ui.map.MapControllerImpl;
import ship.ui.powergrid.BasicPowerGridController;
import ship.ui.template.DefaultController;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class GameControllerImpl extends DefaultController implements GameController {
    private GameView view;
    private ShipGame game; // model

    public GameControllerImpl(ShipGame game) {
        assert game != null;
        this.game = game;
        view = new SwingGameViewImpl(this, game);
    }

    @Override
    public void exit() {
        game.exit();
    }

    @Override
    public void showMapFrame() {
        new MapControllerImpl(game.getPlayerShip());
    }

    @Override
    public void showPowerGridFrame() {
        // TODO factory/ Abstract factory to instanciate the right Controller
        new BasicPowerGridController((BasicPowerGrid) game.getPlayerShip().getPowerGrid());
    }

    @Override
    public void moveTo() {
        Position destiny = askUserForCoordinates();
        if (destiny != null) {
            System.out.println("ship moving to "+destiny);
            game.getPlayerShip().moveTo(destiny);
        }
    }

    @Override
    public void showComputerFrame() {
        new ComputerControllerImpl(game.getPlayerShip().getComputer());
    }


}
