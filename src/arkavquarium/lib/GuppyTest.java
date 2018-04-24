package arkavquarium.lib;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuppyTest {
    private static final int INITIAL_COIN_TIME = 10;
    private static final int INIT_HUNGRY_TIME = 15;
    private static final double EPSILON = 0.00001;

    @Test
    public void getGrowthStage() {
        Guppy guppy = new Guppy();
        assertEquals(guppy.getGrowthStage(), 1);
    }

    @Test
    public void getCoinTimeLeft() {
        Guppy guppy = new Guppy();
        assertEquals(guppy.getCoinTimeLeft(), 10, EPSILON);
    }

    @Test
    public void getNumGuppy() {
        int temp = Guppy.getNumGuppy();
        Guppy guppy = new Guppy();
        assertEquals(Guppy.getNumGuppy(), temp+1);
    }

    @Test
    public void setGrowthStage() {
        Guppy guppy = new Guppy();
        guppy.setGrowthStage(2);
        assertEquals(guppy.getGrowthStage(), 2);
    }

    @Test
    public void setCoinTimeLeft() {
        Guppy guppy = new Guppy();
        guppy.setCoinTimeLeft(9);
        assertEquals(guppy.getCoinTimeLeft(), 9, EPSILON);
    }

    @Test
    public void eat() {
        Guppy guppy = new Guppy();
        guppy.eat();
        assertEquals(guppy.getTimeBeforeHungry(), INIT_HUNGRY_TIME, EPSILON);
    }

    @Test
    public void grow() {
        Guppy guppy = new Guppy();
        guppy.grow();
        assertEquals(guppy.getGrowthStage(), 2);
    }

    @Test
    public void dropCoin() {
        Guppy guppy = new Guppy();
        guppy.dropCoin();
        assertEquals(guppy.getCoinTimeLeft(), INITIAL_COIN_TIME, EPSILON);
    }

    @Test
    public void countdownCoin() {
    }

    @Test
    public void eaten() {
        Guppy guppy = new Guppy();
        guppy.eaten();
        assertEquals(guppy.getIsAlive(), false);
    }

}