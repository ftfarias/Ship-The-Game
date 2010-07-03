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
    public final void removeObserver(Observer observer) {
        observable.removeObserver(observer);
    }

    @Override
    public final void removeAllObservers() {
        observable.removeAllObservers();
    }

    @Override
    public final void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public final void notifyOutputUpdate() {
        observable.notifyAll(this, ObservableEvent.COMPUTER_OUTPUT);
    }

    public final void notifyButtonsUpdate() {
        observable.notifyAll(this, ObservableEvent.COMPUTER_BUTTONS);
    }

    protected void run(String command) {
    }

    @Override
    public ComputerButton[] getButtons() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
