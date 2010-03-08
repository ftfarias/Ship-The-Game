package ship.infra.ui;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class LinearProjection {
    private double scalar;
    private double linear;

    public LinearProjection(double scalar, double linear) {
        this.scalar = scalar;
        this.linear = linear;
    }

    public int convert(double coordinate) {
        return (int) Math.round(convertDouble(coordinate));
    }

    public double invertConvert(int coordinate) {
        return (coordinate - linear) / scalar;
    }


    public double convertDouble(double coordinate) {
        return (coordinate*scalar) + linear;
    }

    public double getLinear() {
        return linear;
    }

    public double getScalar() {
        return scalar;
    }

}
