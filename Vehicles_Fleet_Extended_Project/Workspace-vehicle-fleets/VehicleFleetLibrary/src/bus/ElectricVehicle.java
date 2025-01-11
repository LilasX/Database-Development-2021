package bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import database.ElectricVehicleDB;

/**
 * Represents an electric vehicle in the vehicles' fleet factory.
 * Inherits the attributes and methods of the superclass Vehicle
 * and also has the attribute kwPowerConsumed which represents the energy consumed by this ElectricVehicle.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see Vehicle
 */
public class ElectricVehicle extends Vehicle
{
	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Quantity of energy consumed measured in kilowatts by this Electric Vehicle.
	 */
	private Double kwPowerConsumed;
	
	/**
	 * Default Constructor of class ElectricVehicle which inherits from the constructor of the superclass Vehicle
	 * and all variables are set to their default value.
	 * @see Vehicle#Vehicle()
	 */
	public ElectricVehicle()
	{
		super();
		this.kwPowerConsumed = 0.0;
	}
	
	/**
	 * Inherits the serial number, the made, the model, the date of manufacture, the trip counter of the superclass Vehicle
	 * and specifies the value of the kilowatt power consumed of this ElectricVehicle created.
	 * 
	 * @param serialNumber the serial number of this ElectricVehicle as a Long
	 * @param made the made of this ElectricVehicle as a String
	 * @param model the model of this ElectricVehicle as a String
	 * @param dateOfManufacture the date of manufacture of this ElectricVehicle as a Date
	 * @param tripCounter the trip counter of this ElectricVehicle as an Integer
	 * @param kwPowerConsumed the kilowatt power consumed by this ElectricVehicle as a Double
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see Vehicle#Vehicle(Long, String, String, Date, Integer)
	 */
	public ElectricVehicle(Long serialNumber, String made, String model, Date dateOfManufacture, Integer tripCounter, Double kwPowerConsumed) throws RaiseException
	{
		super(serialNumber, made, model, dateOfManufacture,tripCounter);
		this.setKwPowerConsumed(kwPowerConsumed);
	}
	
	/**
	 * Inherits the serial number, the made, the model, the date of manufacture, the trip counter, the type from the superclass Vehicle
	 * and specifies the value of the kilowatt power consumed of this ElectricVehicle created.
	 * 
	 * @param serialNumber the serial number of this ElectricVehicle as a Long
	 * @param made the made of this ElectricVehicle as a String
	 * @param model the model of this ElectricVehicle as a String
	 * @param dateOfManufacture the date of manufacture of this ElectricVehicle as a Date
	 * @param tripCounter the trip counter of this ElectricVehicle as an Integer
	 * @param type the type of this ElectricVehicle as an EnumType
	 * @param kwPowerConsumed the kilowatt power consumed by this ElectricVehicle as a Double
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see Vehicle#Vehicle(Long, String, String, Date, Integer, EnumType)
	 */
	public ElectricVehicle(Long serialNumber, String made, String model, Date dateOfManufacture, Integer tripCounter, EnumType type, Double kwPowerConsumed) throws RaiseException
	{
		super(serialNumber, made, model, dateOfManufacture, tripCounter, type);
		this.setKwPowerConsumed(kwPowerConsumed);
	}
	
	/**
	 * Gets the value of kilowatt power consumed by this ElectricVehicle.
	 * The kilowatt power consumed refers to the energy used by electric vehicles.
	 * 
	 * @return A Double which represents this ElectricVehicle's kwPowerConsumed
	 * @see ElectricVehicle#kwPowerConsumed
	 */
	public Double getKwPowerConsumed()
	{
		return kwPowerConsumed;
	}

	/**
	 * Registers the value of kilowatt power consumed by this ElectricVehicle.
	 * 
	 * @param kwPowerConsumed the field kwPowerConsumed to modify
	 * @throws RaiseException if the argument is negative or 0.
	 * @see ElectricVehicle#kwPowerConsumed
	 * @see RaiseException
	 * @see Validator#isInRange(double)
	 * @see Validator#isDouble(String)
	 */
	public void setKwPowerConsumed(Double kwPowerConsumed) throws RaiseException
	{
		Validator.isDouble(kwPowerConsumed.toString());
		Validator.isInRange(kwPowerConsumed);
		this.kwPowerConsumed = kwPowerConsumed;
	}

