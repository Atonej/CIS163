package surroundpack2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*****************************************************************
A program that creates a game where players attempt to surround
each other. The first to surround an opposing player wins!

@author Tiesha Anderson
@version October 2017
CIS 163-03
 *****************************************************************/
public class Surround {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Surround");		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SurroundPanel panel = new SurroundPanel();
		JMenuBar mb = new JMenuBar();        
		JMenu menu = new JMenu("Menu");        
		JMenuItem newGameItem = new JMenuItem("New Game");  
		JMenuItem quitItem = new JMenuItem("Quit");


		menu.add(newGameItem);
		menu.add(quitItem);

		mb.add(menu);  
		frame.setJMenuBar(mb); 		

		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
						JOptionPane.YES_NO_OPTION);
				if (dialogButton == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});		

		//Clears board and restarts game
		newGameItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();	
				main(args);				
			}
		});

		frame.getContentPane().add(panel);		
		frame.setSize(700,700);
		frame.setVisible(true);
	}
}
