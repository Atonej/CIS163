package campingsystem;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**********************************************************************
 * Site - Simulates a camp site reservation.
 * 
 * @author Atone Joryman
 * 
 * @version Fall 2017
 *********************************************************************/
@SuppressWarnings("serial")
public class Site implements Serializable {

	/** Name of the person occupying the site */
	protected String nameReserving;

	/** Date the site was checked-in */
	protected GregorianCalendar checkIn;

	/** Number of days a customer is staying */
	protected int daysStaying;

	/** Site number */
	protected int siteNum;

	private int lateFee;

	/******************************************************************
	 * Constructor method for Site class. Instantiates a new site
	 *****************************************************************/
	public Site() {
		nameReserving = "";
		checkIn = new GregorianCalendar(2017, Calendar.OCTOBER, 10);
		daysStaying = 0;
		siteNum = 0;
	}

	/******************************************************************
	 * Constructor method for the Site class. Accepts values for the parameters
	 * for the site requested.
	 * 
	 * @param nameReserving
	 *            - the name the site is reserved under
	 * @param checkIn
	 *            - the check in date requested
	 * @param daysStaying
	 *            - the number of days the site will be occupied
	 * @param siteNum
	 *            - the site number requested for the site
	 *****************************************************************/
	public Site(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, int siteNum) {

		this.checkIn = checkIn;
		this.nameReserving = nameReserving;
		this.daysStaying = daysStaying;
		this.siteNum = siteNum;
	}

	/******************************************************************
	 * A method that sets the name of the reservation
	 * 
	 * @param nameReserving
	 *            - the name of the reservation
	 *****************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	/******************************************************************
	 * A method that gets the name of the reservation
	 * 
	 * @returns the name under the reservation
	 *****************************************************************/
	public String getNameReserving() {
		return this.nameReserving;
	}

	/******************************************************************
	 * A method that sets the date of the reservation
	 * 
	 * @param checkIn
	 *            - the date of the reservation
	 *****************************************************************/
	public void setCheckIn(GregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}

	/******************************************************************
	 * A method that gets the date of the reservation
	 * 
	 * @returns the date of the reservation
	 *****************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}

	/******************************************************************
	 * A method that sets the number of days an occupants plans on staying
	 * 
	 * @param daysStaying
	 *            - the number the occupant plans on staying
	 *****************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	/******************************************************************
	 * A method that gets the number of days an occupant plans on staying
	 * 
	 * @returns the number of days the occupant plans on staying
	 *****************************************************************/
	public int getDaysStaying() {
		return this.daysStaying;
	}

	/******************************************************************
	 * A method that sets the site number of the reservation
	 * 
	 * @param siteNum
	 *            - the site number of the reservation
	 *****************************************************************/
	public void setSiteNum(int siteNum) {
		this.siteNum = siteNum;
	}

	/******************************************************************
	 * A method that gets the site number of the reservation
	 * 
	 * @returns the site number of the reservation
	 *****************************************************************/
	public int getSiteNum() {
		return this.siteNum;
	}

	/******************************************************************
	 * getter method that will allow user to know cost of reservation
	 * 
	 * @returns the price of reservation of type double
	 *****************************************************************/
	public double cost() {
		// cost comes from tent and rv
		return 0;
	}

	/******************************************************************
	 * setter method that will allow user to know latefee of reservation
	 * 
	 * @param the extra days recorded of reservation of type int
	 *****************************************************************/
	public void setLateFee(int extraDays) {
		// latefee comes from tent and rv
		lateFee += extraDays * 10;
	}

	/******************************************************************
	 * getter method that will allow user to know latefee of reservation
	 * 
	 * @returns the late fee of reservation of type double
	 *****************************************************************/
	public double getLateFee() {
		return lateFee;
	}
}