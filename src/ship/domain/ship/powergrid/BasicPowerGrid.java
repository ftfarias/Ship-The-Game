package ship.domain.ship.powergrid;

import ship.domain.ship.battery.Battery;
import ship.domain.ship.powergenerator.PowerGenerator;
import ship.infra.observer.ObservableEvent;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicPowerGrid extends DefaultPowerGrid {

    protected Battery battery;
    protected PowerGenerator powerGenerator;
    private double avaliableEnergy = 0;
    private double powerGridBalance = 0;

    public BasicPowerGrid(PowerGenerator powerGenerator, Battery battery) {
        this.battery = battery;
        assert this.battery != null;
        this.powerGenerator = powerGenerator;
        assert this.powerGenerator != null;
    }

    @Override
    public void timeElapsed(double timeElapsed) {
        avaliableEnergy = powerGenerator.getEnergy(timeElapsed);
    }

    @Override
    public double requestEnergy(double amountRequested) {
        if (amountRequested < avaliableEnergy) {
            avaliableEnergy -= amountRequested;
            return amountRequested;
        } else {
            avaliableEnergy += battery.drain(amountRequested - avaliableEnergy);
            double result = avaliableEnergy;
            avaliableEnergy = 0;
            return result;
        }
    }

    @Override
    public void update() {
        powerGridBalance = avaliableEnergy;
//        notifyAll(ObservableEvent.POWER_GRID_UPDATE);
        if (avaliableEnergy > 0) {
            battery.charge(avaliableEnergy);
        }
        notifyAll(this, ObservableEvent.POWER_GRID_UPDATE);
    }

    @Override
    public double getPowerGridBalance() {
        return powerGridBalance;
    }
}
