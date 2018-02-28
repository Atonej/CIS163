package atmPack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/******************************************************************************
 * Graphical representation of multiple ATM's containing only hundreds, fifties
 * and twenties.
 * 
 * @author Atone Joryman
 * @version Winter 2017
 ******************************************************************************/
public class ATM implements java.io.Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L; // for loading file

	/** number of hundreds */
	private int hundreds;

	/** number of fifties */
	private int fifties;
	/** number of twenties */
	private int twenties;

	/** check if there is an amount */
	private static boolean cap;

	/*****************************************************************
	 * Default constructor that sets the ChangeJar to zero
	 *****************************************************************/
	public ATM() {
		// this instance variable
		this.hundreds = 0;
		this.fifties = 0;
		this.twenties = 0;
		this.cap = false;

	}

	/*****************************************************************
	 * A constructor that initializes the instance variables with the
	 * parameters.
	 * @param hundreads integer that represents hundreds
	 * @param fifties integer that represents fifties
	 * @param twenties integer that represents twenties
	 *****************************************************************/
	public ATM(int hundreds, int fifties, int twenties) {
		// less than zero throw
		if (hundreds < 0 || fifties < 0 || twenties < 0) {
			throw new IllegalArgumentException("This is invalid");
		} else {
			// then take in values
			this.hundreds = hundreds;
			this.fifties = fifties;
			this.twenties = twenties;
			cap = false;
		}
	}

	/*****************************************************************
	 * Constructor that initializes the instance variable with the other ATM
	 * parameter
	 * @param other the object of the type ATM
	 *****************************************************************/
	public ATM(ATM other) {
		this.hundreds = other.hundreds;
		this.fifties = other.fifties;
		this.twenties = other.twenties;

	}

	/*****************************************************************
	 * Creates the getters and setters for the instance variables
	 *****************************************************************/
	public void setHundreds(int hundreds) {
		this.hundreds = hundreds;

	}

	// public void setAmount(double amount) {
	// this.amount = amount;
	//
	// }

	public void setFifties(int fifties) {
		this.fifties = fifties;

	}

	public void setTwenties(int twenties) {

		this.twenties = twenties;
	}

	public double getAmount() {

		return hundreds * 100 + fifties * 50 + twenties * 20;

	}

	public int getHundreds() {
		return hundreds;

	}

	public int getFifties() {

		return fifties;
	}

	public int getTwenties() {
		return twenties;

	}

	/*****************************************************************
	 * Method that returns true if "this" ATM object is exactly the same as the
	 * other object.
	 * @param other the object of the type Object
	 * @return true or false
	 *****************************************************************/
	public boolean equals(Object other) {
		if (other == null)
			return false;

		ATM temp = (ATM) other;
		if (this.hundreds != temp.hundreds)
			return false;
		if (this.fifties != temp.fifties)
			return false;
		if (this.twenties != temp.twenties)
			return false;

		return true;
	}

	/*****************************************************************
	 * Method that returns true if "this" ATM object is exactly the same as the
	 * other ATM.
	 * @param other the object of the type ATM
	 * @return true or false
	 *****************************************************************/
	public boolean equals(ATM other) {
		ATM temp = other;
		if (this.hundreds != temp.hundreds)
			return false;
		if (this.fifties != temp.fifties)
			return false;
		if (this.twenties != temp.twenties)
			return false;

		return true;
	}

	/*****************************************************************
	 * A static method that returns true if ATM object ohter1 is exactly the
	 * same as if ATM object other2
	 * @param other1 object of the type ATM that represents one atm
	 * @param other2 object of the type ATM that represents a separate atm
	 * @return true or false
	 *****************************************************************/
	public static boolean equals(ATM other1, ATM other2) {

		if (other1 == null || other2 == null) {
			return false;
		}
		if (other1.hundreds != other2.hundreds)
			return false;
		if (other1.fifties != other2.fifties)
			return false;
		if (other1.twenties != other2.twenties)
			return false;

		return true;
	}

	/*****************************************************************
	 * A method that returns 1 if "this" ATM object is greater than the other
	 * ATM object; returns -1 if the "this" ATM object is less than the other
	 * ATM; returns 0 if the "this" ATM object is equal to the other ATM object
	 * @param other the object of the type ATM
	 * @return -1, 0, or 1
	 *****************************************************************/
	public int compareTo(ATM other) {
		if (other == null) {
			return -1;
		}
		int thisATM = 0, otherATM = 0;
		thisATM = (this.hundreds * 100);
		thisATM += (this.fifties * 50);
		thisATM += (this.twenties * 20);

		otherATM = (other.hundreds * 100);
		otherATM += (other.fifties * 50);
		otherATM += (other.twenties * 20);
		if (thisATM > otherATM) {
			return 1;
		}
		if (thisATM < otherATM) {
			return -1;
		}
		return 0;
	}

	/*************************************************************************
	 * A method that returns 1 if ATM object other1 is greater than ATM object
	 * other2; returns -1 if the ATM object other1 is less that ChangeJar
	 * other2; returns 0 if the ChangeJar object other1 is equal to ChangeJar
	 * object other2.
	 * @param jar1 object of the type ChangeJar
	 * @param jar2 object of the type ChangeJar
	 * @return -1, 0, 1
	 *************************************************************************/
	public static int compareTo(ATM other1, ATM other2) {
		int other1Tot = 0, other2Tot = 0;
		other1Tot = (other1.hundreds * 100);
		other1Tot += (other1.fifties * 50);
		other1Tot += (other1.twenties * 20);

		other2Tot = (other2.hundreds * 100);
		other2Tot += (other2.fifties * 50);
		other2Tot += (other2.twenties * 20);
		if (other1Tot > other2Tot) {
			return 1;
		}
		if (other1Tot < other2Tot) {
			return -1;
		}
		return 0;
	}

	/*****************************************************************
	 * A method that adds the parameters from the "this" ATM object.
	 * @param hundreds an integer representing hundreds
	 * @param fifties an integer representing fifties
	 * @param twenties an integer representing twenties
	 *****************************************************************/
	public void putIn(int hundreds, int fifties, int twenties) {
		if (cap) {
			return;
		}
		if (hundreds < 0 || fifties < 0 || twenties < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		this.hundreds += hundreds;
		this.fifties += fifties;
		this.twenties += twenties;

		// hundreds = hundreds * 100;
		// fifties = fifties * 50;
		// twenties = twenties * 20;
	}

	/*****************************************************************
	 * A method that adds ATM other to the "this" ATM object.
	 * @param other the object of the type ATM
	 *****************************************************************/
	public void putIn(ATM other) {
		if (cap) {
			return;
		}
		if (hundreds < 0 || fifties < 0 || twenties < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		other.hundreds += hundreds;
		other.fifties += fifties;
		other.twenties += twenties;
	}

	/*****************************************************************
	 * A method that subtracts the parameters from the "this" ATM object.
	 * @param hundreds an integer representing hundreds
	 * @param fifties an integer representing fifties
	 * @param twenties an integer representing twenties
	 *****************************************************************/
	public void takeOut(int hundreds, int fifties, int twenties) {
		if (cap) {
			return;
		}
		if (hundreds < 0 || fifties < 0 || twenties < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		this.hundreds -= hundreds;
		this.fifties -= fifties;
		this.twenties -= twenties;
	}

	/*****************************************************************
	 * A method that subtracts ATM other to the "this" ATM object.
	 * @param other the object of the type ATM
	 *****************************************************************/
	public void takeOut(ATM other) {
		if (cap) {
			return;
		}
		if (hundreds < 0 || fifties < 0 || twenties < 0) {
			throw new IllegalArgumentException("This is invalid");
		}
		other.hundreds -= hundreds;
		other.fifties -= fifties;
		other.twenties -= twenties;

	}

	/**********************************************************************
	 * Constructor that initializes the instance variable with the provided
	 * value converted to hundreds, fifties and twenties.
	 * @param amount the provided value in denomination
	 * @return ATM() everything zero
	 **********************************************************************/
	public ATM takeOut(double amount) {
		if (cap) {
			return null;
		}
		ATM A = new ATM();
		if (amount < 0 || (amount % 10) != 0)
			throw new IllegalArgumentException("This is invalid");

		if (amount < 20 || amount > this.getAmount())
			return null;
		// temporary dollars bills
		int tmp_twenties = 0;
		int tmp_fifties = 0;
		int tmp_hundreds = 0;
		double rem = amount;
		// sort the twenties first
		while (rem >= 20) {
			if (rem == 50) {
				tmp_fifties++;
				rem -= 50;
				break;
			}
			if ((tmp_twenties == 3) && (rem + 10) % 20 == 0) {
				tmp_fifties++;
				if (tmp_fifties == 2) {
					tmp_hundreds++;
					tmp_fifties = 0;
				}
				tmp_twenties = 0;
				rem += 10;

			} else {
				tmp_twenties++;
				rem -= 20;
				if (tmp_twenties == 5) {
					tmp_hundreds++;
					tmp_twenties = 0;
				}
			}
		}

		if (rem != 0)
			return null;

		// now found which bills to give
		ATM ATM_cp = new ATM(this);

		// first take-out the hundreds
		if (ATM_cp.hundreds >= tmp_hundreds) {
			ATM_cp.hundreds -= tmp_hundreds;
			A.hundreds = tmp_hundreds;
		} else {
			int tmp = tmp_hundreds - ATM_cp.hundreds;
			A.hundreds = ATM_cp.hundreds;
			ATM_cp.hundreds = 0;
			tmp_fifties += tmp * 2;
		}

		// then take-out the fifties
		if (ATM_cp.fifties >= tmp_fifties) {
			ATM_cp.fifties -= tmp_fifties;
			A.fifties = tmp_fifties;
		} else {
			int tmp = tmp_fifties - ATM_cp.fifties;
			int x = ATM_cp.fifties;
			ATM_cp.fifties = 0;
			if (tmp % 2 != 0 && x > 0) {
				tmp++;
				ATM_cp.fifties++;
				A.fifties = x - ATM_cp.fifties;
			} else {
				A.fifties = x;
				ATM_cp.fifties = 0;
			}

			tmp_twenties += (tmp * 50) / 20;

		}

		if (ATM_cp.twenties < tmp_twenties)
			return null;

		ATM_cp.twenties -= tmp_twenties;
		A.twenties = tmp_twenties;

		if (A.getAmount() != amount)
			return null;

		this.hundreds = ATM_cp.hundreds;
		this.fifties = ATM_cp.fifties;
		this.twenties = ATM_cp.twenties;

		return A;
	}

	/*****************************************************************
	 * Method that returns a string that represents a ATM
	 * @return value of string
	 *****************************************************************/
	public String toString() {
		String tmp = "";
		if (hundreds == 1)
			tmp += hundreds + " hundred dollar bill\n";
		else
			tmp += hundreds + " hundred dollar bills\n";

		if (fifties == 1)
			tmp += fifties + " fifty dollar bill\n";
		else
			tmp += fifties + " fifty dollar bills\n";

		if (twenties == 1)
			tmp += twenties + " twenty dollar bill\n";
		else
			tmp += twenties + " twenty dollar bills";

		return tmp;

	}

	/*****************************************************************
	 * A method that save the "this" ATM to a file
	 * @param fileName a String representing the name of the file
	 *****************************************************************/
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

	/*****************************************************************
	 * A method that loads the "this" ATM object from a file.
	 * @param fileName a String representing the name of the file
	 *****************************************************************/
	public void load(String fileName) {
		try {
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fin);

			ATM tmp = (ATM) ois.readObject();
			this.hundreds = tmp.hundreds;
			this.fifties = tmp.fifties;
			this.twenties = tmp.twenties;
			ois.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************
	 * A method that turns off and on any subtract/add method from changing the
	 * state of the "this" object as it relates to the amount in the ATM
	 * @param on a boolean set to true signifying that the mutator is on
	 *****************************************************************/
	public static void suspend(Boolean on) {
		cap = on; // causes effect to methods with this flag for amounts

	}

	/*****************************************************************
	 * This is a main method being used for unit testing. Refers to the class,
	 * but does not access anything.
	 *****************************************************************/
	public static void main(String[] args) {
		ATM s = new ATM(10, 2, 3);
		System.out.println("Created ATM:$1160, result: " + s.getAmount());

		s = new ATM(3, 2, 3);
		System.out.println("\nCreated ATM:$460, result: " + s.getAmount());

		s = new ATM(7, 13, 4);
		System.out.println("\nCreated ATM:$1430, result: " + s.getAmount());
		// s.takeOut(140);

		s = new ATM(15, 3, 0);
		System.out.println("\nCreated ATM:$1650, result: " + s.getAmount());

		s = new ATM(5, 4, 2);
		System.out.println("\nCreated ATM:$740, result: " + s.getAmount());

		ATM s1 = new ATM();
		System.out.println("\nCreated ATM:$0, result: " + s1.getAmount());

		s1.putIn(10, 2, 3);
		System.out.println("\nAdded ATM:$1160, result: " + s1.getAmount());

		ATM s2 = new ATM(10, 2, 3);
		s2.putIn(0, 0, 0);
		System.out.println("\nAdded ATM:$1160, result: " + s2.getAmount());

		s2 = new ATM(2, 1, 3);
		System.out.println("\nCreated ATM:$310, result: " + s2.getAmount());
		ATM temp = s2.takeOut(250);
		System.out.println("Take out the following:\n" + temp);
		System.out
				.println("Remaining ChangeJar:$60, result: " + s2.getAmount());

		s2 = new ATM(4, 2, 7);
		System.out.println("\nCreated ATM:$640, result: " + s2.getAmount());
		ATM temp2 = s2.takeOut(450);
		System.out.println("Take out the following:\n" + temp2);
		System.out.println("Remaining ChangeJar:$190, result: "
				+ s2.getAmount());

		s2 = new ATM();
		System.out.println("\nCreated ATM:$0, result: " + s2.getAmount());

		s2 = new ATM(5, 4, 3);
		System.out
				.println("\nCreated ATM: hundreds:5, fifties:4, twenties:3\nresult: \n"
						+ s2);
		s2.save("pizza");
		System.out.println("saving ATM in file pizza and delete ATM ");
		s2 = new ATM();
		s2.load("pizza");
		System.out.println("Load ATM from file pizza, result: \n" + s2);

		if (s2.equals(new ATM(5, 4, 3)))
			System.out.println("\nLoad and Save and Equals works!");

		ATM s3 = null;
		try {
			System.out
					.println("\nCreated ATM: hundreds:-5, fifties:0, twenties:3\nresult: ");
			s3 = new ATM(-5, 0, 3);
		} catch (IllegalArgumentException ex) {
			System.out.println("Error in hudreds: null");
		}

		try {
			System.out
					.println("\nCreated ATM: hundreds:5, fifties:-3, twenties:6\nresult: ");
			s3 = new ATM(3, -3, 6);
		} catch (IllegalArgumentException ex) {
			System.out.println("Error in fifties: null");
		}

		try {
			System.out
					.println("\nCreated ATM: hundreds:9, fifties:2, twenties:-1\nresult: ");
			s3 = new ATM(9, 2, -1);
		} catch (IllegalArgumentException ex) {
			System.out.println("Error in twenties: null");
		}
	}
}
