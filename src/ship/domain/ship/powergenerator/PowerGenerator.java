package ship.domain.ship.powergenerator;

import ship.domain.ship.module.Module;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface PowerGenerator extends Module {

    public double getEnergy(double timeElapsed);

}
