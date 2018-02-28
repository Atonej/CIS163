package surroundpack2;

import java.awt.*; import java.awt.event.*;
import java.util.Random;

import javax.swing.*; import java.awt.Color;

/*****************************************************************
A program that creates a game where players attempt to surround
each other. The first to surround an opposing player wins!

@author Tiesha Anderson
@version October 2017
CIS 163-03
 *****************************************************************/
public class SurroundPanel extends JPanel {

	private JButton[][] board;
	private SurroundGame game;
	public String difficulty; 
	private String totalPlayers; 
	private String starter; 
	private int BDSIZE = 10; //starts with a 10 X 10 board
	private ButtonListener buttonListener;
	private int winner;
	private int player;
	private String message = "Input can only contain digits 0-9.";
	private String message2 = "Input can only contain integers 1-";


	/*****************************************************************
	Default constructor accepts input from user to define SurroundGame 
	parameters. 
	 *****************************************************************/
	public SurroundPanel() {

		userInputDifficulty();
		userInputPlayers();
		userInputStarter();

		if (Integer.parseInt(difficulty) > 0)			
			BDSIZE = Integer.parseInt(difficulty);

		setLayout(new GridLayout(BDSIZE,BDSIZE));


		game = new SurroundGame(Integer.parseInt(difficulty),
				Integer.parseInt(totalPlayers),
				Integer.parseInt(starter));

		buttonListener = new ButtonListener();
		board = new JButton[BDSIZE][BDSIZE];

		for (int row = 0; row < BDSIZE; row++) 
			for (int col = 0; col < BDSIZE; col++) {
				board[row][col] = new JButton(" ");
				board[row][col].addActionListener(buttonListener);
				add(board[row][col]); 			
			}		
	}


	private class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{			
			for (int row = 0; row < BDSIZE; row++) 
				for (int col = 0; col < BDSIZE; col++)			
					if (board [row][col] == event.getSource()) { 
						Cell c = game.getCell(row, col);
						if (c != null && c.getPlayerNumber() == -1) {	
							board[row][col].setText(""+game.getPlayerNumber());
						}
						else if (c.getPlayerNumber() > -1)
							break;
						else
							board[row][col].setText("");
					}			

			for (int row = 0; row < BDSIZE; row++) 
				for (int col = 0; col < BDSIZE; col++)			
					if (board [row][col] == event.getSource()) 
						if (game.select(row, col)== true) 
							player = game.nextPlayer();
						else 
							JOptionPane.showMessageDialog(null,"Pick again.");

			//Calls Surround Game method to check for winner.			
			winner = game.isWinner();
			if (winner != -1) {
				JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");				
				updateDisplay();
			}			
		}
	}


	/**
	 * clears board for panel and board for game.
	 */
	private void updateDisplay() {
		for (int row = 0; row < BDSIZE; row++) 
			for (int col = 0; col < BDSIZE; col++) {
				board[row][col].setText(" ");				
				game.reset();
			}
	}


	/**
	 * Asks for user input then calls validation method
	 */
	private void userInputDifficulty() {
		difficulty = "";
		difficulty = JOptionPane.showInputDialog("Please provide a board size"
				+ " between 3 and 20. Surround will default to 10.");
		if(difficulty == null)
			System.exit(0);

		parseInputDifficulty();	
	}


	/**
	 * Asks for user input then calls validation method
	 */
	private void userInputPlayers() {
		totalPlayers = "";
		totalPlayers = JOptionPane.showInputDialog("Number of players:");
		if (totalPlayers == null)
			userInputDifficulty();
		if(totalPlayers != null)
			parseInputPlayers();
	}


	/**
	 * Asks for user input then calls validation method
	 */
	private void userInputStarter() {		
		starter = JOptionPane.showInputDialog("Starting player:");
		
		/**
		 * Choosing cancel allows user to change the total players
		 * and redefine the starting player.
		 */
		if(starter == null) {
			userInputPlayers();
			userInputStarter();
		}

		if (Integer.parseInt(starter) > Integer.parseInt(totalPlayers)) {
			JOptionPane.showMessageDialog(null, "There are only " + totalPlayers + " players. " + 
					"How can player " + starter + " be first? We'll choose for you.");
			
			Random rn = new Random();
			int n = Integer.parseInt(totalPlayers) - 1 + 1;
			int i = rn.nextInt() % n;
			starter =  String.valueOf(1 + i);
			if (Integer.parseInt(starter) < 0) {
				starter = "1";
			}
				
		}
		else if (Integer.parseInt(starter) 	< 1) {
			JOptionPane.showMessageDialog(null, message2 + totalPlayers + " First player will start.");
			starter = "1";
		}

		
		if(starter != null)
			parseInputStarter();

	}


	/**
	 * Validates user input for board size.
	 * Will show error message to user
	 * and allow them to reset value.
	 * @throws NumberFormatException
	 */
	private void parseInputDifficulty() {
		try 
		{
			if (Integer.parseInt(difficulty) > 20 || Integer.parseInt(difficulty) < 3) {
				JOptionPane.showMessageDialog(null, "That's way too small! Let's start with 10.");
				difficulty = "10";
			}
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, message);
			userInputDifficulty();
		}
	}


	/**
	 * Validates user input for total players
	 * Will show error message to user
	 * and allow them to reset value.
	 * @throws NumberFormatException
	 */
	private void parseInputPlayers() {
		try 
		{
			if(Integer.parseInt(totalPlayers) < 2) {
				JOptionPane.showMessageDialog(null, "This is a multi-player game. Let's start with 2 players.");
				totalPlayers = "2";
			}
			Integer.parseInt(totalPlayers);
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, message);
			userInputPlayers();
		}
	}


	/**
	 * Validates user input for start player
	 * Will show error message to user
	 * and allow them to reset value.
	 * @throws NumberFormatException
	 */
	private void parseInputStarter() {
		try 
		{
			Integer.parseInt(starter);			
		}
		catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, message);
			userInputStarter();
		}
	}
}
