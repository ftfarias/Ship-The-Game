/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.universe;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Positionable {

    public Position getCurrentPosition();

    public void setCurrentPosition(Position position);

    public boolean inRange(Position position, Range range);

    public boolean inRange(Position position, double range);
}
