package arkavquarium.lib;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * The type Coin.
 */
public class Coin extends AqObject implements Moveable{
    /**
     * Variable for coin's value.
     */
    private int value;
    /**
     * Constant for coin's speed.
     */
    private static final int COIN_SPEED = 75;
    /**
     * Constant for thirty.
     */
    private static final int THIRTY = 30;
    /**
     * Constant for thirty two.
     */
    private static final int THIRTY_TWO = 32;
    /**
     * Constant for twenty siz.
     */
    private static final int TWENTY_SIX = 26;
    /**
     * Constant for one hundred.
     */
    private static final int ONE_HUNDRED = 100;
    /**
     * Variable for coin sprite.
     */
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

    /**
     * Load the sprite.
     */
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
     * Coin Movement.
     *
     * @param timePassed the time passed
     */

    public void move(final double timePassed) {
        setY(getY() + (COIN_SPEED * timePassed));
        updateHitBox();
    }

    /**
     * Implementation of time has passed.
     *
     * @param dtime sec of time has passed
     */
    public void timeHasPassed(final double dtime) {
        move(dtime);
    }

    /**
     * Coin Collected.
     */
    public void collected() {
        setIsAlive(false);
    }

    /**
     * Draw the image.
     *
     * @return image drawn
     */
    public Image draw() {
        return coinSprite;
    }
}
