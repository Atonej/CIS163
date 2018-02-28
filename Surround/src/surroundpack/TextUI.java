package surroundpack;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class TextUI {
	/** the game logic */
    private SurroundGame game;
    private int[][] grid;
    private int CELL_WIDTH = 3;
    private int ROWS = 10;
    private int COLS = 10;
    private int player = 1;
    private String NUM_FORMAT, BLANK_FORMAT;
    private Scanner inp;
	private Scanner sc;

    public static void main(String[] arg) {
        TextUI t = new TextUI();
        t.playLoop();
    }
    
    public TextUI() {
        game = new SurroundGame(player);

     if (game == null) {
System.err.println ("*--------------------------------------------*");
System.err.println ("| You must first modify the UI program.      |");
System.err.println ("| Look for the first TODO item in TextUI.java|");
System.err.println ("*--------------------------------------------*");
      System.exit(0xE0);
        }
        grid = new int[ROWS][COLS];

        /* Set the string to %4d */
        NUM_FORMAT = String.format("%%%dd", CELL_WIDTH + 1);

        /* Set the string to %4s, but without using String.format() */
        BLANK_FORMAT = "%" + (CELL_WIDTH + 1) + "s";
        inp = new Scanner(System.in);
    }

    private void renderBoard() {
        
        // reset all the 2D array elements to ZERO
        for (int k = 0; k < grid.length; k++)
            for (int m = 0; m < grid[k].length; m++)
                grid[k][m] = 0;
                

        // print the 2D array using dots for a blank space
        System.out.println();
				
        for (int k = 0; k < grid.length; k++) {
            for (int m = 0; m < grid[k].length; m++)
                if (grid[k][m] == 0)
                    System.out.printf (BLANK_FORMAT, ".");
                else
                    System.out.printf (NUM_FORMAT, grid[k][m]);
            System.out.println();
        }
    }

    /*********************************************
     * The main loop for playing a SINGLE game. Notice
     * this method contains NO GAME LOGIC! Its only 
     * task is to accept user input and invoke the 
     * appropriate methods in the game engine.
     ********************************************/
    public void playLoop() {
        char choice;
        
        // start the game
     //   game.resizeBoard(ROWS, COLS, WIN);
       // game.placeRandomValue();
        //game.placeRandomValue();
        renderBoard();

        // continue until quit or game over
        do{
            // prompt player for choice
        	sc = new Scanner(System.in);
            System.out.print ("Enter the row and then column ");
            //choice = inp.next().trim().toUpperCase().charAt(0);
            
            
             ROWS = sc.nextInt();
             COLS = sc.nextInt();
            if (game.select(ROWS, COLS)){
				//when not going into next player
				if (player >=0 ){
					grid[ROWS][COLS]=player;
					//System.out.print(grid[i][j]);
					game.setBoard(ROWS, COLS);
					
//	game.board[row][col]= null; //tried to make a protected instance var
								
				player = game.nextPlayer();
				}
				
			}
			//if picked again 
		else {
			JOptionPane.showMessageDialog(null, 
					"Pick again.");
			break;
		}
            System.out.print ("ROW: "+ ROWS + "COLUMN:" + COLS);
            

            renderBoard();
 }while (game.isWinner() == -1);
        System.out.println("Game Over Player "+ game.isWinner()+ " WINS");
    }


}
