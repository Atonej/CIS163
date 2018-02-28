package labexam;

import java.io.Serializable;
import java.util.Comparator;
/**
 * 
 * @author jorymana
 *
 */

public class Student implements Serializable, Comparator<Student> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double gpa;

// Create all needed methods (e.g., constructors, getter/setters,  etc. ) as needed for TO DOs
public Student(String name, double gpa){
	super();
	this.name = name;
	this.gpa = gpa;
}
	public String toString () {
		return "\nName : " + name + " GPA: " + gpa;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public static Comparator<Student> COMPARE_BY_NAME = new Comparator<Student>() {

		@Override
		public int compare(Student o1, Student o2) {
			// TODO Auto-generated method stub
			return o1.getName().compareTo(o2.getName());
		}
		
	};
	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
