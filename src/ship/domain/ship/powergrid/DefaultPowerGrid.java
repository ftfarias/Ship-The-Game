package ship.domain.ship.powergrid;

import ship.infra.observer.Observable;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public abstract class DefaultPowerGrid implements PowerGrid {

    protected Observable<PowerGrid> observable = new Observable<PowerGrid>();
    
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

    public void notifyAll(PowerGrid object, ObservableEvent event) {
        observable.notifyAll(object, event);
    }

}
