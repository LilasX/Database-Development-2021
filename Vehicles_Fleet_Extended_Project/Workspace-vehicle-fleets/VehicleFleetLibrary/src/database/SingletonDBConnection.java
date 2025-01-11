package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents the connection made when connecting to the database.
 * The connection is a singleton to prevent having many instances during the execution of the application.
 *  
 * @author Lilas Guan
 * @version 2021-12-14
 */
public class SingletonDBConnection
{
	private static SingletonDBConnection singletonDB = null;
	
	private Connection myConnection = null;
	
	/**
	 * Creates a single instance of the class SingletonDBConnection
	 * which is used to connect to the database.
	 * 
	 * @return A singleton used for the connection to the database
	 */
	public static SingletonDBConnection getInstance()
	{
		if(singletonDB == null) 
		{
			singletonDB = new SingletonDBConnection();
		}
		return singletonDB;
	}
	
	/**
	 * Connects to the database and creates a database user named vehiclefleetdb
	 * which is used to manage the fleet of vehicles.
	 * 
	 * @return A Connection which represents this SingletonDBConnection's myConnection.
	 * @see java.sql.Connection
	 * @see java.sql.DriverManager
	 * @see java.sql.SQLException
	 */
	public Connection getConnection()
	{
		if(singletonDB.myConnection == null)
		{
			String userName, password, service, url;
			
			 userName = "vehiclefleetdb";
			 password = "123";
			 service = "localhost";
			
			 url = "jdbc:oracle:thin:";
			
			try
			{
		        myConnection = DriverManager.getConnection(url + userName + "/" + password + "@" + service);
		 	    
		        System.out.println("Connection successful");
			} 
			catch (SQLException ex) 
			{
				  ex.printStackTrace();
				  System.out.println("Connection failed");
			}
			
		}
		
		return myConnection;		
	}
}
