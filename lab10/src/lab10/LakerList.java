package lab10;

import java.util.*;
/**********************************************************
 * A custom List that maintains Courses in sorted order
 * by course number.
 *********************************************************/
public class LakerList extends ArrayList<Course>{

    // For testing purposes
    public static void main(String[] args){
        LakerList lakers = new LakerList();
        lakers.add(new Course("CIS", 452, "Course"));
        lakers.add(new Course("CIS", 451, "Course"));
        lakers.add(new Course("CIS", 450, "Course"));
        for(Course c:lakers)
             System.out.println(c);
    }
    
    public LakerList(){
        super();
    }

    // Insert Course using course number for ordering
    public boolean add(Course item){
        // FIX ME: insert Course into sorted position
        // Use the Course compareTo method to determine order
    	
    	int loc = 0;
    	
    	if (size() != 0){
    		while(loc < size()){
    			if (item.compareTo(get(loc)) <= 0){
    				break;
    			}
    			loc++;
    		}
    	}
    	
    	super.add(loc, item);
    	
    	return true;
    }
    
    // not supported for an ordered List
    public void add(int index, Course item){
        throw new UnsupportedOperationException();
    };
}
