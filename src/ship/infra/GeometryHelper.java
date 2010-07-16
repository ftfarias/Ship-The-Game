package ship.infra;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class GeometryHelper {
    public static final double PI2 = Math.PI * 2;

    public static double normalizeAngleInDegrees(double angle) {
        while (angle >= 360) {
            angle -= 360;
        }
        while (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public static double normalizeAngleInRad(double angle) {
        while (angle >= PI2) {
            angle -= PI2;
        }
        while (angle < 0) {
            angle += PI2;
        }
        return angle;
    }


    /**
     * convert angle [-PI,+PI] to bearing [0,+2*PI]
     */
    public static double convertAngleToBearingInRad(double angle) {
//        if (angle < 0 ) {
//            angle += Math.PI*2;
//        }
        return normalizeAngleInRad(angle + (Math.PI / 2));
    }

}
