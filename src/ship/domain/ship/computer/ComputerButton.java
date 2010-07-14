package ship.domain.ship.computer;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class ComputerButton {

    private String caption;
    private String command;

    public ComputerButton(String caption, String command) {
        this.caption = caption;
        this.command = command;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


}
