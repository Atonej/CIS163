package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project2.Cell;
import project2.NumberGame;

public class Project3HelpRevalidate extends JPanel
{
	private JLabel[][] labels;
	private JPanel panelUpper;
	private JPanel panelLower;

	private JButton resize;
	private JButton left;
	private JButton right;
	private JButton up;
	private JButton down;
	private JTextField textRow;
	private JTextField textCol;
	private JLabel lblCount;

	private ButtonListener butListener;
	
	
	private NumberGame game;

	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Lab 6 example");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		Project3HelpRevalidate panel = new Project3HelpRevalidate();
		frame.getContentPane().add(panel);
		frame.setSize(600,400);
		frame.setVisible(true);
	}

	public Project3HelpRevalidate()
	{
		panelUpper = new JPanel();
		panelLower = new JPanel();

		panelUpper.setLayout(new GridLayout(10,10));
		panelLower.setLayout(new GridLayout(5,5));

		labels = new JLabel[10][10];
		
		butListener = new ButtonListener();
		
		for (int r = 0; r < 10; r++) 
			for (int c = 0; c < 10; c++) {
				labels[r][c] = new JLabel(r + "," +c);
				panelUpper.add (labels[r][c]);
			}
		
		game = new NumberGame();
		game.resizeBoard (10, 10, 16);
		game.reset();
		
       for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
                labels[r][c].setText("0");
        
        for (Cell c : game.getNonEmptyTiles())
            labels[c.getRow()][c.getCol()].setText(""+ c.getValue());
		
		resize = new JButton ("Reset");
		left = new JButton("LEFT");
		up = new JButton("UP");
		down= new JButton("DOWN");
		right = new JButton("RIGHT");
		resize.addActionListener(butListener);
		textRow = new JTextField ("10");
		textCol = new JTextField ("10");
		lblCount = new JLabel ("0");
		
		panelLower.add(resize);
		panelLower.add(left);
		panelLower.add(up);
		panelLower.add(down);
		panelLower.add(right);
		panelLower.add(textRow);
		panelLower.add(textCol);
		panelLower.add(lblCount);

		
		

		setLayout (new BorderLayout());
		add (panelUpper, BorderLayout.NORTH);
		add (panelLower, BorderLayout.SOUTH);
	}

	private class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event)
		{

			if (resize == event.getSource()) {
				panelUpper.removeAll();
				int row = Integer.parseInt(textRow.getText());
				int col = Integer.parseInt(textCol.getText());

				panelUpper.setLayout(new GridLayout(row,col));
				labels = new JLabel[row][col];

				for (int r = 0; r < row; r++) 
					for (int c = 0; c < col; c++) {
						labels[r][c] = new JLabel(r + " " +c);
						panelUpper.add (labels[r][c]);
					}
				
				game = new NumberGame();
				game.resizeBoard (row, col, 16);
				game.reset();
				
			       for (int r = 0; r < row; r++)
			            for (int c = 0; c < col; c++)
			                labels[r][c].setText("0");
			        for (Cell c : game.getNonEmptyTiles())
			            labels[c.getRow()][c.getCol()].setText(""+ c.getValue());

				int count = Integer.parseInt(lblCount.getText());
				count++;
				lblCount.setText(""+count);

				panelUpper.revalidate();
				repaint();
			}
		}
	}
}

