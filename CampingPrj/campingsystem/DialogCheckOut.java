package campingsystem;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogCheckOut extends JDialog implements ActionListener {

	private JTextField checkOutDate;
	
	private JButton okButton;
	private JButton cancelButton;
	
	private JLabel description;
	
	private SiteModel siteList;
	
	private Site site;
	
	private JPanel panel;
	private JPanel panelButtons;
	
	private ButtonListener listener;

	private int spot;

	//private int staying;

	private GregorianCalendar newDate;

	private String person;
	
	public DialogCheckOut(JFrame occupy, Site site, SiteModel listSites, int is) {
		super(occupy);
		siteList = listSites;
		this.site = site;
		spot = is;
		
		panel = new JPanel();
		panelButtons = new JPanel();
		
		listener = new ButtonListener();
		person = siteList.getList().get(spot).getNameReserving();
		
		 //daysStaying = siteList.getValueAt(spot, 2);
		//siteList.get;

		  newDate = siteList.getList().get(spot).getCheckIn();
		  
		  
		  int staying = siteList.getList().get(spot).getDaysStaying();
		  description = new JLabel(" You are reserved for " + staying + " days. " + "Checking Out For "+ person + " On (Date)");
//		  siteList.setSiteAvailability(true);
		  
		  //TODO figure out how to show the checkout date in the text
		  //without changing the getValueAt() method
		 //newDate.add(Calendar.DAY_OF_YEAR, staying);
		 
		 
		//newDate.add((int)newDate.getTimeInMillis(), 3/**this.site.getDaysStaying()*365*60*60*1000**/); 
		  SimpleDateFormat formatCal = new SimpleDateFormat("M/d/yyyy");
		checkOutDate = new JTextField();
		checkOutDate.setText(formatCal.format(newDate.getTime()));
		//set the box size
		checkOutDate.setPreferredSize(new Dimension(50, 82));
		
		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");
		
		panel.add(description);
		panel.add(checkOutDate);
		
		panelButtons.add(okButton);
		panelButtons.add(cancelButton);
		
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
		
		
		
		
		panel.setLayout(new GridLayout(2, 0));
		panelButtons.setLayout(new GridLayout(1, 2));
		setLayout(new BorderLayout());
		
		this.add(panel, BorderLayout.NORTH);
		this.add(panelButtons, BorderLayout.SOUTH);
		
		
		panel.setVisible(true);

		occupy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == okButton) {				
				//int days = Integer.parseInt(checkOutDate.getText());
				String days = checkOutDate.getText();
				GregorianCalendar convert = new GregorianCalendar();
				SimpleDateFormat formatCal = new SimpleDateFormat("M/d/yyyy");
				try {
					Date date = formatCal.parse(days);
					convert.setTime(date);
					int trackDays = siteList.convertToDays(convert);
					int currentDays = siteList.convertToDays(newDate);
					
					
					//if after or on the assigned checkout date
					 if(trackDays >= currentDays) {
						trackDays -= currentDays;
						site.setLateFee(trackDays);
						//int[] row= new int[spot];
						//siteList.dayLeaving(spot);
						siteList.setCheckOut(true);
						dispose();
					}
					
					 //for wrong check in
					 else if(siteList.getCheckOut()==false) {
						JOptionPane.showMessageDialog
						(null, "Can not enter a date before check in");
						
				}
										
					

				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, 
							"Use correct format, MM/dd/yyyy");
				}
			}
			
			else {
			dispose();
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
