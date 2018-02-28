package labexam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * 
 * @author jorymana
 *
 */
public class LabExam {
	private ArrayList<Student> list;   // Change this code for TO DO 4
	private String name;
	private double gpa;

	public LabExam () throws IOException {
		list = new ArrayList<Student>();
		// Your Code goes here for TO DO 1
		Student name1 = new Student("Cathy", 4.0);
		Student name2 = new Student("Roger", 1.0);
		Student name3 = new Student ("Gregg", 3.0);
		Student name4 = new Student("Scott", 2.0);
		Student name5 = new Student("Ginger", 2.5);
		
		list.add(name1);
		list.add(name2);
		list.add(name3);
		list.add(name4);
		list.add(name5);
		
//		list.addAfter(name1);
//		list.addAfter(name2);
//		list.addAfter(name3);
//		list.addAfter(name4);
//		list.addAfter(name5);
	
	}
	
	public void saveAsTxt(String fileName) throws FileNotFoundException {
//		Student name1 = new Student("Cathy", 4.0);
//		Student name2 = new Student("Roger", 1.0);
//		Student name3 = new Student ("Gregg", 3.0);
//		Student name4 = new Student("Scott", 2.0);
//		Student name5 = new Student("Ginger", 2.5);
//		
//		list.add(name1);
//		list.add(name2);
//		list.add(name3);
//		list.add(name4);
//		list.add(name5);
		PrintWriter out = new PrintWriter(fileName);
		out.println(list.size());
		for(int i=0; i< list.size(); i++) {
			Student temp = (Student)list.get(i);
			Object temp2 = list.get(i);
			out.println(temp.getName());
			out.println(temp.getGpa());
		}
		out.close();
	}
	
	public void loadFromTxt(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
		String temp = reader.readLine();
		Scanner scnr = new Scanner(temp);
		int count = scnr.nextInt();
		
		list.clear();
		for(int i =0; i< count; i++) {
			String name = reader.readLine();
			String gpa = reader.readLine();
			double newGPA = Double.parseDouble(gpa);
			
//			Student s = new Student (name, newGPA);
//			list.add(s);
//			
			
		}
		System.out.println("Here " + list + "There");
		reader.close();
	
	}

	public static void main (String[] args) throws IOException {
		(new LabExam()).exam();   // This will avoid a static method 
	}

	public void  exam() throws IOException {
		LabExam test = new LabExam();
		
		test.saveAsTxt("test.txt");
		test.loadFromTxt("test.txt");
		// Your Code goes here for TO DO 2
		Collections.sort(test.list, Student.COMPARE_BY_NAME);
		//int i =0;
		//while( i < list.size()) {
		System.out.println("Sorted by GPA: " + test.list + "\n");
		//i++;
		//}
		Collections.sort(test.list, (one, other) -> 
			Double.toString(one.getGpa()).compareTo(Double.toString(other.getGpa())));
		
		// Your Code goes here for TO DO 3
		System.out.println("Sorted by Names: " + test.list + "\n");
	}
	
//	Sorted by GPA: [
//	Name : Cathy GPA: 4.0, 
//	Name : Ginger GPA: 2.5, 
//	Name : Gregg GPA: 3.0, 
//	Name : Roger GPA: 1.0, 
//	Name : Scott GPA: 2.0]
//
//	Sorted by Names: [
//	Name : Roger GPA: 1.0, 
//	Name : Scott GPA: 2.0, 
//	Name : Ginger GPA: 2.5, 
//	Name : Gregg GPA: 3.0, 
//	Name : Cathy GPA: 4.0]
}
