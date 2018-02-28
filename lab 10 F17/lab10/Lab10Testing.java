package lab10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Lab10Testing {

	// nothing in top
	@Test
	public void emptyList() {
		LinkListLab s = new LinkListLab();
		assertEquals(s.getLen(), 0);
	}

	// only one element
	@Test
	public void onlyOne() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		assertEquals(s.getLen(), 1);
	}

	// more than one element in list
	@Test
	public void moreThanOne() {
		LinkListLab s = new LinkListLab();
		LinkListLab s2 = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");

		assertEquals(s.getLen(), 2);

		s2 = s;
		s2.insertBefore(1, "Lunch");

		assertEquals(s2.getLen(), 3);
	}
	
	//test insertBefore
	@Test
	public void insertBeforeTest() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");
			
		assertTrue(s.getLen() == 2);
	}
	
	//test insertAfter
	@Test
	public void insertAfterTest() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertAfter(0, "Breakfast");
		s.insertAfter(1, "Lunch");
			
		assertTrue(s.getLen() == 3);
	}
	
	//remove top test
	@Test
	public void removeTopTest() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");
		s.insertAfter(1, "Lunch");
		s.removeTop();
			
		assertTrue(s.getLen() == 2);
	}
	
	//do it all
		@Test
		public void allMethodsTest() {
			LinkListLab s = new LinkListLab();
			s.insertBefore(0, "Dinner");
			s.insertBefore(0, "Breakfast");
			s.insertAfter(1, "Lunch");
			s.removeTop();
			s.delAt(1);
				
			assertTrue(s.getLen() == 1);
			}

	// insertBefore out of range
	@Test
	public void insertBeforeError() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");
		s.insertBefore(1, "Lunch");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.insertBefore(3, "Desert");

		});

	}

	// insertBefore after out of range
	@Test
	public void insertBeforeError2() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");
		s.insertBefore(1, "Lunch");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.insertAfter(3, "Desert");

		});

	}

	// insertBefore before out of range
	@Test
	public void insertBeforeError3() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");
		s.insertBefore(1, "Lunch");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.insertBefore(-1, "Desert");

		});

	}

	// insertAfter out of range
	@Test
	public void insertAfterError() {
		LinkListLab s = new LinkListLab();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.insertAfter(0, "Desert");

		});

	}

	// delAt out of range
	@Test
	public void delAtError() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertBefore(0, "Breakfast");
		s.insertBefore(1, "Lunch");
		s.insertBefore(2, "Desert");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			s.delAt(4);

		});

	}

	// delAt top is empty
	@Test
	public void removeTopError() {
		LinkListLab s = new LinkListLab();

		Assertions.assertThrows(RuntimeException.class, () -> {
			s.removeTop();

		});

	}

	// delAt top is empty
	@Test
	public void removeTopError2() {
		LinkListLab s = new LinkListLab();
		s.insertBefore(0, "Dinner");
		s.insertAfter(0, "Breakfast");
		s.removeTop();
		s.removeTop();
		
		Assertions.assertThrows(RuntimeException.class, () -> {
			s.removeTop();

		});

	}

}
