package ship.domain.ship;

import java.util.Random;
import ship.domain.player.Player;
import ship.domain.player.playerType.DumbFighterPlayer;
import ship.domain.player.playerType.ManualPlayer;
import ship.domain.ship.battery.BasicBattery;
import ship.domain.ship.battery.Battery;
import ship.domain.ship.computer.Computer;
import ship.domain.ship.computer.StandardComputer;
import ship.domain.ship.computer.task.PrintTask;
import ship.domain.ship.movebehavior.OmnidirectionalMoveBehavior;
import ship.domain.ship.powergenerator.BasicPowerGenerator;
import ship.domain.ship.powergenerator.PowerGenerator;
import ship.domain.ship.powergrid.BasicPowerGrid;
import ship.domain.ship.powergrid.PowerGrid;
import ship.domain.ship.sensor.BasicSensor;
import ship.domain.ship.sensor.Sensor;
import ship.domain.ship.shield.BasicShield;
import ship.domain.ship.shield.Shield;
import ship.domain.universe.Universe;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipFactory {

    public static ShipImpl buildPlayerShip(Universe universe, String shipName) {
        ShipImpl ship = buildBasicShip(universe, shipName);
        ship.getPlayer().setPlayterType(new ManualPlayer());
        StandardComputer computer = (StandardComputer) ship.getComputer();

        Random r = new Random();
        computer.addTask(new PrintTask(computer, 1, "Computer on-line !"));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Booting MaxOS XII..."));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Instantiating Round-Robin task queues..."));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5000), "Balancing decision matrix..."));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Training neural networks..."));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Rendering a new brain..."));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Load00ing artificial intelligence routines..."));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Singularity Conscious System (SCS) ready !"));
//        computer.addTask(new PrintTask(computer, 500+r.nextInt(5500), "Computer is awake and conscious!"));
        return ship;
    }

    public static ShipImpl buildDumbFighterShip(Universe universe, String shipName) {
        ShipImpl ship = buildBasicShip(universe, shipName);
        ship.getPlayer().setPlayterType(new DumbFighterPlayer());
        return ship;
    }

    public static ShipImpl buildBasicShip(Universe universe, String shipName) {
        Player player = new Player();
        //Movable moveBehavior = new OmnidirectionalMoveBehavior();
        
        PowerGenerator powerGenerator = new BasicPowerGenerator();
        Battery battery = new BasicBattery(10000, 10000);
        PowerGrid powerGrid = new BasicPowerGrid(powerGenerator, battery);

        OmnidirectionalMoveBehavior moveBehavior = new OmnidirectionalMoveBehavior();
        moveBehavior.setPowerGrid(powerGrid);
        moveBehavior.setSpeed(0.001);

        Computer computer = new StandardComputer();
        Shield shield = new BasicShield();
        Sensor sensor = new BasicSensor();

        ShipImpl ship = new ShipImpl(shipName, player, universe, moveBehavior, sensor, powerGrid, battery, powerGenerator, computer, shield);

        computer.setShip(ship);
        shield.setShip(ship);
        sensor.setShip(ship);
        moveBehavior.setShip(ship);

        return ship;
    }
}
