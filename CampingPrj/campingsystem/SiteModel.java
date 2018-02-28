package campingsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EmptyStackException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

/**********************************************************************
 * SiteModel - Creates the model that the camp sites interact with. Can add,
 * remove, undo, display, and modify the lists.
 * 
 * @author Atone Joryman
 * 
 * @version Fall 2017
 *********************************************************************/

public class SiteModel extends AbstractTableModel {

	/**use to save a file*/
	private static final long serialVersionUID = 1L;

	/** Constant for number of columns */
	private final int COLUMN_COUNT = 5;

	/** Constant for amount of sites */
	private final int SITE_AMOUNT = 5;

	/** Number of sites used in saving and loading */
	private int countSites;

	/** ArrayList of Sites (RV or Tents) */
	private ArrayList<Site> listSites;

	/** Array of Strings for name of columns */
	private String[] columnNames = { "Name Reserving", "Checked In",
			"Days Staying", "Site #", "Tent/RV Info" };

	/** SiteDates variable to check if reserved */
	private SiteDates listDates;

	/** Stack of strings for actions to undo */
	private Stack<String> undoActions;

	/** Stack of sites to undo */
	private Stack<Site> undoSites;

	/** Stack of index */
	private Stack<Integer> index;

	/** keep of with what is being added */
	private boolean flag;

	/** Used to return status of interface */
	private String message;

	/** follow what is being checkout */
	private boolean checkout;

	/******************************************************************
	 * Constructor method for SiteModel class. Instantiates a new 
	 * ArrayList for sites (Takes both Tent and RV classes) 0(1)
	 *****************************************************************/
	public SiteModel() {
		listSites = new ArrayList<Site>();
		listDates = new SiteDates();
		undoSites = new Stack<Site>();
		undoActions = new Stack<String>();
		index = new Stack<Integer>();
		// setCheckOut(true);
	}

	/******************************************************************
	 * Method for AbstractTableModel class that returns amount of rows 
	 * needed for siteModel based on siteList size
	 * 
	 * @return returns the size of the site list, which is the amount 
	 * of checked in sites. 0(1)
	 *****************************************************************/
	public int getRowCount() {
		return listSites.size();
	}

	/******************************************************************
	 * Method for AbstractTableModel class that returns amount of 
	 * columns, which is always 5 in this project.
	 * 
	 * @return amount of columns, which is a constant. 0(1)
	 *****************************************************************/
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	/******************************************************************
	 * Returns name of columns for AbstractTableModel
	 * 
	 * @param column
	 *            number to get name for
	 * @return string of column name 0(1)
	 *****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/******************************************************************
	 * Calls dialogue box for Tent to add new Tent listing, and updates 
	 * the SiteModel to display it.
	 * 
	 * @throws ParseException
	 *             Error in parsing dates
	 * @throws InvalidDate
	 *             Date out of range
	 * @param tentRental
	 *            tent to be added to site 0(1)
	 *****************************************************************/
	public void addTent(Tent tentRental) {

		try {
			if (!listDates.isReserved(tentRental)) {

				// Add undo information to stacks
				undoSites.push(tentRental);
				undoActions.push("add");

				listDates.reserve(tentRental);
				listSites.add(tentRental);
				fireTableRowsUpdated(listSites.size(), COLUMN_COUNT);
				setIsAdded(true);
				setReturnStat("added");
			} else {
				setIsAdded(false);
				setReturnStat("Site already reserved!");
			}

		} catch (ParseException e) {
			setReturnStat("ERROR: Invalid Date");
		} catch (InvalidDate e) {
			setReturnStat("ERROR: Date out of range");
		}
	}
	
	
	public SiteDates getListDate() {
		return listDates;
	}

	/******************************************************************
	 * Calls dialogue box for RV to add new Tent listing, and updates 
	 * the SiteModel to display it.
	 * 
	 * @param RVRental
	 *            RV to add to list of sites 0(1)
	 *****************************************************************/
	public void addRV(RV RVRental) {

		try {
			if (!listDates.isReserved(RVRental)) {

				// Add undo stacks
				undoSites.push(RVRental);
				undoActions.push("add");
				;
				listDates.reserve(RVRental);
				listSites.add(RVRental);
				fireTableRowsUpdated(listSites.size(), COLUMN_COUNT);
				setIsAdded(true);
				setReturnStat("added");
			} else {
				setIsAdded(false);
			setReturnStat("Site already reserved!");
			}
			
		} catch (ParseException e) {
			setReturnStat("ERROR: Invalid Date");
		} catch (InvalidDate e) {
			setReturnStat("ERROR: Date out of range");
		}

	}

