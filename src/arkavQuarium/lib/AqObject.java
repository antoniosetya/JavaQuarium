package arkavquarium.lib;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * The type Aq object.
 */
public abstract class AqObject {
    private static final int HITBOXOFFSET = 4;
    private final double speed;
    private double x, y; // AqObject position
    private int width, height;
    private boolean isAlive;
    private Rectangle hitbox;

    /**
     * Instantiates a new Aq object.
     *
     * @param absis   the absis
     * @param ordinat the ordinat
     * @param s       the s
     * @param i       the
     * @param i1      the 1
     */
    public AqObject(final double absis, final double ordinat,
                    final double s,
                    final int i, final int i1) {
        this.x = absis;
        this.y = ordinat;
        this.speed = s;
        this.isAlive = true;
        this.width = i;
        this.height = i1;
        hitbox = new Rectangle((int) (x - ((i - HITBOXOFFSET) / 2)),
                (int) (y - ((i1 - HITBOXOFFSET) / 2)),
                i - HITBOXOFFSET,
                i1 - HITBOXOFFSET);
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
// Getter
    public double getSpeed() {
        return speed;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Gets is alive.
     *
     * @return the is alive
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets x.
     *
     * @param absis the absis
     */
// Setter
    public void setX(final double absis) {
        this.x = absis;
    }

    /**
     * Sets y.
     *
     * @param ordinat the ordinat
     */
    public void setY(final double ordinat) {
        this.y = ordinat;
    }

    /**
     * Sets is alive.
     *
     * @param alive the alive
     */
    public void setIsAlive(final boolean alive) {
        this.isAlive = alive;
    }

    /**
     * Sets width.
     *
     * @param w the w
     */
    public void setWidth(final int w) {
        this.width = w;
    }

    /**
     * Sets height.
     *
     * @param h the h
     */
    public void setHeight(final int h) {
        this.height = h;
    }

    /**
     * Time has passed.
     *
     * @param absis the absis
     */
    public abstract void timeHasPassed(double absis);

    /**
     * Draw image.
     *
     * @return the image
     */
    public abstract Image draw();

    /**
     * Update hit box.
     */
    public void updateHitBox() {
        hitbox.setBounds((int) (x - ((width - HITBOXOFFSET) / 2)),
                (int) (y - ((height - HITBOXOFFSET) / 2)),
                width - HITBOXOFFSET,
                height - HITBOXOFFSET);
    }

    /**
     * Gets hit box.
     *
     * @return the hit box
     */
    public Rectangle getHitBox() {
        return hitbox;
    }
}
