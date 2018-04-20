package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class Aquarium {
	private int width;
	private int height;
	private int coin_val;
	private JLabel background;
	
	private List<Piranha> Piranhas = new List<Piranha>();
	private List<Guppy> Guppies = new List<Guppy>();
	private List<FishFood> FishFoods = new List<FishFood>();
	private List<Coin> Coins = new List<Coin>();
	/* private Siput Snail */

	private static final int col_radius = 20;

	// Constructors
	public Aquarium() {
		width = 640;
		height = 480;
		coin_val = 150;
		initializeBackground();
	}

	public Aquarium(int width, int height) {
		this.width = width;
		this.height = height;
		coin_val = 150;
		initializeBackground();	
	}
	
	private void initializeBackground() {
		try {
			BufferedImage back_image = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/background.jpg"));
			background = new JLabel(new ImageIcon(back_image));
			background.setBounds(0, 0, width, height);
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
		Guppies.append(new Guppy(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1)));
		Piranhas.append(new Piranha(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1)));
		FishFoods.append(new FishFood(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1)));
		Coins.append(new Coin(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1),100));
		// Snail = new Snail(rng.nextFloat() % this.getWidth(), this.getHeight());
	}

	public void createNewObject(char obj) {
		Random rng = new Random();
		double x = rng.nextInt(this.getWidth() + 1);
		double y = rng.nextInt(this.getHeight() + 1);
		createNewObject(obj,x,y);
	}

	public void createNewObject(char obj, double x, double y) {
		if (obj == 'F') {
			FishFoods.append(new FishFood(x,y));
		} else if (obj == 'G') {
			Guppies.append(new Guppy(x,y));
		} else if (obj == 'P') {
			Piranhas.append(new Piranha(x,y));
		}
	}
	
	public void drawAquarium(JFrame destination) {
		int i;
		// Invoke draw on Guppies
		for (i = 0; i < Guppies.getSize(); i++) {
			Guppies.get(i).draw(background);
		}
		// Invoke draw on Piranhas
		for (i = 0; i < Piranhas.getSize(); i++) {
			Piranhas.get(i).draw(background);
		}
		// Invoke draw on FishFoods
		for (i = 0; i < FishFoods.getSize(); i++) {
			FishFoods.get(i).draw(background);
		}
		// Invoke draw on Coins
		for (i = 0; i < Coins.getSize(); i++) {
			Coins.get(i).draw(background);
		}
		destination.getContentPane().add(background);
	}

	public void keepOnAquarium(AqObject ao) {
		
	}
	
	public void timeHasPassed(double sec) {
		
	}
}
