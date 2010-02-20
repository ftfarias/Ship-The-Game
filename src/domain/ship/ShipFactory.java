package domain.ship;

import domain.player.Player;
import domain.player.playerType.DumbFighterPlayer;
import domain.player.playerType.ManualPlayer;
import domain.ship.movement.OmnidirectionalMoveBehavior;
import domain.universe.Movable;

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
        Movable moveBehavior = new OmnidirectionalMoveBehavior();

        ShipImpl ship = new ShipImpl("My Ship", player, moveBehavior);
        return ship;
    }


}
