/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.ui.computer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
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
    private JButton[] jbuttons = new JButton[12];

    public ComputerViewImpl(ComputerController controller, Computer computer) {
        super();
        this.controller = controller;
        this.computer = computer;
        System.out.println("Creating Computer View Impl for " + computer);
        createView();
    }

    @Override
    protected final void createView() {
        super.createView();
        viewFrame.setLocation(PositionService.getPositionForComputer());
    }

    @Override
    protected void initComponents() {
        viewFrame.setTitle("Ship the Game - Computer");
        outputPane = new JTextPane();
        outputPane.setContentType("text/html");
        outputPane.setEditable(false);
        outputPane.setText("<html><font color=\"#FFFFFF\"><b>Computer off-line</b></font></html>");
        outputPane.setBackground(Color.black);
        outputPane.setForeground(Color.white);
        outputPane.setFont(new Font("Serif", Font.BOLD, 12));

        inputPane = new JTextField();
        inputPane.addActionListener(this);

        for (int i = 0; i < jbuttons.length; i++) {
            jbuttons[i] = new JButton("");
        }

        viewFrame.setMinimumSize(new Dimension(500, 500));

        // buttons
        viewFrame.setLayout(new GridBagLayout());
        addButton(jbuttons[0], 0, 0);
        addButton(jbuttons[1], 0, 1);
        addButton(jbuttons[2], 0, 2);
        addButton(jbuttons[3], 0, 3);

        addButton(jbuttons[4], 5, 0);
        addButton(jbuttons[5], 5, 1);
        addButton(jbuttons[6], 5, 2);
        addButton(jbuttons[7], 5, 3);

        addButton(jbuttons[8], 1, 4);
        addButton(jbuttons[9], 2, 4);
        addButton(jbuttons[10], 3, 4);
        addButton(jbuttons[11], 4, 4);

        // output panel
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 4;
        c.weightx = 1;
        c.weighty = 1;
        viewFrame.add(outputPane, c);

//        c = new GridBagConstraints();
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 5;
//        c.gridwidth = 5;
//        c.gridheight = 1;
//        c.weightx = 1;
//        c.weighty = 1;
//        viewFrame.add(inputPane, c);


    }

    private void addButton(JButton button, int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = 1;
        c.gridheight = 1;
        // do not resize buttons
        c.weightx = 0.001;
        c.weighty = 0.001;
        // sets the minimun size for bottom buttons
        c.ipady = 30;
        viewFrame.add(button, c);
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == inputPane) {
            controller.inputCommand(ae.getActionCommand());
            inputPane.setText("");
        }
        if (ae.getSource().getClass() == JButton.class) {
            JButton button = (JButton) ae.getSource();
            controller.inputCommand(button.getToolTipText());
        }

    }

    @Override
    public void update(Computer computer, ObservableEvent event) {
        if (event == ObservableEvent.COMPUTER_OUTPUT) {
            try {
                updateDisplay(computer.getDisplayText());
            } catch (InterruptedException ex) {
                Logger.getLogger(ComputerViewImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ComputerViewImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (event == ObservableEvent.COMPUTER_BUTTONS) {
            try {
                updateButtons();
            } catch (InterruptedException ex) {
                Logger.getLogger(ComputerViewImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ComputerViewImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void updateDisplay(String text) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                outputPane.setText(computer.getDisplayText());
            }
        });
    }

    @Override
    public void setButton(int button, String caption, String command) {
        if ((button < 0) || (button >= jbuttons.length)) {
            return;
        }
        jbuttons[button].setText(caption);
        jbuttons[button].setToolTipText(command);
    }

    private void updateButtons() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < jbuttons.length; i++) {
                    jbuttons[i].setText(computer.getButtons()[i].getCaption());
                    jbuttons[i].setToolTipText(computer.getButtons()[i].getCommand());
                }
            }
        });
    }
}
