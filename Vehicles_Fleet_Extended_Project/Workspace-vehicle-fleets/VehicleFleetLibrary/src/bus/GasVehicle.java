package bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import database.GasolineVehicleDB;

/**
 * Represents a gasoline vehicle in the vehicles' fleet factory.
 * Inherits the attributes and methods of the superclass Vehicle
 * and also has the attribute fuelConsumed which represents the energy consumed by this GasVehicle.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see Vehicle
 */
public class GasVehicle extends Vehicle
{
	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Quantity of energy consumed in fuel by this Gasoline Vehicle.
	 */
	private Double fuelConsumed;
	
	/**
	 * Default Constructor of class GasVehicle which inherits from the constructor of the superclass Vehicle
	 * and all variables are set to their default value.
	 * @see Vehicle#Vehicle()
	 */
	public GasVehicle()
	{
		super();
		this.fuelConsumed = 0.0;
	}
	
	/**
	 * Inherits the serial number, the made, the model, the date of manufacture, the trip counter of the superclass Vehicle
	 * and specifies the value of the fuel consumed of this GasVehicle created.
	 * 
	 * @param serialNumber the serial number of this GasVehicle as a Long
	 * @param made the made of this GasVehicle as a String
	 * @param model the model of this GasVehicle as a String
	 * @param dateOfManufacture the date of manufacture of this GasVehicle as a Date
	 * @param tripCounter the trip counter of this GasVehicle as an Integer
	 * @param fuelConsumed the fuel consumed by this GasVehicle as a Double
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see Vehicle#Vehicle(Long, String, String, Date, Integer)
	 */
	public GasVehicle(Long serialNumber, String made, String model, Date dateOfManufacture, Integer tripCounter, Double fuelConsumed) throws RaiseException
	{
		super(serialNumber, made, model, dateOfManufacture,tripCounter);
		this.setFuelConsumed(fuelConsumed);
	}
	
	/**
	 * Inherits the serial number, the made, the model, the date of manufacture, the trip counter, the type from the superclass Vehicle
	 * and specifies the value of the fuel consumed of this GasVehicle created.
	 * 
	 * @param serialNumber the serial number of this GasVehicle as a Long
	 * @param made the made of this GasVehicle as a String
	 * @param model the model of this GasVehicle as a String
	 * @param dateOfManufacture the date of manufacture of this GasVehicle as a Date
	 * @param tripCounter the trip counter of this GasVehicle as an Integer
	 * @param type the type of this GasVehicle as an EnumType
	 * @param fuelConsumed the fuel consumed by this GasVehicle as a Double
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see Vehicle#Vehicle(Long, String, String, Date, Integer, EnumType)
	 */
	public GasVehicle(Long serialNumber, String made, String model, Date dateOfManufacture, Integer tripCounter, EnumType type, Double fuelConsumed) throws RaiseException
	{
		super(serialNumber, made, model, dateOfManufacture, tripCounter, type);
		this.setFuelConsumed(fuelConsumed);
	}
	
	/**
	 * Gets the value of fuel consumed by this GasVehicle.
	 * The fuel consumed refers to the energy used by gasoline vehicles.
	 * 
	 * @return A Double which represents this GasVehicle's fuelConsumed
	 * @see GasVehicle#fuelConsumed
	 */
	public Double getFuelConsumed()
	{
		return fuelConsumed;
	}

	/**
	 * Registers the value of fuel consumed by this GasVehicle.
	 * 
	 * @param fuelConsumed the field fuelConsumed to modify
	 * @throws RaiseException if the argument is negative or 0.
	 * @see GasVehicle#fuelConsumed
	 * @see RaiseException
	 * @see Validator#isInRange(double)
	 * @see Validator#isDouble(String)
	 */
	public void setFuelConsumed(Double fuelConsumed) throws RaiseException
	{
		Validator.isDouble(fuelConsumed.toString());
		Validator.isInRange(fuelConsumed);
		this.fuelConsumed = fuelConsumed;
	}
	
