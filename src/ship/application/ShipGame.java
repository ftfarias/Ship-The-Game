package ship.application;

import java.util.Timer;
import java.util.TimerTask;
import ship.domain.ship.Ship;
import ship.domain.ship.ShipFactory;
import ship.domain.universe.Position;
import ship.domain.universe.Universe;
import ship.infra.observer.Observable;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipGame {
    private Observable<ShipGame> observable = new Observable<ShipGame>();
    private Universe universe;
    private long lastTurnTime;
    private Ship playerShip;
    private Timer timer;

    public void initialize() {
        System.out.println("Creating Universe... ");
        setupUniverse();
        System.out.println("Creating Player and Ships... ");
        createPlayerAndShips();
        System.out.println("Starting game...");
        startTimer();
    }

    private void setupUniverse() {
        universe = new Universe();
    }

    private void createPlayerAndShips() {
        playerShip = ShipFactory.buildPlayerShip(universe);
        playerShip.setCurrentPosition(new Position(1, 2));
        universe.addShip(playerShip);

        Ship otherShip = ShipFactory.buildDumbFighterShip(universe);
        otherShip.setCurrentPosition(new Position(3, 3));
        otherShip.moveTo(new Position(3, 3));
        universe.addShip(otherShip);

        otherShip = ShipFactory.buildDumbFighterShip(universe);
        otherShip.setCurrentPosition(new Position(4, 5));
        otherShip.moveTo(new Position(-2, -2));
        universe.addShip(otherShip);
    }

    private void startTimer() {
        timer = new Timer("Ship Model timer", false);
        lastTurnTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis() - lastTurnTime;
                lastTurnTime = System.currentTimeMillis();
                universe.timeElapsed(elapsedTime);
            }
        }, 1000, 50);
    }

    public void exit() {
        System.out.println("Exiting...");
//        timer.cancel();
        System.exit(0);
    }

    private void updateObservers(ObservableEvent event) {
        observable.notifyAll(this, event);
    }

    public void removeObserver(Observer observer) {
        observable.removeObserver(observer);
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public Universe getUniverse() {
        return universe;
    }

    public Ship getPlayerShip() {
        return playerShip;
    }

}
