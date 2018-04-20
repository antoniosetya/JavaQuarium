package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.lang.Math;

public class Piranha extends Fish {
	private static final int PIRANHA_SPEED = 40;
	private static final int GUPPY_PRICE = 100;
	private static final int HALF_FULL_DEGREE = 180;
	private int degOfMovement;
	private JLabel PirSprite = new JLabel();

	// Constructor
	public Piranha() {
		super(0, 0, PIRANHA_SPEED);
		this.degOfMovement = HALF_FULL_DEGREE;
	}

	public Piranha(final double x, final double y) {
		super(x, y, PIRANHA_SPEED);
		this.degOfMovement = HALF_FULL_DEGREE;
	}

	// Getter
	public int getDegOfMovement() {
		return degOfMovement;
	}

	// Setter
	public void setDegOfMovement(final int degree) {
		this.degOfMovement = degree;
	}

	// Method for piranha eating guppy
	// public Coin eatGuppy(Guppy g) {
	// g.eaten();
	// setFishFull(true);
	// setTimeBeforeHungry(15);
	// setTimeBeforeDying(20);
	// // Create new coin
	// Coin c1 = new Coin(this.getX(), this.getY(), GUPPY_PRIDE *
	// (g.getGrowthStage() + 1));
	// return c1;
	// }
	
	@Override
	public void draw(JLabel destination) {
		String state, filename;
		if (this.isFishFull()) {
			state = "n";
		}
		else {
			state = "h";
		}
		filename = "Piranha_" + state + "_" + this.getFacing() + ".png";
		try {
			BufferedImage sprite = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/" + filename));
			PirSprite.setIcon(new ImageIcon(sprite));
			PirSprite.setBounds((int)Math.round(this.getX()) - 42,(int)Math.round(this.getY()) - 37,84,73);
			destination.add(PirSprite);
		}
		catch (IOException ex) {
			System.out.println("Cannot load sprite Piranha " + state + " " + this.getFacing());
			ex.printStackTrace();
		}
	}
}
