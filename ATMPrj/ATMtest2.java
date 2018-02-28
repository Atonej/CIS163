package atmPack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ATMtest2 {

	/**
	 * *** Your assignment is to write many test cases *****
	 */
	/* some examples provided to help you get started */

	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ATM s1 = new ATM(6, 5, 4);

		assertEquals(s1.getHundreds(), 6);
		assertEquals(s1.getFifties(), 5);
		assertEquals(s1.getTwenties(), 4);

		ATM s2 = new ATM();
		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 0);

		ATM s3 = new ATM(s1);
		assertEquals(s3.getHundreds(), 6);
		assertEquals(s3.getFifties(), 5);
		assertEquals(s3.getTwenties(), 4);
	}

	// testing valid takeOut with wide range of
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ATM s1 = new ATM(3, 3, 2);
		s1.takeOut(1, 1, 1);
		assertEquals(s1.getHundreds(), 2);
		assertEquals(s1.getFifties(), 2);
		assertEquals(s1.getTwenties(), 1);
	}

	// testing valid takeOut with wide range of amounts
	@Test
	public void testTakeOut2() {
		ATM s1 = new ATM(5, 3, 3);
		ATM s2 = s1.takeOut(120);

		assertEquals(s1.getHundreds(), 4);
		assertEquals(s1.getFifties(), 3);
		assertEquals(s1.getTwenties(), 2);

		assertEquals(s2.getHundreds(), 1);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 1);
	}

	// testing putIn for valid low numbers
	@Test
	public void testPutIn() {
		ATM s1 = new ATM();
		s1.putIn(2, 3, 4);
		assertEquals(s1.getHundreds(), 2);
		assertEquals(s1.getFifties(), 3);
		assertEquals(s1.getTwenties(), 4);
	}

	// testing putIn and takeOut together
	@Test
	public void testPutInTakeOut() {
		ATM s1 = new ATM();
		s1.putIn(3, 3, 2);
		s1.takeOut(1, 1, 1);
		assertEquals(s1.getHundreds(), 2);
		assertEquals(s1.getFifties(), 2);
		assertEquals(s1.getTwenties(), 1);
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual() {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 5, 4);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
	}

	// testing compareTo all returns
	@Test
	public void testCompareTo() {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 3, 4);
		ATM s4 = new ATM(2, 5, 4);

		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		assertTrue(s1.compareTo(s4) == 0);
	}

	// load and save combined.
	@Test
	public void testLoadSave() {
		ATM s1 = new ATM(6, 5, 4);
		ATM s2 = new ATM(6, 5, 4);

		s1.save("file1");
		s1 = new ATM(); // resets to zero

		s1.load("file1");
		assertTrue(s1.equals(s2));

	}

	// testing not able to make change
	@Test
	public void testTakeOutNull1() {
		ATM s1 = new ATM(3, 1, 2);
		ATM s2 = s1.takeOut(700);
		assertEquals(s2, null);
	}

	public ATM myTakeOut(int totalAmount, int hundreds, int fifties,
			int twenties) {

		if (totalAmount <= (hundreds * 100 + fifties * 50 + twenties * 20)) {
			for (int a = hundreds; a >= 0; a--) {
				for (int b = fifties; b >= 0; b--) {
					for (int c = twenties; c >= 0; c--) {

						if ((a * 100 + b * 50 + c * 20) == totalAmount) {
							hundreds -= a;
							fifties -= b;
							twenties -= c;
							return new ATM(a, b, c);
						}
					}
				}
			}
		}
		return null;
	}

	@Test
	public void testTakeSuperTest() {
		int amount = 30;

		for (int h = 0; h < amount; h++) {
			System.out.println(h);
			for (int f = 0; f < amount; f++)
				for (int t = 0; t < amount; t++)
					for (int m = 10; m < ((amount * 100) + (amount * 50)
							+ (amount * 20) + 10); m += 10) {

						ATM a1 = new ATM(h, f, t);
						ATM b1 = a1.takeOut(m);

						ATM a2 = new ATM(h, f, t);
						ATM b2 = myTakeOut(m, h, f, t);
						// System.out.print("h: " + h + " f: " + f + " t: " + t
						// + " m:" + m + "\n");
						// assertEquals(a1, a2);

						assertEquals(b1, b2);
					}
		}
	}

	@Test
	public void testTakeOut3() {
		ATM s1 = new ATM(0, 50, 3);
		ATM s2 = s1.takeOut(160);
		assertEquals(s1.getHundreds(), 0);
		assertEquals(s1.getFifties(), 48);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 2);
		assertEquals(s2.getTwenties(), 3);
	}

	@Test
	public void testTakeOut4() {
		ATM s1 = new ATM(5, 1, 4);
		ATM s2 = s1.takeOut(180);
		assertEquals(s1.getHundreds(), 4);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 1);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 4);
	}

	@Test
	public void testTakeOut4a() {
		ATM s1 = new ATM(1, 1, 4);
		ATM s2 = s1.takeOut(130);
		assertEquals(s1.getHundreds(), 1);
		assertEquals(s1.getFifties(), 0);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 1);
		assertEquals(s2.getTwenties(), 4);
	}

	@Test
	public void testTakeOut4b() {
		ATM s1 = new ATM(5, 1, 4);
		ATM s2 = s1.takeOut(180);
		assertEquals(s1.getHundreds(), 4);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 1);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 4);
	}

	@Test
	public void testTakeOut5() {
		ATM s1 = new ATM(0, 1, 4);
		ATM s2 = s1.takeOut(80);
		assertEquals(s1.getHundreds(), 0);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 0);
		assertEquals(s2.getTwenties(), 4);
	}

	@Test
	public void testTakeOut6() {
		ATM s1 = new ATM(0, 3, 3);
		ATM s2 = s1.takeOut(160);
		assertEquals(s1.getHundreds(), 0);
		assertEquals(s1.getFifties(), 1);
		assertEquals(s1.getTwenties(), 0);

		assertEquals(s2.getHundreds(), 0);
		assertEquals(s2.getFifties(), 2);
		assertEquals(s2.getTwenties(), 3);
	}

	@Test
	public void testMutate() {
		ATM s1 = new ATM(6, 5, 4);
		ATM.suspend(true);
		s1.takeOut(120);
		assertEquals(s1.getHundreds(), 6);
		assertEquals(s1.getFifties(), 5);
		assertEquals(s1.getTwenties(), 4);
		ATM.suspend(false);
	}

	// IMPORTANT: only one test per exception!!!

	// testing negative number for nickels, takeOut
	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegTwenties() {
		ATM s1 = new ATM(2, 2, 2);
		s1.takeOut(1, 1, -1);
	}

	// testing negative number quarters, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegHunderies() {
		new ATM(-300, 0, 0);
	}

	// testing negative number for quarters, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNeghunderies() {
		ATM s = new ATM(2, 3, 4);
		s.putIn(-30, 2, 30);
	}
}

