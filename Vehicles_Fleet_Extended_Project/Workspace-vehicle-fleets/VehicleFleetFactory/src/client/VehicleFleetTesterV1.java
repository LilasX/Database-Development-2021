package client;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import bus.*;

/**
 * This is the front-end application where the user can interact with it.
 * 
 * @author Lilas Guan
 */
public class VehicleFleetTesterV1
{

	/**
	 * Adds a Vehicle object from the user inputs to SingletonVehiclesFleet's fleetOfVehicles
	 * and asks the user to enter the values of the attributes necessary to create the Vehicle object.
	 * 
	 * @param scan the reader of user inputs.
	 * @param vehicle the object Vehicle added to the ArrayList of Vehicle.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#add(Vehicle)
	 * @see Vehicle#getType()
	 * @see Vehicle#setType(EnumType)
	 * @see IMileAgeEfficiency#makeTrip()
	 * @see GasVehicle
	 * @see ElectricVehicle
	 * @see RaiseException
	 * @see NumberFormatException
	 * @see java.util.Scanner
	 */
	public static void add(Scanner scan, Vehicle vehicle)  
	{
		boolean valid = false;
		
		if(vehicle.getType() == EnumType.GasVehicle)
	    {
			Date aDate = new Date();
	    	GasVehicle gv = new GasVehicle();
	    	Long aSerialNumber = 0L;
	    	gv = (GasVehicle)vehicle; 
	    	
	    	do
			{
	    		try
				{
					System.out.print("Enter a serial number : ");
					aSerialNumber = Validator.isNotDuplicate(Long.parseLong(scan.nextLine()));
					gv.setSerialNumber(aSerialNumber);
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Invalid Input, data must be a long.");
					continue;
				}
				
			}while(!valid);
			
	    	valid = false;
	    	do
			{
				try
				{
					System.out.print("Enter a made : ");
					gv.setMade(scan.nextLine());
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}while(!valid);
	    	
	    	valid = false;
	    	do
			{
				try
				{
					System.out.print("Enter a model : ");
					gv.setModel(scan.nextLine());
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}while(!valid);
	    	
	    	valid = false;
	    	do
			{
				try
				{
					System.out.print("Enter the date of manufacture (yyyyMMdd) : ");
					aDate = Validator.isDate(scan.nextLine());
					gv.setDateOfManufacture(aDate);
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}while(!valid);
					    
		    gv = (GasVehicle)vehicle;
		    
		    gv.makeTrip();
		    
		    SingletonVehiclesFleet.getSingleton().add((Vehicle) gv);
		     
	    }
	    else if(vehicle.getType() == EnumType.ElectricVehicle)
	    {
	    	Date aDate = new Date();
	    	ElectricVehicle ev = new ElectricVehicle();
	    	Long aSerialNumber = 0L;
	    	ev = (ElectricVehicle)vehicle; 
	    	
			do
			{
				try
				{
					System.out.print("Enter a serial number : ");
					aSerialNumber = Validator.isNotDuplicate(Long.parseLong(scan.nextLine()));
					ev.setSerialNumber(aSerialNumber);
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Invalid Input, data must be a long.");
					continue;
				}
				
			}while(!valid);
			
			valid = false;
			do
			{
				try
				{
					System.out.print("Enter a made : ");
					ev.setMade(scan.nextLine());
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}while(!valid);
			
			valid = false;
	    	do
			{
				try
				{
					System.out.print("Enter a model : ");
					ev.setModel(scan.nextLine());
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}while(!valid);
			
	    	valid = false;
	    	do
			{
				try
				{
					System.out.print("Enter the date of manufacture (yyyyMMdd) : ");
					aDate = Validator.isDate(scan.nextLine());
					ev.setDateOfManufacture(aDate);
				    valid = true;
				}
				catch(RaiseException ex)
				{
					System.out.println(ex.getMessage());
				}
				
			}while(!valid);
	    	
		    ev = (ElectricVehicle)vehicle;
		    
		    ev.makeTrip();
		    
		    SingletonVehiclesFleet.getSingleton().add((Vehicle) ev);
		     
	    }	
	}
	
	/**
	 * Asks the user to enter the serial number of the Vehicle object to remove.
	 * If the serial number matches the serial number of a Vehicle object in the list, 
	 * remove the Vehicle object found from SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @param scan the reader of user inputs
	 * @param vehicle the object Vehicle removed from the ArrayList of Vehicle.
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#getVehicles()
	 * @see SingletonVehiclesFleet#remove(Vehicle)
	 * @see SingletonVehiclesFleet#search(Long)
	 * @see Vehicle#getSerialNumber()
	 * @see NumberFormatException
	 * @see java.util.Scanner
	 */
	public static void remove(Scanner scan) 
	{
		Long key = 0L;
		Vehicle searched = null;
		int again = 0;

	   do
	   {
		   boolean valid = false;
		while(!valid)
		{
			try
			{
				System.out.print("Enter the serial number of the vehicle you want to remove: ");
				key = Long.parseLong(scan.nextLine());	
				valid = true;
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Invalid Input, data must be a long.");
				continue;
			}
		}
		
		searched = SingletonVehiclesFleet.getSingleton().search(key);
		
		if(searched==null){
			System.out.println("Vehicle NOT found...");
		}
		else {
			SingletonVehiclesFleet.getSingleton().remove(searched);
			System.out.println(searched.toString() + " removed...");				
		}
		
		valid = false;
		while(!valid)
		  {
			  	try
				{
			  		System.out.print("Remove again? (type 1 to continue, otherwise type any other int to quit): ");
					again = Integer.parseInt(scan.nextLine());	
				    valid = true;
				}
			  	catch(NumberFormatException ex)
				{
					System.out.println("Invalid Input, data must be an int.");
					continue;
				}
		  }
		
	   }while(again == 1);
	}
	
	/**
	 * Asks the user the type of Vehicle to add in SingletonVehiclesFleet's fleetOfVehicles ArrayList.
	 * 
	 * @param scan the reader of the user inputs
	 * @see VehicleFleetTesterV1#add(Scanner, Vehicle)
	 * @see Vehicle#setType(EnumType)
	 * @see NumberFormatException
	 * @see java.util.Scanner
	 */
	public static void loadData(Scanner scan) 
	{    
		int option;
		int keepLooping = 0;
		boolean isDefault = false;
	      
		  do
		  {
			  GasVehicle gv= new GasVehicle();
			  ElectricVehicle ev = new ElectricVehicle();
			  isDefault = false;				
			  
			  boolean valid = false;
			  while(!valid)
			  {
				  	try
					{
						System.out.print("What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : ");		
						option = Integer.parseInt(scan.nextLine());
						switch(option)
						{
						   case 1:
							    gv.setType(EnumType.GasVehicle);
							    add(scan, gv);
							    break;
							
							case 2: 
								ev.setType(EnumType.ElectricVehicle);
								add(scan, ev);
								break;
								
							default:
								isDefault = true;
								System.out.println("Invalid option, please try again.");
								keepLooping = 1;
								break;
						}
					    valid = true;
					}
				  	catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be an int.");
						continue;
					}
					
			  }
			
			valid=false;
			if(!isDefault) 
			{
				while(!valid)
				  {
					  	try
						{
					  		System.out.print("Add another vehicle? (type 1 to continue, otherwise type any other int to quit): ");
							keepLooping = Integer.parseInt(scan.nextLine());
						    valid = true;
						}
					  	catch(NumberFormatException ex)
						{
							System.out.println("Invalid Input, data must be an int.");
							continue;
						}
				  }
				
			}
			
		  }while (keepLooping == 1);		
	}
	
	/**
	 * Searches a Vehicle object by serial number entered by the user in SingletonVehiclesFleet's fleetOfVehicles.
	 * 
	 * @param scan the reader of user inputs
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#getVehicles()
	 * @see SingletonVehiclesFleet#search(Long)
	 * @see Vehicle#getSerialNumber()
	 * @see NumberFormatException
	 * @see java.util.Scanner
	 */
	public static void search(Scanner scan)
	{
		Long key = 0L;
		Vehicle searched = null;
		int again = 0;
		
	   do
	   {
		   boolean valid = false;
			while(!valid)
			{
				try
				{
					System.out.println("- Searching for a vehicle (by serial number) - ");
					System.out.print("Enter the serial number: ");
					key = Long.parseLong(scan.nextLine());	
					valid = true;
				}
				catch(NumberFormatException ex)
				{
					System.out.println("Invalid Input, data must be a long.");
					continue;
				}	
			}
		
		searched = SingletonVehiclesFleet.getSingleton().search(key);
		
		if(searched==null){
			System.out.println("Vehicle NOT found...");
		}
		else {
			System.out.println("Vehicle found..."  + searched.toString());				
		}
		
		valid = false;
		while(!valid)
		  {
			  	try
				{
			  		System.out.print("Search again ? (type 1 to continue, otherwise type any other int to quit): ");
					again = Integer.parseInt(scan.nextLine());
				    valid = true;
				}
			  	catch(NumberFormatException ex)
				{
					System.out.println("Invalid Input, data must be an int.");
					continue;
				}
		  }
		
		
	   }while(again == 1);
	}
	
	/**
	 * Starts the Vehicle's trip counter chosen by serial number 
	 * from SingletonVehiclesFleet's fleetOfVehicles by calling makeTrip method
	 * which sets a value for trip counter and a value for energy consumed from the user.
	 * 
	 * @param scan the reader of user inputs
	 * @see SingletonVehiclesFleet#getSingleton()
	 * @see SingletonVehiclesFleet#search(Long)
	 * @see Vehicle#getSerialNumber()
	 * @see Vehicle#setTripCounter(Integer)
	 * @see GasVehicle#setFuelConsumed(Double)
	 * @see GasVehicle#makeTrip(int, double)
	 * @see ElectricVehicle#setKwPowerConsumed(Double)
	 * @see ElectricVehicle#makeTrip(int, double)
	 * @see RaiseException
	 * @see NumberFormatException
	 * @see java.util.Scanner
	 */
	public static void makeTrip(Scanner scan)
	{
		Long key = 0L;
		Vehicle searched = null;
		int again = 0;

	   do
	   {		
		   boolean valid = false;
		while(!valid)
		{
			try
			{
				System.out.println("- Choose a vehicle (by serial number) to start the trip counter - ");
				System.out.print("Enter the serial number: ");
				key = Long.parseLong(scan.nextLine());	
				valid = true;
			}
			catch(NumberFormatException ex)
			{
				System.out.println("Invalid Input, data must be a long.");
				continue;
			}	
		}
		
		searched = SingletonVehiclesFleet.getSingleton().search(key);
		
		if(searched==null){
			System.out.println("Vehicle NOT found...");
		}
		else {
			valid = false;
			if(searched instanceof GasVehicle)
			{
				do
				{
					try
					{
						System.out.print("Enter a trip counter : ");
					    searched.setTripCounter(Integer.parseInt(scan.nextLine()));
					    valid = true;
					}
					catch(RaiseException ex)
					{
						System.out.println(ex.getMessage());
					}
					catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be an int.");
						continue;
					}
					
				}while(!valid);
				
				valid = false;
				do
				{
					try
					{
						System.out.print("Enter fuel consumed : ");
					    ((GasVehicle)searched).setFuelConsumed(Double.parseDouble(scan.nextLine()));
					    valid = true;
					}
					catch(RaiseException ex)
					{
						System.out.println(ex.getMessage());
					}
					catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be a double.");
						continue;
					}
					
				}while(!valid);
				
				searched.makeTrip(searched.getTripCounter(), ((GasVehicle) searched).getFuelConsumed());
				System.out.printf("Mileage Efficiency: %.2f\n", searched.getMilesPerEnergyConsumed());
			}	
			else if(searched instanceof ElectricVehicle)
			{
				do
				{
					try
					{
						System.out.print("Enter a trip counter : ");
					    searched.setTripCounter(Integer.parseInt(scan.nextLine()));
					    valid = true;
					}
					catch(RaiseException ex)
					{
						System.out.println(ex.getMessage());
					}
					catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be an int.");
						continue;
					}
					
				}while(!valid);
				
				valid = false;
				do
				{
					try
					{
						System.out.print("Enter kilowatt power consumed : ");
					    ((ElectricVehicle)searched).setKwPowerConsumed(Double.parseDouble(scan.nextLine()));
					    valid = true;
					}
					catch(RaiseException ex)
					{
						System.out.println(ex.getMessage());
					}
					catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be a double.");
						continue;
					}
					
				}while(!valid);
				
				searched.makeTrip(searched.getTripCounter(), ((ElectricVehicle) searched).getKwPowerConsumed());
				System.out.printf("Mileage Efficiency: %.2f\n", searched.getMilesPerEnergyConsumed());
			}
		}
		
		valid = false;
		while(!valid)
		  {
			  	try
				{
			  		System.out.print("Choose again ? (type 1 to continue, otherwise type any other int to quit): ");
					again = Integer.parseInt(scan.nextLine());
				    valid = true;
				}
			  	catch(NumberFormatException ex)
				{
					System.out.println("Invalid Input, data must be an int.");
					continue;
				}
		  }
		
		
	   }while(again == 1);
	}
	
