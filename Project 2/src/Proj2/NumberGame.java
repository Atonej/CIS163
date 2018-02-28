package Proj2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by dulimarh on 6/30/14.
 */
public class NumberGame implements NumberSlider {
	private int GOAL_VALUE;
	private int[][] grid;
	private ArrayList<Cell> cells;
	private Stack<ArrayList<Cell>> moves;
	private Random rgen;
	private GameStatus gameStatus;
	private int numFilledCells;

	@Override
	public void resizeBoard(int NROW, int NCOL, int goal) {
		grid = new int[NROW][NCOL];
		cells = new ArrayList<Cell>();
		rgen = new Random();
		numFilledCells = 0;
		GOAL_VALUE = goal;
		gameStatus = GameStatus.IN_PROGRESS;
		if (moves != null)
			moves.clear();
		else
			moves = new Stack<ArrayList<Cell>>();

		// generateNewCell();
	}

	@Override
	public void reset() {
		gameStatus = GameStatus.IN_PROGRESS;
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c < grid[r].length; c++)
				grid[r][c] = 0;
		placeRandomValue();
		placeRandomValue();
	}

	/* The setValues() method is needed for testing */
	@Override
	public void setValues(final int[][] input) {
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c < grid[0].length; c++)
				grid[r][c] = input[r][c];
	}

	private int randomTileValue() /* generate random value 2, 4, 8, 16 */
	{
		int val = 2;
		for (int k = 0; k < rgen.nextInt(2); k++)
			val *= 2;
		return val;
	}

	@Override
	public Cell placeRandomValue() {
		int r, c;
		boolean foundEmpty = false;
		for (int[] row : grid)
			for (int x : row)
				if (x == 0) {
					foundEmpty = true;
					break;
				}
		if (!foundEmpty)
			throw new IllegalStateException(
					"No empty spot to place random value");
		do {
			r = rgen.nextInt(grid.length);
			c = rgen.nextInt(grid[0].length);
		} while (grid[r][c] != 0);

		grid[r][c] = 2;
		Cell val = new Cell();
		val.row = r;
		val.column = c;
		val.value = grid[r][c];
		return val;
	}

	private boolean moveInRow(int r, int startCol, int endCol, int dCol) {
		if (startCol == endCol)
			return false;
		int dest = startCol; /* index of destination cell */
		int c = startCol + dCol;
		while (c != endCol && grid[r][c] == 0)
			c += dCol;
		int lastValueMoved;
		if (grid[r][startCol] == 0)
			lastValueMoved = -1;
		else
			lastValueMoved = grid[r][startCol];
		/*
		 * when lastValueMoved is -1, dest is the position of an empty cell when
		 * lastValueMoved is >= 0, dest is the position of a filled cell
		 */
		int updateCount = 0;
		while (c != endCol) {
			if (grid[r][c] == 0) {
				c += dCol;
				continue;
			}
			if (lastValueMoved == -1) { /* nothing was moved last time */
				grid[r][dest] = grid[r][c];
				lastValueMoved = grid[r][c];
				grid[r][c] = 0;
				updateCount++;
			} else {
				/* we moved a cell last time */
				if (grid[r][c] == lastValueMoved) {
					grid[r][dest] *= 2; /* merge and sum */
					grid[r][c] = 0;
					lastValueMoved = -1;
					dest += dCol;
					updateCount++;
				} else {
					dest += dCol;
					if (grid[r][dest] == 0) {
						grid[r][dest] = grid[r][c];
						grid[r][c] = 0;
						updateCount++;
					}
					lastValueMoved = grid[r][dest];
				}
			}
			c += dCol;
		}
		return updateCount != 0;
	}

	private boolean moveInColumn(int c, int startRow, int endRow, int dRow) {
		if (startRow == endRow)
			return false;
		int dest = startRow; /* index of destination cell */
		int r = startRow + dRow;
		while (r != endRow && grid[r][c] == 0)
			r += dRow;
		int lastValueMoved;
		if (grid[startRow][c] == 0)
			lastValueMoved = -1;
		else
			lastValueMoved = grid[startRow][c];
		/*
		 * when lastValueMoved is -1, dest is the position of an empty cell when
		 * lastValueMoved is >= 0, dest is the position of a filled cell
		 */
		int updateCount = 0;
		while (r != endRow) {
			if (grid[r][c] == 0) {
				r += dRow;
				continue;
			}
			if (lastValueMoved == -1) { /* nothing was moved last time */
				grid[dest][c] = grid[r][c];
				lastValueMoved = grid[r][c];
				grid[r][c] = 0;
				updateCount++;
			} else {
				/* we moved a cell last time */
				if (grid[r][c] == lastValueMoved) {
					grid[dest][c] *= 2; /* merge and sum */
					lastValueMoved = -1;
					dest += dRow;
					grid[r][c] = 0;
					updateCount++;
				} else {
					dest += dRow;
					if (grid[dest][c] == 0) {
						grid[dest][c] = grid[r][c];
						grid[r][c] = 0;
						updateCount++;
					}
					lastValueMoved = grid[dest][c];
				}
			}
			r += dRow;
		}
		return updateCount != 0;
	}

	/*
	 * THe following solution is from Hans and does not use and arraylist... I
	 * think it can simplified a bit.
	 */
	/* return true if the move changes the board */
	// @Override
	public boolean slide1(SlideDirection dir) {
		boolean moveMade = false;
		for (int k = 0; k < grid.length; k++)
			for (int m = 0; m < grid[k].length; m++)
				grid[k][m] = Math.abs(grid[k][m]);

		/*
		 * We must must a new object because my implementation of
		 * getNonEmptyTiles() reuses the same ArrayList object
		 */
		moves.push(new ArrayList(getNonEmptyTiles()));

		switch (dir) {
		case UP:
			for (int k = 0; k < grid[0].length; k++)
				moveMade |= moveInColumn(k, 0, grid.length, +1);
			break;
		case DOWN:
			for (int k = 0; k < grid[0].length; k++)
				moveMade |= moveInColumn(k, grid.length - 1, -1, -1);
			break;
		case LEFT:
			for (int k = 0; k < grid.length; k++)
				moveMade |= moveInRow(k, 0, grid[k].length, +1);
			break;
		case RIGHT:
			for (int k = 0; k < grid.length; k++)
				moveMade |= moveInRow(k, grid[k].length - 1, -1, -1);
			break;
		default:
		}
		if (moveMade) {
			for (int[] row : grid) {
				for (int v : row)
					if (v == GOAL_VALUE) {
						gameStatus = GameStatus.USER_WON;
						return true;
					}
			}
			try {
				placeRandomValue();
			} catch (IllegalStateException e) {
				gameStatus = GameStatus.USER_LOST;
			}
		} else {
			moves.pop();
		}
		return moveMade;
	}

	/*
	 * THe following solution is from Me (Roger) and does use an arraylist... It
	 * is very simple and only covers the 'up' direction. However, it would be
	 * very easy to solve for the the other directions.
	 */
	/* return true if the move changes the board */
	@Override
	public boolean slide(SlideDirection dir) {
		boolean moveMade = false;
		moves.push(new ArrayList<Cell>(getNonEmptyTiles()));

		switch (dir) {
		case UP:
			for (int c = 0; c < grid[0].length; c++) {
				ArrayList<Integer> listWithOutSpaces = new ArrayList<Integer>();
				for (int r = 0; r < grid.length; r++)
					if (grid[r][c] != 0)
						listWithOutSpaces.add((Integer) grid[r][c]);

				ArrayList<Integer> mergedList = merge(listWithOutSpaces,
						grid.length);

				for (int i = 0; i < mergedList.size(); i++)
					if (grid[i][c] != (int) mergedList.get(i)) {
						moveMade = true;
						grid[i][c] = (int) mergedList.get(i);
					}
			}
			break;

		case DOWN:
			for (int c = 0; c < grid[0].length; c++) {
				ArrayList<Integer> listWithOutSpaces = new ArrayList<Integer>();
				for (int r = 0; r < grid.length; r++)
					if (grid[r][c] != 0)
						listWithOutSpaces.add(0, (Integer) grid[r][c]);

				ArrayList<Integer> mergedList = merge(listWithOutSpaces,
						grid.length);

				for (int i = 0; i < mergedList.size(); i++)
					if (grid[i][c] != (int) mergedList.get(mergedList.size()
							- 1 - i)) {
						moveMade = true;
						grid[i][c] = (int) mergedList.get(mergedList.size() - 1
								- i);
					}
			}

			break;
		case LEFT:
			for (int r = 0; r < grid.length; r++) {
				ArrayList<Integer> listWithOutSpaces = new ArrayList<Integer>();
				for (int c = 0; c < grid[0].length; c++)
					if (grid[r][c] != 0)
						listWithOutSpaces.add((Integer) grid[r][c]);

				ArrayList<Integer> mergedList = merge(listWithOutSpaces,
						grid[0].length);

				for (int i = 0; i < mergedList.size(); i++)
					if (grid[r][i] != (int) mergedList.get(i)) {
						moveMade = true;
						grid[r][i] = (int) mergedList.get(i);
					}
			}
			break;

		case RIGHT:
			for (int r = 0; r < grid.length; r++) {
				ArrayList<Integer> listWithOutSpaces = new ArrayList<Integer>();
				for (int c = 0; c < grid[0].length; c++)
					if (grid[r][c] != 0)
						listWithOutSpaces.add(0, (Integer) grid[r][c]);

				ArrayList<Integer> mergedList = merge(listWithOutSpaces,
						grid[0].length);

				for (int i = 0; i < mergedList.size(); i++)
					if (grid[r][i] != (int) mergedList.get(mergedList.size()
							- 1 - i)) {
						moveMade = true;
						grid[r][i] = (int) mergedList.get(mergedList.size() - 1
								- i);
					}
			}

			break;
		default:
		}
		if (moveMade) {
			for (int[] row : grid) {
				for (int v : row)
					if (v == GOAL_VALUE) {
						gameStatus = GameStatus.USER_WON;
						return true;
					}
			}
			try {
				placeRandomValue();
			} catch (IllegalStateException e) {
				gameStatus = GameStatus.USER_LOST;
			}
		} else {
			moves.pop();
		}
		return moveMade;
	}

	/**
	 * @param listWithOutSpaces
	 * @return
	 */
	private ArrayList<Integer> merge(ArrayList<Integer> listWithOutSpaces,
			int size) {
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		listWithOutSpaces.add(0); // taking 2 at a time, need extra 0
		for (int i = 0; i < listWithOutSpaces.size() - 1; i++)
			if ((int) listWithOutSpaces.get(i) == (int) listWithOutSpaces
					.get(i + 1)) {
				mergedList.add((int) listWithOutSpaces.get(i)
						+ (int) listWithOutSpaces.get(i + 1));
				i++;
			}

			else if ((int) listWithOutSpaces.get(i) != 0) {
				mergedList.add(listWithOutSpaces.get(i));
			}

		// match the arrayList with the row or col sent here and fill with zeros
		while (size != mergedList.size())
			mergedList.add(0);

		return mergedList;
	}

	@Override
	public ArrayList<Cell> getNonEmptyTiles() {
		ArrayList cells = new ArrayList<Cell>();
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c < grid[0].length; c++)
				if (grid[r][c] != 0) {
					Cell cel = new Cell();
					cel.row = r;
					cel.column = c;
					cel.value = grid[r][c];
					cells.add(cel);
				}
		return cells;
	}

	private boolean movePossible() {
		/* check for zero */
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++)
				if (grid[r][c] == 0)
					return true;
		}

		/* check for adjacent duplicate entries in row */
		for (int r = 0; r < grid.length; r++)
			for (int c = 0; c + 1 < grid[r].length; c++)
				if (grid[r][c] == grid[r][c + 1])
					return true;

		/* check for adjacent duplicate entries in column */
		for (int r = 0; r + 1 < grid.length; r++)
			for (int c = 0; c < grid[r].length; c++)
				if (grid[r][c] == grid[r + 1][c])
					return true;
		return false;

	}

	// public boolean hasWon ()
	// {
	// for (int[] rows : grid)
	// for (int x : rows)
	// if (x == GOAL_VALUE) return true;
	// return false;
	// }

	@Override
	public GameStatus getStatus() {
		if (movePossible())
			return gameStatus;
		else
			return GameStatus.USER_LOST;
	}

	@Override
	public void undo() {
		if (moves.size() > 0) {
			for (int k = 0; k < grid.length; k++)
				for (int m = 0; m < grid[k].length; m++)
					grid[k][m] = 0;
			for (Cell c : moves.peek()) {
				grid[c.row][c.column] = c.value;
			}
			moves.pop();
		} else
			throw new IllegalStateException("Can't undo further....");
	}
}

