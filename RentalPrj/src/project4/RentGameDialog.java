package project4;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/***********************************************************************
 * Rent Game popup dialog used under the GUI 
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class RentGameDialog extends JDialog implements ActionListener {
	/** the panel containing elements on the dialog */
	JPanel dialog;
	
	/** fields for user to enter information */
	JTextField name, title, rentDate, dueDate;
	
	/** buttons to rent, cancel and cycle playertype */
	JButton rent, cancel, playerButton;
	
	/** the combo box for player type */	
	JComboBox drop;
	
	/** the game being returned by the dialog */
	Game rental;
	
/***********************************************************************
* default constructor for dialog
 **********************************************************************/
	public RentGameDialog(){
		
		setModal(true);
		dialog = new JPanel();
		dialog.setLayout(new GridLayout(8,2));
		
		dialog.add(new JLabel("Your Name:"));
		name = new JTextField("Quinton Sail");
		dialog.add(name);
		
		dialog.add(new JLabel("Title of Game:"));
		title = new JTextField("Call of Duty");
		dialog.add(title);
		
		SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		
		dialog.add(new JLabel("Rented on Date:"));
		rentDate = new JTextField(s.format(today));
		dialog.add(rentDate);
		
		today.setTime(today.getTime() + 1*24*60*60*1000);
		dialog.add(new JLabel("Due on Date:"));
		dueDate = new JTextField(s.format(today));
		dialog.add(dueDate);
		// Create the correct size array 
		int i=0;
		PlayerType[] gameNames = new PlayerType[PlayerType.values().length];  
		for (PlayerType p: PlayerType.values())    	// select each value;
		gameNames[i++] = p;

		//String used to also compare with the enum
		dialog.add(new JLabel("Choose Console or Find One:"));
		String[] patternExamples = {
				"PS3",
		         "PS4",
		         "PC",
		         "XboxOne",
		         "Xbox360",
		         "Xbox720"
		};
	//set up the drop down box
	final JComboBox patternList = new JComboBox(patternExamples);
		drop = patternList;
		dialog.add(patternList);
		//combo box will be editable
		patternList.setEditable(true);
		patternList.addActionListener(this);
		//make action possible
		rent = new JButton("Rent");
		dialog.add(rent);
		rent.addActionListener(this);
		
		cancel = new JButton("Cancel");
		dialog.add(cancel);
		cancel.addActionListener(this);
		add(dialog);
		setSize(500,500);
		setVisible(true);
	}
	
/***********************************************************************
 * returns the game being rented
 * @return rental the game being rented
***********************************************************************/
	public Game returnGame(){
		return rental;
	}
/***********************************************************************
* For action listener class to be able to perform actual things under
* program
* @param that action
***********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//exits dialog without doing anything
		if (e.getSource() == cancel){
			dispose();
		}
		//JComboBox select = (JComboBox)e.getSource();
 
        
		//updates game with user inputted value		
		if (e.getSource() == rent){
			try{
				SimpleDateFormat s = 
						new SimpleDateFormat("MM/dd/yyyy");
				if (rentDate.getText().length() != 10 || 
						dueDate.getText().length() != 10){
					JOptionPane.showMessageDialog(
							null, "Enter a appropriate date");
					return;
				}
				//bring the date together 
				Date temp1 = s.parse(rentDate.getText());
				Date temp2 = s.parse(dueDate.getText());
				
				if(temp1.getTime() > temp2.getTime()){
					JOptionPane.showMessageDialog(
							null, "return date is before rental date!");
					return;
				}
					
				GregorianCalendar bought = new GregorianCalendar();
				bought.setTime(temp1);
				
				GregorianCalendar dueBack = new GregorianCalendar();
				dueBack.setTime(temp2);
				
		        String console = (String)drop.getSelectedItem();
		        //PlayerType p = PlayerType.valueOf(“PS4”);	
		       				
				rental = new Game( title.getText(), 
						name.getText(), console ,bought , dueBack);
				setVisible(false);
			}catch(ParseException ex){
				JOptionPane.showMessageDialog(
						null, "Enter a appropriate date");
			}
		}
	
	}
}
