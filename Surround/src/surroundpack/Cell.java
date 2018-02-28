package surroundpack;

/***********************************************************************
 * This Cell class is useful to modify the boards and players at hand.
 * 
 * @author Atone Joryman
 * @version Fall 2017
 *
***********************************************************************/
public class Cell {

	/** setting of the player number**/
	private int playerNumber;
	
	/** setting of the initial number of players**/
	private int initialP;
	
	/** setting the next player**/
	private int nextP;

/***********************************************************************
 * A constructor method that sets up number of players
 * @param number of players in line
***********************************************************************/
	public Cell(int playerNumber) {
//		if(playerNumber == 0){
//			//call the application over again if out of players
//			new Surround();
//		}

		//update to instance variable
		this.playerNumber = playerNumber;
	}
/***********************************************************************
 * getter method for the mutated number of players
 * @return number of players.
***********************************************************************/
	public int getPlayerNumber() {
	return this.playerNumber;
	
	}
/***********************************************************************
* setter method for next player in line
* @return the next number
***********************************************************************/
	public int getNextPlayer() {
	return this.nextP; 	
	}
	
/***********************************************************************
* setter method for next player in line
* @param the next number
***********************************************************************/
public void setNextPlayer(int num) {
this.nextP =num; 	
}

/***********************************************************************
* setter method for first player in line
* @param the next number
***********************************************************************/
public void setFirstPlayer(int num) {
this.initialP =num; 	
}

/***********************************************************************
* setter method for first player in line
* @param the next number
***********************************************************************/
public int getFirstPlayer() {
return this.initialP; 	
}
}
