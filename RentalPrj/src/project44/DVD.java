package project44;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**********************************************************************
 * DVD class for the Rental Store, is extended by game and 
 * blue ray classes and contains all relevant information for
 * its use in the store
 * @author Gregory Huizenga
 * @version 7/25/2017
 *********************************************************************/
public class DVD implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	/** The date the DVD was rented */
	protected GregorianCalendar bought;
	
	/** The date the DVD is due back */
	protected GregorianCalendar dueBack;
	
	/** The title of the DVD */
	protected String title;
	
	/** The name of the person renting the DVD*/
	protected String nameOfRenter;
	
	/******************************************************************
	 * Parameterized constructor for DVD
	 * @param bought date bought
	 * @param dueBack date due
	 * @param title title of dvd
	 * @param nameOfRenter name of dvd renter
	 *****************************************************************/
	public DVD(GregorianCalendar bought, GregorianCalendar dueBack, 
			String title, String nameOfRenter) {
		
		this.bought = bought;
		this.dueBack = dueBack;
		this.title = title;
		this.nameOfRenter = nameOfRenter;
	}
	
	/******************************************************************
	 * getter for date dvd was rented
	 * @return bought date dvd was rented
	 *****************************************************************/
	public GregorianCalendar getBought() {
		return bought;
	}
	
	/******************************************************************
	 * setter for date dvd was rented
	 * @param bought date dvd was rented
	 *****************************************************************/
	public void setBought(GregorianCalendar bought) {
		this.bought = bought;
	}
	
	/******************************************************************
	 * getter for due date
	 * @return dueBack due date for dvd
	 *****************************************************************/
	public GregorianCalendar getDueBack() {
		return dueBack;
	}

	/******************************************************************
	 * setter for due date
	 * @param dueBack due date for dvd
	 *****************************************************************/
	public void setDueBack(GregorianCalendar dueBack) {
		this.dueBack = dueBack;
	}
	
	/******************************************************************
	 * getter for dvd title
	 * @return title dvd title
	 *****************************************************************/
	public String getTitle() {
		return title;
	}
	
	/******************************************************************
	 * setter for dvd title
	 * @param title dvd title
	 *****************************************************************/
	public void setTitle(String title) {
		this.title = title;
	}
	
	/******************************************************************
	 * getter for dvd renter
	 * @return nameOfRenter name of renter
	 *****************************************************************/
	public String getNameOfRenter() {
		return nameOfRenter;
	}

	/******************************************************************
	 * setter for dvd renter
	 * @param nameOfRenter
	 *****************************************************************/
	public void setNameOfRenter(String nameOfRenter) {
		this.nameOfRenter = nameOfRenter;
	}
	
	/******************************************************************
	 * returns a string detailing the total cost of a rental
	 * @param returnDate the date the rental was returned
	 * @return costDialog the string detailing all the information
	 *****************************************************************/
	public String getCost(GregorianCalendar returnDate){
		int daysLate = 0;
		try{
			// gets time for both days and increments one day at a time until equal
			while (dueBack.getTimeInMillis() < returnDate.getTimeInMillis()){
				dueBack.setTimeInMillis(dueBack.getTimeInMillis() + 86400000);
				daysLate++;
				}
		}catch(IllegalArgumentException e){
			
		}
		
		int cost = 1;
		
		String costDialog = "Base Cost = $1.00" + "\n";
		
		// appends string with extra details if it's late
		if (daysLate > 0){
			cost += 1 + daysLate;
			costDialog += "Late fee: $1.00 + $1.00 per day late" + "\n";
			costDialog += "Days late: " + daysLate + "\n";
		}
		
		costDialog += "Total cost: $" + cost + ".00";
		
		return costDialog;
		}
		
	/******************************************************************
	 * converts DVD's information into a string
	 *****************************************************************/
	public String toString(){
		String description = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String rentedOn = sdf.format(bought.getTime());
		String dueOn = sdf.format(dueBack.getTime());
		
		description = "Name:  " + nameOfRenter + " ,  Title:  " + title + 
				",  Rented On:  " + rentedOn + ",  Due Back On:  " + dueOn;
		
		return description;
	}
}

	
	

