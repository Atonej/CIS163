package project4;

import java.io.Serializable;

/**********************************************************************
 * double node to be referenced in MyDoubleLinkedList
 * @author Atone Joryman
 * @version 7/31/2017
 * @param <T> generic casting for node
 *********************************************************************/
public class DNode<T> implements Serializable{
	
	/** the value stored in the node */
	private Object data;
	
	/** the next node in the list */
	private DNode<T> next;
	
	/** the previous node in the list */
	private DNode<T> previous;
	
	/******************************************************************
	 * constructor for DNode
	 * @param data the value stored in the node
	 * @param next the next node in the list
	 * @param previous the previous node in the list
	 *****************************************************************/
	public DNode(Object data, DNode<T> next, DNode<T> previous){
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public DNode<T> getNext() {
		return next;
	}

	public void setNext(DNode<T> next) {
		this.next = next;
	}

	public DNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DNode<T> previous) {
		this.previous = previous;
	}
	
	
} 

