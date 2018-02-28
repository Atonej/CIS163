package project1;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestStopWatch {

/**********************************************************************
 * JUnit Testing for the StopWatch class to test for all errors
 * 
 * @author Atone Joryman
 * @version Fall 2017
***********************************************************************/
	
	//test constructor and all fields
	@Test
	public void testConstructor() {
		StopWatch s = new StopWatch (5,10,300);
		assertEquals(s.toString(),"5:10:300");
		
		s = new StopWatch("20:10:8");
		assertEquals(s.toString(),"20:10:008");
		
		s = new StopWatch("20:8");
		assertEquals(s.toString(),"0:20:008");
		
		s = new StopWatch("8");
		assertEquals(s.toString(),"0:00:008");

	}
	
	//test constructor and big numbers
		@Test
		public void testConstructorHuge() {
			StopWatch s = new StopWatch (1000,59,999);
			assertEquals(s.toString(),"1000:59:999");
			
			s = new StopWatch("200:10:8");
			assertEquals(s.toString(),"200:10:008");
			
					
			s = new StopWatch("999");
			assertEquals(s.toString(),"0:00:999");

		}
		
		//test constructor for zeros
				@Test
				public void testConstructorZero() {
					StopWatch s = new StopWatch ("00:0000:00000");
					assertEquals(s.toString(),"0:00:000");
					
					s = new StopWatch("00:0000:0000");
					assertEquals(s.toString(),"0:00:000");
					
					s = new StopWatch("0000:0");
					assertEquals(s.toString(),"0:00:000");
					
					s = new StopWatch("000");
					assertEquals(s.toString(),"0:00:000");

				}
				//test constructor ability to convert
				@Test
				public void testConstructorConvertMin() {
					StopWatch s = new StopWatch ("1:62:2");
					System.out.println("testConstructorConvertSec: "+
					s.toString());
					assertEquals(s.toString(),"2:02:002");
					
									}
				//test constructor ability to convert
				@Test
				public void testConstructorConvertSec() {
					StopWatch s = new StopWatch ("3:32:1200");
					System.out.println("testConstructorConvertSec: "+
					s.toString());
					assertEquals(s.toString(),"3:33:200");
					
					s = new StopWatch("30:1450");
					System.out.println("testConstructorConvertSec.2: "+
							s.toString());
					assertEquals(s.toString(), "0:31:450");
					
					
									}
				//test for characters
				@Test (expected = IllegalArgumentException.class)
				public void testConstructerChar() {
					new StopWatch("a");
					
				}
				
				//test for characters
				@Test (expected = IllegalArgumentException.class)
				public void testConstructerChar1() {
					new StopWatch("a:-c:3");
					
				}
				
				//test for characters
				@Test (expected = IllegalArgumentException.class)
				public void testConstructerChar2() {
					new StopWatch("z:W");
					
				}
	
	// There can only be one test here
	// no more lines of code after "new StopWatch("-2");"
	@Test (expected = IllegalArgumentException.class)
	public void testNegSingleInput() {
		new StopWatch("-2");
		
	}
	//two negatives
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble1Input() {
		new StopWatch("-59:-23");
		
	}
	
	//three negatives
		@Test (expected = IllegalArgumentException.class)
		public void testNegTriple1Input() {
			new StopWatch("-59:-23:-765");
			
		}
		
		
		//test below the boundary as negative
		@Test (expected = IllegalArgumentException.class)
		public void testNegBelow1Input() {
			new StopWatch("-63");
			
		}
		
		//test below the boundary as negative
				@Test (expected = IllegalArgumentException.class)
				public void testNegBelow2Input() {
					new StopWatch("-63:-2000");
					
				}
				
				//test below the boundary as negative
				@Test (expected = IllegalArgumentException.class)
				public void testNegBelow3Input() {
					new StopWatch("-63:-432:-122344");
					
				}
				
				//test all three but with one negative
				@Test (expected = IllegalArgumentException.class)
				public void testNegChooseOne() {
					new StopWatch("54:3:-216");
					
				}
				
				//test all three with one negative and high bounds
				@Test (expected = IllegalArgumentException.class)
				public void testNegChooseOneTooHigh() {
					new StopWatch("2000:-3:0000");
					
				}
	
	//test through all times 
	@Test 
	public void testAlotInput() {
		for (int m = 0; m < 50; m++)
			for (int s = 0; s < 60; s++)
				for (int ms = 0; ms < 1000; ms++) {
					String st = m + ":" + s + ":" + ms;
					StopWatch d = new StopWatch(st);
					assertEquals(m, d.getMinutes());
					assertEquals(s, d.getSeconds());
					assertEquals(ms, d.getMilliseconds());					
				}
	}
	
	 //negative one input
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2aInput() {
		new StopWatch("2:-2");
		
	}
	//negative sec input
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2bInput() {
		new StopWatch("-2:7");
		
	}
	//add a negative 
	@Test (expected = IllegalArgumentException.class)
	public void testNegAddInput() {
		StopWatch s1 = new StopWatch();
		s1.add(-2);
	}
	//subtract a negative
	@Test (expected = IllegalArgumentException.class)
	public void testNegSubInput() {
		StopWatch s1 = new StopWatch();
		s1.sub(-2);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testAlphaInput() {
		new StopWatch("a");

	}
	
//	@Test (expected = IllegalArgumentException.class)
//	public void testAddInput() {
//		StopWatch s1 = new StopWatch();
//		s1.add('a');
//
//	}
	
//	@Test (expected = IllegalArgumentException.class)
//	public void testSubInput() {
//		StopWatch s1 = new StopWatch();
//		s1.sub('a');
//
//	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegDouble2cInput() {
		new StopWatch("-2:-7");
		
	}
//test the add method
	@Test
	public void testAddMethod () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.add(2000);
		assertEquals (s1.toString(),"6:01:300");
		
		s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,2,300);
		s1.add(s2);
		System.out.println ("testAddMethod.1 : " + s1);
		assertEquals (s1.toString(),"8:01:600");
	
		for (int i = 0; i < 15000; i++)
			s1.inc();
		System.out.println ("testAddMethod.2 : " + s1);
		assertEquals (s1.toString(),"8:16:600");
	}
	//test add using a for loop
	@Test 
	public void testAddMethod2(){
		StopWatch s1 = new StopWatch(3,43,123);
		for(int i=0; i<250;i++)
				s1.add(1000);
		assertEquals(s1.toString(),"7:53:123");
	}
	//test the substract method 
	@Test
	public void testSubMethod () {
		StopWatch s1 = new StopWatch (5,59,300);
		s1.sub(2000);
		System.out.println("testSubMethod.1 : " + s1);
		assertEquals (s1.toString(),"5:57:300");
		
		s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (2,2,300);
		s1.sub(s2);
		System.out.println ("testSubMethod.2 : " + s1);
		assertEquals (s1.toString(),"3:57:000");
	
		for (int i = 0; i < 15000; i++)
			s1.dec();
		System.out.println ("testSubMethod.3 : " + s1);
		assertEquals (s1.toString(),"3:42:000");
	}
	
	//test two negatives
	 @Test (expected = IllegalArgumentException.class)
	    public void testContuctor() {
		 new StopWatch("2,-3,-3");
		 
	    }
