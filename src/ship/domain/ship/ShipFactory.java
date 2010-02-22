package ship.domain.ship;

import ship.domain.player.Player;
import ship.domain.player.playerType.DumbFighterPlayer;
import ship.domain.player.playerType.ManualPlayer;
import ship.domain.ship.movement.OmnidirectionalMoveBehavior;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipFactory {

    public static ShipImpl buildPlayerShip() {
        ShipImpl ship = buildBasicShip();
        ship.getPlayer().setPlayterType(new ManualPlayer());
        return ship;
    }


    public static ShipImpl buildDumbFighterShip() {
        ShipImpl ship = buildBasicShip();
        ship.getPlayer().setPlayterType(new DumbFighterPlayer());
        return ship;
    }

    public static ShipImpl buildBasicShip() {
        Player player = new Player();
        //Movable moveBehavior = new OmnidirectionalMoveBehavior();
        OmnidirectionalMoveBehavior moveBehavior = new OmnidirectionalMoveBehavior();
        moveBehavior.setSpeed(0.001);

        ShipImpl ship = new ShipImpl("My Ship", player, moveBehavior);
        return ship;
    }


}
