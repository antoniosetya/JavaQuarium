package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Aquarium {
	private int width;
	private int height;
	private int coin_val;
	private JLabel background;
	
	/*
	 * private List<Guppy> Guppies; private List<Piranha> Piranhas; private List<Coin>
	 * Coins; private List<FishFood> FishFoods; private Siput Snail
	 */

	private static final int col_radius = 20;

	// Constructors
	public Aquarium() {
		width = 640;
		height = 480;
		coin_val = 150;
		// Initializing background label
		try {
			BufferedImage back_image = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/background.jpg"));
			background = new JLabel(new ImageIcon(back_image));
		}
		catch (IOException ex) {
			System.out.println("Cannot open image!");
		}	
	}

	public Aquarium(int width, int height) {
		this.width = width;
		this.height = height;
		coin_val = 150;
		try {
			BufferedImage back_image = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/background.jpg"));
			background = new JLabel(new ImageIcon(back_image));
		}
		catch (IOException ex) {
			System.out.println("Cannot open image!");
		}	
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCoinVal() {
		return coin_val;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setCoinVal(int coin_val) {
		this.coin_val = coin_val;
	}

	public void initialize() {
		Random rng = new Random();
		// Guppies.append(new Guppy(rng.nextFloat() % this.getWidth(), rng.nextFloat() %
		// this.getHeight()));
		// Snail = new Snail(rng.nextFloat() % this.getWidth(), this.getHeight());
	}

	public void createNewObject(char obj) {
		Random rng = new Random();
		double x = rng.nextFloat() % this.getWidth();
		double y = rng.nextFloat() % this.getHeight();
		// createNewObject(obj,x,y);
	}

	public void createNewObject(char obj, double x, double y) {
		if (obj == 'F') {
			// FishFoods.append(new FishFood(x,y));
		} else if (obj == 'G') {
			// Guppies.append(new Guppy(x,y));
		} else if (obj == 'P') {
			// Piranhas.append(new Piranha(x,y));
		}
	}
	
	public void drawAquarium(JPanel destination) {
		destination.add(background);
		/* int i;
		 * // Invoke Draw methods for each
		 * for (i = 0;i < 
		 */
	}

	/*
	 * private void keepOnAquarium(AqObject obj) { if (obj.getX() < 0) {
	 * obj.setX(0); } else if (obj.getX() > width) { obj.setX(width); }
	 * 
	 * if (obj.getY() < 0) { obj.setY(0); } else if (obj.getY() > height) {
	 * obj.setY(height); } }
	 */
}
