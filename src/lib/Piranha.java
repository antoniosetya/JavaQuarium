package lib;

public class Piranha extends Fish {
    private static final int PIRANHA_SPEED = 40;
    private static final int GUPPY_PRICE = 100;
    private static final int HALF_FULL_DEGREE = 180;
    private int degOfMovement;

    // Constructor
    public Piranha() {
        super(0, 0, PIRANHA_SPEED);
        this.degOfMovement = HALF_FULL_DEGREE;
    }

    public Piranha(final double x, final double y) {
        super(x, y, PIRANHA_SPEED);
        this.degOfMovement = HALF_FULL_DEGREE;
    }

    // Getter
    public int getDegOfMovement() {
        return degOfMovement;
    }

    // Setter
    public void setDegOfMovement(final int degree) {
        this.degOfMovement = degree;
    }

    // Method for piranha eating guppy
//    public Coin eatGuppy(Guppy g) {
//        g.eaten();
//        setFishFull(true);
//        setTimeBeforeHungry(15);
//        setTimeBeforeDying(20);
//        // Create new coin
//        Coin c1 = new Coin(this.getX(), this.getY(), GUPPY_PRIDE *
// (g.getGrowthStage() + 1));
//        return c1;
//    }
}
