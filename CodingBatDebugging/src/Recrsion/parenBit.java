package Recursion;

public class parenBit {
	
	private String result;
	public parenBit(String str) {
this.result = str;
		}

	public String parentBit(String str) {
		//String result = "";
		if(str.charAt(0) == '(') {
		if(str.charAt(str.length() - 1) == ')') {
		return str;
		} else {
		return parentBit(str.substring(0, str.length() - 1));
		}
		} else {
		return parentBit(str.substring(1, str.length()));
		}
		}
	public static void main(String[] args){
		parenBit s = new parenBit("()");
		System.out.println(s.parentBit("x(xyz)"));
		System.out.println(s.parentBit("x(xyz)heyhey"));
		System.out.println(s.parentBit("(xyz)"));
		System.out.println(s.parentBit("(xyz)b"));
		
	}
}
