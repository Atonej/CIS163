package Proj2;

import java.util.ArrayList;

/***
 * With interface in order to use it you need to use a java class that 
 * "has a" interface or in this case NumberSlider.
 * @author ajj_a
 *
 */
public class forFun implements NumberSlider{

	@Override
	public void resizeBoard(int height, int width, int winningValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValues(int[][] ref) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cell placeRandomValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean slide(SlideDirection dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Cell> getNonEmptyTiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
