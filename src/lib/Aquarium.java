package lib;
import java.util.Random;

public class Aquarium {
	private int width;
	private int height;
	private int coin_val;
	
	private static final int col_radius = 20;
	
	/* public List<Guppy> Guppies;
	   public List<Piranha> Piranhas;
	   public List<Coin> Coins;
	   public List<FishFood> FishFoods;
	   public Siput Snail */
	
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
	
	public void Initialize() {
		Random rng = new Random();
		// Guppies.append(new Guppy(rng.nextFloat() % this.getWidth(), rng.nextFloat() % this.getHeight()));
		// Snail = new Snail(rng.nextFloat() % this.getWidth(), this.getHeight());
	}
	
	public void CreateNewObject(char obj) {
		Random rng = new Random();
		//double x = rng.nextFloat() % this.getWidth();
		//double y = rng.nextFloat() % this.getHeight();
		//CreateNewObject(obj,x,y);
	}
	
	public void CreateNewObject(char obj, double x, double y) {
		if (obj == 'F') {
			//FishFoods.append(new FishFood(x,y));
		}
		else if (obj == 'G') {
			//Guppies.append(new Guppy(x,y));
		}
		else if (obj == 'P') {
			//Piranhas.append(new Piranha(x,y));
		}
	}
	
	/*
	private void KeepOnAquarium(AqObject obj) {
		if (obj.getX() < 0) {
			obj.setX(0);
		}
		else if (obj.getX() > width) {
			obj.setX(width);
		}
		
		if (obj.getY() < 0) {
			obj.setY(0);
		}
		else if (obj.getY() > height) {
			obj.setY(height);
		}
	}
	*/
}
