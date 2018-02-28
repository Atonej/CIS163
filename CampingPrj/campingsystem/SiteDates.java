package campingsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

/**********************************************************************
 * SiteDates class that reserves the dates. Uses SiteInfo for array of booleans,
 * setting false if they're unreserved, true if reserved.
 * 
 * @author Atone Joryman
 * 
 * @version Fall 2017
 *********************************************************************/
public class SiteDates {

	/**
	 * Constant for number of years to add to HashMap of reservations
	 */
	private final int NUMBER_OF_YEARS = 20;

	/**
	 * HashMap using date Strings as Keys, and SiteInfo. Used for 
	 * reserving sites.
	 */
	private HashMap<String, SiteInfo> map;

	/** Formatter for date Strings */
	private SimpleDateFormat formatCal;


	/******************************************************************
	 * Instantiates a hashmap of Strings and SiteInfo, using Strings of 
	 * valid dates from the current date to number of years constant.
	 *****************************************************************/
	public SiteDates() {

		int totalDays = 0;
		// keep up with the current traced year
		// count days and see if leap year
		GregorianCalendar current = new GregorianCalendar();
		for (int year = 2017; year < 2017 + NUMBER_OF_YEARS; year++) {
			if (current.isLeapYear(year)) {
				totalDays += 366;
			}

			else {
				totalDays += 365;
			}
		}

		formatCal = new SimpleDateFormat("M/d/yyyy");
		map = new HashMap<String, SiteInfo>();
		// now set up adapting calendar
		for (int i = 0; i < (totalDays); i++) {
			;
			SiteInfo info = new SiteInfo();
			Calendar cal = Calendar.getInstance();
			// start the calendar at January 1st 2017
			cal.set(Calendar.MONTH, Calendar.JANUARY);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.YEAR, 2017);

			// add that iterated number to the date
			// set up the date as a string for hashmap
			cal.add(Calendar.DATE, i);
			String date = formatCal.format(cal.getTime());
			map.put(date, info);

		}
	}

	/******************************************************************
	 * Getter method of hashmap
	 * 
	 * @return hashmap of date strings and SiteInfo
	 *****************************************************************/
	public HashMap<String, SiteInfo> getHashMap() {
		return map;
	}

	/******************************************************************
	 * Checks if there is already a site reserved in the dates and site 
	 * that site parameter wishes to reserve in.
	 * 
	 * @param site
	 *            Site to check availability in hashmap
	 * @return true if already reserved, false if open.
	 * @throws ParseException
	 *             Shouldn't hit. Error check with parse.
	 * @throws InvalidDate
	 *             give the error for Invalid date
	 *****************************************************************/
	public boolean isReserved(Site site)
			throws ParseException, InvalidDate {

		String date = formatCal.format(site.getCheckIn().getTime());
		int daysStaying = site.getDaysStaying();
		int siteTemp = site.getSiteNum();

		for (int i = 0; i < daysStaying; i++) {
			try {
				if (map.get(date).getAt(siteTemp))
					return true;
				Calendar cal = Calendar.getInstance();
				cal.setTime(formatCal.parse(date));
				cal.add(Calendar.DATE, 1);
				date = formatCal.format(cal.getTime());

			} catch (NullPointerException e) {
				throw new InvalidDate();
			}
		}
		return false;
	}

	/******************************************************************
	 * Reserves a site on the requested days for parameter t
	 * 
	 * @param site
	 *            Site to reserve spots.
	 * @throws ParseException
	 *             Error during parsing
	 *****************************************************************/
	public void reserve(Site site) throws ParseException {

		String date = formatCal.format(site.getCheckIn().getTime());

		int daysStaying = site.getDaysStaying();
		int siteTemp = site.getSiteNum();

		for (int i = 0; i < daysStaying; i++) {

			// Set current date as taken (true)
			map.get(date).setAt(siteTemp, true);

			// Convert date to calendar to add 1 day
			Calendar cal = Calendar.getInstance();
			cal.setTime(formatCal.parse(date));
			cal.add(Calendar.DATE, 1);
			date = formatCal.format(cal.getTime());
		}
	}

	/******************************************************************
	 * Removes the reservation for the request site. Used in deletion or 
	 * undo methods.
	 * 
	 * @param site
	 *            Site to remove reservation
	 *****************************************************************/
	public void removeReservation(Site site) {
		try {
			String date = formatCal.format(site.getCheckIn().getTime());

			int daysStaying = site.getDaysStaying();
			int siteTemp = site.getSiteNum();

			for (int i = 0; i < daysStaying; i++) {

				// Set current date as taken (true)
				map.get(date).setAt(siteTemp, false);

				// Convert date to calendar to add 1 day
				Calendar cal = Calendar.getInstance();
				cal.setTime(formatCal.parse(date));
				cal.add(Calendar.DATE, 1);
				date = formatCal.format(cal.getTime());
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "ERROR");
		}

	}

	/******************************************************************
	 * Returns array of booleans to see the sites that are reserved or 
	 * open for the parameter date
	 * 
	 * @param date
	 *            date to check site's reservations.
	 * @return returns array of booleans for each site
	 *****************************************************************/
	public boolean[] reservedForDay(String date) {
		return map.get(date).getAvailable();
	}

	/******************************************************************
	 * Returns all dates where all sites are reserved.
	 * 
	 * @return ArrayList of Strings of dates that are reserved
	 *****************************************************************/
	public ArrayList<String> fullyReservedSites() {
		ArrayList<String> bookedDays = new ArrayList<String>();
		for (String keyDate : map.keySet()) {
			if (map.get(keyDate).allReserved()) {
				bookedDays.add(keyDate);
			}
		}
		return bookedDays;
	}

}