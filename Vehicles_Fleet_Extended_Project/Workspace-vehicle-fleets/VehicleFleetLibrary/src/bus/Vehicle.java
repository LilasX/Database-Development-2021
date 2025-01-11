package bus;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a vehicle in the vehicles' fleet factory.
 * A vehicle has a serial number, a made, a model, a date of manufacture, a trip counter and a type.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 */
public abstract class Vehicle extends Object implements IMileAgeEfficiency, Serializable
{
	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Serial Number of this Vehicle.
	 */
	private Long serialNumber;
	/**
	 * Make of this Vehicle (brand of this Vehicle).
	 */
	private String made;
	/**
	 * Model of this Vehicle (specific product).
	 */
	private String model;
	/**
	 * Date of Manufacture of this Vehicle.
	 */
	private Date dateOfManufacture;
	/**
	 * Trip counter of this Vehicle. 
	 */
	private Integer tripCounter;
	/**
	 * The type of this Vehicle.
	 */
	private EnumType type;
	
	/**
	 * Default Constructor of class Vehicle which all variables are set to their default value.
	 */
	public Vehicle()
	{
		super();
		this.serialNumber = 0L;
		this.made = "Undefined";
		this.model = "Undefined";
		this.dateOfManufacture = new Date(0);
		this.tripCounter = 0;
		this.type = EnumType.Undefined;
	}
	
	/**
	 * Vehicle constructor specifying the serial number, the made, the model, the date of manufacture and the trip counter
	 * of this Vehicle created.
	 * 
	 * @param serialNumber the serial number of this Vehicle as a Long
	 * @param made the made of this Vehicle as a String
	 * @param model the model of this Vehicle as a String
	 * @param dateOfManufacture the date of manufacture of this Vehicle as a Date
	 * @param tripCounter the trip counter of this Vehicle as an Integer
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see RaiseException
	 */
	public Vehicle(Long serialNumber, String made, String model, Date dateOfManufacture, Integer tripCounter) throws RaiseException
	{
		super();
		this.setSerialNumber(serialNumber);
		this.setMade(made);
		this.setModel(model);
		this.setDateOfManufacture(dateOfManufacture);
		this.setTripCounter(tripCounter);
		this.type = EnumType.Undefined;
	}
	
	/**
	 * Vehicle constructor specifying the serial number, the made, the model, the date of manufacture, the trip counter
	 * and the type of this Vehicle created.
	 * 
	 * @param serialNumber the serial number of this Vehicle as a Long
	 * @param made the made of this Vehicle as a String
	 * @param model the model of this Vehicle as a String
	 * @param dateOfManufacture the date of manufacture of this Vehicle as a Date
	 * @param tripCounter the trip counter of this Vehicle as an Integer
	 * @param type the type of this Vehicle as an EnumType
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see RaiseException
	 */
	public Vehicle(Long serialNumber, String made, String model, Date dateOfManufacture, Integer tripCounter, EnumType type) throws RaiseException
	{
		super();
		this.setSerialNumber(serialNumber);
		this.setMade(made);
		this.setModel(model);
		this.setDateOfManufacture(dateOfManufacture);
		this.setTripCounter(tripCounter);
		this.setType(type);
	}
	
	/**
	 * Gets the serial number of this Vehicle.
	 * The serial number is used as primary key to uniquely identify each Vehicle in a list.
	 * 
	 * @return A Long which represents this Vehicle's serialNumber
	 * @see Vehicle#serialNumber
	 */
	public Long getSerialNumber()
	{
		return serialNumber;
	}

