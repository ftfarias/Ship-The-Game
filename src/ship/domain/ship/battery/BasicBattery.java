package ship.domain.ship.battery;

import ship.infra.observer.ObservableEvent;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicBattery extends DefaultBattery implements Battery {

    protected double maxCharge;
    protected double currentCharge;

    public BasicBattery(double currentCharge, double maxCharge) {
        this.maxCharge = maxCharge;
        this.currentCharge = currentCharge;
    }

    @Override
    public double getCharge() {
        return currentCharge;
    }

    @Override
    public double getMaxCharge() {
        return maxCharge;
    }

    @Override
    public double charge(double wantedAmount) {
        double chargedAmount = 0;
        if (wantedAmount > getAvaliableChargingCapacity()) {
            chargedAmount = getAvaliableChargingCapacity();
        } else {
            chargedAmount = wantedAmount;
        }
        currentCharge += chargedAmount;
        return chargedAmount;
    }

    public double getAvaliableChargingCapacity() {
        return maxCharge - currentCharge;
    }

    @Override
    public double drain(double wantedAmount) {
        double returnedAmount = 0;
        if (wantedAmount > currentCharge) {
            returnedAmount = currentCharge;
        } else {
            returnedAmount = wantedAmount;
        }
        currentCharge -= returnedAmount;
        return returnedAmount;
    }
}
