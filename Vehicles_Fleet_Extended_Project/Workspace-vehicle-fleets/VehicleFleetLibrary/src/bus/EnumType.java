package bus;

/**
 * Defines a collection of constants of Vehicle's type.
 * Represents the type of motor a Vehicle could use.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see Vehicle#getType()
 * @see Vehicle#setType(EnumType)
 * @see GasVehicle
 * @see ElectricVehicle
 */
public enum EnumType
{
	/**
	 * Gasoline type vehicle
	 */
	GasVehicle, 
	/**
	 * Electric type vehicle
	 */
	ElectricVehicle, 
	/**
	 * Undefined type vehicle
	 */
	Undefined
}

