package ship.ui.template;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public abstract class DefaultSwingView implements ActionListener {

    protected JFrame viewFrame;

    protected void createView() {
        viewFrame = new JFrame("Ship - The Game");
        viewFrame.setSize(500, 500);
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setLocationRelativeTo(null);
        //viewFrame.

        createControls();
        viewFrame.pack();
        viewFrame.setVisible(true);
    }

    public DefaultSwingView() {
    }

    protected abstract void createControls();

}
