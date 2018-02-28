package campingsystem;

import java.util.GregorianCalendar;
/**********************************************************************
 * RV - Creates a child object of Site that simulates a RV 
 * reservation on the campsite.
 * 

 * @version November 1 2017
 *********************************************************************/

@SuppressWarnings("serial")
public class RV extends Site{
	
	/** The amount of power supplied to the site depending on RV size*/
	private int power;
	private int lateFee = 0;
	
	/******************************************************************
	 * Constructor method for the RV class. Instantiates a new RV 
	 * and sets the number of amps of power to zero
	 *****************************************************************/
	public RV() {
		super();
		power = 0;
	}
	
	/******************************************************************
	 * Constructor method for the RV class. Accepts values for the
	 * parameters for the RV requested.
	 * @param nameReserving - the name the tent is reserved under
	 * @param checkIn - the check in date requested
	 * @param daysStaying - the number of days the Tent will be 
	 * 			occupied
	 * @param siteNum - the site number requested for the tent
	 * @param power - the number of amps requested for the RV 
	 * 			reservation
	 ******************************************************************/
	public RV(String nameReserving, GregorianCalendar checkIn,  
			int daysStaying, int siteNum, int power) {
		
		super(nameReserving, checkIn, daysStaying, siteNum);
		this.power = power;
	}
	/******************************************************************
	 * A method that sets the amps that an RV reservation takes	
	 * @param power - the number of amps requested for the RV 
	 * 			reservation
	 *****************************************************************/
	public void setPow(int power) {
		this.power = power;
	}
	
	/******************************************************************
	 * A method that gets the amps that an RV reservation takes
	 * @returns the amount of amps that an RV reservation 
	 * 			requested
	 *****************************************************************/
	public int getPow() {
		return this.power;
	}
	
	
	/******************************************************************
	 * A method that returns the cost of the RV reservation based on
	 * how long the reservation is for
	 * @returns the cost of the RV reservation
	 *****************************************************************/
	public double cost() {
		return this.daysStaying * 30 + getLateFee();
	}
	
	public void setLateFee(int extraDays) {
		if(getPow() == 50) {
			lateFee += extraDays*5;
		}
		lateFee += extraDays*10;
	}
	
	public double getLateFee(){
		return lateFee;
	}
}