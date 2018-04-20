package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Guppy extends Fish {
    private static final int GUPPY_SPEED = 35;
    private static int NumGuppy = 0;
    private int GrowthStage;
    private double CoinDropTime;
    private JLabel GupSprite = new JLabel();

    public Guppy() {
        super(0, 0, GUPPY_SPEED);
        setGrowthStage(1);
        setCoinTimeLeft(10);
        NumGuppy++;
    }

    public Guppy(final double x, final double y) {
        super(x, y,GUPPY_SPEED);
        setGrowthStage(1);
        setCoinTimeLeft(10);
        NumGuppy++;
    }

    //Getter & Setter
    public int getGrowthStage() {return this.GrowthStage;}
    public double getCoinTimeLeft() {return this.CoinDropTime;}
    public static int getNumGuppy() {return NumGuppy;}
    public void setGrowthStage(final int growthstage) {this.GrowthStage = growthstage;}
    public void setCoinTimeLeft(final double coindroptime) {this.CoinDropTime = coindroptime;}

    // Method for Guppy to eat, move, and grow
    public void eat() {
        setFishFull(true);
        if (((getNumEaten() > 6) && (getGrowthStage() == 2)) || ((getNumEaten() > 3) && (getGrowthStage() == 1))) {
            grow();
        }
        setTimeBeforeHungry(15);
        setTimeBeforeDying(20);
    }

    public void grow() {setGrowthStage(getGrowthStage() + 1);}

    // Coin
    public Coin dropCoin() {
        Coin c1 = new Coin(this.getX(), this.getY(), this.getGrowthStage() * 10);
        return c1;
    }
    
    public Coin countdownCoin(final double dtime) {
        if (getCoinTimeLeft() > 0) {
            this.setCoinTimeLeft(getCoinTimeLeft() - dtime);
            return null;
        } else {
            this.setCoinTimeLeft(10);
            return dropCoin();
        }
    }

    // Call this if Guppy is eaten
    public void eaten() {
        setIsAlive(false);
    }
    
    public void draw(JLabel destination) {
    	String state, filename;
    	int width, height;
		if (this.isFishFull()) {
			state = "n";
		}
		else {
			state = "h";
		}
		filename = this.getGrowthStage() + "_Guppy_" + state + "_" + this.getFacing() + ".png";
		switch(this.getGrowthStage()) {
			case 2:
				width = 62;
				height = 56;
				break;
			case 3:
				width = 79;
				height = 71;
				break;
			default:
				width = 45;
				height = 42;
		}
		try {
			BufferedImage sprite = ImageIO.read(new File(System.getProperty("user.dir") + "/bin/assets/" + filename));
			GupSprite.setIcon(new ImageIcon(sprite));
			GupSprite.setBounds((int)Math.round(this.getX()) - (width / 2),(int)Math.round(this.getY()) - (height / 2),width,height);
			destination.add(GupSprite);
		}
		catch (IOException ex) {
			System.out.println("Cannot load sprite Piranha " + state + " " + this.getFacing());
			ex.printStackTrace();
		}
    }
}