//test equal method
	@Test 
	public void testEqual () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));
		
		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);
	
	}
	
	//test compareTo method
	@Test 
	public void testCompareTo () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (6,01,200);
		StopWatch s3 = new StopWatch (5,50,200);
		StopWatch s4 = new StopWatch (5,59,300);

		assertFalse(s1.equals(s2));
		assertTrue (s1.equals(s4));
		
		assertTrue (s2.compareTo(s1) > 0);
		assertTrue (s3.compareTo(s1) < 0);
		assertTrue (s1.compareTo(s4) == 0);
	
	}
//Test load and saving 		
	@Test 
	public void testLoadSave () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);

		s1.save("file1");
		s1 = new StopWatch ();  // resets to zero

		s1.load("file1");
		assertTrue (s1.equals(s2));
	}
	
	//Test load and saving out of bounds	
		@Test (expected= IllegalArgumentException.class)
		public void testLoadSave2 () {
			StopWatch s1 = new StopWatch (50,59,3000);
			StopWatch s2 = new StopWatch (50,59,3000);

			s1.save("file2");
			s1 = new StopWatch ();  // resets to zero

			s1.load("file2");
			assertTrue (s1.equals(s2));
		}
		
		//Test load and saving out of bounds	
				@Test 
				public void testLoadSave3 () {
					StopWatch s1 = new StopWatch (5,59,30);
					StopWatch s2 = new StopWatch (5,59,30);

					s1.save("file2kaljkdsljlfajlclk");
					s1 = new StopWatch ();  // resets to zero

					s1.load("file2kaljkdsljlfajlclk");
					assertTrue (s1.equals(s2));
				}
				
				//Test load and saving symbols	
				@Test 
				public void testLoadSave4 () {
					StopWatch s1 = new StopWatch (5,59,30);
					StopWatch s2 = new StopWatch (5,59,30);

					s1.save("-A@#%");
					s1 = new StopWatch ();  // resets to zero

					s1.load("-A@#%");
					assertTrue (s1.equals(s2));
					
					
				}
	//if add true
	@Test 
	public void testMutate () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);
		
		StopWatch.suspend(true);
		s1.add(1000);
		assertTrue (s1.equals(s2));	
		StopWatch.suspend(false);
		}
	//if add false
	@Test 
	public void testMutate2 () {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);
		
		s1.add(1000);
		StopWatch.suspend(false);
		assertFalse(s1.equals(s2));
		}
	
	//if sub true
		@Test 
		public void testMutate3 () {
			StopWatch s1 = new StopWatch (5,59,300);
			StopWatch s2 = new StopWatch (5,59,300);
			
			s1.sub(1000);
			StopWatch.suspend(true);
			assertFalse(s1.equals(s2));
			}
		
		//if sub false
		@Test 
		public void testMutate4 () {
			StopWatch s1 = new StopWatch (5,59,300);
			StopWatch s2 = new StopWatch (5,59,300);
			
			s1.add(1000);
			StopWatch.suspend(false);
			assertFalse(s1.equals(s2));
			}
