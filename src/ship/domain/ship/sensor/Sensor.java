/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.sensor;

import java.util.Set;
import ship.domain.ship.Ship;
import ship.domain.ship.module.Module;
import ship.domain.universe.Position;
import ship.domain.universe.Universe;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Sensor extends Module {
    /**
     * conducts a search in the universe for objects
     * @param universe
     * @param position
     * @return true if any object was detected
     */
    public boolean runShortSensorScan(Universe universe, Position position);

    public Set<Object> getShortSensorScanResults();

    public double getShortRangeSensorRadius();

    public void setShip(Ship ship);

}
