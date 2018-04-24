package arkavquarium.lib;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * The type Fish food.
 */
public class FishFood extends AqObject implements Moveable {
    /**
     * Constant for fish food's speed.
     */
    private static final int FISH_FOOD_SPEED = 20;
    /**
     * Constant for width.
     */
    private static final int WIDTH = 21;
    /**
     * Constant for height.
     */
    private static final int HEIGHT = 22;
    /**
     * Variable for image fish food.
     */
    private Image ffsprite;

    /**
     * Instantiates a new Fish food.
     */
    public FishFood() {
        super(0, 0, FISH_FOOD_SPEED, WIDTH, HEIGHT);
        setIsAlive(true);
        loadSprite();
    }

    /**
     * Instantiates a new Fish food.
     *
     * @param x the x
     * @param y the y
     */
    public FishFood(final double x, final double y) {
        super(x, y, FISH_FOOD_SPEED, WIDTH, HEIGHT);
        setIsAlive(true);
        loadSprite();
    }

    /**
     * Load sprite.
     */
    private void loadSprite() {
        ImageIcon temp = new ImageIcon("./assets/FishFood.png");
        ffsprite = temp.getImage();
    }

    /**
     * Is equal boolean.
     *
     * @param anotherFishFood the another fish food
     * @return the boolean
     */
    public boolean isEqual(final FishFood anotherFishFood) {
        return (this.getX() == anotherFishFood.getX())
                && (this.getY() == anotherFishFood.getY())
                && (getIsAlive() == anotherFishFood.getIsAlive());
    }

    /**
     * Move the fish food.
     *
     * @param sec refers to second already spend
     */
    public void move(final double sec) {
        setY(getY() + (getSpeed() * sec));
        updateHitBox();
    }

    /**
     * Implementation of time has passed.
     *
     * @param dtime sec of time has passed
     */
    public void timeHasPassed(final double dtime) {
        this.move(dtime);
    }

    /**
     * Eaten.
     */
    public void eaten() {
        setIsAlive(false);
    }

    /**
     * Draw the image.
     *
     * @return image drawn
     */
    public Image draw() {
        return ffsprite;
    }
}
