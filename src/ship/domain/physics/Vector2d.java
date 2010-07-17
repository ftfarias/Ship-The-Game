package ship.domain.physics;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class Vector2d {
    private double x;
    private double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2d multiply(double factor) {
        return new Vector2d(x*factor,y*factor);
    }

    public Vector2d add(Vector2d adder) {
        return new Vector2d(x+adder.x, y+adder.y);
    }

}
