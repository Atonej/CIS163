package surroundpack2;

import java.util.List;
import java.util.ArrayList;

/*****************************************************************
A program that creates a game where players attempt to surround
each other. The first to surround an opposing player wins!

@author Tiesha Anderson
@version October 2017
CIS 163-03
 *****************************************************************/
public class SurroundGame {
	/** creates the game board in this class */
	private Cell[][] board;

	/** tracks visited cells on board */
	private isVisited[][] check;

	/** used to group neighbors of a cell */
	ArrayList<Integer> neighbors =new ArrayList<Integer>();

	/** used to set the size of the board. Between 3 & 20 */
	private int boardSize;

	/** the total amount of Surround game players */
	private int totalPlayers;

	/** who will start the game*/
	private int starter;

	/** Used to keep track of the current player */
	private int player;

	/*****************************************************************
	Constructor that sets the board size, number of players, and
	the player who has the first turn.
	@param boardSize provides the length and width of board in buttons
	@param players is the total amount of players
	@param starter is the player the user chose to go first 
	 *****************************************************************/
	public SurroundGame(int boardSize, int players, int starter) {

		this.boardSize = boardSize;		
		totalPlayers = players;	
		this.starter = starter;

		board = new Cell[this.boardSize][this.boardSize];
		check = new isVisited[this.boardSize][this.boardSize];


		//makes sure the game board is clear and each cell holds a value of -1
		reset();
		//The minus one offsets the value provided by the user with the array
		player = this.starter - 1; 
	}


	/**
	 * This method is used to initialize the cell object in the
	 * JPanel to the value of the currently clicked button.
	 * This will help keep the game and display in sync.
	 * @param row used to track position on board
	 * @param col used to track position on board
	 * @return board[row][col] a cell on game board
	 */
	public Cell getCell (int row, int col) {
		return board[row][col];
	}


	/**
	 * this method is called from the SurroundPanel
	 * class and is invoked when the user has selected
	 * a JButton. This method determines if the row, col
	 * that was selected was an empty square. Return true
	 * if valid, otherwise return false.
	 * @param row used to track position on board
	 * @param col used to track position on board
	 * @return true if player number is -1
	 * @return false if cell is occupied by a player
	 */
	public Boolean select (int row, int col) {		
		//if text is blank return true. If text is not blank return false
		if(board[row][col].getPlayerNumber() == -1) {
			board[row][col] = new Cell(player);
			return true;
		}
		else      
			return false;		
	}


