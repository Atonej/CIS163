package Project3;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.table.AbstractTableModel;


/***********************************************************************
 * This class handles the data for the Site class. It will fill the
 * table with the associated data for each row and column. 
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ritcksgers 
 * @version Fall 2017 
 **********************************************************************/
@SuppressWarnings("serial")
public class SiteModel extends AbstractTableModel {

	/** Contains registered camping sites**/
	private ArrayList<Site> listSites;

	/** Used as back up registered camping site list **/
	private ArrayList<Site> listSitesStored;

	/**Used to traverse the list in either direction. **/
	private ListIterator<Site> litr;

	/** Tracks availability by day for each camp site **/
	private int[][] availible;
	/**/
	private ArrayList<Site> hold;
	
	private final int fiftyYears = 18250;
	private final int siteAvailable = 5;
	
	/** Column Headers **/
	private String[] columnNames = { "Name Reserving", "Checked in", "Days Staying",
			"Site #", "Tent/RV info"};


	/***********************************************************************
	* Constructor method that initializes the array lists and sets all sites
	* to available.
	**********************************************************************/
	public SiteModel() {
		listSites = new ArrayList<>();
		listSitesStored = new ArrayList<>();

		availible = new int[siteAvailable][fiftyYears];
		for(int i=0; i<fiftyYears; i++)		 
			for(int j = 0 ; j <siteAvailable; j++)				 
				availible[j][i] = 0;

	}
	
	

	/***********************************************************************
	* 
	**********************************************************************/
	public SiteModel(ArrayList<Site> listSites)
	{
		this.listSites = listSites;
	}


