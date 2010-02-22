package ship.domain.universe;

import ship.domain.ship.Ship;
import java.util.HashSet;
import java.util.Set;
import ship.infra.observer.Observable;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class Universe {
    private Observable<Universe> observable = new Observable<Universe>();
    private Set<Object> objects = new HashSet<Object>();
    private double universeTime = 0;

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
        updateObservers(ObservableEvent.SHIP_ADDED);
    }

    public void removeShip(Ship ship) {
        addObject(ship);
        updateObservers(ObservableEvent.SHIP_REMOVED);
    }

    /**
     * Informs the universe some time has elapsed, so things will happen.
     * @param elapsedTime time elapsed in miliseconds
     */
    public void timeElapsed(long elapsedTime) {
        increaseUniverseTime(elapsedTime);
        notifyUniverseObjectsAboutElapsedTime(elapsedTime);
        updateObservers(ObservableEvent.TIME_ELAPSED);
    }

    private void increaseUniverseTime(long elapsedTime) {
        universeTime += elapsedTime;
    }

    public void notifyUniverseObjectsAboutElapsedTime(long elapsedTime) {
        for (Object obj : objects) {
            if (obj instanceof TimeDependent) {
                TimeDependent td = (TimeDependent) obj;
                td.timeElapsed(elapsedTime);
            }
        }
    }

    private void updateObservers(ObservableEvent event) {
        observable.notifyAll(this, event);
    }

    public void removeObserver(Observer observer) {
        observable.removeObserver(observer);
    }

    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public double getUniverseTime() {
        return universeTime/10000;
    }
    
}
