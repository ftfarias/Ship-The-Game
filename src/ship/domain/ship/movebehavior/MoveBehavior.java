/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.movebehavior;

import ship.domain.ship.Ship;
import ship.domain.ship.module.Module;
import ship.domain.universe.Position;
import ship.domain.universe.Positionable;
import ship.domain.universe.TimeDependent;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface MoveBehavior extends Positionable, TimeDependent, Module {

    public void setShip(Ship ship);


    /**
     * 
     * @return currente speed in km/s
     */
    public double getSpeed();

    public boolean canMoveTo(Position position);

    public void moveTo(Position position);

    public void turnOn();

    public void turnOff();

    public void stop();

    public void increaseSpeed();

    public void decreaseSpeed();

    public void setSpeedRelativeToMaxSpeed(double newSpeed);

    public Position getDestination();
    
    public String getName();

    public boolean isEnabled();

}
