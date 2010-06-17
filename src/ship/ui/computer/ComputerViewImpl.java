/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.ui.computer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
    private JTextPane outputPane;
    private JTextField inputPane;

    public ComputerViewImpl(ComputerController controller, Computer computer) {
        this.controller = controller;
        this.computer = computer;
        createView();
    }

    @Override
    protected final void createView() {
        super.createView();
        viewFrame.setLocation(PositionService.getPositionForComputer());
        
    }

    @Override
    protected void initComponents() {
        outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setEditable(false);
        outputPane.setText("<html><font color=\"#FFFFFF\"><b>Computer off-line</b></font></html>");
        outputPane.setBackground(Color.black);
        outputPane.setForeground(Color.white);
        outputPane.setFont(new Font("Serif", Font.BOLD, 12));

        inputPane = new JTextField();
        inputPane.addActionListener(this);


        viewFrame.setMinimumSize(new Dimension(500, 500));
        viewFrame.add(inputPane, BorderLayout.SOUTH);
        viewFrame.add(outputPane, BorderLayout.CENTER);

        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == inputPane) {
            controller.inputCommand(ae.getActionCommand());
            inputPane.setText("");
        }
    }

    @Override
    public void update(Computer computer, ObservableEvent event) {
        if (event == ObservableEvent.COMPUTER_OUTPUT) {
            outputPane.setText(computer.getDisplayText());
        }
    }
}
