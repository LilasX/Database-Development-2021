package bus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Represents the collection of Vehicle in the Vehicles' Fleet Factory.
 * Stores a HashMap that associates the serialNumber of the Vehicle object with the Vehicle object itself
 * and provides the public services to manage the collection.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 */
public class SingletonHashMapCollection
{
	private static SingletonHashMapCollection singleInstance = null;
	
	private HashMap<Long, Vehicle> vehicleCollection;
	
	/**
	 * Instantiates a HashMap object and assigns it to the HashMap variable 
	 * that associates the serialNumber of the Vehicle object with the Vehicle object itself.
	 * 
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see java.util.HashMap
	 */
	private SingletonHashMapCollection()
	{
		vehicleCollection = new HashMap<Long, Vehicle>();
	}
	
	/**
	 * Creates a single instance of the class SingletonHashMapCollection
	 * which is used to manage the collection.
	 * 
	 * @return A singleton used for collecting data with a HashMap
	 */
	public static SingletonHashMapCollection getSingleInstance() 
	{
		if(singleInstance == null)
	 	{
			singleInstance = new SingletonHashMapCollection();
	 	}		 
	 	return singleInstance;
	}	
	
	/**
	 * Gets the collection of key-value pairs from this SingletonHashMapCollection.
	 * Refers to the collection of vehicles encapsulated in a HashMap.
	 * 
	 * @return A HashMap which represents this SingletonHashMapCollection's vehicleCollection.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see Vehicle
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.HashMap
	 */
	public HashMap<Long, Vehicle> getVehicleList() 
	{
		return singleInstance.vehicleCollection;
	}	
	
	/**
	 * Gets the collection of key-value pairs from this SingletonHashMapCollection.
	 * Refers to the collection of gasoline vehicles encapsulated in a HashMap.
	 * 
	 * @return list of gasoline vehicles encapsulated in a HashMap 
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see SingletonHashMapCollection#getVehicleList()
	 * @see Vehicle
	 * @see GasVehicle
	 * @see java.util.HashMap
	 */
	public HashMap<Long, GasVehicle> getGasolineVehiclesList() 
	{
		HashMap<Long, GasVehicle> list = new HashMap<Long, GasVehicle>();
		for(Vehicle item : SingletonHashMapCollection.getSingleInstance().getVehicleList().values())
		{
			if(item instanceof GasVehicle)
			{
				list.put(item.getSerialNumber(), (GasVehicle)item);	
			}
		}	
		return list;
	}
	
	/**
	 * Gets the collection of key-value pairs from this SingletonHashMapCollection.
	 * Refers to the collection of electric vehicles encapsulated in a HashMap.
	 * 
	 * @return list of electric vehicles encapsulated in a HashMap
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see SingletonHashMapCollection#getVehicleList()
	 * @see Vehicle
	 * @see ElectricVehicle
	 * @see java.util.HashMap
	 */
	public HashMap<Long, ElectricVehicle> getElectricVehiclesList() 
	{
		HashMap<Long, ElectricVehicle> list = new HashMap<Long, ElectricVehicle>();
		for(Vehicle item : SingletonHashMapCollection.getSingleInstance().getVehicleList().values())
		{
			if(item instanceof ElectricVehicle)
			{
				list.put(item.getSerialNumber(), (ElectricVehicle)item);	
			}
		}	
		return list;
	}
	
	/**
	 * Adds an element that inherits the attributes of Vehicle in this SingletonHashMapCollection's vehicleCollection.
	 * 
	 * @param v the Vehicle object to add to this SingletonHashMapCollection's vehicleCollection.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see Vehicle
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.HashMap
	 */
	public void add(Vehicle v)	
	{		
		singleInstance.vehicleCollection.put(v.getSerialNumber(), v);	
	}	
    
	/**
	 * Adds an element that inherits the attributes of Vehicle in this SingletonHashMapCollection's vehicleCollection.
	 * 
	 * @param serialNumber the serial number of the Vehicle object to add to this SingletonHashMapCollection's vehicleCollection.
	 * @param v the Vehicle object to add to this SingletonHashMapCollection's vehicleCollection.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see Vehicle
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.HashMap
	 */
    public void add(Long serialNumber, Vehicle v)	
    {	
		singleInstance.vehicleCollection.put(serialNumber, v);	
	}	
    
