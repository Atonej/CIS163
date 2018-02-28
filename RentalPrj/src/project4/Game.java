package project4;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/***********************************************************************
 * Extends to DVD class, except uses the aspect of game player 
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class Game extends DVD {

	/** Represents the type of player */
    //private String player;   // Xbox 360, PS4, Xbox1.(String)
    private PlayerType player; //enum type
    
/***********************************************************************
*Constructor that sets the requirements to be known under Game
*@param String of the title
*@param String of the renter name
*@param String of the game station
*@param GregorianCalender of the buy date
*@param GregorianCalender of the due back date
***********************************************************************/
public Game(String t, String nof, String p, GregorianCalendar b,
    		GregorianCalendar d){
    	//reach to DVD class
    	super(t,nof, b,d);
    	//add on the player 
    	this.player = PlayerType.valueOf(p);;
    	
    }
    
/***********************************************************************
*method to receive the player station
*@return player of type player
***********************************************************************/
public PlayerType getPlayer() {
		return player;
	}

/***********************************************************************
*method to set the player station 
*@param player of type player
***********************************************************************/
public void setPlayer(PlayerType player) {
		this.player = player;
	}

/***********************************************************************
 * Calculate the amount of money due in fees, money increments after 
 * a number of late days
 * @param GregorianCalender of number of days to calculate if late.
 * @return a String of the fees calculated.
 **********************************************************************/
public String calcLateFees(GregorianCalendar days){
//		Calendar rightNow = Calendar.getInstance();
//		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("MM");
//		java.text.SimpleDateFormat df2 = new java.text.SimpleDateFormat("MMM");
//		java.text.SimpleDateFormat df3 = new java.text.SimpleDateFormat("MMMM");
//		double money =3;
//		if(days< 0)
//			throw new IllegalStateException();
//		if(days == 0)
//			return money;
//		if(days >0){
//			return money + days;
//		}
//	return 0; 
		int daysLate = 0;
		try{
			
			// gets time for both days and increments one day at a time until equal
			while ((int) dueBack.getTimeInMillis() < (int) days.getTimeInMillis()){
				dueBack.setTimeInMillis(dueBack.getTimeInMillis() + 86400000);
				daysLate++;
			}
			
		}catch(IllegalArgumentException e){
			
		}

		int cost = 3;
		
		String costDisplay = "Base Cost = $3.00" + "\n";
		
		// add onto string when late
		if (daysLate > 0){
			cost += 3 + daysLate;
			costDisplay += "Late fee: $3.00 + $1.00 per day late" + "\n";
			costDisplay += "Days late: " + daysLate + "\n";
		}
		
		costDisplay += "Total cost: $" + cost + ".00";
		
		return costDisplay;
	}
	
/***********************************************************************
* converts DVD's information into a string for game
***********************************************************************/
public String toString(){
		String description = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String rentedOn = sdf.format(bought.getTime());
		String dueOn = sdf.format(dueBack.getTime());
		
		description = "Name:  " + nameOfRenter + " ,  Title:  " + title + 
				",  Rented On:  " + rentedOn + ",  Due Back On:  " + dueOn +
				",  Game Player:  " + player;
		
		return description;
	}
   
   }
