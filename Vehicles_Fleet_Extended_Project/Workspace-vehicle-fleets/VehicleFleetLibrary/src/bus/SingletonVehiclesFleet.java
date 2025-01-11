package bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the collection of Vehicle in the Vehicles' Fleet Factory.
 * Stores an ArrayList of Vehicle and provides the public services 
 * to manage the encapsulated fleet of vehicles.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 */
public class SingletonVehiclesFleet
{
	private static SingletonVehiclesFleet singleton = null;
	
	private ArrayList<Vehicle> fleetOfVehicles;
	
	/**
	 * Instantiates an ArrayList object and assigns it to the ArrayList variable of type Vehicle.
	 * 
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see java.util.ArrayList
	 */
	private SingletonVehiclesFleet()
	{
		fleetOfVehicles = new ArrayList<Vehicle>();
	}
	
	/**
	 * Creates a single instance of the class SingletonVehiclesFleet
	 * which is used to manage the encapsulated fleet of vehicles.
	 * 
	 * @return A singleton used for collecting data of Vehicle objects
	 */
	public static SingletonVehiclesFleet getSingleton()
	{
		if(singleton == null) 
		{
			singleton = new SingletonVehiclesFleet();
		}
		return singleton;
	}
	
	/**
	 * Gets the list of Vehicle from this SingletonVehiclesFleet.
	 * The fleet of vehicles refers to the collection of vehicles encapsulated in a list.
	 * 
	 * @return An ArrayList of type Vehicle which represents this SingletonVehiclesFleet's fleetOfVehicles
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see Vehicle
	 * @see java.util.ArrayList
	 */
	public ArrayList<Vehicle> getVehicles()
	{
		return fleetOfVehicles;
	}
	
	/**
	 * Gets the list of GasVehicle from this SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @return An ArrayList of type GasVehicle which represents the list of gas vehicles in this SingletonVehiclesFleet's fleetOfVehicles
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#getVehicles()
	 * @see GasVehicle
	 * @see java.util.ArrayList
	 */
	public ArrayList<GasVehicle> getGasolineVehicles() 
	{
		ArrayList<GasVehicle> list = new ArrayList<GasVehicle>();
		for(Vehicle item : singleton.getVehicles())
		{
			if(item instanceof GasVehicle)
			{
				list.add((GasVehicle)item);	
			}
		}	
		return list;
	}
	
	/**
	 * Gets the list of ElectricVehicle from this SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @return An ArrayList of type ElectricVehicle which represents the list of electric vehicles in this SingletonVehiclesFleet's fleetOfVehicles
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#getVehicles()
	 * @see ElectricVehicle
	 * @see java.util.ArrayList
	 */
	public ArrayList<ElectricVehicle> getElectricVehicles() 
	{
		ArrayList<ElectricVehicle> list = new ArrayList<ElectricVehicle>();
		for(Vehicle item : singleton.getVehicles())
		{
			if(item instanceof ElectricVehicle)
			{
				list.add((ElectricVehicle)item);	
			}
		}	
		return list;
	}
	
	/**
	 * Adds an element that inherits the attributes of Vehicle in this SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @param object the Vehicle object to add to this SingletonVehiclesFleet's fleetOfVehicles.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see java.util.ArrayList
	 */
	public void add(Vehicle object)
	{
		singleton.fleetOfVehicles.add(object);
	}
	
	/**
	 * Removes an element that inherits the attributes of Vehicle in this SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @param object the Vehicle object to remove from this SingletonVehiclesFleet's fleetOfVehicles.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see java.util.ArrayList
	 */
	public void remove(Vehicle object)
	{
		singleton.fleetOfVehicles.remove(object);
	}
	
	/**
	 * Searches a Vehicle object by serial number through this SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @param serialNumber the Long to compare to an existing serial number in this SingletonVehiclesFleet's fleetOfVehicles.
	 * @return The Vehicle object found which their serial number matched the serialNumber. 
	 * 		   If the Vehicle object is not found, return null.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles 
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.ArrayList
	 */
	public Vehicle search(Long serialNumber)
	{
		Vehicle searchedVehicle = null;
		
		for(Vehicle aVehicle:singleton.fleetOfVehicles)
		{
			if(aVehicle.getSerialNumber().equals(serialNumber))
			{
				searchedVehicle = aVehicle;
				break;
			}
		}
		
		return searchedVehicle;
	}
	
