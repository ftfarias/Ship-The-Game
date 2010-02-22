package ship.domain.ship;

import ship.domain.player.Player;
import ship.domain.universe.Movable;
import ship.domain.universe.Position;
import ship.domain.universe.Range;
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
    private Movable moveBehavior;
//    private double bearing;

    public ShipImpl(String name, Player player, Movable moveBehavior) {
        this.name = name;
        this.player = player;
        this.moveBehavior = moveBehavior;
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
        return 0l;
    }

    @Override
    public long getWeight() {
        return 0l;
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
    public boolean canMoveTo(Position position) {
        return moveBehavior.canMoveTo(position);
    }

    @Override
    public void timeElapsed(double time) {
        moveBehavior.timeElapsed(time);
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

    public void notifyAll(Ship object, ObservableEvent event) {
        observable.notifyAll(object, event);
    }

}
