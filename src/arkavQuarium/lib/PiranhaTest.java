package arkavQuarium.lib;

import static org.junit.Assert.*;

public class PiranhaTest {
    private static final double EPSILON = 0.00001;

	@org.junit.Test
	public void getDegOfMovement() throws Exception {
		Piranha p1 = new Piranha();
		assertEquals(p1.getDegOfMovement(), 0);
	}

	@org.junit.Test
	public void setDegOfMovement() throws Exception {
		Piranha p1 = new Piranha();
		p1.setDegOfMovement(90);
		assertEquals(p1.getDegOfMovement(), 90);
	}

	@org.junit.Test
    public void eatGuppy() throws Exception {
	    Piranha p1 = new Piranha(1,1);
	    Guppy g1 = new Guppy();
	    Coin c1 = p1.eatGuppy(g1);
	    assertEquals(g1.getIsAlive(), false);
	    assertEquals(p1.isFishFull(), true);
	    assertEquals(p1.getTimeBeforeHungry(), 15, EPSILON);
        assertEquals(p1.getTimeBeforeDying(), 20, EPSILON);
        assertEquals(c1.getValue(), 200);
    }

}