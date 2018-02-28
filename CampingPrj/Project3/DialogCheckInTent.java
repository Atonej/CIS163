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

public class DialogCheckInTent extends JDialog implements ActionListener, KeyListener{


	private static final long serialVersionUID = 1L;
	private JTextField nameTxt;
	private JTextField occupiedOnTxt;
	private JTextField stayingTxt;
	private JTextField tentersNumberTxt;
	private JTextField siteNumberTxt;
	private JButton okButton;
	private JButton cancelButton;
	private GregorianCalendar cal;
	private JPanel dialog;
	private JTextField dueDate;
	public Tent tentInfo;
	public JFrame frame;

	DialogCheckInTent(JFrame frame, Tent tent){
		//used to process dialog before setting visible
		setModal(true);

		this.tentInfo = tent;
		
		//make new panel
		dialog = new JPanel();
		dialog.setLayout(new GridLayout(6,2));

		//display of list
		dialog.add(new JLabel("Name of Reserver:"));
		nameTxt = new JTextField("Jebron Lames");
		dialog.add(nameTxt);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();

		dialog.add(new JLabel("Occupied on Date:"));
		occupiedOnTxt = new JTextField(sdf.format(today));
		dialog.add(occupiedOnTxt);

		dialog.add(new JLabel("Days Planning On Staying:"));
		stayingTxt = new JTextField("1");
		dialog.add(stayingTxt);
		stayingTxt.addKeyListener(this);

		dialog.add(new JLabel("Requested Site Number:"));
		siteNumberTxt = new JTextField("1");
		dialog.add(siteNumberTxt);
		siteNumberTxt.addKeyListener(this);

		dialog.add(new JLabel("Number of Tenters"));
		tentersNumberTxt = new JTextField("1");
		dialog.add(tentersNumberTxt);

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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
if(okButton == e.getSource()) {
	try {
	SimpleDateFormat sdf =
			new SimpleDateFormat("MM/dd/yyyy");
	
	int staying = Integer.parseInt(stayingTxt.getText());
	int site = Integer.parseInt(siteNumberTxt.getText());
	String tenters = tentersNumberTxt.getText() + " Tenter(s)";
	Date temp1 = sdf.parse(occupiedOnTxt.getText());
	
	GregorianCalendar occupied = new GregorianCalendar();
	
	occupied.setTime(temp1);
	
	
	this.tentInfo.setCheckIn(occupied);
	this.tentInfo.setDaysStaying(staying);
	this.tentInfo.setNameReserving(nameTxt.getText());
	this.tentInfo.setSiteNumber(site);
	this.tentInfo.setNumOfTenters(tenters);


	
	//tentInfo = new Tent(nameTxt.getText(), occupied, staying, tenters, site);
	//System.exit(-1);
	//don't exit just make visibility false
	setVisible(false);
	

	}
	
	catch (ParseException e2) {
		JOptionPane.showMessageDialog(
				null, "Invalid date entered");
		
	}
}
if(cancelButton == e.getSource()) {
	//System.exit(-1);
	dispose();

}
	}		
	


	@Override
	public void keyPressed(KeyEvent evt) {
		int key = evt.getKeyCode();

		if(stayingTxt.isFocusOwner())

			//if the key pressed is a number allow user to edit the JTextfield
			if((key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9) || (key >= KeyEvent.VK_NUMPAD0 &&
			key <= KeyEvent.VK_NUMPAD9) || (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE))
				stayingTxt.setEditable(true);		
			else	
				stayingTxt.setEditable(false);			

		//A site can only be values 1-5 so the event checks the key against the possible values
		if(siteNumberTxt.isFocusOwner())
			if((key >= KeyEvent.VK_0 && key <= KeyEvent.VK_5) || (key >= KeyEvent.VK_NUMPAD0 &&
			key <= KeyEvent.VK_NUMPAD5) || (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE))
				siteNumberTxt.setEditable(true);
			else
			{
				siteNumberTxt.setEditable(false);
				JOptionPane.showMessageDialog(null, "Please choose a site between digits 1 to 5.");
				siteNumberTxt.setEditable(true);
				siteNumberTxt.requestFocus();
			}

	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		if (siteNumberTxt.isFocusOwner())

			//Limits the siteNumberTxt JTextField to only one character
			if(siteNumberTxt.getText().length() >= 1 && !(e.getKeyChar()==KeyEvent.VK_DELETE||e.getKeyChar() ==
			KeyEvent.VK_BACK_SPACE)) {				
				e.consume();
			} 
		
	}
}