	/******************************************************************
	 * Deletes the selected row(s) from the table and listSites and 
	 * also adds to the undo list.
	 * 
	 * @param row
	 *            integer for row to delete 0(n^2)
	 *****************************************************************/

	public void deleteReservation(int[] row) {
		for (int collect : row) {
			undoSites.push(listSites.get(row[0]));
			undoActions.push("remove");
			index.push(row[0]);
			// opens site
			listDates.removeReservation(listSites.get(row[0]));
			listSites.remove(row[0]);
		}

		fireTableDataChanged();
	}

	/******************************************************************
	 * Deletes same as above method but not muliple for this is 
	 * specified for check out
	 * 
	 * @param row
	 *            integer for row to delete 0(n^2)
	 *****************************************************************/
	public void dayLeaving(int moveOut) {
		undoSites.push(listSites.get(moveOut));
		undoActions.push("remove");
		index.push(moveOut);

		listDates.removeReservation(listSites.get(moveOut));
		listSites.remove(moveOut);
		setCheckOut(true);

		fireTableDataChanged();
	}

	/*******************************************************************
	 * Getter method for the checkout of reservation
	 * 
	 * @return checkout the assigned site
	 ******************************************************************/
	public boolean getCheckOut() {
		return checkout;
	}

	/*******************************************************************
	 * Setter method for the checkout of reservation
	 * 
	 * @param checkout
	 *            set the assigned site type boolean
	 ******************************************************************/
	public void setCheckOut(boolean on) {
		checkout = on;
	}

	/******************************************************************
	 * Undoes the last action performed including adding, deleting, and 
	 * loading text and serial files. 0(1)
	 * 
	 * @return the string message, as an error or null
	 *****************************************************************/

	public void undo() {
		try {
			undoActions.peek();
			String action = undoActions.pop();

			// If action was to add, remove it instead
			if (action.equals("add")) {
				Site undone = undoSites.pop();
				listSites.remove(listSites.indexOf(undone));
				listDates.removeReservation(undone);
			}

			// If action was to remove, re-add it.
			else if (action.equals("remove")) {
				Site undone = undoSites.pop();
				listSites.add(index.pop(), undone);
				listDates.reserve(undone);
			}
			fireTableDataChanged();
			setReturnStat("null");
		} catch (EmptyStackException e) {
			setReturnStat("Nothing to Undo");
		} catch (ParseException e) {
			setReturnStat("ERROR Parse Exception");
		}
	}

	/******************************************************************
	 * Gets site availability for parameter date entered
	 * 
	 * @param date
	 *            date to check site availability
	 * @return boolean array, true if reserved, false if open. 0(1)
	 *****************************************************************/
	public boolean[] getArrayReserves(String date) {
		return listDates.reservedForDay(date);
	}

	/******************************************************************
	 * Tests site values to ensure integers are not out of ranges.
	 * 
	 * @param site
	 * @return boolean true if good input, false if invalid input. 0(1)
	 *****************************************************************/
	public boolean isValidInput(Site site) {

		// Test site number
		if (site.getSiteNum() <= 0 || site.getSiteNum() > SITE_AMOUNT)
			return false;

		// Test days staying
		if (site.getDaysStaying() <= 0)
			return false;

		// Test number of people at tent site
		if (site instanceof Tent) {
			if (((Tent) site).getNumTenters() <= 0)
				return false;
		}

		// Test power usage at RV
		else if (site instanceof RV) {
			if (((RV) site).getPow() != 30 && ((RV) site).getPow() != 40
					&& ((RV) site).getPow() != 50)

				return false;
		}
		return true;
	}

