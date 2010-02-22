package ship.ui;

import javax.swing.JOptionPane;
import ship.application.ShipGame;
import ship.domain.universe.Position;
import ship.domain.universe.Universe;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;
import ship.ui.template.DefaultController;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class GameControllerImpl extends DefaultController implements GameController, Observer<Universe> {
    private GameView view;
    private ShipGame game; // model

    public GameControllerImpl(ShipGame game) {
        assert game != null;
        this.game = game;
        view = new SwingGameViewImpl(this, game);
        game.getUniverse().registerObserver(this);
    }

    @Override
    public void exit() {
        game.exit();
    }

    @Override
    public void update(Universe universe, ObservableEvent event) {
        if (event == ObservableEvent.TIME_ELAPSED) {
            view.setUniverseTime(universe.getUniverseTime());
            view.refresh();
            return;
        }
        System.out.println("New Event from model: "+event.name());
    }

    @Override
    public void moveTo() {
        Position destiny = askUserForCoordinates();
        if (destiny != null) {
            System.out.println("moving to "+destiny);
            game.getPlayerShip().moveTo(destiny);
        }
    }

}
