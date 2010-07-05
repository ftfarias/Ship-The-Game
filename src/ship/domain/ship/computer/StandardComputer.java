package ship.domain.ship.computer;

import ship.domain.ship.Ship;
import ship.domain.ship.computer.task.Task;
import java.util.LinkedList;
import java.util.Queue;
import ship.domain.ship.computer.task.ClearDisplayTask;
import ship.domain.ship.computer.task.PrintTask;
import ship.domain.ship.computer.task.ShowNavTask;

/**
 *
 * @author ftfarias
 */
public class StandardComputer extends DefaultComputer {

    protected Queue<Task> tasks = new LinkedList<Task>();
    protected StringBuilder displayText = new StringBuilder();
    protected long taskCompletedPercent = 0;
    protected ComputerButton[] buttons = new ComputerButton[12];

    public StandardComputer() {
        System.out.println("Standard Computer created ! Sn: " + this.hashCode());
        setButtonsForMainMenu();
    }

    public void setButtonsForMainMenu() {
        buttons[0] = new ComputerButton("Navegation", "nav");
        buttons[1] = new ComputerButton("Power Grid", "pwrgrd");
        buttons[2] = new ComputerButton("Computer", "computer");
        buttons[3] = new ComputerButton("Generators", "gen");
        buttons[4] = new ComputerButton("Weapons", "wpn");
        buttons[5] = new ComputerButton("Shield", "shld");
        buttons[6] = new ComputerButton("Comm", "comm");
        buttons[7] = new ComputerButton("Sensors", "sns");
        buttons[8] = new ComputerButton("Main", "main");
        buttons[9] = new ComputerButton("", "");
        buttons[10] = new ComputerButton("", "");
        buttons[11] = new ComputerButton("Clear", "cls");
    }

    public void setButton(int index, String caption, String command) {
        buttons[index] = new ComputerButton(caption, command);
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
        notifyOutputUpdate();
    }

    public void clearDisplay() {
        displayText.delete(0, displayText.length());
    }

    @Override
    protected void run(String command) {
        Task task = interpretCommand(command);
        if (task != null) {
            tasks.add(task);
            addDisplay("new task added: " + task);
            notifyOutputUpdate();
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyOutputUpdate();
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
                notifyOutputUpdate();
                notifyButtonsUpdate();
            }
        }
    }

    protected Task interpretCommand(String command) {
        // System.out.println("new command: "+command);
        Task task = null;

        if (command.equalsIgnoreCase("main")) {
            setButtonsForMainMenu();
            notifyButtonsUpdate();
            return null;
        } else if (command.equalsIgnoreCase("cls")) {
            task = new ClearDisplayTask(this);
        } else if (command.startsWith("nav")) {
            return interpretNavCommand(command);
        } else {
            task = new PrintTask(this, command);
        }
        return task;
    }

    protected Task interpretNavCommand(String command) {
        setButton(1, "SPD +", "navspd+");
        setButton(2, "SPD -", "navspd-");
        setButton(4, "ON", "navon");
        setButton(5, "OFF", "navoff");
        setButton(6, "STOP", "navstop");
        notifyButtonsUpdate();
        Task task = new ShowNavTask(this);
        if (command.equalsIgnoreCase("navstop")) {
             getShip().getMoveBehavior().stop();
        } else if (command.startsWith("navspd+")) {
            getShip().getMoveBehavior().increaseSpeed();
        } else if (command.startsWith("navspd+")) {
            getShip().getMoveBehavior().decreaseSpeed();
        } else if (command.startsWith("navon")) {
            getShip().getMoveBehavior().turnOn();
        } else if (command.startsWith("navoff")) {
            getShip().getMoveBehavior().turnOff();
        } else {
        }
        return task;

    }


    private long getTimeForTask(Task currentTask, long remainingTime) {
        return Math.min(currentTask.getTimeRemaing(), remainingTime);
    }

    @Override
    public ComputerButton[] getButtons() {
        return buttons;
    }

    public Ship getShip() {
        return ship;
    }
}
