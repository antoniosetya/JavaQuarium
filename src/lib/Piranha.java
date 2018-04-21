package lib;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Piranha extends Fish {
    private static final int PIRANHA_SPEED = 40;
    private static final int GUPPY_PRICE = 100;
    private static final int FIFTEEN = 15;
    private static final int TWENTY = 20;
    private static final int WIDTH = 84;
    private static final int HEIGHT = 73;
    private Image pirSprite;

    public Piranha() {
        super(0, 0, PIRANHA_SPEED);
    }

    public Piranha(final double x, final double y) {
        super(x, y, PIRANHA_SPEED);
    }

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
        pirSprite = (new ImageIcon("./bin/assets/" + filename)).getImage();
        return pirSprite;
    }
}
