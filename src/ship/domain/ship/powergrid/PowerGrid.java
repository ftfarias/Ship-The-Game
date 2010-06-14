/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.powergrid;

import ship.domain.ship.module.Module;
import ship.domain.universe.TimeDependent;
import ship.infra.observer.ObservableInterface;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface PowerGrid extends TimeDependent, ObservableInterface, Module {

    double requestEnergy(double amountRequested);

    public double getPowerGridBalance();
}
