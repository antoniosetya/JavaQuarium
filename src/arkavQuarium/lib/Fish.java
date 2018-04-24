package arkavquarium.lib;

import java.util.Random;
import java.awt.Image;

/**
 * The type Fish.
 */
public abstract class Fish extends AqObject implements Moveable {

    /**
     * Constants for Initial Hungry Time.
     */
    private static final int INIT_HUNGRY_TIME = 15;
    /**
     * Constants for Initial Dying Time.
     */
    private static final int INIT_DYING_TIME = 20;
    /**
     * Constants for 90 degrees.
     */
    private static final int QUARTER_DEGREE = 90;
    /**
     * Constants for 270 degrees.
     */
    private static final int THREE_QUART_DEGREE = 270;
    /**
     * Constants for 360 degrees.
     */
    private static final int FULL_QUART_DEGREE = 360;
    /**
     * Variables describing fish have eaten how many times.
     */
    private int numEaten;
    /**
     * Variables describing face of the fish.
     */
    private char facing;
    /**
     * Variables describing is fish in a non-hungry state.
     */
    private boolean fishFull;
    /**
     * Variables describing degree of movement of fish.
     */
    private int degOfMovement;
    /**
     * Variables describing time needed for move and hunger mechanism.
     */
    private double timeBeforeHungry, timeBeforeDying, timeToRandomize;
    /**
     * Variables describing fish to go to what object.
     */
    private AqObject toObj;

    /**
     * Instantiates a new Fish.
     *
     * @param x      the absis
     * @param y      the ordinat
     * @param z      the speed
     * @param width  the width
     * @param height the height
     */
    public Fish(final double x, final double y, final double z,
                final int width, final int height) {
        super(x, y, z, width, height);
        numEaten = 0;
        fishFull = true;
        setIsAlive(true);
        facing = 'r';
        setTimeBeforeHungry(INIT_HUNGRY_TIME);
        setTimeBeforeDying(INIT_DYING_TIME);
        moveRandomly();
    }

    /**
     * Gets num eaten.
     *
     * @return the num eaten
     */
    public int getNumEaten() {
        return numEaten;
    }

    /**
     * Gets facing.
     *
     * @return the facing
     */
    public char getFacing() {
        return facing;
    }

    /**
     * Is fish full boolean.
     *
     * @return the boolean
     */
    public boolean isFishFull() {
        return fishFull;
    }

    /**
     * Gets deg of movement.
     *
     * @return the deg of movement
     */
    public int getDegOfMovement() {
        return degOfMovement;
    }

    /**
     * Gets time before hungry.
     *
     * @return the time before hungry
     */
    public double getTimeBeforeHungry() {
        return timeBeforeHungry;
    }

    /**
     * Gets time before dying.
     *
     * @return the time before dying
     */
    public double getTimeBeforeDying() {
        return timeBeforeDying;
    }

    /**
     * Getter for toObj.
     *
     * @return the toObj
     */
    public AqObject getToObj() {
        return toObj;
    }

    /**
     * Gets time to randomize.
     *
     * @return the time to randomize
     */
    public double getTimeToRandomize() {
        return timeToRandomize;
    }

    /**
     * Sets num eaten.
     *
     * @param numOfEaten the num of eaten
     */
    public void setNumEaten(final int numOfEaten) {
        this.numEaten = numOfEaten;
    }

    /**
     * Sets facing.
     *
     * @param face the face
     */
    public void setFacing(final char face) {
        this.facing = face;
    }

    /**
     * Sets fish full.
     *
     * @param full the full
     */
    public void setFishFull(final boolean full) {
        this.fishFull = full;
    }

    /**
     * Sets deg of movement.
     *
     * @param degree the degree
     */
    public void setDegOfMovement(final int degree) {
        this.degOfMovement = degree;
    }

    /**
     * Sets time before hungry.
     *
     * @param time the time
     */
    public void setTimeBeforeHungry(final double time) {
        this.timeBeforeHungry = time;
    }

    /**
     * Sets time before dying.
     *
     * @param time the time
     */
    public void setTimeBeforeDying(final double time) {
        this.timeBeforeDying = time;
    }

    /**
     * Sets time to randomize.
     *
     * @param time the time
     */
    public void setTimeToRandomize(final double time) {
        this.timeToRandomize = time;
    }

    /**
     * Sets toObj.
     *
     * @param obj the obj
     */
    public void setToObj(final AqObject obj) {
        this.toObj = obj;
    }

    /**
     * Move towards.
     *
     * @param obj the obj
     */
    public void moveTowards(final AqObject obj) {
        this.toObj = obj;
    }

    /**
     * Move randomly.
     */
    public void moveRandomly() {
        this.toObj = null;
    }

    /**
     * Countdown hungry.
     *
     * @param dtime the delta time
     */
    public void countdownHungry(final double dtime) {
        if (timeBeforeHungry <= 0) {
            fishFull = false;
        } else {
            timeBeforeHungry -= dtime;
        }
    }

    /**
     * Countdown dying.
     *
     * @param dtime the delta time
     */
    public void countdownDying(final double dtime) {
        if (timeBeforeDying <= 0) {
            setIsAlive(false); // Kill the fish
        } else {
            timeBeforeDying -= dtime;
        }
    }

    /**
     * Reset random time.
     */
    public void resetRandomTime() {
        this.timeToRandomize = 1;
    }

    /**
     * @param timePassed
     *            refers to time already passed
     */
    public void move(final double timePassed) {
        double curDegOfMovement;
        if ((toObj != null) && !fishFull) {
            if (toObj.getIsAlive()) {
                curDegOfMovement =
                        Math.atan2(toObj.getY() - getY(),
                        toObj.getX() - getX());
                if (Math.abs(Math.toDegrees(curDegOfMovement))
                        >= QUARTER_DEGREE
                        && Math.abs(Math.toDegrees(curDegOfMovement))
                        <= THREE_QUART_DEGREE) {
                    setFacing('l');
                } else {
                    setFacing('r');
                }
                this.timeToRandomize = 0;
            } else {
                moveRandomly();
                curDegOfMovement = Math.toRadians(degOfMovement);
            }
        } else {
            setTimeToRandomize(getTimeToRandomize() - timePassed);
            if (getTimeToRandomize() <= 0) {
                Random rand = new Random();
                // Randomize direction
                int newDirection = rand.nextInt(FULL_QUART_DEGREE);
                setDegOfMovement(newDirection);
                // Set facing of the fish
                if (newDirection >= QUARTER_DEGREE
                        && newDirection <= THREE_QUART_DEGREE) {
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
        setY(getY() + (getSpeed() * Math.sin(curDegOfMovement) * timePassed));
        updateHitBox();
    }

    /**
     * @param dtime
     *            refers to time that already passed
     */
    public void timeHasPassed(final double dtime) {
        this.move(dtime);
        if (fishFull) {
            this.countdownHungry(dtime);
        } else {
            this.countdownDying(dtime);
        }
    }

    /**
     *
     * @return
     *          image drawn
     */
    public abstract Image draw();
}
