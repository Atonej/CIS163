package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
public class Multiexceptions {
	public void method1 () 
	{			
		try {
			ArrayList x = null;
			x= new ArrayList(-10);
			System.out.print("A");
			System.out.print(x.get(-1));
			System.out.print("B");
		}
		catch(NullPointerException e){
			System.out.print("1");
			throw new IndexOutOfBoundsException();
		}
		catch (IndexOutOfBoundsException e) {
			System.out.print("2");
		}
		catch (RuntimeException e) {
			System.out.print ("3");
		}
		finally {
			System.out.print ("4");
			// throw new IndexOutOfBoundsException();
		}
	}
	public static void main(String[] args) {
		
		try {
			Multiexceptions q = new Multiexceptions();
			q.method1();
			System.out.print ("5");
		}
		catch (IllegalArgumentException e) {
			System.out.print ("6");
	
		}
		catch (RuntimeException e) {
			System.out.print ("7");
		}
		catch (Exception e) {
			System.out.println ("8");
		}

		System.out.print("9");
	}





}

















