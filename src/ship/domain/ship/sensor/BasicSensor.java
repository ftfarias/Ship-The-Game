package ship.domain.ship.sensor;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import ship.domain.universe.Position;
import ship.domain.universe.Universe;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicSensor implements Sensor {
    private double shortRangeSensorRadius = 5.0;
    private Set<Object> shortScanResult = Collections.EMPTY_SET;


    @Override
    public double getShortRangeSensorRadius() {
        return shortRangeSensorRadius;
    }

    @Override
    public boolean runShortSensorScan(Universe universe, Position position) {
        shortScanResult = universe.getObjectInRange(position, shortRangeSensorRadius);
        return !shortScanResult.isEmpty();

    }

    @Override
    public Set<Object> getShortSensorScanResults() {
        return Collections.unmodifiableSet(shortScanResult);
    }

    @Override
    public String getDescription() {
        return "Basic Sensors";
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
        return 100l;
    }




}
