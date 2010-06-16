/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.computer.task;

import ship.domain.ship.computer.Computer;
import ship.domain.ship.computer.StandardComputer;

/**
 *
 * @author ftfarias
 */
public class Task {

    protected StandardComputer computer;
    private long timeToComplete;

    public Task(StandardComputer computer, long timeToComplete) {
        this.computer = computer;
        this.timeToComplete = timeToComplete;
    }

    public long getTimeRemaing() {
        return timeToComplete;
    }

    public void done() {
    }

    public boolean doTask(long timeAvaliable) {
        timeToComplete -= timeAvaliable;
        if (timeToComplete <= 0) {
            done();
            return true;
        }
        return false;
    }
}
