package Project3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/***********************************************************************
 * This is the GUI of the program that would allow an interface to show
 * the features of the camping program
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ritcksgers 
 * @version Fall 2017 
 **********************************************************************/
public class GUICampingReg extends JFrame implements ActionListener {
	// declare GUI components (menu items, buttons, etc.) needed
	// constructor method that prepares the GUI
	// event listeners and other methods needed to build the GUI

	/** menu bar at the top of JFram**/
	private JMenuBar menuBar;

	/** drop down menu to choose to save or load **/ 
	private JMenu file;

	/** menu option to check in as a RV or tent **/
	private JMenu checkIn;

	/** menu item to save a file as Serializable **/ 
	private JMenuItem save;

	/** menu item to load a file from Serializable file **/ 
	private JMenuItem load;

	/** menu item to save a file as a text **/ 
	private JMenuItem saveText;

	/** menu item to save a file from text file **/ 
	private JMenuItem loadText;

	/** to check in under rv **/
	private JMenuItem rv;

	/** to check in under tent **/
	private JMenuItem tent;

	/** exit out program item **/
	private JMenuItem exitItem;

	/** set the list into a table**/
	private JTable jListTable;

	/** adjust to a scroll pane once too big**/
	private JScrollPane scroll;

	/**used to refer to SiteModel class to add to list**/
	private SiteModel model;

	private JButton undo;

	private JButton delete;

	private JPanel panel;
	
	private JMenuItem name;
	private JMenu sort;


	/***********************************************************************
	 * This constructor will build the GUI needed to see the items on 
	 * the JFrame.  
	 **********************************************************************/
	public GUICampingReg() {
		//set up options on the screen
		file = new JMenu("File");
		checkIn = new JMenu("Check In");
		sort = new JMenu("Sort List By:");
		load = new JMenuItem("Open Serial");
		save = new JMenuItem("Save Serial");
		exitItem = new JMenuItem("Exit");
		loadText = new JMenuItem("Open Text");
		saveText = new JMenuItem("Save Text");
		rv= new JMenuItem("RV");
		tent= new JMenuItem("Tent");
		name = new JMenuItem("Name");
		panel = new JPanel();

		undo = new JButton("Undo");
		delete = new JButton("Delete");
		//set the list
		model = new SiteModel();
		//set up a different panel than this frame
		panel.setLayout(new GridLayout(1,2));

		setLayout(new BorderLayout());


		//for the file
		file.add(load);
		file.add(save);
		file.addSeparator();
		file.add(loadText);
		file.add(saveText);
		file.addSeparator();		
		file.add(exitItem);

		//for check in menu 
		checkIn.add(rv);
		checkIn.add(tent);
		
		sort.add(name);

		//set the action listeners	
		rv.addActionListener(this);
		tent.addActionListener(this);
		load.addActionListener(this);
		exitItem.addActionListener(this);
		save.addActionListener(this);
		loadText.addActionListener(this);
		saveText.addActionListener(this);
		undo.addActionListener(this);
		delete.addActionListener(this);
		name.addActionListener(this);

		//set up the menu bar for file and checking in
		menuBar = new JMenuBar();
		menuBar.add(file); 
		menuBar.add(checkIn);
		menuBar.add(sort);
		setJMenuBar(menuBar);

		//add in the listed table 
		jListTable = new JTable(model);

		//add onto a scroll pane
		scroll = new JScrollPane(jListTable);

		panel.add(undo);
		panel.add(delete);

		//this.repaint(); //is this needed??
		// add scrollPane and everything it's holding inside the frame
		this.add(scroll, BorderLayout.NORTH);
		//add the undo and delete button
		this.add(panel, BorderLayout.SOUTH);

		setVisible(true);
		setSize(800,515);
	}


