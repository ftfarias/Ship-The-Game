/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.computer;

import ship.domain.ship.Ship;
import ship.domain.ship.module.Module;
import ship.domain.universe.TimeDependent;
import ship.infra.observer.ObservableInterface;

/**
 *
 * @author ftfarias
 */
public interface Computer extends Module, TimeDependent, ObservableInterface {

    public void setShip(Ship ship);

    public String getDisplayText();

    public void inputCommand(String command);

}
