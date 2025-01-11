package bus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Serializes the data collected into a file.
 * Reads and writes data in the file.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 */
public class FileManager2
{
	/**
	 * References the file path where the data is serialized.
	 */
	public static String filePath = "src//datafile//SerVehicleFleet2.ser";
	
	/**
	 * Writes the data collected with the HashMap in the file.
	 * 
	 * @param collection the HashMap that associates the serialNumber of the Vehicle object with the Vehicle object itself.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @see java.io.IOException
	 * @see java.io.FileOutputStream
	 * @see java.io.ObjectOutputStream
	 * @see java.util.ArrayList
	 * @see java.util.HashMap
	 */
	public static void writeToSerializedFile(HashMap<Long, Vehicle> collection) throws IOException
	{		 
		ArrayList<Vehicle> list = new ArrayList <Vehicle> (((HashMap<Long, Vehicle>) collection).values());
		
		FileOutputStream fos = new FileOutputStream(filePath);		
		ObjectOutputStream oos = new ObjectOutputStream(fos);	
		
		oos.writeObject(list);		  
		fos.close();		
	}	

	/**
	 *  Reads the data collected from the file.
	 * 
	 * @return the HashMap that associates the serialNumber of the Vehicle object with the Vehicle object itself.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @throws ClassNotFoundException if no definition for the class 
	 * 		   with the specified name could be found when loading in a class through its string name.
	 * @see java.io.IOException
	 * @see java.io.FileInputStream
	 * @see java.io.ObjectInputStream
	 * @see java.util.ArrayList
	 * @see java.util.HashMap
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<Long, Vehicle> readFromSerializedFile() throws IOException, ClassNotFoundException
	{
		HashMap<Long, Vehicle> vehicleHashMap = new HashMap<Long, Vehicle>();		
		ArrayList<Vehicle> vehicleArrayList = new ArrayList<Vehicle>();
		
		FileInputStream fis = new FileInputStream(filePath);		  
		ObjectInputStream ois = new ObjectInputStream(fis);		  
		  
		vehicleArrayList = (ArrayList<Vehicle>)ois.readObject();
		  
		for(Vehicle aVehicle : vehicleArrayList)  
		{ 
			vehicleHashMap.put(aVehicle.getSerialNumber(), aVehicle);
		}		  
		fis.close();			  
		return vehicleHashMap;	
	} 	

}
