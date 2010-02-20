package main;

import main.render.Render;
import domain.ship.Ship;
import domain.ship.ShipFactory;
import domain.universe.Universe;
import java.util.Timer;
import java.util.TimerTask;
import main.render.SwingRender;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipApp {
    private final Universe universe = new Universe();
    private long lastTurnTime;
    private Render render;
    private Ship playerShip;

    public static void main(String[] args) {
        ShipApp app = new ShipApp();
        System.out.println("Creating Universe... ");
        app.setupUniverse();
        System.out.println("Preparing Render... ");
        app.setupRender();
        System.out.println("Creating Player and Ships... ");
        app.createPlayerAndShips();
        System.out.println("Starting game...");
        app.startTimer();
    }

    private void setupUniverse() {
        
    }

    private void createPlayerAndShips() {
        playerShip = ShipFactory.buildPlayerShip();
        universe.addShip(playerShip);

        Ship otherShip = ShipFactory.buildDumbFighterShip();
        universe.addShip(otherShip);
    }

    private void startTimer() {
        Timer timer = new Timer("Ship timer",false);
        lastTurnTime = System.currentTimeMillis();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                long elapsedTime = System.currentTimeMillis()- lastTurnTime;
                lastTurnTime = System.currentTimeMillis();
                universe.timeElapsed(elapsedTime);
                render();
            }

        }, 1000,1000);
    }

    private void render() {
        render.render();
    }

    private void setupRender() {
        render = new SwingRender(universe, playerShip);
    }


}
