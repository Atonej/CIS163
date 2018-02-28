package changeJar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/***********************************************************************
 * Graphical representation of multiple Change jars containing only
 * quarters, dimes, nickels and pennies.
 * 
 * @author Atone Joryman
 * @version Summer 2017
 ***********************************************************************/

public class ChangeJar implements java.io.Serializable {
	/** number of quarters */
	private int quarters;

	/** `number of dimes */
	private int dimes;

	/** number of nickels */
	private int nickels;

	/** number of pennies */
	private int pennies;

	/** check if there is an amount and what to control */
	private static boolean cap;

	/** serialVersionUID for loading file */
	private static final long serialVersionUID = 1L;

/***********************************************************************
 * Default constructor for resetting money to zero
***********************************************************************/

	public ChangeJar() {
		this.quarters = 0;
		this.dimes = 0;
		this.nickels = 0;
		this.pennies = 0;
		//for suspend keep off
		this.cap = false;
	}

/***********************************************************************
 * Creates the getter for quarters
 * @return number of quarters
***********************************************************************/
	public int getQuarters() {
		return quarters;
	}

/***********************************************************************
 * Creates the setter for quarters
 * @param number of quarters
***********************************************************************/
	public void setQuarters(int quarters) {
		this.quarters = quarters;
	}

/***********************************************************************
 * Creates the getter for dimes
 * @return number of dimes
***********************************************************************/
	public int getDimes() {
		return dimes;
	}

/***********************************************************************
 * Creates the setter for dimes
 * @param number of dimes
***********************************************************************/
	public void setDimes(int dimes) {
		this.dimes = dimes;
	}

/***********************************************************************
 * Creates the getter for nickels
 * @return number of nickels
***********************************************************************/
	public int getNickels() {
		return nickels;
	}

/***********************************************************************
 * Creates the setter for nickels
 * @param number of nickels
***********************************************************************/
	public void setNickels(int nickels) {
		this.nickels = nickels;
	}

/***********************************************************************
 * Creates the getter for pennies
 * @return number of pennies
***********************************************************************/
	public int getPennies() {
		return pennies;
	}

/***********************************************************************
 * Creates the setter for pennies
 * @param number of pennies
***********************************************************************/
	public void setPennies(int pennies) {
		this.pennies = pennies;
	}

/***********************************************************************
 * Creates the getter for amount of change collected
 * @return the quarters, dimes, nickels and pennies (amount)
***********************************************************************/
	public double getAmount() {

		return (double) (quarters * 25 + dimes * 10 + nickels * 5
				+ pennies) / 100;

	}

/***********************************************************************
 * Constructor that initializes the instance variable with the
 * provided value converted to quarters, dimes, nickels, and
 * pennies.
 * @param amount the provided value in quarters
 * @param amount the provided value in dimes
 * @param amount the provided value in nickels 
 * @param amount the provided value in pennies
 * @throw when amount is less than 0
***********************************************************************/
	public ChangeJar(int quarters, int dimes, int nickels,
			int pennies) {
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
			throw new IllegalArgumentException("This is invalid");
		}

		else {

			this.quarters = quarters;
			this.dimes = dimes;
			this.nickels = nickels;
			this.pennies = pennies;

			cap = false;
		}
	}

/***********************************************************************
 * Constructor that initializes the instance variable with the other
 * ChangeJar parameter
 * @param other the object of the type ChangeJar
***********************************************************************/
	public ChangeJar(ChangeJar other) {
		this.quarters = other.quarters;
		this.dimes = other.dimes;
		this.nickels = other.nickels;
		this.pennies = other.pennies;

	}

/***********************************************************************
 * Method that returns true if "this" ChangeJar object is exactly
 * the same as the other object
 * @param other the object of the type Object
 * @return true or false
***********************************************************************/
	public boolean equals(Object other) {
		if (other == null)
			return false;
		//temp change jar
		ChangeJar temp = (ChangeJar) other;
		//below check if have to put into this jar
		if (this.quarters != temp.quarters)
			return false;
		if (this.dimes != temp.dimes)
			return false;
		if (this.nickels != temp.nickels)
			return false;
		if (this.pennies != temp.pennies)
			return false;

		return true;
	}

