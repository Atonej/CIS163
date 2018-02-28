package changeJar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChangeJarTest {

	/**
	 * *** Your assignment is to write many test cases *****
	 */
	/* some examples provided to help you get started */

	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);

		assertEquals(s1.getQuarters(), 6);
		assertEquals(s1.getDimes(), 5);
		assertEquals(s1.getNickels(), 4);
		assertEquals(s1.getPennies(), 2);

		ChangeJar s2 = new ChangeJar();
		assertEquals(s2.getQuarters(), 0);
		assertEquals(s2.getDimes(), 0);
		assertEquals(s2.getNickels(), 0);
		assertEquals(s2.getPennies(), 0);

		ChangeJar s3 = new ChangeJar(s1);
		assertEquals(s3.getQuarters(), 6);
		assertEquals(s3.getDimes(), 5);
		assertEquals(s3.getNickels(), 4);
		assertEquals(s3.getPennies(), 2);
	}

	// testing valid takeOut with wide range of
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ChangeJar s1 = new ChangeJar(3, 3, 2, 2);
		s1.takeOut(1, 1, 1, 1);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 2);
		assertEquals(s1.getNickels(), 1);
		assertEquals(s1.getPennies(), 1);
	}

	// testing valid takeOut with wide range of amounts
	@Test
	public void testTakeOut2() {
		ChangeJar s1 = new ChangeJar(5, 3, 4, 3);
		ChangeJar s2 = s1.takeOut(1.22);

		assertEquals(s1.getQuarters(), 1);
		assertEquals(s1.getDimes(), 1);
		assertEquals(s1.getNickels(), 4);
		assertEquals(s1.getPennies(), 1);

		assertEquals(s2.getQuarters(), 4);
		assertEquals(s2.getDimes(), 2);
		assertEquals(s2.getNickels(), 0);
		assertEquals(s2.getPennies(), 2);
	}

	// testing putIn for valid low numbers
	@Test
	public void testPutIn() {
		ChangeJar s1 = new ChangeJar();
		s1.putIn(2, 3, 4, 5);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 3);
		assertEquals(s1.getNickels(), 4);
		assertEquals(s1.getPennies(), 5);
	}

	// testing putIn and takeOut together
	@Test
	public void testPutInTakeOut() {
		ChangeJar s1 = new ChangeJar();
		s1.putIn(3, 3, 2, 2);
		s1.takeOut(1, 1, 1, 1);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 2);
		assertEquals(s1.getNickels(), 1);
		assertEquals(s1.getPennies(), 1);
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual() {
		ChangeJar s1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s3 = new ChangeJar(2, 5, 4, 2);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
	}

	// testing compareTo all returns
	@Test
	public void testCompareTo() {
		ChangeJar s1 = new ChangeJar(2, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s3 = new ChangeJar(2, 3, 4, 2);
		ChangeJar s4 = new ChangeJar(2, 5, 4, 2);

		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		assertTrue(s1.compareTo(s4) == 0);
	}

	// load and save combined.
	@Test
	public void testLoadSave() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(6, 5, 4, 2);

		s1.save("file1");
		s1 = new ChangeJar(); // resets to zero

		s1.load("file1");
		assertTrue(s1.equals(s2));

	}

	// testing not able to make change
	@Test
	public void testTakeOutNull() {
		ChangeJar s1 = new ChangeJar(3, 1, 2, 2);
		ChangeJar s2 = s1.takeOut(1.22);
		assertEquals(s2, null);
	}

	private ChangeJar myTakeOut(int quarters, int dimes, int nickels,
			int pennies, double Amount) {

		int intAmount = (int) (Amount * 100);
		for (int q = quarters; q >= 0; q--)
			for (int d = dimes; d >= 0; d--)
				for (int n = nickels; n >= 0; n--)
					for (int p = pennies; p >= 0; p--)
						if ((q * 25 + d * 10 + n * 5 + p) == intAmount) {
							quarters -= q;
							dimes -= d;
							nickels -= n;
							pennies -= p;
							return new ChangeJar(q, d, n, p);
						}

		return null;
	}

	// Test thousands of takes outs with thousands of different ChangeJars
	@Test
	public void testTakeSuperTest() {
		int amount = 10;

		for (int q = 0; q < amount; q++) {
			System.out.println(q + " of " + (amount - 1) + " done");
			for (int d = 0; d < amount; d++)
				for (int n = 0; n < amount; n++)
					for (int p = 0; p < amount; p++)
						for (double m = 0; m < ((amount * q) + (amount * d)
								+ (amount * n) + p + 1.28); m++) {
							// System.out.println (m);
							ChangeJar a1 = new ChangeJar(q, d, n, p);
							ChangeJar b1 = a1.takeOut(m);

							ChangeJar a2 = new ChangeJar(q, d, n, p);
							ChangeJar b2 = myTakeOut(q, d, n, p, m);

							if (b1 != null)

								assertEquals(b1, b2);
						}
		}
	}

	// IMPORTANT: only one test per exception!!!

	// testing negative number for nickels, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegNickels() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 2);
		s1.takeOut(1, 1, -1, 1);
	}

	// testing negative number quarters, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegQuarters() {
		new ChangeJar(-300, 0, 0, 0);
	}

	// testing negative number for quarters, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNegQuarters() {
		ChangeJar s = new ChangeJar(2, 3, 4, 5);
		s.putIn(-30, 2, 30, 2);
	}
}
