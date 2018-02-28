package project1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**********************************************************************
 * This is a basic program that runs a StopWatch with the regular 
 * functions
 * 
 * @author Atone Joryman
 * @version Fall 2017
 **********************************************************************/
public class StopWatch implements Serializable {

/** keep record of milliseconds*/
private int milli;

/**keep record of minutes */
private int min;

/**keep record of seconds */
private int sec;

/** a flag for suspending methods*/
private static boolean cap;

/** for loading a file*/
private static final long serialVersionUID = 1L;

/***********************************************************************
 * Getter method to keep record of milliseconds
 * 
 * @return number of milliseconds
 **********************************************************************/
public int getMilliseconds() {
	return milli;
}

/***********************************************************************
 * Setter method to mutate the number of milliseconds
 * 
 * @param to enter milliseconds under 1000
 **********************************************************************/
public void setMilliseconds(int milli) {
	this.milli = milli;
}

/***********************************************************************
 * Getter method to keep record of minutes
 * @return number of minutes
 **********************************************************************/
public int getMinutes() {
	return min;
}

/***********************************************************************
 * Setter method to mutate the number of minutes
 * 
 * @param to enter minutes
 **********************************************************************/
public void setMinutes(int min) {
	this.min = min;
}

/***********************************************************************
 * Getter method to keep record of seconds
 * 
 * @return number of seconds
 **********************************************************************/
public int getSeconds() {
	return sec;
}

/***********************************************************************
 * Setter method to mutate the number of seconds
 * 
 * @param to enter seconds
 **********************************************************************/
public void setSeconds(int sec) {
	this.sec = sec;
}

/***********************************************************************
 * Constructor that will take in the input of a time 
 * 
 * @param String type write time in the format: MM:mm:ss
 * @throws IllegalArgumentException for number and letters
 **********************************************************************/
public StopWatch(String startTime) {
	
	//split number with a : to format a stopwatch
	String[] s= startTime.split(":");
	if(s.length ==3){ //if array is of length 3
		//if negative throw  
		if(startTime.contains("-")){ 
		throw new IllegalArgumentException("Positive number please");
		}
		//catches if string has letters
		if (startTime.matches(".*[a-z].*")) { 
		    throw new IllegalArgumentException("No letters");
		}
		
		
		
		//make string into integer 
	min= Integer.parseInt(s[0]);
	sec= Integer.parseInt(s[1]);
	milli= Integer.parseInt(s[2]);
	
	if(sec >59){
		int minTmp=0, newSec=0;
		//if divisible by 60 and no remainder
		if(sec % 60 == 0){
			minTmp = sec/59;
			this.min += minTmp;
		}
		
		//if remainder not zero then compute new time
		else if(sec%60 != 0){
			minTmp = sec/60;
			newSec = sec%60;	
			this.min += minTmp;
			this.sec = newSec;
		}
		
		else
		{
			throw new IllegalArgumentException("Can not do!");
		}
	}
	//if greater than maximum milliseconds computer to seconds or 
			//minutes
			if(milli>999){
				int minTmp = 0,secTmp=0, newMilli=0;
				//if even minute
				if(milli % 60000 == 0){
					minTmp = milli/60000;
					this.min += minTmp;
				}
				
				//if remainder not zero then computer new time
				else if(milli%1000 != 0){
					secTmp = milli/1000;
					newMilli = milli%1000;	
					this.sec += secTmp;
					this.milli = newMilli;
				}
				//if greater than 60 seconds
				else if(milli>60000){
					minTmp= milli/60000;
					secTmp = milli/1000;
					newMilli = milli%1000;
					this.min+= minTmp;
					this.sec = secTmp;
					this.milli = newMilli;
				}
				
				else
				{
					throw new IllegalArgumentException("Can not do!");
				}
	}
	}
	else if (s.length ==2){
		
		if(startTime.contains("-")){
		throw new IllegalArgumentException("Positive number please");
		}
		
		if (startTime.matches(".*[a-z].*")) { 
		    throw new IllegalArgumentException("No letters");
		}
		sec= Integer.parseInt(s[0]);
		milli= Integer.parseInt(s[1]);
		
		//can not be greater than 59
				if(sec>59){
				throw new IllegalArgumentException("Only second and "
							+ "milliseconds");
				}
				
		//if greater than maximum milliseconds computer to seconds or 
		//minutes
				if(milli>999){
					int minTmp = 0,secTmp=0, newMilli=0;
					//if even minute
					if(milli % 60000 == 0){
						minTmp = milli/60000;
						this.min += minTmp;
					}
					
					//if remainder not zero then computer new time
					else if(milli%1000 != 0){
						secTmp = milli/1000;
						newMilli = milli%1000;	
						this.sec += secTmp;
						this.milli = newMilli;
					}
					//if greater than 60 seconds
					else if(milli>60000){
						minTmp= milli/60000;
						secTmp = milli/1000;
						newMilli = milli%1000;
						this.min += minTmp;
						this.sec = secTmp;
						this.milli = newMilli;
					}
					
					else
					{
					throw new IllegalArgumentException("Can not do!");
					}
				}
	}
	
	else if (s.length ==1){
		if(startTime.contains("-")){
		throw new IllegalArgumentException("Positive number please");
		}
		
		if (startTime.matches(".*[a-z].*")) { 
		    throw new IllegalArgumentException("No letters");
		}
		milli = Integer.parseInt(s[0]);
		
		//can not be greater than 999
				if(milli>999){
				throw new IllegalArgumentException("Only milliseconds");
				}
	}
	
	else{
		//any other option would be invalid
	throw new IllegalArgumentException("Not a valid entry");
	}
//	this.milli = milli;
//	this.min = min;
//	this.sec = sec;
}
/***********************************************************************
 * Default constructor that sets the StopWatch to zero and the flag to 
 * false
 **********************************************************************/
public StopWatch(){

	//set to zero and false
	this.min= 0;
	this.sec= 0;
	this.milli= 0;
	cap= false;
}

/***********************************************************************
 * A constructor that initializes the instance variables with the 
 * provided values, and if over max convert
 * 
 * @param int type for minutes
 * @param int type for seconds
 * @param int type for milliseconds
 * @throws IllegalArgumentException
 **********************************************************************/
public StopWatch(int minutes, int seconds, int milliseconds){
	
	//if seconds inputed is greater than 59 compute to minutes
		if(seconds >59){
			int minTmp=0, minTmp2=0, newSec=0;
			//if divisible by 60 and no remainder
			if(seconds % 60 == 0){
				minTmp = seconds/60;
				this.min += minTmp;
			}
			
			//if remainder not zero then computer new time
			else if(seconds%60 != 0){
				minTmp = seconds/60;
				newSec = seconds%60;	
				this.min += minTmp;
				this.sec = newSec;
			}
			
			else
			{
				throw new IllegalArgumentException("Can not do!");
			}
			
		}
		//if greater than maximum milliseconds computer to seconds or 
		//minutes
		else if(milliseconds>999){
			int minTmp = 0,secTmp=0, newMilli=0;
			//if even minute
			if(milliseconds % 60000 == 0){
				minTmp = milliseconds/60000;
				this.min += minTmp;
			}
			
			//if remainder not zero then computer new time
			else if(milliseconds%1000 != 0){
				secTmp = milliseconds/1000;
				newMilli = milliseconds%1000;	
				this.sec += secTmp;
				this.milli = newMilli;
			}
			//if greater than 60 seconds
			else if(milliseconds>60000){
				minTmp= milliseconds/60000;
				secTmp = milliseconds/1000;
				newMilli = milliseconds%1000;
				this.min += minTmp;
				this.sec = secTmp;
				this.milli = newMilli;
			}
			
			else
			{
				throw new IllegalArgumentException("Can not do!");
			}
				}
		//negative number throw
		else if(milliseconds<0 || minutes<0 || seconds < 0){
		throw new IllegalArgumentException("apply an positive number "
					+ "greater than zero");
		}
		//no other case
		else{
			this.min=minutes;
			this.sec= seconds;
			this.milli= milliseconds;
			//no suspend
			cap=false;
		}
	}
/***********************************************************************
 * A constructor that initializes the instance variables with the 
 * provided values, and if over max convert
 * 
 * @param int type for seconds
 * @param int type for milliseconds
 * @throws IllegalArgumentException
 **********************************************************************/
	public StopWatch(int seconds, int milliseconds){
		//can not be greater than 59
		if(seconds>59){
			throw new IllegalArgumentException("Only second and "
					+ "milliseconds");
		}
		
		//if greater than maximum milliseconds computer to seconds or 
		//minutes
		if(milliseconds>999){
			int minTmp = 0,secTmp=0, newMilli=0;
			//if even minute
			if(milliseconds % 60000 == 0){
				minTmp = milliseconds/60000;
				this.min += minTmp;
			}
			
			//if remainder not zero then computer new time
			else if(milliseconds%1000 != 0){
				secTmp = milliseconds/1000;
				newMilli = milliseconds%1000;	
				this.sec += secTmp;
				this.milli = newMilli;
			}
			//if greater than 60 seconds
			else if(milliseconds>60000){
				minTmp= milliseconds/60000;
				secTmp = milliseconds/1000;
				newMilli = milliseconds%1000;
				this.min+= minTmp;
				this.sec = secTmp;
				this.milli = newMilli;
			}
			
			else
			{
				throw new IllegalArgumentException("Can not do!");
			}
		}
		//negative number
		if(milliseconds<0 || seconds < 0){
		throw new IllegalArgumentException("apply an positive number "
					+ "greater");
		}
		
		else{
			this.sec= seconds;
			this.milli= milliseconds;
		}	
	}
	
/***********************************************************************
 * A constructor that initializes the instance variables with the 
 * provided values, and if over max convert
 * 
 * @param int type for milliseconds
 * @throws IllegalArgumentException
**********************************************************************/
	public StopWatch(int milliseconds) {
		//can not be greater than 999
		if(milliseconds>999){
			throw new IllegalArgumentException("Only milliseconds");
		}
		//negative number
		if(milliseconds<0){
		throw new IllegalArgumentException("apply an positive number "
					+ "greater");
		}
		//last case
		else{
			this.milli= milliseconds;
		}	
	}
	
/***********************************************************************
 * A method that returns true if “this” StopWatch object is exactly the 
 * same as the other StopWatch object; this.minutes equals other.minutes
 *  and this.seconds equals other.seconds and so on.
 *  
 * @param equals to another StopWatch
 * @return true if all instance are same
 * @return false if instance differ
 **********************************************************************/
	public boolean equals(StopWatch other){
		//all must be the same
        if ((this.min == other.min) &&
                (this.sec == other.sec) &&
                (this.milli == other.milli))
            return true;
        else 
            return false;
	}
/***********************************************************************
 * A method that returns true if “this” StopWatch object is exactly 
 * the same as the other StopWatch object. 
 *  
 * @param equals to another StopWatch
 * @return true if all instance are same
 * @return false if instance differ
***********************************************************************/
	public boolean equals (Object other) {
		//for object
        if (other instanceof StopWatch) {
            StopWatch temp = (StopWatch) other;
            //all must be the same
            if ((this.min == temp.min) &&
                    (this.sec == temp.sec) &&
                    (this.milli == temp.milli))
                return true;
            else 
                return false;
        }
        //if nothing false
        return false;
	}
/***********************************************************************
* Compare two StopWatches for if they are equal
* @param s1 of StopWatch
* @param s2 of StopWatch
* @return true if equal
* @return false if not
***********************************************************************/
	public static boolean equals (StopWatch s1, StopWatch s2) {
		if(cap){//suspend off
		if (s1 == null || s2 == null) {
			return false;
		}
		//minute
		if (s1.min != s2.min)
			return false;
		//second
		if (s1.sec != s2.sec)
			return false;
		//millisecond
		if (s1.milli != s2.milli)
			return false;
		
		return true;
		}
		return false;
	}
/***********************************************************************
 *A method that returns 1 if “this” StopWatch object is greater than the
 * other StopWatch object; returns -1 if the “this” StopWatch object is
 *  less than the other StopWatch; returns 0 if the “this” StopWatch 
 *  object is equal to the other StopWatch object
 * 
 * @param Compare to other stopwatch
 * @return -1 when null or less then
 * @return 1 when instance are greater than
 * @return 0 when instance are equal to
 **********************************************************************/
	public int compareTo(StopWatch other) {
		// null 
		if(other==null){
			return -1;
		}
		//greater than
		if(this.min>other.min|| this.sec> other.sec 
				|| this.milli > other.milli){
			return 1;
		}
		//less than
		if(this.min<other.min|| this.sec< other.sec 
				|| this.milli < other.milli){
			return -1;
		}
		//equal to
		if(this.min==other.min|| this.sec== other.sec 
				|| this.milli == other.milli){
			return 0;
		}
		//otherwise 0
		return 0;
	}
/***********************************************************************
 * A method that adds the number of milliseconds to “this” StopWatch 
 * object
 * 
 * @param add in milliseconds
 * @return flag
 * @throws IllegalArgumentException
 **********************************************************************/
	public void add (int milliseconds) {
		//should I catch if it is character??
		
		if(cap){
			//suspend
			return;
		}
		
		if(milliseconds<0){
			throw new IllegalArgumentException("Use a positive "
					+ "integer to increase!!");
		}
		
//		if(milliseconds!=(int)milliseconds){
//			throw new IllegalArgumentException("Do not use a letter!!");
//		}
		
//		String aString=" ";
//		if(!Character.isDigit(aString.charAt(milliseconds))){
//			throw new IllegalArgumentException("Do not use a letter!!");
//		}
		
		//inc up to the milliseconds
		for(int i=0;i<milliseconds;i++){
		
		inc();
		}
	}
/***********************************************************************
 * A method that adds StopWatch other to the “this” StopWatch.  
 * 
 * @param other StopWatch to add
 * @return flag
 **********************************************************************/
	public void add (StopWatch other){
		//make conversions
		int milliTmp = 0;
		milliTmp=other.min*60*1000;
		milliTmp+=other.sec*1000;
		milliTmp+= other.milli;
		//this.milli+=milliTmp;//do not change this setter method
		add(milliTmp);
	} 
/***********************************************************************
 * A method that increments the “this” StopWatch by 1 millisecond
 * 
 * @return flag
 **********************************************************************/
	public void inc(){
		//case under the boundary
		if(this.milli<999){
		  this.milli++;
		}
		else{
			//other case is new second
			this.sec++;
			//keep track of seconds to minutes
			if(this.sec>59){
				this.sec=0;
				this.min++;
			}
			//milliseconds should reset
			this.milli=0;
		}
	}
	
/***********************************************************************
 * A method that subtracts the number of milliseconds from “this” 
 * StopWatch object.  
 * 
 * @param subtract milliseconds
 * @return flag
 **********************************************************************/
	public void sub (int milliseconds){ 
		//flag yes
		if(cap){
			return;
		}
		
//		if(!(Character.isDigit(milliseconds))){
//			throw new IllegalArgumentException("Do not use a letter!!");
//		}
		
		//will not take a negative subtraction
		if(milliseconds<0){
			throw new IllegalArgumentException("Use a positive "
					+ "integer to decrease!!");
		}
		//decrement to zero
		for(int i=milliseconds;i>0;i--){
			dec();
			}
	}
	
/***********************************************************************
 *A method that subtracts StopWatch other from the “this” StopWatch.   
 * 
 * @param subtract the object of type StopWatch 
 **********************************************************************/
    public void sub (StopWatch other){
    	int milliTmp = 0;
		milliTmp=other.min*60*1000;
		milliTmp+=other.sec*1000;
		milliTmp+= other.milli;
		//this.milli+=milliTmp;//do not change this setter method
		sub(milliTmp);
	}
    
/***********************************************************************
 * A method that decrements the “this” StopWatch by 1 millisecond
 * 
 * @throw IllegalArgumentException
 **********************************************************************/
	public void dec(){
		//case still positive then decrease
		if(this.milli>0){
		this.milli--;
		}
		
		//in going backwards be careful
		else { 
			//when second hits zero and min is still above
			if(this.sec==0 && this.min>0){
			this.min--;
		    this.sec=59;
		    this.milli=999;
			}
			//when milli hits zero and sec is above zero
			if(this.milli == 0 && this.sec>0){
				this.sec--;
				this.milli=999;
						}
			
	}
		//if all negative, format 00:00:000
		if(this.min< 0 && this.sec < 0 && this.milli < 0){
			//milli would decrease everything
			throw new IllegalArgumentException("Can not "
					+ "decrease below this point!");
		}
		}
/***********************************************************************
 * Method that returns a string that represents a StopWatch with the 
 * following format: “1:06:010”.  
 * 
 *@return String s, which is the format for timer 
 **********************************************************************/
	public String toString(){
		//empty string
		//no limit for minute
		String s= "";
		//get min first and work down
		s+= min;
		//working with zeros a lot
		if(sec<10){
			s+= ":"+"0"+ sec;
		}// higher than 9
		else{
		s+=":"+sec;
		}
		//handling all zeros
		if(milli<10){
			s+=":"+ "00"+ milli;
		}
		else if(milli<100){
			s+=":"+"0"+ milli;
		}
		//higher than 99
		else{
			s+= ":"+ milli;
		}
		
		return s;
		
	}
/***********************************************************************
 * A method that saves the “this” StopWatch to a file
 * 
 * @param fileName to be saved
 **********************************************************************/
	
public void save(String fileName){
	try {
		FileOutputStream fos = new FileOutputStream(fileName);
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
}
public void load(String fileName){
	try {
		FileInputStream fin = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fin);

		StopWatch tmp = (StopWatch) ois.readObject();
		//assign the loaded file to the StopWatch instance variables
		this.min = tmp.min;
		this.sec = tmp.sec;
		this.milli = tmp.milli;
		ois.close();
		//catch exceptions
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}

/***********************************************************************
 *This method suspends all operations (add, sub, inc, dec) when the 
 *flag = true. Otherwise all StopWatch objects can be mutated.  
 * 
 * @param flag whether or not a method should be pointed to or not
 **********************************************************************/
public static void suspend(boolean flag){
	cap = flag;
}



}
