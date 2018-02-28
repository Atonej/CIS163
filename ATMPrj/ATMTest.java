package atmPack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ATMTest {

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
	// hundreds, fifties and twenties
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
	public void testLoadSave() {// check throw
		ATM s1 = new ATM(6, 5, 4);
		ATM s2 = new ATM(6, 5, 4);

		s1.save("file1");
		s1 = new ATM(); // resets to zero

		s1.load("file1");
		assertTrue(s1.equals(s2));

	}

	// testing not able to make change
	@Test
	public void testTakeOutNull() {
		ATM s1 = new ATM(3, 1, 2);
		ATM s2 = s1.takeOut(700);
		assertEquals(s2, null);
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
