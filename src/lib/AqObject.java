package lib;

public abstract class AqObject {
    private final double speed;
    private double x, y; // AqObject position
    private int timeToRandomize;
    private boolean isAlive;

    public AqObject(final double absis, final double ordinat, final double s) {
        this.x = absis;
        this.y = ordinat;
        this.speed = s;
        this.timeToRandomize = 0;
        this.isAlive = true;
    }

    // Getter
    public double getSpeed() {
        return speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getTimeToRandomize() {
        return timeToRandomize;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    // Setter
    public void setX(final double absis) {
        this.x = absis;
    }

    public void setY(final double ordinat) {
        this.y = ordinat;
    }

    public void setTimeToRandomize(final int randomTime) {
        timeToRandomize = randomTime;
    }

    public void setIsAlive(final boolean alive) {
        this.isAlive = alive;
    }

    public abstract void timeHasPassed(double absis);

    public void resetRandomTime() {
        this.timeToRandomize = 1;
    }
}

