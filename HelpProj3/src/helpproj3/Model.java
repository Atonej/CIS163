package helpproj3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel {

	private ArrayList<Book> books;  // TO DO 2: change this to an ArrayList
	private String[] getCol;
	
//	private String getCol(){
//		return getCol
//	}

	public Model() {
		books = new ArrayList<Book>();
		books.add(new Book(1000, "Book1"));
		books.add(new Dictionary(1000, "Dict1", "French"));
		books.add(new Dictionary(2000, "Dict2", "English"));
	}

	public int getRowCount() {
		return books.size();
	}

	public int getColumnCount() {
		return 1; 
	}
	
	public void add () {
		// TO DO 1: add on two more Books. Call this method from the GUI somehow. 
		books.add(new Book(1000, "Book1"));
		//fireTableRowsInserted(1, 5);
		fireTableDataChanged();
	}

	public Object getValueAt(int row, int col) {
		
	// TO DO 3: Notice the col is not be used, that is, there are 3 columns not 1, 
	//	     change the getColumnCount method to return 3 so you can utilize a JTable 
	//	     more effectively. (This one is hard to do, you may skip and goto TO DO 4
	//	return " Row: " + row + " Column: " + col;
		 
		String temp;
		temp = books.get(row).name + " " + books.get(col).pages;
		if (books.get(row) instanceof Dictionary) {
			temp += (" " + ((Dictionary)books.get(row)).getType());
		}
			
		return temp;
	}

	public void saveAsSerialized(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			//write to this streaming output
			oos.writeObject(this);
			//do not keep up
			oos.close();
			//no file
		} catch (FileNotFoundException error) {
			System.out.println("File Not Found");
			//something else
		} catch (IOException error) {
			System.out.println("Oops! Something went wrong.");
		}
		// TO DO 4:  save the file Serialized
	}

	public void loadFromSerialized(String filename) {
		try {
			FileInputStream fin = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fin);

			Model tmp = (Model) ois.readObject();
			//assign the loaded file to the StopWatch instance variables
			books.addAll((Collection<? extends Book>) tmp);
			ois.close();
			//catch exceptions
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// TO DO 5: Load the from Serialized 
	}
}

