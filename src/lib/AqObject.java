// File		    : Object.java
// Author	    : 13516122 - Manasye
// Created on   : 15 April 2018
// Updated by   :

public abstract class AqObject {
    // movement speed of an object
    protected final double speed;
    protected double x, y; // AqObject position
    protected int TimeToRandomize;
    protected boolean isAlive;

    public AqObject(double x, double y, double s) {
        this.x = x;
        this.y = y;
        this.speed = s;
        this.TimeToRandomize = 0;
        this.isAlive = true;
    }

    // Getter
    public double getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTimeToRandomize() {
        return TimeToRandomize;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    // Setter
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTimeToRandomize(int timeToRandomize) {
        TimeToRandomize = timeToRandomize;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public abstract void timeHasPassed(double x);

    public void ResetRandomTime() {
        this.TimeToRandomize = 1;
    }
}