package lib;

public class Guppy extends Fish {
    private static final int GUPPY_SPEED = 35;
    private static final int INITIAL_COIN_TIME = 10;
    private static final int INITIAL_GROWTH_STAGE = 1;
    private static final int SIX = 6;
    private static final int TWO = 2;
    private static final int ONE = 1;
    private static final int TEN = 10;
    private static final int FIFTEEN = 15;
    private static final int THREE = 3;
    private static final int TWENTY = 20;
    private static int numGuppy = 0;
    private int growthStage;
    private double coinDropTime;

    public Guppy() {
        super(0, 0, GUPPY_SPEED);
        setGrowthStage(INITIAL_GROWTH_STAGE);
        setCoinTimeLeft(INITIAL_COIN_TIME);
        numGuppy++;
    }

    public Guppy(final double x, final double y) {
        super(x, y, GUPPY_SPEED);
        setGrowthStage(INITIAL_GROWTH_STAGE);
        setCoinTimeLeft(INITIAL_COIN_TIME);
        numGuppy++;
    }

    //Getter & Setter
    public int getGrowthStage() {
        return this.growthStage;
    }

    public double getCoinTimeLeft() {
        return this.coinDropTime;
    }

    public static int getNumGuppy() {
        return numGuppy;
    }

    public void setGrowthStage(final int growthstage) {
        this.growthStage = growthstage;
    }

    public void setCoinTimeLeft(final double coindroptime) {
        this.coinDropTime = coindroptime;
    }


    // Method for Guppy to eat, move, and grow
    public void eat() {
        setFishFull(true);
        if (((getNumEaten() > SIX) && (getGrowthStage() == TWO))
                || ((getNumEaten() > THREE) && (getGrowthStage() == ONE))) {
            grow();
        }
        setTimeBeforeHungry(FIFTEEN);
        setTimeBeforeDying(TWENTY);
    }

    public void grow() {
        setGrowthStage(getGrowthStage() + 1);
    }

    // Coin
    public Coin dropCoin() {
        Coin c1 = new Coin(this.getX(), this.getY(),
                this.getGrowthStage() * TEN);
        return c1;
    }

    public Coin countdownCoin(final double dtime) {
        if (getCoinTimeLeft() > 0) {
            this.setCoinTimeLeft(getCoinTimeLeft() - dtime);
            return null;
        } else {
            this.setCoinTimeLeft(TEN);
            return dropCoin();
        }
    }

    // Call this if Guppy is eaten
    public void eaten() {
        setIsAlive(false);
    }
}
