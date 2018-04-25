package arkavquarium.lib;

import javax.swing.ImageIcon;

import arkavquarium.Main;

import java.awt.Image;
import java.util.Random;

/**
 * The type Siput.
 */
public class Siput extends AqObject implements Moveable {
    /**
     * Constants for binary direction.
     */
    private static final int BINARY_DIRECTION = 2;
    /**
     * Constants for left direction.
     */
    private static final int LEFT_DIRECTION = 1;
    /**
     * Constants for snail speed.
     */
    private static final int SNAIL_SPEED = 40;
    /**
     * Constants for width.
     */
    private static final int WIDTH = 76;
    /**
     * Constants for height.
     */
    private static final int HEIGHT = 50;
    /**
     * Constants for fifteen.
     */
    private static final int FIFTEEN = 15;
    /**
     * Constants for twenty.
     */
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
    /**
     * Constants for 270 degrees.
     */
    private char facing;
    /**
     * Variable for face of snail.
     */
    private int degOfMovement;
    /**
     * Variable time to randomize.
     */
    private double timeToRandomize;
    /**
     * Variable to object.
     */
    private AqObject toObj;
    /**
     * Variable for snail sprite.
     */
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

    /**
     * Move when certain time has passed.
     *
     * @param timePassed refers to sec time has passed
     */
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

    /**
     * Implementation of time has passed.
     *
     * @param dTime sec of time has passed
     */
    public void timeHasPassed(final double dTime) {
        this.move(dTime);
    }

    /**
     * Draw the image.
     *
     * @return image drawn
     */
    public Image draw() {
        String filename = "assets/Snail_" + getFacing() + ".png";
        filename = Main.class.getClassLoader().getResource(filename).getPath();
    	try {
    		filename = java.net.URLDecoder.decode(filename, "UTF-8");
    	}
    	catch (Exception e) {
    		System.out.println("Failed to load Siput assets...");
    	}
        sipSprite = (new ImageIcon(filename)).getImage();
        return sipSprite;
    }
}
