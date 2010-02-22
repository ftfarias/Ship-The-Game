package ship.infra.observer;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class Observable<T> implements ObservableInterface {
    private Set<Observer> observers = new HashSet<Observer>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void removeAllObservers() {
        observers.clear();
    }

    public void notifyAll(T object, ObservableEvent event) {
        for (Observer observer:observers) {
            observer.update(object, event);
        }
    }

}
