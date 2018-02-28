package lab10;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/*****************************************************************
 * Maintains the GUI for a Program Of Study
 *****************************************************************/
public class GUIProgramOfStudy extends JFrame implements ActionListener{

    /** menu items in each of the menus */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu actionMenu;
    private JMenuItem openItem;
    private JMenuItem quitItem;
    private JMenuItem saveItem;
    private JMenuItem removeItem;
    private JMenuItem randomItem;

    /** Program of Study for a student */
    private ProgramOfStudy courses;

    /** Displays all courses */
    private JList JListArea;
  
    /*****************************************************************
     * Main method starts the GUI
     *****************************************************************/
    public static void main(String[] args) {
        new GUIProgramOfStudy();
    }
    
    /*****************************************************************
     * Constructor 
     *****************************************************************/
    public GUIProgramOfStudy(){
        
        // Create menu items
        menus = new JMenuBar();
        setJMenuBar(menus);
        fileMenu = new JMenu("File");
        actionMenu = new JMenu("Action");
        openItem = new JMenuItem("Open File");
        quitItem = new JMenuItem("Quit");
        saveItem = new JMenuItem("Save File");
        removeItem = new JMenuItem("Remove");
        randomItem= new JMenuItem("Add Random");

        //adding items to menu bar
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(quitItem);
        actionMenu.add(removeItem);
        actionMenu.add(randomItem);
        menus.add(fileMenu);
        menus.add(actionMenu);

        // Add actionListeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        quitItem.addActionListener(this);
        removeItem.addActionListener(this);
        randomItem.addActionListener(this);

        // Create a Program of Study with initial test data
        courses = new ProgramOfStudy();
        courses.fillWithTestData();
         
        // Create JListArea and link to the Program of Study
        JListArea = new JList(courses);
        add(JListArea);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        setVisible(true);
    }

    /*****************************************************************
     * This method handles event-handling code for the GUI
     * 
     * @param e - Holds the action event parameter
     ****************************************************************/
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
        
        if(randomItem == e.getSource()){
        	courses.addRandomCourse();
        }
        
        if (removeItem == e.getSource()) {
        	try{
        		int index = JListArea.getSelectedIndex();
        		// FIX ME: Remove selected item or display message
        		// if not item selected
        		if (index != -1) {      
        			JOptionPane.showMessageDialog(this,"Removed: "+courses.remove(index));
        		}else{
                	JOptionPane.showMessageDialog(this,"Select a course");
        		}
        	}catch(IndexOutOfBoundsException er){
        		JOptionPane.showMessageDialog(this, "Warning, no course selected!");
        	}
        }
    }
    
    /*****************************************************************
     * Display JFileChooser to load previously saved data
     ****************************************************************/
    private void loadFromFile(){
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);
            
            // only process if user clicked OK
            if (status == JFileChooser.APPROVE_OPTION) {
            try{
                String filename = chooser.getSelectedFile().getAbsolutePath();
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                courses = (ProgramOfStudy) ois.readObject();
                JListArea.setModel(courses);
                ois.close();
            }catch(Exception ex){
                // FIX ME: provide appropriate messages for possible errors
            }   
            }
            
    
    }
    
    /*****************************************************************
     * Display JFileChooser to save current list of courses
     ****************************************************************/
     private void saveToFile(){
            
            // Display JFileChooser and wait for response
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);
            
            // only process if user clicked Save
            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                try{
                    FileOutputStream fos = new FileOutputStream(filename); 
                    ObjectOutputStream oos = new ObjectOutputStream(fos); 
                    
                    // FIX ME: write the Program Of Study object to the file
                    oos.writeObject(courses); 
                    oos.flush(); 
                    oos.close(); 
                }catch(Exception ex){
                    // provide appropriate messages for possible errors
                }
            }       

    }
}
