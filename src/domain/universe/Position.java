/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.universe;

/**
 * Imutable position class
 * @author Felipe
 */

public class Position {
    public static final double TOUCH_RANGE = 0.01;

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Position other) {
        return Math.sqrt(Math.pow(x - other.getX(),2) + Math.pow(y - other.getY(),2));
    }

    public boolean inRange(Position other, double range) {
        return distance(other) <= range;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (Math.abs(this.x-x) > 0.01) {
            return false;
        }
        if (Math.abs(this.y-y) > 0.01) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.format("X:%2.2f   Y:%2.2f", x,y);
    }


}
