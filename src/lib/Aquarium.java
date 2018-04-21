package lib;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Aquarium {
	private int width;
	private int height;
	private int coin_val;
	
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
	}

	public Aquarium(int width, int height) {
		this.width = width;
		this.height = height;
		coin_val = 150;
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
	
	public void drawAquarium(Graphics g, int offset, JPanel io) {
		int i;
		Image temp;
		// Invoke draw on Guppies
		for (i = 0; i < Guppies.getSize(); i++) {
			if (Guppies.get(i) != null) {
				temp = Guppies.get(i).draw();
				g.drawImage(temp,
						(int)(Guppies.get(i).getX() - (temp.getWidth(io) / 2)),
								(int)(Guppies.get(i).getY() - (temp.getHeight(io) / 2) + offset)
								, io);
			}
		}
		// Invoke draw on Piranhas
		for (i = 0; i < Piranhas.getSize(); i++) {
			if (Piranhas.get(i) != null) {
				temp = Piranhas.get(i).draw();
				g.drawImage(temp,
						(int)(Piranhas.get(i).getX() - (temp.getWidth(io) / 2)),
								(int)(Piranhas.get(i).getY() - (temp.getHeight(io) / 2) + offset)
								, io);
			}
		}
		// Invoke draw on FishFoods
		for (i = 0; i < FishFoods.getSize(); i++) {
			if (FishFoods.get(i) != null) {
				temp = FishFoods.get(i).draw();
				g.drawImage(temp,
						(int)(FishFoods.get(i).getX() - (temp.getWidth(io) / 2)),
								(int)(FishFoods.get(i).getY() - (temp.getHeight(io) / 2) + offset)
								, io);
			}
		}
		// Invoke draw on Coins
		for (i = 0; i < Coins.getSize(); i++) {
			if (Coins.get(i) != null) {
				temp = Coins.get(i).draw();
				g.drawImage(temp,
						(int)(Coins.get(i).getX() - (temp.getWidth(io) / 2)),
								(int)(Coins.get(i).getY() - (temp.getHeight(io) / 2) + offset)
								, io);
			}
		}
	}

	public void keepOnAquarium(AqObject ao) {
		if (ao.getX() < 0) ao.setX(0);
		else if(ao.getX() > width) ao.setX(width);
		
		if (ao.getY() < 0) ao.setY(0);
		else if(ao.getY() > height) ao.setY(height);
	}
	
	private void cleanList(List<? extends AqObject> Lao) {
		int i = 0;
		while ((i < Lao.getSize()) && (Lao.get(i) != null)) {
			if (!Lao.get(i).getIsAlive()) {
				Lao.removeAt(i);
			}
			else {
				i++;
			}
		}
	}
	
	public void timeHasPassed(double sec) {
		int i;
		/* --- INVOKING timeHasPassed --- */
		// Invoke timeHasPassed for Guppy
		for (i = 0; i < Guppies.getSize(); i++) {
			if (Guppies.get(i) != null) {
				Guppies.get(i).timeHasPassed(sec);
				keepOnAquarium(Guppies.get(i));
			}
			else {
				break;
			}
		}
		// Invoke timeHasPassed for Piranhas
		for (i = 0; i < Piranhas.getSize(); i++) {
			if (Piranhas.get(i) != null) {
				Piranhas.get(i).timeHasPassed(sec);
				keepOnAquarium(Piranhas.get(i));
			}
			else {
				break;
			}
		}
		// Invoke timeHasPassed for FishFoods
		for (i = 0; i < FishFoods.getSize(); i++) {
			if (FishFoods.get(i) != null) {
				FishFoods.get(i).timeHasPassed(sec);
				if (FishFoods.get(i).getY() >= height) {
					FishFoods.get(i).setIsAlive(false);
				}
			}
			else {
				break;
			}
		}
		// Invoke timeHasPassed for Coins
		for (i = 0; i < Coins.getSize(); i++) {
			if (Coins.get(i) != null) {
				Coins.get(i).timeHasPassed(sec);
				keepOnAquarium(Coins.get(i));
			}
			else {
				break;
			}
		}
		
		/* --- "GARBAGE CLEANER" -- */
		cleanList(Guppies);
		cleanList(Piranhas);
		cleanList(FishFoods);
		cleanList(Coins);
	}
}
