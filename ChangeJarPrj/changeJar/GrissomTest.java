package changeJar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GrissomTest {

	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);

		assertEquals(6, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(2, s1.getPennies());

		ChangeJar s2 = new ChangeJar();
		assertEquals(0, s2.getQuarters());
		assertEquals(0, s2.getDimes());
		assertEquals(0, s2.getNickels());
		assertEquals(0, s2.getPennies());

		ChangeJar s3 = new ChangeJar(s1);
		assertEquals(6, s3.getQuarters());
		assertEquals(5, s3.getDimes());
		assertEquals(4, s3.getNickels());
		assertEquals(2, s3.getPennies());
	}

	// testing valid takeOut with wide range of amounts
	@Test
	public void testPutIn() {
		ChangeJar s1 = new ChangeJar(1, 1, 1, 1);
		ChangeJar s2 = new ChangeJar(1, 2, 3, 4);
		s1.putIn(s2);

		assertEquals(2, s2.getQuarters());
		assertEquals(3, s2.getDimes());
		assertEquals(4, s2.getNickels());
		assertEquals(5, s2.getPennies());

		assertEquals(0, s1.getQuarters());
		assertEquals(0, s1.getDimes());
		assertEquals(0, s1.getNickels());
		assertEquals(0, s1.getPennies());
	}

	// testing toString
	@Test
	public void testToString() {
		ChangeJar s1 = new ChangeJar(1, 10, 0, 100);
		String str = s1.toString();
		assertTrue(str.contains("1 quarter"));
		assertTrue(str.contains("10 dimes"));
		assertTrue(str.contains("0 nickels"));
		assertTrue(str.contains("100 pennies"));

	}

	// testing get amount
	@Test
	public void testGetAmount() {
		ChangeJar s1 = new ChangeJar(5, 1, 1, 3);
		double diff = s1.getAmount() - 1.43;
		assertTrue(Math.abs(diff) < 0.01);

	}

	// testing suspend
	@Test
	public void testSuspend() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.suspend(true);
		s1.takeOut(1, 1, 1, 1);
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
	}

	// testing negative number for
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegative() {
		ChangeJar s = new ChangeJar(2, 3, 4, 5);
		s.takeOut(-1.2);
	}

	// testing insuffient coins, for take out
	@Test
	public void testTakeOutInsufficientCoins() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 2);
		ChangeJar s2 = s1.takeOut(5, 0, 0, 0);
		assertEquals(null, s2);
		assertEquals(2, s1.getQuarters());
	}

	// testing insuffient coins, for take out
	@Test
	public void testTakeOut() {
		ChangeJar s1 = new ChangeJar(3, 3, 3, 3);
		ChangeJar s2 = new ChangeJar(1, 1, 1, 1);
		s2 = s1.takeOut(s2);
		assertEquals(2, s1.getQuarters());
		assertEquals(1, s2.getDimes());
	}
}
