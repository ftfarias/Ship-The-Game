/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.shield;

import ship.domain.universe.TimeDependent;
import ship.infra.observer.ObservableInterface;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Shield extends TimeDependent, ObservableInterface {

    public boolean isShieldUp();

    public void shieldsUp();

    public void shieldsDown();
}
