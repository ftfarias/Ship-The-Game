package ship.domain.ship.computer.task;

import ship.domain.ship.computer.StandardComputer;

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
        computer.addDisplay("<b>Current Position</b>: "+getShip().getDestination());
        computer.addDisplay("<b>Destination</b>: "+getShip().getDestination());
        computer.addDisplay("<b>Speed</b>: "+getShip().getMoveBehavior().getSpeed());


    }




}
