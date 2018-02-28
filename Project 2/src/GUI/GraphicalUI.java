package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Proj2.Cell;
import Proj2.GameStatus;
import Proj2.NumberGame;
import Proj2.NumberSlider;
import Proj2.SlideDirection;

/************************************************************************
 * GUI class for implementation GUI for 2048 game
 *
 * @author Atone Joryman
 * @version Winter 2017
 ***********************************************************************/
public class GraphicalUI extends JFrame implements MouseListener, KeyListener {

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
	private int[][] grid;

	/** Displays board according to grid */
	private BoardPanel board;

	/** Used to detect mouse position while dragging events in the y dir. */
	private float lastY = 0;
	/** Used to detect mouse position while dragging events in the x dir. */
	private float lastX = 0;
	/** Quit button */
	private JButton quit;
	/** Restart button */
	private JButton restart;
	/** Undo button */
	private JButton undo;
	/** Resize button */
	private JButton resize;

	/************************************************************
	 * Method set the basic graphics to panel
	 ***********************************************************/
	public GraphicalUI() {
		game = new NumberGame();
		board = new BoardPanel();

		initGrid();
		initQuitButton();
		initRestartButton();
		initUndoButton();
		initResizeButton();

		setTitle("2048");
		setLayout(new BorderLayout(GAP, GAP));
		setBackground(Colors.FRAME_BACKGROUND);

		JPanel jPanel = new JPanel();
		jPanel.add(undo);
		jPanel.add(quit);
		jPanel.add(restart);
		jPanel.add(resize);

		add(board, BorderLayout.CENTER);
		add(jPanel, BorderLayout.NORTH);

		addMouseListener(this);
		addKeyListener(this);
		initGrid();
	}

	/************************************************************
	 * Method to initialize resize button and handle click event
	 ***********************************************************/
	private void initResizeButton() {
		JFrame _this = this;
		resize = new JButton("RESIZE");
		resize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int width = 4, height = 4, winningScore = 64;

				// Read width
				while (true) {
					String w = (String) JOptionPane.showInputDialog(_this,
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
					String h = (String) JOptionPane.showInputDialog(_this,
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
					String h = (String) JOptionPane.showInputDialog(_this,
							"Enter winning score: ", "Resize 2048 Board",
							JOptionPane.PLAIN_MESSAGE, null, null, "2048");
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

	/***************************************************************
	 * Method to initialize undo button and handle click event of it
	 **************************************************************/
	private void initUndoButton() {
		JFrame _this = this;
		undo = new JButton("UNDO");
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					game.undo();
					updateGrid();
					_this.requestFocus();
				} catch (IllegalStateException e1) {
				}

			}
		});
	}

	/************************************************************
	 * Method to initialize restart button and handle click event
	 ***********************************************************/
	private void initRestartButton() {
		JFrame _this = this;
		restart = new JButton("RESTART");
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.reset();
				updateGrid();
				_this.requestFocus();
			}
		});
	}

	/*********************************************************
	 * Method to initialize quit button and handle click event
	 ********************************************************/
	private void initQuitButton() {
		JFrame _this = this;
		quit = new JButton("QUIT");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// set close event to frame
				_this.dispatchEvent(new WindowEvent(_this,
						WindowEvent.WINDOW_CLOSING));
			}
		});
	}

	/*************************************************************
	 * This method resizes board and initialized the game and grid
	 ************************************************************/
	public void initGrid() {
		game.resizeBoard(WIDTH, HEIGHT, WINNING_SCORE);
		game.placeRandomValue();
		game.placeRandomValue();
		grid = new int[WIDTH][HEIGHT];
		board.setGrid(grid);
		updateGrid();

		setFocusable(true);
		this.requestFocus();
	}

	/*****************************************************
	 * This method updates the grid and repaints the board
	 ****************************************************/
	public void updateGrid() {
		for (int k = 0; k < grid.length; k++)
			for (int m = 0; m < grid[k].length; m++)
				grid[k][m] = 0;

		/* fill in the 2D array using information for non-empty tiles */
		for (Cell c : game.getNonEmptyTiles())
			grid[c.row][c.column] = c.value;

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
				game.reset();
				updateGrid();
			} else {
				this.dispatchEvent(new WindowEvent(this,
						WindowEvent.WINDOW_CLOSING));
			}
		}
	}

	/*************************************************
	 * Main method to test if is runnable
	 ************************************************/
	public static void main(String[] args) {
		final JFrame frame = new GraphicalUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame.setVisible(true);
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	/*********************************************
	 * Handle keyboard events
	 ********************************************/
	@Override
	public void keyReleased(KeyEvent e) {
		if (game.getStatus() != GameStatus.IN_PROGRESS)
			return;
		if (e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_KP_LEFT
				|| e.getKeyCode() == KeyEvent.VK_A) {
			game.slide(SlideDirection.LEFT);
			updateGrid();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_KP_RIGHT
				|| e.getKeyCode() == KeyEvent.VK_S) {
			game.slide(SlideDirection.RIGHT);
			updateGrid();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN
				|| e.getKeyCode() == KeyEvent.VK_KP_DOWN
				|| e.getKeyCode() == KeyEvent.VK_Z) {
			game.slide(SlideDirection.DOWN);
			updateGrid();
		} else if (e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_KP_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {
			game.slide(SlideDirection.UP);
			updateGrid();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/************************************************************
	 * Handle mouse drag via mousePressed and mouseReleased
	 ***********************************************************/
	@Override
	public void mousePressed(MouseEvent e) {
		lastY = e.getY();
		lastX = e.getX();
	}

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

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
