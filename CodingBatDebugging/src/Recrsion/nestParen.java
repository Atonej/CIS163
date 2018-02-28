package Recursion;

public class nestParen {

	private String result;
	public nestParen(String str) {
this.result = str;
		}

	public boolean paren(String str) {
		if(str.length() == 0){
		return true;
		}
		
		if(str.charAt(0) == '(') {
		if(str.charAt(str.length() - 1) == ')') {
		return paren(str.substring(1, str.length() - 1));
		}
		return false;
		} else {
		return false;
		}
		}
	public static void main(String[] args){
		nestParen s = new nestParen("()");
		System.out.println(s.paren("()"));
		System.out.println(s.paren("x(xyz)heyhey"));
		System.out.println(s.paren(" "));
		System.out.println(s.paren(""));
		
	}
}
