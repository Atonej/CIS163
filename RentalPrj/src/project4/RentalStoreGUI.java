package project4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListModel;


public class RentalStoreGUI extends JFrame implements
ActionListener, Serializable {
	

    /** menu bar for applied menu */
    private JMenuBar menus;
    
    /** menu for file */
    private JMenu fileMenu;
    
    /** menu for action */
    private JMenu actionMenu;
    
    /** open menu item */
    private JMenuItem openItem;
    
    /** quit menu item */
    private JMenuItem quitItem;
    
    /** open text menu item */
    private JMenuItem openText;
    
    /** save text menu item */
    private JMenuItem saveText;
    
    /** save menu item */
    private JMenuItem saveItem;
    
    /** remove menu item */
    private JMenuItem removeItem;
    
    /** rent DVD menu item under action */
    private JMenuItem rentDVD;
    
    /** rent Game menu item under action */
    private JMenuItem rentGame;
    
    /** rent Blue-Ray menu item under action */
    private JMenuItem rentBluray;

    
    /** Program of Study for a student */
    private RentalStore store;

    /** Displays all courses */
    private JList JListArea;
  
/***********************************************************************
* Main method starts the GUI
***********************************************************************/
    public static void main(String[] args) {
        new RentalStoreGUI();
    }
    
/*****************************************************************
* Constructor  for the the rental store 
 *****************************************************************/
    public RentalStoreGUI(){
        
        // Create menu items
        menus = new JMenuBar();
        setJMenuBar(menus);
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        openItem = new JMenuItem("Open File");
        quitItem = new JMenuItem("Quit");
        saveItem = new JMenuItem("Save File");
        openText = new JMenuItem("Open Text");
        saveText = new JMenuItem("Save Text");
        removeItem = new JMenuItem("Return");
        rentDVD = new JMenuItem("rent DVD");
        rentGame = new JMenuItem("rent Game");
        rentBluray = new JMenuItem("rent Blu-Ray");

        //adding items to menu bar
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(openText);
        fileMenu.add(saveText);
        fileMenu.addSeparator();
        fileMenu.add(quitItem);
        actionMenu.add(removeItem);
        actionMenu.add(rentDVD);
        actionMenu.add(rentGame);
        actionMenu.add(rentBluray);
        menus.add(fileMenu);
        menus.add(actionMenu);

        // Add actionListeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        saveText.addActionListener(this);
        quitItem.addActionListener(this);
        removeItem.addActionListener(this);
        rentDVD.addActionListener(this);
        rentGame.addActionListener(this);
        rentBluray.addActionListener(this);
        // Create a Program of Study with initial test data
        store = new RentalStore();
        //store.fillWithTestData();
         
        // Create JListArea and link to the Program of Study
        JListArea = new JList((ListModel) store);
        add(JListArea);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        setVisible(true);
        
    }

/***********************************************************************
* This method handles event-handling code for the GUI
* 
* @param e - Holds the action event parameter
***********************************************************************/
    public void actionPerformed(ActionEvent e) {

        if (openItem == e.getSource()) {
            loadFromFile();
        }

        if (saveItem == e.getSource()) {
            saveToFile();
        }

        if(quitItem == e.getSource()){
            System.exit(1);
        }
        
        if(openText == e.getSource()){
        	try {
				loadFromText("filename");
			} catch (IOException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        if(saveText == e.getSource()){
        	try {
				saveToText("filename");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        
        if (removeItem == e.getSource()) {
        	try{
        		int index = JListArea.getSelectedIndex();
        		// FIX ME: Remove selected item or display message
        		// if not item selected
        		if (index != -1) {      
        			JOptionPane.showMessageDialog(
        					this,"Removed: "+ store.remove(index));
        		}else{
                	JOptionPane.showMessageDialog(
                			this,"Select a course");
        		}
        	}catch(IndexOutOfBoundsException er){
        		JOptionPane.showMessageDialog(
        				this, "Warning, no course selected!");
        	}
        }
        
		
		//if rent dvd button was pushed
		if (e.getSource() == rentDVD){
			
			//creates dialog prompting user to add fields for dvd
			RentDVDDialog rent = new RentDVDDialog();
			store.add(rent.getRentalDVD());
			store.update();
			rent.dispose();
		}
		
		//if rent Bluray button was pushed
		if (e.getSource() == rentBluray){
			
			//creates dialog prompting user to add fields for bluray
			RentDVDDialog rent = new RentDVDDialog();
			store.add(rent.getRentalBlueRay());
			store.update();
			rent.dispose();
		}
		
		//if rent game button was pushed
		if (e.getSource() == rentGame){
			
			//creates dialog prompting user to add fields for game
			RentGameDialog rent = new RentGameDialog();
			store.add(rent.returnGame());
			store.update();
			rent.dispose();
		}
		
    }
    
/***********************************************************************
* Display JFileChooser to load previously saved data
***********************************************************************/
    private void loadFromFile(){
          JFileChooser chooser = new JFileChooser();
          //send to the folder being used in
     File workingDirectory = new File(System.getProperty("user.dir"));
         chooser.setCurrentDirectory(workingDirectory);
         int status = chooser.showOpenDialog(null);
          
            if (status == JFileChooser.APPROVE_OPTION) {
       try{
      	String filename = chooser.getSelectedFile().getAbsolutePath();
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                store = (RentalStore) ois.readObject();
                JListArea.setModel(store);
                ois.close();
            }catch(Exception ex){
            }   
            }
            
    
    }
    
/***********************************************************************
* Display JFileChooser to save current list of courses
***********************************************************************/
     private void saveToFile(){
            
            // Display JFileChooser and wait for response
            JFileChooser chooser = new JFileChooser();
            File workingDirectory = 
            		new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            int status = chooser.showSaveDialog(null);
            
            // when saved
            if (status == JFileChooser.APPROVE_OPTION) {
         String filename = chooser.getSelectedFile().getAbsolutePath();
                try{
                 FileOutputStream fos = new FileOutputStream(filename); 
                  ObjectOutputStream oos = new ObjectOutputStream(fos); 
                    
                    oos.writeObject(store); 
                    oos.flush(); 
                    oos.close(); 
                }catch(Exception ex){
            
                }
            }       

    }
     
/***********************************************************************
* saves list of rentals to a text file with specified name
* @param filename the name to save to
* @throws FileNotFoundException 
***********************************************************************/
 	public void saveToText(String filename) 
 			throws FileNotFoundException{
 		//write to file 
 		PrintWriter out = new PrintWriter(filename);
 		out.println(RentalStore.getRentalStore().size());
 		for(int i = 0; i < RentalStore.getRentalStore().size(); i++){
 			//usual format of dating
 			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 			DVD temp = (DVD)RentalStore.getRentalStore().get(i);
 			Object temp2 = RentalStore.getRentalStore().get(i);
 			//print to the file in the order of the list, new line
 			out.println(RentalStore.getRentalStore().get(i).getClass());
 			out.println(temp.getNameOfRenter());
 			out.println(temp.getTitle());
 			out.println(sdf.format(temp.getBought().getTime()));
 			out.println(sdf.format(temp.getDueBack().getTime()));
 			
if (RentalStore.getRentalStore().get(i).getClass().toString().
		contains("Game")){
 				out.println(((Game) temp2).getPlayer());
 			}
 		}
 		out.close();
 	}
/***********************************************************************
 * loads the specified file that should be a list as a text file
 * @param filename the name to save to
 * @throws IOException
 * @throws ParseException
***********************************************************************/
	public void loadFromText(String filename) 
			throws IOException, ParseException{

		BufferedReader reader = new BufferedReader(
				new FileReader(filename));
		
		String temp = reader.readLine();
		Scanner scnr = new Scanner(temp);
		int count = scnr.nextInt();
		
		 RentalStore.getRentalStore().clear();
		
		for(int i = 0; i < count; i++){
			String type = reader.readLine();
			String name = reader.readLine();
			String title = reader.readLine();
			String rentDate = reader.readLine();
			String dueDate = reader.readLine();
			String player = reader.readLine();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date rDate = sdf.parse(rentDate);
			Date dDate = sdf.parse(dueDate);
			
			GregorianCalendar reDate = new GregorianCalendar();
			GregorianCalendar duDate = new GregorianCalendar();
			reDate.setTime(rDate);
			duDate.setTime(dDate);
			
			if (type.contains("Game")){
				//String t = PlayerType.PS4.toString();	
				//String p = player.toString();
				//player= reader.read();
		Game game = new Game(title, name, player,reDate, duDate);
				 RentalStore.getRentalStore().add(game);
			}else if (type.contains("BlueRay")){
				BlueRay blu = new BlueRay(title, name,reDate, duDate);
				 RentalStore.getRentalStore().add(blu);
			}else{
				DVD dvd = new DVD(title, name,reDate, duDate);
				 RentalStore.getRentalStore().add(dvd);
			}
			
			
		}
		reader.close();
		}
     }

