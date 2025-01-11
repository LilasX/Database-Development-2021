package bus;

import java.util.Comparator;

/**
 * Used to compare the variables of serialNumber from the subclasses of Vehicle 
 * to determine the order of a list
 * by implementing the interface Comparator.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see  Vehicle#getSerialNumber()
 * @see java.util.Comparator
 */
public class SerialNumberPredicate implements Comparator<Vehicle>
{
	/**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     *
     * @param v1 the first String to be compared.
     * @param v2 the second String to be compared.
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
		if(v1.getSerialNumber().compareTo(v2.getSerialNumber()) < 0) return -1;
		else if(v1.getSerialNumber().compareTo(v2.getSerialNumber()) > 0) return 1;
		else return 0;
	}
}
