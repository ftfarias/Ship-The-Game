package domain.ship;

import domain.player.Player;
import domain.universe.Movable;
import domain.universe.Position;
import domain.universe.Range;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShipImpl implements Ship {

    private String name;
    private Player player;
    private Movable moveBehavior;

    private double bearing;
    private Position goingTo;

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
    public void moveTo(Position position, double timeInterval) {
        if (goingTo.equals(this))
        moveBehavior.moveTo(position, timeInterval);
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
        
    }

}
