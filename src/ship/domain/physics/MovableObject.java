package ship.domain.physics;

import ship.domain.universe.TimeDependent;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class MovableObject implements TimeDependent {
    private Vector2d position;
    private Vector2d speed;
    private Vector2d acceleration;

    public Vector2d getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2d acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public Vector2d getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2d speed) {
        this.speed = speed;
    }

    @Override
    public void beforeTimeElapsed() {
        // do nothing
    }

    @Override
    public void timeElapsed(long timeElapsed) {
        speed = speed.add(acceleration.multiply(timeElapsed));
        position = position.add(speed.multiply(timeElapsed));
    }

    @Override
    public void afterTimeElapsed() {
        // do nothing
    }

}