	@Override
	public String toString()
	{
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();
        formatter.format("%.2f", this.getMilesPerEnergyConsumed());
		return "GasVehicle [Serial Number: " + super.getSerialNumber() + "  -  Made: " + this.getMade() + "  -  Model: " + this.getModel() + "  -  Date of Manufacture: " + this.getDateOfManufacture() + "  -  Trip Counter: " + this.getTripCounter() + " - Fuel Consumed: " + fuelConsumed + " - Mileage Efficiency: " + formatter.toString() + "]";
	}
	
	@Override
	public double getMilesPerEnergyConsumed()
	{
		return (this.getTripCounter()/this.getFuelConsumed());
	}

	@Override
	public void makeTrip()
	{
		try
		{
			this.setTripCounter(100);
		}
		catch (RaiseException ex)
		{
			System.out.println(ex.getMessage());
		}
		try
		{
			this.setFuelConsumed(7.77);
		}
		catch (RaiseException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void makeTrip(int tripCounter, double energyConsumed)
	{
		try
		{
			this.setTripCounter(tripCounter);
		}
		catch (RaiseException ex)
		{
			System.out.println(ex.getMessage());
		}
		try
		{
			this.setFuelConsumed(energyConsumed);
		}
		catch (RaiseException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Adds a GasVehicle object in the database of GasolineVehicleDB.
	 * 
	 * @param element the GasVehicle object to add in GasolineVehicleDB database.
	 * @return the insert method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see GasolineVehicleDB#insert(GasVehicle)
	 * @see java.sql.SQLException
	 */
	public static int add(GasVehicle element) throws SQLException 
	{
		return GasolineVehicleDB.insert(element);
	}
	
	/**
	 * Modifies a GasVehicle object in the database of GasolineVehicleDB.
	 * 
	 * @param element the GasVehicle object to modify in GasolineVehicleDB database.
	 * @return the update method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see GasolineVehicleDB#update(GasVehicle)
	 * @see java.sql.SQLException
	 */
	public static int update(GasVehicle element) throws SQLException 
	{
		return GasolineVehicleDB.update(element);
	}
	
	/**
	 * Removes a GasVehicle object in the database of GasolineVehicleDB by their serial number.
	 * 
	 * @param id the GasVehicle object's serial number to remove in GasolineVehicleDB database.
	 * @return the delete method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see GasolineVehicleDB#delete(Long)
	 * @see java.sql.SQLException
	 */
	public static int remove(Long id) throws SQLException 
	{
		return GasolineVehicleDB.delete(id);
	}
	
	/**
	 * Searches a GasVehicle object in the database of GasolineVehicleDB by their serial number.
	 * 
	 * @param id the GasVehicle object's serial number to search for in GasolineVehicleDB database.
	 * @return the search method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasolineVehicleDB#search(Long)
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static GasVehicle search(Long id) throws SQLException, NumberFormatException, RaiseException
	{
		return GasolineVehicleDB.search(id);
	}
	
	/**
	 * Displays the table containing the data of GasVehicle objects in the database of GasolineVehicleDB.
	 * 
	 * @return the select method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasolineVehicleDB#select()
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static ArrayList<GasVehicle> getData() throws SQLException, NumberFormatException, RaiseException
	{
		return GasolineVehicleDB.select();
	}
	
	/**
	 * Modifies the values of tripCounter and fuelConsumed of the GasVehicle in the database.
	 * 
	 * @param vehicle the GasVehicle object to modify in GasolineVehicleDB database.
	 * @return the makeTrip method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see GasolineVehicleDB#makeTrip(GasVehicle)
	 * @see java.sql.SQLException
	 */
	public static int makeTripDB(GasVehicle vehicle) throws SQLException
	{
		return GasolineVehicleDB.makeTrip(vehicle);
	}
	
	/**
	 * Displays and sorts the table containing the data of GasVehicle objects in the database of GasolineVehicleDB.
	 * 
	 * @return the sort method in GasolineVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasolineVehicleDB#sort()
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static ArrayList<GasVehicle> getDataSorted() throws SQLException, NumberFormatException, RaiseException
	{
		return GasolineVehicleDB.sort();
	}
}