/***********************************************************************
 * Method that returns true if "this" ChangeJar object is exactly
 * the same as the other ChangeJar.
 * @param other the object of the type ChangeJar
 * @return true or false
***********************************************************************/
	public boolean equals(ChangeJar other) {
		ChangeJar temp = other;
		if (this.quarters != temp.quarters)
			return false;
		if (this.dimes != temp.dimes)
			return false;
		if (this.nickels != temp.nickels)
			return false;
		if (this.pennies != temp.pennies)
			return false;

		return true;
	}

/***********************************************************************
 * A static method that returns true if ChangeJar object jar1 is
 * exactly the same as if ChangeJar object jar2
 * @param jar1 object of the type ChangeJar that represents one
 * change jar
 * @param jar2 object of the type ChangeJar that represents a
 * separate change jar
 * @return true or false
***********************************************************************/
	public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
		if (jar1 == null || jar2 == null) {
			return false;
		}
		if (jar1.quarters != jar2.quarters)
			return false;
		if (jar1.dimes != jar2.dimes)
			return false;
		if (jar1.nickels != jar2.nickels)
			return false;
		if (jar1.pennies != jar2.pennies)
			return false;

		return true;
	}

/***********************************************************************
 * A method that returns 1 if "this" ChangeJar object is greater
 * than the other ChangeJar object; returns -1 if the "this"
 * ChangeJar object is less than the other ChangeJar; returns 0 if
 * the "this" ChangeJar object is equal to the other ChangeJar
 * object
 * @param other the object of the type ChangeJar
 * @return -1, 0, or 1
***********************************************************************/
	public int compareTo(ChangeJar other) {
		if (other == null) {
			return -1;
		}
		int thisChangeJar = 0, otherChangeJar = 0;
		//put into my change jar
		thisChangeJar = (this.quarters * 25);
		thisChangeJar += (this.dimes * 10);
		thisChangeJar += (this.nickels * 5);
		thisChangeJar += (this.pennies);
		//put into other change jar
		otherChangeJar = (other.quarters * 25);
		otherChangeJar += (other.dimes * 10);
		otherChangeJar += (other.nickels * 5);
		otherChangeJar += (other.pennies);
		if (thisChangeJar > otherChangeJar) {
			return 1;
		}
		if (thisChangeJar < otherChangeJar) {
			return -1;
		}
		return 0;
	}

/***********************************************************************
 * A method that returns 1 if ChangeJar object jar1 is greater than
 * ChangeJar object jar2; returns -1 if the ChangeJar object jar1 is
 * less that ChangeJar jar2; returns 0 if the ChangeJar object jar1
 * is equal to ChangeJar object jar2.
 * @param jar1 object of the type ChangeJar
 * @param jar2 object of the type ChangeJar
 * @return -1, 0, 1
***********************************************************************/
	public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
		
		int jar1Total = 0, jar2Total = 0;
		
		//put into my change jar
		jar1Total = (jar1.quarters * 25);
		jar1Total += (jar1.dimes * 10);
		jar1Total += (jar1.nickels * 5);
		jar1Total += (jar1.pennies);
		
		//put into other change jar
		jar2Total = (jar2.quarters * 25);
		jar2Total += (jar2.dimes * 10);
		jar2Total += (jar2.nickels * 5);
		jar2Total += (jar2.pennies);

		if (jar1Total > jar2Total) {
			return 1;
		}
		if (jar1Total < jar2Total) {
			return -1;
		}
		return 0;
	}

/***********************************************************************
 * A method that subtracts the parameters from the "this" ChangeJar
 * object.
 * @param quarters an integer representing what to take out
 * @param dimes an integer representing what to take out
 * @param nickels an integer representing what to take out
 * @param pennies an integer representing what to take out
 * @return an empty change jar or null
***********************************************************************/
	public ChangeJar takeOut(int quarters, int dimes, int nickels,
			int pennies) {
		if (cap) {
			ChangeJar A = new ChangeJar();
			return A;
		}
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		if (this.quarters > quarters && this.dimes > dimes
				&& this.nickels >= nickels && this.pennies >= pennies) {
			this.quarters -= quarters;
			this.dimes -= dimes;
			this.nickels -= nickels;
			this.pennies -= pennies;
		}
		return null;
	}

