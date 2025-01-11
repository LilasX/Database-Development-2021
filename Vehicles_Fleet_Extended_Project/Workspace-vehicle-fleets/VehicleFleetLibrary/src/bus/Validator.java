package bus;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Validates the user inputs and manages the exceptions thrown when an error occurs 
 * during the execution of the application.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 */
public class Validator 
{
	/**
	 * Verifies if the value is positive.
	 * The value is valid if positive, else it's an invalid value.
	 * 
	 * @param value the value int to validate.
	 * @return true if the value is positive, else throws RaiseException
	 * @throws RaiseException if the argument is negative or 0.
	 * @see RaiseException
	 */
	public static boolean isInRange (int value) throws RaiseException
	{
		if(value<=0)
		{
			throw new RaiseException("Invalid Input, data must be positive.");
		}
		else 
		{
			return true;
		}
	}
	
	/**
	 * Verifies if the value is positive.
	 * The value is valid if positive, else it's an invalid value.
	 * 
	 * @param value the value double to validate.
	 * @return true if the value is positive, else throws RaiseException
	 * @throws RaiseException if the argument is negative or 0.
	 * @see RaiseException
	 */
	public static boolean isInRange (double value) throws RaiseException
	{
		if(value<=0)
		{
			throw new RaiseException("Invalid Input, data must be positive.");
		}
		else 
		{
			return true;
		}
	}
	
	/**
	 * Verifies if the value is positive.
	 * The value is valid if positive, else it's an invalid value.
	 * 
	 * @param value the value Long to validate.
	 * @return true if the value is positive, else throws RaiseException
	 * @throws RaiseException if the argument is negative or 0.
	 * @see RaiseException
	 */
	public static boolean isInRange (Long value) throws RaiseException
	{
		if(value<=0)
		{
			throw new RaiseException("Invalid Input, data must be positive.");
		}
		else 
		{
			return true;
		}
	}
	
	/**
	 * Verifies if the string contains only digits.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the string to validate.
	 * @return true if the value has only digits, else throws RaiseException
	 * @throws RaiseException if the string does not contain only digits.
	 * @see RaiseException
	 * @see Character
	 */
	public static boolean isDigit (String value) throws RaiseException
	{
		for(int i=0; i!=value.length(); i++) 
		{
			if(!Character.isDigit(value.charAt(i)))
			{
				throw new RaiseException("Invalid Input, data must be only digits.");
			}
		}
		return true;
	}
	
	/**
	 * Verifies if the string contains only alphabet letters.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the string to validate.
	 * @return true if the value has only alphabet letters, else throws RaiseException
	 * @throws RaiseException if the string does not contain only alphabet letters.
	 * @see RaiseException
	 * @see Character
	 */
	public static boolean isChar (String value) throws RaiseException
	{
		for(int i=0; i!=value.length(); i++) 
		{
			if(!Character.isAlphabetic(value.charAt(i)))
			{
				throw new RaiseException("Invalid Input, data must be only alphabet letters.");
			}
		}
		return true;
	}

	/**
	 * Verifies if the string contains only letters and/or digits.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the string to validate.
	 * @throws RaiseException if the string does not contain only letters and/or digits.
	 * @see RaiseException
	 * @see Character
	 */
	public static void isLetterOrDigit(String value) throws RaiseException
	{
		for(int i=0; i!=value.length(); i++) 
		{
			if(!Character.isLetterOrDigit(value.charAt(i)))
			{
				throw new RaiseException("Invalid Input, data must be only letters and/or digits.");
			}
		}
	}
	
	/**
	 * Verifies if the string contains a double.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the string to validate.
	 * @return true if the value is a double, else throws RaiseException
	 * @throws RaiseException if the string does not contain a double.
	 * @see RaiseException
	 * @see NumberFormatException
	 */
	public static boolean isDouble(String value) throws RaiseException
	{
		try
		{
			Double.parseDouble(value);
			return true;
		}
		catch (NumberFormatException e)
		{
			throw new RaiseException("Invalid Input, data must be a double.");
		}
	}
	
	/**
	 * Verifies if the string is a date with the defined format.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the string to validate.
	 * @return a Date if value is valid
	 * @throws RaiseException if the date format is invalid.
	 * @see RaiseException
	 * @see java.util.Date
	 * @see java.text.SimpleDateFormat
	 * @see java.text.ParseException
	 */
	public static Date isDate(String value) throws RaiseException
	{
		Date aDate = new Date(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		dateFormat.setLenient(false);
		boolean valid = false;
		do
		{
			try
			{
				dateFormat.parse(value.trim());
				aDate = new SimpleDateFormat("yyyyMMdd").parse(value);
				valid = true;
			}
			catch (ParseException e)
			{
				throw new RaiseException("Invalid Input, date must be in format : yyyyMMdd.");
			}
		}while(!valid);
    	return aDate;
	}
	
	/**
	 * Verifies if the serial number of the Vehicle to add in the appropriate database doesn't exist in the database.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the Long to validate.
	 * @return a Long if value is valid
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws RaiseException if the value matches a serial number in the database.
	 * @see Vehicle#getSerialNumber()
	 * @see GasVehicle#search(Long)
	 * @see GasVehicle#getData()
	 * @see ElectricVehicle#search(Long)
	 * @see ElectricVehicle#getData()
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static Long isNotDuplicateDB(Long value) throws SQLException, RaiseException
	{
		Vehicle searched;
		
		searched = GasVehicle.search(value);
		if(searched != null)
		{
			throw new RaiseException("This serial number already exists, please enter a different serial number.");
		}
		else {
			searched = ElectricVehicle.search(value);
			if(searched != null)
			{
				throw new RaiseException("This serial number already exists, please enter a different serial number.");
			}
		}
		
		return value;
	}
	
	/**
	 * Verifies if the serial number of the Vehicle to add doesn't exist in the SingletonVehiclesFleet's fleetOfVehicles.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the Long to validate.
	 * @return a Long if value is valid
	 * @throws RaiseException if the value matches a serial number in the list.
	 * @see SingletonVehiclesFleet#search(Long)
	 * @see RaiseException
	 */
	public static Long isNotDuplicate(Long value) throws RaiseException
	{
		Vehicle searched;
		
		searched = SingletonVehiclesFleet.getSingleton().search(value);
		
		if(searched != null)
		{
			throw new RaiseException("This serial number already exists, please enter a different serial number.");
		}
		
		return value;
	}
	
	/**
	 * Verifies if the serial number of the Vehicle to add doesn't exist in the SingletonHashMapCollection's vehicleCollection.
	 * Otherwise, it's an invalid input.
	 * 
	 * @param value the Long to validate.
	 * @return a Long if value is valid
	 * @throws RaiseException if the value matches a serial number in the list.
	 * @see SingletonHashMapCollection#getVehicleList()
	 * @see RaiseException
	 */
	public static Long isNotDuplicateHM(Long value) throws RaiseException
	{
		if (SingletonHashMapCollection.getSingleInstance().getVehicleList().containsKey(value))
		 {
			throw new RaiseException("This serial number already exists, please enter a different serial number.");
		 }  
		return value;
	}
}
