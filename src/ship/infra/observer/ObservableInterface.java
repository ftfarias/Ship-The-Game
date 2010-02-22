/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.infra.observer;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public interface ObservableInterface {

    void registerObserver(Observer observer);

    void removeAllObservers();

    void removeObserver(Observer observer);

}
