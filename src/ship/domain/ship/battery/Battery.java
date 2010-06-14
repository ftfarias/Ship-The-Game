/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.battery;

import ship.domain.ship.module.Module;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Battery extends Module{

    public double getCharge();

    public double getMaxCharge();

    public double charge(double wantedAmount);

    public double drain(double amount);

}
