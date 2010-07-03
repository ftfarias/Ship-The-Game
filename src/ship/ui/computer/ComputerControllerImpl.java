/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.ui.computer;

import ship.domain.ship.computer.Computer;
import ship.ui.template.DefaultController;

/**
 *
 * @author ftfarias
 */
public class ComputerControllerImpl extends DefaultController implements ComputerController {
    private Computer computer;
    private ComputerView view;

    public ComputerControllerImpl(Computer computer) {
        this.computer = computer;
        this.view = new ComputerViewImpl(this, computer);
        computer.registerObserver(view);
    }

    @Override
    public void inputCommand(String command) {
        computer.inputCommand(command);
    }


}
