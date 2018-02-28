package campingsystem;

import java.util.GregorianCalendar;

/**********************************************************************
 * Tent - Creates a child object of Site that simulates a Tent 
 * reservation on the campsite.
 * 
 
 * @version November 1 2017
 *********************************************************************/

@SuppressWarnings("serial")
public class Tent extends Site{
	
	/** Number of people staying at the tent site*/
	private int numTenters;
	private int lateFee;
	
	/******************************************************************
	 * Constructor method for the Tent class. Instantiates a new Tent 
	 * and sets the number of occupants to zero
	 *****************************************************************/
	public Tent() {
		super();
		numTenters = 0;
	}
	/******************************************************************
	 * Constructor method for the Tent class. Accepts values for the
	 * parameters for the tent requested.
	 * @param nameReserving - the name the tent is reserved under
	 * @param checkIn - the check in date requested
	 * @param daysStaying - the number of days the Tent will be 
	 * 			occupied
	 * @param siteNum - the site number requested for the tent
	 * @param numTenters - the number of occupants a tent reservation
	 * 			has
	 *****************************************************************/
	public Tent(String nameReserving,  GregorianCalendar checkIn, 
			int daysStaying, int siteNum, int numTenters) {
		super(nameReserving, checkIn, daysStaying, siteNum);
		this.numTenters = numTenters;
	}
	
	/******************************************************************
	 * A method that sets the number of occupants for a tent site
	 * @param numTenters - the number of occupants for a tent site
	 *****************************************************************/
	public void setNumTenters(int numTenters) {
		this.numTenters = numTenters;
	}
	
	/******************************************************************
	 * A method that returns the number of occupants for a tent site
	 * @returns the number of occupants for a tent site
	 *****************************************************************/
	public int getNumTenters() {
		return this.numTenters;
	}
	
	/******************************************************************
	 * A method that returns the cost of the tent reservation based on
	 * the number of occupants and how long the reservation is for
	 * @returns the cost of the tent reservation
	 *****************************************************************/
	public double cost() {
		return this.numTenters * 3 * this.daysStaying + getLateFee();
	}
	
	public void setLateFee(int extraDays) {
//		if(extraDays == null) {
//			lateFee = 0;
//		}
		lateFee += extraDays*5;
	}
	
	public double getLateFee(){
		return lateFee;
	}
}