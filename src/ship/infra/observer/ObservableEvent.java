/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.infra.observer;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public enum ObservableEvent {
    TIME_ELAPSED,
    SHIP_ADDED,
    SHIP_REMOVED,
    SHIP_MOVED,
    SHIP_SRS_DETECTED_OBJECT, // SRS = Short Range Sensor
    POWER_GRID_UPDATE,
    SHIELD_UPDATE,
    BATTERY_UPDATE,
    COMPUTER_OUTPUT,
    COMPUTER_BUTTONS;
}
