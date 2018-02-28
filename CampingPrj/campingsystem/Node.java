package campingsystem;

public class Node<T> {
	private Site data;
	private Node<T> next;

	public Node(Site site, Node<T> next) {
		this.data = site;
		this.next = next;
	}
	public Node() {
	}

	public Site getData() {
		return data;
	}
	
	public void setData(Site data) {
		this.data = data;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public Node<T> getNext() {
		return next;
	}
}


