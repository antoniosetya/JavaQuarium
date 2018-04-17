import org.junit.Test;

import static org.junit.Assert.*;

public class FishTest {
    private static final double epsilon = 0.00001;
    Fish f1 = new Fish(1, 3, 5);

    @Test
    public void getNumEaten() throws Exception {
        assertEquals(f1.getNumEaten(), 0);
    }

    @Test
    public void getFacing() throws Exception {
        assertEquals(f1.getFacing(), 'r');
    }

    @Test
    public void isFishFull() throws Exception {
        assertEquals(f1.isFishFull(), true);
    }

    @Test
    public void getDegOfMovement() throws Exception {
        assertEquals(f1.getDegOfMovement(), 0);
    }

    @Test
    public void getTimeBeforeHungry() throws Exception {
        assertEquals(f1.getTimeBeforeHungry(), 15, epsilon);
    }

    @Test
    public void getTimeBeforeDying() throws Exception {
        assertEquals(f1.getTimeBeforeDying(), 20, epsilon);
    }

    @Test
    public void getToX() throws Exception {
        assertEquals(f1.getToX(), -1, epsilon);
    }

    @Test
    public void getToY() throws Exception {
        assertEquals(f1.getToY(), -1, epsilon);
    }

    @Test
    public void setNumEaten() throws Exception {
        f1.setNumEaten(10);
        assertEquals(f1.getNumEaten(), 10);
    }

    @Test
    public void setFacing() throws Exception {
        f1.setFacing('l');
        assertEquals(f1.getFacing(), 'l');
    }

    @Test
    public void setFishFull() throws Exception {
        f1.setFishFull(false);
        assertEquals(f1.isFishFull(), false);
    }

    @Test
    public void setDegOfMovement() throws Exception {
        f1.setDegOfMovement(90);
        assertEquals(f1.getDegOfMovement(), 90);
    }

    @Test
    public void setTimeBeforeHungry() throws Exception {
        f1.setTimeBeforeHungry(10);
        assertEquals(f1.getTimeBeforeHungry(), 10, epsilon);
    }

    @Test
    public void setTimeBeforeDying() throws Exception {
        f1.setTimeBeforeDying(10);
        assertEquals(f1.getTimeBeforeDying(), 10, epsilon);
    }

    @Test
    public void setTimeToRandomize() throws Exception {
        f1.setTimeToRandomize(10);
        assertEquals(f1.getTimeToRandomize(), 10, epsilon);
    }

    @Test
    public void setToX() throws Exception {
        f1.setToX(1);
        assertEquals(f1.getToX(), 1, epsilon);
    }

    @Test
    public void setToY() throws Exception {
        f1.setToY(1);
        assertEquals(f1.getToY(), 1, epsilon);
    }

    @Test
    public void moveTowards() throws Exception {
        f1.moveTowards(30, 30);
        assertEquals(f1.getToX(), 30, epsilon);
        assertEquals(f1.getToY(), 30, epsilon);
    }

    @Test
    public void moveRandomly() throws Exception {
        f1.moveRandomly();
        assertEquals(f1.getToX(), -1, epsilon);
        assertEquals(f1.getToY(), -1, epsilon);
    }

    @Test
    public void countdownHungry() throws Exception {
        f1.countdownHungry(10);
        assertEquals(f1.getTimeBeforeHungry(), 5, epsilon);
        f1.countdownHungry(10);
        assertEquals(f1.getTimeBeforeHungry(), -5, epsilon);
        f1.countdownHungry(10);
        assertEquals(f1.isFishFull(), false);
    }

    @Test
    public void countdownDying() throws Exception {
        f1.countdownDying(10);
        assertEquals(f1.getTimeBeforeDying(), 10, epsilon);
        f1.countdownDying(10);
        assertEquals(f1.getTimeBeforeDying(), 0, epsilon);
        f1.countdownDying(10);
        assertEquals(f1.getIsAlive(), false);
    }

    @Test
    public void move() throws Exception {
        f1.move(10);
        // Random move
        assertNotEquals(f1.getDegOfMovement(), 180);
        assertNotEquals(f1.getToX(), -1);
    }

    @Test
    public void timeHasPassed() throws Exception {
        f1.timeHasPassed(3);
        assertEquals(f1.isFishFull(), true);
        assertEquals(f1.getTimeBeforeHungry(), 12, epsilon);
        f1.setFishFull(false);
        f1.timeHasPassed(3);
        assertEquals(f1.getTimeBeforeDying(), 17, epsilon);
    }

}