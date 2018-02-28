package project1;

import javax.swing.JButton;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
/**********************************************************************
 * This class is the mainPanel to be used so I can show 3 stopwatchs 
 * and control what works
 * 
 * @author Atone Joryman
 * @version Fall 2017
 **********************************************************************/

public class mainPanel extends JPanel {
	private JMenuItem quitItem;
	private JMenuItem suspendItem;

	public mainPanel(){
//	JPanel panel = new JPanel();
//	JPanel panel2 = new JPanel();
//	JPanel panel3 = new JPanel();
//	panel.add(new MyTimerPanel(quitItem, suspend));
//	panel2.add(new MyTimerPanel(quitItem, suspend));
//	panel3.add(new MyTimerPanel(quitItem, suspend));
//
//	this.quitItem = quitItem;
//	this.suspendItem = suspendItem;
		
//3 JPanels made
	MyTimerPanel p1 = new MyTimerPanel();
	MyTimerPanel p2 = new MyTimerPanel();
	MyTimerPanel p3 = new MyTimerPanel();
//add to the frame	
	add (p1);
	add (p2);
	add (p3);
//	add (master);
}


}
