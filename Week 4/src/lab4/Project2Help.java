package lab4;

	import java.awt.*; import java.awt.event.*; import javax.swing.*;

	public class Project2Help extends JPanel
	{
		private JLabel[][] labels;
		private JPanel panelUpper;
		private JPanel panelLower;

		private JButton resize;

		private JTextField textRow;
		private JTextField textCol;
		private JLabel lblCount;

		private ButtonListener butListener;

		public static void main (String[] args)
		{
			JFrame frame = new JFrame ("Lab 4 example");
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			Project2Help panel = new Project2Help();
			frame.getContentPane().add(panel);
			frame.setSize(600,400);
			frame.setVisible(true);
		}


		public Project2Help()
		{
			panelUpper = new JPanel();
			panelLower = new JPanel();

			panelUpper.setLayout(new GridLayout(10,10));
			panelLower.setLayout(new GridLayout(4,1));

			labels = new JLabel[10][10];
			butListener = new ButtonListener();
			
			for (int r = 0; r < 10; r++)
				for (int c = 0; c < 10; c++) {
					labels[r][c] = new JLabel(r + "," +c);
					panelUpper.add (labels[r][c]);
				}
					
			resize = new JButton ("Reset");
			resize.addActionListener(butListener);
			textRow = new JTextField ("10");
			textCol = new JTextField ("10");
			lblCount = new JLabel ("0");
			
			panelLower.add(resize);
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
	
	for (int r = 0; r < row; r++)
		for(int c=0; c <col; c++){
			labels[r][c] = new JLabel(r + ","+ c);
			panelUpper.add(labels[r][c]);
			
		}

	int count = Integer.parseInt(lblCount.getText());
	count++;
	lblCount.setText(""+count);
					panelUpper.revalidate();
					repaint();
				}
			}
		}
	}


