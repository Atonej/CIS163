package project44;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;

/**********************************************************************
 * rental store that holds the list of rented DVDs, 
 * used in RentalStoreGUI
 * @author Gregory Huizenga
 * @version 7/26/2017
 *********************************************************************/
public class RentalStore extends AbstractListModel implements Serializable{

	/** the list containing the rented dvds */
	private MyDoubleLinkedList<DVD> storeList;
	
	/******************************************************************
	 * default constructor for RentalStore
	 *****************************************************************/
	public RentalStore(){
		storeList = new MyDoubleLinkedList<DVD>();
	}
	
	/******************************************************************
	 * add method for dvds
	 * @param item the dvd to be added
	 *****************************************************************/
	public void add(DVD item){
		storeList.add(item);
	}
	
	/******************************************************************
	 * add method for blue rays
	 * @param item the blue ray to be added
	 *****************************************************************/
	public void add(BlueRay item){
		storeList.add(item);
	}
	
	/******************************************************************
	 * add method for games
	 * @param item the game to be added
	 *****************************************************************/
	public void add(Game item){
		storeList.add(item);
	}
	
	/******************************************************************
	 * removes the item at the specified index from the list
	 * @param index the index of the item to be removed
	 * @return temp the element that was removed
	 *****************************************************************/
	public DVD remove(int index){
		DVD temp = (DVD) storeList.get(index);
		storeList.remove(index);
		return temp;
	}
	
	@Override
	/******************************************************************
	 * returns the element stored at the given index
	 * @return index index of item
	 *****************************************************************/
	public Object getElementAt(int index) {
		return storeList.get(index);
	}

	@Override
	/******************************************************************
	 * returns the current size of the list
	 * @return size size of list
	 *****************************************************************/
	public int getSize() {
		return storeList.size();
	}
	
	/******************************************************************
	 * updates the jlist in the gui
	 *****************************************************************/
	public void update(){
		this.fireContentsChanged(this, 0, storeList.size() - 1);
	}
	
	/******************************************************************
	 * saves the current list to a file with specified name
	 * @param filename the name to be saved to
	 * @throws FileNotFoundException 
	 *****************************************************************/
	public void saveToFile(String filename) throws FileNotFoundException{
		try {
			FileOutputStream outputF = new FileOutputStream(filename);
			ObjectOutputStream outputO = new ObjectOutputStream(outputF);
			
			outputO.writeObject(storeList);
			outputO.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/******************************************************************
	 * loads to the current list from a file with specified name
	 * @param filename the name to be loaded from
	 * @throws FileNotFoundException 
	 *****************************************************************/
	public void loadFromFile(String filename) throws FileNotFoundException{
		try {
			FileInputStream inputF = new FileInputStream(filename);
			ObjectInputStream inputO = new ObjectInputStream(inputF);
			
			storeList = (MyDoubleLinkedList<DVD>) inputO.readObject();
		}catch(IOException e){
			throw new FileNotFoundException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/******************************************************************
	 * saves list of rentals to a text file with specified name
	 * @param filename the name to save to
	 * @throws FileNotFoundException 
	 *****************************************************************/
	public void saveToText(String filename) throws FileNotFoundException{
		PrintWriter out = new PrintWriter(filename);
		out.println(storeList.size());
		for(int i = 0; i < storeList.size(); i++){
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			DVD temp = (DVD)storeList.get(i);
			Object temp2 = storeList.get(i);
			
			out.println(storeList.get(i).getClass());
			out.println(temp.getNameOfRenter());
			out.println(temp.getTitle());
			out.println(sdf.format(temp.getBought().getTime()));
			out.println(sdf.format(temp.getDueBack().getTime()));
			
			if (storeList.get(i).getClass().toString().contains("Game")){
				out.println(((Game) temp2).getPlayer());
			}
		}
		out.close();
	}
	
	public void loadFromText(String filename) throws IOException, ParseException{

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String temp = reader.readLine();
		Scanner scnr = new Scanner(temp);
		int count = scnr.nextInt();
		
		storeList.clear();
		
		for(int i = 0; i < count; i++){
			String type = reader.readLine();
			String name = reader.readLine();
			String title = reader.readLine();
			String rentDate = reader.readLine();
			String dueDate = reader.readLine();
			String player;
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date rDate = sdf.parse(rentDate);
			Date dDate = sdf.parse(dueDate);
			
			GregorianCalendar reDate = new GregorianCalendar();
			GregorianCalendar duDate = new GregorianCalendar();
			reDate.setTime(rDate);
			duDate.setTime(dDate);
			
			if (type.contains("Game")){
				player = reader.readLine();
				Game game = new Game(reDate, duDate, title, name, player);
				storeList.add(game);
			}else if (type.contains("BlueRay")){
				BlueRay blu = new BlueRay(reDate, duDate, title, name);
				storeList.add(blu);
			}else{
				DVD dvd = new DVD(reDate, duDate, title, name);
				storeList.add(dvd);
			}
			
			
		}
		reader.close();
		}
	}