	/***********************************************************************
	* Overrides the method from TableModel class. Based
	* on the given columnIndex, which provides the type of data to be inserted
	* into that column, this method will fill the table with the associated
	* array list item.
	* @param rowIndex the object row coincides with the object index within
	* the listSites array list.
	* @param columnIndex will help specify which method to call
	* @return val the value to be inserted into the table.
	**********************************************************************/
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Object val = null;
		switch (columnIndex) {
		case 0:
			val = listSites.get(rowIndex).getNameReserving();
			break;
		case 1: 
			val = DateFormat.getDateInstance(DateFormat.LONG).format(listSites.get(rowIndex).getCheckIn().getTime());
			break;
		case 2:
			val = listSites.get(rowIndex).getDaysStaying();
			break;
		case 3:
			val = listSites.get(rowIndex).getSiteNumber();
			break;
		case 4:
			//the local variable val will equal the amps required for an RV or the number of tenters on the site
			if (listSites.get(rowIndex) instanceof Tent) {
				val = (((Tent) listSites.get(rowIndex)).getNumOfTenters());
				break;
			}
			if (listSites.get(rowIndex) instanceof RV) {
				val = (((RV) listSites.get(rowIndex)).getPower());
				break;
			}
		}
		return val;
	}


	/***********************************************************************
	* Accessor method for columnName. Overrides the 
	* getColumnName method in the AbstractTableModel.
	* Will return the column name found in the columnName 
	* array based on commonality between array indexes and 
	* the value passed into the method.
	* @param col specifies the column heading from array
	* @return columnNames[col] an array of column names
	* 
	**********************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}


	/***********************************************************************
	* Calculates the amount of sites in the listSites array.
	* @return listSites.size()
	**********************************************************************/
	public int getSize() {
		return listSites.size();

	}


	/***********************************************************************
	* Accessor method for columnCount. Overrides the 
	* getColumnCount method in the AbstractTableModel.
	* Will return the amount of columns needed. This value 
	* comes from the length of the columnNames array.
	* 
	* @return columnNames.length the amount of items in
	* the array columnNames.
	* 
	**********************************************************************/
	public int getColumnCount() {
		return columnNames.length;
	}


	/***********************************************************************
	* Accessor method for rowCount. Overrides the 
	* getRowCount method in the AbstractTableModel.
	* Will return the amount of rows needed based on the
	* number of Sites. This value comes from the size
	* of the array list listSites.
	* 
	* @return listSites.size() the amount of items in
	* the array list listSites.
	* 
	**********************************************************************/
	public int getRowCount() {
		return listSites.size();
	}


	/***********************************************************************
	* This method will add additional Sites
	* @param s allows Site objects to be passed to
	* the add method.
	**********************************************************************/
	public void add(Site s) {

		insertSite(getRowCount(), s);
	}


	/***********************************************************************
	* Adds site to the array list based on the specified index position
	* then Notifies all listeners that all cell values in the table's rows
	* may have changed. The number of rows may also have changed and the
	* JTable should redraw the table from scratch. The structure of the
	* table (as in the order of the columns) is assumed to be the same.
	* @param row the index where the Site object should be inserted
	* @param s the site object to be added to the list
	**********************************************************************/
	public void insertSite(int row, Site s)
	{	
		listSites.add(row, s);
		writeLastAction("add");

		if(listSites.size()>1)
		{
			listSitesStored.add(s);
			listSitesStored.remove(0);
		}
		else if(listSites.size() ==1)
		{
			listSitesStored.add(s);
			//put back to zero 
			listSitesStored.clear();
		}

		fireTableDataChanged();   
	}


	/***********************************************************************
	* Sets registered sites to -1 rendering them unavailable.
	* @param siteNumber A camp site
	* @param daysstaying The number of days a camper will stay at 
	* their site.
	* @param cal The calendar
	**********************************************************************/
	public void changeAvalibility(int siteNumber, int daysstaying, GregorianCalendar cal ){

		int days = convertToDays(cal);

		for(int i= 0; i < daysstaying; i++)	
			availible[siteNumber-1][days + i]= -1;
	}


	/***********************************************************************
	* Checks site to see if it is available on a particular range of days.
	* @param site A camp site
	* @param daysstaying The number of days a camper will stay at 
	* their site.
	* @param cal The calendar
	* @return 0 represents availability
	* @return -1 represents unavailability
	* @throws IllegalArgumentException If date is set to a day in the past
	**********************************************************************/
	public int checkSite(int site,int daysstaying, GregorianCalendar cal){

		int days = convertToDays(cal);

		if(days < 0){
			throw new IllegalArgumentException(
					"date must not be before today");
		}
		
		
		
		for(int i=0; i < daysstaying; i++)		
			if( availible[site-1][days + i] != 0)			
				return -1;

		return 0;
	}


	/***********************************************************************
	* Calculates the number of days from the date given to the beginning of
	* 2017 
	* @param cal calendar Date chosen by user
	* @return days the number of days since the beginning of 2017.
	* @throws IllegalArgumentException If date is set to a day in the past.
	**********************************************************************/
	public int convertToDays(GregorianCalendar cal){		

		int days = (int) (cal.getTime().getTime()/(24*60*60*1000));

		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.set(2017, 0, 1);
		int hold = (int) (cal2.getTime().getTime()/(24*60*60*1000));
		days = days - hold;

		if (days < 0 ){
			throw new IllegalArgumentException("date must not be before today");
		}
		
		return days;
	}


	/***********************************************************************
	* returns a string of the dates taken for a camp site
	* @param site the chosen camp site
	* @param daysstaying The length of the campers stay
	* @param cal a date used for calculating the number of days from itself
	* to the beginning of 2017. 
	* @return dates represents a string of dates that are unavailable 
	**********************************************************************/
	@SuppressWarnings("deprecation")
	public String returnDates(int site, int daysstaying, GregorianCalendar cal){

		int days = convertToDays(cal);

		String dates = "";

		for(int i = 0; i < daysstaying;i++){

			if( availible[site-1][days + i] != 0){
				//this could probably be optimized
				Calendar calendar = new GregorianCalendar(2017,1,days + i +2);
				Date date = new Date(days);
				date.setDate((int) (calendar.getTimeInMillis()/(24*60*60*1000)));
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				dates += sdf.format(date) + " ";
			}	
		}
		return dates;
	}
	
	
	/******************************************************************
	 * updates the jlist in the gui
	 *****************************************************************/
	public void update(){
		this.fireTableDataChanged();
	}