	/**
	 * Registers the serial number of this Vehicle.
	 * It can contains numbers only.
	 * It must not be 0.
	 * 
	 * @param serialNumber the field serialNumber to modify.
	 * @throws RaiseException if the argument does not contain only digits, is 0 or is negative.
	 * @see Vehicle#serialNumber
	 * @see RaiseException
	 * @see Validator#isDigit(String)
	 * @see Validator#isInRange(Long)
	 */
	public void setSerialNumber(Long serialNumber) throws RaiseException
	{
		Validator.isDigit(serialNumber.toString());
		Validator.isInRange(serialNumber);
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Gets the made of this Vehicle.
	 * The made refers to the brand of this Vehicle.
	 * 
	 * @return A String which represents this Vehicle's made
	 * @see Vehicle#made
	 */
	public String getMade()
	{
		return made;
	}

	/**
	 * Registers the made of this Vehicle.
	 * 
	 * @param made the field made to modify.
	 * @throws RaiseException if the argument does not contain only alphabet letters.
	 * @see Vehicle#made
	 * @see RaiseException
	 * @see Validator#isChar(String)
	 */
	public void setMade(String made) throws RaiseException
	{
		Validator.isChar(made);
		this.made = made;
	}
	
	/**
	 * Gets the model of this Vehicle.
	 * The model refers to the name of the Vehicle product.
	 * 
	 * @return model A String which represents this Vehicle's model
	 * @see Vehicle#model
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * Registers the model of this Vehicle.
	 * 
	 * @param model the field model to modify.
	 * @throws RaiseException if the argument does not contain only letters and/or digits.
	 * @see Vehicle#model
	 * @see RaiseException
	 * @see Validator#isLetterOrDigit(String)
	 */
	public void setModel(String model) throws RaiseException
	{
		Validator.isLetterOrDigit(model);
		this.model = model;
	}
	
	/**
	 * Gets the date of manufacture of this Vehicle.
	 * The date of manufacture refers to the date on which the Vehicle becomes the product as described.
	 * 
	 * @return sqlDate A Date which represents this Vehicle's date of manufacture in a SQL date format
	 * @see Vehicle#dateOfManufacture
	 * @see java.sql.Date
	 */
	public Date getDateOfManufacture()
	{
		java.sql.Date sqlDate = new java.sql.Date(dateOfManufacture.getTime());
		return sqlDate;
	}

	/**
	 * Registers the date of manufacture of this Vehicle in a SQL date format (YYYY-MM-DD).
	 * 
	 * @param dateOfManufacture the field dateOfManufacture to modify.
	 * @see Vehicle#dateOfManufacture
	 * @see java.sql.Date
	 */
	public void setDateOfManufacture(Date dateOfManufacture)
	{
		java.sql.Date sqlDate = new java.sql.Date(dateOfManufacture.getTime());
		this.dateOfManufacture = sqlDate;
	}
	
	/**
	 * Gets the trip counter of this Vehicle.
	 * The trip counter refers to the distance traveled by this Vehicle.
	 * 
	 * @return tripCounter An Integer which represents this Vehicle's tripCounter
	 * @see Vehicle#tripCounter
	 */
	public Integer getTripCounter()
	{
		return tripCounter;
	}
	
	/**
	 * Initializes the trip counter of this Vehicle.
	 * 
	 * @param tripCounter the field tripCounter to modify.
	 * @throws RaiseException if the argument is negative or 0.
	 * @see Vehicle#tripCounter
	 * @see RaiseException
	 * @see Validator#isInRange(int)
	 * @see Validator#isDigit(String)
	 */
	public void setTripCounter(Integer tripCounter) throws RaiseException
	{
		Validator.isDigit(tripCounter.toString());
		Validator.isInRange(tripCounter);
		this.tripCounter = tripCounter;
	}

	/**
	 * Gets the type of this Vehicle.
	 * The type refers to the type of motor this Vehicle uses.
	 * 
	 * @return type An EnumType which represents this Vehicle's type
	 * @see Vehicle#type
	 * @see EnumType
	 */
	public EnumType getType()
	{
		return type;
	}

	/**
	 * Registers the type of this Vehicle.
	 * 
	 * @param type the field type to modify.
	 * @see Vehicle#type
	 * @see EnumType
	 */
	public void setType(EnumType type)
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "Vehicle [serialNumber=" + serialNumber + ", made=" + made + ", model=" + model + ", dateOfManufacture="
				+ dateOfManufacture + ", tripCounter=" + tripCounter + ", type=" + type + "]";
	}
}
