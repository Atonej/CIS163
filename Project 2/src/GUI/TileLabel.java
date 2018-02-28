package GUI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

/*****************************************************************
 * Special label which will show the number in the tile
 *
 * @author Atone Joryman
 * @version Winter 2017
 ****************************************************************/
public class TileLabel extends JLabel {

	int number = 0;
	/** Set basic font */
	public static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 36);
	/** Set smaller font */
	public static final Font SMALL_FONT = new Font(Font.SANS_SERIF, Font.BOLD,
			24);

	/***********************************************************
	 * Method to return the position of only set bit in number
	 * @param number in bin
	 * @return position of bin
	 **********************************************************/
	int findPosition(int n) {
		int i = n, pos = 1;

		while (i > 0) {
			i = i / 2;
			++pos;
		}

		return pos;
	}

	@Override
	/***********************************************************
	 * Method to control graphics of specified tiles
	 **********************************************************/
	public void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Colors.TILE_COLORS[findPosition(number)
				% (Colors.TILE_COLORS.length)]);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

		if (number > 3)
			g.setColor(Colors.NUMBER_COLOR_2);
		else
			g.setColor(Colors.NUMBER_COLOR_1);

		Font font;
		if (number < 1024) {
			g.setFont(FONT);
			font = FONT;
		} else {
			g.setFont(SMALL_FONT);
			font = SMALL_FONT;
		}

		String str = (number > 0) ? String.valueOf(number) : "";
		g.drawString(str, getWidth() / 2 - str.length() * font.getSize() / 2
				+ 8, getHeight() / 2 + 12);
	}

	/***********************************************************
	 * Setter Method to apply value of i to number position
	 **********************************************************/
	public void setValue(int i) {
		number = i;
	}

}
