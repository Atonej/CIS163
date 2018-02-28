package project2;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.Random;
import java.util.Stack;

/******************************************************************
* Simulates the 1024 game with a TextUI
*
* @author Atone Joryman
* @version Summer 2017
*****************************************************************/
public class NumberGame implements NumberSlider {
	
	/** value to win the game */
	private int theGoal;
	
	/** array of numbers in grid */
	private int[][] grid;
	
	/** cells with values for row, col, and value */
	private ArrayList<Cell> cells;
	
	/**status of the game recorded**/
	private GameStatus gameStatus;
	
	/**store moves being made(past boards) in a stack**/
	private Stack<ArrayList<Cell>> movesMade;
	
	/**used for random generating**/
	private Random random;
	
	/**keep up with cells being filled**/
	private int amtCellsFilled;
   
	/**keep up with the score for each match**/
    private int score = 0;
	
/***********************************************************************
* Method used for the calling of the board size
* @param height of the board
* @param width of the board
* @param number goal set for the user to reach
* @throws if any of the values of user input is negative
* @throws not a power of 2
***********************************************************************/
	@Override
	public void resizeBoard(int numrow, int numcol, int goal) {
		grid = new int[numrow][numcol];
		cells = new ArrayList<Cell>();
		random = new Random();
		amtCellsFilled = 0;
		theGoal = goal;
		gameStatus = GameStatus.IN_PROGRESS;
		if (movesMade != null)
			movesMade.clear();
		// Throw exception for invalid entry
		if (numrow < 0 || numcol < 0 || goal < 0)
		throw new IllegalArgumentException();
		
		//throw if not a power of two 
	
	if ((!(goal % 2 ==0)))
		throw new IllegalArgumentException();
	
	
	
		else
			movesMade = new Stack<ArrayList<Cell>>();

	}
/***********************************************************************
* Constructor that loops through rows and columns and change them back 
* to empty
***********************************************************************/

	@Override
	public void reset() {
		//gamestatus should be in prgress
		gameStatus = GameStatus.IN_PROGRESS;
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c < grid[r].length; c++)
				grid[r][c] = 0;
		// when reset bring in two random values
		placeRandomValue();
		placeRandomValue();
		score=0;
	}

/***********************************************************************
* setter method that runs through board and put in values to a specific
* area
* @param set the reference to the grid Array
***********************************************************************/
	
	@Override
	public void setValues(final int[][] input) {
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c < grid[0].length; c++)
				grid[r][c] = input[r][c];
	}

/***********************************************************************
* Method utilizing Cell class to put spawn a random value either 2 or 4
* @return that random value into a random cell
* @throw if random value is impossible to place
***********************************************************************/
	@Override
	public Cell placeRandomValue() {
		
		// Create random object
		Random rgen = new Random();
		int r = 0;
		int c = 0;
		//no empty tile
		boolean isEmpty = false;
		for (int[] row : grid)
			for (int x : row)
				if (x == 0) {
					isEmpty = true;
					break;
				}
		if (!isEmpty)
			throw new IllegalStateException(
					"No empty spot to place a value");

		// Look for random cell in grid, use if blank
		do {
		r = rgen.nextInt(grid.length);
		c = rgen.nextInt(grid[0].length);

		} while (grid[r][c] != 0);

		Cell randCell = new Cell();
		randCell.setRow(r);
		randCell.setCol(c);

		int randValue = 2;
		int x = rgen.nextInt(5);

		// Get random value as 2 or 4...4/5 chance of 2
		switch (x) {
		case 0:
		randValue = 2;
		break;
		case 1:
		randValue = 2;
		break;
		
		case 2:
		randValue = 2;
		break;
		
		case 3:
		randValue = 2;
		break;
		
		case 4:
		randValue = 4;
		break;
		}

		// Set generated value to cell
		randCell.setValue(randValue);
		grid[r][c] = randValue;

		return randCell;
	}

