package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import project2.Cell;
import project2.GameStatus;
import project2.NumberGame;
import project2.NumberSlider;
import project2.SlideDirection;


/***********************************************************************
 * GUI class for implementation GUI for 1024/2048 game
 *
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class GraphicalUI extends JFrame implements MouseListener, 
KeyListener {

	/** for loading file*/
	private static final long serialVersionUID = 1L;

	/** size of tile */
	public static final int TILE_SIZE = 100;
	
	/** gap size */
	public static final int GAP = 10;
	
	/** set winning score */
	public static int WINNING_SCORE = 64;
	
	/** initial grid width */
	public static int WIDTH = 4;
	
	/** initial grid height */
	public static int HEIGHT = 4;

	/** Number game stores the logic of game */
	private NumberSlider game;
	
	/**grid 2D array*/
	private int[][] grid;

	/** Displays board according to grid */
	private BoardPanel board;

	/** Used to detect mouse position while dragging events in y dir.*/
	private float lastY = 0;
	
	/** Used to detect mouse position while dragging events in x dir.*/
	private float lastX = 0;
	
	/** on screen buttons to play game */
	private JButton undo, left, right, up , down;
	
	/** game stats*/
	private JLabel score, highsc, title, gcount, slide;
		
	/** menu items for the file */
	private JMenuItem quit, restart, resize;
	
	/**drop down menu under file*/
	private JMenu file;
	
	/**image instead of a button*/
	private ImageIcon image;
	
	/**keep up with score, high score, slides and games*/
	private int hscnum=0 ,scnum=0, snum=0, gnum=0;
/***********************************************************************
 * Method set the basic graphics to panel
***********************************************************************/
	public GraphicalUI() {
		game = new NumberGame();
		board = new BoardPanel();
		//call all methods to get Jframe setup
		initFile();
		initGrid();
		GameStats();
		initQuitMenuItem();
		initRestartMenuItem();
		initUndoButton();
		initLeftButton();
		initRightButton();
		initUpButton();
		initDownButton();
		//initResizeMenuItem();
		
		//basis of gui
		setTitle("Number Game: 1024/2048");
		setLayout(new BorderLayout(GAP, GAP));
		setBackground(Colors.FRAME_BACKGROUND);
		
		//add to menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(file);
		
		//add to panel
		JPanel jPanel = new JPanel();
		jPanel.add(undo);
		jPanel.add(left);
		jPanel.add(right);
		jPanel.add(up);
		jPanel.add(down);
		
		//second panel added
		JPanel stats = new JPanel();
		stats.add(title);
		stats.add(score);
		stats.add(highsc);
		stats.add(slide);
		stats.add(gcount);
		
		
		//send to a location 
		add(board, BorderLayout.CENTER);
		add(jPanel, BorderLayout.EAST);
		add(menuBar, BorderLayout.NORTH);
		add(stats, BorderLayout.WEST);

		addMouseListener(this);
		addKeyListener(this);
		initGrid();
	}

/***********************************************************************
* Method to manipulate other private methods depending on chosen options
***********************************************************************/
	private void initFile(){
		//file options
		file = new JMenu("File");
		
		resize = new JMenuItem("Resize");
		file.add(resize);
		initResizeMenuItem();

		restart = new JMenuItem("Restart");
		file.add(restart);
		initRestartMenuItem();
		
		quit = new JMenuItem("Quit");
		file.add(quit);
		initQuitMenuItem();
	}

