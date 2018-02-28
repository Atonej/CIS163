package campingsystem;

/**********************************************************************
 * SiteInfo is a 5 slot array of booleans for if a site is available.

 * @version November 1 2017
 *********************************************************************/
public class SiteInfo {

	private boolean[] available;  
	
	public SiteInfo() {
		available = new boolean[5];
	}

	public boolean[] getAvailable() {
		return available;
	}

	public void setAvailable(boolean[] available) {
		this.available = available;
	}
	
	public boolean getAt(int i) {
		return available[i-1];
	}
	
	public void setAt(int i, boolean value) {
		available[i-1] = value;
	}
	
	public boolean allReserved() {
		
		for(int i = 0; i < 5; i++) 
			if(available[i] == false)
				return false;
		
		return true;
		
	}
	
	public String toString() {
		return "" + available[0] + available[1] + available[2] + available[3] + available[4];
	}
 }