	/**
	 * Menu of the application.
	 * 
	 * @param scan the reader of user inputs
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @throws ClassNotFoundException if no definition for the class 
	 * 		   with the specified name could be found when loading in a class through its string name.
	 * @see SingletonVehiclesFleet
	 * @see ClassNotFoundException
	 * @see java.io.IOException
	 * @see java.util.Scanner
	 */
	public static void menu(Scanner scan) throws IOException, ClassNotFoundException 
	{
		int option = 0;
		System.out.println("\n----- Welcome to the Vehicles' Fleet Factory! -----");
		System.out.println("----- Using ArrayList -----\n");
		
		while(true)
		{
			try
			{
				System.out.println("\n----- Menu -----\n");
				System.out.println("1-Add a vehicle. \n2-Remove a vehicle. \n3-Search a vehicle. "
						+ "\n4-Test a vehicle (make trip). \n5-Print list of vehicles. "
						+ "\n6-Print list of gas vehicles. \n7-Print list of electric vehicles. "
						+ "\n8-Sort the fleet of vehicles by serial number. \n9-Sort the fleet of vehicles by mileage efficiency. "
						+ "\n10-Save the fleet of vehicles into a serialized file "
						+ "\n11-Read the fleet of vehicles from the serialized file "
						+ "\n12-Display the vehicle having the best efficient mileage \n13-Exit. \n");
				
				System.out.print("\nSelect an option: ");
				option = Integer.parseInt(scan.nextLine());
				switch(option)
				{
				   case 1:
					    loadData(scan);
					    break;
					
					case 2: 
						remove(scan);
						break;
						
					case 3: 
						search(scan);
						break;
						
					case 4: 
						makeTrip(scan);
						break;
						
					case 5: 
						System.out.println("\n --------------------");	
						System.out.println(" List of Vehicles :\n");	
						for(Vehicle aVehicle : SingletonVehiclesFleet.getSingleton().getVehicles())
						{
							System.out.println(aVehicle);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 6: 
						System.out.println("\n --------------------");	
						System.out.println(" List of Gasoline Vehicles :\n");	
						for(Vehicle aVehicle : SingletonVehiclesFleet.getSingleton().getGasolineVehicles())
						{
							System.out.println(aVehicle);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 7: 
						System.out.println("\n --------------------");	
						System.out.println(" List of Electric Vehicles :\n");	
						for(Vehicle aVehicle : SingletonVehiclesFleet.getSingleton().getElectricVehicles())
						{
							System.out.println(aVehicle);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 8: 
						SingletonVehiclesFleet.getSingleton().sortBySerialNumber();
						System.out.println("\n --------------------");	
						System.out.println(" List of Vehicles after sorting by serial number :\n");
						for(Vehicle v : SingletonVehiclesFleet.getSingleton().getVehicles())
						{
							System.out.println(v);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 9: 
						SingletonVehiclesFleet.getSingleton().sortByMileageEfficiency();
						System.out.println("\n --------------------");	
						System.out.println(" List of Vehicles after sorting by mileage efficiency :\n");
						for(Vehicle v : SingletonVehiclesFleet.getSingleton().getVehicles())
						{
							System.out.println(v);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 10:
						System.out.println(" The fleet of vehicles is now saved into a serialized file.\n");
						SingletonVehiclesFleet.getSingleton().serialize(SingletonVehiclesFleet.getSingleton().getVehicles());
						break;
						
					case 11:
						System.out.println("\n --------------------");	
						System.out.println(" List of Vehicles From File\n");
						for(Vehicle v : SingletonVehiclesFleet.getSingleton().deSerialize()) 
						{
							System.out.println(v);
						}
						System.out.println(" --------------------\n");
						break;
						
					case 12:
						SingletonVehiclesFleet.getSingleton().sortByMileageEfficiency();
						System.out.println("\n --------------------");	
						System.out.println(" Vehicle having the best efficient mileage\n");
						System.out.println(SingletonVehiclesFleet.getSingleton().getVehicles().get(0));
						System.out.println(" --------------------\n");
						break;
						
					case 13: 
						scan.close();
						System.out.println("The application is now closed.");
						System.exit(0);
						break;
						
					default:
						System.out.println("Invalid option, please try again.");
						break;
				}
			}
		  	catch(NumberFormatException ex)
			{
				System.out.println("Invalid Input, data must be an int.");
				continue;
			}
		}
	}
	
	/**
	 * This is the main where the execution of the application begins.
	 * 
	 * @param args the array of Strings passed as parameters when you are running your application. 
	 * @throws IOException if there is a failure during reading, writing, 
	 * 		   and searching file or directory operations.
	 * @throws ClassNotFoundException if no definition for the class 
	 * 		   with the specified name could be found when loading in a class through its string name.
	 * @see ClassNotFoundException
	 * @see java.io.IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		Scanner scan = new Scanner(System.in);
		
		menu(scan);
	}

}

/* CONSOLE OUTPUT 

----- Welcome to the Vehicles' Fleet Factory! -----
----- Using ArrayList -----


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1111
Enter a made : Mitsubishi
Enter a model : Mirage
Enter the date of manufacture (yyyyMMdd) : 20201231
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 1222
Enter a made : Volkswagen
Enter a model : ID4
Enter the date of manufacture (yyyyMMdd) : 20210101
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1333
Enter a made : Kia
Enter a model : Rio
Enter the date of manufacture (yyyyMMdd) : 20201231
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1444
Enter a made : MINI
Enter a model : Cooper
Enter the date of manufacture (yyyyMMdd) : 20210101
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1555
Enter a made : Honda
Enter a model : Fit
Enter the date of manufacture (yyyyMMdd) : 20201231
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : Nissan
Invalid Input, data must be a long.
Enter a serial number : 1777
Enter a made : Nissan
Enter a model : Leaf
Enter the date of manufacture (yyyyMMdd) : 20210101
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 1666
Enter a made : Kandi
Enter a model : K27
Enter the date of manufacture (yyyyMMdd) : 20210101
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 1888
Enter a made : Hyundai
Enter a model : IoniqElectric
Enter the date of manufacture (yyyyMMdd) : 20201231
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : BMW
Invalid Input, data must be a long.
Enter a serial number : 1999
Enter a made : BMW
Enter a model : i3
Enter the date of manufacture (yyyyMMdd) : 20210101
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 5

 --------------------
 List of Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1999  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 11111
Enter a made : Jaguar
Enter a model : FPace
Enter the date of manufacture (yyyyMMdd) : 20171231
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 5

 --------------------
 List of Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1999  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
GasVehicle [Serial Number: 11111  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 6

 --------------------
 List of Gasoline Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 11111  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 7

 --------------------
 List of Electric Vehicles :

ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1999  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 2
Enter the serial number of the vehicle you want to remove: 11111
GasVehicle [Serial Number: 11111  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87] removed...
Remove again? (type 1 to continue, otherwise type any other int to quit): 1
Enter the serial number of the vehicle you want to remove: 11111
Vehicle NOT found...
Remove again? (type 1 to continue, otherwise type any other int to quit): 1
Enter the serial number of the vehicle you want to remove: 1999
ElectricVehicle [Serial Number: 1999  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05] removed...
Remove again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 3
- Searching for a vehicle (by serial number) - 
Enter the serial number: 11111
Vehicle NOT found...
Search again ? (type 1 to continue, otherwise type any other int to quit): 1
- Searching for a vehicle (by serial number) - 
Enter the serial number: 1999
Vehicle NOT found...
Search again ? (type 1 to continue, otherwise type any other int to quit): 1
- Searching for a vehicle (by serial number) - 
Enter the serial number: 1222
Vehicle found...ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
Search again ? (type 1 to continue, otherwise type any other int to quit): 1
- Searching for a vehicle (by serial number) - 
Enter the serial number: 1111
Vehicle found...GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
Search again ? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 4
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1111
Enter a trip counter : 359
Enter fuel consumed : 9.21
Mileage Efficiency: 38.98
Choose again ? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 5

 --------------------
 List of Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 4
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1222
Enter a trip counter : 250
Enter kilowatt power consumed : 2.58
Mileage Efficiency: 96.90
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1333
Enter a trip counter : 428
Enter fuel consumed : 11.89
Mileage Efficiency: 36.00
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1333
Enter a trip counter : 428
Enter fuel consumed : 11.89
Mileage Efficiency: 36.00
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1444
Enter a trip counter : 360
Enter fuel consumed : 11.61
Mileage Efficiency: 31.01
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1555
Enter a trip counter : 382
Enter fuel consumed : 10.61
Mileage Efficiency: 36.00
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1666
Enter a trip counter : 59
Enter kilowatt power consumed : 0.52
Mileage Efficiency: 113.46
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1777
Enter a trip counter : 149
Enter kilowatt power consumed : 1.34
Mileage Efficiency: 111.19
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1888
Enter a trip counter : 170
Enter kilowatt power consumed : 1.28
Mileage Efficiency: 132.81
Choose again ? (type 1 to continue, otherwise type any other int to quit): 1
- Choose a vehicle (by serial number) to start the trip counter - 
Enter the serial number: 1999
Vehicle NOT found...
Choose again ? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 9

 --------------------
 List of Vehicles after sorting by mileage efficiency :

ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 8

 --------------------
 List of Vehicles after sorting by serial number :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 9

 --------------------
 List of Vehicles after sorting by mileage efficiency :

ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 10
 The fleet of vehicles is now saved into a serialized file.


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 11

 --------------------
 List of Vehicles From File

ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 5

 --------------------
 List of Vehicles :

ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
ElectricVehicle [Serial Number: 1666  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 1777  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 1222  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
GasVehicle [Serial Number: 1555  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1333  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1444  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 12

 --------------------
 Vehicle having the best efficient mileage

ElectricVehicle [Serial Number: 1888  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
 --------------------


----- Menu -----

1-Add a vehicle. 
2-Remove a vehicle. 
3-Search a vehicle. 
4-Test a vehicle (make trip). 
5-Print list of vehicles. 
6-Print list of gas vehicles. 
7-Print list of electric vehicles. 
8-Sort the fleet of vehicles by serial number. 
9-Sort the fleet of vehicles by mileage efficiency. 
10-Save the fleet of vehicles into a serialized file 
11-Read the fleet of vehicles from the serialized file 
12-Display the vehicle having the best efficient mileage 
13-Exit. 


Select an option: 13
The application is now closed.

 */