/***********************************************************************
 * A method that subtracts ChangeJar other to the "this" ChangJar
 * object.
 * @param other the object of the type ChangeJar
 * @return empty change jar or null
 * @throws when amount is not possible
***********************************************************************/
	
	public ChangeJar takeOut(ChangeJar other) {
		if (cap) {
			ChangeJar A = new ChangeJar();
			return A;
		}
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		if (this.quarters > quarters && this.dimes > dimes
				&& this.nickels >= nickels && this.pennies >= pennies) {
			this.quarters -= quarters;
			this.dimes -= dimes;
			this.nickels -= nickels;
			this.pennies -= pennies;
		}
		return null;
	}

/***********************************************************************
 * Constructor that accepts a string as a parameter with the
 * provided value converted to quarters, dimes, nickels, and pennies
 * @param amount as a double of the provided value
 * @throw Illegal Argument Exception when not possible
 * @return when code does like return null
***********************************************************************/

	public ChangeJar takeOut(double amount) {

		if (cap) {
			return null;
		}
		// Change jar to carry how much to take out
		ChangeJar C = new ChangeJar();
		if (amount < 0)
			throw new IllegalArgumentException("This is invalid");

		if (amount < 0 || amount > this.getAmount())
			return null;

		// temporary coins
		int tmp_quarters = 0;
		int tmp_dimes = 0;
		int tmp_nickels = 0;
		int tmp_pennies = 0;
		
		//change into non-decimal to avoid rounding error
		double rem = amount * 100;
		// sort the pennies first
		while (rem >= 1) {
			//break as many quarters when divisible by 100
			if (rem % 100 == 0) {
				tmp_quarters = (int) (rem / 25);
				rem = 0;
				break;
			}
			if (tmp_dimes == 5) {
				tmp_quarters += 2;
				tmp_dimes = 0;
			}
			if ((tmp_nickels == 2)) {
				tmp_dimes++;
				tmp_nickels = 0;
			}
			//continue to increment pennies
			else {
				tmp_pennies++;
				rem -= 1;
				if (tmp_pennies == 5) {
					tmp_nickels++;
					tmp_pennies = 0;
				}

			}
		}

		// tmp change jar to place as a holder
		ChangeJar ChangeJar_cp = new ChangeJar(this);
		
		// now found which bills to give
		// first take-out the quarters
		if (ChangeJar_cp.quarters >= tmp_quarters) {
			ChangeJar_cp.quarters -= tmp_quarters;
			C.quarters = tmp_quarters;
		} else {
			int tmp = tmp_quarters - ChangeJar_cp.quarters;
			C.quarters = ChangeJar_cp.quarters;
			ChangeJar_cp.quarters = 0;
			tmp_dimes += tmp * 10;
		}

		// then take-out the dimes
		if (ChangeJar_cp.dimes >= tmp_dimes) {
			ChangeJar_cp.dimes -= tmp_dimes;
			C.dimes = tmp_dimes;
		} else {
			int tmp = tmp_dimes - ChangeJar_cp.dimes;
			int x = ChangeJar_cp.dimes;
			ChangeJar_cp.dimes = 0;
			if (tmp % 2 != 0 && x > 0) {
				tmp++;
				ChangeJar_cp.dimes++;
				C.dimes = x - ChangeJar_cp.dimes;
			} else {
				C.dimes = x;
				ChangeJar_cp.dimes = 0;
			}

			tmp_pennies += (tmp * 100) / 100;

		}
		//catch if not enough pennies
		if (ChangeJar_cp.pennies < tmp_pennies)
			return null;

		ChangeJar_cp.pennies -= tmp_pennies;
		C.pennies = tmp_pennies;
		
		//catch if the same amount
		if (C.getAmount() != amount)
			return null;

		this.quarters = ChangeJar_cp.quarters;
		this.dimes = ChangeJar_cp.dimes;
		this.nickels = ChangeJar_cp.nickels;
		this.pennies = ChangeJar_cp.pennies;

		return C;
	}

	// /*****************************************************************
	// * A method that decrements the "this" ChangeJar by 1 penny
	// *****************************************************************/
	// public void dec() {
	// if (cap) {
	// return;
	// }
	// this.pennies--;
	// if (pennies < 0) {
	// throw new ArithmeticException("This is invalid");
	// }
	//
	// }

