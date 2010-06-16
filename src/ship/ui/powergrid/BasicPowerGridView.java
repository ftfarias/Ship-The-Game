package ship.ui.powergrid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import ship.domain.ship.powergrid.BasicPowerGrid;
import ship.domain.ship.powergrid.PowerGrid;
import ship.infra.observer.ObservableEvent;
import ship.ui.services.PositionService;
import ship.ui.template.DefaultSwingView;
import ship.ui.util.NumberFormater;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicPowerGridView extends DefaultSwingView implements PowerGridView {

    private final BasicPowerGridController controller;
    private final BasicPowerGrid powerGrid;
    private JProgressBar batteryGauge;
    private JLabel batteryChargeValue;

    public BasicPowerGridView(BasicPowerGridController controller, BasicPowerGrid powerGrid) {
        this.controller = controller;
        this.powerGrid = powerGrid;
        createView();
        powerGrid.registerObserver(this);
    }

    @Override
    protected void createView() {
        super.createView();
        viewFrame.setLocation(PositionService.getPositionForPowerGrid());
    }

    @Override
    protected void initComponents() {
       // viewFrame.setPreferredSize(new Dimension(500, 500));
//        viewFrame.setContentPane(mapPanel);
        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 3, 3));
//        JPanel centerPanel = new MapPanel(game.getPlayerShip());
//        centerPanel.setPreferredSize(new Dimension(200, 200));

        // Power Grid Balance


//        powerGridBalanceGauge = new JProgressBar(-1000, 1000);
//        powerGridBalanceGauge.setValue(0);
//        powerGridBalanceGauge.setStringPainted(true);
//        powerGridBalanceGauge.setString("-");
//
//        centerPanel.add(new JLabel("Power Grid Balance"));

        addPowerGridBalanceGauge(centerPanel);


//        // Battery Gauge
        batteryGauge = new JProgressBar(0, (int) powerGrid.getBatteryMaxCharge());
        batteryGauge.setValue((int) powerGrid.getBatteryCharge());
        batteryGauge.setOpaque(true);
        batteryGauge.setStringPainted(true);

        JLabel batteryChargeLabel = new JLabel("Battery Charge");
        batteryChargeLabel.setHorizontalAlignment(JLabel.RIGHT);
        centerPanel.add(batteryChargeLabel);
        centerPanel.add(batteryGauge);

        viewFrame.add(centerPanel, BorderLayout.CENTER);
        viewFrame.pack();
    }

    private void addPowerGridBalanceGauge(JPanel parent) {
        // label
        JLabel batteryChargeLabel = new JLabel("Grid Power");
        batteryChargeLabel.setHorizontalAlignment(JLabel.RIGHT);
        parent.add(batteryChargeLabel);

        batteryChargeValue = new JLabel("0");
        batteryChargeValue.setHorizontalAlignment(JLabel.LEFT);
        batteryChargeValue.setOpaque(true);
        parent.add(batteryChargeValue);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private void updateBattery() {
        batteryGauge.setMaximum((int) powerGrid.getBatteryMaxCharge());
        batteryGauge.setValue((int) powerGrid.getBatteryCharge());
        batteryGauge.setString(NumberFormater.INTEGER_FORMAT.format(powerGrid.getBatteryCharge()) + " - " + (int) (batteryGauge.getPercentComplete() * 100) + "%");
    }

    private void updatePowerGridBalance() {
        batteryChargeValue.setText(NumberFormater.INTEGER_FORMAT.format(powerGrid.getPowerGridBalance()));
        if (powerGrid.getPowerGridBalance() >= 0) {
            batteryChargeValue.setBackground(Color.GREEN);
        } else {
            batteryChargeValue.setBackground(Color.RED);
        }

    }

    @Override
    public void update(PowerGrid powerGrid, ObservableEvent event) {
        if (event == ObservableEvent.POWER_GRID_UPDATE) {
            updateBattery();
            updatePowerGridBalance();
            viewFrame.repaint();
        }
    }
}
