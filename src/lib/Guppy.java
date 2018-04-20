package lib;

public class Guppy extends Fish {
    private static final int GUPPY_SPEED = 35;
    private static int NumGuppy = 0;
    private int GrowthStage;
    private double CoinDropTime;

    public Guppy() {
        super(0, 0, GUPPY_SPEED);
        setGrowthStage(1);
        setCoinTimeLeft(10);
        NumGuppy++;
    }

    public Guppy(double x, double y) {
        super(x, y,GUPPY_SPEED);
        setGrowthStage(1);
        setCoinTimeLeft(10);
        NumGuppy++;
    }

    //Getter & Setter
    public int getGrowthStage() {return this.GrowthStage;}
    public double getCoinTimeLeft() {return this.CoinDropTime;}
    public static int getNumGuppy() {return NumGuppy;}
    public void setGrowthStage(int growthstage) {this.GrowthStage = growthstage;}
    public void setCoinTimeLeft(double coindroptime) {this.CoinDropTime = coindroptime;}

    // Method for Guppy to eat, move, and grow
    public void eat() {
        setFishFull(true);
        if (((getNumEaten() > 6) && (getGrowthStage() == 2)) || ((getNumEaten() > 3) && (getGrowthStage() == 1))) {
            grow();
        }
        setTimeBeforeHungry(15);
        setTimeBeforeDying(20);
    }

    public void grow() {setGrowthStage(getGrowthStage() + 1);}

    // Coin
    public Coin dropCoin() {
        Coin c1 = new Coin(this.getX(), this.getY(), this.getGrowthStage() * 10);
        return c1;
    }
    
    public Coin countdownCoin(double dtime) {
        if (getCoinTimeLeft() > 0) {
            this.setCoinTimeLeft(getCoinTimeLeft() - dtime);
            return null;
        } else {
            this.setCoinTimeLeft(10);
            return dropCoin();
        }
    }

    // Call this if Guppy is eaten
    public void eaten() {
        setIsAlive(false);
    }
}