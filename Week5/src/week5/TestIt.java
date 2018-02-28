package week5;


public class TestIt {

	public static void main(String[] args) //throws MyException
	{	
		TestIt t = new TestIt();
		try {
			t.myMain();
		} catch (NegSqrtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void myMain () throws NegSqrtException   
	{
		Poly p = new Poly (0,2,4);		

		double x = p.getQuadSolveNeg();


		try {
			//			Poly p = new Poly (8,2,1);			
			System.out.println("Negative = " + p.getQuadSolveNeg());
		}

		catch (ArithmeticException e) {
			System.out.println ("output1: " + e.getClass().toString());
			System.out.println ("output2: " + e.getMessage());
			System.out.println ("output3: " + e.toString());
			System.out.println ("output4: " + e.fillInStackTrace());
			System.out.println ("output5: " + e.getStackTrace());
		}

		catch (NegSqrtException e) {
			System.out.println ("Here I am");
			System.out.println (e.getClass().toString());
			System.out.println (e.getMessage());
			System.out.println (e.getSpecialMessage());
		}

		finally {
			System.out.println ("cleaning up program");	
		}

		System.out.println ("I continue from here");


	}

}