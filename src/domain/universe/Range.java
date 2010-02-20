/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.universe;

/**
 *
 * @author felipefarias
 */
public enum Range {
    TOUCH(0.01),
    VISUAL(1),
    COMBAT(10),
    FAR(100);

    private double distance;

    private Range(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

}
