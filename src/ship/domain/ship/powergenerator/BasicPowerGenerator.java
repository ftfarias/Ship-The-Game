package ship.domain.ship.powergenerator;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicPowerGenerator implements PowerGenerator{
    private final double energyPerSecond;


    public BasicPowerGenerator() {
        energyPerSecond = 100;
    }

    public BasicPowerGenerator(double energyPerSecond) {
        this.energyPerSecond = energyPerSecond;
    }


    @Override
    public double getEnergy(double timeElapsed) {
        return energyPerSecond * timeElapsed / 1000;
    }

    @Override
    public String getDescription() {
        return "Basic Power Generator";
    }

    @Override
    public long getWeight() {
        return 100l;
    }

    @Override
    public long getSize() {
        return 100l;
    }

    @Override
    public long getValue() {
        return 100l;
    }

}
