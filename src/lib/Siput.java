package lib;

import java.awt.*;
import java.util.Random;

public abstract class Siput extends AqObject implements Moveable {
    private static final int BINARY_DIRECTION = 2;
    private static final int LEFT_DIRECTION = 1;
    private static final int SNAIL_SPEED = 25;

    private char facing;
    private int degOfMovement;
    private double toX, toY;
    private double timeToRandomize;

    public Siput(final int x, final int y, final int width, final int height){
        super(x, y, SNAIL_SPEED, width, height);
        this.toX = -1;
        this.toY = -1;
    }

    public int getDegOfMovement() {
        return degOfMovement;
    }

    public void setDegOfMovement(final int degOfMovement) {
        this.degOfMovement = degOfMovement;
    }

    public double getToX() {
        return toX;
    }

    public void setToX(final double toX) {
        this.toX = toX;
    }

    public double getToY() {
        return toY;
    }

    public void setToY(final double toY) {
        this.toY = toY;
    }

    public char getFacing() {
        return facing;
    }

    public void setFacing(final char facing) {
        this.facing = facing;
    }

    public double getTimeToRandomize() {
        return timeToRandomize;
    }

    public void setTimeToRandomize(final double timeToRandomize) {
        this.timeToRandomize = timeToRandomize;
    }

    public void resetRandomTime(){
        this.timeToRandomize = 1;
    }

    public void moveTowards(final double x, final double y){
        this.toX = x;
        this.toY = y;
    }

    public void moveRandomly(){
        this.toX = -1;
        this.toY = -1;
    }

    public void move(final double timePassed) {
        double curDegOfMovement;
        if (toX != -1) { // Snail shall move towards (toX, toY)
            curDegOfMovement = Math.atan2(toY - getY(), toX - getX());
            if ((curDegOfMovement * 57.2958 >= 90) && (curDegOfMovement * 57.2958 <= 270)) {
                setFacing('l');
            } else {
                setFacing('r');
            }
            setTimeToRandomize(0);
        } else {
            setTimeToRandomize(getTimeToRandomize() - timePassed);
            if (getTimeToRandomize() <= 0) {
                Random rand = new Random();
                // Randomize direction
                int newDirection = rand.nextInt(BINARY_DIRECTION);
                setDegOfMovement(newDirection);
                // Set facing of the fish
                if (newDirection == LEFT_DIRECTION) {
                    setFacing('l');
                } else {
                    setFacing('r');
                }
                this.resetRandomTime();
            }
            curDegOfMovement = Math.toRadians(degOfMovement);
        }
        // Set movement based on direction
        setX(getX() + (getSpeed() * Math.cos(curDegOfMovement) * timePassed));
    }

    public void timeHasPassed(double dTime){
        this.move(dTime);
    }

    public abstract Image draw();
}
