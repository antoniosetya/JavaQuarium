import java.util.Random;

public class Fish extends AqObject implements Moveable {
    private static final int INIT_HUNGRY_TIME = 15;
    private static final int INIT_DYING_TIME = 20;
    private static final int QUARTER_DEGREE = 90;
    private static final int THREE_QUART_DEGREE = 270;
    private static final int FULL_QUART_DEGREE = 360;
    private int numEaten; // Fish has eaten how many times
    private char facing;
    private boolean fishFull; // Guppy is full or not
    private int degOfMovement; // Stores degree of movement [0..359]
    private double timeBeforeHungry, timeBeforeDying, timeToRandomize; // Timers
    private double toX, toY; // If this is set, Fish will move towards X and Y

    // Constructor
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

    // Getter
    public int getNumEaten() {
        return numEaten;
    }

    public char getFacing() {
        return facing;
    }

    public boolean isFishFull() {
        return fishFull;
    }

    public int getDegOfMovement() {
        return degOfMovement;
    }

    public double getTimeBeforeHungry() {
        return timeBeforeHungry;
    }

    public double getTimeBeforeDying() {
        return timeBeforeDying;
    }

    public double getToX() {
        return toX;
    }

    public double getToY() {
        return toY;
    }

    // Setter
    public void setNumEaten(final int numOfEaten) {
        this.numEaten = numOfEaten;
    }

    public void setFacing(final char face) {
        this.facing = face;
    }

    public void setFishFull(final boolean full) {
        this.fishFull = full;
    }

    public void setDegOfMovement(final int degree) {
        this.degOfMovement = degree;
    }

    public void setTimeBeforeHungry(final double time) {
        this.timeBeforeHungry = time;
    }

    public void setTimeBeforeDying(final double time) {
        this.timeBeforeDying = time;
    }

    public void setTimeToRandomize(final double time) {
        this.timeToRandomize = time;
    }

    public void setToX(final double x) {
        this.toX = x;
    }

    public void setToY(final double y) {
        this.toY = y;
    }

    public void moveTowards(final double x, final double y) {
        setToX(x);
        setToY(y);
    }

    public void moveRandomly() {
        setToX(-1);
        setToY(-1);
    }

    public void countdownHungry(final double dtime) {
        if (timeBeforeHungry <= 0) {
            fishFull = false;
        } else {
            timeBeforeHungry -= dtime;
        }
    }

    public void countdownDying(final double dtime) {
        if (timeBeforeDying <= 0) {
            setIsAlive(false); // Kill the fish
        } else {
            timeBeforeDying -= dtime;
        }
    }

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
        setX(getX() + getSpeed() * Math.cos(curDegOfMovement) * timePassed);
        setY(getY() + getSpeed() * Math.sin(curDegOfMovement) * timePassed);
    }

    public void timeHasPassed(final double dtime) {
        this.move(dtime);
        if (fishFull) {
            this.countdownHungry(dtime);
        } else {
            this.countdownDying(dtime);
        }
    }
}

