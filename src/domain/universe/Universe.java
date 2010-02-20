package domain.universe;

import domain.ship.Ship;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class Universe {

    private Set<Object> objects = new HashSet<Object>();

    protected void addObject(Object object) {
        objects.add(object);
    }

    protected void removeObject(Object object) {
        objects.remove(object);
    }

    public Set<Positionable> getMovablesInPosition(Position position, Range range) {
        Set<Positionable> result = new HashSet<Positionable>();
        for (Object obj : objects) {
            if (obj instanceof Positionable) {
                Positionable p = (Positionable) obj;
                if (p.inRange(position, range)) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    public void addShip(Ship ship) {
        addObject(ship);
    }

    public void removeShip(Ship ship) {
        addObject(ship);
    }

    public void timeElapsed(long elapsedTime) {
        for (Object obj : objects) {
            if (obj instanceof TimeDependent) {
                TimeDependent td = (TimeDependent) obj;
                td.timeElapsed(elapsedTime);
            }
        }
    }
}
