/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.ship;

import domain.player.Player;
import domain.universe.Movable;
import domain.universe.TimeDependent;

/**
 *
 * @author felipefarias
 */
public interface Ship extends Movable, TimeDependent {

    String getName();

    Player getPlayer();

    long getSize();

    long getWeight();

    void setName(String name);

    void setPlayer(Player player);

}
