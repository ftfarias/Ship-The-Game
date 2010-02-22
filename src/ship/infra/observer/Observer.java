/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.infra.observer;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface Observer<T> {

    void update(T object, ObservableEvent event);

}
