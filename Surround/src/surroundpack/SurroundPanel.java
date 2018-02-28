package surroundpack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




/***********************************************************************
 * This JPanel class will provide the code needed to start up the GUI.
 * Includes all basic functionalities.
 * 
 * @author Atone Joryman
 * @version Fall 2017
 *
***********************************************************************/
public class SurroundPanel extends JPanel {
	/** for saving and loading a file**/
	private static final long serializable = 1L;
	
	/**double array board**/
	private JButton board[][];
	
	/**function under game rule**/
	private SurroundGame game;
	
	/**macro to stick to simple scale**/
	private final static int BDSIZE = 10;
	
	/**JMenu for file menu**/
	private JMenu file;
	
	/**JMenuItem for a new game**/
	private JMenuItem newGameItem;
	
	/**JMenuItem to be able to quit**/
	private JMenuItem quitItem;
	
	/**JPanel to set a panel within Jframe**/
	private JPanel panel;
	
	/**set up an action for when needed**/
	private ButtonListener listener;
	
	/**used to setup player in line**/
	private int player=2;
	
	/** keep up with who is winning **/
	private int winner;

/***********************************************************************
 * A constructor method that sets up the entire panel to Jframe
 * @param quitItem that allows a pass to exit the system
 * @param newGameItem by giving the option to resize and start a new 
 * game
***********************************************************************/	
public SurroundPanel(JMenuItem quitItem, JMenuItem newGameItem){
	//set up board look
	panel = new JPanel();
	//start applying to the cells
	
	
	board = new JButton[BDSIZE][BDSIZE];
	
	//pass gameItem and quitItem to this game and quit
	this.newGameItem = newGameItem;
	this.quitItem= quitItem;
	game = new SurroundGame(player);
	listener = new ButtonListener();
	
	
	//add to file
	file = new JMenu("FILE");
	file.add(this.quitItem);
	file.add(this.newGameItem);
	
	
	//add to menu bar
	JMenuBar menuBar = new JMenuBar();
	menuBar.add(file);
	
	//set the layout for panel 
    panel.setLayout(new GridLayout(10,10,3,3));
	panel.setBackground(Color.BLUE);
	
	
	//apply the board to jpanel	
	updateGrid();
	//action listeners for quit and new game item
	
	this.quitItem.addActionListener(listener);
	this.newGameItem.addActionListener(listener);

	
	this.repaint();
	this.setLayout(new BorderLayout());
	this.add(menuBar, BorderLayout.NORTH);
	this.add(panel, BorderLayout.CENTER);
	
//	this.add(panel, BorderLayout.CENTER);	

}

/***********************************************************************
* This method updates the grid and repaints the board
***********************************************************************/
	public void updateGrid() {

		//if game restarts and board is not empty/null
		if(board!=null){
			panel.removeAll();
			//game.reset();
		}
		panel.revalidate();
		panel.repaint();
		//paint the board
		
		for (int row = 0; row < BDSIZE; row++) 
			for (int col = 0; col < BDSIZE; col++) {
				//board[row][col].setText(" ");
				board[row][col] = new JButton("?");
				board[row][col].addActionListener(listener);
				panel.add(board[row][col]);
				}
JOptionPane.showMessageDialog(null, "This Surround Game has " + 
				player + " player(s)" );

//set the first player 
		player = game.initialPlayer();

	}
	
/***********************************************************************
 * This method will provide the option to continue or not  
 **********************************************************************/
	
public void continueGame(){
	
	
		int reply = 0;
		//display option to start over
reply = JOptionPane.showConfirmDialog(null, 
	"Would you like to start over?"
	, null, JOptionPane.YES_NO_OPTION);
//if just the yes option
		if(reply == JOptionPane.YES_OPTION){
			game.reset();
	String reply2="";
	String reply3 = "";
reply2 = JOptionPane.showInputDialog(null, 
	"What is your number of players?");
if(Integer.parseInt(reply2)>1){
player = Integer.parseInt(reply2);
}
else {
	throw new IllegalArgumentException("This is not a valid input");
}

reply3 = JOptionPane.showInputDialog(null, 
		"What is the size of the board?");
int newSize = Integer.parseInt(reply3);
board = new JButton[newSize][newSize];
			updateGrid();
			
		}
		else {
			System.exit(0);
		}
}
/***********************************************************************
* ActionListener is inherited to perform an ability to function the 
* surround game.
************************************************************************/
private class ButtonListener implements ActionListener{
	@Override
	public void actionPerformed (ActionEvent event){
		
	
		// Determine which button was selected.
		for (int row = 0; row < BDSIZE; row++){ 
			for (int col = 0; col < BDSIZE; col++){	
				panel.repaint();
			panel.revalidate();
				if (board [row][col] == event.getSource()) {
					
					//check if button was selected just once
					if (game.select(row, col)){
						
						//when not going into next player
						if (player >0 ){

							board[row][col].setText("" + player);
							 
							game.setBoard(row, col);							
							player = game.nextPlayer();

//game.board[row][col]= null; //tried to make a protected instance var
										
						
						}
						
					}
					//if picked again 
				else {
					JOptionPane.showMessageDialog(null, 
							"Pick again.");
					break;
				}
	
				//going into next player display
//				else {
//					board[row][col].setText(""+player);
//					game.setBoard(row, col);
//				}
		
				}

		}
			winner = game.isWinner();
			//check for winner
				if (winner >0) {
				//display who won
				JOptionPane.showMessageDialog(null,"Player "+winner+" Wins!");
				continueGame();
				}
		}
		
				
		//if pressing on quit in the file menu
		if(quitItem == event.getSource()){
			//system exit
		System.exit(0);
		}
		
		//if pressing on new game in the file menu
		if(newGameItem == event.getSource()){
			//reset the game
			continueGame();
			
		}
		


}
}
}