	/******************************************************************
	 * Method to display values on the table based on the row and column
	 * accessed based on parameters
	 * 
	 * @param row
	 *            number of row find variable of list
	 * @param col
	 *            number of column to find what value to print
	 * @return String object of what to display in row/column cell 0(1)
	 *****************************************************************/
	public Object getValueAt(int row, int col) {

		String temp = "";

		// Switch statements for all 5 columns (0,1,2,3,4)
		switch (col) {
		case 0:
			temp = listSites.get(row).getNameReserving();
			break;
		case 1:
			SimpleDateFormat formatCal = new SimpleDateFormat(
					"M/d/yyyy");
			temp = formatCal
					.format(listSites.get(row).getCheckIn().getTime());
			break;
		case 2:
			temp = listSites.get(row).getDaysStaying() + "";

			break;
		case 3:
			temp = listSites.get(row).getSiteNum() + "";
			break;
		case 4:
			if (listSites.get(row) instanceof Tent)
				temp = (((Tent) listSites.get(row)).getNumTenters()
						+ " Campers");
			else if (listSites.get(row) instanceof RV)
				temp = (((RV) listSites.get(row)).getPow() + " Amps");
			break;
		}

		return temp;

	}

	/*******************************************************************
	 * This getter method is use for the gui to see what site is being 
	 * used.
	 * 
	 * @return listSites the list that is being received
	 ******************************************************************/
	public ArrayList<Site> getList() {
		return listSites;
	}

	/*****************************************************************
	 * Calls fullyReservedSites of listDates to return all dates where 
	 * all five sites are reserved
	 * 
	 * @return ArrayList of strings of fully booked dates 0(n)
	 ****************************************************************/
	public ArrayList<String> getBookedDays() {
		return listDates.fullyReservedSites();
	}

	/*******************************************************************
	 * Getter method to see if list is added
	 * 
	 * @return flag if added, false or true
	 ******************************************************************/

	public boolean getIsAdded() {
		return this.flag;
	}

	/*******************************************************************
	 * This setter method will accept a boolean type that proves list 
	 * has been added
	 * 
	 * @param on
	 *            to accept whether or not list added
	 ******************************************************************/
	public void setIsAdded(boolean on) {
		this.flag = on;
	}

	/*******************************************************************
	 * Getter method for the message sent as the status off the users 
	 * current action
	 * 
	 * @return
	 ******************************************************************/
	public String getReturnStat() {
		return message;
	}

	/*******************************************************************
	 * Setter method will allow for passage of a string to be sent 
	 * throughout the program
	 * 
	 * @param message
	 ******************************************************************/

	public void setReturnStat(String message) {
		this.message = message;
	}

	/******************************************************************
	 * updates the jlist in the gui
	 *****************************************************************/
	public void update() {
		this.fireTableRowsInserted(0, listSites.size() - 1);
	}

	/*****************************************************************
	 * Saves off the list of Sites to a text file.
	 * 
	 * @param filename
	 *            name of file to save text to 0(n)
	 ****************************************************************/
	public void saveText(String filename) {

		try {
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new FileWriter(filename)));
			out.println(listSites.size());

