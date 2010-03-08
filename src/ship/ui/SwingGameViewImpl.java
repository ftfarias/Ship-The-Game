package ship.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.KeyStroke;
import ship.application.ShipGame;
import ship.domain.ship.Ship;
import ship.domain.ship.powergrid.PowerGrid;
import ship.infra.observer.ObservableEvent;
import ship.infra.observer.Observer;
import ship.ui.template.DefaultSwingView;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class SwingGameViewImpl extends DefaultSwingView implements GameView, Observer<Ship> {
    private static final NumberFormat DOUBLE_FORMAT = new DecimalFormat("#,##0.00");
    private GameController gameController;
    private ShipGame game; // model
    private Ship playerShip;
    private JMenuBar menuBar;
    private JLabel universalTime;
    private JProgressBar batteryGauge;
    private JProgressBar powerGridBalanceGauge;

    public SwingGameViewImpl(GameController gameController, ShipGame game) {
        assert game != null;
        assert gameController != null;
        this.gameController = gameController;
        this.game = game;
        this.playerShip = game.getPlayerShip();
        playerShip.registerObserver(this);
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
        //viewFrame.setPreferredSize(new Dimension(500, 500));
        viewFrame.setLocation(0, 0);
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
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 4, 4));
//        JPanel centerPanel = new MapPanel(game.getPlayerShip());
//        centerPanel.setPreferredSize(new Dimension(200, 200));

        // Power Grid Balance


        powerGridBalanceGauge = new JProgressBar(-1000, 1000);
        powerGridBalanceGauge.setValue(0);
        powerGridBalanceGauge.setStringPainted(true);
        powerGridBalanceGauge.setString("-");

        centerPanel.add(new JLabel("Power Grid Balance"));
        centerPanel.add(powerGridBalanceGauge);


//        // Battery Gauge
//        batteryGauge = new JProgressBar(0, (int) playerShip.getBatteryMaxCharge());
//        batteryGauge.setValue((int) playerShip.getBatteryCharge());
//        batteryGauge.setStringPainted(true);
//        centerPanel.add(new JLabel("Battery Charge"));
//        centerPanel.add(batteryGauge);

        viewFrame.add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void setUniverseTime(double time) {
        universalTime.setText("Universe Time: " + String.format("%9.3f", time));
    }

    @Override
    public void refresh() {
        viewFrame.repaint();
    }

    @Override
    public void update(Ship ship, ObservableEvent event) {
        switch (event) {
//            case BATTERY_CHANGED:
//                updateBattery(ship);
//                break;
            case POWER_GRID_UPDATE:
                updatePowerGridBalance(ship);
                break;
        }
    }

    private void updateBattery(Ship ship) {
//        batteryGauge.setMaximum((int) ship.getBatteryMaxCharge());
//        batteryGauge.setValue((int) ship.getBatteryCharge());
//        batteryGauge.setString(DOUBLE_FORMAT.format(ship.getBatteryCharge())+" - "+(int)(batteryGauge.getPercentComplete()*100)+"%");
    }

    private void updatePowerGridBalance(Ship ship) {
        PowerGrid powerGrid = ship.getPowerGrid();
        powerGridBalanceGauge.setValue((int) powerGrid.getPowerGridBalance());
        powerGridBalanceGauge.setString(DOUBLE_FORMAT.format(powerGrid.getPowerGridBalance()));
        if (powerGrid.getPowerGridBalance() >= 0 ) {
            powerGridBalanceGauge.setBackground(Color.GREEN);
        } else {
            powerGridBalanceGauge.setBackground(Color.RED);
        }

    }
}
