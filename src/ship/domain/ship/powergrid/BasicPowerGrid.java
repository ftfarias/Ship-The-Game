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
    protected double avaliableEnergy = 0;
    protected double powerGridBalance = 0;
    protected double timeElapsed;

    public BasicPowerGrid(PowerGenerator powerGenerator, Battery battery) {
        this.battery = battery;
        assert this.battery != null;
        this.powerGenerator = powerGenerator;
        assert this.powerGenerator != null;
    }

    @Override
    public void timeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
        avaliableEnergy = powerGenerator.getEnergy(timeElapsed);
    }

    @Override
    public double requestEnergy(double amountRequested) {
        if (amountRequested <= avaliableEnergy) {
            avaliableEnergy -= amountRequested;
            return amountRequested;
        } else {
//            System.out.println("AE: " + avaliableEnergy);
//            System.out.println("Drain: " + (amountRequested - avaliableEnergy));
            double drainedEnergy = battery.drain(amountRequested - avaliableEnergy);
            powerGridBalance -= drainedEnergy;
            avaliableEnergy += drainedEnergy;
//            System.out.println("AE2: " + avaliableEnergy);
            double result = avaliableEnergy;
            avaliableEnergy = 0;

            return result;
        }
    }

    @Override
    public double getPowerGridBalance() {
        return powerGridBalance / timeElapsed * 1000;
    }

    public double getBatteryMaxCharge() {
        return battery.getMaxCharge();
    }

    public double getBatteryCharge() {
        return battery.getCharge();
    }

    @Override
    public void beforeTimeElapsed() {
        powerGridBalance = 0;
    }

    @Override
    public void afterTimeElapsed() {
        if (avaliableEnergy > 0) {
            powerGridBalance += avaliableEnergy;
            battery.charge(avaliableEnergy);
            // avaliableEnergy = 0;
        }
        notifyAll(this, ObservableEvent.POWER_GRID_UPDATE);
    }

    @Override
    public String getDescription() {
        return "Basic Power Grid";
    }

    @Override
    public long getWeight() {
        return 100l;
    }

    @Override
    public long getSize() {
        return 100l;
    }

    @Override
    public long getValue() {
        return 10l;
    }
}