	@Override
	public String toString()
	{
		@SuppressWarnings("resource")
		Formatter formatter = new Formatter();
        formatter.format("%.2f", this.getMilesPerEnergyConsumed());
		return "ElectricVehicle [Serial Number: " + super.getSerialNumber() + "  -  Made: " + this.getMade() + "  -  Model: " + this.getModel() + "  -  Date of Manufacture: " + this.getDateOfManufacture() + "  -  Trip Counter: " + this.getTripCounter() + " - Kilowatt Power Consumed: " + kwPowerConsumed + " - Mileage Efficiency: " + formatter.toString() + "]";
	}
	
	@Override
	public double getMilesPerEnergyConsumed()
	{
		return (this.getTripCounter()/this.getKwPowerConsumed());
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
			this.setKwPowerConsumed(2.22);
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
			this.setKwPowerConsumed(energyConsumed);
		}
		catch (RaiseException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Adds a ElectricVehicle object in the database of ElectricVehicleDB.
	 * 
	 * @param element the ElectricVehicle object to add in ElectricVehicleDB database.
	 * @return the insert method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see ElectricVehicleDB#insert(ElectricVehicle)
	 * @see java.sql.SQLException
	 */
	public static int add(ElectricVehicle element) throws SQLException 
	{
		return ElectricVehicleDB.insert(element);
	}
	
	/**
	 * Modifies a ElectricVehicle object in the database of ElectricVehicleDB.
	 * 
	 * @param element the ElectricVehicle object to modify in ElectricVehicleDB database.
	 * @return the update method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see ElectricVehicleDB#update(ElectricVehicle)
	 * @see java.sql.SQLException
	 */
	public static int update(ElectricVehicle element) throws SQLException 
	{
		return ElectricVehicleDB.update(element);
	}
	
	/**
	 * Removes a ElectricVehicle object in the database of ElectricVehicleDB by their serial number.
	 * 
	 * @param id the ElectricVehicle object's serial number to remove in ElectricVehicleDB database.
	 * @return the delete method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see ElectricVehicleDB#delete(Long)
	 * @see java.sql.SQLException
	 */
	public static int remove(Long id) throws SQLException 
	{
		return ElectricVehicleDB.delete(id);
	}
	
	/**
	 * Searches a ElectricVehicle object in the database of ElectricVehicleDB by their serial number.
	 * 
	 * @param id the ElectricVehicle object's serial number to search for in ElectricVehicleDB database.
	 * @return the search method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see ElectricVehicleDB#search(Long)
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static ElectricVehicle search(Long id) throws SQLException, NumberFormatException, RaiseException
	{
		return ElectricVehicleDB.search(id);
	}
	
	/**
	 * Displays the table containing the data of ElectricVehicle objects in the database of ElectricVehicleDB.
	 * 
	 * @return the select method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see ElectricVehicleDB#select()
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static ArrayList<ElectricVehicle> getData() throws SQLException, NumberFormatException, RaiseException
	{
		return ElectricVehicleDB.select();
	}
	
	/**
	 * Modifies the values of tripCounter and kwPowerConsumed of the ElectricVehicle in the database.
	 * 
	 * @param vehicle the ElectricVehicle object to modify in ElectricVehicleDB database.
	 * @return the makeTrip method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see ElectricVehicleDB#makeTrip(ElectricVehicle)
	 * @see java.sql.SQLException
	 */
	public static int makeTripDB(ElectricVehicle vehicle) throws SQLException
	{
		return ElectricVehicleDB.makeTrip(vehicle);
	}
	
	/**
	 * Displays and sorts the table containing the data of ElectricVehicle objects in the database of ElectricVehicleDB.
	 * 
	 * @return the sort method in ElectricVehicleDB
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws NumberFormatException if the application has attempted to convert a string to one of the numeric types, 
	 * 		   but that the string does not have the appropriate format.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see ElectricVehicleDB#sort()
	 * @see NumberFormatException
	 * @see RaiseException
	 * @see java.sql.SQLException
	 */
	public static ArrayList<ElectricVehicle> getDataSorted() throws SQLException, NumberFormatException, RaiseException
	{
		return ElectricVehicleDB.sort();
	}
}
