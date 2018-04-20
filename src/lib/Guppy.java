package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Guppy extends Fish {
    private static final int GUPPY_SPEED = 35;
    private static final int INITIAL_COIN_TIME = 10;
    private static final int INITIAL_GROWTH_STAGE = 1;
    private static final int SIX = 6;
    private static final int TWO = 2;
    private static final int ONE = 1;
    private static final int TEN = 10;
    private static final int FIFTEEN = 15;
    private static final int THREE = 3;
    private static final int TWENTY = 20;
    private static final int SIXTY_TWO = 62;
    private static final int FIFTY_SIX = 56;
    private static final int SEVENTY_NINE = 79;
    private static final int SEVENTY_ONE = 71;
    private static final int FORTY_FIFE = 45;
    private static final int FORTY_TWO = 42;
    private static int numGuppy = 0;
    private int growthStage;
    private double coinDropTime;
    private JLabel gupSprite = new JLabel();

    public Guppy() {
        super(0, 0, GUPPY_SPEED);
        setGrowthStage(INITIAL_GROWTH_STAGE);
        setCoinTimeLeft(INITIAL_COIN_TIME);
        numGuppy++;
    }

    public Guppy(final double x, final double y) {
        super(x, y, GUPPY_SPEED);
        setGrowthStage(INITIAL_GROWTH_STAGE);
        setCoinTimeLeft(INITIAL_COIN_TIME);
        numGuppy++;
    }

    //Getter & Setter
    public int getGrowthStage() {
        return this.growthStage;
    }

    public double getCoinTimeLeft() {
        return this.coinDropTime;
    }

    public static int getNumGuppy() {
        return numGuppy;
    }

    public void setGrowthStage(final int growthstage) {
        this.growthStage = growthstage;
    }

    public void setCoinTimeLeft(final double coindroptime) {
        this.coinDropTime = coindroptime;
    }


    // Method for Guppy to eat, move, and grow
    public void eat() {
        setFishFull(true);
        if (((getNumEaten() > SIX) && (getGrowthStage() == TWO))
                || ((getNumEaten() > THREE) && (getGrowthStage() == ONE))) {
            grow();
        }
        setTimeBeforeHungry(FIFTEEN);
        setTimeBeforeDying(TWENTY);
    }

    public void grow() {
        setGrowthStage(getGrowthStage() + 1);
    }

    // Coin
    public Coin dropCoin() {
        Coin c1 = new Coin(this.getX(), this.getY(),
                this.getGrowthStage() * TEN);
        return c1;
    }

    public Coin countdownCoin(final double dtime) {
        if (getCoinTimeLeft() > 0) {
            this.setCoinTimeLeft(getCoinTimeLeft() - dtime);
            return null;
        } else {
            this.setCoinTimeLeft(TEN);
            return dropCoin();
        }
    }

    // Call this if Guppy is eaten
    public void eaten() {
        setIsAlive(false);
    }

    public void draw(final JLabel destination) {
        String state, filename;
        int width, height;
        if (this.isFishFull()) {
            state = "n";
        } else {
            state = "h";
        }
        filename = this.getGrowthStage() + "_Guppy_" + state
                + "_" + this.getFacing() + ".png";
        switch (this.getGrowthStage()) {
            case TWO:
                width = SIXTY_TWO;
                height = FIFTY_SIX;
                break;
            case THREE:
                width = SEVENTY_NINE;
                height = SEVENTY_ONE;
                break;
            default:
                width = FORTY_FIFE;
                height = FORTY_TWO;
        } try {
            BufferedImage sprite = ImageIO.read(
                    new File(System.getProperty("user.dir")
                            + "/bin/assets/" + filename));
            gupSprite.setIcon(new ImageIcon(sprite));
            gupSprite.setBounds((int) Math.round(this.getX()) - (width / 2),
                    (int) Math.round(this.getY()) - (height / 2),
                    width, height);
            destination.add(gupSprite);
        } catch (IOException ex) {
            System.out.println("Cannot load sprite Piranha "
                    + state + " " + this.getFacing());
            ex.printStackTrace();
        }
    }
}