//equal test of input
	@Test 
	public void equalsTest() {
		StopWatch s1 = new StopWatch (5,59,300);
		StopWatch s2 = new StopWatch (5,59,300);
		
		assertEquals(s1, s2);
	}
	
	//equal test of input
		@Test 
		public void equalsTest2() {
			StopWatch s1 = new StopWatch (5,61,300);
			StopWatch s2 = new StopWatch (6,01,300);
			
			assertNotEquals(s1, s2);
		}
		//testing on toString
		@Test
		public void toStringTest(){
		StopWatch s1 = new StopWatch(1,2,3);	
			s1.inc();
			assertEquals(s1.toString(),"1:02:004");
		}
		//test inc
		@Test
		public void incTest(){
		StopWatch s1 = new StopWatch(1,2,3);
		for(int i=0; i<2000;i++)
			s1.inc();
			assertEquals(s1.toString(),"1:04:003");
		}
		
		//test dec
				@Test
				public void decTest(){
				StopWatch s1 = new StopWatch(1,2,3);
				for(int i=2000; i>0;i--)
					s1.dec();
					assertEquals(s1.toString(),"1:00:003");
				}
				
				//test dec
				@Test
				public void decTest2(){
				StopWatch s1 = new StopWatch(1,2,3);
				for(int i=0; i<2000;i++)
					s1.dec();
					assertEquals(s1.toString(),"1:00:003");
				}
		// test milliseconds		
			@Test 
			public void getMilliTest(){
				StopWatch s1 = new StopWatch();
				assertEquals(s1.getMilliseconds(),0);
				
			}
			
			// test minutes		
						@Test 
						public void getMinTest(){
							StopWatch s1 = new StopWatch();
							assertEquals(s1.getMinutes(),0);
							
						}
						// test seconds		
						@Test 
						public void getSecTest(){
							StopWatch s1 = new StopWatch();
							assertEquals(s1.getSeconds(),0);
							
						}
						// test milliseconds		
						@Test 
						public void getMilliTest1(){
							StopWatch s1 = new StopWatch(999);
							assertEquals(s1.getMilliseconds(),999);
							
							s1= new StopWatch(3,545);
							assertEquals(s1.getMilliseconds(),545);
							
							s1= new StopWatch(3,23,214);
							assertEquals(s1.getMilliseconds(),214);
						}
						
						// test minutes		
						@Test 
						public void getMinTest1(){
							StopWatch s1 = new StopWatch(999);
							assertEquals(s1.getMinutes(),0);
							
							s1= new StopWatch(3,545);
							assertEquals(s1.getMinutes(),0);
							
							s1= new StopWatch(3,23,214);
							assertEquals(s1.getMinutes(),3);
						}
						
						// test sec		
						@Test 
						public void getSecTest1(){
							StopWatch s1 = new StopWatch(999);
							assertEquals(s1.getSeconds(),0);
							
							s1= new StopWatch(3,545);
							assertEquals(s1.getSeconds(),3);
							
							s1= new StopWatch(3,23,214);
							assertEquals(s1.getSeconds(),23);
						}
						
						// test all getters		
						@Test (expected= IllegalArgumentException.class)
						public void getEmAllTest(){
							StopWatch s1 = new StopWatch(1000);
							assertEquals(s1.getMilliseconds(),0);
							
							
							assertEquals(s1.getSeconds(),1);
							
							
							assertEquals(s1.getMinutes(),0);
						}
						//test set milli
						@Test
						public void setMilliTest(){
							StopWatch s1 = new StopWatch();
							s1.setMilliseconds(1);
							assertEquals(s1.getMilliseconds(),1);
							
						}
						
						//test set min
						@Test
						public void setMinTest(){
							StopWatch s1 = new StopWatch();
							s1.setMinutes(1);
							assertEquals(s1.getMinutes(),1);
							
						}
						
						//test set seconds
						@Test
						public void setSecTest(){
							StopWatch s1 = new StopWatch();
							s1.setSeconds(1);
							assertEquals(s1.getSeconds(),1);
							
						}
		
	
}
