package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/*********************************************************************
 * This is the main panel which saves all the label which shows the number in
 * them
 * @author Atone Joryman
 * @version Winter 2017
 ********************************************************************/
public class BoardPanel extends JPanel {

	/** Grid Layout */
	private GridLayout layout;
	/** Tile labels */
	private TileLabel[][] labels;
	/** set up the dimensions */
	private static Dimension tileDimension = new Dimension(
			GraphicalUI.TILE_SIZE, GraphicalUI.TILE_SIZE);
	/** grid array */
	private int[][] grid;

	/***********************************************************************
	 * Method updates the number of labels to be displayed whenever grid is
	 * updated
	 **********************************************************************/
	public void setGrid(int[][] grid) {
		this.grid = grid;
		removeAll();
		layout = new GridLayout(grid.length, grid[0].length, GraphicalUI.GAP,
				GraphicalUI.GAP);
		setLayout(layout);
		setBorder(new LineBorder(Colors.BOARD_BACKGROUND, GraphicalUI.GAP, true));

		labels = new TileLabel[grid.length][grid[0].length];
		for (int i = grid.length - 1; i >= 0; --i) {
			for (int j = grid[i].length - 1; j >= 0; --j) {
				labels[i][j] = new TileLabel();
				labels[i][j].setPreferredSize(tileDimension);
				add(labels[i][j], 0, 0);
			}
		}
	}

	/***********************************************************************
	 * Method adds the color to the background of the game.
	 **********************************************************************/
	public void paintComponent(Graphics g) {
		g.setColor(Colors.BOARD_BACKGROUND);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

		for (int i = 0; i < labels.length; ++i) {
			for (int j = 0; j < labels[i].length; ++j) {
				labels[i][j].setValue(grid[i][j]);
				labels[i][j].paint(g);
			}
		}
		// check grid values
		// System.out.println("\n");
		// for (int k = 0; k < grid.length; k++) {
		// for (int m = 0; m < grid[k].length; m++)
		// if (grid[k][m] == 0)
		// System.out.printf(".");
		// else
		// System.out.printf("%d", grid[k][m]);
		// System.out.println();
		// }
	}
}
