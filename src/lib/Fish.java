// File		    : Fish.java
// Author	    : 13516122 - Manasye
// Created on   : 15 April 2018
// Updated by   :

import java.util.Random;
import java.lang.*;

public class Fish extends AqObject implements Moveable{
    protected int NumEaten; // Fish has eaten how many times
    protected char facing;
    protected boolean FishFull; // Guppy is full or not
    protected int degOfMovement; // Stores degree of movement [0..359]
    protected double TimeBeforeHungry, TimeBeforeDying, TimeToRandomize; // Timers
    protected double toX, toY; // If this is set, Fish will move towards X and Y

    // Constructor
    public Fish(double x, double y, double z) {
        super(x, y, z);
        NumEaten = 0;
        TimeToRandomize = 0;
        FishFull = true;
        isAlive = true;
        facing = 'r';
        setTimeBeforeHungry(15);
        setTimeBeforeDying(20);
        toX = -1;
        toY = -1;
    }

    // Getter
    public int getNumEaten() {
        return NumEaten;
    }

    public char getFacing() {
        return facing;
    }

    public boolean isFishFull() {
        return FishFull;
    }

    public int getDegOfMovement() {
        return degOfMovement;
    }

    public double getTimeBeforeHungry() {
        return TimeBeforeHungry;
    }

    public double getTimeBeforeDying() {
        return TimeBeforeDying;
    }

    public double getToX() {
        return toX;
    }

    public double getToY() {
        return toY;
    }

    // Setter
    public void setNumEaten(int numEaten) {
        NumEaten = numEaten;
    }

    public void setFacing(char facing) {
        this.facing = facing;
    }

    public void setFishFull(boolean fishFull) {
        FishFull = fishFull;
    }

    public void setDegOfMovement(int degOfMovement) {
        this.degOfMovement = degOfMovement;
    }

    public void setTimeBeforeHungry(double timeBeforeHungry) {
        TimeBeforeHungry = timeBeforeHungry;
    }

    public void setTimeBeforeDying(double timeBeforeDying) {
        TimeBeforeDying = timeBeforeDying;
    }

    public void setTimeToRandomize(double timeToRandomize) {
        TimeToRandomize = timeToRandomize;
    }

    public void setToX(double toX) {
        this.toX = toX;
    }

    public void setToY(double toY) {
        this.toY = toY;
    }

    public void moveTowards(double x, double y) {
        setToX(x);
        setToY(y);
    }

    public void moveRandomly() {
        setToX(-1);
        setToY(-1);
    }

    public void countdownHungry(double dtime) {
        if(TimeBeforeHungry <= 0) {
            FishFull = false;
        } else {
            TimeBeforeHungry -= dtime;
        }
    }

    public void countdownDying(double dtime) {
        if(TimeBeforeDying <= 0) {
            isAlive = false; // Kill the fish
        } else {
            TimeBeforeDying -= dtime;
        }
    }

    public void move(double timePassed) {
        double curDegOfMovement;
        if ((toX != -1) && !FishFull) { // Fish shall move towards (toX, toY) only if it's hungry
            curDegOfMovement = Math.atan2(toY - getY(), toX - getX());
            if (Math.toRadians(curDegOfMovement) >= 90 && Math.toRadians(curDegOfMovement) <= 270)
                setFacing('l');
            else
                setFacing('r');
            this.TimeToRandomize = 0;
        }
        else {
            TimeToRandomize -= timePassed;
            if (TimeToRandomize <= 0)
            {   // Time to change face
                Random rand = new Random();
                // Randomize direction
                int newDirection = rand.nextInt(360);
                setDegOfMovement(newDirection);
                // Set facing of the fish
                if (newDirection >= 90 && newDirection <= 270)
                    setFacing('l');
                else
                    setFacing('r');
                this.ResetRandomTime();
            }
            curDegOfMovement = Math.toRadians(degOfMovement);
        }
        // Set movement based on direction
        setX(getX() + getSpeed() * Math.cos(curDegOfMovement) * timePassed);
        setY(getY() + getSpeed() * Math.sin(curDegOfMovement) * timePassed);
    }

    public void timeHasPassed(double dtime) {
        this.move(dtime);
        if (FishFull) {
            this.countdownHungry(dtime);
        }
        else {
            this.countdownDying(dtime);
        }
    }
}

