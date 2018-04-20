package lib;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Coin extends AqObject {
    private int value;
    private static final int COIN_SPEED = 75;
    private static final int TWENTY = 20;
    private static final int FORTY = 40;
    private JLabel coinSprite = new JLabel();

    public Coin() {
        super(0, 0, COIN_SPEED);
        setValue(0);
        loadSprite();
    }

    public Coin(final double x, final double y, final int val) {
        super(x, y, COIN_SPEED);
        setValue(value);
        loadSprite();
    }

    private void loadSprite() {
        try {
            BufferedImage sprite = ImageIO.read(
                    new File(System.getProperty("user.dir")
                            + "/bin/assets/Coin.png"));
            coinSprite.setIcon(new ImageIcon(sprite));
        } catch (IOException ex) {
            System.out.println("Cannot load sprite FishFood");
            ex.printStackTrace();
        }
    }

    //Getter & Setter
    public int getValue() {
        return this.value;
    }

    public void setValue(final int val) {
        this.value = val;
    }

    // Coin movement
    public void move(final double timePassed) {
        setY(getY() + (COIN_SPEED * timePassed));
    }

    public void timeHasPassed(final double dtime) {
        move(dtime);
    }

    // Call this if coin is collected
    public void collected() {
        setIsAlive(false);
    }

    public void draw(final JLabel destination) {
        coinSprite.setBounds((int) Math.round(this.getX()) - TWENTY,
                (int) Math.round(this.getY()) - TWENTY, FORTY, FORTY);
        destination.add(coinSprite);
    }
}