/***********************************************************************
 * Method to initialize resize button and handle click event
***********************************************************************/
	private void initResizeMenuItem() {
		JFrame _this = this;
		resize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int width = 4, height = 4, winningScore = 64;

				// Read width
				while (true) {
					String w = (String) JOptionPane.showInputDialog
							(_this,
							"Enter width: ", "Resize 2048 Board",
							JOptionPane.PLAIN_MESSAGE, null, null, "4");
					if (w == null) {
						_this.requestFocus();
						return;
					}
					try {
						width = Integer.parseInt(w);
						break;
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(_this,
								"Width should be integer.");
					}
				}

				// Read height
				while (true) {
					String h = (String) JOptionPane.showInputDialog
							(_this,
							"Enter height: ", "Resize 2048 Board",
							JOptionPane.PLAIN_MESSAGE, null, null, "4");
					if (h == null) {
						_this.requestFocus();
						return;
					}
					try {
						height = Integer.parseInt(h);
						break;
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(_this,
								"Height should be integer.");
					}
				}

				// Read winning sore
				while (true) {
					String h = (String) JOptionPane.showInputDialog
							(_this,
							"Enter winning score: ", 
							"Resize 2048 Board",
							JOptionPane.PLAIN_MESSAGE, null, null, 
							"2048");
					if (h == null) {
						_this.requestFocus();
						return;
					}
					try {
						winningScore = Integer.parseInt(h);
						if (countSetBits(winningScore) != 1) {
							JOptionPane.showMessageDialog(_this,
								"Winning Score should be power of 2.");
							continue;
						}
						break;
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(_this,
								"Winning Score should be integer.");
					}
				}

				WIDTH = width;
				HEIGHT = height;
				WINNING_SCORE = winningScore;
				initGrid();
				_this.pack();
			}

			// keep up with bit count
			private int countSetBits(int number) {
				int count = 0;
				while (number > 0) {
					++count;
					number &= number - 1;
				}
				return count;
			}
		});
	}


/***********************************************************************
* Method to initialize restart button and handle click event
***********************************************************************/
	private void initRestartMenuItem() {
		JFrame _this = this;
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.reset();
				updateGrid();//keep updating grid
				_this.requestFocus();
				snum=0;
				slide.setText("Slides: " + snum );
			}
		});
	}

/***********************************************************************
* Method to initialize quit button and handle click event
***********************************************************************/
	private void initQuitMenuItem() {
		JFrame _this = this;
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// set close event to frame
				_this.dispatchEvent(new WindowEvent(_this,
						WindowEvent.WINDOW_CLOSING));
			}
		});
	}

/***********************************************************************
* This method resizes board and initialized the game and grid
***********************************************************************/
	public void initGrid() {
		game.resizeBoard(WIDTH, HEIGHT, WINNING_SCORE);
		game.placeRandomValue();
		game.placeRandomValue();
		grid = new int[WIDTH][HEIGHT];
		board.setGrid(grid);
		updateGrid();

		setFocusable(true);
		//Requests that this Component get the input focus, 
		//and that this Component's top-level ancestor 
		//become the focused Window
		this.requestFocus();
	}
	
/***********************************************************************
 * Method to initialize undo button and handle click event of it
***********************************************************************/
	private void initUndoButton() {
		JFrame _this = this;
        try
        {	//use an url for the undo button
            image = new ImageIcon(ImageIO.read(
                    new URL("http://www.thedigitaldeacon.com"
                    		+ "/wp-content/uploads/2014/"
                    		+ "06/UndoButton-300x300.jpg")));
        }
        catch(MalformedURLException mue)
        {	//error of form to frame
            mue.printStackTrace();
        }
        catch(IOException ioe)
        {	//can't print
            ioe.printStackTrace();
        }
		undo = new JButton(image);
		
		
       

		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					game.undo();
					
					updateGrid();
					_this.requestFocus();
					snum--; //when undo take away slides
					slide.setText("Slides: " + snum );
				} catch (IllegalStateException e1) {
					JOptionPane.showMessageDialog(_this,
							"Cannot undo any further!");
				}

				finally{
					_this.requestFocus();
				}
			}
		});
		
	}
	
/***********************************************************************
 * Method to initialize undo button and handle click event of it
***********************************************************************/
		private void initLeftButton() {
			JFrame _this = this;
	   
			left = new JButton("Left");	       
			left.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					game.slide(SlideDirection.LEFT);
					updateGrid();
					
					//Requests that this Component get the input focus, 
					//and that this Component's top-level ancestor 
					//become the focused Window
					_this.requestFocus();
					snum++;
					slide.setText("Slides: " + snum );
		}
			});
			
		}
		
