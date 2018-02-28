package labb88;

public class CountIt {
	private long fib(int n) {
		if (n < 3) {
			return 1;
		}
		else {
			return fib(n - 1) + fib (n - 2);
		}
	}
	public static void main(String[] args) {
		CountIt r = new CountIt();
		Long t = System.currentTimeMillis();
		System.out.println(r. fib (55));
		System.out.println ("Time:" + (System.currentTimeMillis() - t));
	}
}



