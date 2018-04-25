package arkavquarium.lib;

import arkavquarium.lib.Siput;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiputTest {
    public static final double EPSILON = 0.00001;
    @Test
    public void getDegOfMovement() {
        Siput s1 = new Siput(0, 0);
        assertEquals(s1.getDegOfMovement(), 0);
    }

    @Test
    public void setDegOfMovement() {
        Siput s1 = new Siput(0, 0);
        s1.setDegOfMovement(180);
        assertEquals(s1.getDegOfMovement(), 180);
    }

    @Test
    public void getFacing() {
        Siput s1 = new Siput(0, 0);
        assertEquals(s1.getFacing(), 'l');
    }

    @Test
    public void setFacing() {
        Siput s1 = new Siput(0, 0);
        s1.setFacing('r');
        assertEquals(s1.getFacing(), 'r');
    }

    @Test
    public void setTimeToRandomize() {
        Siput s1 = new Siput(0, 0);
        s1.setTimeToRandomize(10);
        assertEquals(s1.getTimeToRandomize(), 10, EPSILON);
    }

    @Test
    public void resetRandomTime() {
        Siput s1 = new Siput(0, 0);
        s1.resetRandomTime();
        assertEquals(s1.getTimeToRandomize(), 1, EPSILON);
    }
}