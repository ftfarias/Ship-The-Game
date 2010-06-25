/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.computer;

import ship.domain.ship.Ship;
import ship.infra.observer.Observable;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;

/**
 *
 * @author ftfarias
 */
public class DefaultComputer implements Computer {

    protected Observable<Computer> observable = new Observable<Computer>();
    protected String lastCommand;
    protected Ship ship;

    @Override
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    @Override
    public String getDescription() {
        return "Ship's Computer";
    }

    @Override
    public long getWeight() {
        return 10;
    }

    @Override
    public long getSize() {
        return 1;
    }

    @Override
    public long getValue() {
        return 1000;
    }

    @Override
    public void beforeTimeElapsed() {
    }

    @Override
    public void timeElapsed(long timeElapsed) {
    }

    @Override
    public void afterTimeElapsed() {
    }

    @Override
    public String getDisplayText() {
        // fake "echo" display. Subclasses should implement real behavior
        return "<html><font color=\"#FFFFFF\">"+lastCommand+"</font></html>";
    }

    @Override
    public void inputCommand(String command) {
        lastCommand = command;
        run(command);
        observable.notifyAll(this, ObservableEvent.COMPUTER_OUTPUT);
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

    public void notifyUpdate() {
        observable.notifyAll(this, ObservableEvent.COMPUTER_OUTPUT);
    }

    protected void run(String command) {
    }
}
