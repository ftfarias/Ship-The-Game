/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.movebehavior;

import ship.domain.universe.Position;
import ship.domain.universe.Positionable;
import ship.domain.universe.TimeDependent;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface MoveBehavior extends Positionable, TimeDependent {

    public boolean canMoveTo(Position position);

    public void moveTo(Position position);

    public Position getDestination();
    
    public String getName();

    public String getDescription();

}
