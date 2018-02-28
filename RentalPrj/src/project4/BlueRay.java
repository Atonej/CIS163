package project4;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/***********************************************************************
 * Extends to DVD class, into the aspect of the Blue Ray 
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class BlueRay extends DVD {
	
/***********************************************************************
*Constructor that sets the requirements to be known under DVD
*@param String of the title
*@param String of the renter name
*@param GregorianCalender of the buy date
*@param GregorianCalender of the due back date
***********************************************************************/
public BlueRay(String t, String nof, GregorianCalendar b,
		GregorianCalendar d){
	//reach to the DVD constructor
	super(t,nof, b,d);
}

/***********************************************************************
 * Calculate the amount of money due in fees, money increments after 
 * a number of late days
 * @param GregorianCalender of number of days to calculate if late.
 * @return a String of the fees calculated.
 **********************************************************************/
public String calcLateFees(GregorianCalendar days){
//	Calendar rightNow = Calendar.getInstance();
//	java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
//	java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("MMM");
//	java.text.SimpleDateFormat df3 = new java.text.SimpleDateFormat("MMMM");
//	double money =2;
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
		daysLate = (int) TimeUnit.MILLISECONDS.toDays
			(dueBack.getTimeInMillis() - days.getTimeInMillis());
	}catch(IllegalArgumentException e){
		
	}

	int cost = 2;
	
	String costDisplay = "Base Cost = $2.00" + "\n";
	
	// add onto string when late
	if (daysLate > 0){
		cost += 2 + daysLate;
		costDisplay += "Late fee: $2.00 + $1.00 per day late" + "\n";
		costDisplay += "Days late: " + daysLate + "\n";
	}
	
	costDisplay += "Total cost: $" + cost + ".00";
	
	return costDisplay;
	}

}
