package lib;
import javax.swing.JLabel;

public class Coin extends AqObject {
    private int value;
    private static final int COIN_SPEED = 75;

    public Coin() {
        super(0, 0, COIN_SPEED);
        setValue(0);
    }

    public Coin(final double x, final double y, final int value) {
        super(x, y, COIN_SPEED);
        setValue(value);
    }

    //Getter & Setter
    public int getValue() {return this.value;}
    public void setValue(final int value) {this.value = value;}

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

    public void draw(JLabel destination) {
    }
}