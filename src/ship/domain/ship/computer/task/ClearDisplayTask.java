package ship.domain.ship.computer.task;

import ship.domain.ship.computer.StandardComputer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ClearDisplayTask extends Task {

    public ClearDisplayTask(StandardComputer computer) {
        super(computer, 1);
    }

    @Override
    public void done() {
        computer.clearDisplay();
    }

}
