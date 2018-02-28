package project44;

import java.io.Serializable;

/**********************************************************************
 * Double linked list created by me for use in RentalStore.
 * @author Gregory Huizenga
 * @version 7/26/2017
 * @param <E>
 *********************************************************************/
public class MyDoubleLinkedList<E> implements Serializable {
	
	/** the first node in the list */
	private DNode<E> top;
	
	/** the last node in the list */
	private DNode<E> tail;
	
	/** the size of the list */
	public int size;
	
	/******************************************************************
	 * default constructor for DLinkedList
	 *****************************************************************/
	public MyDoubleLinkedList(){
		top = null;
		tail = null;
		size = 0;
	}
	
	/******************************************************************
	 * gets current size of list
	 * @return size size of list
	 *****************************************************************/
	public int size(){
		return size;
	}
	
	/******************************************************************
	 * clears all elements in list
	 *****************************************************************/
	public void clear(){
		top = null;
		tail = null;
		size = 0;
	}
	
	/******************************************************************
	 * returns the element stored at the specified index
	 * @param index
	 * @return
	 *****************************************************************/
	public Object get(int index){
		int i = 0;
	
		DNode<E> temp;
		// only executes if index is legal
		if (index >= 0 && index < size){
			temp = top;
			while (i < index){
				temp = temp.getNext();
				i++;
			}
		}else{
			return null;
		}
		
		return temp.getData();
	}
	
	/******************************************************************
	 * removes all elements in list with specified value
	 * @param s the element to be removed
	 * @return boolean whether at least 1 element was removed
	 *****************************************************************/
	public boolean removeAll(E s){
		DNode<E> temp = top;
		int i = 0;
		boolean removed = false;
		
		//iterates until end of list
		while (temp != null){
			if (temp.getData() == s){
				remove(i);
				removed = true;
				size--;
			}
			temp = temp.getNext();
			i++;
		}
		
		return removed;
	}
	
	/******************************************************************
	 * searches list for specified element
	 * @param s the element to search for
	 * @return int the index of the element, -1 if not found
	 *****************************************************************/
	public int find(E s){
		DNode<E> temp = top;
		int i = 0;
		
		// iterates until last element in list
		while (temp != null){
			if (temp.getData() == s){
				return i;
			}
			temp = temp.getNext();
			i++;
		}
		return -1;
	}
	
	/******************************************************************
	 * removes the object at the specified index
	 * @param index index of object to be removed
	 * @return Object the object that was removed
	 *****************************************************************/
	public Object remove(int index){
		int i = 0;
		DNode<E> temp = top;
		
		//only executes if index is legal
		if (index >= 0 && index < size){
			while (i < index){
				temp = temp.getNext();
				i++;
			}
			
			//case 0, first item
			if (index == 0){
				top = top.getNext();
				size--;
				return temp.getData();
			}
			
			//case 1, last item
			if (index == size - 1){
				temp = tail;
				tail = tail.getPrevious();
				size--;
				return temp.getData();
			}
				//case 2, item with next and previous
				
				temp.getPrevious().setNext(temp.getNext());
				temp.getNext().setPrevious(temp.getPrevious());
				size--;
				return temp.getData();
			
		}
		return null;
	}
	
	/******************************************************************
	 * adds specified element at beginning of list
	 * @param s the element to add
	 *****************************************************************/
	public void addFirst (E s){
		
		//case 0, 1 element exists in list
		if (size == 1){
			tail = top;
			top.setData(s);
			top.setNext(tail);
			tail.setPrevious(top);
			size++;
			return;
		}
		
		//case 1, list is empty
		if (size == 0){
			top = new DNode<E>(s, null, null);
			size++;
			return;
		}
		
		//case 2, list contains at least 2 elements
		DNode<E> temp = new DNode(top.getData(), top.getNext(), top);
		top.getNext().setPrevious(temp);
		top.setNext(temp);
		top.setData(s);
		size++;
		
	}
	
	/******************************************************************
	 * adds specified element to the end of the list
	 * @param s the element to be added
	 *****************************************************************/
	public void add(E s){
		
		//case 0, list is empty
		if (size == 0){
			top = new DNode<E>(s, null, null);
			size++;
			return;
		}
		
		//case 1, list has 1 element
		if (size == 1){
			tail = new DNode<E>(s, null, top);
			top.setNext(tail);
			size++;
			return;
		}
		
		//case 2, list has at least 2 elements
		DNode<E> temp = new DNode(tail.getData(), null, tail.getPrevious());
		tail.getPrevious().setNext(temp);
		tail.setPrevious(temp);
		temp.setNext(tail);
		tail.setData(s);
		size++;
		
	}
}