/***********************************************************************
* Uses the slide direction class to access a direction using loops
* @param direction of input
* @return movement of tile is valid
* @return movement of tile not valid
***********************************************************************/
	@Override
	public boolean slide(SlideDirection dir) {
		boolean moveMade = false;
		movesMade.push(new ArrayList<Cell>(getNonEmptyTiles()));

		switch (dir) {
		
		case UP:
	for (int c = 0; c < grid[0].length; c++) {
		ArrayList<Integer> noZeros = new ArrayList<Integer>();
				for (int r = 0; r < grid.length; r++)
					if (grid[r][c] != 0)
						noZeros.add((Integer) grid[r][c]);

			ArrayList<Integer> addList = addTiles(noZeros,
						grid.length);

				for (int i = 0; i < addList.size(); i++)
					if (grid[i][c] != (int) addList.get(i)) {
						moveMade = true;
						score += (int) addList.get(i); //add score
						grid[i][c] = (int) addList.get(i);
					}
			}
			break;

		case DOWN:
	for (int c = 0; c < grid[0].length; c++) {
		ArrayList<Integer> noZeros = new ArrayList<Integer>();
			for (int r = 0; r < grid.length; r++)
				if (grid[r][c] != 0)
					noZeros.add(0, (Integer) grid[r][c]);

			ArrayList<Integer> addList = addTiles(noZeros,
						grid.length);

			for (int i = 0; i < addList.size(); i++)
				if (grid[i][c] != (int) addList.get(addList.size()
							- 1 - i)) {
						moveMade = true;
						score += (int) addList.get(addList.size() //add score
								- 1 - i);
				grid[i][c] = (int) addList.get(addList.size() - 1
								- i);
					}
			}

			break;
		case LEFT:
	for (int r = 0; r < grid.length; r++) {
				//place row in ArrayList with no zeros
				ArrayList<Integer> noZeros = new ArrayList<Integer>();
				for (int c = 0; c < grid[0].length; c++)
					if (grid[r][c] != 0)
						noZeros.add((Integer) grid[r][c]);
				//combine adjacent tiles with identical values
				ArrayList<Integer> addList = addTiles(noZeros,
						grid[0].length);
				
				for (int i = 0; i < addList.size(); i++)
					if (grid[r][i] != (int) addList.get(i)) {
						moveMade = true;
						score += (int) addList.get(i); //add score
						grid[r][i] = (int) addList.get(i);
					}
			}
			break;

		case RIGHT:
	for (int r = 0; r < grid.length; r++) {
		ArrayList<Integer> noZeros = new ArrayList<Integer>();
				for (int c = 0; c < grid[0].length; c++)
					if (grid[r][c] != 0)
						noZeros.add(0, (Integer) grid[r][c]);

			ArrayList<Integer> addList = addTiles(noZeros,
						grid[0].length);

				for (int i = 0; i < addList.size(); i++)
				if (grid[r][i] != (int) addList.get(addList.size()
							- 1 - i)) {
						moveMade = true;
						score += (int) addList.get(addList.size()
								- 1 - i);	//add score
				grid[r][i] = (int) addList.get(addList.size() - 1
								- i);
					}
			}

			break;
		default:
		}
		if (moveMade) {
			for (int[] row : grid) {
				for (int v : row)
					if (v == theGoal) {
						gameStatus = GameStatus.USER_WON;
						score=0;
						return true;
					}
			}
			try {
				placeRandomValue();
			} catch (IllegalStateException e) {
				gameStatus = GameStatus.USER_LOST;
				score=0;
			}
		} else {
			movesMade.pop();
		}
		return moveMade;
	}

/***********************************************************************
* method to check whether or not the grid should be adjusted
* @param where to move tiles
* @param size of list given by user
* @return the outcome of list
***********************************************************************/
	private ArrayList<Integer> addTiles(ArrayList<Integer> moveList,
			int size) {
		ArrayList<Integer> addList = new ArrayList<Integer>();
		moveList.add(0); // taking 2 at a time, need extra 0
		for (int i = 0; i < moveList.size() - 1; i++)
			if ((int) moveList.get(i) == (int) moveList
					.get(i + 1)) {
				addList.add((int) moveList.get(i)
						+ (int) moveList.get(i + 1));
				i++;
			}

			else if ((int) moveList.get(i) != 0) {
				addList.add(moveList.get(i));
			}

// match the arrayList with the row or col sent here and fill with zeros
		while (size != addList.size())
			addList.add(0);

		return addList;
	}
