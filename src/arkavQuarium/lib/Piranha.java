package arkavquarium.lib;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * The type Piranha.
 */
public class Piranha extends Fish {
    private static final int PIRANHA_SPEED = 40;
    private static final int GUPPY_PRICE = 100;
    private static final int FIFTEEN = 15;
    private static final int TWENTY = 20;
    private static final int WIDTH = 76;
    private static final int HEIGHT = 64;
    private Image pirSprite;

    /**
     * Instantiates a new Piranha.
     */
    public Piranha() {
        super(0, 0, PIRANHA_SPEED, WIDTH, HEIGHT);
    }

    /**
     * Instantiates a new Piranha.
     *
     * @param x the x
     * @param y the y
     */
    public Piranha(final double x, final double y) {
        super(x, y, PIRANHA_SPEED, WIDTH, HEIGHT);
    }

    /**
     * Eat guppy coin.
     *
     * @param g the g
     * @return the coin
     */
    public Coin eatGuppy(final Guppy g) {
        g.eaten();
        setFishFull(true);
        setTimeBeforeHungry(FIFTEEN);
        setTimeBeforeDying(TWENTY);
        Coin c1 = new Coin(this.getX(), this.getY(), GUPPY_PRICE
                * (g.getGrowthStage() + 1));
        return c1;
    }

    @Override
    public Image draw() {
        String state, filename;
        if (this.isFishFull()) {
            state = "n";
        } else {
            state = "h";
        }
        filename = "Piranha_" + state + "_" + this.getFacing() + ".png";
        pirSprite = (new ImageIcon("./assets/" + filename)).getImage();
        return pirSprite;
    }
}
