package bus;

import java.util.Comparator;

/**
 * Used to compare the results of the method getMilePerUnitOfEnergyConsumed() 
 * from the interface IMileageEfficiency implemented by the abstract class Vehicle
 * which the method will be overridden by its subclasses,
 * to determine the order of a list
 * by implementing the interface Comparator.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see IMileAgeEfficiency#getMilesPerEnergyConsumed()
 * @see GasVehicle#getMilesPerEnergyConsumed()
 * @see ElectricVehicle#getMilesPerEnergyConsumed()
 * @see java.util.Comparator
 */
public class MileageEfficiencyPredicate implements Comparator<Vehicle>
{
	/**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param v1 the first double to be compared.
     * @param v2 the second double to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     * @throws NullPointerException if an argument is null and this
     *         comparator does not permit null arguments
     * @throws ClassCastException if the arguments' types prevent them from
     *         being compared by this comparator.
     * @see java.util.Comparator
     */
	@Override
	public int compare(Vehicle v1, Vehicle v2)
	{
		if(Double.compare(v1.getMilesPerEnergyConsumed(), v2.getMilesPerEnergyConsumed()) < 0) return -1;
		else if(Double.compare(v1.getMilesPerEnergyConsumed(), v2.getMilesPerEnergyConsumed()) > 0) return 1;
		else return 0;
	}
}