	/**
	 * Searches a Vehicle object by model through this SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @param model the String to compare to an existing model in this SingletonVehiclesFleet's fleetOfVehicles.
	 * @return The Vehicle object found which their model matched the model. 
	 * 		   If the Vehicle object is not found, return null.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles 
	 * @see Vehicle#getModel()
	 * @see java.util.ArrayList
	 */
	public Vehicle search(String model)
	{
		Vehicle searchedVehicle = null;
		
		for(Vehicle aVehicle:singleton.fleetOfVehicles)
		{
			if(aVehicle.getModel().equals(model))
			{
				searchedVehicle = aVehicle;
				break;
			}
		}
		
		return searchedVehicle;
	}
	
	/**
	 * Writes the data collected in the file by calling the FileManager.
	 * 
	 * @param list the ArrayList of type Vehicle of the file which stores the encapsulated fleet of vehicles.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#getVehicles()
	 * @see FileManager#serialize(ArrayList)
	 * @see java.util.ArrayList
	 * @see java.io.IOException
	 */
	public void serialize (ArrayList<Vehicle> list) throws IOException
	{
		list = SingletonVehiclesFleet.getSingleton().getVehicles();
		FileManager.serialize(list);
	}
	
	/**
	 * Reads the data collected from the file by calling the FileManager.
	 * 
	 * @return the ArrayList of type Vehicle which stores the encapsulated fleet of vehicles from the file.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @throws ClassNotFoundException if no definition for the class 
	 * 		   with the specified name could be found when loading in a class through its string name.
	 * @see FileManager#deSerialize()
	 * @see java.util.ArrayList
	 * @see java.io.IOException
	 */
	public ArrayList<Vehicle> deSerialize() throws IOException, ClassNotFoundException
	{
		return FileManager.deSerialize();
	}
	
	/**
	 * Sorts this VehiclesFleet's fleetOfVehicles in ascending order by serial number.
	 * 
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see Vehicle#getSerialNumber()
	 * @see SerialNumberPredicate#compare(Vehicle, Vehicle)
	 * @see java.util.Collections
	 */
	public void sortBySerialNumber()
	{
		Collections.sort(singleton.fleetOfVehicles, new SerialNumberPredicate());
	}
	
	/**
	 * Sorts this VehiclesFleet's fleetOfVehicles in descending order by mileage efficiency.
	 * 
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see Vehicle#getMilesPerEnergyConsumed()
	 * @see MileageEfficiencyPredicate#compare(Vehicle, Vehicle)
	 * @see java.util.Collections
	 */
	public void sortByMileageEfficiency()
	{
		Collections.sort(singleton.fleetOfVehicles, Collections.reverseOrder(new MileageEfficiencyPredicate()));
	}
	
	/**
	 * Sorts this VehiclesFleet's fleetOfVehicles in ascending order by serial number.
	 * 
	 * @param predict the comparator used to sort this VehiclesFleet's fleetOfVehicles.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see Vehicle#getSerialNumber()
	 * @see SerialNumberPredicate#compare(Vehicle, Vehicle)
	 * @see java.util.Collections
	 */
	public void sort(SerialNumberPredicate predict)
	{
		Collections.sort(singleton.fleetOfVehicles, predict);
	}
	
	/**
	 * Sorts this VehiclesFleet's fleetOfVehicles in descending order by mileage efficiency.
	 * 
	 * @param predict the comparator used to sort this VehiclesFleet's fleetOfVehicles.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#fleetOfVehicles
	 * @see Vehicle#getMilesPerEnergyConsumed()
	 * @see MileageEfficiencyPredicate#compare(Vehicle, Vehicle)
	 * @see java.util.Collections
	 */
	public void sort(MileageEfficiencyPredicate predict)
	{
		Collections.sort(singleton.fleetOfVehicles, Collections.reverseOrder(predict));
	}
}
