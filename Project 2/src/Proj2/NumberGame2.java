package Proj2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/******************************************************************
 * Simulates the 1024 game with both a text and graphical ui
 *****************************************************************/
public class NumberGame2 implements NumberSlider {
	
	/** value for win */
	private static final GameStatus USER_WON = null;
	
	/** value for game in progress */
	private static final GameStatus IN_PROGRESS = null;
	
	/** value for game lost */
	private static final GameStatus USER_LOST = null;
	
	/** array of numbers in grid */
	private int[][] gridArray;
	
	/** cells with values for row, col, and value */
	private ArrayList<Cell> cells;
	
	/** value to win the game */
	private int winningValue;

	/** deque used for storing past boards */
	Deque<ArrayList<Cell>> deque;

	@Override
	public void resizeBoard
		(int height, int width, int winningValue) {
		//Store boards in deque
		deque = new ArrayDeque<ArrayList<Cell>>();
		
		gridArray = new int[height][width];
		cells = new ArrayList<Cell>();
		this.winningValue = winningValue;

		//Throw exception for invalid entry
		if(height < 0 || width < 0 || winningValue < 0)
			throw new IllegalArgumentException();

	}

	@Override
	public void reset() {

		// Clear board
		for (int r = 0; r < gridArray.length; r++) {
			for (int c = 0; c < gridArray[0].length; c++) {
				gridArray[r][c] = 0;
			}
		}

		// Clear deque
		deque.clear();

		placeRandomValue();
		placeRandomValue();
	}

	@Override
	public void setValues(int[][] ref) {
		
		if (gridArray[0].length == ref[0].length && 
				gridArray.length == ref.length) {
			for (int c = 0; c < gridArray.length; c++) {
				for (int r = 0; r < gridArray[c].length; r++) {
					gridArray[c][r] = ref[c][r];
				}
			}
		} else {
			// throw illegal argument
			throw new IllegalArgumentException();
		}

	}

	@Override
	public Cell placeRandomValue() {

		// Create random object
		Random rgen = new Random();
		int r = 0;
		int c = 0;

		// Look for random cell in grid, use if blank
		do {
			r = rgen.nextInt(gridArray.length);
			c = rgen.nextInt(gridArray[0].length);

		} while (gridArray[r][c] != 0);

		Cell randCell = new Cell();
		randCell.row = r;
		randCell.column = c;

		int randValue = 2;
		int x = rgen.nextInt(2);

		// Get random value as 2, 4, or 8
		switch (x) {
		case 0:
			randValue = 2;
			break;
		case 1:
			randValue = 4;
			break;
		}
		
		// Set generated value to cell
		randCell.value = randValue;
		gridArray[r][c] = randValue;

		return randCell;
	}

	@Override
	public boolean slide(SlideDirection dir) {

		// Add board to deque if no saved boards
		if (deque.isEmpty())
			deque.addLast(getNonEmptyTiles());

		ArrayList<Integer> moveList = new ArrayList<Integer>();

		switch (dir) {
		case UP:
			for (int c = 0; c < gridArray[0].length; c++) {
				for (int r = 0; r < gridArray.length; r++) {
					if (gridArray[r][c] != 0) {
						moveList.add(gridArray[r][c]);
					}

				}

				moveList = addTiles(moveList);

				// Put added tiles for column into grid
				for (int r = 0; r < gridArray.length; r++) {
					
					if (r < moveList.size())
						gridArray[r][c] = moveList.get(r);
					else
						gridArray[r][c] = 0;
				}

				// reset numbers in list for next column
				moveList.clear();
			}
			break;

		case DOWN:
			for (int c = 0; c < gridArray[0].length; c++) {
				for (int r = 0; r < gridArray.length; r++) {
					if (gridArray[r][c] != 0)
						moveList.add(gridArray[r][c]);
				}

				moveList = addTiles(moveList);

				int count = 1;

				// Put added tiles for column into grid
				for (int r = gridArray.length - 1; r >= 0; r--) {
					
					if (count < (moveList.size() + 1)) {
						gridArray[r][c] = moveList.get
								(moveList.size() - count);
					} else
						gridArray[r][c] = 0;
					count++;
				}

				// reset numbers in list for next column
				moveList.clear();
			}
			break;

		case LEFT:
			for (int r = 0; r < gridArray.length; r++) {
				for (int c = 0; c < gridArray[0].length; c++) {
					if (gridArray[r][c] != 0)
						moveList.add(gridArray[r][c]);
				}

				moveList = addTiles(moveList);
				
				// Put added tiles for column into grid
				for (int c = 0; c < gridArray[0].length; c++) {

					if (c < moveList.size())
						gridArray[r][c] = moveList.get(c);
					else
						gridArray[r][c] = 0;
				}

				// reset numbers in list for next column
				moveList.clear();
			}
			break;

		case RIGHT:
			for (int r = 0; r < gridArray.length; r++) {
				for (int c = 0; c < gridArray[0].length; c++) {
					if (gridArray[r][c] != 0)
						moveList.add(gridArray[r][c]);
				}

				moveList = addTiles(moveList);

				int count = 1;

				// Put added tiles for column into grid
				for (int c = gridArray[0].length - 1; c >= 0; c--) {
					
					if (count < (moveList.size() + 1))
						gridArray[r][c] = moveList.get
						(moveList.size() - count);
					else
						gridArray[r][c] = 0;
					count++;
				}

				// reset numbers in list for next column
				moveList.clear();
			}
			break;
		}

		// Compare board produced from movement to previous 
		//board using deque
		ArrayList<Cell> one, two;

		one = getNonEmptyTiles();
		two = (ArrayList<Cell>) deque.peekLast();

		Set<Cell> s1 = new TreeSet<>(one);
		Set<Cell> s2 = new TreeSet<>(two);

		// Check if movement produced a change, if so add to 
		//saved boards
		if (!s1.equals(s2)) {
			if (getStatus() == GameStatus.IN_PROGRESS)
				placeRandomValue();
			deque.addLast(getNonEmptyTiles());
			return true;
		}
		return false;

	}

