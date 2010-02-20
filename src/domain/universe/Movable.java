/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.universe;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Movable extends Positionable {

    public boolean canMoveTo(Position position);

    public void moveTo(Position position, double timeInterval);
}
