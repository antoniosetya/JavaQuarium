package arkavquarium.lib;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * The type Object for Aquarium.
 */
public abstract class AqObject {
    /**
     * Constants for Hit Box Offset.
     */
    private static final int HITBOXOFFSET = 4;
    /**
     * Variable for speed.
     */
    private final double speed;
    /**
     * Variable for coordinate x and y.
     */
    private double x, y;
    /**
     * Variable for width and height.
     */
    private int width, height;
    /**
     * Variable refering to object is alive or not.
     */
    private boolean isAlive;
    /**
     * Variable for Hit Box.
     */
    private Rectangle hitbox;

    /**
     * Instantiates a new Aquarium object.
     *
     * @param absis   the absis of object
     * @param ordinat the ordinat of object
     * @param s       the speed of object
     * @param i       the width of object
     * @param i1      the height of object
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
     * Gets absis.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets ordinat.
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
     * Sets absis.
     *
     * @param absis the absis
     */
// Setter
    public void setX(final double absis) {
        this.x = absis;
    }

    /**
     * Sets ordinat.
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
     * Function to do things when certain time has passed.
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
