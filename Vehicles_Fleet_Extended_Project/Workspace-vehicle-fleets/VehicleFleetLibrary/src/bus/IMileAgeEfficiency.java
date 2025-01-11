package bus;

/**
 * This interface is implemented by the abstract class Vehicle
 * and its subclasses will provide an appropriate implementation for the methods
 * getMilePerEnergyConsumed(), makeTrip() and makeTrip(int tripCounter, double energyConsumed).
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see Vehicle
 * @see GasVehicle#getMilesPerEnergyConsumed()
 * @see GasVehicle#makeTrip()
 * @see GasVehicle#makeTrip(int, double)
 * @see ElectricVehicle#getMilesPerEnergyConsumed()
 * @see ElectricVehicle#makeTrip()
 * @see ElectricVehicle#makeTrip(int, double)
 */
public interface IMileAgeEfficiency
{
	/**
	 * Gets the mileage efficiency of the Vehicle and this method will be overridden 
	 * by the subclasses of the abstract class Vehicle.
	 * 
	 * @return A double by dividing the value of tripCounter with the value of the energy consumed
	 * which will depend on the subclass of Vehicle, which represents the mileage efficiency.
	 * @see GasVehicle#getMilesPerEnergyConsumed()
	 * @see ElectricVehicle#getMilesPerEnergyConsumed()
	 */
	public abstract double getMilesPerEnergyConsumed ();
	
	/**
	 * Initializes tripCounter and the appropriate type of energy consumed
	 * to their defined initial values depending on the type of the Vehicle.
	 * 
	 * @see Vehicle#setTripCounter(Integer)
	 * @see GasVehicle#setFuelConsumed(Double)
	 * @see GasVehicle#makeTrip()
	 * @see ElectricVehicle#setKwPowerConsumed(Double)
	 * @see ElectricVehicle#makeTrip()
	 */
	public abstract void makeTrip();
	
	/**
	 * Sets the field tripCounter which is the distance traveled by a Vehicle
	 * and sets the appropriate type of energy consumed depending on the type of the Vehicle.
	 * This method will be overridden by Vehicle's subclasses.
	 * 
	 * @param tripCounter the field tripCounter to set.
	 * @param energyConsumed the double overridden by Vehicle's subclasses's own energy consumed variable.
	 * @see Vehicle#setTripCounter(Integer)
	 * @see GasVehicle#setFuelConsumed(Double)
	 * @see GasVehicle#makeTrip(int, double)
	 * @see ElectricVehicle#setKwPowerConsumed(Double)
	 * @see ElectricVehicle#makeTrip(int, double)
	 */
	public abstract void makeTrip(int tripCounter, double energyConsumed);
}

