package ship.domain.ship;

import ship.domain.player.Player;
import ship.domain.player.playerType.DumbFighterPlayer;
import ship.domain.player.playerType.ManualPlayer;
import ship.domain.ship.battery.BasicBattery;
import ship.domain.ship.battery.Battery;
import ship.domain.ship.movebehavior.OmnidirectionalMoveBehavior;
import ship.domain.ship.powergenerator.BasicPowerGenerator;
import ship.domain.ship.powergenerator.PowerGenerator;
import ship.domain.ship.powergrid.BasicPowerGrid;
import ship.domain.ship.powergrid.PowerGrid;
import ship.domain.ship.sensor.BasicSensor;
import ship.domain.ship.sensor.Sensor;
import ship.domain.universe.Universe;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipFactory {

    public static ShipImpl buildPlayerShip(Universe universe) {
        ShipImpl ship = buildBasicShip(universe);
        ship.getPlayer().setPlayterType(new ManualPlayer());
        return ship;
    }

    public static ShipImpl buildDumbFighterShip(Universe universe) {
        ShipImpl ship = buildBasicShip(universe);
        ship.getPlayer().setPlayterType(new DumbFighterPlayer());
        return ship;
    }

    public static ShipImpl buildBasicShip(Universe universe) {
        Player player = new Player();
        //Movable moveBehavior = new OmnidirectionalMoveBehavior();
        
        PowerGenerator powerGenerator = new BasicPowerGenerator();
        Battery battery = new BasicBattery(50, 100);
        PowerGrid powerGrid = new BasicPowerGrid(powerGenerator, battery);

        Sensor sensor = new BasicSensor();

        OmnidirectionalMoveBehavior moveBehavior = new OmnidirectionalMoveBehavior();
        moveBehavior.setPowerGrid(powerGrid);
        moveBehavior.setSpeed(0.001);


        
        ShipImpl ship = new ShipImpl("My Ship", player, universe, moveBehavior, sensor, powerGrid);
        return ship;
    }
}
