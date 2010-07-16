package ship.infra;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class Helper {

    public static double between(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

}
