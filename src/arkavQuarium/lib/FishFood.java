package arkavQuarium.lib;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * The type Fish food.
 */
public class FishFood extends AqObject implements Moveable {
    private static final int FISH_FOOD_SPEED = 20;
    private static final int WIDTH = 21;
    private static final int HEIGHT = 22;
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

    public void move(final double sec) {
        setY(getY() + (getSpeed() * sec));
        updateHitBox();
    }

    public void timeHasPassed(final double dtime) {
        this.move(dtime);
    }

    /**
     * Eaten.
     */
    public void eaten() {
        setIsAlive(false);
    }

    public Image draw() {
        return ffsprite;
    }
}
