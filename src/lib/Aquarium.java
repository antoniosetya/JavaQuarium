package lib;

import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import java.lang.Math;

public class Aquarium {
	private int width;
	private int height;
	
	private List<Piranha> Piranhas = new List<Piranha>();
	private List<Guppy> Guppies = new List<Guppy>();
	private List<FishFood> FishFoods = new List<FishFood>();
	private List<Coin> Coins = new List<Coin>();
	/* private Siput Snail */

	// Constructors
	public Aquarium() {
		width = 640;
		height = 480;
	}

	public Aquarium(int width, int height) {
		this.width = width;
		this.height = height;
	}
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public List<Piranha> getPiranhas() {
		return Piranhas;
	}

	public List<Guppy> getGuppies() {
		return Guppies;
	}

	public List<FishFood> getFishFoods() {
		return FishFoods;
	}

	public List<Coin> getCoins() {
		return Coins;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void initialize() {
		Random rng = new Random();
		Guppies.append(new Guppy(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1)));
		//Piranhas.append(new Piranha(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1)));
		//Coins.append(new Coin(rng.nextInt(this.getWidth() + 1), rng.nextInt(this.getHeight() + 1),100));
		//Snail = new Snail(rng.nextFloat() % this.getWidth(), this.getHeight());
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
	
	private static double Euclidean(double x1, double y1, double x2, double y2) {
		return(Math.sqrt(Math.pow(x2 - x1,2) + Math.pow(y2 - y1,2)));
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
		int i, j, optLoc;
		double shortest, temp;
		Coin droppedCoin;
		/* --- INVOKING timeHasPassed --- */
		// Invoke timeHasPassed for Guppy
		for (i = 0; i < Guppies.getSize(); i++) {
			if (Guppies.get(i) != null) {
				// Determines if there's nearby fish foods and Guppy is hungry
				if (!Guppies.get(i).isFishFull() && !FishFoods.isEmpty()) {
					j = 0;
					optLoc = 0;
					shortest = Euclidean(Guppies.get(i).getX(), Guppies.get(i).getY(),
							FishFoods.get(j).getX(), FishFoods.get(j).getY());
					for (j = 1;j < FishFoods.getSize(); j++) {
						temp = Euclidean(Guppies.get(i).getX(), Guppies.get(i).getY(),
								FishFoods.get(j).getX(), FishFoods.get(j).getY());
						if (shortest > temp) {
							shortest = temp;
							optLoc = j;
						}
					}
					Guppies.get(i).moveTowards(FishFoods.get(optLoc));
				}
				// Move the Guppy
				Guppies.get(i).timeHasPassed(sec);
				keepOnAquarium(Guppies.get(i));
				// Special for Guppy : dropping coins every fixed interval
				droppedCoin = Guppies.get(i).countdownCoin(sec);
				if (droppedCoin != null) {
					Coins.append(droppedCoin);
				}
			}
			else {
				break;
			}
		}
		// Invoke timeHasPassed for Piranhas
		for (i = 0; i < Piranhas.getSize(); i++) {
			if (Piranhas.get(i) != null) {
				// Determines if there's nearby Guppy and Piranha is hungry
				if (!Piranhas.get(i).isFishFull() && !Guppies.isEmpty()) {
					j = 0;
					optLoc = 0;
					shortest = Euclidean(Piranhas.get(i).getX(), Piranhas.get(i).getY(),
							Guppies.get(j).getX(), Guppies.get(j).getY());
					for (j = 1;j < FishFoods.getSize(); j++) {
						temp = Euclidean(Piranhas.get(i).getX(), Piranhas.get(i).getY(),
								Guppies.get(j).getX(), Guppies.get(j).getY());
						if (shortest > temp) {
							shortest = temp;
							optLoc = j;
						}
					}
					Piranhas.get(i).moveTowards(Guppies.get(optLoc));
				}
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
		
		/* --- COLLISION DETECTION --- */
		// For Guppy and FishFood
		for (i = 0;i < Guppies.getSize();i++) {
			if (!Guppies.get(i).isFishFull()) {
				// Loop FishFood to see whether a collision has happened
				for (j = 0;j < FishFoods.getSize();j++) {
					if (FishFoods.get(j).getIsAlive() && Guppies.get(i).getHitBox().intersects(FishFoods.get(j).getHitBox())) {
						Guppies.get(i).eat();
						FishFoods.get(j).eaten();
						break;
					}
				}
			}
		}
		
		// For Piranhas and Guppy
		for (i = 0;i < Piranhas.getSize();i++) {
			if (!Piranhas.get(i).isFishFull()) {
				// Loop FishFood to see whether a collision has happened
				for (j = 0;j < Guppies.getSize();j++) {
					if (Guppies.get(j).getIsAlive() && Piranhas.get(i).getHitBox().intersects(Guppies.get(j).getHitBox())) {
						droppedCoin = Piranhas.get(i).eatGuppy(Guppies.get(j));
						Coins.append(droppedCoin);
						Guppies.get(i).eaten();
						break;
					}
				}
			}
		}
		/* --- "GARBAGE CLEANER" -- */
		cleanList(Guppies);
		cleanList(Piranhas);
		cleanList(FishFoods);
		cleanList(Coins);
	}
	
	public int CollectCoin(int x, int y) {
		int value = -1;
		boolean stop = false;
		int i = 0;
		while ((i < Coins.getSize()) && !stop) {
			if (Coins.get(i).getHitBox().contains(x,y)) {
				value = Coins.get(i).getValue();
				Coins.get(i).collected();
				stop = true;
			}
			else {
				i++;
			}
		}
		return value;
	}
}