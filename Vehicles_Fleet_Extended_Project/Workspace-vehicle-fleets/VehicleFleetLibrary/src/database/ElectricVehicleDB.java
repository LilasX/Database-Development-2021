package database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bus.*;

/**
 * Manages the vehicles in the ElecVehicle database and 
 * provides the public services to manage the database from the class ElectricVehicle.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see SingletonDBConnection#getConnection()
 * @see ElectricVehicle
 */
public class ElectricVehicleDB
{
	static private Connection myConnection;
	static private String mySQLStatement = null;	
	static private String mySQLQuery = null;
	static private Statement myStatement = null;
	static private ResultSet myResultSet = null;
	static private ElectricVehicle ev = null;
	
	/**
	 * Adds a ElectricVehicle into the database of ElectricVehicleDB
	 * by using the SQL command Insert.
	 * 
	 * @param v the ElectricVehicle to add in the database.
	 * @return 1 if added successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int insert(ElectricVehicle v) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "Insert into ElecVehicle(serialNumber, made, model, dateOfManufacture, tripCounter, kwPowerConsumed)  values(\'"
				+ v.getSerialNumber() + "\', \'" + v.getMade() + "\', \'" + v.getModel() + "\', \'" + v.getDateOfManufacture() + "\'," + v.getTripCounter() + ", " + v.getKwPowerConsumed() + ")";
		
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
	 * Modifies a ElectricVehicle in the database of ElectricVehicleDB
	 * by using the SQL command Update.
	 * Changes the made, the model and the date of manufacture of the ElectricVehicle in the database.
	 * 
	 * @param v the ElectricVehicle to modify in the database.
	 * @return 1 if modified successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int update(ElectricVehicle v) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "UPDATE ElecVehicle set made = \'" + v.getMade()  +  "\', model = \'" + v.getModel() + "\', dateOfManufacture = \'" + v.getDateOfManufacture() + "\' WHERE serialNumber = " +  v.getSerialNumber();
		
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
		
		mySQLStatement = "Delete FROM ElecVehicle WHERE serialNumber = "  + id;
		
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
	 * @return the ElectricVehicle found by serial number
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see java.sql.SQLException
	 */
	public static ElectricVehicle search(Long id) throws SQLException, NumberFormatException, RaiseException
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLQuery = "SELECT serialNumber, made, model, dateOfManufacture, tripCounter, kwPowerConsumed FROM ElecVehicle WHERE serialNumber = " + id ;
		
		myStatement = myConnection.createStatement();
		
		myResultSet = myStatement.executeQuery(mySQLQuery);
		
		if(myResultSet.next()) 
		{
			ev = new ElectricVehicle(Long.parseLong(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), myResultSet.getDate(4), Integer.parseInt(myResultSet.getString(5)), Double.parseDouble(myResultSet.getString(6)));
		}		
		else
		{
			ev = null;
		}
		return ev;
	}
	
	/**
	 * Displays all vehicles in the database using the SQL command Select.
	 * 
	 * @return an ArrayList of ElectricVehicle which represents the list of ElectricVehicle in the database
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
	public static ArrayList<ElectricVehicle> select() throws SQLException, NumberFormatException, RaiseException
	{
		ArrayList<ElectricVehicle> myList = new ArrayList<ElectricVehicle>();
		
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLQuery = "Select serialNumber as \"Serial Number\", made as \"Made\", model as \"Model\", to_char(dateOfManufacture,\'YYYY-MM-DD\') as \"DateOfManufacture\", tripCounter as \"Trip Counter\", kwPowerConsumed as \"Kw Power Consumed\" from ElecVehicle";
		
		myStatement = myConnection.createStatement();
		
		myResultSet = myStatement.executeQuery(mySQLQuery);
		
		
		while(myResultSet.next()) 
		{
			ev = new ElectricVehicle(Long.parseLong(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), myResultSet.getDate(4), Integer.parseInt(myResultSet.getString(5)), Double.parseDouble(myResultSet.getString(6)));
			
			myList.add(ev);
		}
		
		return myList;
	}
	
	/**
	 * Makes trip with a ElectricVehicle in the database of ElectricVehicleDB
	 * by using the SQL command Update.
	 * Changes the trip counter and the fuel consumed of the ElectricVehicle in the database.
	 * 
	 * @param v the ElectricVehicle to make trip in the database.
	 * @return 1 if the vehicle make trip successfully otherwise 0
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see java.sql.SQLException
	 */
	public static int makeTrip(ElectricVehicle v) throws SQLException 
	{
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLStatement = "UPDATE ElecVehicle set tripCounter = " + v.getTripCounter()  +  ", kwPowerConsumed = " + v.getKwPowerConsumed() + " WHERE serialNumber = " +  v.getSerialNumber();
		
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
	 * Displays and sorts all vehicles in the database by mileage efficiency
	 * using the SQL command Select and Order By.
	 * 
	 * @return an ArrayList of ElectricVehicle which represents the sorted list of ElectricVehicle in the database
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
	public static ArrayList<ElectricVehicle> sort() throws SQLException, NumberFormatException, RaiseException 
	{
		ArrayList<ElectricVehicle> myList = new ArrayList<ElectricVehicle>();
		
		myConnection = SingletonDBConnection.getInstance().getConnection();
		
		mySQLQuery = "Select serialNumber as \"Serial Number\", made as \"Made\", model as \"Model\", to_char(dateOfManufacture,\'YYYY-MM-DD\') as \"DateOfManufacture\", tripCounter as \"Trip Counter\", kwPowerConsumed as \"Kw Power Consumed\" from ElecVehicle";
		
		myStatement = myConnection.createStatement();
		
		myResultSet = myStatement.executeQuery(mySQLQuery + " ORDER BY tripCounter/kwPowerConsumed DESC");
		
		
		while(myResultSet.next()) 
		{
			ev = new ElectricVehicle(Long.parseLong(myResultSet.getString(1)), myResultSet.getString(2), myResultSet.getString(3), myResultSet.getDate(4), Integer.parseInt(myResultSet.getString(5)), Double.parseDouble(myResultSet.getString(6)));
			
			myList.add(ev);
		}
		
		return myList;
	}
}
