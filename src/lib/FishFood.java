package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class FishFood extends AqObject implements Moveable {
	private static final int FISH_FOOD_SPEED = 20;
	private JLabel FFSprite = new JLabel();

	public FishFood() {
		super(0, 0, FISH_FOOD_SPEED);
		setIsAlive(true);
		loadSprite();
	}

	public FishFood(final double x, final double y) {
		super(x, y, FISH_FOOD_SPEED);
		setIsAlive(true);
		loadSprite();
	}

	private void loadSprite() {
		try {
			BufferedImage sprite = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/FishFood.png"));
			FFSprite.setIcon(new ImageIcon(sprite));
		}
		catch (IOException ex) {
			System.out.println("Cannot load sprite FishFood");
			ex.printStackTrace();
		}
	}
	
	public boolean isEqual(final FishFood anotherFishFood) {
		return (this.getX() == anotherFishFood.getX()) && (this.getY() == anotherFishFood.getY())
				&& (getIsAlive() == anotherFishFood.getIsAlive());
	}

	public void move(final double sec) {
		setY(getY() + (getSpeed() * sec));
	}

	public void timeHasPassed(final double dtime) {
		this.move(dtime);
	}

	public void eaten() {
		setIsAlive(false);
	}
	
	public void draw(JLabel destination) {
		FFSprite.setBounds((int)Math.round(this.getX()) - 20,(int)Math.round(this.getY()) - 20,40,40);
		destination.add(FFSprite);
	}
}
