package ship.domain.ship;

import ship.domain.player.Player;
import ship.domain.player.playerType.DumbFighterPlayer;
import ship.domain.player.playerType.ManualPlayer;
import ship.domain.ship.battery.BasicBattery;
import ship.domain.ship.battery.Battery;
import ship.domain.ship.computer.Computer;
import ship.domain.ship.computer.StandardComputer;
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

        return ship;
    }
}
