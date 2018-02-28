package Project3;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/***********************************************************************
 * This will be a pop up dialog within the GUICampingReg class to allow 
 * the use of checking in under a RV
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ricksgers 
 * @version Fall 2017 
 **********************************************************************/
public class DialogCheckInRV extends JDialog implements ActionListener, 
KeyListener{

	/**Stores the state of the object**/	 
	private static final long serialVersionUID = 1L;
	
	/** storing the given name **/
	private JTextField nameTxt;
	
	/**when the check in is**/
	private JTextField occupiedOnTxt;
	
	/**number of days staying**/
	private JTextField stayingTxt;
	
	/**camping site to choose from(1-5)**/
	private JTextField siteNumberTxt;
	
	/**decision to choose from the type of power of an RV**/
	private JComboBox<String> powerTxt;
	
	/**options available in power, of type amps**/
	private String[] amps = {"20", "30" , "40", "50"};
	
	/**ok button for acception**/
	private JButton okButton;
	
	/**cancel button to refer back to last panel**/
	private JButton cancelButton;
	
	/**keep up with where the index is of power**/
	private int index;
	
	/**used to convert the power to a workable string**/
	private String ampTxt;
	
	/**panel to pop up the dialog screen**/
	private JPanel dialog;
	
	/**used to access the sites to display**/
	private RV rvInfo;
	
	
/***********************************************************************
* Constructor method to build dialog, blocks user input to
* top-level windows when shown, and passes the values of the RV object
* to the GUICampingReg.
* @param Parent the JFrame for the GUICampingReg
* @param r the RV object created within the GUICampingReg class
***********************************************************************/
	DialogCheckInRV(JFrame Parent, RV r){
		//used to process dialog before setting visible(modal)
		super(Parent, true);
		this.rvInfo = r;
		
		buildDialogGUI();
	}
	
/***********************************************************************
* Implementation of the ActionListener class.
**********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		//in hitting the ok button 
		if(okButton == e.getSource()) {
			try {
				//set the format of the date
				SimpleDateFormat sdf = new SimpleDateFormat
						("MM/dd/yyyy");
				//use the given date and parse it into a workable date
				Date temp1 = sdf.parse(occupiedOnTxt.getText());
				GregorianCalendar occupied = new GregorianCalendar();
				//set time in the gregorian
				occupied.setTime(temp1);
				//storing the fields
				String name = nameTxt.getText();				
				int staying = Integer.parseInt(stayingTxt.getText());
				int site = Integer.parseInt(siteNumberTxt.getText());				
				
				//
				index = powerTxt.getSelectedIndex();
					
				//get the selected amps and make an integer
				index = powerTxt.getSelectedIndex();
				
				//take away the amps and only show the number
				amps[index] = amps[index].substring(0,2);				
				ampTxt = amps[index]+ " Amps";
				
				//set the fields
				rvInfo.setNameReserving(name);
				rvInfo.setDaysStaying(staying);
				rvInfo.setSiteNumber(site);
				rvInfo.setPower(ampTxt);
				rvInfo.setCheckIn(occupied);				
				
				//no longer show dialog
				setVisible(false);
			}
			//wrong date entered
			catch (ParseException e2) {
				JOptionPane.showMessageDialog(
						null, "Invalid date entered");
			}
		}		
			
		//when hitting cancel button get rid of components
		if(cancelButton == e.getSource()) {			
			this.dispose();		
		}
		}
		
	
/***********************************************************************
* Builds the GUI for the RV dialog.
***********************************************************************/
	public void buildDialogGUI() {
		setModal(true);
		//make new panel
		dialog = new JPanel();
		dialog.setLayout(new GridLayout(6,2));

		//display of list
		dialog.add(new JLabel("Name of Reserver:"));
		nameTxt = new JTextField("Jebron Lames");
		dialog.add(nameTxt);
		
		//set date format
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();

		//display check in field
		dialog.add(new JLabel("Occupied on Date:"));
		occupiedOnTxt = new JTextField(sdf.format(today));
		dialog.add(occupiedOnTxt);

		//days staying field
		dialog.add(new JLabel("Days Planning On Staying:"));
		stayingTxt = new JTextField("1");
		dialog.add(stayingTxt);
		stayingTxt.addKeyListener(this);

		//site number field
		dialog.add(new JLabel("Requested Site Number:"));
		siteNumberTxt = new JTextField("1");
		dialog.add(siteNumberTxt);
		siteNumberTxt.addKeyListener(this);



		//power field, combo box
		dialog.add(new JLabel("Type of Power in Amps:"));
		//20,30,40,50
		powerTxt = new JComboBox(amps);
		//show 50 amps
		powerTxt.setSelectedIndex(3);;
		powerTxt.addActionListener(this);
		dialog.add(powerTxt);
		
		//set up action listener
		okButton = new JButton("OK");
		dialog.add(okButton);
		okButton.addActionListener(this);

		cancelButton = new JButton("Cancel");
		dialog.add(cancelButton);
		cancelButton.addActionListener(this);
		add(dialog);
		setSize(500,500);
		setVisible(true);		
	}
	
/***********************************************************************
* Key pressed event method to keep up with what is allowable to type 
* the Jtextfields
***********************************************************************/
	@Override
	public void keyPressed (KeyEvent evt) {
		int key = evt.getKeyCode();
		
		if(stayingTxt.isFocusOwner())

			//if the key pressed is a number allow user 
			//to edit the JTextfield
			if((key >= KeyEvent.VK_1 && key <= KeyEvent.VK_7) || 
					(key >= KeyEvent.VK_NUMPAD1 &&
			key <= KeyEvent.VK_NUMPAD7) || 
					(key == KeyEvent.VK_BACK_SPACE || 
					key == KeyEvent.VK_DELETE))
				stayingTxt.setEditable(true);		
			else	
			{
				stayingTxt.setEditable(false);
				JOptionPane.showMessageDialog
				(null, 
				"Input must be a digit 1 to 7. Max stay is 7 days.");
				//can still edit
				stayingTxt.setEditable(true);
				stayingTxt.requestFocus();
			}

		//A site can only be values 1-5 so the event 
		//checks the key against the possible values
		if(siteNumberTxt.isFocusOwner())
			if((key >= KeyEvent.VK_1 && key <= KeyEvent.VK_5) ||
					(key >= KeyEvent.VK_NUMPAD0 &&
			key <= KeyEvent.VK_NUMPAD5) || 
					(key == KeyEvent.VK_BACK_SPACE || 
					key == KeyEvent.VK_DELETE))
				siteNumberTxt.setEditable(true);
			else
			{
				siteNumberTxt.setEditable(false);
				JOptionPane.showMessageDialog(null, 
						"Please choose a site between digits 1 to 5.");
				siteNumberTxt.setEditable(true);
				siteNumberTxt.requestFocus();
			}
			
	}
/***********************************************************************
* 
***********************************************************************/
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (siteNumberTxt.isFocusOwner())
			
			//Limits the siteNumberTxt JTextField to only one character
			if(siteNumberTxt.getText().length() >= 1 && 
			!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar() ==
			KeyEvent.VK_BACK_SPACE)) {				
				e.consume();
	         } 
		
		if(stayingTxt.isFocusOwner())
			if(stayingTxt.getText().length() >= 1 && 
			!(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar() ==
			KeyEvent.VK_BACK_SPACE)) {				
				e.consume();
	         } 
	}
}

