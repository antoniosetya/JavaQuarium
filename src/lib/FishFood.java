public class FishFood extends AqObject implements Moveable {
    private static final int FISH_FOOD_SPEED = 20;

    public FishFood() {
        super(0, 0, FISH_FOOD_SPEED);
        setIsAlive(true);
    }

    public FishFood(final double x, final double y) {
        super(x, y, FISH_FOOD_SPEED);
        setIsAlive(true);
    }

    public boolean isEqual(final FishFood anotherFishFood) {
        return (this.getX() == anotherFishFood.getX())
                && (this.getY() == anotherFishFood.getY())
                && (getIsAlive() == anotherFishFood.getIsAlive());
    }

    public void move(final double sec) {
        setY(getY() + (getSpeed() * sec));
    }

    public void timeHasPassed(final double dtime) {
        this.move(dtime);
    }

    public void eaten() {
        setIsAlive(false);
    }
}
