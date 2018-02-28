package project1;

import javax.swing.JFrame;

/**********************************************************************
 * This will start up the application for the StopWatch class
 * 
 * @author Atone Joryman
 * @version Fall 2017
 **********************************************************************/
public class StopWatchGUI {
	 //-----------------------------------------------------------------
    //  Creates and displays the temperature converter GUI.
    //-----------------------------------------------------------------
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Stop Watch Program");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        //new panel
        mainPanel panel = new mainPanel();
        //put all into frame
        frame.getContentPane().add(panel);
        frame.setSize (450,500);
        frame.setVisible(true);
    }
}