/*
 * 
 * 
 * I included this test cases because it represents good examples.
 * 
 * 
 * 
 * 
 * public void TestItALL() {
 * 
 * for (int q = 0; q < 10; q++) for (int d = 0; d < 10; d++) for (int n = 0; n <
 * 10; n++) for (int p = 0; p < 10; p++) for (double amt = 0.01; amt < 500; amt
 * += .01) { ChangeJar j1 = new ChangeJar(q,d,n,p); ChangeJar j2 = new
 * ChangeJar(q,d,n,p); ChangeJar j3 = new ChangeJar(q,d,n,p); ChangeJar j4 = new
 * ChangeJar(q,d,n,p);
 * 
 * try { ChangeJar j11 = j1.takeOut(amt); ChangeJar j12 = j2.takeOut1(amt);
 * ChangeJar j13 = j3.takeOut2(amt); ChangeJar j14 = j4.takeOut3(amt);
 * 
 * if (j11 != null) if (!(j11.equals(j12))) { System.out.println ("" + q + " " +
 * d + " " + n + " " + p); System.out.println ("1v2" + j11 + j12 + amt); } if
 * (j12 != null) if (!(j12.equals(j11))) { System.out.println ("" + q + " " + d
 * + " " + n + " " + p); System.out.println ("2v1" + j11 + j12 + amt); } if (j12
 * != null) if (!(j12.equals(j13))) { System.out.println ("" + q + " " + d + " "
 * + n + " " + p); System.out.println ("2v3" + j12 + j13 + amt); } if (j13 !=
 * null) if (!(j13.equals(j12))) { System.out.println ("" + q + " " + d + " " +
 * n + " " + p); System.out.println ("3v2" + j12 + j13 + amt); } if (j13 !=
 * null) if (!(j13.equals(j14))) { System.out.println ("" + q + " " + d + " " +
 * n + " " + p); System.out.println ("3v4" + j13 + j14 + amt); } if (j14 !=
 * null) if (!(j14.equals(j13))) { System.out.println ("" + q + " " + d + " " +
 * n + " " + p); System.out.println ("4v3" + j13 + j14 + amt); } assertEquals
 * (j11, j12); assertEquals (j12, j13); assertEquals (j13, j14);
 * 
 * 
 * 
 * } catch (Exception e) {
 * 
 * }
 * 
 * 
 * }
 * 
 * 
 * }
 */

