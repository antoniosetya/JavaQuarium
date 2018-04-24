package arkavquarium.lib;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * The type Guppy.
 */
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
    private static final int FIFTY_THREE = 53;
    private static final int FORTY_SEVEN = 47;
    private static final int SEVENTY_ONE = 71;
    private static final int SIXTY_THREE = 63;
    private static final int THIRTY_SIX = 36;
    private static final int THIRTY_TWO = 32;
    private static int numGuppy = 0;
    private int growthStage;
    private double coinDropTime;
    private Image gupSprite;

    /**
     * Instantiates a new Guppy.
     */
    public Guppy() {
        super(0, 0, GUPPY_SPEED, THIRTY_SIX, THIRTY_TWO);
        setGrowthStage(INITIAL_GROWTH_STAGE);
        setCoinTimeLeft(INITIAL_COIN_TIME);
        numGuppy++;
        // Flush initial Image to gupSprite
        draw();
    }

    /**
     * Instantiates a new Guppy.
     *
     * @param x the x
     * @param y the y
     */
    public Guppy(final double x, final double y) {
        super(x, y, GUPPY_SPEED, THIRTY_SIX, THIRTY_TWO);
        setGrowthStage(INITIAL_GROWTH_STAGE);
        setCoinTimeLeft(INITIAL_COIN_TIME);
        numGuppy++;
        // Flush initial Image to gupSprite
        draw();
    }

    /**
     * Gets growth stage.
     *
     * @return the growth stage
     */
//Getter & Setter
    public int getGrowthStage() {
        return this.growthStage;
    }

    /**
     * Gets coin time left.
     *
     * @return the coin time left
     */
    public double getCoinTimeLeft() {
        return this.coinDropTime;
    }

    /**
     * Gets num guppy.
     *
     * @return the num guppy
     */
    public static int getNumGuppy() {
        return numGuppy;
    }

    /**
     * Sets growth stage.
     *
     * @param growthstage the growthstage
     */
    public void setGrowthStage(final int growthstage) {
        this.growthStage = growthstage;
    }

    /**
     * Sets coin time left.
     *
     * @param coindroptime the coindroptime
     */
    public void setCoinTimeLeft(final double coindroptime) {
        this.coinDropTime = coindroptime;
    }


    /**
     * Eat.
     */
// Method for Guppy to eat, move, and grow
    public void eat() {
        setFishFull(true);
        setNumEaten(getNumEaten() + 1);
        if (((getNumEaten() > SIX) && (getGrowthStage() == TWO))
                || ((getNumEaten() > THREE) && (getGrowthStage() == ONE))) {
            grow();
        }
        setTimeBeforeHungry(FIFTEEN);
        setTimeBeforeDying(TWENTY);
    }

    /**
     * Grow.
     */
    public void grow() {
        setGrowthStage(getGrowthStage() + 1);
        switch (this.getGrowthStage()) {
            case TWO:
                setWidth(FIFTY_THREE);
                setHeight(FORTY_SEVEN);
                break;
            case THREE:
                setWidth(SEVENTY_ONE);
                setHeight(SIXTY_THREE);
                break;
            default:
                setWidth(THIRTY_SIX);
                setHeight(THIRTY_TWO);
        }
    }

    /**
     * Drop coin coin.
     *
     * @return the coin
     */
// Coin
    public Coin dropCoin() {
        Coin c1 = new Coin(this.getX(), this.getY(),
                this.getGrowthStage() * TEN);
        return c1;
    }

    /**
     * Countdown coin coin.
     *
     * @param dtime the dtime
     * @return the coin
     */
    public Coin countdownCoin(final double dtime) {
        if (getCoinTimeLeft() > 0) {
            this.setCoinTimeLeft(getCoinTimeLeft() - dtime);
            return null;
        } else {
            this.setCoinTimeLeft(TEN);
            return dropCoin();
        }
    }

    /**
     * Eaten.
     */
// Call this if Guppy is eaten
    public void eaten() {
        setIsAlive(false);
    }

    @Override
    public Image draw() {
        String state, filename;
        if (this.isFishFull()) {
            state = "n";
        } else {
            state = "h";
        }
        filename = this.getGrowthStage() + "_Guppy_" + state
                + "_" + this.getFacing() + ".png";
        gupSprite = (new ImageIcon("./assets/" + filename)).getImage();
        return gupSprite;
    }
}
