package lib;

import static org.junit.Assert.*;

public class FishFoodTest {
	private static final double epsilon = 0.00001;

	@org.junit.Test
	public void isEqual() throws Exception {
		FishFood ff1 = new FishFood(2, 2);
		FishFood ff2 = new FishFood(2, 2);
		boolean equal = ff1.isEqual(ff2);
		assert (equal);
	}

	@org.junit.Test
	public void move() throws Exception {
		FishFood ff1 = new FishFood(2, 2);
		ff1.move(3);
		assertEquals(ff1.getY(), 62, epsilon);
	}

	@org.junit.Test
	public void timeHasPassed() throws Exception {
		FishFood ff1 = new FishFood(2, 2);
		ff1.timeHasPassed(3);
		assertEquals(ff1.getY(), 62, epsilon);
	}

	@org.junit.Test
	public void eaten() throws Exception {
		FishFood ff1 = new FishFood(2, 2);
		ff1.eaten();
		assert (!ff1.getIsAlive());
	}

}