package campingsystem;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**********************************************************************
 * Creates a visual representation of the camp site based on date
 * parameter.

 * @version November 1 2017
 *********************************************************************/

@SuppressWarnings("serial")
public class SiteVisual extends JDialog implements ActionListener{
	
	private SiteModel siteList;
	private ButtonListener listener;
	
	private JPanel panel;
	private JPanel descriptionPanel;
	private JPanel legendPanel;
	
	private JLabel infoDisplay;
	private JLabel legendDisplay;
	
	private String date;
	
	private JButton[][] mapButtons;
	
	private boolean[] reserved;
	
	private RV rv;
	private Tent tent;
	
	private JFrame paOccupy;
	private JPanel classSelection;
	
	private final String[] CLASS_LIST = {"RV","Tent"};
	
	public SiteVisual(JFrame paOccupy, SiteModel listSites, String date) throws Exception {
		super(paOccupy);
		this.paOccupy = paOccupy;
		listener = new ButtonListener();
		this.date = date;
		siteList = listSites;
		
		reserved = siteList.getArrayReserves(date);

		panel = new JPanel();
		descriptionPanel = new JPanel();
		legendPanel = new JPanel();
		
		panel.setLayout(new GridLayout(8,11));
		
		mapButtons = new JButton[8][11];
		infoDisplay = new JLabel("Click on a lettered tile to learn more!"
				+ " Or, click on a cyan-colored site to reserve it!");
		legendDisplay = new JLabel("L = Lake  R = Rental  F = Forest "
				+ "S = Showers  S# = Site #  M = Main Office");
		
		descriptionPanel.add(infoDisplay);
		legendPanel.add(legendDisplay);
		
		for(int r = 0; r < 8; r++)
			for(int c = 0; c < 11; c++) {
				mapButtons[r][c] = new JButton("");
				mapButtons[r][c].addActionListener(listener);
				mapButtons[r][c].setBackground(Color.WHITE);
				panel.add(mapButtons[r][c]);
				mapLabeling(mapButtons[r][c], r, c);
			}

		
		panel.setVisible(true);
		descriptionPanel.setVisible(true);
		
		setLayout(new BorderLayout());
		add(legendPanel, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		add(descriptionPanel, BorderLayout.SOUTH);
		
	}
	
	private void mapLabeling(JButton button, int row, int col) {
		
		//Rental sites
		if(row == 0 && (col == 1 || col == 9)){
			button.setForeground(Color.WHITE);
			button.setBackground(Color.DARK_GRAY);
			button.setText("R");
		}
		//Lake Area
		if(((row <= 3) && (col >= 2) && (col <= 8))||
				(row == 4 && (col >= 3 && col <= 7))) {
			button.setForeground(Color.WHITE);
			button.setBackground(Color.BLUE);
			button.setText("L");
		}
		//Forest Areas
		if((col == 0) || (col == 10) || (col <= 3 && row >=6 && row <= 7) ||
				(col >= 7 && row >= 6 && row <= 7) || (row == 5 && col ==9) ||
				row == 5 && col == 1) {
			button.setForeground(Color.GRAY);
			button.setBackground(Color.GREEN);
			button.setText("F");
		}
		
		if(row == 7 && col == 5) {
			button.setForeground(Color.BLACK);
			button.setBackground(Color.GRAY);
			button.setText("M");
		}
		//Site 1
		if(row == 2 && col == 1) {
			if(reserved[0]) {
				button.setForeground(Color.WHITE);
				button.setBackground(Color.RED);
			}
			else {
				button.setForeground(Color.BLACK);
				button.setBackground(Color.CYAN);
			}
			button.setText("S1");
		}
		//Site 2
		if(row == 4 && col == 2) {
			if(reserved[1]) {
				button.setForeground(Color.WHITE);
				button.setBackground(Color.RED);
			}
			else {
				button.setForeground(Color.BLACK);
				button.setBackground(Color.CYAN);
			}
			button.setText("S2");
		}
		//Site 3
		if(row == 5 && col == 5) {
			if(reserved[2]) {
				button.setForeground(Color.WHITE);
				button.setBackground(Color.RED);
			}
			else {
				button.setForeground(Color.BLACK);
				button.setBackground(Color.CYAN);
			}
			button.setText("S3");
		}
		//Site 4
		if(row == 4 && col == 8) {
			if(reserved[3]) {
				button.setForeground(Color.WHITE);
				button.setBackground(Color.RED);
			}
			else {
				button.setForeground(Color.BLACK);
				button.setBackground(Color.CYAN);
			}
			button.setText("S4");
		}
		
		//Site 5
		if(row == 2 && col == 9) {
			if(reserved[4]) {
				button.setForeground(Color.WHITE);
				button.setBackground(Color.RED);
			}
			else {
				button.setForeground(Color.BLACK);
				button.setBackground(Color.CYAN);
			}
			button.setText("S5");
		}
	}
	private void dialogBoxes(int site) {
		classSelection = new JPanel();
		classSelection.setLayout(new GridLayout(2,1));
		
		
		JLabel label = new JLabel("Select Type of Reservation");
		JComboBox<String> selectionBox = new JComboBox<String>(CLASS_LIST);
		
		classSelection.add(label);
		classSelection.add(selectionBox);
		
		int result = JOptionPane.showConfirmDialog(null,
				classSelection, "", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION) {
			if(selectionBox.getSelectedItem().toString().equals("RV")) {
				rv = new RV();
				DialogCheckInRv rvCheckIn = new DialogCheckInRv(paOccupy,
						rv,siteList);
				rvCheckIn.setSiteDefault(site);
				rvCheckIn.setDateDefault(date);
				rvCheckIn.setModal(true);
				rvCheckIn.setSize(350, 250);
				rvCheckIn.setVisible(true);
				dispose();
			}
			
			if(selectionBox.getSelectedItem().toString().equals("Tent")) {
				tent = new Tent();
				DialogCheckInTent tentCheckIn = new DialogCheckInTent(paOccupy,
						tent, siteList);
				tentCheckIn.setSiteDefault(site);
				tentCheckIn.setDateDefault(date);
				tentCheckIn.setModal(true);
				tentCheckIn.setSize(350,250);
				tentCheckIn.setVisible(true);
				dispose();
			}
		}
	}
	
	private class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			for(int r = 0; r < 8; r++)
				for(int c = 0; c < 11; c++) {
					
					if(e.getSource() == mapButtons[r][c]) {
						
						if(((r <= 3) && (c >= 2) && (c <= 8))||
								(r == 4 && (c >= 3 && c <= 7))) 
							infoDisplay.setText("Warm, freshwater lake with "
									+ "a beach along the shoreline is fun for all!");
			
						if ((r == 0 && (c == 1 || c == 9)))
							infoDisplay.setText(
									"Visit either Rental Site to rent" + " all sorts of recreational equipment!");

						if ((c == 0) || (c == 10) || (c <= 3 && r >= 6 && r <= 7) || (c >= 7 && r >= 6 && r <= 7)
								|| (r == 5 && c == 9) || r == 5 && c == 1)
							infoDisplay.setText("Explore biking and hiking trails" + "around the campsite!");

						if (r == 7 && c == 5)
							infoDisplay.setText("Need to check in or check out? Got"
									+ " questions/issues? Come to the main office!");

						if (r == 2 && c == 1) {
							if (reserved[0]) {
								infoDisplay.setText("Site 1 is current taken for " + date + ".");
							} else {
								dialogBoxes(1);
							}
						}
						if (r == 4 && c == 2) {
							if (reserved[1]) {
								infoDisplay.setText("Site 2 is current taken for " + date + ".");
							} else {
								dialogBoxes(2);
							}

						}
						if (r == 5 && c == 5) {
							if (reserved[2]) {
								infoDisplay.setText("Site 3 is current taken for " + date + ".");
							} else {
								dialogBoxes(3);
							}

						}
						if (r == 4 && c == 8) {
							if (reserved[3]) {
								infoDisplay.setText("Site 4 is current taken for " + date + ".");
							} else {
								dialogBoxes(4);
							}

						}
						if (r == 2 && c == 9) {
							if (reserved[4]) {
								infoDisplay.setText("Site 5 is current taken for " + date + ".");
							} else {
								dialogBoxes(5);
							}

						}

					}

				}
			repaint();
			revalidate();
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}