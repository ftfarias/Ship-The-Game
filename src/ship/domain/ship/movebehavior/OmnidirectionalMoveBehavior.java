package ship.domain.ship.movebehavior;

import ship.domain.ship.powergrid.PowerGrid;
import ship.domain.universe.Position;
import ship.domain.universe.Range;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class OmnidirectionalMoveBehavior implements MoveBehavior {

    private static final String NAME = "Omnidirectional Graviton Engine";
    private static final String DESCRIPTION = "A non-inertial engines that moves in any direction. Consumes a lot of energy";
    private Position currentPosition;
    private Position destination;
    private double speed;
    private PowerGrid powerGrid = null;

    public OmnidirectionalMoveBehavior() {
        setCurrentPosition(Position.ORIGIN);
        moveTo(Position.ORIGIN);
        setSpeed(1);
    }

    public PowerGrid getPowerGrid() {
        return powerGrid;
    }

    public void setPowerGrid(PowerGrid powerGrid) {
        this.powerGrid = powerGrid;
    }

    protected boolean isMoving() {
        return !currentPosition.equals(destination);
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
        this.destination = newPosition;
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
        if (!isMoving()) {
            return;
        }

        double actualSpeed = getActualSpeed();

//        System.out.println("1");
        // limit to time increment so you will NOT pass the destination
        if (actualSpeed * time > currentPosition.distance(destination)) {
            // calc the new time intervel to reach the destination
            time = currentPosition.distance(destination) / actualSpeed;
        }
//        System.out.println("time: "+time+ "speed: "+speed);
        double angle = Math.atan2(destination.getY() - currentPosition.getY(), destination.getX() - currentPosition.getX());
        double xStep = Math.cos(angle) * actualSpeed * time;
        double yStep = Math.sin(angle) * actualSpeed * time;
        double x = currentPosition.getX() + xStep;
        double y = currentPosition.getY() + yStep;
        currentPosition = new Position(x, y);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    private double getActualSpeed() {
        if (powerGrid == null) {
            return 0;
        }
        return powerGrid.requestEnergy(speed * 100) / 100;
    }
}
