package lib;

import java.util.Random;
import javax.swing.JLabel;

/**
 * The type Fish.
 */
public class Fish extends AqObject implements Moveable {

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
	 * Variables describing fish go to what coordinates.
	 */
	private double toX, toY;

	/**
	 * Instantiates a new Fish.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 */
	public Fish(final double x, final double y, final double z) {
		super(x, y, z);
		numEaten = 0;
		timeToRandomize = 0;
		fishFull = true;
		setIsAlive(true);
		facing = 'r';
		setTimeBeforeHungry(INIT_HUNGRY_TIME);
		setTimeBeforeDying(INIT_DYING_TIME);
		toX = -1;
		toY = -1;
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
	 * Gets to x.
	 *
	 * @return the to x
	 */
	public double getToX() {
		return toX;
	}

	/**
	 * Gets to y.
	 *
	 * @return the to y
	 */
	public double getToY() {
		return toY;
	}

	/**
	 * Sets num eaten.
	 *
	 * @param numOfEaten
	 *            the num of eaten
	 */

	public void setNumEaten(final int numOfEaten) {
		this.numEaten = numOfEaten;
	}

	/**
	 * Sets facing.
	 *
	 * @param face
	 *            the face
	 */
	public void setFacing(final char face) {
		this.facing = face;
	}

	/**
	 * Sets fish full.
	 *
	 * @param full
	 *            the full
	 */
	public void setFishFull(final boolean full) {
		this.fishFull = full;
	}

	/**
	 * Sets deg of movement.
	 *
	 * @param degree
	 *            the degree
	 */
	public void setDegOfMovement(final int degree) {
		this.degOfMovement = degree;
	}

	/**
	 * Sets time before hungry.
	 *
	 * @param time
	 *            the time
	 */
	public void setTimeBeforeHungry(final double time) {
		this.timeBeforeHungry = time;
	}

	/**
	 * Sets time before dying.
	 *
	 * @param time
	 *            the time
	 */
	public void setTimeBeforeDying(final double time) {
		this.timeBeforeDying = time;
	}

	/**
	 * Sets time to randomize.
	 *
	 * @param time
	 *            the time
	 */
	public void setTimeToRandomize(final double time) {
		this.timeToRandomize = time;
	}

	/**
	 * Sets to x.
	 *
	 * @param x
	 *            the x
	 */
	public void setToX(final double x) {
		this.toX = x;
	}

	/**
	 * Sets to y.
	 *
	 * @param y
	 *            the y
	 */
	public void setToY(final double y) {
		this.toY = y;
	}

	/**
	 * Move towards.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void moveTowards(final double x, final double y) {
		setToX(x);
		setToY(y);
	}

	/**
	 * Move randomly.
	 */
	public void moveRandomly() {
		setToX(-1);
		setToY(-1);
	}

	/**
	 * Countdown hungry.
	 *
	 * @param dtime
	 *            the dtime
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
	 * @param dtime
	 *            the dtime
	 */
	public void countdownDying(final double dtime) {
		if (timeBeforeDying <= 0) {
			setIsAlive(false); // Kill the fish
		} else {
			timeBeforeDying -= dtime;
		}
	}

	/**
	 * @param timePassed
	 *            refers to time already passed
	 */
	public void move(final double timePassed) {
		double curDegOfMovement;
		if ((toX != -1) && !fishFull) {
			curDegOfMovement = Math.atan2(toY - getY(), toX - getX());
			if (Math.toRadians(curDegOfMovement) >= QUARTER_DEGREE
					&& Math.toRadians(curDegOfMovement) <= THREE_QUART_DEGREE) {
				setFacing('l');
			} else {
				setFacing('r');
			}
			this.timeToRandomize = 0;
		} else {
			timeToRandomize -= timePassed;
			if (timeToRandomize <= 0) {
				Random rand = new Random();
				// Randomize direction
				int newDirection = rand.nextInt(FULL_QUART_DEGREE);
				setDegOfMovement(newDirection);
				// Set facing of the fish
				if (newDirection >= QUARTER_DEGREE && newDirection <= THREE_QUART_DEGREE) {
					setFacing('l');
				} else {
					setFacing('r');
				}
				this.resetRandomTime();
			}
			curDegOfMovement = Math.toRadians(degOfMovement);
		}
		// Set movement based on direction
		setX(getX() + getSpeed() * Math.cos(curDegOfMovement) * timePassed);
		setY(getY() + getSpeed() * Math.sin(curDegOfMovement) * timePassed);
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
	
	public void draw (JLabel destination) {
		
	}
}
