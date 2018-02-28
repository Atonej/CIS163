package week12;

public class MyQ {
	private int front;
	private int back;
	private int Q[];
	private int size;
	
	public MyQ (int size) {
		back = front = 0;
		Q = new int[size];
		size = 0;
	}
	
	public void enQ (int item) {
//		Q[(back = (back + 1) % Q.length)] = item;
//		size++;
		
		Q[back++] = item;
		if (back == Q.length)
			back = 0;
	}
	
	public int deQ() {
		int temp = Q[front++];
		if (front == Q.length)
			front = 0;
		return temp;
	}
	
	
	public static void main(String[]args) {
		
		MyQ s = new MyQ(5);
		s.enQ(1);
		s.enQ(2);
		System.out.print(s.deQ());
	}
}