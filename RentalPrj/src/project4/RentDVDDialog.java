package project4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

/***********************************************************************
 * Rent DVD popup dialog used under the GUI 
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class RentDVDDialog  extends JDialog implements ActionListener{
//	private JTextField titleTxt;
//	private JTextField renterTxt;
//	private JTextField rentedOnTxt;
//	private JTextField DueBackTxt;
//	private JButton okButton;
//	private JButton cancelButton;
//	private boolean closeStatus;
//	private DVD unit;  
//	
//	public RentDVDDialog(JFrame parent, DVD d) {	
//		unit = d; 
//}
//
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
////		if(titleTxt == e.getSource()){
////			
////		}
////if(renterTxt == e.getSource()){
////			
////		}
////if(rentedOnTxt == e.getSource()){
////	
////}
////if(DueBackTxt == e.getSource()){
////	
////}
////if(renterTxt == e.getSource()){
//	
//}
//	    private static final long serialVersionUID = 1L;	 
//	    public RentDVDDialog(JFrame parent, String title, String message) {
//
//	        super(parent, title);
//	        System.out.println("creating the window..");
//	        // set the position of the window
//	        Point p = new Point(400, 400);
//	        setLocation(p.x, p.y);
//
//	        // Create a message
//
//	        JPanel messagePane = new JPanel(new GridLayout(5,2));
//	        
//
//	        messagePane.add(new JLabel(message));
//	        // get content pane, which is usually the
//	        // Container of all the dialog's components.
//	        getContentPane().add(messagePane);
//	 
//	        // Create a button
//	        JPanel buttonPane = new JPanel();
//
//	        JButton button = new JButton("Close me");
//	        buttonPane.add(button);
//	        // set action listener on the button
//	
//	        button.addActionListener(new MyActionListener());
//	
//	        getContentPane().add(buttonPane, BorderLayout.PAGE_END);
//	
//	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//	
//	        pack();
//	
//	        setVisible(true);
//	
//	    }
//
//	 
//	
//	  // override the createRootPane inherited by the JDialog, to create the rootPane.
//	
//	    // create functionality to close the window when "Escape" button is pressed
//
//	    public JRootPane createRootPane() {
//	
//	      JRootPane rootPane = new JRootPane();
//	
//	        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
//	
//	       Action action = new AbstractAction() {
//	
//	             
//	
//	            private static final long serialVersionUID = 1L;
//	
//	 
//
//	            public void actionPerformed(ActionEvent e) {
//	              System.out.println("escaping..");
//             setVisible(false);
//	
//	                dispose();
//
//	            }
//	
//	      };
//	
//	        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
//	
//	        inputMap.put(stroke, "ESCAPE");
//	
//	        rootPane.getActionMap().put("ESCAPE", action);
//	
//	        return rootPane;
//	
//	    }
//	
//	 
//	
//	   // an action listener to be used when an action is performed
//	
//	    // (e.g. button is pressed)
//	
//	    class MyActionListener implements ActionListener {
//	
//	 
//	
//	        //close and dispose of the window.
//	
//	        public void actionPerformed(ActionEvent e) {
//	
//	            System.out.println("disposing the window..");
//	
//	            setVisible(false);
//	            dispose();
//
//	       }
//	
//	    }
//	
//	 
//	
//	    public static void main(String[] a) {
//	
//	        RentDVDDialog dialog = new RentDVDDialog(new JFrame(), "Rent DVD Dialog ", "Need Text fields");
//	
//	        // set the size of the window
//	        dialog.setSize(300, 150);
//		    }
	/** panel containing elements on display */
	JPanel dialog;
	
	/** fields for user to enter information */
	JTextField name, title, rentDate, dueDate;
	
	/** buttons to rent the dvd/blue ray or cancel the dialog */
	JButton rent, cancel;
	
	/** DVD that is returned if dvd is called */
	DVD rentalD;
	
	/** Blueray that is returned if bluray is called */
	BlueRay rentalB;
	
	/******************************************************************
	 * default constructor for dialog
	 *****************************************************************/
	public RentDVDDialog(){
		//used to process dialog before setting visib
		setModal(true);
		//make new panel
		dialog = new JPanel();
		dialog.setLayout(new GridLayout(5,2));
		
		//display of list
		dialog.add(new JLabel("Your Name:"));
		name = new JTextField("Jebron Lames");
		dialog.add(name);
		
		dialog.add(new JLabel("Title of Movie:"));
		title = new JTextField("Avengers");
		dialog.add(title);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		
		dialog.add(new JLabel("Rented on Date:"));
		rentDate = new JTextField(sdf.format(today));
		dialog.add(rentDate);
		
		//set the date format to be into the current day
		//conversion from miliseconds
		today.setTime(today.getTime() + 1*24*60*60*1000);
		dialog.add(new JLabel("Due on Date:"));
		dueDate = new JTextField(sdf.format(today));
		dialog.add(dueDate);
		
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
 * getter method for the renter DVD
 * @return rental the dvd that was rented
***********************************************************************/
	public DVD getRentalDVD(){
		return rentalD;
	}
	
/***********************************************************************
* getter method for the bluray rented
* @return rental2 the bluray that was rented
***********************************************************************/
	public BlueRay getRentalBlueRay(){
		return rentalB;
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
		
		// stores info in textfields into a dvd/blueray
		if (e.getSource() == rent){
			try{
				SimpleDateFormat sdf =
						new SimpleDateFormat("MM/dd/yyyy");
				if (rentDate.getText().length() != 10 || 
						dueDate.getText().length() != 10){
					JOptionPane.showMessageDialog(
							null, "Enter a appropriate date");
					return;
				}
				
					Date temp1 = sdf.parse(rentDate.getText());
					Date temp2 = sdf.parse(dueDate.getText());
				
				if(temp1.getTime() > temp2.getTime()){
					JOptionPane.showMessageDialog(
							null, "return date is before rental date!");
					return;
				}
				//sends the elements needed for the DVD,Game or Blueray
				GregorianCalendar bought = new GregorianCalendar();
				bought.setTime(temp1);
				 
				GregorianCalendar dueBack = new GregorianCalendar();
				dueBack.setTime(temp2);
								
				rentalD = new DVD(title.getText(), 
						name.getText(),bought, dueBack);
				rentalB = new BlueRay(title.getText(),
						name.getText(),bought, dueBack);
				setVisible(false);
			}catch(ParseException ex){
				JOptionPane.showMessageDialog(
						null, "Invalid date entered");
			}
		}
		
	}
	
	}


