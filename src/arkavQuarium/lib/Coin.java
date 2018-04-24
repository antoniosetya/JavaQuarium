package arkavQuarium.lib;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The type Coin.
 */
public class Coin extends AqObject {
    private int value;
    private static final int COIN_SPEED = 75;
    private static final int THIRTY = 30;
    private static final int THIRTY_TWO = 32;
    private static final int TWENTY_SIX = 26;
    private static final int ONE_HUNDRED = 100;
    private Image coinSprite;

    /**
     * Instantiates a new Coin.
     */
    public Coin() {
        super(0, 0, COIN_SPEED, THIRTY, THIRTY);
        setValue(0);
        loadSprite();
    }

    /**
     * Instantiates a new Coin.
     *
     * @param x   the x
     * @param y   the y
     * @param val the val
     */
    public Coin(final double x, final double y, final int val) {
        super(x, y, COIN_SPEED, THIRTY, THIRTY);
        setValue(val);
        if (val >= ONE_HUNDRED) {
            setWidth(THIRTY_TWO);
            setHeight(TWENTY_SIX);
        }
        loadSprite();
    }

    private void loadSprite() {
        if (value >= ONE_HUNDRED) {
            coinSprite = (new ImageIcon("./assets/Diamond.png")).getImage();
        } else {
            coinSprite = (new ImageIcon("./assets/Coin.png")).getImage();
        }
    }

    /**
     * Gets value.
     *
     * @return the value
     */
//Getter & Setter
    public int getValue() {
        return this.value;
    }

    /**
     * Sets value.
     *
     * @param val the val
     */
    public void setValue(final int val) {
        this.value = val;
    }

    /**
     * Move.
     *
     * @param timePassed the time passed
     */
// Coin movement
    public void move(final double timePassed) {
        setY(getY() + (COIN_SPEED * timePassed));
        updateHitBox();
    }

    public void timeHasPassed(final double dtime) {
        move(dtime);
    }

    /**
     * Collected.
     */
// Call this if coin is collected
    public void collected() {
        setIsAlive(false);
    }

    public Image draw() {
        return coinSprite;
    }
}
