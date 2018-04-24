package arkavQuarium.lib;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;

/**
 * The type Siput.
 */
public class Siput extends AqObject implements Moveable {
    private static final int BINARY_DIRECTION = 2;
    private static final int LEFT_DIRECTION = 1;
    private static final int SNAIL_SPEED = 40;
    private static final int WIDTH = 76;
    private static final int HEIGHT = 50;
    private static final int FIFTEEN = 15;
    private static final int TWENTY = 20;
    /**
     * Constants for 270 degrees.
     */
    private static final int THREE_QUART_DEGREE = 270;
    /**
     * Constants for 180 degrees.
     */
    private static final int HALF_FULL_DEGREE = 180;
    /**
     * Constants for 90 degrees.
     */
    private static final int QUARTER_DEGREE = 90;

    private char facing;
    private int degOfMovement;
    private double timeToRandomize;
    private AqObject toObj;
    private Image sipSprite;

    /**
     * Instantiates a new Siput.
     *
     * @param x the x
     * @param y the y
     */
    public Siput(final double x, final double y) {
        super(x, y, SNAIL_SPEED, WIDTH, HEIGHT);
        this.toObj = null;
        this.facing = 'l';
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
     * Sets deg of movement.
     *
     * @param d the d
     */
    public void setDegOfMovement(final int d) {
        this.degOfMovement = d;
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
     * Sets facing.
     *
     * @param f the f
     */
    public void setFacing(final char f) {
        this.facing = f;
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
     * Sets time to randomize.
     *
     * @param t the t
     */
    public void setTimeToRandomize(final double t) {
        this.timeToRandomize = t;
    }

    /**
     * Reset random time.
     */
    public void resetRandomTime() {
        this.timeToRandomize = 1;
    }

    /**
     * Gets to obj.
     *
     * @return the to obj
     */
    public AqObject getToObj() {
        return toObj;
    }

    /**
     * Sets to obj.
     *
     * @param t the t
     */
    public void setToObj(final AqObject t) {
        this.toObj = t;
    }

    /**
     * Move towards.
     *
     * @param t the t
     */
    public void moveTowards(final AqObject t) {
        this.toObj = t;
    }

    /**
     * Move randomly.
     */
    public void moveRandomly() {
        this.toObj = null;
    }

    public void move(final double timePassed) {
        double curDegOfMovement;
        if (toObj != null) { // Snail shall move towards toObj
            if (toObj.getIsAlive()) {
                curDegOfMovement = Math.atan2(toObj.getY() - getY(),
                        toObj.getX() - getX());
                if ((Math.abs(Math.toDegrees(curDegOfMovement))
                        >= QUARTER_DEGREE)
                        && (Math.abs(Math.toDegrees(curDegOfMovement))
                        <= THREE_QUART_DEGREE)) {
                    setFacing('l');
                } else {
                    setFacing('r');
                }
                setTimeToRandomize(0);
            } else {
                moveRandomly();
                curDegOfMovement = Math.toRadians(getDegOfMovement());
            }
        } else {
            setTimeToRandomize(getTimeToRandomize() - timePassed);
            if (getTimeToRandomize() <= 0) {
                Random rand = new Random();
                // Randomize direction
                int newDirection = rand.nextInt(BINARY_DIRECTION);
                // Set facing of the fish
                if (newDirection == LEFT_DIRECTION) {
                    setFacing('l');
                    degOfMovement = HALF_FULL_DEGREE;
                } else {
                    setFacing('r');
                    degOfMovement = 0;
                }
                this.resetRandomTime();
            }
            curDegOfMovement = Math.toRadians(degOfMovement);
        }
        // Set movement based on direction
        setX(getX() + (getSpeed() * Math.cos(curDegOfMovement) * timePassed));
        updateHitBox();
    }

    public void timeHasPassed(final double dTime) {
        this.move(dTime);
    }

    public Image draw() {
        String filename = "Snail_" + getFacing() + ".png";
        sipSprite = (new ImageIcon("./assets/" + filename)).getImage();
        return sipSprite;
    }
}
