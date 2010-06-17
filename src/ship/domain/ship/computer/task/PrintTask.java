/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.computer.task;

import ship.domain.ship.computer.StandardComputer;

/**
 *
 * @author ftfarias
 */
public class PrintTask extends Task{
    private String textToBeDisplayed;

    public PrintTask(StandardComputer computer, long timeToComplete, String textToBeDisplayed) {
        super(computer, timeToComplete);
        this.textToBeDisplayed = textToBeDisplayed;
    }

    public PrintTask(StandardComputer computer, String textToBeDisplayed) {
        super(computer, 1);
        this.textToBeDisplayed = textToBeDisplayed;
    }

    @Override
    public void done() {
        computer.addDisplay(textToBeDisplayed);
    }
}
