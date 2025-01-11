package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.*;

/**
 * Manages the vehicles in the GasVehicle database and 
 * provides the public services to manage the database from the class GasVehicle.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see SingletonDBConnection#getConnection()
 * @see GasVehicle
 */
public class GasolineVehicleDB
{
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatement = null;
	static private ResultSet myResultSet = null;
	static private GasVehicle gv = null;
	
	/**
	 * Adds a GasVehicle into the database of GasolineVehicleDB
	 * by using the SQL command Insert.
	 * 
	 * @param v the GasVehicle to add in the database.
	 * @return 1 if added successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int insert(GasVehicle v) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "Insert into GasVehicle(serialNumber, made, model, dateOfManufacture, tripCounter, fuelConsumed)  values(\'"
				+ v.getSerialNumber() + "\', \'" + v.getMade() + "\', \'" + v.getModel() + "\', \'" + v.getDateOfManufacture() + "\'," + v.getTripCounter() + ", " + v.getFuelConsumed() + ")";
		
		try 
		{
			myStatement = myConnection.createStatement();
			int rowAffected = myStatement.executeUpdate(mySQLStatement);
			myConnection.commit();			
			if(rowAffected > 0) 
			{
				System.out.println("Vehicle added.");
				return 1;
			}
			else 
			{
				System.out.println("Failed to add Vehicle.");
				return 0;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Modifies a GasVehicle in the database of GasolineVehicleDB
	 * by using the SQL command Update.
	 * Changes the made, the model and the date of manufacture of the GasVehicle in the database.
	 * 
	 * @param v the GasVehicle to modify in the database.
	 * @return 1 if modified successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int update(GasVehicle v) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "UPDATE GasVehicle set made = \'" + v.getMade()  +  "\', model = \'" + v.getModel() + "\', dateOfManufacture = \'" + v.getDateOfManufacture() + "\' WHERE serialNumber = " +  v.getSerialNumber();
		
		try 
		{
			myStatement = myConnection.createStatement();
			
			int rowAffected = myStatement.executeUpdate(mySQLStatement);
			
			myConnection.commit();	
			    
			if(rowAffected > 0) 
			{
				System.out.println("Vehicle updated.");
				return 1;
			}
			else 
			{
				System.out.println("Failed to update a vehicle.");
				return 0;
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Removes a vehicle from the database by serial number
	 * using the SQL command Delete.
	 * 
	 * @param id the serial number of the vehicle representing the primary key of the table in the database.
	 * @return 1 if removed successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int delete(Long id) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "Delete FROM GasVehicle WHERE serialNumber = "  + id;
		
		try 
		{
			myStatement = myConnection.createStatement();
			int rowAffected = myStatement.executeUpdate(mySQLStatement);
			
			myConnection.commit();	
			
			if(rowAffected > 0) 
			{
				System.out.println("Vehicle removed.");
				return 1;
			}
			else 
			{
				System.out.println("Vehicle NOT found.");
				return 0;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Searches a vehicle in the database by serial number
	 * using the SQL command Select.
	 * 
	 * @param id the serial number of the vehicle representing the primary key of the table in the database.
	 * @return the GasVehicle found by serial number
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see java.sql.SQLException
	 */
	public static GasVehicle search(Long id) throws SQLException, NumberFormatException, RaiseException
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLQuery = "SELECT serialNumber, made, model, dateOfManufacture, tripCounter, fuelConsumed FROM GasVehicle WHERE serialNumber = " + id ;
		
		myStatement = myConnection.createStatement();
		
		myResultSet = myStatement.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) 
		{
			gv = new GasVehicle(Long.parseLong(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), myResultSet.getDate(4), Integer.parseInt(myResultSet.getString(5)), Double.parseDouble(myResultSet.getString(6)));
		}	
		else
		{
			gv = null;
		}
		return gv;
	}
	
	/**
	 * Displays all vehicles in the database using the SQL command Select.
	 * 
	 * @return an ArrayList of GasVehicle which represents the list of GasVehicle in the database
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.util.ArrayList
	 * @see java.sql.SQLException
	 */
	public static ArrayList<GasVehicle> select() throws SQLException, NumberFormatException, RaiseException
	{
		ArrayList<GasVehicle> myList = new ArrayList<GasVehicle>();
		
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLQuery = "Select serialNumber as \"Serial Number\", made as \"Made\", model as \"Model\", to_char(dateOfManufacture,\'YYYY-MM-DD\') as \"DateOfManufacture\", tripCounter as \"Trip Counter\", fuelConsumed as \"Fuel Consumed\" from GasVehicle";
		
		myStatement = myConnection.createStatement();
		
		myResultSet = myStatement.executeQuery(mySQLQuery);
		
		
		while(myResultSet.next()) 
		{
			gv = new GasVehicle(Long.parseLong(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), myResultSet.getDate(4), Integer.parseInt(myResultSet.getString(5)), Double.parseDouble(myResultSet.getString(6)));
			
			myList.add(gv);
		}
		
		return myList;
	}
	
	/**
	 * Makes trip with a GasVehicle in the database of GasolineVehicleDB
	 * by using the SQL command Update.
	 * Changes the trip counter and the fuel consumed of the GasVehicle in the database.
	 * 
	 * @param v the GasVehicle to make trip in the database.
	 * @return 1 if the vehicle make trip successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int makeTrip(GasVehicle v) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "UPDATE GasVehicle set tripCounter = " + v.getTripCounter()  +  ", fuelConsumed = " + v.getFuelConsumed() + " WHERE serialNumber = " +  v.getSerialNumber();
		
		try 
		{
			myStatement = myConnection.createStatement();
			
			int rowAffected = myStatement.executeUpdate(mySQLStatement);
			
			myConnection.commit();	
			    
			if(rowAffected > 0) 
			{
				System.out.println("Make trip successful.");
				return 1;
			}
			else 
			{
				System.out.println("Failed to make trip.");
				return 0;
			}			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Displays and sorts the list of vehicles in the database by mileage efficiency
	 * using the SQL command Select and Order By.
	 * 
	 * @return an ArrayList of GasVehicle which represents the sorted list of GasVehicle in the database
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.util.ArrayList
	 * @see java.sql.SQLException
	 */
	public static ArrayList<GasVehicle> sort() throws SQLException, NumberFormatException, RaiseException 
	{
		ArrayList<GasVehicle> myList = new ArrayList<GasVehicle>();
		
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLQuery = "Select serialNumber as \"Serial Number\", made as \"Made\", model as \"Model\", to_char(dateOfManufacture,\'YYYY-MM-DD\') as \"DateOfManufacture\", tripCounter as \"Trip Counter\", fuelConsumed as \"Fuel Consumed\" from GasVehicle";
		
		myStatement = myConnection.createStatement();
		
		myResultSet = myStatement.executeQuery(mySQLQuery +  " ORDER BY tripCounter/fuelConsumed DESC");
		
		
		while(myResultSet.next()) 
		{
			gv = new GasVehicle(Long.parseLong(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), myResultSet.getDate(4), Integer.parseInt(myResultSet.getString(5)), Double.parseDouble(myResultSet.getString(6)));
			
			myList.add(gv);
		}
		
		return myList;
	}
}
