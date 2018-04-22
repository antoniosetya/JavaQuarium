package lib;

import javax.swing.ImageIcon;
import java.awt.Image;

public class FishFood extends AqObject implements Moveable {
	private static final int FISH_FOOD_SPEED = 20;
	private static final int WIDTH = 21;
	private static final int HEIGHT = 22;
	private Image FFSprite;

	public FishFood() {
		super(0, 0, FISH_FOOD_SPEED, WIDTH, HEIGHT);
		setIsAlive(true);
		loadSprite();
	}

	public FishFood(final double x, final double y) {
		super(x, y, FISH_FOOD_SPEED, WIDTH, HEIGHT);
		setIsAlive(true);
		loadSprite();
	}

	private void loadSprite() {
		ImageIcon temp = new ImageIcon("./assets/FishFood.png");
		FFSprite = temp.getImage();
	}
	
	public boolean isEqual(final FishFood anotherFishFood) {
		return (this.getX() == anotherFishFood.getX()) && (this.getY() == anotherFishFood.getY())
				&& (getIsAlive() == anotherFishFood.getIsAlive());
	}

	public void move(final double sec) {
		setY(getY() + (getSpeed() * sec));
		updateHitBox();
	}

	public void timeHasPassed(final double dtime) {
		this.move(dtime);
	}

	public void eaten() {
		setIsAlive(false);
	}
	
	public Image draw() {
		return FFSprite;
	}
}