/***********************************************************************
 * A method that adds the parameter from the "this" ChangeJar
 * object.
 * @param quarter integer that represents quarters
 * @param dimes integer that represents dimes
 * @param nickels integer that represents nickels
 * @param pennies integer that represents pennies
 * @throw when less than zero 
***********************************************************************/
	public void putIn(int quarters, int dimes, int nickels, int pennies) 
	{
		if (cap) {
			return;
		}

		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		//add into this
		this.quarters += quarters;
		this.dimes += dimes;
		this.nickels += nickels;
		this.pennies += pennies;
	}

/***********************************************************************
 * A method that adds ChangeJar other to the "this" ChangeJar
 * object.
 * @param other the object of the type ChangeJar
 * @throws when less than zero 
***********************************************************************/
	public void putIn(ChangeJar other) {
		if (cap) {
			return;
		}
		if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
			throw new IllegalArgumentException("This is invalid");
		}

		other.quarters += quarters;
		other.dimes += dimes;
		other.nickels += nickels;
		other.pennies += pennies;
		
		this.quarters = 0;
		this.dimes = 0;
		this.nickels = 0;
		this.pennies = 0;
	}

// /*****************************************************************
// * A method that increments the "this" ChangeJar by 1 penny.
// *****************************************************************/
// public void inc() {
// if (cap) {
// return;
// this.pennies++;
//
// }

/***********************************************************************
 * Method that returns a string that represents a ChangeJar
***********************************************************************/
	public String toString() {
		String tmp = "";
		if (quarters == 1)
			tmp += quarters + " quarter \n";
		else
			tmp += quarters + " quarters\n";

		if (dimes == 1)
			tmp += dimes + " dime\n";
		else
			tmp += dimes + " dimes\n";

		if (nickels == 1)
			tmp += nickels + " nickel\n";
		else
			tmp += nickels + " nickels\n";

		if (pennies == 1)
			tmp += pennies + " penny\n";
		else
			tmp += pennies + " pennies\n";

		return tmp;
	}

/***********************************************************************
 * A method that save the "this" ChaangeJar to a file
 * @param fileName a String representing the name of the file
***********************************************************************/
	public void save(String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException error) {
			System.out.println("File Not Found");
		} catch (IOException error) {
			System.out.println("Oops! Something went wrong.");
		}
	}

/***********************************************************************
 * A method that loads the "this" ChangeJar object from a file.
 * @param fileName a String representing the name of the file
***********************************************************************/
	public void load(String fileName) {
		try {
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);

			ChangeJar tmp = (ChangeJar) ois.readObject();
			this.quarters = tmp.quarters;
			this.dimes = tmp.dimes;
			this.nickels = tmp.nickels;
			this.pennies = tmp.pennies;
			ois.close();
			//catch exceptions
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

/***********************************************************************
 * A method that turns off and on any takeOut/putIn method from
 * changing the state of the "this" object as it relates to the
 * amount in the ChangeJar
 * @param on a boolean set to true signifying that the mutator is on
***********************************************************************/
	public static void suspend(Boolean on) {
		cap = on;
	}

	public static void main(String[] args) {
		ChangeJar s = new ChangeJar(2, 4, 3, 1);
		System.out.println("1.06 Amount: \n" + s);

		s = new ChangeJar(0, 0, 0, 1);
		System.out.println(".01 Amount: \n" + s);

		s = new ChangeJar();
		System.out.println(".01 Amount: \n" + s);

		ChangeJar s1 = new ChangeJar();
		System.out.println("0 Amount: \n" + s1);

		s1.putIn(1, 1, 1, 100);
		System.out.println("1,1,1,100 Amount: \n" + s1);

		// ChangeJar s2 = new ChangeJar(41.99);
		// s2.add(0, 0, 0, 99);
		// for (int i = 0; i < 100; i++)
		// s2.dec();
		// System.out.println("amount: \n" + s2);

		ChangeJar s3 = new ChangeJar(4, 0, 0, 99);
		// s3.inc();
		// System.out.println("19.99 Amount: \n" + s3);

		s3.takeOut(1, 0, 0, 0);
		System.out.println("3,0,0,99 Amount: \n" + s3);

		// s3.dec();
		// System.out.println("1.64 Amount: \n" + s3);

		ChangeJar s6 = new ChangeJar(24, 5, 6, 20);
		// s6.inc();
		System.out.println("7.00 Amount: \n" + s6);
	}
}
