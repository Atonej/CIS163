package campingsystem;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/***********************************************************************
 * Testing of the MyLinkedList class, JUnits
 * 
 * @author Atone Joryman
 *
 * @version Fall 2017
 **********************************************************************/
class MyLinkedListTest {

	// nothing in top
	@Test
	public void emptyList() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		assertEquals(s.size(), 0);
	}

	// only one element
	@Test
	public void onlyOne() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		s.add(site);
		assertEquals(s.size(), 1);
	}

	// more than one element in list
	@Test
	public void moreThanOne() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site s2 = new Site();
		Site s3 = new Site();
		s.add(s2);
		s.add(s3);

		assertEquals(s.size(), 2);

		Site s4 = new Site();
		s.add(s4);

		assertEquals(s.size(), 3);
	}

	// test insertAfter
	@Test
	public void indexOfTop() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.removeTop();

		assertTrue(s.indexOf(site2) == 0);
	}

	@Test
	public void indexOfMiddle() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		Site site4 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.add(site4);

		assertTrue(s.indexOf(site3) == 2);
	}

	// check tail index
	@Test
	public void indexOfTail() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		Site site4 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.add(site4);

		assertTrue(s.indexOf(site4) == 3);
	}

	// clear test
	@Test
	public void clearTest() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.clear();

		assertTrue(s.clear() == true);
	}

	// clear it all
	@Test
	public void bothRemoves() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.removeTop();
		s.remove(2);

		assertTrue(s.size() == 2);
	}

	// get in range
	@Test
	public void get() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);

		assertTrue(s.get(1) == site2);

	}

	// remove an index
	@Test
	public void remove() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.remove(2);

		assertTrue(s.size() == 2);

	}
	
	@Test
	public void addOn() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		s.add(site);
		s.add(site2);
		s.add(0,site);
		s.add(1,site2);

			assertTrue(s.size()==4);


		
		
		
	}

	// check if site are moving correctly
	@Test
	public void removeGet() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		Site site3 = new Site();
		s.add(site);
		s.add(site2);
		s.add(site3);
		s.remove(2);

		assertTrue(s.get(1) == site2);

	}

	// insertBefore after out of range
	@Test
	public void insertBeforeError2() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site3 = new Site();

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.add(1, site3);

		});

	}

	// insertBefore before out of range
	@Test
	public void insertBeforeError3() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.add(-1, site);

		});

	}

	// delAt is null
	@Test
	public void delAtError() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Assertions.assertThrows(RuntimeException.class, () -> {
			s.removeTop();

		});

	}

	// delAt top is empty
	@Test
	public void removeTopError() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();

		Assertions.assertThrows(RuntimeException.class, () -> {
			s.removeTop();

		});

	}

	// delAt top is empty
	@Test
	public void removeTopError2() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		s.add(site);
		s.add(site2);
		s.removeTop();
		s.removeTop();

		Assertions.assertThrows(RuntimeException.class, () -> {
			s.removeTop();

		});

	}
	//check if re add is error
	@Test
	public void addOnError() {
		MyLinkedList<Site> s = new MyLinkedList<Site>();
		Site site = new Site();
		Site site2 = new Site();
		s.add(site);
		s.add(site2);
		s.removeTop();
		s.removeTop();

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.add(0,site);

		});
		
		
		
	}

}
