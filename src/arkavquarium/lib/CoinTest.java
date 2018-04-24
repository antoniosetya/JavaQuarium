package arkavquarium.lib;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoinTest {
    private static final int COIN_SPEED = 75;
    private static final double EPSILON = 0.00001;

    @Test
    public void getValue() throws Exception {
        Coin coin = new Coin();
        assertEquals(coin.getValue(), 0);
    }

    @Test
    public void setValue() throws Exception {
        Coin coin = new Coin();
        coin.setValue(35);
        assertEquals(coin.getValue(), 35);
    }

    @Test
    public void move() throws Exception {
        Coin coin = new Coin();
        double temp = coin.getY();
        coin.move(10);
        assertEquals(coin.getY(), temp + COIN_SPEED * 10, EPSILON);
    }

    @Test
    public void timeHasPassed() throws Exception {
        Coin coin = new Coin();
        double temp = coin.getY();
        coin.timeHasPassed(10);
        assertEquals(coin.getY(), temp + COIN_SPEED * 10, EPSILON);
    }

    @Test
    public void collected() throws Exception {
        Coin coin = new Coin();
        coin.collected();
        assertEquals(coin.getIsAlive(), false);
    }
}