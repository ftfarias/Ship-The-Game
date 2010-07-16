package ship.domain.ship.computer.task;

import ship.domain.ship.computer.StandardComputer;
import ship.ui.util.NumberFormater;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ShowNavTask extends Task {

    public ShowNavTask(StandardComputer computer) {
        super(computer);
    }

    @Override
    public void done() {
        computer.clearDisplay();
        computer.addDisplay("Navigation Data");
        computer.addDisplay("");
        if (getShip().getMoveBehavior().isEnabled()) {

            computer.addDisplay("<b>Current Position</b>: " + getShip().getDestination());
            computer.addDisplay("<b>Destination</b>: " + getShip().getDestination());
            computer.addDisplay("<b>Speed</b>: " + NumberFormater.format(getShip().getMoveBehavior().getSpeed()*1000)+" m/s");;
        } else {
            computer.addDisplay("<b>NAVIGATION SYSTEM IS DISABLED</b>");
        }


    }
}
