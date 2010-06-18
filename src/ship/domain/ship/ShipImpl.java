package ship.domain.ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ship.domain.player.Player;
import ship.domain.ship.battery.Battery;
import ship.domain.ship.computer.Computer;
import ship.domain.ship.module.Module;
import ship.domain.ship.sensor.Sensor;
import ship.domain.ship.movebehavior.MoveBehavior;
import ship.domain.ship.powergenerator.PowerGenerator;
import ship.domain.ship.powergrid.PowerGrid;
import ship.domain.ship.shield.Shield;
import ship.domain.universe.Position;
import ship.domain.universe.Range;
import ship.domain.universe.TimeDependent;
import ship.domain.universe.Universe;
import ship.infra.observer.Observable;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipImpl implements Ship {

    private Observable<Ship> observable = new Observable<Ship>();
    private String name;
    private Player player;
    private MoveBehavior moveBehavior;
    private final Universe universe;
    private Sensor sensor;
    private PowerGrid powerGrid;
    private Battery battery;
    private PowerGenerator powerGenerator;
    private Computer computer;
    private List<Module> modules = new ArrayList<Module>();
    private List<TimeDependent> timeDependents = new ArrayList<TimeDependent>();
    private final Shield shield;

//    private double bearing;
    public ShipImpl(String name, Player player, Universe universe, MoveBehavior moveBehavior, Sensor sensor, PowerGrid powerGrid,
            Battery battery, PowerGenerator powerGenerator, Computer computer, Shield shield) {
        this.name = name;
        this.player = player;
        this.moveBehavior = moveBehavior;
        this.universe = universe;
        this.sensor = sensor;
        this.powerGrid = powerGrid;
        this.battery = battery;
        this.powerGenerator = powerGenerator;
        this.computer = computer;
        this.shield = shield;

        modules.add(moveBehavior);
        modules.add(sensor);
        modules.add(powerGrid);
        modules.add(battery);
        modules.add(powerGenerator);
        modules.add(computer);
        modules.add(shield);

        timeDependents.add(powerGrid);
        timeDependents.add(moveBehavior);
        timeDependents.add(computer);
        timeDependents.add(shield);

        System.out.println("new ship \"" + name + "\" created");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public long getSize() {
        long totalSize = 0;

        for (Module m : modules) {
            totalSize += m.getSize();
        }

        return totalSize;
    }

    @Override
    public long getWeight() {
        long totalWeight = 0;

        for (Module m : modules) {
            totalWeight += m.getWeight();
        }

        return totalWeight;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setCurrentPosition(Position position) {
        moveBehavior.setCurrentPosition(position);
        notifyAll(ObservableEvent.SHIP_MOVED);
    }

    @Override
    public void moveTo(Position position) {
        moveBehavior.moveTo(position);
    }

    @Override
    public boolean inRange(Position position, double range) {
        return moveBehavior.inRange(position, range);
    }

    @Override
    public boolean inRange(Position position, Range range) {
        return moveBehavior.inRange(position, range);
    }

    @Override
    public Position getCurrentPosition() {
        return moveBehavior.getCurrentPosition();
    }

    @Override
    public void timeElapsed(long timeElapsed) {

        for (TimeDependent td : timeDependents) {
            td.beforeTimeElapsed();
        }

        for (TimeDependent td : timeDependents) {
            td.timeElapsed(timeElapsed);
        }

        for (TimeDependent td : timeDependents) {
            td.afterTimeElapsed();
        }


        // run sensors
        if (sensor.runShortSensorScan(universe, getCurrentPosition())) {
            notifyAll(ObservableEvent.SHIP_SRS_DETECTED_OBJECT);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observable.removeObserver(observer);
    }

    @Override
    public void removeAllObservers() {
        observable.removeAllObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    private void notifyAll(ObservableEvent event) {
        observable.notifyAll(this, event);
    }

    @Override
    public double getShortSensorRange() {
        return sensor.getShortRangeSensorRadius();
    }

    @Override
    public Set<Object> getShortSensorScanResults() {
        return sensor.getShortSensorScanResults();
    }

    @Override
    public PowerGrid getPowerGrid() {
        return powerGrid;
    }

    @Override
    public Universe getUniverse() {
        return universe;
    }

    @Override
    public Position getDestination() {
        return moveBehavior.getDestination();
    }

    @Override
    public void beforeTimeElapsed() {
    }

    @Override
    public void afterTimeElapsed() {
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
