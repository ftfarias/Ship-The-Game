package ship.domain.player;

import ship.domain.player.playerType.PlayerType;
import java.util.UUID;

/**
 *
 * @author Felipe Farias
 * @version 1.0
 */
public class Player {

    private UUID uuid;
    private String name;

    private PlayerType playerType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public PlayerType getPlayterType() {
        return playerType;
    }

    public void setPlayterType(PlayerType playterType) {
        this.playerType = playterType;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.uuid != other.uuid && (this.uuid == null || !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.uuid != null ? this.uuid.hashCode() : 0;
    }



}
