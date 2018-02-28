package surroundpack;

import java.util.Arrays;

/***********************************************************************
 * This class is the basis to the functioning of the game determining 
 * the randomized surround game.
 * 
 * @author Atone Joryman
 * @version Fall 2017
 *
***********************************************************************/
public class SurroundGame {
	/**Important for storing values into a artificial board**/
	private Cell[][] board;
	
	/** used to set up array for board **/
	private int row, col;
	
	/**used to keep up with players in line**/
	private int player=1;
		
	/**refer to the cell to apply number of players**/
	private Cell c;
/***********************************************************************
 * A constructor method that initializes the board
***********************************************************************/
	public SurroundGame(int player){
		//int[][] b = (int[][]) board;
		board= new Cell[10][10];
		
		c = new Cell(player);
	}

/***********************************************************************
* this method is called from the SurroundPanel class and it determines 
* if a player has won the game after the select method (see above) was 
* called. 
* @return Return -1 if there is no winner.	
* @return Return playerNumber if there is a winner in all cases whether
* in the corner or center of board

***********************************************************************/	
	public int isWinner() {
		//TODO work on the win cases
		//professor Ferguson method
		// top-left corner case (check 2 sides only)
		//if (row == 0 && col == 0) 	

		 if (board[0][1] != null && board[1][0] != null)
				if (board[0][1].getPlayerNumber() == 
				board[1][0].getPlayerNumber())
					return board[0][1].getPlayerNumber();	

	// left-border case (excluding corners - check 3 sides only)
		if (row != 0 && row != board.length-1 && col == 0) 
		    if (board[row-1][col] != null && board[row][col+1] != null 
		    && board[row+1][col] != null)
			if (board[row-1][col].getPlayerNumber() == 
			board[row][col+1].getPlayerNumber() && 
			board[row-1][col].getPlayerNumber() == 
			board[row+1][col].getPlayerNumber())
					return board[row-1][col].
							getPlayerNumber();// just pick one of them
		
		//checking boards
		//if(row==0 && col== 0){
		//	if(board[0][0] != null && board[0][1]!=null)
		//		return board[0][0].getPlayerNumber();
		//}

		return -1;
	}

/***********************************************************************
* this method is called from the SurroundPanel class and is invoked 
* when the user has selected a JButton.  This method determines if the 
* row, col that was selected was an empty square.  Return true if valid,
*  otherwise return false. 
***********************************************************************/	
	public boolean select (int row, int col) {
		//check if board is null at specified indexes
		if(this.board[row][col]!= null){
			return false;
		}
		else{
		return true;
		}
	}
	
/***********************************************************************
* this method is called from the SurroundPanel class and it resets the 
* board to a new game.
***********************************************************************/	
	public void reset(){
		//make all the indexes point to null
		//new SurroundGame();
		//TODO is array back to null?
		//Arrays.fill( this.board, null );
		this.board = new Cell[row][col];
	
		//new SurroundGame();
	}

/***********************************************************************
* Once one place goes this method allows the next player to play
* @return next player number
***********************************************************************/
public int nextPlayer() {

//get number of players and then go to next
player = player + 1;

//if greater than number of players go to zero
	if(player > c.getPlayerNumber() ){
		player=1;
	}
	//set the next player in the cell
	c.setNextPlayer(player);
	return c.getNextPlayer();
}

/***********************************************************************
* this method will set the cell board to compare to actual board
* @param set the row into board
* @param set the column into board
* @return the information in current selected board
***********************************************************************/
public Cell[][] setBoard(int row, int col){
	this.row = row;
	this.col = col;
	
	//board = new Cell[this.row][this.col];
//Cell c =new Cell(player); 
	board[this.row][this.col] = c;
	
	return board ;
	
}

/***********************************************************************
* this method will allow the board to begin at the first player
* @return the information in current selected board of type int
***********************************************************************/
public int initialPlayer() {
	//make the cell stay at first player
	c.setFirstPlayer(player);
	return c.getFirstPlayer();
}

}
