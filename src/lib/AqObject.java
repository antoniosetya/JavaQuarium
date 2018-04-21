package lib;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class AqObject {
	private static final int HITBOXOFFSET = 4;
	protected final double speed;
	protected double x, y; // AqObject position
	protected int width, height;
	protected boolean isAlive;
	protected Rectangle hitbox;

	public AqObject(final double absis, final double ordinat, final double s, final int width, final int height) {
		this.x = absis;
		this.y = ordinat;
		this.speed = s;
		this.isAlive = true;
		this.width = width;
		this.height = height;
		hitbox = new Rectangle((int)(x - ((width - HITBOXOFFSET) / 2)),
				(int)(y - ((height - HITBOXOFFSET)/2)),
				width - HITBOXOFFSET,
				height - HITBOXOFFSET);
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

	public boolean getIsAlive() {
		return isAlive;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	// Setter
	public void setX(final double absis) {
		this.x = absis;
	}

	public void setY(final double ordinat) {
		this.y = ordinat;
	}

	public void setIsAlive(final boolean alive) {
		this.isAlive = alive;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void timeHasPassed(double absis);

	public abstract Image draw();
	
	public void updateHitBox() {
		hitbox.setBounds((int)(x - ((width - HITBOXOFFSET) / 2)),
				(int)(y - ((height - HITBOXOFFSET)/2)),
				width - HITBOXOFFSET,
				height - HITBOXOFFSET);
	}
	
    public Rectangle getHitBox() {
    	return hitbox;
    }
}