	/**
	 * this method is called from the SurroundPanel class
	 * and it resets the board to a new game.
	 */
	public void reset() {
		for (int row = 0; row < boardSize; row++) 
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = new Cell(-1);
				check[row][col] = new isVisited(false);
				neighbors.removeAll(neighbors);
			}		
	}


	/**
	 * determines if a player has won the game. Return -1 if there is no winner.
	 * Return playerNumber if there is a winner.
	 * @return value
	 */
	public int isWinner() {
		int value = -1;
		for (int row = 0 ; row < board.length; row++)
			for (int col = 0; col < board[0].length; col++) {
				value = isSurrounded(row, col);
				if (value != -1)
					if (value != board[row][col].getPlayerNumber())
						return value;
			}

		return value;
	}


	/**
	 * Checks the board for surrounded players and returns the winner.
	 * @param row used to track position on board
	 * @param col used to track position on board
	 * @return playerNumber found in the cell of the winner if no
	 * @return -1 the value of an empty cell is returned for cases
	 * of no winner.
	 */
	public int isSurrounded(int row, int col) {

				//top-left corner case (check 2 sides only)
				if (row == 0 && col == 0) 	
					if (board[0][1].getPlayerNumber() != -1 &&
					board[1][0].getPlayerNumber() != -1)
						if (board[0][1].getPlayerNumber() == 
						board[1][0].getPlayerNumber())	
							if((board[0][1].getPlayerNumber() !=
						board[row][col].getPlayerNumber()) &&
									(board[0][0].getPlayerNumber() != -1))
								return board[0][1].getPlayerNumber();
		
				//top right corner (check 2 sides only)
			if(row == 0 && col == board[0].length-1)
					if(board[0][board[0].length-2].getPlayerNumber() != 
					-1 && board[1][board[0].length-1].getPlayerNumber() != -1)
						if(board[0][board[0].length-2].getPlayerNumber() ==
						board[1][board[0].length-1].getPlayerNumber())
							if (board[0][board[0].length-2].getPlayerNumber() != 
							board[row][col].getPlayerNumber())
								if(board[row][col].getPlayerNumber() != -1)
									return board[1][board[0].length-1].getPlayerNumber();
		
				//bottom left corner (check 2 sides only)
				if (row == (boardSize -1) && col == 0)
					if (board[boardSize-2][0].getPlayerNumber() !=
					-1 && board[row][col].getPlayerNumber() != -1)
						if(board[boardSize-2][0].getPlayerNumber() ==
						board[row][col+1].getPlayerNumber())
							if(board[boardSize-2][0].getPlayerNumber() !=
							board[row][col].getPlayerNumber())
								return board[boardSize-2][0].getPlayerNumber();
		
				//bottom right corner (check 2 sides only)
			if (row == (boardSize -1) && col == board[0].length-1)
					if(board[row-1][col].getPlayerNumber() != -1 &&
					board[row][col-1].getPlayerNumber() != -1)
					if(board[row-1][col].getPlayerNumber() ==
						board[row][col-1].getPlayerNumber())
							if(board[row-1][col].getPlayerNumber() !=
							board[row][col].getPlayerNumber())
								if(board[row][col].getPlayerNumber() != -1)
									return board[row-1][col].getPlayerNumber();
		
				//All four sides
				if(row > 0 && row < boardSize -1 && col > 0 && col < boardSize -1)
					if(board[row-1][col].getPlayerNumber() != -1 &&
				board[row+1][col].getPlayerNumber() != -1 &&
					board[row][col-1].getPlayerNumber() != -1 &&
					board[row][col+1].getPlayerNumber() != -1)
						if (board[row-1][col].getPlayerNumber() ==
						board[row+1][col].getPlayerNumber() &&
						board[row-1][col].getPlayerNumber() ==
						board[row][col-1].getPlayerNumber() && 
						board[row-1][col].getPlayerNumber() ==
						board[row][col+1].getPlayerNumber())
							if (board[row-1][col].getPlayerNumber() !=
						board[row][col].getPlayerNumber())
								if(board[row][col].getPlayerNumber() != -1)
									return board[row-1][col].getPlayerNumber();
				//bottom row
				if(row == boardSize -1 && col > 0 && col < boardSize - 1)
					if(board[row][col-1].getPlayerNumber() != -1 &&
					board[row][col+1].getPlayerNumber() != -1)
						if(board[row][col-1].getPlayerNumber() ==
						board[row][col+1].getPlayerNumber() &&
						board[row][col-1].getPlayerNumber() ==
						board[row-1][col].getPlayerNumber())
							if (board[row][col-1].getPlayerNumber() !=
							board[row][col].getPlayerNumber())
								if (board[row][col].getPlayerNumber() != -1)
									return board[row][col-1].getPlayerNumber();
		
				//top row
				if(row == 0 && col > 0 && col < boardSize - 1)
					if(board[row+1][col].getPlayerNumber() != -1 &&
					board[row][col-1].getPlayerNumber() != -1 &&
					board[row][col+1].getPlayerNumber() != -1)
						if(board[row+1][col].getPlayerNumber() ==
						board[row][col-1].getPlayerNumber() &&
						board[row+1][col].getPlayerNumber() ==
						board[row][col+1].getPlayerNumber())
						if(board[row+1][col].getPlayerNumber() !=
							board[row][col].getPlayerNumber())
								if (board[row][col].getPlayerNumber() != -1)
									return board[row+1][col].getPlayerNumber();
		
				//left side
				if(row > 0 && row < boardSize -1 && col == 0)
					if(board[row+1][col].getPlayerNumber() != -1 &&
					board[row-1][col].getPlayerNumber() != -1 && 
					board[row][col+1].getPlayerNumber() != -1)
						if(board[row+1][col].getPlayerNumber() ==
						board[row-1][col].getPlayerNumber() && 
						board[row+1][col].getPlayerNumber() ==
						board[row][col+1].getPlayerNumber())
							if(board[row+1][col].getPlayerNumber() !=
							board[row][col].getPlayerNumber())
								if (board[row][col].getPlayerNumber() != -1)
								return board[row+1][col].getPlayerNumber();
				//right side
				if(row > 0 && row < boardSize -1 && col == boardSize - 1)
					if(board[row+1][col].getPlayerNumber() != -1 &&
					board[row-1][col].getPlayerNumber() != -1 && 
					board[row][col-1].getPlayerNumber() != -1)
					if(board[row+1][col].getPlayerNumber() ==
						board[row-1][col].getPlayerNumber() && 
						board[row+1][col].getPlayerNumber() == board
						[row][col-1].getPlayerNumber())
							if(board[row+1][col].getPlayerNumber() !=
							board[row][col].getPlayerNumber())
								if (board[row][col].getPlayerNumber() != -1)
									return board[row+1][col].getPlayerNumber();
		if(board[row][col].getPlayerNumber() != -1) {
			completeSurround(row,col);
			return advancedWin();
		}
		else
			return -1;

	} 


	public void completeSurround (int row, int col) {


		int baseCell = board[row][col].getPlayerNumber();
		//check[row][col] = new isVisited(true);

		if (col - 1 >= 0)
		{
			if (check[row][col-1].getVisited() != true)
			{
				if(baseCell != board[row][col-1].getPlayerNumber())
				{
					//					if(board[row][col-1].getPlayerNumber() != -1)
					//					{
					neighbors.add(board[row][col-1].getPlayerNumber());
					//					}
					check[row][col-1] = new isVisited(true);
				}
				else 
				{
					check[row][col] = new isVisited(true);
					completeSurround(row,col-1);				
				}
			}
		}

		if(col + 1 <= boardSize - 1)
		{
			if (check[row][col+1].getVisited() != true)
			{
				if(baseCell != board[row][col+1].getPlayerNumber())
				{
					//					if(board[row][col+1].getPlayerNumber() != -1) 
					//					{
					neighbors.add(board[row][col+1].getPlayerNumber());
					//					}					
					check[row][col+1] = new isVisited(true);
				}
				else
				{
					check[row][col] = new isVisited(true);
					completeSurround(row,col+1);				
				}
			}
		}

		if(row - 1 >= 0)
		{
			if(check[row-1][col].getVisited() != true)
			{			
				if(baseCell != board[row-1][col].getPlayerNumber())
				{
					//					if(board[row-1][col].getPlayerNumber() != -1)
					//					{
					neighbors.add(board[row-1][col].getPlayerNumber());
					//					}
					check[row-1][col] = new isVisited(true);
				}
				else
				{
					check[row][col] = new isVisited(true);
					completeSurround(row-1,col);
				}					
			}
		}

		if (row+1 <= boardSize -1) {
			if (check[row+1][col].getVisited() != true)
			{
				if(baseCell != board[row+1][col].getPlayerNumber())
				{				
//					if(board[row-1][col].getPlayerNumber() != -1)
//					{
//						neighbors.add(board[row-1][col].getPlayerNumber());
//					}
					check[row+1][col] = new isVisited(true);
				}
				else
				{
					check[row][col] = new isVisited(true);				
					completeSurround(row+1,col);
				}
			}
		}

	}


	public int advancedWin() {
		//this may need to be a separate method
		int listCount = getListCount();
		int count = 0;
		for (int i = 0; i < listCount - 1; i++) {
			if(neighbors.get(i) == neighbors.get(i+1)) {
				count++; //incremented when a list item equals the next
			}		

		}
		//if count is incremented the same number of times
		//as the list capacity then each item equals the
		//the other. Meaning the baseCell is surrounded.
		if(count == listCount - 1)
			return neighbors.get(0);
		else 
			return -1;
	}



	/**
	 * Provides the neighbor list capacity
	 * This is used to determine if all the list items
	 * equal each other.
	 * @return neighbors.size() the size of the list
	 */
	public int getListCount () {
		return neighbors.size();
	}


	/**
	 * Used to track the current player
	 * @return player the current player
	 */
	public int getPlayerNumber() {

		return player;
	}


	/**
	 * A method used rotate players based on the
	 * total amount of players identified by user
	 * @return player which is the current player
	 */
	public int nextPlayer() {

		player++;
		if (player >= totalPlayers)
			player = 0;                                  

		return player;
	}

}

