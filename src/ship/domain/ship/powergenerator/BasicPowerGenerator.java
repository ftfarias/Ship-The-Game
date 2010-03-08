package ship.domain.ship.powergenerator;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicPowerGenerator implements PowerGenerator{
    private static final double MEGA_WATTS = 10;

    @Override
    public double getEnergy(double timeElapsed) {
        return MEGA_WATTS * timeElapsed / 1000;
    }

}
