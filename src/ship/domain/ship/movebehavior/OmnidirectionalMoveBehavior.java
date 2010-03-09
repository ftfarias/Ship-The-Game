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
    private static final double ENERGY_PER_SPEEDY_UNIT = 8000;

    private Position currentPosition;
    private Position destination;
    private double speed;
    private PowerGrid powerGrid = null;

    public OmnidirectionalMoveBehavior() {
        setCurrentPosition(Position.ORIGIN);
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
        //double actualSpeed = speed;

//        System.out.println("1");
        // limit to time increment so you will NOT pass the destination
        if ((actualSpeed * time) > currentPosition.distance(destination)) {
            // calc the new time intervel to reach the destination
            //System.out.println("Limiting move: time: "+time+" Speed: "+actualSpeed+"   Distance:"+(time*actualSpeed));
            time = currentPosition.distance(destination) / actualSpeed;
        }
//        System.out.println("time: "+time+ "  speed: "+actualSpeed);
        double angle = Math.atan2(destination.getY() - currentPosition.getY(), destination.getX() - currentPosition.getX());
        double xStep = Math.cos(angle) * actualSpeed * time;
        double yStep = Math.sin(angle) * actualSpeed * time;
        double x = currentPosition.getX() + xStep;
        double y = currentPosition.getY() + yStep;
//        System.out.println("Step x: "+xStep+"   Step y: "+yStep);
//        System.out.println("New Position: "+new Position(x, y).toString());
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

    protected double getActualSpeed() {
        if (powerGrid == null) {
//            System.out.println("NO power, no move");
            return 0;
        }
//        System.out.println("requested speed: "+speed);
        double actualSpeed = powerGrid.requestEnergy(speed * ENERGY_PER_SPEEDY_UNIT) / ENERGY_PER_SPEEDY_UNIT;
//        System.out.println("actual Speed: "+actualSpeed);
        return actualSpeed;
    }

    @Override
    public Position getDestination() {
        return destination;
    }
}
