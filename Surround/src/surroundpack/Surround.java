package surroundpack;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
/***********************************************************************
 * This class is the starter to the interface of JPanel class 
 * SurroundPanel.
 * 
 * @author Atone Joryman
 * @version Fall 2017
 *
***********************************************************************/
public class Surround {
	/**menu item to quit**/
	private static JMenuItem quitItem = new JMenuItem("QUIT");
	
	/**menu item to create a new game**/
	private static JMenuItem newGameItem = new JMenuItem("NEW GAME");
	
/***********************************************************************
* This main method will call into SurroundPanel and create an 
* application to physical look at.
***********************************************************************/	
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Surround Game");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        //new panel
        SurroundPanel panel = new SurroundPanel(quitItem, newGameItem);
        //put all into frame
        frame.getContentPane().add(panel);
        frame.setSize (500,500);
        frame.setVisible(true);
    }
}
