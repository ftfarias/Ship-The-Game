package ship.infra.ui;

import java.awt.Graphics2D;
import ship.domain.universe.Position;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class CoordinatesProjection {
    private LinearProjection x;
    private LinearProjection y;

    public CoordinatesProjection(double scalarX, double linearX, double scalarY, double linearY) {
        x = new LinearProjection(scalarX, linearX);
        y = new LinearProjection(scalarY, linearY);
    }

    public double getXScalar() {
        return x.getScalar();
    }

    public double getXLinear() {
        return x.getLinear();
    }

    public double getYScalar() {
        return y.getScalar();
    }

    public double getYLinear() {
        return y.getLinear();
    }

    /**
     * Create the projection for a imaginary coordinate system,
     * given the cetral position in screen and the radius, for
     * a given Graphics2D object.
     * @param centerPosition
     * @param radius
     * @param g2d
     */
    public CoordinatesProjection(Position centerPosition, double radius, Graphics2D g2d) {
        double scalarX = g2d.getClipBounds().width / radius;
        double scalarY = g2d.getClipBounds().height / radius;
        double linearX =  (-centerPosition.getX()+(radius/2))*scalarX;
        double linearY =  (-centerPosition.getY()+(radius/2))*scalarY;
//        System.out.println("Scalar X: "+scalarX);
//        System.out.println("Linear X: "+linearX);
        x = new LinearProjection(scalarX, linearX);
        y = new LinearProjection(scalarY, linearY);
    }

    public int convertX(double coordinate) {
        return x.convert(coordinate);
    }

    public int convertY(double coordinate) {
        return y.convert(coordinate);
    }


}
