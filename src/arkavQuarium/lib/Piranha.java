package arkavquarium.lib;

import javax.swing.ImageIcon;

import arkavquarium.Main;

import java.awt.Image;

/**
 * The type Piranha.
 */
public class Piranha extends Fish {
    /**
     * Constant piranha's speed.
     */
    private static final int PIRANHA_SPEED = 40;
    /**
     * Constant guppy's price.
     */
    private static final int GUPPY_PRICE = 100;
    /**
     * Constant for fifteen.
     */
    private static final int FIFTEEN = 15;
    /**
     * Constant for twenty.
     */
    private static final int TWENTY = 20;
    /**
     * Constant for width.
     */
    private static final int WIDTH = 76;
    /**
     * Constant for height.
     */
    private static final int HEIGHT = 64;
    /**
     * Constant for seventy one.
     */
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

    /**
     * Draw the image.
     *
     * @return image drawn
     */
    @Override
    public Image draw() {
        String state, filename;
        if (this.isFishFull()) {
            state = "n";
        } else {
            state = "h";
        }
        filename = "assets/Piranha_" + state + "_" + this.getFacing() + ".png";
        filename = Main.class.getClassLoader().getResource(filename).getPath();
    	try {
    		filename = java.net.URLDecoder.decode(filename, "UTF-8");
    	}
    	catch (Exception e) {
    		System.out.println("Failed to load Piranha assets...");
    	}
        pirSprite = (new ImageIcon(filename)).getImage();
        return pirSprite;
    }
}