			for (int currentSize = 0; currentSize < listSites
					.size(); currentSize++) {

				// listSites is an ArrayList<Site>
				Site SiteUnit = listSites.get(currentSize);

				// Output the class name.
				out.println(SiteUnit.getClass().getName());

				// Output the Site Name
				out.println(SiteUnit.getNameReserving());

				SimpleDateFormat formatCal = new SimpleDateFormat(
						"M/d/yyyy");
				String temp = formatCal.format(listSites
						.get(currentSize).getCheckIn().getTime());
				out.println(temp);

				// Output the days staying
				out.println(SiteUnit.getDaysStaying());
				// Output the site number
				out.println(SiteUnit.getSiteNum());

				if (SiteUnit instanceof Tent)
					out.println(((Tent) SiteUnit).getNumTenters());
				if (SiteUnit instanceof RV)
					out.println(((RV) SiteUnit).getPow());
			}
			out.close();
		} catch (IOException ex) {
			// System.out.println("IO Error!");
			setReturnStat("IO Error!");
		}
	}

	/******************************************************************
	 * Loads a file named from parameter. Checks for valid file format, 
	 * valid numbers, and valid dates. If any errors are found, clear 
	 * arrayList and listDates
	 * 
	 * @param filename
	 *            name of file to load 0(n)
	 *****************************************************************/
	public void loadText(String filename) {
		ArrayList<Site> tempList = new ArrayList<Site>();
		Site temp;
		File inFile = new File(filename);
		try {
			Scanner sc = new Scanner(inFile);
			// Read first line, which is amount of sites.
			String line = sc.nextLine();
			listDates = new SiteDates();
			countSites = Integer.parseInt(line);
			int counter = 0;
			while (counter < countSites && sc.hasNext()) {

				String className = sc.nextLine();
				if ("campingsystem.Tent".equals(className))
					temp = new Tent();
				else if ("campingsystem.RV".equals(className))
					temp = new RV();
				else {
					sc.close();
					throw new NumberFormatException();
				}
				temp.setNameReserving(sc.nextLine());
				DateFormat df = new SimpleDateFormat("M/d/yyyy");
				java.util.Date date = null;
				try {
					date = df.parse(sc.nextLine());
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTime(date);
					temp.setCheckIn(cal);
				} catch (Exception e1) {
					sc.close();
					throw new NumberFormatException();
				}
				temp.setDaysStaying(Integer.parseInt(sc.nextLine()));
				temp.setSiteNum(Integer.parseInt(sc.nextLine()));
				if (temp instanceof Tent)
					((Tent) temp).setNumTenters(
							Integer.parseInt(sc.nextLine()));
				else if (temp instanceof RV)
					((RV) temp).setPow(Integer.parseInt(sc.nextLine()));

				if (listDates.isReserved(temp)) {
					sc.close();
					throw new InvalidDate();
				}
				if (isValidInput(temp)) {
					tempList.add(temp);
					listDates.reserve(temp);
				} else {
					sc.close();
					throw new IllegalArgumentException();
				}
				++counter;
			}

			listSites = tempList;
			fireTableDataChanged();
			sc.close();

		} catch (FileNotFoundException e) {
			setReturnStat("Cannot Find File!");
			listDates = new SiteDates();
			listSites.clear();
		} catch (InvalidDate e) {
			setReturnStat("Invalid Dates");
			listDates = new SiteDates();
			listSites.clear();
		} catch (Exception e) {
			setReturnStat("Invalid File!");
			listDates = new SiteDates();
			listSites.clear();
		}
		fireTableDataChanged();

	}

	/******************************************************************
	 * Saves siteList as serialized file.
	 * 
	 * @param filename
	 *            name of file to save to 0(1)
	 *****************************************************************/
	public void saveAsSerialized(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listSites);
			oos.close();
		} catch (IOException e) {
			setReturnStat("IO Error!");
		}

	}

	/******************************************************************
	 * Loads from serialized file. Checks for invalid file format. If error, do
	 * not load the file
	 * 
	 * @param filename
	 *            name of file to load from. 0(1)
	 *****************************************************************/
	public void loadFromSerialized(String filename) {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			listSites = (ArrayList<Site>) in.readObject();
			in.close();
			fireTableRowsInserted(0, listSites.size());
		} catch (IOException e) {
			setReturnStat("Invalid File");
		} catch (ClassNotFoundException e) {
			setReturnStat("Invalid File");

		} catch (Exception e) {
			setReturnStat("Invalid File");

		}

	}

	/***********************************************************************
	 * Calculates the number of days from the date given to the 
	 * beginning of given year
	 * 
	 * @param cal
	 *            calendar Date chosen by user
	 * @return days the number of days since the beginning of 2017.
	 **********************************************************************/
	public int convertToDays(GregorianCalendar cal) {
		// conversion into days
		int days = (int) (cal.getTime().getTime()
				/ (24 * 60 * 60 * 1000));

		GregorianCalendar cal2 = new GregorianCalendar();
		// set to beginning of calendar year
		cal2.set(Calendar.YEAR, 0, 1);
		int hold = (int) (cal2.getTime().getTime()
				/ (24 * 60 * 60 * 1000));
		days = days - hold;

		return days;
	}

	/*******************************************************************
	 * This method is to be called by the GUI that will allow for the
	 *  output message of a list of site holders staying 3 or more days
	 * 
	 * @return stream of strings from the listSites
	 ******************************************************************/
	public String streaming() {

		ArrayList<Site> followList = listSites;

		// only showing the names of people staying 3 or more days
		String days = followList.stream()
				.filter(staying -> staying.getDaysStaying() >= 3)
				.map(name -> name.getNameReserving())
				// joins the list by separating with a new line
				.collect(Collectors.joining("\n"));
		//test to console
		// System.out.println(days);
		
		
		// appears more as a list
		// List<String> days = followList.stream()
		// .filter(staying-> staying.getDaysStaying()>=3)
		// .map(name-> name.getNameReserving())
		// //joins the list by separating with a new line
		// .collect(Collectors.toList());
		return days;

	}

}