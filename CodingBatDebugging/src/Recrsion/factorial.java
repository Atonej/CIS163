package Recursion;

public class factorial {
	private int num;
	public factorial(int n) {
this.num = n;
		}
	
	public int fac(int n) {
		  int i;
		  for(i=1; i<= n; ++i){
		    if(n<=1){
		    num = 1;
		    return i;
		    }
		    else
		    {
		      num*=i;
		      
		    
		  }
		    
		  }
		return this.num;
		}
	
	public static void main(String[] args){
		factorial s = new factorial(0);
		System.out.println(s.fac(0));
		System.out.println(s.fac(1));
		System.out.println(s.fac(3));
		
	}
}
