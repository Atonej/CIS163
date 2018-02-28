package GUI;

import java.awt.Color;

/***********************************************************************
 * This class defines colors to be used in the game, even when slide
 * is called
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class Colors {
	/** frame color */
	public static final Color FRAME_BACKGROUND = new Color(250, 248, 239);
	/** board color */

	public static final Color BOARD_BACKGROUND = new Color(187, 173, 160);
	/** primary color */
	public static final Color NUMBER_COLOR_1 = new Color(118, 110, 101);
	/** secondary color */
	public static final Color NUMBER_COLOR_2 = Color.white;

/***********************************************************************
* Set tile colors for each number gained to
***********************************************************************/
	public static final Color[] TILE_COLORS = { new Color(205, 192, 180),
			new Color(205, 192, 180), new Color(238, 228, 218), // 2
			new Color(237, 224, 200), // 4
			new Color(242, 177, 121), // 8
			new Color(245, 149, 99), // 16
			new Color(246, 124, 95), // 32
			new Color(245, 94, 60), // 64
			new Color(239, 211, 114), // 128
			new Color(239, 207, 99), // 256
			new Color(231, 198, 75), // 512
			new Color(238, 198, 66), // 1024
			new Color(239, 193, 40), // 2048
			new Color(239, 106, 107), // 4096
			new Color(230, 77, 82), // 8192
			new Color(231, 69, 58), // 16384
			new Color(116, 183, 214), // 32768
			new Color(90, 158, 222), // 65536
			new Color(96, 162, 79) // 131072
	};
}
