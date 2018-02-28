package labexam;
/**
 * 
 * @author jorymana
 *
 */
public class MyDoubleLinkedList<E> {
	private DNode<E> top;
	private DNode<E> tail;
	public int size;
	
	public int size() {
		return size;
	}
	
	public Object get(int index) {
		int i =0;
		
		DNode<E> temp;
		if(index>=0 && index < size) {
			temp = top;
			while(i<index) {
				temp = temp.getNext();
				i++;
			}
			
		}
		else {
			return null;
		}
		
		return temp.getDate();
	}
public void add(E s) {
	
	if(size== 0) {
		top = new DNode<E>(s,null,null);
		size++;
		return;
	}
	
	if(size == 1) {
		tail = new DNode<E>(s,null,top);
		top.setNext(tail);
		size++;
		return;
	}
	
	DNode<E> temp = new DNode<E>(tail.getDate(),null,tail.getPrevious());
	tail.getPrevious().setNext(temp);
	tail.setPrevious(temp);
	temp.setNext(tail);
	tail.setDate(s);
	size++;
}

//public boolean clear() {
//	Node<E> temp = top;
//	int i =0;
//	boolean removed = false;
//	
//	while(temp!=null) {
//		remove(i);
//		removed =true; 
//		size--;
//		temp = temp.get
//	}
//}
}
