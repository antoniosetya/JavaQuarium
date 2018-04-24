package arkavquarium.lib;

import static org.junit.Assert.*;
import org.junit.Test;

public class AquariumTest {
	Aquarium test;
	
	@org.junit.Before
	public void initBeforeTest() {
		test = new Aquarium();
	}
	
	@Test
	/* Used to test default ctor */
	public void testDefaultCtor() {
		assertEquals(test.getWidth(),640);
		assertEquals(test.getHeight(),480);
	}

	@Test
	/* Used to test alternate ctor
	 * (the one with parameters on it)
	 */
	public void testAlternateCtor() {
		test = new Aquarium(1000,1000);
		assertEquals(test.getWidth(),1000);
		assertEquals(test.getHeight(),1000);
	}
	
	@Test
	/* Used to test getters & setters for width & height */
	public void testDimChange() {
		test.setWidth(250);
		assertEquals(test.getWidth(),250);
		test.setHeight(300);
		assertEquals(test.getHeight(),300);
	}
	
	@Test
	/* Used to test getters & setters for numOfCoins */
	public void testNumOfCoin() {
		assertEquals(test.getNumOfCoins(),150);
		test.setNumOfCoins(test.getNumOfCoins() + 200);
		assertEquals(test.getNumOfCoins(),350);
	}
	
	@Test
	/* Used to test method initialize() */
	public void testInitialize() {
		test.initialize();
		assertEquals(test.getGuppies().getSize(),1);
		double x = test.getGuppies().get(0).getX();
		double y = test.getGuppies().get(0).getY();
		assertTrue((x >= 0) && (x <= test.getWidth()) && (y >= 0) && (y <= test.getHeight()));
		assertNotNull(test.getSnail());
		x = test.getSnail().getX();
		y = test.getSnail().getY();
		assertTrue((x >= 0) && (x <= test.getWidth()) && (y >= 0) && (y <= test.getHeight()));
	}
	
	@Test
	/* Used to test method createNewObject() */
	public void testObjectCreation() {
		test.initialize();
		test.createNewObject('G');
		assertEquals(test.getGuppies().getSize(),2);
		
		test.createNewObject('F',150,350);
		assertEquals(test.getFishFoods().getSize(),1);
		assertEquals(test.getFishFoods().get(0).getX(),150,2);
		assertEquals(test.getFishFoods().get(0).getY(),350,2);
		
		test.createNewObject('P');
		test.createNewObject('P');
		assertEquals(test.getPiranhas().getSize(),2);
	}
	
	@Test
	/* Used to test method keepOnAquarium() */
	public void testKeepOnAquarium() {
		test.createNewObject('G',250,300);
		test.keepOnAquarium(test.getGuppies().get(0));
		assertEquals(test.getGuppies().get(0).getX(),250,2);
		assertEquals(test.getGuppies().get(0).getY(),300,2);
		
		test.getGuppies().get(0).setX(test.getWidth() + 250);
		test.getGuppies().get(0).setY(test.getHeight() + 150);
		test.keepOnAquarium(test.getGuppies().get(0));
		assertEquals(test.getGuppies().get(0).getX(),test.getWidth(),2);
		assertEquals(test.getGuppies().get(0).getY(),test.getHeight(),2);
		
		test.getGuppies().get(0).setX(-50);
		test.getGuppies().get(0).setY(-1);
		test.keepOnAquarium(test.getGuppies().get(0));
		assertEquals(test.getGuppies().get(0).getX(),0,2);
		assertEquals(test.getGuppies().get(0).getY(),0,2);
	}
	
	@Test
	/* Used to test that a Piranha will eat nearby Guppy only if it's hungry */
	public void testTimeHasPassedEaten1() {
		test.createNewObject('G',175,175);
		test.createNewObject('P',175,175);
		test.setSnail(new Siput(160,test.getHeight()));
		test.timeHasPassed(0.033);
		assertEquals(test.getGuppies().getSize(),1);
		test.getPiranhas().get(0).setFishFull(false);
		test.timeHasPassed(0.033);
		assertEquals(test.getGuppies().getSize(),0);
		assertTrue(test.getPiranhas().get(0).isFishFull());
		assertEquals(test.getCoins().getSize(),1);
	}
	
	@Test
	/* Used to test that a Guppy will eat nearby FishFood only if it's hungry */
	public void testTimeHasPassedEaten2() {
		test.createNewObject('G',175,175);
		test.setSnail(new Siput(160,test.getHeight()));
		test.createNewObject('F',175,175);
		test.timeHasPassed(0.033);
		assertEquals(test.getFishFoods().getSize(),1);
		test.getGuppies().get(0).setFishFull(false);
		test.timeHasPassed(0.033);
		assertEquals(test.getFishFoods().getSize(),0);
		assertTrue(test.getGuppies().get(0).isFishFull());
	}
	
	@Test
	/* Used to test if Snail will pick up nearby Coins */
	public void testTimeHasPassedEaten3() {
		long initial = test.getNumOfCoins();
		test.getCoins().append(new Coin(162,test.getHeight(),20));
		test.setSnail(new Siput(160,test.getHeight()));
		test.timeHasPassed(0.033);
		assertEquals(test.getCoins().getSize(),0);
		assertEquals(test.getNumOfCoins(),initial + 20);
	}
	
	@Test
	/* Used to test that a Piranha will only eat 1 Guppy when hungry */
	public void testTimeHasPassedEaten4() {
		test.createNewObject('G',175,175);
		test.createNewObject('G',177,177);
		test.createNewObject('P',176,176);
		test.setSnail(new Siput(160,test.getHeight()));
		test.getPiranhas().get(0).setFishFull(false);
		test.timeHasPassed(0.033);
		assertEquals(test.getGuppies().getSize(),1);
		assertTrue(test.getPiranhas().get(0).isFishFull());
		assertEquals(test.getCoins().getSize(),1);
	}
	
	@Test
	/* Used to test that a Guppy will only eat 1 FishFood when hungry */
	public void testTimeHasPassedEaten5() {
		test.createNewObject('G',175,175);
		test.getGuppies().get(0).setFishFull(false);
		test.setSnail(new Siput(160,test.getHeight()));
		test.createNewObject('F',176,176);
		test.createNewObject('F',177,177);
		test.timeHasPassed(0.033);
		assertEquals(test.getFishFoods().getSize(),1);
		assertTrue(test.getGuppies().get(0).isFishFull());
	}
	
	@Test
	/* Used to test method collectCoin() */
	public void testCollectCoin() {
		long initial = test.getNumOfCoins();
		test.getCoins().append(new Coin(150,250,30));
		test.getCoins().append(new Coin(100,200,40));
		test.getCoins().append(new Coin(300,100,50));
		test.collectCoin(50, 50);
		assertEquals(test.getCoins().getSize(),3);
		test.collectCoin(99, 199);
		assertFalse(test.getCoins().get(1).getIsAlive());
		assertEquals(test.getNumOfCoins(),initial + 40);
		test.collectCoin(98, 198);
		assertEquals(test.getNumOfCoins(),initial + 40);
		test.collectCoin(300, 99);
		assertFalse(test.getCoins().get(2).getIsAlive());
		assertEquals(test.getNumOfCoins(),initial + 90);
	}
}

