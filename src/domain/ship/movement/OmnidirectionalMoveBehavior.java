package domain.ship.movement;

import domain.universe.Movable;
import domain.universe.Position;
import domain.universe.Range;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class OmnidirectionalMoveBehavior implements Movable {
    private Position currentPosition;
    private double speed;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean canMoveTo(Position position) {
        return true;
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public void setCurrentPosition(Position newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public void moveTo(Position destination, double timeInterval) {
        if (currentPosition.equals(destination)) {
            return;
        }
        // limit to time increment so you will NOT pass the destination
        if (speed * timeInterval > currentPosition.distance(destination) ) {
            // calc the new time intervel to reach the destination
            timeInterval = currentPosition.distance(destination) / speed;
        }
        double angle = Math.atan2(destination.getY() - currentPosition.getY(), destination.getX() - currentPosition.getX());
        double xStep = Math.cos(angle) * speed * timeInterval;
        double yStep = Math.sin(angle) * speed * timeInterval;
        double x = currentPosition.getX() + xStep;
        double y = currentPosition.getY() + yStep;
        setCurrentPosition(new Position(x, y));
    }

    @Override
    public boolean inRange(Position position, Range range) {
        return inRange(position, range.getDistance());
    }

    @Override
    public boolean inRange(Position position, double range) {
        return currentPosition.inRange(position, range);
    }
}
