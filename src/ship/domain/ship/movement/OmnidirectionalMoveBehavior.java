package ship.domain.ship.movement;

import ship.domain.universe.Movable;
import ship.domain.universe.Position;
import ship.domain.universe.Range;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class OmnidirectionalMoveBehavior implements Movable {
    private Position currentPosition;
    private Position destination;
    private double speed;

    public OmnidirectionalMoveBehavior() {
        setCurrentPosition(Position.ORIGIN);
        moveTo(Position.ORIGIN);
        setSpeed(1);
    }


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
//        System.out.println("Current Position : "+newPosition);
        this.currentPosition = newPosition;
    }

    @Override
    public void moveTo(Position destination) {
//        System.out.println("Moving to "+destination);
        this.destination = destination;
    }

    @Override
    public boolean inRange(Position position, Range range) {
        return inRange(position, range.getDistance());
    }

    @Override
    public boolean inRange(Position position, double range) {
        return currentPosition.inRange(position, range);
    }

    @Override
    public void timeElapsed(double time) {
//        System.out.println("Time :"+time+ "   "+currentPosition.equals(destination) );
//        System.out.println("Curre: "+currentPosition+" ->  "+destination);
        if (currentPosition.equals(destination)) {
            return;
        }
//        System.out.println("1");
        // limit to time increment so you will NOT pass the destination
        if (speed * time > currentPosition.distance(destination) ) {
            // calc the new time intervel to reach the destination
            time = currentPosition.distance(destination) / speed;
        }
//        System.out.println("time: "+time+ "speed: "+speed);
        double angle = Math.atan2(destination.getY() - currentPosition.getY(), destination.getX() - currentPosition.getX());
        double xStep = Math.cos(angle) * speed * time;
        double yStep = Math.sin(angle) * speed * time;
        double x = currentPosition.getX() + xStep;
        double y = currentPosition.getY() + yStep;
        setCurrentPosition(new Position(x, y));
    }
}
