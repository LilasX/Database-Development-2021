package bus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Serializes the data collected into a file.
 * Reads and writes data in the file.
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 */
public class FileManager
{
	/**
	 * References the file path where the data is serialized.
	 */
	public static String filePath = "src//datafile//SerVehicleFleet.ser";
	
	/**
	 * Writes the data collected in the file.
	 * 
	 * @param list the ArrayList of type Vehicle which stores the encapsulated fleet of vehicles.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @see java.io.IOException
	 * @see java.io.FileOutputStream
	 * @see java.io.ObjectOutputStream
	 * @see java.util.ArrayList
	 */
	public static void serialize(ArrayList<Vehicle> list) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(list);
		
		fos.close();
	}
	
	/**
	 * Reads the data collected from the file.
	 * 
	 * @return the ArrayList of type Vehicle which stores the encapsulated fleet of vehicles.
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @throws ClassNotFoundException if no definition for the class 
	 * 		   with the specified name could be found when loading in a class through its string name.
	 * @see java.io.IOException
	 * @see java.io.FileInputStream
	 * @see java.io.ObjectInputStream
	 * @see java.util.ArrayList
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Vehicle> deSerialize() throws IOException, ClassNotFoundException
	{
		ArrayList<Vehicle> list = new ArrayList<Vehicle>();
		FileInputStream fis = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		list = (ArrayList<Vehicle>) ois.readObject();
		
		fis.close();
		return list;
	}
}
