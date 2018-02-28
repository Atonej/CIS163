package Project3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***********************************************************************
 * This class inherit the properties of Site to incorporate a Tent in 
 * the camp site
 * 
 * @author Tiesha Anderson, Atone Joryman, Jessica Ritcksgers 
 * @version Fall 2017 
 **********************************************************************/
public class Validation {

	/***********************************************************************
	* validates user input as an integer
	* @param text user input in DialogCheckInRV and
	* DialogCheckInTent classes. 
	* @return true user input is found
	* @return false user input was an empty string
	**********************************************************************/
	
	private Boolean isNotEmpty(String text) {
		if (text.trim().isEmpty())
			return false;
		else
			return true;
	}
	
	
	/***********************************************************************
	* validates user input as an integer
	* @param text user input in DialogCheckInRV and
	* DialogCheckInTent classes. 
	* @return true user input is valid
	* @return false user input contains characters other
	* than integers
	**********************************************************************/
	
	public Boolean isNumber(String text) {
		try 
		{
			if(isNotEmpty(text)) {			
				Integer.parseInt(text.trim());
				return true;
			}
			else
				return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	
	/***********************************************************************
	* checks user input for symbols
	* @param text user input in DialogCheckInRV and
	* DialogCheckInTent classes. 
	* @return true user input does not contain symbols
	* @return false user input contains symbols
	**********************************************************************/
	
	public Boolean isValidInput(String text) {
		try
		{
			if(isNotEmpty(text)) {
				text.trim();
				Pattern p = Pattern.compile("[^A-Za-z0-9]");
				Matcher m = p.matcher(text.trim());

				boolean b = m.find();
				if (b == true)
					return false;
				else
					return true;
			}
			else
				return false;
		}
		catch(Exception ex)
		{
			return false;
		}			

	}

}