/***********************************************************************
* Method to initialize undo button and handle click event of it
***********************************************************************/
				private void initRightButton() {
					JFrame _this = this;
			   
					right = new JButton("Right");	       
					right.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						game.slide(SlideDirection.RIGHT);
							updateGrid();
					//Requests that this Component get the input focus, 
					//and that this Component's top-level ancestor 
					//become the focused Window
							_this.requestFocus();
							snum++;
							slide.setText("Slides: " + snum );
				}
					});
				
				}
				

/***********************************************************************
* Method to initialize undo button and handle click event of it
***********************************************************************/
				private void initUpButton() {
					JFrame _this = this;
			   
					up = new JButton("Up");	       
					up.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
						game.slide(SlideDirection.UP);
							updateGrid();
					//Requests that this Component get the input focus, 
					//and that this Component's top-level ancestor 
					//become the focused Window
							_this.requestFocus();
							snum++;
							slide.setText("Slides: " + snum );
				}
					});
				}
				

/***********************************************************************
* Method to initialize undo button and handle click event of it
***********************************************************************/
				private void initDownButton() {
					JFrame _this = this;
			   
					down = new JButton("Down");	       
					down.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
						game.slide(SlideDirection.DOWN);
							updateGrid();
					//Requests that this Component get the input focus, 
					//and that this Component's top-level ancestor 
					//become the focused Window
							_this.requestFocus();
							snum++;
							slide.setText("Slides: " + snum );
				}
					});
				
				}
				

/***********************************************************************
* Method to initialize undo button and handle click event of it
***********************************************************************/
				private void GameStats() {

//					results = new JLabel[5][1];
					
					title = new JLabel();
					this.title.setText("Game Stats ||");
					scnum = game.getScore();
					this.score = new JLabel();
					this.score.setText("Score: "+ scnum);
					if(gnum == 0)
					hscnum = scnum;
						if(hscnum< scnum)
							hscnum = scnum;
					this.highsc = new JLabel();
					this.highsc.setText("High Score: " + hscnum );
					this.slide = new JLabel();
					this.slide.setText("Slides: " + snum );
					this.gcount = new JLabel();
					this.gcount.setText("Games Played: " + gnum );
				}
/***********************************************************************
* This method updates the grid and repaints the board
***********************************************************************/
	public void updateGrid() {
		
		for (int k = 0; k < grid.length; k++)
			for (int m = 0; m < grid[k].length; m++)
				grid[k][m] = 0;

		// fill in the 2D array using information for non-empty tiles 
		for (Cell c : game.getNonEmptyTiles())
			grid[c.getRow()][c.getCol()] = c.getValue();

		board.repaint();
		if (game.getStatus() != GameStatus.IN_PROGRESS) {
			int reply = 0;
			if (game.getStatus() == GameStatus.USER_LOST) {
				reply = JOptionPane
						.showConfirmDialog(this,
			"Sorry you lost the game. Want to play the game again?");
			} else if (game.getStatus() == GameStatus.USER_WON) {
				reply = JOptionPane
						.showConfirmDialog(this,
		  "Congrats!! you won the game. Want to play the game again?");
			}
			if (reply == JOptionPane.YES_OPTION) {
				gnum++;
				this.gcount.setText("Games Played: " + gnum );
				snum=0;
				this.slide.setText("Slides: " + snum );
				
				game.reset();
				updateGrid();
			} else if (reply == JOptionPane.NO_OPTION) {
				gnum=0;
				this.gcount.setText("Games Played: " + gnum );
				snum=0;
				this.slide.setText("Slides: " + snum );
				this.dispatchEvent(new WindowEvent(this,
						WindowEvent.WINDOW_CLOSING));
			}
			

		}
	}

