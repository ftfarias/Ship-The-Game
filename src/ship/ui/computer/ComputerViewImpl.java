/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.ui.computer;

import java.awt.event.ActionEvent;
import ship.domain.ship.computer.Computer;
import ship.infra.observer.ObservableEvent;
import ship.ui.services.PositionService;
import ship.ui.template.DefaultSwingView;

/**
 *
 * @author ftfarias
 */
public class ComputerViewImpl extends DefaultSwingView implements ComputerView {

    private final ComputerController controller;
    private final Computer computer;

    public ComputerViewImpl(ComputerController controller, Computer computer) {
        this.controller = controller;
        this.computer = computer;
        createView();
    }

    @Override
    protected void createView() {
        super.createView();
        viewFrame.setLocation(PositionService.getPositionForComputer());
    }

    @Override
    protected void createControls() {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void update(Computer object, ObservableEvent event) {
    }
}