    /**
	 * Adds an element that inherits the attributes of Vehicle in this SingletonHashMapCollection's vehicleCollection.
	 * 
	 * @param vehiclesList the ArrayList that encapsulates the fleet of vehicles.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see SingletonVehiclesFleet#getVehicles()
	 * @see Vehicle
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.ArrayList
	 * @see java.util.HashMap
	 */
    public void add(ArrayList<Vehicle> vehiclesList)
    {
        for(Vehicle v : vehiclesList) 
        {
        	singleInstance.vehicleCollection.put(v.getSerialNumber(), v);	
        }
    }

    /**
	 * Clears the collection of key-value pairs from this SingletonHashMapCollection.
	 * The collection of vehicles will be empty.
	 * 
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see java.util.HashMap
	 */
    public void clear()
    {
    	singleInstance.vehicleCollection.clear();
    }
    
    /**
	 * Removes an element that inherits the attributes of Vehicle in this SingletonHashMapCollection's vehicleCollection.
	 * 
	 * @param v the Vehicle object to remove from this SingletonHashMapCollection's vehicleCollection.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.HashMap
	 */
	public void remove(Vehicle v)	
	{
		singleInstance.vehicleCollection.remove(v.getSerialNumber());	
	}

	/**
	 * Searches a Vehicle object by serial number through this SingletonHashMapCollection's vehicleCollection.
	 * 
	 * @param serialNumber the Long to compare to an existing serial number in this SingletonHashMapCollection's vehicleCollection.
	 * @return The Vehicle object found which their serial number matched the serialNumber. 
	 * 		   If the Vehicle object is not found, return null.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see Vehicle#getSerialNumber()
	 * @see java.util.HashMap
	 */
	public Vehicle search(Long serialNumber)
	{
		 Vehicle object = null;
		 if (singleInstance.vehicleCollection.containsKey(serialNumber))
		 {
			 object = singleInstance.vehicleCollection.get(serialNumber);
		 }       
	        
		 return object;
	}
	
	/**
	 * Writes the data collected in the file by calling the FileManager2.
	 * 
	 * @param list the HashMap of the file that associates the serialNumber of the Vehicle object with the Vehicle object itself.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see FileManager2#writeToSerializedFile(HashMap)
	 * @see java.util.HashMap
	 * @see java.io.IOException
	 */
	public void writeToSerializedFile (HashMap<Long, Vehicle> list) throws IOException
	{
		list = SingletonHashMapCollection.getSingleInstance().getVehicleList();
		FileManager2.writeToSerializedFile(list);
	}
	
	/**
	 * Reads the data collected from the file by calling the FileManager2.
	 * 
	 * @return the HashMap that associates the serialNumber of the Vehicle object with the Vehicle object itself from the file.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @throws ClassNotFoundException if no definition for the class 
	 * 		   with the specified name could be found when loading in a class through its string name.
	 * @see FileManager2#readFromSerializedFile()
	 * @see java.util.ArrayList
	 * @see java.util.HashMap
	 * @see java.io.IOException
	 */
	public HashMap<Long, Vehicle> readFromSerializedFile() throws IOException, ClassNotFoundException
	{
		return FileManager2.readFromSerializedFile();
	}
	
	/**
	 * Sorts this SingletonHashMapCollection's vehicleCollection in ascending order by serial number.
	 * 
	 * @return list the ArrayList sorted with the keys from the HashMap collection.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see java.util.HashMap
	 * @see java.util.Collections
	 */
	public ArrayList<Long> sortByKey()
	{
		ArrayList<Long> list = new ArrayList<Long>(singleInstance.vehicleCollection.keySet());
		Collections.sort(list);
		return list;
	}
	
	/**
	 * Sorts this SingletonHashMapCollection's vehicleCollection in descending order by mileage efficiency.
	 * 
	 * @return list the ArrayList sorted with the values from the HashMap collection.
	 * @see SingletonHashMapCollection#vehicleCollection
	 * @see SingletonHashMapCollection#getSingleInstance()
	 * @see Vehicle#getMilesPerEnergyConsumed()
	 * @see MileageEfficiencyPredicate#compare(Vehicle, Vehicle)
	 * @see java.util.HashMap
	 * @see java.util.Collections
	 */
	public ArrayList<Vehicle> sortByValues()
	{
		ArrayList<Vehicle> list = new ArrayList<Vehicle>(singleInstance.vehicleCollection.values());
		Collections.sort(list, Collections.reverseOrder(new MileageEfficiencyPredicate()));
		return list;
	}
}
