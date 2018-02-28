package lab10;

public class LinkListLab {
	private Node top;
	private Node tail;

	public LinkListLab() {
		top = null;
		tail = null;
	}

	/****************************************************************
	 * 
	 * Determines the size, that is, the number of elements in the list
	 *
	 * @return the size of the list
	 * 
	 ****************************************************************/

	public int getLen() {
		// if not null will always catch at least one value
		int size = 1;
		// set temp to top pointer
		Node temp = top;

		// if null
		if (temp == null) {
			return 0;
		}

		// go through each element of top until null
		while (temp.getNext() != null) {
			size++;
			temp = temp.getNext();
		}

		return size;
	}

	/****************************************************************
	 * 
	 * Inserts a node before a specific index. When the list is empty 
	 * that is, top = null, then the index must be 0. After the first 
	 * element is added, index must be: 0 <= index < size of list
	 * 
	 * @param index
	 *            a specific index into the list.
	 * 
	 * @throws IllegalArgumentStringxception
	 *             if index < 0 or index >= size of the list
	 * 
	 ****************************************************************/

	public void insertBefore(int index, String data) {

		// case 0, no list
		// simply places element at top
		if (top == null) {
			top = new Node(data, null);

			// tail is same as top
			tail = top;
			return;
		}

		// case 1, element placed at top of list
		// puts temp in tops current position and then moves top up
		if (index == 0) {

			// temp copies top's values, but doesn't point at top
			Node temp = new Node(top.getData(), top.getNext());

			top.setData(data);
			top.setNext(temp);
			return;
		}

		// valid index not modifying top or tail
		if (index > 0 && index < getLen()) {

			// iterates through linkedlist to "pre-index" node
			int current = 0;
			Node temp = top;
			while (current < (index - 1)) {
				current++;
				temp = temp.getNext();
			}

			// creates new node and inserts it after pre-index
			Node temp2 = new Node(data, temp.getNext());
			temp.setNext(temp2);

			return;
		}

		if (index < 0 || index >= getLen()) {
			throw new IllegalArgumentException("Not within the bounds");
		}
	}

	/****************************************************************
	 * 
	 * Inserts a node after a specific index. When the list is empty 
	 * that is, top = null, then the index must be 0. After the first 
	 * element is added, index must be: 0 <= index < size of list
	 * 
	 * @param index
	 *            a specific index into the list.
	 * 
	 * @throws IllegalArgumentStringxception
	 *             if index < 0 or index >= size of the list
	 * 
	 ****************************************************************/

	public void insertAfter(int index, String data) {

		if (index < -1 || index >= getLen()) {
			throw new IllegalArgumentException("Not within the bounds");
		}

		// iterates similarly to insert before
		int current = 0;
		Node temp = top;
		while (current < (index)) {
			current++;
			temp = temp.getNext();
		}

		// creates new node and inserts after the index, points to old next
		// value
		Node temp2 = new Node(data, temp.getNext());
		temp.setNext(temp2);

		// iterates to last item of list and sets it as tail element
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}

		tail = temp;

	}

	/****************************************************************
	 * 
	 * Removes the top element of the list
	 * 
	 * @return returns the element that was removed.
	 * 
	 * @throws RuntimeStringxception
	 *             if top == null, that is, there is no list.
	 * 
	 ****************************************************************/

	public String removeTop() {

		if (top == null) {
			throw new RuntimeException("Top of list is null!");
		}

		String data = top.getData();
		top = top.getNext();
		return data;
	}

	/****************************************************************
	 * 
	 * This Method removes a node at the specific index position. 
	 * The first node is index 0.
	 * 
	 * @param index
	 *            the position in the linked list that is to be removed. The
	 *            first position is zero.
	 * 
	 * @throws IllegalArgumentStringxception
	 *             if index < 0 or index >= size of the list
	 * 
	 ****************************************************************/

	public void delAt(int index) {

		if (index < 0 || index >= getLen()) {
			throw new IllegalArgumentException("Not within the bounds");
		}

		int current = 0;
		Node temp = top;
		//rank through the list until index found
		while (current < (index - 1)) {
			current++;
			temp = temp.getNext();
		}
		
		//make selected index to next index in order to "delete" current
		//index
		temp.setNext(temp.getNext().getNext());

		// restore and go through each element until null
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}
		tail = temp;

		return;

	}

	// A simple testing program. Not complete but a good start.

	public static void main(String[] args) {
		LinkListLab list = new LinkListLab();

		list.display();
		System.out.println("Current length = " + list.getLen());

		list.insertBefore(0, "apple");
		list.insertBefore(0, "pear");
		list.insertBefore(1, "peach");
		list.insertBefore(1, "cherry");
		list.insertBefore(3, "donut");
		list.insertAfter(0, "apple");
		list.insertAfter(0, "pear");
		list.insertAfter(1, "peach");
		list.insertAfter(1, "cherry");
		list.insertAfter(3, "donut");
		list.display();
		System.out.println("Current length = " + list.getLen());

		list.removeTop();
		list.delAt(4);
		list.delAt(2);
		list.delAt(0);
		list.removeTop();
		list.removeTop();

		list.display();
		System.out.println("Current length = " + list.getLen());

	}

	public void display() {
		Node temp = top;

		System.out.println("___________ List ________________________");
		while (temp != null) {
			System.out.println(temp.getData());
			temp = temp.getNext();
		}
	}
}
