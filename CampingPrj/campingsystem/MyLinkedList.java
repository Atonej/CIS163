package campingsystem;


/***********************************************************************
 * Custom made list, generic type allows for the implementing a list of 
 * type Site
 * 
 * @author Atone Joryman
 *
 * @version Fall 2017
 **********************************************************************/
public class MyLinkedList<E> {
	private Node<E> top;
	private Node<E> tail;
	private int size;

	public MyLinkedList() {
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

	public int size() {
		return size;
	}


	/****************************************************************
	 * 
	 * Inserts a node after a specific index. When the list is empty 
	 * that is, top = null, then the index must be 0. After the first 
	 * element is added, index must be: 0 <= index < size of list
	 * 
	 * @param site
	 *            a specific index into the list.
	 * 
	 ****************************************************************/

	public void add(Site site) {

		// case 0, list is empty
		if (size == 0) {
			top = new Node<E>(site, null);
			size++;
			return;
		}

		// case 1, list has 1 element
		if (size == 1) {
			tail = new Node<E>(site, null);
			top.setNext(tail);
			size++;
			return;
		}

		// case 2, list has at least 2 elements
		Node<E> temp = new Node<E>(site, null);
		//set temp first then make tail to temp
		tail.setNext(temp);
		temp.setNext(temp.getNext());
		tail = temp;
		size++;

	}
	/****************************************************************
	 * 
	 * Inserts a node after a specific index. When the list is empty 
	 * that is, top = null, then the index must be 0. After the first 
	 * element is added, index must be: 0 <= index < size of list
	 * 
	 * @param index
	 *            a specific index into the list.
	 * @param data
	 * 				insert site reservation
	 * 
	 * @throws IllegalArgumentStringxception
	 *             if index < 0 or index >= size of the list
	 * 
	 ****************************************************************/
	public void add(int index, Site data) {

		if (index < -1 || index >= (size() - 1)) {
			throw new IllegalArgumentException("Not within the bounds");
		}

		// iterates as an insert after
		int current = 0;
		Node<E> temp = top;
		while (current < (index)) {
			current++;
			temp = temp.getNext();
		}

		// creates new node and inserts after the index, points to old 
		//next value
		Node<E> temp2 = new Node<E>(data, temp.getNext());
		temp.setNext(temp2);
		size++;

		// iterates to last item of list and sets it as tail element
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}

		//set back to tail
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

	public Site removeTop() {

		if (top == null) {
			throw new RuntimeException("Top of list is null!");
		}

		Site data = top.getData();
		top = top.getNext();
		size--;
		return data;
	}

	/****************************************************************
	 * 
	 * This Method removes a node at the specific index position. 
	 * The first node is index 0.
	 * 
	 * @param index
	 *            the position in the linked list that is to be removed. 
	 *            The first position is zero.
	 * 
	 * @throws IllegalArgumentStringxception
	 *             if index < 0 or index >= size of the list
	 * 
	 ****************************************************************/

	public void remove(int index) {

		int i = 0;
		Node<E> temp = top;

		// only executes if index is legal
		if (index >= 0 && index < size) {
			while (i < index) {
				temp = temp.getNext();
				i++;
			}

			// case 0, first item
			if (index == 0 && size > 0) {
				top = top.getNext();
				size--;
				// return temp.getData();
			}

			// case 1, last item
			else if (index == size - 1) {
				temp = tail;
				// tail = tail.getPrevious();
				size--;
				// return temp.getData();
			}
			// case 2, item with next and previous
			else {
				temp.setData(temp.getNext().getData());
				size--;
			}
			// return temp.getData();

		}

	}
	
	/******************************************************************
	 * removes all elements in list with specified value
	 * 
	 * @param index
	 *            the element to be received
	 * @return Site whether possible to receive index
	 *****************************************************************/
	public Site get(int index) {

		int i = 0;

		Node<E> temp;
		// only executes if index is legal
		if (index >= 0 && index < size) {
			temp = top;
			while (i < index) {
				temp = temp.getNext();
				i++;
			}
		} else {
			return null;
		}

		return temp.getData();

	}

	/******************************************************************
	 * searches list for specified element
	 * 
	 * @param find
	 *            the element to search for
	 * @return generic type the index of the element, -1 if not found
	 *****************************************************************/
	public int indexOf(E find) {
		Node<E> temp = top;
		int i = 0;

		// iterates until last element in list
		while (temp != null) {
			if (temp.getData() == find) {
				return i;
			}
			temp = temp.getNext();
			i++;
		}
		return -1;
	}

	/******************************************************************
	 * removes all elements in list with specified value
	 * 
	 *            the element to be removed
	 * @return boolean whether at least 1 element was removed
	 *****************************************************************/
	public boolean clear() {
		Node<E> temp = top;
		int i = 0;
		boolean removed = false;

		// iterates until end of list
		while (temp != null) {
			remove(i);
			removed = true;
			size--;
			//set temp to next
			temp = temp.getNext();
			i++;
		}

		return removed;
	}

}
