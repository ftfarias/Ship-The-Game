/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship;

import ship.domain.player.Player;
import ship.domain.universe.Movable;
import ship.domain.universe.Positionable;
import ship.domain.universe.TimeDependent;
import ship.infra.observer.ObservableInterface;

/**
 *
 * @author felipefarias
 */
public interface Ship extends Positionable, Movable, TimeDependent, ObservableInterface {

    String getName();

    Player getPlayer();

    long getSize();

    long getWeight();

    void setName(String name);

    void setPlayer(Player player);

}
