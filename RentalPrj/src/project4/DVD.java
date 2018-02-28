package project4;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/***********************************************************************
 * DVD class used to access what is in the DVD 
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/

public class DVD implements Serializable/**, Comparable<DVD>*/ {
/**universal version identifier for a Serializable class. Save/Load*/
private static final long serialVersionUID = 1L;

/** The date the DVD was rented */
protected GregorianCalendar bought;

/** The date the DVD is due back */
protected GregorianCalendar dueBack;

/** The title of the DVD */
protected String title;

/** The name of the person who is renting the DVD */
protected String nameOfRenter; 

/***********************************************************************
 *Constructor that sets the requirements to be known under DVD
 *@param String of the title
 *@param String of the renter name
 *@param GregorianCalender of the buy date
 *@param GregorianCalender of the due back date
 **********************************************************************/

public DVD(String t, String nof, GregorianCalendar b,
		GregorianCalendar d){
	title= t;
	nameOfRenter = nof;
	bought= b;
	dueBack= d;
	
}
/***********************************************************************
 * When DVD equals another class
 * @return is equal 
 **********************************************************************/

public boolean equals(DVD other){
	return true;
}

/***********************************************************************
 * Calculate the amount of money due in fees, money increments after 
 * a number of late days
 * @param GregorianCalender of number of days to calculate if late.
 * @return a String of the fees calculated.
 **********************************************************************/
public String calcLateFees(GregorianCalendar days){
//	double money =1;
//	if(days< 0)
//		throw new IllegalStateException();
//	if(days == 0)
//		return money;
//	if(days >0){
//		return money + days;
//	}
//return 0; 
	int daysLate = 0;
	try{
		// receive the time for both days and increments one 
		//day until equal to days in param
		for (daysLate=0; dueBack.getTimeInMillis() < 
				days.getTimeInMillis(); daysLate++){
		dueBack.setTimeInMillis(dueBack.getTimeInMillis() + 86400000);
			}
	}catch(IllegalArgumentException e){
		
	}
	
	int cost = 1;
	
	String costDisplay = "Base Cost = $1.00" + "\n";
	
	// add onto string when late
	if (daysLate > 0){
		cost += 1 + daysLate;
		costDisplay += "Late fee: $1.00 + $1.00 per day late" + "\n";
		costDisplay += "Days late: " + daysLate + "\n";
	}
	
	costDisplay += "Total cost: $" + cost + ".00";
	
	return costDisplay;
}

/***********************************************************************
 * Creates and returns a string representation of this DVD.
 * 
 * @return a string representation of the DVD
 **********************************************************************/
public String toString(){
//	String result = bought + " " + dueBack + ": " + title + " " + nameOfRenter;
//	if (!title.equals(""))
//		result += "  [" + title+ "]";
//	return result;
	String description = "";
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	String rentedOn = sdf.format(bought.getTime());
	String dueOn = sdf.format(dueBack.getTime());
	
	description = "Name:  " + nameOfRenter + " ,  Title:  " + title + 
			",  Rented On:  " + rentedOn + ",  Due Back On:  " + dueOn;
	
	return description;
}

/***********************************************************************
 * accessor method the the bought date as a Gregorian Calender
 * @return bought date
 **********************************************************************/
public GregorianCalendar getBought() {
	return bought;
}

/***********************************************************************
 * set method the the bought date as a Gregorian Calender
 * @param set bought date
 **********************************************************************/
public void setBought(GregorianCalendar bought) {
	this.bought = bought;
}
/***********************************************************************
 * accessor method the due back date as a Gregorian Calender
 * @return due back date
 **********************************************************************/
public GregorianCalendar getDueBack() {
	return dueBack;
}

/***********************************************************************
 * set the due back date as a Gregorian Calender
 * @param due back date
 **********************************************************************/
public void setDueBack(GregorianCalendar dueBack) {
	this.dueBack = dueBack;
}

/***********************************************************************
 * get the name of the renter
 * @return name of renter
 **********************************************************************/
public String getNameOfRenter() {
	return nameOfRenter;
}

/***********************************************************************
 * set the name of the renter
 * @param name of renter
 **********************************************************************/
public void setNameOfRenter(String nameOfRenter) {
	this.nameOfRenter = nameOfRenter;
}

/***********************************************************************
 * set the title of the DVD
 * @param given DVD
 **********************************************************************/
public void setTitle(String title) {
	this.title = title;
}

/***********************************************************************
 * get the title of the DVD
 * @return given DVD
 **********************************************************************/
public String getTitle() {
	return title;
}


//public int compareTo(DVD arg0) {
//	if(arg0 instanceof DVD){
//        DVD c = (DVD) arg0;
//        return 0;
//       }
//    else
//        throw new NullPointerException();
//}
//public String getCost(GregorianCalendar dat){
//	String retStr = " Base cost = 1 dollar\n";
//	double cost = 1;
//
//	if(dat.after(dueBack)){
//		cost +=1;
//		retStr += "Late cost = 1 dollars + 1 per day late \n";
//	}
//	
//	return retStr;
//}
}