/***********************************************************************
* getter method to determine whether or not a tile on the board is 
* empty
* @return the tiles that are filled in the array
***********************************************************************/
	@Override
	public ArrayList<Cell> getNonEmptyTiles() {
		ArrayList cells = new ArrayList<Cell>();
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c < grid[0].length; c++)
				if (grid[r][c] != 0) {
					Cell cel = new Cell();
					cel.setRow(r);
					cel.setCol(c);
					cel.setValue(grid[r][c]);
					cells.add(cel);
				}
		return cells;
	}

/***********************************************************************
* getter method for the status(win or lost) of the game, of type 
* GameStatus
* @return if user lost
* @return if user won
* @return if in progress
***********************************************************************/
	@Override
	public GameStatus getStatus() {

		for (int r = 0; r < grid.length; r++) {
		for (int c = 0; c < grid[0].length; c++) {

		// Winning status if a cell has the winning value
		if (grid[r][c] == this.theGoal)
		return GameStatus.USER_WON;
		}
		}

		// "Reset" value to check against for cells next to each other
		int prevValue = -1;
		for (int r = 0; r < grid.length; r++) {
		for (int c = 0; c < grid[0].length; c++) {

		// Check for same #s next to each other in same column
		if (prevValue == grid[r][c])
		return GameStatus.IN_PROGRESS;

		// Check for an empty cell
		if (grid[r][c] == 0)
		return GameStatus.IN_PROGRESS;

		// Save value to check next
		prevValue = grid[r][c];
		}
		prevValue = -1;
		}

		for (int r = 0; r < grid.length; r++) {
		for (int c = 0; c < grid[0].length; c++) {

		// Check for same #s next to each other in same row
		if (prevValue == grid[r][c])
		return GameStatus.IN_PROGRESS;

		// Save value to check next
		prevValue = grid[r][c];
		}
		prevValue = -1;
		}
		return GameStatus.USER_LOST;
		}
/***********************************************************************
* Constructor that reverses the new move made into the previous
* @throws there is no previous move to apply
***********************************************************************/
	@Override
	public void undo() {
		if (movesMade.size() > 0) {
			for (int k = 0; k < grid.length; k++)
				for (int m = 0; m < grid[k].length; m++)
					grid[k][m] = 0;
			for (Cell c : movesMade.peek()) {
				grid[c.getRow()][c.getCol()] = c.getValue();
			}
			movesMade.pop();
		} else{
			//resizeBoard(getRows(), getCols(), theGoal);
			throw new IllegalStateException("Can't undo further....");
		}
		
	}
	
/***********************************************************************
 @return number of rows
***********************************************************************/
	@Override
	public int getRows() {
		//Cell cellRow = new Cell();
		//return cellRow.getRow();
		
		return grid.length;
	}
	
/***********************************************************************
 @return number of columns
***********************************************************************/ 
	@Override
	public int getCols() {
		//Cell cellCols = new Cell();
		//return cellCols.getCol();
		return grid[0].length;
	}
	
/***********************************************************************
@return current game score
***********************************************************************/ 
	@Override
	public int getScore() {
		//Cell cellScore = new Cell();
		//return cellScore.getValue();		
//		int biggestVal=0;
//		for (int r = 0; r < grid.length; r++) {
//		for (int c = 0; c < grid[0].length; c++) {
//		if(grid[r][c]> biggestVal)
//			biggestVal = grid[r][c];
//		// Winning status if a cell has the winning value
////		if (grid[r][c] == this.theGoal)
////			return this.theGoal;
//		}
//		}
//		return biggestVal;
		return score;
		
	}	
	
/***********************************************************************
* Use System.out.print() to display each value
* Use a period for blank tiles (value of 0)
***********************************************************************/
	@Override
	public void printBoard() {
//NumberGame print = new NumberGame();
        
// print the 2D array using dots for a blank space
System.out.println();
for (int k = 0; k < grid.length; k++) {
    for (int m = 0; m < grid[k].length; m++)
        if (grid[k][m] == 0)
            System.out.print(" . ");
        else
            System.out.print(" "+ grid[k][m]+ " ");
    System.out.println();
	
}
}
	
public static void main(String[] args){
	NumberGame n = new NumberGame();
	n.resizeBoard(4, 4, 16);
    int[][] board1 = {
            { 0, 2, 2, 0},
            { 2, 16, 2, 0},
            { 2, 2, 2, 2},
            { 0, 0, 32, 2},
        };  
	n.setValues(board1);
	n.printBoard();
	System.out.println(n.getScore());
	
}

}