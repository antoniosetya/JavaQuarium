package lib;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;

public class Siput extends AqObject implements Moveable {
    private static final int BINARY_DIRECTION = 2;
    private static final int LEFT_DIRECTION = 1;
    private static final int SNAIL_SPEED = 40;
    private static final int WIDTH = 76;
    private static final int HEIGHT = 50;

    private char facing;
    private int degOfMovement;
    private double timeToRandomize;
    private AqObject toObj;
    private Image sipSprite;

    public Siput(final double x, final double y){
        super(x, y, SNAIL_SPEED, WIDTH, HEIGHT);
        this.toObj = null;
        this.facing = 'l';
    }

    public int getDegOfMovement() {
        return degOfMovement;
    }

    public void setDegOfMovement(final int degOfMovement) {
        this.degOfMovement = degOfMovement;
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

    public AqObject getToObj() {
		return toObj;
	}

	public void setToObj(AqObject toObj) {
		this.toObj = toObj;
	}

	public void moveTowards(AqObject toObj) {
		this.toObj = toObj;
	}
	
	public void moveRandomly() {
		this.toObj = null;
	}

    public void move(final double timePassed) {
        double curDegOfMovement;
        if (toObj != null) { // Snail shall move towards toObj
        	if (toObj.getIsAlive()) {
        		curDegOfMovement = Math.atan2(toObj.getY() - getY(), toObj.getX() - getX());
        		if ((Math.abs(Math.toDegrees(curDegOfMovement)) >= 90) && (Math.abs(Math.toDegrees(curDegOfMovement)) <= 270)) {
        			setFacing('l');
        		} else {
        			setFacing('r');
        		}
        		setTimeToRandomize(0);
        	}
        	else {
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
                    degOfMovement = 180;
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

    public void timeHasPassed(double dTime){
        this.move(dTime);
    }

    public Image draw() {
    	String filename = "Snail_" + getFacing() + ".png";
    	sipSprite = (new ImageIcon("./assets/" + filename)).getImage();
    	return sipSprite;
    }
}
