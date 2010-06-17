/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship;

import java.util.Set;
import ship.domain.player.Player;
import ship.domain.ship.computer.Computer;
import ship.domain.ship.powergrid.PowerGrid;
import ship.domain.universe.Position;
import ship.domain.universe.Positionable;
import ship.domain.universe.TimeDependent;
import ship.domain.universe.Universe;
import ship.infra.observer.ObservableInterface;

/**
 *
 * @author felipefarias
 */
public interface Ship extends Positionable, TimeDependent, ObservableInterface {

    void setName(String name);

    void setPlayer(Player player);

//    void setUniverse(Universe universe);

    Universe getUniverse();

    public String getName();

    public Player getPlayer();

    public long getSize();

    public long getWeight();

    public double getShortSensorRange();

    public Set<Object> getShortSensorScanResults();

    public void moveTo(Position position);

    public Position getDestination();

    public PowerGrid getPowerGrid();

    public Computer getComputer();

}
