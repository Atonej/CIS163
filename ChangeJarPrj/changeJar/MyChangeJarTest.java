package changeJar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MyChangeJarTest {

	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegQuarters() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 2);
		s1.takeOut(-1, 1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegDimes() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 2);
		s1.takeOut(1, -1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegNickels() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 2);
		s1.takeOut(1, 1, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegPennies() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 2);
		s1.takeOut(1, 1, 1, -1);
	}
//	@Test(expected = IllegalArgumentException.class)
//	public void testTakeOutLetterInQuarters() {
//			ChangeJar s1 = new ChangeJar(3, 1, 2, 2);
//			ChangeJar s2 = s1.takeOut(a);
//			assertEquals(s2, null);
//		}

	@Test(expected = IllegalArgumentException.class)
	public void testTakeOutNegNull() {
		ChangeJar s1 = new ChangeJar(3, 1, 2, 2);
		ChangeJar s2 = s1.takeOut(-1.22);
		assertEquals(s2, null);
	}
	@Test
	public void testToStringQZero() {
		ChangeJar s1 = new ChangeJar(0, 2, 2, 2);
		String str = s1.toString();
		assertTrue(str.contains("0 quarter"));
		assertTrue(str.contains("2 dimes"));
		assertTrue(str.contains("2 nickels"));
		assertTrue(str.contains("2 pennies"));

	}
	@Test
	public void testToStringDZero() {
		ChangeJar s1 = new ChangeJar(2, 0, 2, 2);
		String str = s1.toString();
		assertTrue(str.contains("2 quarter"));
		assertTrue(str.contains("0 dimes"));
		assertTrue(str.contains("2 nickels"));
		assertTrue(str.contains("2 pennies"));

	}
	@Test
	public void testToStringNZero() {
		ChangeJar s1 = new ChangeJar(2, 2, 0, 2);
		String str = s1.toString();
		assertTrue(str.contains("2 quarter"));
		assertTrue(str.contains("2 dimes"));
		assertTrue(str.contains("0 nickels"));
		assertTrue(str.contains("2 pennies"));

	}
	@Test
	public void testToStringPZero() {
		ChangeJar s1 = new ChangeJar(2, 2, 2, 0);
		String str = s1.toString();
		assertTrue(str.contains("2 quarter"));
		assertTrue(str.contains("2 dimes"));
		assertTrue(str.contains("2 nickels"));
		assertTrue(str.contains("0 pennies"));

	}
	
	@Test
	public void testSuspendNegALL() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.suspend(true);
		s1.takeOut(-1, -1, -1, -1);
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
	}
	@Test
	public void testSuspendNegQ() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.suspend(true);
		s1.takeOut(-1, 1, 1, 1);
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
	}
	@Test
	public void testSuspendNegD() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.suspend(true);
		s1.takeOut(1, -1, 1, 1);
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
	}
	@Test
	public void testSuspendNegN() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.suspend(true);
		s1.takeOut(1, 1, -1, 1);
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
	}
	@Test
	public void testSuspendNegP() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.suspend(true);
		s1.takeOut(1, 1, 1, -1);
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
	}
	@Test
	public void testSuspendAfter() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(1, 1, 1, 1);
		s1.suspend(true);
		assertEquals(4, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(4, s1.getPennies());
		
		s1.takeOut(1,1,1,1);
		
		assertEquals(4, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(4, s1.getPennies());
		
	}
	
	@Test
	public void testSuspendAfterOnOff() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(1, 1, 1, 1);
		s1.suspend(true);
		assertEquals(4, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(4, s1.getPennies());
		
		s1.takeOut(1,1,1,1);
		
		assertEquals(4, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(4, s1.getPennies());
		
		s1.suspend(false);
		
		s1.takeOut(1,1,1,1);
		
		assertEquals(3, s1.getQuarters());
		assertEquals(3, s1.getDimes());
		assertEquals(3, s1.getNickels());
		assertEquals(3, s1.getPennies());
		
	}
	
	@Test
	public void testTakeOutPutIn() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(1, 1, 1, 1);

		assertEquals(4, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(4, s1.getPennies());
		
		s1.putIn(1,1,1,1);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
	}
	
	@Test
	public void testTakeOutPutInJustQuarters() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(1, 0, 0, 0);

		assertEquals(4, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
		s1.putIn(1,0,0,0);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
	}
	
	@Test
	public void testTakeOutPutInJustDimes() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(0, 1, 0, 0);

		assertEquals(5, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
		s1.putIn(0,1,0,0);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
	}
	
	@Test
	public void testTakeOutPutInJustNickels() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(0, 0, 1, 0);

		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(4, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
		s1.putIn(0,0,1,0);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
	}
	@Test
	public void testTakeOutPutInJustPennies() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(0, 0, 0, 1);

		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(4, s1.getPennies());
		
		s1.putIn(0,0,0,1);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
	}
	
	@Test
	public void testTakeOutPutInSuspend() {
		ChangeJar s1 = new ChangeJar(5, 5, 5, 5);
		s1.takeOut(0, 1, 0, 0);

		assertEquals(5, s1.getQuarters());
		assertEquals(4, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
		s1.putIn(0,1,0,0);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
		s1.suspend(true);
		s1.takeOut(0, 331, 540, 30);
		
		assertEquals(5, s1.getQuarters());
		assertEquals(5, s1.getDimes());
		assertEquals(5, s1.getNickels());
		assertEquals(5, s1.getPennies());
		
		
	}
	
	@Test
	public void testLoadSave() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(8, 5, 4, 2);

		s1.save("file1");
		s1 = new ChangeJar(); // resets to zero

		s1.load("file1");
		assertFalse(s1.equals(s2));

	}
	
	@Test
	public void testSave() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
		s1.save("file1");

	}
	
	@Test
	public void testLoad() {
		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
		ChangeJar s2 = new ChangeJar(8, 5, 4, 2);

		s1.load("file1");
		assertFalse(s1.equals(s2));

	}
	
