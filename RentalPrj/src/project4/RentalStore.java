package project4;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;


/***********************************************************************
 * Main class used for the sum of all classes, incorporates things in 
 * relation to DVD and sends information the GUI.
 * 
 * @author Atone Joryman
 * @version Summer 2017
 **********************************************************************/
public class RentalStore extends AbstractListModel  {

//apply the list, ArrayList, LinkedList, or Double Linked list
	private static ArrayList<DVD> rentalStore;

/***********************************************************************
 * Constructor for a new implied list of some kind
 **********************************************************************/	
	public RentalStore(){
		rentalStore = new ArrayList<DVD>();
	}

/***********************************************************************
 * Overrides the method under abstract list model to get an element
 * @param the element
 * @return list at this index
 **********************************************************************/
	@Override
	public Object getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return rentalStore.get(arg0);
	}
	
/***********************************************************************
 * Overrides the method under abstract list model to get a size
 * @return size of list
 **********************************************************************/
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return rentalStore.size();
	}
	
/**********************************************************************
 * Adds item to end of list
 * @param element of type DVD
***********************************************************************/    
     public void add(DVD a) {
        rentalStore.add(a);
        fireContentsChanged(this, 0, rentalStore.size());
    }
     
/***********************************************************************
* Remove item at some index
* @param element at this index
* @return the changed list
***********************************************************************/    
      public DVD remove(int i) {
        DVD d = (DVD) rentalStore.remove(i);
        //from abstract list model which tells that a list has been
        // modified
         fireContentsChanged(this, 0, rentalStore.size());
         return d;
     }
      
/******************************************************************
* add method for blue rays
* @param element the blue ray to be added
 *****************************************************************/
  	public void add(BlueRay b){
  		rentalStore.add(b);
  		fireContentsChanged(this, 0, rentalStore.size());
  	}
  	
 /******************************************************************
 * add method for games
 * @param element the game to be added
 *****************************************************************/
  	public void add(Game g){
  		rentalStore.add(g);
  		fireContentsChanged(this, 0, rentalStore.size());
  	}
  	
/******************************************************************
* getter method for the rental store of a list
* @return list of the rental store
*****************************************************************/  	
    public static ArrayList<DVD> getRentalStore() {
		return rentalStore;
	}
      
/******************************************************************
* updates the jlist in the gui
*****************************************************************/
  	public void update(){
  		this.fireContentsChanged(this, 0, rentalStore.size() - 1);
  	}

//	/*********************************************************
//       * Adds the specified course after the target course. 
//       * Does nothing if either course is null or if the target 
//       * is not found.
//       * 
//       * @param target insertion point
//       * @param newCourse the course to add
//       ********************************************************/
//      public void addRentalAfter(DVD target, DVD newDVD){
//          if (target == null || newDVD == null)
//              return;
//
//          int targetIndex = rentalStore.indexOf(target);
//          if (targetIndex != -1){
//              rentalStore.add(targetIndex + 1, newDVD);
//              fireContentsChanged(this, 0, rentalStore.size());
//          }
//      }
 
//	public void setBought(GregorianCalendar newb){
//		bought = newb;
//	}
//
//	
//	public void setDaysLate(int newd){
//		daysLate= newd;
//	}
//	
//	public DVD getDVD(){
//		return rentedDVD;
//	}
//	
//	public GregorianCalendar getBought(){
//		return bought;
//	}
//
//	
//	public int getDaysLate(){
//		return daysLate;
//	}
//	
//	public double calculateLateFee(){
//		return rentedDVD.calcLateFees(daysLate);
//	}
//
//	public static void main(String[] args){
//		RentalStore store = new RentalStore();
//		
//		
//	}

}
