package Project3;

import java.util.GregorianCalendar;

/***********************************************************************
 * This class inherit the properties of Site to incorporate a Tent in 
 * the camp site
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ritcksgers 
 * @version Fall 2017 
 **********************************************************************/
public class Tent extends Site {
	/**Used for saving and loading a file**/
	private static final long serialVersionUID = 1L;
	
	/** Represents the number of tenters on this site **/
	private String numOfTenters;

/***********************************************************************
* This constructor will take in the parameters of its parent and add it 
* to the super class 
* @param the name of the person reserving the site
* @param using GregorianCalender give the time this person checked in
* @param of type integer give the number of days staying 
* @param give the position in line the site is in of type int
***********************************************************************/
	public Tent(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, String tenters, int siteNumber) {
		super(nameReserving, checkIn, daysStaying, siteNumber);
		this.numOfTenters = tenters;
		// TODO Auto-generated constructor stub
	}
	
	
public Tent() {
	// TODO Auto-generated constructor stub
}


/***********************************************************************
* Getter method for number of people in the tent 
* @return the power of the RV
***********************************************************************/
	public String getNumOfTenters() {
		return numOfTenters;
	}

/***********************************************************************
* Setter method for number of people in the tent 
* @return the power of the RV
***********************************************************************/
	public void setNumOfTenters(String tenters) {
		this.numOfTenters = tenters;
	}

public int cost(){
    	
    //make into a valid number	
	//if only one digit
	if(Integer.parseInt(getNumOfTenters().substring(0,1)) < 10) {
	return Integer.parseInt(getNumOfTenters().substring(0,1)) * (daysStaying * 3);
	}
	//double digits
	else {
		return Integer.parseInt(getNumOfTenters().substring(0,2)) * (daysStaying * 3);
	
	}
    	
    	
    }
	   // add constructor
	   // add getter, setter methods

}