//	@Test(expected = IOException.class)
//	public void testLoad1() {
//		ChangeJar s1 = new ChangeJar(6, 5, 4, 2);
//
//		s1.load("file1");
//
//
//	}
	
	@Test
	public void testPutInLarge() {
		ChangeJar s1 = new ChangeJar();
		s1.putIn(2, 3, 4, 500000000);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 3);
		assertEquals(s1.getNickels(), 4);
		assertEquals(s1.getPennies(), 500000000);
	}
	
	@Test
	public void testTakeOutLarge() {
		ChangeJar s1 = new ChangeJar(30000,3,2,1);
		s1.takeOut(500, 1, 1, 0);
		assertEquals(s1.getQuarters(), 29500);
		assertEquals(s1.getDimes(), 2);
		assertEquals(s1.getNickels(), 1);
		assertEquals(s1.getPennies(), 1);
	}
	
	@Test 
	public void testTakeOutQIllegal() {
		ChangeJar s1 = new ChangeJar(2,3,2,1);
		s1.takeOut(4, 1, 1, 0);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 3);
		assertEquals(s1.getNickels(), 2);
		assertEquals(s1.getPennies(), 1);
	}
	@Test 
	public void testTakeOutDIllegal() {
		ChangeJar s1 = new ChangeJar(2,3,2,1);
		s1.takeOut(1, 5, 1, 0);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 3);
		assertEquals(s1.getNickels(), 2);
		assertEquals(s1.getPennies(), 1);
	}
	@Test
	public void testTakeOutNIllegal() {
		ChangeJar s1 = new ChangeJar(2,3,2,1);
		s1.takeOut(1, 1, 6, 0);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 3);
		assertEquals(s1.getNickels(), 2);
		assertEquals(s1.getPennies(), 1);
	}
	@Test 
	public void testTakeOutPIllegal() {
		ChangeJar s1 = new ChangeJar(2,3,2,1);
		s1.takeOut(1, 1, 1, 7);
		assertEquals(s1.getQuarters(), 2);
		assertEquals(s1.getDimes(), 3);
		assertEquals(s1.getNickels(), 2);
		assertEquals(s1.getPennies(), 1);
	}
}

