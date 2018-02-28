package project44;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class Game extends DVD{
	
	/** Represents the type of player */
	private String player;

	/******************************************************************
	 * constructor for Game class
	 * @param bought date game was rented
	 * @param dueBack date game is due
	 * @param title title of game
	 * @param nameOfRenter name of game renter
	 * @param gamePlayer type of game player
	 *****************************************************************/
	public Game(GregorianCalendar bought, GregorianCalendar dueBack, 
			String title, String nameOfRenter, String gamePlayer) {
		
		super(bought, dueBack, title, nameOfRenter);
		this.player = gamePlayer;

	}

	/******************************************************************
	 * getter for game player
	 * @return player the type of game player
	 *****************************************************************/
	public String getPlayer() {
		return player;
	}

	/******************************************************************
	 * setter for game player
	 * @param player the type of game player
	 *****************************************************************/
	public void setPlayer(String player) {
		this.player = player;
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
			while ((int) dueBack.getTimeInMillis() < (int) returnDate.getTimeInMillis()){
				dueBack.setTimeInMillis(dueBack.getTimeInMillis() + 86400000);
				daysLate++;
			}
			
		}catch(IllegalArgumentException e){
			
		}

		int cost = 3;
		
		String costDialog = "Base Cost = $3.00" + "\n";
		
		// appends string with extra details if it's late
		if (daysLate > 0){
			cost += 3 + daysLate;
			costDialog += "Late fee: $3.00 + $1.00 per day late" + "\n";
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
				",  Rented On:  " + rentedOn + ",  Due Back On:  " + dueOn +
				",  Game Player:  " + player;
		
		return description;
	}

}
