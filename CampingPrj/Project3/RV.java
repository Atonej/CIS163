package Project3;

import java.util.GregorianCalendar;

/***********************************************************************
 * This class inherit the properties of Site to incorporate an RV in the 
 * camp site
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ritcksgers 
 * @version Fall 2017 
 **********************************************************************/
public class RV extends Site {
	/**for saving and loading a file**/
	private static final long serialVersionUID = 1L;

	/** Represents the power supplied to the site */
	private String power;   // 30, 40, 50 amps of service.

	public RV() {

	}
	/***********************************************************************
	* This constructor will take in the parameters of its parent and add it 
	* to the super class 
	* @param the name of the person reserving the site
	* @param using GregorianCalender give the time this person checked in
	* @param of type integer give the number of days staying 
	* @param give the position in line the site is in of type int
	**********************************************************************/
	public RV(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, String power, int siteNumber) {
		super(nameReserving, checkIn, daysStaying, siteNumber);
		// TODO Auto-generated constructor stub

		this.power = power;
	}

	/***********************************************************************
	* Calculates the price for the customer's stay based on
	* their power needs and number of days staying.
	* @return the price for for the customer's stay
	***********************************************************************/
	public int cost(){
		return (Integer.parseInt(getPower().substring(0, 2).trim()) / 5 ) * (daysStaying * 3);    	
	}

	/***********************************************************************
	* Getter method for power the RV will exert 
	* @return the power of the RV
	***********************************************************************/
	public String getPower() {
		return power;
	}

	/***********************************************************************
	* Setter method for power the RV will exert 
	* @param the power of the RV
	***********************************************************************/
	public void setPower(String ampTxt) {
		this.power = ampTxt;
	}	
}