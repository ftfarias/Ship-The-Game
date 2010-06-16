/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.universe;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface TimeDependent {

    public void beforeTimeElapsed();

    public void timeElapsed(long timeElapsed);

    public void afterTimeElapsed();


}
