package lab10;

import java.io.*;
import java.util.*;
import javax.swing.*;
/********************************************************
 * Represents a Program of Study, 
 * a list of courses taken and planned, for an individual student.
 * 
 * @author Java Foundations
 * @author Scott Grissom (adapted)
 * @version 4.0
 *******************************************************/
public class ProgramOfStudy extends AbstractListModel{

    /** List can be implemented as an ArrayList or LinkedList */
    private List<Course> courseList;

    /****************************************************
     * Constructs an initially empty Program of Study.
     ***************************************************/
    public ProgramOfStudy(){
        courseList = new LakerList();
    }

    /****************************************************
     * Adds item to end of list
     ***************************************************/    
     public void add(Course a) {
        courseList.add(a);
        fireContentsChanged(this, 0, courseList.size());
    }
    
    /****************************************************
     * Removes item at index i
     ***************************************************/    
    public Course remove(int i) {
        Course c = courseList.remove(i);
        fireContentsChanged(this, 0, courseList.size());
        return c;
    }

    /******************************************************
     * @overrides for AbstractListModel
     * @returns size of the list
     *****************************************************/
    public int getSize() {
        // FIX ME: Complete this method
        // return 0;
        return courseList.size();
    }
    
    /******************************************************
     * @overrides for AbstractListModel
     * @returns item at index i
     *****************************************************/  
    public Object getElementAt(int i) { 
        // FIX ME: Complete this method
        // return null;        
        return courseList.get(i);
    }   

    /******************************************************
     * Finds and returns the course matching the specified 
     * prefix and number.
     * 
     * @param prefix the prefix of the target course
     * @param number the number of the target course
     * @return the course, or null if not found
     *****************************************************/
    public Course find(String prefix, int number){
        for (Course course : courseList)
            if (prefix.equals(course.getPrefix()) &&
                    number == course.getNumber())
                return course;

        return null;
    }

    /*********************************************************
     * Adds the specified course after the target course. 
     * Does nothing if either course is null or if the target 
     * is not found.
     * 
     * @param target insertion point
     * @param newCourse the course to add
     ********************************************************/
    public void addCourseAfter(Course target, Course newCourse){
        if (target == null || newCourse == null)
            return;

        int targetIndex = courseList.indexOf(target);
        if (targetIndex != -1){
            courseList.add(targetIndex + 1, newCourse);
            fireContentsChanged(this, 0, courseList.size());
        }
    }

    /*******************************************************
     * Replaces the specified target course with the new 
     * course. Does nothing if either course is null or if 
     * the target is not found.
     * 
     * @param target the course to be replaced
     * @param newCourse the new course to add
     ******************************************************/
    public void replace(Course target, Course newCourse){

    }

    /**********************************************************
     * @return a string representation of the Program of Study
     *********************************************************/
    public String toString(){
        String result = "";
        for (Course course : courseList)
            result += course + "\n";
        return result;
    }

    public void fillWithTestData(){
        add(new Course("CIS", 162, "Computer Science I", "A-"));
        add(new Course("COM", 201, "Oral Communication", "A"));
        add(new Course("MTH", 225, "Discrete Structures"));
        add(new Course("CIS", 163, "Computer Science II"));
        add(new Course("STA", 215, "Probability and Statistics", "C"));
    }

    public void addRandomCourse(){
        Random rand = new Random();
        switch(rand.nextInt(7)){
            case 0: add(new Course("CIS", 241, "Systems Programming"));
                    break;
            case 1: add(new Course("CIS", 350, "Software Engineering"));
                    break;
            case 2: add(new Course("CIS", 353, "Database"));
                    break;
            case 3: add(new Course("CIS", 263, "Algorithms"));
                    break;
            case 4: add(new Course("CIS", 351, "Compute Organization"));
                    break;  
            case 5: add(new Course("CIS", 452, "Operating Systems"));
                    break; 
            case 6: add(new Course("CIS", 457, "Network Communication"));
                    break;                     
    }
         fireContentsChanged(this, 0, courseList.size());
}
}