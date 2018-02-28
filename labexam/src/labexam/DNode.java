package labexam;

import java.io.Serializable;
/**
 * 
 * @author jorymana
 *
 */
public class DNode<T> implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Object data;
private DNode<T> next;
private DNode<T> previous;

public DNode(Object data, DNode<T> next, DNode<T> previous) {
	this.data= data;
	this.next = next;
	this.previous = previous;
}
public Object getDate() {
	return data;
}
public void setDate(Object date) {
	this.data = date;
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