	/***********************************************************************
	 * This main class will start up the GUI inside the program
	 **********************************************************************/
	public static void main (String[] args) {
		new GUICampingReg();
	}


/***********************************************************************
 * this method will be the listener for the buttons used with 
 * ActionLisener
 **********************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		//open under serialized file
				if(load == e.getSource()){
					
					JFileChooser chooser = new JFileChooser();
					int status = chooser.showOpenDialog(null);
					if (status == JFileChooser.APPROVE_OPTION) {
						String filename = chooser.getSelectedFile().
								getAbsolutePath();
						Site s = new Site(); 
						int n = JOptionPane.showConfirmDialog(
								this, 
								"Would you like to clear the screen?",
										"", JOptionPane.YES_NO_OPTION);					
						if( n == JOptionPane.YES_OPTION){
							model.clear();
						}
					try {
						model.loadFromSerialized(filename, s);	
					}
					//if site taken
					catch(IllegalArgumentException i) {
				JOptionPane.showConfirmDialog(
						this, "At least one of these sites are taken");
					}
					}
					
					
				}

		//save under a serialized file
		if(save == e.getSource()){
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showSaveDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile().
						getAbsolutePath();				
				model.saveSerializedSite(filename);				
			}
		}
		
		if(name == e.getSource()){
			model.sortName();
		}

		// load from txt
		if(loadText == e.getSource()){
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String fileName = chooser.getSelectedFile().getAbsolutePath();
				try{
					int n = JOptionPane.showConfirmDialog(
							this, "Would you like to clear the screen?",
									"", JOptionPane.YES_NO_OPTION);					
					if( n == JOptionPane.YES_OPTION){
						model.clear();
					}
						try {
						model.loadFromText(fileName);
						model.update();
						}
						
						catch(IllegalArgumentException a) {
						JOptionPane.showConfirmDialog(null, "Cannot interfere with dates already on the board");
						}
					
				}catch(IOException i){
					
					JOptionPane.showMessageDialog(null, 
							"oops, there was a problem");
				}catch(ParseException p){
					JOptionPane.showMessageDialog(null,
							"oops, there was an issue");
				
			}
				
		}
		}

		//save from txt
		if(saveText == e.getSource()){
			try {
				model.saveToText(JOptionPane.showInputDialog
						("Enter a file name to save to"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		//exit campingReg
		if(exitItem == e.getSource()){
			System.exit(-1);
		}


		//check in the rv
		if(rv == e.getSource()){
			RV r = new RV();
			new DialogCheckInRV(this, r);

			if(model.checkSite(r.getSiteNumber(),r.getDaysStaying(), r.getCheckIn()) == -1){

				String dates = model.returnDates(r.getSiteNumber(),r.getDaysStaying(), r.getCheckIn());
				JOptionPane.showMessageDialog(this, "These Dates: \n" + dates +" are not avalible.");
			}
			else{
				int n = JOptionPane.showConfirmDialog(
						this, "To stay "+ r.daysStaying +" days with "+ r.getPower() +" amps of power it will cost $" +
								r.cost() + ". Is that okay?",
								"", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					model.add(r);
					model.changeAvalibility(r.siteNumber,r.daysStaying, r.checkIn);
				} 
				else if (n == JOptionPane.NO_OPTION) {
					dispose();
				} 
			}
		}

		//check in the tent
		if(tent == e.getSource()){
			Tent t = new Tent();
			new DialogCheckInTent(this, t);
			if(model.checkSite(t.getSiteNumber(),t.getDaysStaying(), t.getCheckIn()) == -1){				

				JOptionPane.showMessageDialog(this, "These Dates: \n" + model.returnDates( 
						t.getSiteNumber(),t.getDaysStaying(), t.getCheckIn())+ "\n are not avalible");
			}
			else{
				int n = JOptionPane.showConfirmDialog(
						this, "To stay "+ t.daysStaying +" days with "+ t.getNumOfTenters() 
						+" tenters it will cost $" +  t.cost() + ". Is that okay?",
						"", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION)
				{
					model.add(t);
					model.changeAvalibility(t.siteNumber,t.daysStaying, t.checkIn);
				} 
				else if (n == JOptionPane.NO_OPTION) {
					dispose();
				} 
			}
		}

		//undo within the list
		if(undo == e.getSource()){			 
			model.undo();			
		}

		//delete a row in the list and makes the site available for that date
		if(delete == e.getSource()){
			int index =	jListTable.getSelectedRow();
			
			model.openAvailability(index);
			model.delete(index);

		}
	}
}
