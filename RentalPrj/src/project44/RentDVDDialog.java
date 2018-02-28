package project44;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**********************************************************************
 * dialog box for renting dvd or blue ray, user must close box before
 * accessing the gui again
 * @author Gregory
 * @version 7/26/2017
 *********************************************************************/
public class RentDVDDialog extends JDialog implements ActionListener{
	
	/** panel containing elements on display */
	JPanel dialogPanel;
	
	/** fields for user to enter information */
	JTextField name, title, rentDate, dueDate;
	
	/** buttons to rent the dvd/blue ray or cancel the dialog */
	JButton rent, cancel;
	
	/** DVD that is returned if dvd is called */
	DVD rental;
	
	/** Blueray that is returned if bluray is called */
	BlueRay rental2;
	
	/******************************************************************
	 * default constructor for dialog
	 *****************************************************************/
	public RentDVDDialog(){
		setModal(true);
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(5,2));
		
		dialogPanel.add(new JLabel("Your Name:"));
		name = new JTextField("Bob Ross");
		dialogPanel.add(name);
		
		dialogPanel.add(new JLabel("Title of Movie:"));
		title = new JTextField("Avengers");
		dialogPanel.add(title);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		
		dialogPanel.add(new JLabel("Rented on Date:"));
		rentDate = new JTextField(sdf.format(today));
		dialogPanel.add(rentDate);
		
		today.setTime(today.getTime() + 1*24*60*60*1000);
		dialogPanel.add(new JLabel("Due on Date:"));
		dueDate = new JTextField(sdf.format(today));
		dialogPanel.add(dueDate);
		
		rent = new JButton("Rent");
		dialogPanel.add(rent);
		rent.addActionListener(this);
		
		cancel = new JButton("Cancel");
		dialogPanel.add(cancel);
		cancel.addActionListener(this);
		add(dialogPanel);
		setSize(500,500);
		setVisible(true);
	}
	
	/******************************************************************
	 * gets the dvd for the gui when called
	 * @return rental the dvd that was rented
	 *****************************************************************/
	public DVD getRentalDVD(){
		return rental;
	}
	
	/******************************************************************
	 * gets the bluray for the gui when called
	 * @return rental2 the bluray that was rented
	 *****************************************************************/
	public BlueRay getRentalBlueRay(){
		return rental2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//exits dialog without doing anything
		if (e.getSource() == cancel){
			dispose();
		}
		
		// stores info in textfields into a dvd/blueray
		if (e.getSource() == rent){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				if (rentDate.getText().length() != 10 || 
						dueDate.getText().length() != 10){
					JOptionPane.showMessageDialog(null, "Invalid date entered");
					return;
				}
				
					Date temp1 = sdf.parse(rentDate.getText());
					Date temp2 = sdf.parse(dueDate.getText());
				
				if(temp1.getTime() > temp2.getTime()){
					JOptionPane.showMessageDialog(null, "return date is before rental date!");
					return;
				}
				
				GregorianCalendar temp3 = new GregorianCalendar();
				temp3.setTime(temp1);
				
				GregorianCalendar temp4 = new GregorianCalendar();
				temp4.setTime(temp2);
								
				rental = new DVD(temp3, temp4, title.getText(), name.getText());
				rental2 = new BlueRay(temp3, temp4, title.getText(), name.getText());
				setVisible(false);
			}catch(ParseException ex){
				JOptionPane.showMessageDialog(null, "Invalid date entered");
			}
		}
		
	}
}
