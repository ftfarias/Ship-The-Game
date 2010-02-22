package ship.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import ship.application.ShipGame;
import ship.ui.map.MapPanel;
import ship.ui.template.DefaultSwingView;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class SwingGameViewImpl extends DefaultSwingView implements GameView {

    private GameController gameController;
    private ShipGame game; // model
    private JMenuBar menuBar;
    private JLabel universalTime;

    public SwingGameViewImpl(GameController gameController, ShipGame game) {
        System.out.println("AQUI");
        assert game != null;
        assert gameController != null;
        this.gameController = gameController;
        this.game = game;
        createView();
    }

    @Override
    protected void createView() {
        super.createView();
        setFrameToExitTheGame();
    }

    private void setFrameToExitTheGame() {
        viewFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent event) {
                gameController.exit();
            }
        });
    }

    @Override
    protected void createControls() {
        viewFrame.setPreferredSize(new Dimension(500, 500));
        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Navigation");
        menu.setMnemonic(KeyEvent.VK_N);

        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Move To",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_M, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.moveTo();
            }
        });
        menu.add(menuItem);
        viewFrame.setJMenuBar(menuBar);

        viewFrame.setLayout(new BorderLayout());
        
        // viewFrame North
        universalTime = new JLabel("<time>");
        universalTime.setHorizontalAlignment(JLabel.CENTER);
        viewFrame.add(universalTime, BorderLayout.NORTH);
        
        // viewFrame Center
//        JPanel centerPanel = new JPanel(new GridLayout(1, 1, 4, 4));
        JPanel centerPanel = new MapPanel(game.getUniverse(),game.getPlayerShip(),40);
        centerPanel.setPreferredSize(new Dimension(200, 200));
        viewFrame.add(centerPanel, BorderLayout.CENTER);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void setUniverseTime(double time) {
        universalTime.setText("Universe Time: "+String.format("%9.3f", time));
    }

    @Override
    public void refresh() {
        viewFrame.repaint();
    }
}
