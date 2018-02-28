package week5;

import javax.swing.JOptionPane;

public class SimpleTryExample {

	public static void main (String[] args) {
		int n = 0;
		int i;

		try {
			if (n != 0)
				i = 5 / n;		
			System.out.println ("Hi");
		}

		catch (ArithmeticException e) {

		}
	}
}
