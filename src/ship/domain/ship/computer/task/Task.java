package ship.domain.ship.computer.task;

import ship.domain.ship.computer.StandardComputer;

/**
 * A computer task, with no result. Uses this class as base for real tasks,
 * overriding done() method.
 * @author ftfarias
 */
public class Task {

    protected StandardComputer computer;
    private long timeToComplete;
    private long remainingTime;

    public Task(StandardComputer computer) {
        this(computer, 1);
    }

    public Task(StandardComputer computer, long timeToComplete) {
        this.computer = computer;
        this.timeToComplete = timeToComplete;
        this.remainingTime = this.timeToComplete;
    }

    public int getPercentDone() {
        return (int) (1-(remainingTime / timeToComplete))*100;
    }

    public long getTimeRemaing() {
        return remainingTime;
    }

    public void done() {
    }

    public boolean doTask(long timeAvaliable) {
        remainingTime -= timeAvaliable;
        if (remainingTime <= 0) {
            remainingTime = 0;
            done();
            return true;
        }
        return false;
    }
}