	//Add the tiles in an arraylist
	private ArrayList<Integer> addTiles
		(ArrayList<Integer> moveList) {

		if (moveList.size() > 1) {

			// add numbers next to each other
			for (int i = 0; i < moveList.size() - 1; i++) {

				// Search for two values next to each other
				if (moveList.get(i) == moveList.get(i + 1)) {

					// Add values together, put in position of 
					//first value
					moveList.set(i, moveList.get(i) * 2);

					// Remove second value
					moveList.remove(i + 1);
				}

			}
		}

		return moveList;
	}

	@Override
	public ArrayList<Cell> getNonEmptyTiles() {
		ArrayList<Cell> filledCellList = new ArrayList<Cell>();
		
		//Loop through array
		for (int r = 0; r < gridArray.length; r++) {
			for (int c = 0; c < gridArray[r].length; c++) {
				
				//Look for empty cells
				if (gridArray[r][c] != 0) {
					Cell filledCell = new 
							Cell(r, c, gridArray[r][c]);
					filledCellList.add(filledCell);
				}
			}
		}
		return filledCellList;
	}

	@Override
	public GameStatus getStatus() {

		for (int r = 0; r < gridArray.length; r++) {
			for (int c = 0; c < gridArray[0].length; c++) {

				// Winning status if a cell has the winning value
				if (gridArray[r][c] == this.winningValue)
					return GameStatus.USER_WON;
			}
		}

		// "Reset" value to check against for cells next to each other
		int prevValue = -1;
		for (int r = 0; r < gridArray.length; r++) {
			for (int c = 0; c < gridArray[0].length; c++) {

				// Check for same #s next to each other in same column
				if (prevValue == gridArray[r][c])
					return GameStatus.IN_PROGRESS;

				// Check for an empty cell
				if (gridArray[r][c] == 0)
					return GameStatus.IN_PROGRESS;

				// Save value to check next
				prevValue = gridArray[r][c];
			}
			prevValue = -1;
		}

		for (int r = 0; r < gridArray.length; r++) {
			for (int c = 0; c < gridArray[0].length; c++) {

				// Check for same #s next to each other in same row
				if (prevValue == gridArray[r][c])
					return GameStatus.IN_PROGRESS;

				// Save value to check next
				prevValue = gridArray[r][c];
			}
			prevValue = -1;
		}
		return GameStatus.USER_LOST;
	}

	@Override
	public void undo() {

		// Check for empty deque
		if (deque.isEmpty())
			deque.addLast(getNonEmptyTiles());

		// Store the last element in the deque (current board)
		ArrayList<Cell> temp = new ArrayList<Cell>();
		temp = deque.pollLast();

		// Check for previous saved board
		if (deque.peekLast() != null) {

			// Reset all grid elements to 0
			for (int r = 0; r < gridArray.length; r++)
				for (int c = 0; c < gridArray[0].length; c++)
					gridArray[r][c] = 0;

			// Fill in array using non-empty cells from last deque
			for (Cell x : deque.peekLast())
				gridArray[x.row][x.column] = x.value;

		} else {
			deque.addLast(temp);
			throw new IllegalStateException();
		}

	}

}

