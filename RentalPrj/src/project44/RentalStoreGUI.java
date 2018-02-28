package project44;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**********************************************************************
 * the GUI for the rental store, uses a jlist to update currently
 * rented dvds and can call save/load functions from RentalStore class
 * @author Gregory Huizenga
 * @version 7/26/2017
 *********************************************************************/
public class RentalStoreGUI extends JFrame implements ActionListener{

	// used for compatibility when saving / loading files
	private static final long serialVersionUID = 1L;

	/**the RentalStore used by the gui */
	private RentalStore rentalStore;
	
	/** the menu */
	private JMenuBar menu;
	
	/** the tabs on the menu */
	private JMenu file, action;
	
	/** the options available from the menu */
	private JMenuItem saveFile, saveText, loadFile, loadText, rentDVD, 
					  rentBlueRay, rentGame, turnIn;
	
	/** the jlist used to display the rented dvds */
	private JList<DVD> rentalList;
	
	/******************************************************************
	 * default constructor for gui
	 *****************************************************************/
	public RentalStoreGUI(){
		rentalStore = new RentalStore();
		rentalList = new JList<DVD>(rentalStore);
		menu = new JMenuBar();
		file = new JMenu("file");
		action = new JMenu("action");
		saveFile = new JMenuItem("save (file)");
		saveText = new JMenuItem("save (text)");
		loadFile = new JMenuItem("load (file)");
		loadText = new JMenuItem("load (text)");
		rentDVD = new JMenuItem("rent DVD");
		rentBlueRay = new JMenuItem("rent Blue Ray");
		rentGame = new JMenuItem("rent Game");
		turnIn = new JMenuItem("return");
		
		menu.add(file);
		menu.add(action);
		file.add(saveFile);
		file.add(loadFile);
		file.add(saveText);
		file.add(loadText);
		action.add(rentDVD);
		action.add(rentBlueRay);
		action.add(rentGame);
		action.add(turnIn);
		
		// adds all actionlisteners
		saveFile.addActionListener(this);
		loadFile.addActionListener(this);
		saveText.addActionListener(this);
		loadText.addActionListener(this);
		rentDVD.addActionListener(this);
		rentBlueRay.addActionListener(this);
		rentGame.addActionListener(this);
		turnIn.addActionListener(this);
		
		//adds jlist inside a scrollpane
		rentalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(rentalList);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		setJMenuBar(menu);
		getContentPane().add(scroll);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
		setTitle("Rental Store");
		setVisible(true);
		
		

	}
	
	/******************************************************************
	 * main method used to create gui for user
	 * @param args
	 *****************************************************************/
	public static void main(String [] args){
		RentalStoreGUI GUI = new RentalStoreGUI();
		
	}
	
	
	
	@Override
	/******************************************************************
	 * checks for which button was pressed and performs actions
	 *****************************************************************/
	public void actionPerformed(ActionEvent e) {
		int index = rentalList.getSelectedIndex();
		GregorianCalendar temp3 = null;
		
		// if return button was pressed
		if (e.getSource() == turnIn){
			if (index == -1 || index == rentalStore.getSize()){
				return;
			}
			
			try{
			
			// converts provided string into a gregoriancalendar date
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String returnDate = JOptionPane.showInputDialog("Enter return date (format MM/dd/yyyy)", sdf.format(new Date()));
			
			if (returnDate.length() != 10){
				JOptionPane.showMessageDialog(null, "Invalid return date entered");
				return;
			}
			
			Date temp1 = sdf.parse(returnDate);
			temp3 = new GregorianCalendar();
			temp3.setTime(temp1);
			
			}catch(ParseException ex){
				JOptionPane.showMessageDialog(null, "Invalid return date entered");
				return;
			}
		
		//creates message dialog telling user the cost of the rental
		String returnMessage = "Thank you for returning: " + ((DVD) rentalStore.getElementAt(index)).getTitle() + "\n";
		returnMessage += ((DVD) rentalStore.getElementAt(index)).getCost(temp3);
			JOptionPane.showMessageDialog(null, returnMessage);
			rentalStore.remove(rentalList.getSelectedIndex());
			rentalStore.update();
		}
		
		//if savefile button was pushed
		if (e.getSource() == saveFile){
			String fileName = JOptionPane.showInputDialog("Enter a name for the file to be saved to:");
			if (fileName != null){
					try {
						rentalStore.saveToFile(fileName);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
			}
		}
		
		// if savetext button was pushed, currently unimplemented
		if (e.getSource() == saveText){
			try {
				rentalStore.saveToText(JOptionPane.showInputDialog
						("Enter a file name to save to"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//if loadfile button was pushed
		if (e.getSource() == loadFile){
			String fileName = JOptionPane.showInputDialog("Enter a name for the file to be loaded from:");
			if (fileName != null){
				try{
					rentalStore.loadFromFile(fileName);
					rentalStore.update();
				}catch(FileNotFoundException f){
					JOptionPane.showMessageDialog(null, "File not found!");
				}
			}
		}
		
		//if loadtext button was pushed, currently unimplemented
		if (e.getSource() == loadText){
			String fileName = JOptionPane.showInputDialog("Enter a name for the file to be loaded from:");
			if (fileName != null){
				try{
					rentalStore.loadFromText(fileName);
					rentalStore.update();
				}catch(IOException i){
					JOptionPane.showMessageDialog(null, "oops, there was a problem");
				}catch(ParseException p){
					JOptionPane.showMessageDialog(null, "oops, there was an issue");
				}
			}
		}
		
		//if rentdvd button was pushed
		if (e.getSource() == rentDVD){
			
			//creates dialog prompting user to add fields for dvd
			RentDVDDialog rent = new RentDVDDialog();
			rentalStore.add(rent.getRentalDVD());
			rentalStore.update();
			rent.dispose();
		}
		
		//if rentBluray button was pushed
		if (e.getSource() == rentBlueRay){
			
			//creates dialog prompting user to add fields for bluray
			RentDVDDialog rent = new RentDVDDialog();
			rentalStore.add(rent.getRentalBlueRay());
			rentalStore.update();
			rent.dispose();
		}
		
		//if rentgame button was pushed
		if (e.getSource() == rentGame){
			
			//creates dialog prompting user to add fields for game
			RentGameDialog rent = new RentGameDialog();
			rentalStore.add(rent.returnGame());
			rentalStore.update();
			rent.dispose();
		}
		
			
			

		}
		
	}


