package ship.domain.ship.shield;

import ship.domain.ship.Ship;
import ship.infra.observer.Observable;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public abstract class DefaultShield implements Shield {

    private boolean shieldEnabled = false;
    protected Observable<Shield> observable = new Observable<Shield>();
    protected Ship ship;

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public boolean isShieldUp() {
        return shieldEnabled;
    }

    @Override
    public void shieldsUp() {
        this.shieldEnabled = true;
    }

    @Override
    public void shieldsDown() {
        this.shieldEnabled = false;
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

    public void notifyAll(Shield object, ObservableEvent event) {
        observable.notifyAll(object, event);
    }

    public void notifyUpdate() {
        observable.notifyAll(this, ObservableEvent.SHIELD_UPDATE);
    }

    
}
