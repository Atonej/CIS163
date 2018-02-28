package Project3;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


/***********************************************************************
 * This class creates the Site object. It will be the parent to other
 * classes that need to inherit the site's information. 
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ritcksgers 
 * @version Fall 2017 
 **********************************************************************/
public class Site implements Serializable {

	/** To store saved state of a class **/
	private static final long serialVersionUID = 1L;

	/** The name of the person who is occupying the Site */
	protected String nameReserving;

	/** The date the Site was checked-in (occupied) */
	protected GregorianCalendar checkIn;

	/** The estimated number of days the person is reserving */
	protected int daysStaying; 

	/** The Site number */
	protected int siteNumber;



	/***********************************************************************
	 * Generic site constructor.
	 **********************************************************************/
	public Site() {

	}

	/***********************************************************************
	 * This constructor will take in the information to design a site and 
	 * the information on the assigned site to a customer
	 * @param the name of the person reserving the site
	 * @param using GregorianCalender give the time this person checked in
	 * @param of type integer give the number of days staying 
	 * @param give the position in line the site is in
	 **********************************************************************/
	public Site(String nameReserving, GregorianCalendar checkIn,
			int daysStaying, int siteNumber) {
		super();
		this.nameReserving = nameReserving;
		this.checkIn = checkIn;
		this.daysStaying = daysStaying;
		this.siteNumber = siteNumber;
	}

	/***********************************************************************
	 * Getter method for the name that is reserving the site
	 * @return the name of the person 
	 **********************************************************************/
	public String getNameReserving() {
		return nameReserving;
	}

	/***********************************************************************
	 * Setter method for the name that is reserving the site
	 * @param the name of the person 
	 **********************************************************************/
	public void setNameReserving(String nameReserving) {
		this.nameReserving = nameReserving;
	}

	/***********************************************************************
	 * Getter method for the check in time 
	 * @return the time of the check in 
	 **********************************************************************/
	public GregorianCalendar getCheckIn() {
		return checkIn;
	}

	/***********************************************************************
	 * Setter method for the check in time
	 * @param the time of check in the GregorianCalender format
	 **********************************************************************/
	public void setCheckIn(GregorianCalendar checkIn) {
		this.checkIn = checkIn;
	}

	/***********************************************************************
	 * Getter method for number of days this person is staying
	 * @return the number of days staying 
	 **********************************************************************/
	public int getDaysStaying() {
		return daysStaying;
	}

	/***********************************************************************
	 * Setter method for telling the site number of days staying
	 * @param the number of days staying at the site
	 **********************************************************************/
	public void setDaysStaying(int daysStaying) {
		this.daysStaying = daysStaying;
	}

	/***********************************************************************
	 * Getter method for the number the site is located in line
	 * @return the number the site is in 
	 **********************************************************************/
	public int getSiteNumber() {
		return siteNumber;
	}

	/***********************************************************************
	 * Setter method for the chosen site number
	 * @return that site number
	 **********************************************************************/
	public void setSiteNumber(int site) {
		if (site <= 5)
			this.siteNumber = site;			
	}
}
