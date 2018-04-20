package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Piranha extends Fish {
    private static final int PIRANHA_SPEED = 40;
    private static final int GUPPY_PRICE = 100;
    private static final int HALF_FULL_DEGREE = 180;
    private static final int FIFTEEN = 15;
    private static final int TWENTY = 20;
    private static final int WIDTH = 84;
    private static final int HEIGHT = 73;
    private int degOfMovement;
    private JLabel pirSprite = new JLabel();

    public Piranha() {
        super(0, 0, PIRANHA_SPEED);
        this.degOfMovement = HALF_FULL_DEGREE;
    }

    public Piranha(final double x, final double y) {
        super(x, y, PIRANHA_SPEED);
        this.degOfMovement = HALF_FULL_DEGREE;
    }

    public int getDegOfMovement() {
        return degOfMovement;
    }

    public void setDegOfMovement(final int degree) {
        this.degOfMovement = degree;
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
    public void draw(final JLabel destination) {
        String state, filename;
        if (this.isFishFull()) {
            state = "n";
        } else {
            state = "h";
        }
        filename = "Piranha_" + state + "_" + this.getFacing() + ".png";
        try {
            BufferedImage sprite = ImageIO.read(
                    new File(System.getProperty("user.dir")
                            + "/bin/assets/" + filename));
            pirSprite.setIcon(new ImageIcon(sprite));
            pirSprite.setBounds((int) Math.round(this.getX()),
                    (int) Math.round(this.getY()), WIDTH, HEIGHT);
            destination.add(pirSprite);
        } catch (IOException ex) {
            System.out.println("Cannot load sprite Piranha " + state
                    + " " + this.getFacing());
            ex.printStackTrace();
        }
    }
}
