package domain.ship.module;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public abstract class Module {

    public String getDescription() {
        return "ship module";
    }

    public abstract long getWeight();
    

    public abstract long getSize();

    /** Monetary cost */
    public abstract long getValue();


    public abstract long getEnergy();
}
