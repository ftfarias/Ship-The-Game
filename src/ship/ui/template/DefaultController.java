package ship.ui.template;

import javax.swing.JOptionPane;
import ship.domain.universe.Position;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class DefaultController {

    public Position askUserForCoordinates() {
        return askUserForCoordinates(null);
    }
    
    public Position askUserForCoordinates(String msg) {
        if (msg == null) {
            msg = "Enter the coordinates";
        }
        String answer = (String)JOptionPane.showInputDialog(msg);
        String[] coord = answer.split(" ");
        if (coord.length != 2) {
            return null;
        }
        double x = Double.parseDouble(coord[0]);
        double y = Double.parseDouble(coord[1]);
        return new Position(x, y);
    }

}
