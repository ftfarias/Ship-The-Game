package ship.ui;

import java.awt.BorderLayout;
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
import ship.application.ShipGame;
import ship.domain.ship.Ship;
import ship.domain.universe.Universe;
import ship.infra.observer.ObservableEvent;
import ship.ui.template.DefaultSwingView;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class SwingGameViewImpl extends DefaultSwingView implements GameView {

    
    private GameController gameController;
    private ShipGame game; // model
    private Ship playerShip;
    private JMenuBar menuBar;
    private JLabel universalTime;

    public SwingGameViewImpl(GameController gameController, ShipGame game) {
        assert game != null;
        assert gameController != null;
        this.gameController = gameController;
        this.game = game;
        this.playerShip = game.getPlayerShip();
        createView();
        game.registerObserver(this);
        game.getUniverse().registerObserver(this);
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
        //viewFrame.setPreferredSize(new Dimension(500, 500));
        viewFrame.setLocation(0, 0);
        menuBar = new JMenuBar();

        JMenu menu = new JMenu("Navigation");
        menu.setMnemonic(KeyEvent.VK_N);

        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Move To");
        menu.setMnemonic(KeyEvent.VK_M);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_M, ActionEvent.ALT_MASK));

        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.moveTo();
            }
        });
        menu.add(menuItem);

        // menu Controler
        JMenu menuControls = new JMenu("Controls");

        JMenuItem miShowMap = new JMenuItem("Show Map");
        miShowMap.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showMapFrame();
            }
        });
        menuControls.add(miShowMap);

        JMenuItem miShowPowerGrid = new JMenuItem("Show Power Grid");
        miShowPowerGrid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.showPowerGridFrame();
            }
        });
        menuControls.add(miShowPowerGrid);


        menuBar.add(menuControls);
        


        viewFrame.setJMenuBar(menuBar);
        viewFrame.setLayout(new BorderLayout());

        // viewFrame North
        universalTime = new JLabel("<time>");
        universalTime.setHorizontalAlignment(JLabel.CENTER);
        viewFrame.add(universalTime, BorderLayout.NORTH);

        // viewFrame Center
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 4, 4));
        viewFrame.add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private void setUniverseTime(double time) {
        universalTime.setText("Universe Time: " + String.format("%9.3f", time));
    }

    @Override
    public void update(Universe universe, ObservableEvent event) {
        if (event == ObservableEvent.TIME_ELAPSED) {
            setUniverseTime(universe.getUniverseTime());
            viewFrame.repaint();
        }
    }
}
