package ship.domain.ship.shield;

import ship.domain.ship.powergrid.PowerGrid;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class BasicShield extends DefaultShield implements Shield {

    private double shieldStrength = 0;
    private double maxShieldStrength = 10000;
    private double maxRechargeRate = 100;
    private PowerGrid powerGrid;

    public BasicShield(PowerGrid powerGrid) {
        this.powerGrid = powerGrid;
    }

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
    public void timeElapsed(double timeElapsed) {
        if (isShieldUp()) {
            if (shieldStrength < maxShieldStrength) {
                recharge();
            }
        }
    }

    private void recharge() {
        double amountToRecharge = maxShieldStrength - shieldStrength;
        if (amountToRecharge > maxRechargeRate) {
            amountToRecharge = maxRechargeRate;
        }
        double energyAvaliable = powerGrid.requestEnergy(amountToRecharge);
        shieldStrength += energyAvaliable;
    }
}
