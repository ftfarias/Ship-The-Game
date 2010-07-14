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
    /*
     * F16 max speed: 2500 km/h ~= 700 m/s
     * a Ship in space ? maybe 3 times this:
     * Ship max speed: 2100 m/s
     */

    private static final String NAME = "Omnidirectional Graviton Engine";
    private static final String DESCRIPTION = "A non-inertial engines that moves in any direction. Consumes a lot of energy";
    private static final double ENERGY_PER_SPEEDY_UNIT = 1; // MW/(km/s)
    private static final double MAX_SPEED = 2.100; // km/s
    private static final double MIN_SPEED = 0.100; // km/s
    private boolean enabled = true;
    private Position currentPosition;
    private Position destination;
    private double speed;
    private PowerGrid powerGrid = null;

    public OmnidirectionalMoveBehavior() {
        speed = MIN_SPEED;
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

    @Override
    public double getSpeed() {
        if (!enabled) {
            return 0;
        }
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = Math.max(MIN_SPEED, Math.min(MAX_SPEED, speed));
    }

    @Override
    public boolean canMoveTo(Position position) {
        if (!enabled) {
            return false;
        }
        return true;
    }

    @Override
    public Position getCurrentPosition() {
        if (!enabled) {
            return new Position(0, 0);
        }
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
    public void timeElapsed(long time) {
        if (!enabled) {
            return;
        }
        double timeElapsed = ((double)time)/1000; // convert "long" to "double"
        System.out.println("Time :"+time+ "   "+timeElapsed );
//        System.out.println("Curre: "+currentPosition+" ->  "+destination);
        if (!isMoving()) {
            return;
        }

        // Finds the distance
        double distanceToDistination = currentPosition.distance(destination);

        double desiredSpeed = speed;

        // if we are too close, reduces speed
        if ((timeElapsed * desiredSpeed) >= distanceToDistination) {
            desiredSpeed = distanceToDistination / timeElapsed;
            System.out.println("Reducing speed to "+desiredSpeed);
        }

        double actualSpeed = getActualSpeed(desiredSpeed, timeElapsed);

//        System.out.println("1");
        // limit to time increment so you will NOT pass the destination
//        if ((actualSpeed * timeElapsed) > currentPosition.distance(destination)) {
            // calc the new time intervel to reach the destination
            //System.out.println("Limiting move: time: "+time+" Speed: "+actualSpeed+"   Distance:"+(time*actualSpeed));
//            timeElapsed = currentPosition.distance(destination) / actualSpeed;
//        }
//        System.out.println("time: "+time+ "  speed: "+actualSpeed);
        double angle = Math.atan2(destination.getY() - currentPosition.getY(), destination.getX() - currentPosition.getX());
        double xStep = Math.cos(angle) * actualSpeed * timeElapsed;
        double yStep = Math.sin(angle) * actualSpeed * timeElapsed;
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

    protected double getActualSpeed(double desiredSpeed, double time) {
        if (powerGrid == null) {
//            System.out.println("NO power, no move");
            return 0;
        }
        double actualSpeed = powerGrid.requestEnergy(desiredSpeed * ENERGY_PER_SPEEDY_UNIT * time) / ENERGY_PER_SPEEDY_UNIT;
//        double actualSpeed = desiredSpeed;
//        System.out.println("Desired Speed:"+desiredSpeed+"     actual Speed: "+actualSpeed);
        return actualSpeed;
    }

    @Override
    public Position getDestination() {
        return destination;
    }

    @Override
    public void beforeTimeElapsed() {
    }

    @Override
    public void afterTimeElapsed() {
    }

    @Override
    public long getWeight() {
        return 100l;
    }

    @Override
    public long getSize() {
        return 100l;
    }

    @Override
    public long getValue() {
        return 10l;
    }

    @Override
    public void turnOn() {
        enabled = true;
    }

    @Override
    public void turnOff() {
        enabled = false;
        speed = MIN_SPEED;
    }

    @Override
    public void stop() {
        destination = currentPosition;
    }

    @Override
    public void increaseSpeed() {
        if (!enabled) {
            return;
        }
        speed = Math.min(MAX_SPEED, speed * 1.2);
    }

    @Override
    public void decreaseSpeed() {
        if (!enabled) {
            return;
        }
        speed = Math.max(MIN_SPEED, speed * 0.8);
    }

    @Override
    public void setSpeedRelativeToMaxSpeed(double newSpeed) {
        if (!enabled) {
            return;
        }
        setSpeed(MAX_SPEED*newSpeed);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
