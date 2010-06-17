package ship.domain.ship.module;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public interface Module {

    public String getDescription();

    public abstract long getWeight();
    

    public abstract long getSize();

    /** Monetary cost */
    public abstract long getValue();

}