/***********************************************************************
* This method will delete additional Sites.
* @param row represents which site to delete.
**********************************************************************/
	public void delete(int row) {
		if (listSites.size() > 0)
		{			
			writeLastAction("delete");
			listSitesStored.add(listSites.get(row));			
			listSites.remove(row);
			
			fireTableRowsDeleted(row, row);			
		}
	}

	
/***********************************************************************
* Makes a camp site available after deleting it from the GUI.
**********************************************************************/
	public void openAvailability(int row) {		
		int days = convertToDays(listSites.get(row).getCheckIn());

		for(int i = 0; i < listSites.get(row).getDaysStaying(); i++)	
			availible[listSites.get(row).getSiteNumber()-1][days + i]= 0;
	}
	
	
	public void clear() {
		listSites.clear();
		this.fireTableDataChanged();
	}

	
/***********************************************************************
* Reverses the last action made.
**********************************************************************/
	public void undo() {
		String lastAction = readLastAction();
		
		//Only allow the undo action if a Camp site has been registered
		if(listSites.size() > 0) {

			//if there is an error reading the file, do nothing.
			if(lastAction == null) {

			}
			
			//if the last action recorded was delete, add the previously 
			//deleted row back onto the GUI
			if(lastAction.equals("delete")) {
				if(listSitesStored.size() - 1 >= 0)
					add(listSitesStored.get(getSize() - 1));			

			}
			
			//if the last action recorded was add, delete the previously
			//added row from the GUI
			if(lastAction.equals("add")) {
				if(listSites.size() > 0) {
					openAvailability(getSize() - 1);
					delete(getSize() - 1);
				}
			}	

			fireTableDataChanged();
		}
	}
	
	/***********************************************************************
	* Sorts the list of sites by name then updates the GUI.
	**********************************************************************/
	public void sortName(){
		hold = new ArrayList<>(listSites);		
		ArrayList<String> names = new ArrayList<>(); 
		
		for(int i =0; i < getRowCount(); i++){
			names.add(hold.get(i).getNameReserving().toUpperCase()) ;
		}
		
		names.sort(null);		
		listSites.clear();
		
		for(int j = 0; j < names.size(); j++){
			for(int i = 0; i < names.size(); i++)				
				if(names.get(j).equals(hold.get(i).
						getNameReserving().toUpperCase())) {
					add(hold.get(i));
					hold.remove(i);
					break;
				}
		}
		fireTableDataChanged();
	}
	
	
	/***********************************************************************
	* Records the last add or delete action made to the listSites array list
	* to a file.
	**********************************************************************/
	public void writeLastAction(String lastAction) {
		PrintWriter out = null;
		BufferedWriter bw = null;
		try {
			
			String filename = "lastaction.txt";
			out = new PrintWriter(new BufferedWriter
					(new FileWriter(filename)));
			//System.out.println(lastAction);
			out.write(lastAction);
			out.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/***********************************************************************
	* Reads the last recorded action made to the listSites array list from
	* a file.
	* @return lastAction the last add or delete action recorded.
	* @return null an error occurred while attempting to read file.
	**********************************************************************/
	public String readLastAction()
	{
		
		try {
			
			String filename = "lastaction.txt";
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String lastAction = br.readLine();
			br.close();
			return lastAction;
		} catch (FileNotFoundException e) {
			
			//if an error occurs reading the file return null
			//choose how to handle 
			return null;			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if an error occurs
		return null;		   

	}
	

	/***********************************************************************
	* This method serializes and writes the listSite array list
	* to a file. 
	* @param filename is the chosen name to be used
	* in the file directory.
	**********************************************************************/
	public void saveSerializedSite(String filename) {
		FileOutputStream out = null;
		ObjectOutputStream oos = null;

		//if the file has the wrong extension add the
		//Java .ser extension to the filename
		if(filename.contains(".ser") == false)
			filename += ".ser";


		try {
			out = new FileOutputStream(filename);
			oos = new ObjectOutputStream(out);

			oos.writeObject(listSites);

		}		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			if (out != null) {
				try {
					out.close();
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}


/***********************************************************************
* Deserializes and displays a list of Site objects
* on the registration GUI.
* @param filename the user selected file which is chosen
* using the JFileChooser utilized in the GUICampingReg
* save event.
**********************************************************************/
	@SuppressWarnings("unchecked")
	public void loadFromSerialized(String filename, Site s) {

		try {

			FileInputStream fileIn = new FileInputStream(filename);		
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);		

			Object newbie = objectIn.readObject();
			litr = ((ArrayList<Site>) newbie).listIterator();			 

			while (litr.hasNext()) {
				s = litr.next();
				checkSite(s.siteNumber, s.daysStaying, s.checkIn);
				if(checkSite(s.siteNumber, s.daysStaying, s.checkIn)
						==-1) {
					throw new IllegalArgumentException
					("There is an issue with interfering with "
							+ "sites already on the list");
				}
				else{
				insertSite(getRowCount(), s);
				changeAvalibility(s.siteNumber,
						s.daysStaying, s.checkIn);
				}

			}

			//close out the stream
			objectIn.close();			

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		
	}
	
	
/***********************************************************************
* saves list of sites to a text file with specified name
* @param filename the location of the file used for saving.
* @throws FileNotFoundException Signals that an attempt to open
* the file denoted by a specified pathname has failed. 
***********************************************************************/
	public void saveToText(String filename) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(filename);
		out.println(listSites.size());
		for(int i = 0; i < listSites.size(); i++){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Site temp = (Site)listSites.get(i);
			Object temp2 = listSites.get(i);
			
			out.println(listSites.get(i).getClass());
			out.println(temp.getNameReserving());
			out.println(sdf.format(temp.getCheckIn().getTime()));
			out.println(temp.getDaysStaying());
			out.println(temp.getSiteNumber());
			
			//if tent 
			if (listSites.get(i).getClass().toString().contains("Tent")){
				out.println(((Tent) temp2).getNumOfTenters());
			}
			
			//if rv
			if (listSites.get(i).getClass().toString().contains("RV")){
				out.println(((RV) temp2).getPower());
			}
		}
		out.close();
	}
	
/***********************************************************************
* Records the last add or delete action made to the listSites array list
* @param filename represents the file location
* @throws IOException This class is the general class of exceptions
* produced by failed or interrupted I/O operations.
* @throws ParseException Signals that an error has been reached
* unexpectedly while parsing.
***********************************************************************/
	public void loadFromText(String filename) throws IOException, ParseException{

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String temp = reader.readLine();
		Scanner scnr = new Scanner(temp);
		//count number of sites in text file
		int count = scnr.nextInt();
		
		listSites.clear();
		
		for(int i = 0; i < count; i++){
			
			//type of site
			String type = reader.readLine();
			String name = reader.readLine();
			String checkIn = reader.readLine();
			String daysIn = reader.readLine();
			String siteNum = reader.readLine();
			String extra = reader.readLine();
			
			int newDaysIn = Integer.parseInt(daysIn);
			int newSiteNum = Integer.parseInt(siteNum);
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date rDate = sdf.parse(checkIn);
			
			GregorianCalendar reDate = new GregorianCalendar();
			reDate.setTime(rDate);
			
			
			if (type.contains("RV")){
				RV rv = new RV(name,reDate,newDaysIn,extra, newSiteNum);
				checkSite(rv.siteNumber, rv.daysStaying, rv.checkIn);
				if(checkSite(rv.siteNumber, rv.daysStaying, rv.checkIn)
						==-1) {
					throw new IllegalArgumentException
					("There is an issue with interfering with "
							+ "sites already on the list");
				}
				else {
				listSites.add(rv);
				}
			}
			else{
				
				Tent tent = new Tent
						(name, reDate, newDaysIn, extra, newSiteNum);
				checkSite(tent.siteNumber, tent.daysStaying, tent.checkIn);
				if(checkSite(tent.siteNumber, 
						tent.daysStaying, tent.checkIn)
						==-1) {
					throw new IllegalArgumentException
					("There is an issue with interfering with "
							+ "sites already on the list");
				}
				else {
				listSites.add(tent);
				}
			}
			
			
		}
		reader.close();
		}

}
