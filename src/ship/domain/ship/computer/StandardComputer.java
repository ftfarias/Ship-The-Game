/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.computer;

import ship.domain.ship.computer.task.Task;
import java.util.LinkedList;
import java.util.Queue;
import ship.domain.ship.computer.task.PrintTask;
import ship.infra.observer.ObservableEvent;

/**
 *
 * @author ftfarias
 */
public class StandardComputer extends DefaultComputer {
    protected Queue<Task> tasks = new LinkedList<Task>();
    protected StringBuilder displayText = new StringBuilder();

    public StandardComputer() {
        tasks.add(new PrintTask(this, 1, "Computer on-line !"));
        tasks.add(new PrintTask(this, 1, "Booting MaxOS XII..."));
        tasks.add(new PrintTask(this, 10000, "Collecting enviroment data...."));
        tasks.add(new PrintTask(this, 5000, "Training neural networks...."));
        tasks.add(new PrintTask(this, 8000, "Computer ready !"));
    }

    @Override
    public String getDisplayText() {
        return "<html><font color=\"#FFFFFF\">"+displayText+"</font></html>";
    }

    public void addDisplay(String text) {
        displayText.append(text);
        displayText.append("<br>");
        observable.notifyAll(this, ObservableEvent.COMPUTER_OUTPUT);
    }

    @Override
    protected void run(String command) {
        Task task = interpretCommand(command);
        if (task != null) {
            tasks.add(task);
            addDisplay("new task added: "+task);
        }
    }

    @Override
    public void timeElapsed(long timeElapsed) {
        //System.out.println("Computer Time Elapsed "+timeElapsed);
        long remainingTime = timeElapsed;
        while (remainingTime > 0) {
            Task currentTask = tasks.peek();
            if (currentTask == null) {
                // no tasks in the queue, just return
                return;
            }

            long timeForTask = getTimeForTask(currentTask,remainingTime);
            System.out.println("time for task: "+timeForTask);

            boolean isTaskDone = currentTask.doTask(timeForTask);
            remainingTime -= timeForTask;
            if (isTaskDone) {
                System.out.println("task done: "+currentTask);
                tasks.remove(currentTask);
            }
        }
    }

    protected Task interpretCommand(String command) {
         Task task = new PrintTask(this, command);
         return task;
    }

    private long getTimeForTask(Task currentTask, long remainingTime) {
        return Math.min(currentTask.getTimeRemaing(), remainingTime);
    }



}
