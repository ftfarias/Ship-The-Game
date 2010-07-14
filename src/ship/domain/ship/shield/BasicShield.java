package ship.domain.ship.shield;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicShield extends DefaultShield implements Shield {

    private static final double DISCHARGE_RATE = 30; // Kjoules/sec
    private double shieldStrength = 0;
    private double maxShieldStrength = 10000;
    private double maxRechargeRate = 100;

    public double getShieldStrength() {
        return shieldStrength;
    }

    @Override
    public void shieldsDown() {
        super.shieldsDown();
        shieldStrength = 0;
    }

    @Override
    public void shieldsUp() {
        super.shieldsUp();
    }

    @Override
    public void timeElapsed(long timeElapsed) {
        if (isShieldUp()) {
            applyShieldNaturalDischarge(timeElapsed);
            if (shieldStrength < maxShieldStrength) {
                recharge(timeElapsed);
            }
        }
    }

    private void recharge(long timeElapsed) {
        double amountToRecharge = Math.max(maxShieldStrength - shieldStrength, maxRechargeRate) * timeElapsed / 100;
        double energyAvaliable = ship.getPowerGrid().requestEnergy(amountToRecharge);
        shieldStrength += energyAvaliable;
        notifyUpdate();
    }

    @Override
    public void beforeTimeElapsed() {
        
    }

    @Override
    public void afterTimeElapsed() {

    }

    @Override
    public String getDescription() {
        return "Basic Shield";
    }

    @Override
    public long getWeight() {
        return 100;
    }

    @Override
    public long getSize() {
        return 100;
    }

    @Override
    public long getValue() {
        return 100;
    }

    private void applyShieldNaturalDischarge(long timeElapsed) {
        shieldStrength -= (DISCHARGE_RATE * timeElapsed / 1000);

    }
}
