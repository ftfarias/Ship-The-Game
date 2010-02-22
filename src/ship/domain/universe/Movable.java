/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.universe;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Movable extends Positionable, TimeDependent {

    public boolean canMoveTo(Position position);

    public void moveTo(Position position);
}
