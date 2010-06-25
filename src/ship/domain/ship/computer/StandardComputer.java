/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.computer;

import ship.domain.ship.computer.task.Task;
import java.util.LinkedList;
import java.util.Queue;
import ship.domain.ship.computer.task.PrintTask;

/**
 *
 * @author ftfarias
 */
public class StandardComputer extends DefaultComputer {

    protected Queue<Task> tasks = new LinkedList<Task>();
    protected StringBuilder displayText = new StringBuilder();
    protected long taskCompletedPercent = 0;

    public StandardComputer() {
        System.out.println("Standard Computer created ! Sn: " + this.hashCode());
    }

    @Override
    public String getDisplayText() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<font color=\"#FFFFFF\">");
        sb.append(displayText);
        sb.append("</font>");
//        if (taskCompletedPercent > 0) {
//            sb.append("<br><font color=\"#FFFFFF\"><i>Remaning cycles: ");
//            sb.append(taskCompletedPercent);
//            sb.append("</i></font>");
////            sb.append("");
//        }
        sb.append("</html>");
        return sb.toString();
    }

    public void addDisplay(String text) {
        displayText.append(text);
        displayText.append("<br>");
        notifyUpdate();
    }

    @Override
    protected void run(String command) {
        Task task = interpretCommand(command);
        if (task != null) {
            tasks.add(task);
            addDisplay("new task added: " + task);
            notifyUpdate();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyUpdate();
    }

    public int getCurrentTaskProgressDone() {
        Task currentTask = tasks.peek();
        if (currentTask == null) {
            return 0;
        }
        return currentTask.getPercentDone();
    }

    @Override
    public void timeElapsed(long timeElapsed) {
        Task currentTask = tasks.peek();
        if (currentTask == null) {
            return;
        }
//        System.out.println("Computer " + this + " doing " + currentTask);
        //System.out.println("Computer Time Elapsed "+timeElapsed);
        long remainingTime = timeElapsed;
        while ((remainingTime > 0) && (currentTask != null)) {
            taskCompletedPercent = currentTask.getTimeRemaing();
            long timeForTask = getTimeForTask(currentTask, remainingTime);
            //System.out.println("time for task: "+timeForTask);
            boolean isTaskDone = currentTask.doTask(timeForTask);
            remainingTime -= timeForTask;
            //System.out.println("remaining time: " + remainingTime);
            if (isTaskDone) {
                System.out.println("task done: " + currentTask);
                tasks.remove(currentTask);
                remainingTime = 0;
                currentTask = tasks.peek();
                notifyUpdate();
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