// package Proj2;
//
// import java.util.ArrayDeque;
// import java.util.ArrayList;
// import java.util.Deque;
// import java.util.Random;
// import java.util.Set;
// import java.util.TreeSet;
//
// /******************************************************************
// * Simulates the 1024 game with a TextUI
// *
// * @author Atone Joryman
// * @version Winter 2017
// *****************************************************************/
// public class NumberGame implements NumberSlider {
//
// /** value for win */
// private static final GameStatus USER_WON = null;
//
// /** value for game in progress */
// private static final GameStatus IN_PROGRESS = null;
//
// /** value for game lost */
// private static final GameStatus USER_LOST = null;
//
// /** array of numbers in grid */
// private int[][] ;
//
// /** cells with values for row, col, and value */
// private ArrayList<Cell> cells;
//
// /** value to win the game */
// private int winningValue;
//
// /** deque used for storing past boards */
// Deque<ArrayList<Cell>> deque;
//
// /*****************************************************************
// * Method used for the calling of the board size
// * @param height of the board
// * @param width of the board
// * @param number goal set for the user to reach
// * @throws if any of the values of user input is negative
// *****************************************************************/
// public void resizeBoard(int height, int width, int winningValue) {
// // Store boards in deque
// deque = new ArrayDeque<ArrayList<Cell>>();
//
// gridArray = new int[height][width];
// cells = new ArrayList<Cell>();
// this.winningValue = winningValue;
//
// // Throw exception for invalid entry
// if (height < 0 || width < 0 || winningValue < 0)
// throw new IllegalArgumentException();
//
// }
//
// /*****************************************************************
// * Constructor that loops through rows and columns and change them back to
// * empty
// *****************************************************************/
// public void reset() {
//
// // Clear board
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[0].length; c++) {
// gridArray[r][c] = 0;
// }
// }
//
// // Clear deque
// deque.clear();
//
// placeRandomValue();
// placeRandomValue();
// }
//
// /*****************************************************************
// * setter method that runs through board and put in values to a specific
// * area
// * @param set the reference to the grid Array
// * @throws anything less than 0 or outside grid throw
// *****************************************************************/
// public void setValues(int[][] ref) {
//
// if (gridArray[0].length == ref[0].length
// && gridArray.length == ref.length) {
// for (int c = 0; c < gridArray.length; c++) {
// for (int r = 0; r < gridArray[c].length; r++) {
// gridArray[c][r] = ref[c][r];
// }
// }
// } else {
// // throw illegal argument
// throw new IllegalArgumentException();
// }
//
// }
//
// /*****************************************************************
// * Method utilizing Cell class to put spawn a random value either 2 or 4
// * @return that random value into a random cell
// *****************************************************************/
// public Cell placeRandomValue() {
//
// // Create random object
// Random rgen = new Random();
// int r = 0;
// int c = 0;
//
// // Look for random cell in grid, use if blank
// do {
// r = rgen.nextInt(gridArray.length);
// c = rgen.nextInt(gridArray[0].length);
//
// } while (gridArray[r][c] != 0);
//
// Cell randCell = new Cell();
// randCell.row = r;
// randCell.column = c;
//
// int randValue = 2;
// int x = rgen.nextInt(2);
//
// // Get random value as 2 or 4
// switch (x) {
// case 0:
// randValue = 2;
// break;
// case 1:
// randValue = 4;
// break;
// }
//
// // Set generated value to cell
// randCell.value = randValue;
// gridArray[r][c] = randValue;
//
// return randCell;
// }
//
// /*****************************************************************
// * Uses the slide direction class to access a direction using loops
// * @param direction of input
// * @return movement of tile is valid
// * @return movement of tile not valid
// *****************************************************************/
// public boolean slide(SlideDirection dir) {
//
// // Add board to deque if no saved boards
// if (deque.isEmpty())
// deque.addLast(getNonEmptyTiles());
//
// ArrayList<Integer> moveList = new ArrayList<Integer>();
//
// switch (dir) {
// case UP:
// for (int c = 0; c < gridArray[0].length; c++) {
// for (int r = 0; r < gridArray.length; r++) {
// if (gridArray[r][c] != 0) {
// moveList.add(gridArray[r][c]);
// }
//
// }
//
// moveList = addTiles(moveList);
//
// // Put added tiles for column into grid
// for (int r = 0; r < gridArray.length; r++) {
//
// if (r < moveList.size())
// gridArray[r][c] = moveList.get(r);
// else
// gridArray[r][c] = 0;
// }
//
// // reset numbers in list for next column
// moveList.clear();
// }
// break;
//
// case DOWN:
// for (int c = 0; c < gridArray[0].length; c++) {
// for (int r = 0; r < gridArray.length; r++) {
// if (gridArray[r][c] != 0)
// moveList.add(gridArray[r][c]);
// }
//
// moveList = addTiles(moveList);
//
// int count = 1;
//
// // Put added tiles for column into grid
// for (int r = gridArray.length - 1; r >= 0; r--) {
//
// if (count < (moveList.size() + 1)) {
// gridArray[r][c] = moveList.get(moveList.size() - count);
// } else
// gridArray[r][c] = 0;
// count++;
// }
//
// // reset numbers in list for next column
// moveList.clear();
// }
// break;
//
// case LEFT:
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[0].length; c++) {
// if (gridArray[r][c] != 0)
// moveList.add(gridArray[r][c]);
// }
//
// moveList = addTiles(moveList);
//
// // Put added tiles for column into grid
// for (int c = 0; c < gridArray[0].length; c++) {
//
// if (c < moveList.size())
// gridArray[r][c] = moveList.get(c);
// else
// gridArray[r][c] = 0;
// }
//
// // reset numbers in list for next column
// moveList.clear();
// }
// break;
//
// case RIGHT:
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[0].length; c++) {
// if (gridArray[r][c] != 0)
// moveList.add(gridArray[r][c]);
// }
//
// moveList = addTiles(moveList);
//
// int count = 1;
//
// // Put added tiles for column into grid
// for (int c = gridArray[0].length - 1; c >= 0; c--) {
//
// if (count < (moveList.size() + 1))
// gridArray[r][c] = moveList.get(moveList.size() - count);
// else
// gridArray[r][c] = 0;
// count++;
// }
//
// // reset numbers in list for next column
// moveList.clear();
// }
// break;
// }
//
// // Compare board produced from movement to previous
// // board using deque
// ArrayList<Cell> one, two;
//
// one = getNonEmptyTiles();
// two = (ArrayList<Cell>) deque.peekLast();
//
// Set<Cell> s1 = new TreeSet<>(one);
// Set<Cell> s2 = new TreeSet<>(two);
//
// // Check if movement produced a change, if so add to
// // saved boards
// if (!s1.equals(s2)) {
// if (getStatus() == GameStatus.IN_PROGRESS)
// placeRandomValue();
// deque.addLast(getNonEmptyTiles());
// return true;
// }
// return false;
//
// }
//
//
// /*****************************************************************
// * method to check whether or not the grid should be adjusted
// * @param where to move tiles
// * @return the outcome of list
// *****************************************************************/
// private ArrayList<Integer> addTiles(ArrayList<Integer> moveList) {
// // Add the tiles in an arraylist
// if (moveList.size() > 1) {
//
// // add numbers next to each other
// for (int i = 0; i < moveList.size() - 1; i++) {
//
// // Search for two values next to each other
// if (moveList.get(i) == moveList.get(i + 1)) {
//
// // Add values together, put in position of
// // first value
// moveList.set(i, moveList.get(i) * 2);
//
// // Remove second value
// moveList.remove(i + 1);
// }
//
// }
// }
//
// return moveList;
// }
//
// /*****************************************************************
// * getter method to determine whether or not a tile on the board is empty
// * @return the tiles that are filled in the array
// *****************************************************************/
// public ArrayList<Cell> getNonEmptyTiles() {
// ArrayList<Cell> filledCellList = new ArrayList<Cell>();
//
// // Loop through array
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[r].length; c++) {
//
// // Look for empty cells
// if (gridArray[r][c] != 0) {
// Cell filledCell = new Cell(r, c, gridArray[r][c]);
// filledCellList.add(filledCell);
// }
// }
// }
// return filledCellList;
// }
//
// /*****************************************************************
// * getter method for the status(win or lost) of the game
// * @return if user lost
// * @return if user won
// *****************************************************************/
// public GameStatus getStatus() {
//
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[0].length; c++) {
//
// // Winning status if a cell has the winning value
// if (gridArray[r][c] == this.winningValue)
// return GameStatus.USER_WON;
// }
// }
//
// // "Reset" value to check against for cells next to each other
// int prevValue = -1;
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[0].length; c++) {
//
// // Check for same #s next to each other in same column
// if (prevValue == gridArray[r][c])
// return GameStatus.IN_PROGRESS;
//
// // Check for an empty cell
// if (gridArray[r][c] == 0)
// return GameStatus.IN_PROGRESS;
//
// // Save value to check next
// prevValue = gridArray[r][c];
// }
// prevValue = -1;
// }
//
// for (int r = 0; r < gridArray.length; r++) {
// for (int c = 0; c < gridArray[0].length; c++) {
//
// // Check for same #s next to each other in same row
// if (prevValue == gridArray[r][c])
// return GameStatus.IN_PROGRESS;
//
// // Save value to check next
// prevValue = gridArray[r][c];
// }
// prevValue = -1;
// }
// return GameStatus.USER_LOST;
// }
//
// /*****************************************************************
// * Constructor that reverses the new move made into the previous
// * @throws no last move
// *****************************************************************/
// public void undo() {
//
// // Check for empty deque
// if (deque.isEmpty())
// deque.addLast(getNonEmptyTiles());
//
// // Store the last element in the deque (current board)
// ArrayList<Cell> temp = new ArrayList<Cell>();
// temp = deque.pollLast();
//
// // Check for previous saved board
// if (deque.peekLast() != null) {
//
// // Reset all grid elements to 0
// for (int r = 0; r < gridArray.length; r++)
// for (int c = 0; c < gridArray[0].length; c++)
// gridArray[r][c] = 0;
//
// // Fill in array using non-empty cells from last deque
// for (Cell x : deque.peekLast())
// gridArray[x.row][x.column] = x.value;
//
// } else {
// deque.addLast(temp);
// throw new IllegalStateException();
// }
//
// }
//
// }