/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.computer;

import ship.domain.ship.module.Module;
import ship.domain.universe.TimeDependent;

/**
 *
 * @author ftfarias
 */
public class Computer implements Module, TimeDependent {

    @Override
    public String getDescription() {
        return "Ship's Computer";
    }

    @Override
    public long getWeight() {
        return 10;
    }

    @Override
    public long getSize() {
        return 1;
    }

    @Override
    public long getValue() {
        return 1000;
    }

    @Override
    public void beforeTimeElapsed() {
    }

    @Override
    public void timeElapsed(double timeElapsed) {
    }

    @Override
    public void afterTimeElapsed() {
    }
}
