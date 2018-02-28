package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
public class MExceptionMulti {
	public void method1 () 
	{			
		try {
			ArrayList l = new ArrayList(-10);
		}
		catch (IllegalArgumentException e) {
			System.out.println ("here 1");
			throw e;
		}
		catch (Exception e) {
			System.out.println ("here 2");
			throw new IllegalArgumentException();
		}
		finally {
			System.out.println ("here I am");
			// throw new IndexOutOfBoundsException();
		}
		System.out.println ("here 4");
	}
	public static void main(String[] args) throws IOException    {
		MExceptionMulti example = new MExceptionMulti();

		example.method2();
		try {
			example.method1();
			example.method2();
			System.out.println ("I am after the method");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println ("here A");
			throw e;
		}
		catch (NumberFormatException e) {
			System.out.println ("here B1");
		}
		catch (Exception e) {
			System.out.println ("here B2");
		}
		catch (Throwable e) {
			System.out.println ("here B3");
		}
		System.out.println ("Here at the end of it all!!!");
	}





	public void method2 () throws IOException
	{
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
		System.out.println ("How much do you weight?");
		String inputit="";	
		
		inputit = in.readLine();
		int weight = Integer.parseInt(inputit);

	}

}


















