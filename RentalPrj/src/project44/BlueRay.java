package project44;

import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class BlueRay extends DVD{

	/******************************************************************
	 * constructor for Blue ray class
	 * @param bought date blue ray was rented
	 * @param dueBack date blue ray is due
	 * @param title title of blue ray
	 * @param nameOfRenter name of renter
	 *****************************************************************/
	public BlueRay(GregorianCalendar bought, GregorianCalendar dueBack, 
			String title, String nameOfRenter) {
		
		super(bought, dueBack, title, nameOfRenter);

	}
	
	/******************************************************************
	 * returns a string detailing the total cost of a rental
	 * @param returnDate the date the rental was returned
	 * @return costDialog the string detailing all the information
	 *****************************************************************/
	public String getCost(GregorianCalendar returnDate){
		int daysLate = 0;
		try{
			daysLate = (int) TimeUnit.MILLISECONDS.toDays
				(dueBack.getTimeInMillis() - returnDate.getTimeInMillis());
		}catch(IllegalArgumentException e){
			
		}

		int cost = 2;
		
		String costDialog = "Base Cost = $2.00" + "\n";
		
		// appends string with extra details if it's late
		if (daysLate > 0){
			cost += 2 + daysLate;
			costDialog += "Late fee: $2.00 + $1.00 per day late" + "\n";
			costDialog += "Days late: " + daysLate + "\n";
		}
		
		costDialog += "Total cost: $" + cost + ".00";
		
		return costDialog;
		}
	
}
