package project44;

import java.io.Serializable;

/**********************************************************************
 * A double linked node used in MyDoubleLinkedList
 * @author Atone Joryman
 * @version Summer 2017
 * @param <T> generic casting
 *********************************************************************/
public class DNode<T> implements Serializable{
	
	/** the value stored in the node */
	private Object data;
	
	/** the next node in the list */
	private DNode<T> next;
	
	/** the previous node in the list */
	private DNode<T> previous;
	
/***********************************************************************
 * constructor for DNode
 * @param data the value stored in the node
 * @param next the next node in the list
 * @param previous the previous node in the list
***********************************************************************/
	public DNode(Object data, DNode<T> next, DNode<T> previous){
		this.data = data;
		this.next = next;
		this.previous = previous;
	}

/***********************************************************************
 * getter method for the data node
 * @return previous
***********************************************************************/
	public Object getData() {
		return data;
	}
/***********************************************************************
 * setter method for the data node
 * @param data
***********************************************************************/
	public void setData(Object data) {
		this.data = data;
	}
/***********************************************************************
 * getter method for the next node
 * @return previous
***********************************************************************/
	public DNode<T> getNext() {
		return next;
	}
/***********************************************************************
 * setter method for the next node
 * @param next
***********************************************************************/
	public void setNext(DNode<T> next) {
		this.next = next;
	}
/***********************************************************************
 * getter method for the previos node
 * @return previous
***********************************************************************/
	public DNode<T> getPrevious() {
		return previous;
	}
/***********************************************************************
 * setter method for the previos node
 * @param previous
***********************************************************************/
	public void setPrevious(DNode<T> previous) {
		this.previous = previous;
	}
	
	
} 
