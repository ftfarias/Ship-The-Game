package ship.domain.ship.module.weapon;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public abstract class AbstractAmmoWeapon extends AbstractWeapon {

    /**
     * Ammo Weapons does not use energy
     * @return
     */
    public final long getEnergy() {
        return 0;
    }

    

}
