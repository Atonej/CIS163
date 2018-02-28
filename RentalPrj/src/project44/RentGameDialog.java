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
 * dialog box for renting a game, has an additional row where game
 * player is selected
 * @author Gregory Huizenga
 * @version 7/26/2017
 *********************************************************************/
public class RentGameDialog extends JDialog implements ActionListener{
	/** the panel containing elements on the dialog */
	JPanel dialogPanel;
	
	/** fields for user to enter information */
	JTextField name, title, rentDate, dueDate;
	
	/** buttons to rent, cancel and cycle playertype */
	JButton rent, cancel, playerButton;
	
	/** the label for current playertype */
	JLabel game;
	
	/** the game being returned by the dialog */
	Game rental;
	
	/******************************************************************
	 * default constructor for dialog
	 *****************************************************************/
	public RentGameDialog(){
		setModal(true);
		dialogPanel = new JPanel();
		dialogPanel.setLayout(new GridLayout(6,2));
		
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
		
		playerButton = new JButton("Game Player:");
		playerButton.addActionListener(this);
		dialogPanel.add(playerButton);
		
		game = new JLabel("Xbox 360");
		dialogPanel.add(game);
		
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
	 * returns the game being rented
	 * @return rental the game being rented
	 *****************************************************************/
	public Game returnGame(){
		return rental;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//exits dialog without doing anything
		if (e.getSource() == cancel){
			dispose();
		}
		
		//updates game with user inputted values
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
				
				rental = new Game(temp3, temp4, title.getText(), name.getText(), game.getText());
				setVisible(false);
			}catch(ParseException ex){
				JOptionPane.showMessageDialog(null, "Invalid date entered");
			}
		}
	
		//cycles between playertypes
		if (e.getSource() == playerButton){
			if (game.getText().equals("Xbox 360")){
				game.setText("PS4");
				return;
			}
			
			if(game.getText().equals("PS4")){
				game.setText("Xbox 720");
				return;
			}
			
			if(game.getText().equals("Xbox 720")){
				game.setText("Xbox 360");
				return;
			}
			}
		}
		
	}


