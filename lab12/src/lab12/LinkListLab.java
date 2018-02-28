package lab12;
import javax.management.RuntimeErrorException;

public class LinkListLab {
	private Node top; 
	
	public LinkListLab() {
		top = null;
	}
	
	/****************************************************************
	* 
	* Determines the size, that is, the number of elements in the list
	*
	* @return  the size of the list
	* 
	****************************************************************/
	public int getLen() {
		int size = 1;
		Node temp = top;
		
		if (temp == null){
			return 0;
		}
		
		while (temp.getNext() != null){
			size++;
			temp = temp.getNext();
		}
		
		return size;
	}
	
	/****************************************************************
	* 
	* Inserts a node before a specific index.  When the list is empty
	* that is, top = null, then the index must be 0. After the first
	* element is added, index must be:  0 <= index < size of list  
	* 
	* @param index a specific index into the list.
	* 
	* 
	****************************************************************/
	
	public void insertBefore (int index, String data) {	
		
		// case 0, no list
		// simply places element at top
//		if (top == null){
//			top = new Node(data, null,top);
//			//tail = top;
//			return;
//		}
		
		// case 1, element placed at top of list
		// puts temp in tops current position and then moves top up
//		if (index == 0){
			
			// temp copies top's values, but doesn't point at top
//			Node temp = new Node(top.getData(), top.getNext(), top);
//			
//			top.setData(data);
//			top.setNext(temp);
//			top.setPrev(top);
//			return;
//		}
		
		// valid index not modifying top
//		if (index > 0 && index < getLen()){
			
			// iterates through linkedlist to "pre-index" node
//			int current = 0;
//			Node temp = top;
//			while (current < (index - 1)){
//				current++;
//				temp = temp.getNext();
//			}
			
			// creates new node and inserts it after pre-index
//			Node temp2 = new Node(data, temp.getNext(), temp.getPrev());
//			temp.setNext(temp2);
//			temp.setPrev(temp);
//			
//			return;
//		}
		
//		if (index < 0 || index >= getLen()){
//			throw new IndexOutOfBoundsException();
//		}
		
		if (top == null){
			top = new Node(data, null, null);
			return;
		}
		if (index <= 0){
			Node temp = new Node(top.getData(), top.getNext(), top);
			top.setNext(temp);
			top.setData(data);
			return;
		}
		
		insertAfter(index - 1, data);
		
	}
	
	/****************************************************************
	* 
	* Inserts a node after a specific index.  When the list is empty
	* that is, top = null, then the index must be 0. After the first
	* element is added, index must be:  0 <= index < size of list  
	* 
	* @param index a specific index into the list.
	* 
	* 
	****************************************************************/
	
	public void insertAfter (int index, String data) {	
		
		if (index < 0 || index >= getLen()){
			throw new IndexOutOfBoundsException();
		}
		
		// iterates similarly to insert before
		int current = 0;
		Node temp = top;
		while (current < index){
			current++;
			temp = temp.getNext();
		}
		
		// creates new node and inserts after the index, points to old next value
		Node temp2 = new Node(data, temp.getNext(), temp);
		temp.getNext().setPrev(temp2);
		temp.setNext(temp2);
		
	}

	/****************************************************************
	* 
	* Removes the top element of the list
	* 
	* @return returns the element that was removed.
	* 
	* @throws RuntimeStringxception if top == null, that is,
	* 			 there is no list.
	* 
	****************************************************************/
	
	public String removeTop () {
		String data;
		if(top == null){
			return "empty list";
		}
		
		data = top.getData();
		
		if(top.getNext() == null){
			top = null;
			return data;
		}
		
		top = top.getNext();
		
		return data;
	}
	
	
	/****************************************************************
	* 
	* This Method removes a node at the specific index position.  The
	* first node is index 0.
	* 
	* @param index the position in the linked list that is to be 
	* 			removed.  The first position is zero.  
	* 
	* @throws IllegalArgumentStringxception if index < 0 or 
	* 			index >= size of the list
	* 
	****************************************************************/
	public void delAt(int index) {
		
		if(index < 0 || index >= getLen()){
			throw new IllegalArgumentException();
		}
		
		int current = 0;
		Node temp = top;
		while (current < (index - 1)){
			current++;
			temp = temp.getNext();
		}
		
		temp.setNext(temp.getNext().getNext());
		temp.getNext().setPrev(temp);
		
		return;
		
	}
	
// A simple testing program.  Not complete but a good start.
	
	public static void main (String[] args){
		LinkListLab list = new LinkListLab();
		
		list.display();
		System.out.println ("Current length = " + list.getLen());
		
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

		list.removeTop();
		list.delAt(4);
		list.delAt(2);
		list.delAt(0);
		list.removeTop();
		list.removeTop();
			
		list.display();
	}

	public void display() {
		Node temp = top;

		System.out.println ("___________ List ________________________");
		while (temp != null) {
			System.out.println (temp.getData());
			temp = temp.getNext();
		}
	}
}





