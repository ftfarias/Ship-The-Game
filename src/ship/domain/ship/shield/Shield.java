package ship.domain.ship.shield;

import ship.domain.ship.Ship;
import ship.domain.ship.module.Module;
import ship.domain.universe.TimeDependent;
import ship.infra.observer.ObservableInterface;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Shield extends TimeDependent, ObservableInterface, Module {

    public void setShip(Ship ship);

    public boolean isShieldUp();

    public void shieldsUp();

    public void shieldsDown();
}
