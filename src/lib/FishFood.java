// File		    : FishFood.java
// Author	    : 13516122 - Manasye
// Created on   : 15 April 2018
// Updated by   :

public class FishFood extends AqObject implements Moveable{
    private static final int fishFoodSpeed = 20;

    public FishFood() {
        super(0, 0, fishFoodSpeed);
        isAlive = true;
    }

    public FishFood(double x, double y) {
        super(x, y, fishFoodSpeed);
        isAlive = true;
    }

    public boolean isEqual(FishFood FF) {
        return (this.getX() == FF.getX()) && (this.getY() == FF.getY()) && (isAlive == FF.isAlive);
    }

    public void move(double sec) {
        setY(getY() + (speed * sec));
    }

    public void timeHasPassed(double dtime) {
        this.move(dtime);
    }

    public void eaten() {
        isAlive = false;
    }
}