/***********************************************************************
* Main method to run java application
***********************************************************************/
	public static void main(String[] args) {
		final JFrame frame = new GraphicalUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
//run the application
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() { //make visible
				frame.setVisible(true);
			}
		});
	}

/***********************************************************************
* method to react to key being typed
***********************************************************************/
	@Override
	public void keyTyped(KeyEvent e) {

	}

/***********************************************************************
* method to react to key being pressed
s***********************************************************************/
	@Override
	public void keyPressed(KeyEvent e) {

	}

/***********************************************************************
* method to react to key being released and choose direction
* @param KeyEvent directions
***********************************************************************/
	@Override
	public void keyReleased(KeyEvent e) {
		
		if (game.getStatus() != GameStatus.IN_PROGRESS) //return status
			return;
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_KP_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A
				) {
			snum++;	//increment slide 
			this.slide.setText("Slides: " + snum );
			scnum = game.getScore(); //update score
			this.score.setText("Score: "+ scnum);
			if(gnum == 0)
			hscnum = scnum;
				if(hscnum< scnum)
					hscnum = scnum;
			this.highsc.setText("High Score: " + hscnum );
			
			game.slide(SlideDirection.LEFT);
			updateGrid();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_KP_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_S) {
			snum++;
			this.slide.setText("Slides: " + snum );
			scnum = game.getScore();
			this.score.setText("Score: "+ scnum);
			if(gnum == 0)
			hscnum = scnum;
				if(hscnum< scnum)
					hscnum = scnum;
			this.highsc.setText("High Score: " + hscnum );
			game.slide(SlideDirection.RIGHT);
			updateGrid();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_KP_DOWN
				|| e.getKeyCode() == KeyEvent.VK_Z) {
			snum++;
			this.slide.setText("Slides: " + snum );
			scnum = game.getScore();
			this.score.setText("Score: "+ scnum);
			if(gnum == 0)
			hscnum = scnum;
				if(hscnum< scnum)
					hscnum = scnum;
			this.highsc.setText("High Score: " + hscnum );
			game.slide(SlideDirection.DOWN);
			updateGrid();
		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_KP_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
			snum++;
			this.slide.setText("Slides: " + snum );
			scnum = game.getScore();
			this.score.setText("Score: "+ scnum);
			if(gnum == 0)
			hscnum = scnum;
				if(hscnum< scnum)
					hscnum = scnum;
			this.highsc.setText("High Score: " + hscnum );
			game.slide(SlideDirection.UP);
			updateGrid();
		}

	}

/***********************************************************************
* Handle mouse drag when mouse clicked
***********************************************************************/
	@Override
	public void mouseClicked(MouseEvent e) {

	}

/***********************************************************************
* Handle mouse drag when mouse pressed this way can press buttons
***********************************************************************/
	@Override
	public void mousePressed(MouseEvent e) {
		lastY = e.getY();
		lastX = e.getX();
	}
/***********************************************************************
* Handle mouse drag when mouse released this way can press buttons
***********************************************************************/
	@Override
	public void mouseReleased(MouseEvent e) {
		if (game.getStatus() != GameStatus.IN_PROGRESS)
			return;

		float dx = Math.abs(e.getX() - lastX);
		float dy = Math.abs(e.getY() - lastY);

		if (dx >= dy) {
			if (e.getX() < lastX) {
				game.slide(SlideDirection.LEFT);
				updateGrid();
			} else if (e.getX() > lastX) {
				game.slide(SlideDirection.RIGHT);
				updateGrid();
			}
		} else {
			if (e.getY() < lastY) {
				game.slide(SlideDirection.UP);
				updateGrid();
			} else if (e.getY() > lastY) {
				game.slide(SlideDirection.DOWN);
				updateGrid();
			}
		}

	}

/***********************************************************************
* Handle mouse drag when mouse enters something
***********************************************************************/
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	
/***********************************************************************
* Handle mouse drag when mouse exits application
***********************************************************************/
	@Override
	public void mouseExited(MouseEvent e) {

	}
}
