package week5;

import java.io.IOException;


public class Poly {
	
	private double a,b,c; 
	
	public Poly(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 * 
	 * @return
	 * @throws NegSqrtException
	 */
	
	public double getQuadSolveNeg() throws NegSqrtException  
	{
		if  (b * b < 4 * a * c) 
			throw (new NegSqrtException ("Negative sqrt"));
		
		if (a == 0) 
			throw (new ArithmeticException ());
		
		return (-b - Math.sqrt(b * b + 4 * a * c) / (2 * a));
	}
	
	/**
	 * 
	 * @return
	 * @throws PosSqrtException
	 */
	public double getQuadSolvePos()  
	{
		if  (b * b < 4 * a * c)
			throw (new PosSqrtException ("Postive sqrt"));
		
		if (a == 0) 
			throw (new ArithmeticException ());
			
		return (-b + Math.sqrt(b * b + 4 * a * c) / (2 * a));
	}
	
}

