package client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import bus.*;

/**
 * This is the front-end application where the user can interact with it.
 * 
 * @author Lilas Guan
 */
public class VehicleFleetTesterV2
{
	/**
	 * Adds a Vehicle object from the user inputs to the appropriate database
	 * and asks the user to enter the values of the attributes necessary to create the Vehicle object.
	 * 
	 * @param scan the reader of user inputs.
	 * @param vehicle the object Vehicle added to the database.
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see Vehicle#getType()
	 * @see Vehicle#setType(EnumType)
	 * @see IMileAgeEfficiency#makeTrip()
	 * @see GasVehicle#add(GasVehicle)
	 * @see ElectricVehicle#add(ElectricVehicle)
	 * @see RaiseException
	 * @see NumberFormatException
	 * @see java.sql.SQLException
	 * @see java.util.Scanner
	 */
	public static void add(Scanner scan, Vehicle vehicle) throws SQLException, RaiseException  
	{
		boolean valid = false;
		
		if(vehicle.getType() == EnumType.GasVehicle)
	    {
			Date aDate = new Date();
			Long aSerialNumber = 0L;
	    	GasVehicle gv = new GasVehicle();
	    	gv = (GasVehicle)vehicle; 
	    	
	    	do
			{
				try
				{
					System.out.print("Enter a serial number : ");
					aSerialNumber = Validator.isNotDuplicateDB(Long.parseLong(scan.nextLine()));
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
		    
		    GasVehicle.add((GasVehicle) gv);
		     
	    }
	    else if(vehicle.getType() == EnumType.ElectricVehicle)
	    {
	    	Long aSerialNumber = 0L;
	    	Date aDate = new Date();
	    	ElectricVehicle ev = new ElectricVehicle();
	    	ev = (ElectricVehicle)vehicle; 
	    	
			do
			{
				try
				{
					System.out.print("Enter a serial number : ");
					aSerialNumber = Validator.isNotDuplicateDB(Long.parseLong(scan.nextLine()));
					ev.setSerialNumber(aSerialNumber);
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
		    
		    ElectricVehicle.add((ElectricVehicle) ev);
	    }	
	}
	
	/**
	 * Asks the user to enter the serial number of the Vehicle object to remove.
	 * If the serial number matches the serial number of a Vehicle object in the database, 
	 * remove the Vehicle object found from the appropriate database.
	 * 
	 * @param scan the reader of user inputs
	 * @param vehicle the object Vehicle removed from the database.
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasVehicle#remove(Long)
	 * @see ElectricVehicle#remove(Long)
	 * @see NumberFormatException
	 * @see java.sql.SQLException
	 * @see java.util.Scanner
	 */
	public static void remove(Scanner scan) throws SQLException, RaiseException 
	{
		Long key = 0L;
		int again = 0;
		boolean isDefault = false;
		int option;
		
	   do
	   {
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
							   while(!valid)
								{
									try
									{
										System.out.print("Enter the serial number of the vehicle you want to remove: ");
										key = Long.parseLong(scan.nextLine());	
										GasVehicle.remove(key);
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
							    break;
							
							case 2: 
								while(!valid)
								{
									try
									{
										System.out.print("Enter the serial number of the vehicle you want to remove: ");
										key = Long.parseLong(scan.nextLine());	
										ElectricVehicle.remove(key);
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
								break;
								
							default:
								isDefault = true;
								System.out.println("Invalid option, please try again.");
								again = 1;
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
		if(!isDefault)
		{
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
		}
		
	   }while(again == 1);
	}
	
	/**
	 * Asks the user the type of Vehicle to add in the database.
	 * 
	 * @param scan the reader of the user inputs
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasVehicle#add(GasVehicle)
	 * @see ElectricVehicle#add(ElectricVehicle)
	 * @see VehicleFleetTesterV2#add(Scanner, Vehicle)
	 * @see Vehicle#setType(EnumType)
	 * @see NumberFormatException
	 * @see java.sql.SQLException
	 * @see java.util.Scanner
	 */
	public static void loadData(Scanner scan) throws SQLException, RaiseException 
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
	 * Searches a Vehicle object by serial number entered by the user in the database.
	 * 
	 * @param scan the reader of user inputs
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasVehicle#search(Long)
	 * @see ElectricVehicle#search(Long)
	 * @see Vehicle#getSerialNumber()
	 * @see java.sql.SQLException
	 * @see java.util.Scanner
	 */
	public static void search(Scanner scan) throws SQLException, RaiseException
	{
		Long key = 0L;
		int again = 0;
		boolean isDefault = false;
		int option;
		
	   do
	   {
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
							   while(!valid)
								{
									try
									{
										System.out.print("Enter the serial number of the vehicle you want to search: ");
										key = Long.parseLong(scan.nextLine());
										GasVehicle.search(key);
										if(GasVehicle.search(key) == null)
										{
											System.out.println("Vehicle NOT found.");
										}
										else
										{
											System.out.println(GasVehicle.search(key));
										}
										
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
							    break;
							
							case 2: 
								while(!valid)
								{
									try
									{
										System.out.print("Enter the serial number of the vehicle you want to search: ");
										key = Long.parseLong(scan.nextLine());
										ElectricVehicle.search(key);
										if(ElectricVehicle.search(key) == null)
										{
											System.out.println("Vehicle NOT found.");
										}
										else
										{
											System.out.println(ElectricVehicle.search(key));
										}
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
								break;
								
							default:
								isDefault = true;
								System.out.println("Invalid option, please try again.");
								again = 1;
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
		if(!isDefault)
		{
			valid = false;
			while(!valid)
			  {
				  	try
					{
				  		System.out.print("Search again? (type 1 to continue, otherwise type any other int to quit): ");
						again = Integer.parseInt(scan.nextLine());	
					    valid = true;
					}
				  	catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be an int.");
						continue;
					}
			  }
		}
		
	   }while(again == 1);
	}
	
	/**
	 * Modifies an existing Vehicle's made, model and date of manufacture chosen by serial number 
	 * from the database by calling update method from the appropriate class
	 * which sets values for made, model and date of manufacture from the user.
	 * 
	 * @param scan the reader of user inputs
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasVehicle#update(GasVehicle)
	 * @see GasVehicle#search(Long)
	 * @see ElectricVehicle#update(ElectricVehicle)
	 * @see ElectricVehicle#search(Long)
	 * @see Vehicle#getSerialNumber()
	 * @see RaiseException
	 * @see java.sql.SQLException
	 * @see java.util.Scanner
	 */
	public static void update(Scanner scan) throws SQLException, RaiseException
	{
		Long key = 0L;
		int again = 0;
		boolean isDefault = false;
		int option;
		
	   do
	   {
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
							   while(!valid)
								{
									try
									{
										GasVehicle gasV = new GasVehicle();
										System.out.print("Enter the serial number of the vehicle you want to update: ");
										key = Long.parseLong(scan.nextLine());
										gasV = GasVehicle.search(key);
										if(GasVehicle.search(key) == null)
										{
											System.out.println("Vehicle NOT found.");
										}
										else
										{
											Date aDate = new Date();
											
									    	valid = false;
									    	do
											{
												try
												{
													System.out.print("Enter a made : ");
													gasV.setMade(scan.nextLine());
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
													gasV.setModel(scan.nextLine());
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
													gasV.setDateOfManufacture(aDate);
												    valid = true;
												}
												catch(RaiseException ex)
												{
													System.out.println(ex.getMessage());
												}
												
											}while(!valid);
											
									    	GasVehicle.update(gasV);
										}
										
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
							    break;
							
							case 2: 
								while(!valid)
								{
									try
									{
										ElectricVehicle electricV = new ElectricVehicle();
										System.out.print("Enter the serial number of the vehicle you want to update: ");
										key = Long.parseLong(scan.nextLine());
										electricV = ElectricVehicle.search(key);
										if(ElectricVehicle.search(key) == null)
										{
											System.out.println("Vehicle NOT found.");
										}
										else
										{
											Date aDate = new Date();
									    	
									    	valid = false;
									    	do
											{
												try
												{
													System.out.print("Enter a made : ");
													electricV.setMade(scan.nextLine());
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
													electricV.setModel(scan.nextLine());
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
													electricV.setDateOfManufacture(aDate);
												    valid = true;
												}
												catch(RaiseException ex)
												{
													System.out.println(ex.getMessage());
												}
												
											}while(!valid);
											
									    	ElectricVehicle.update(electricV);
										}
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
								break;
								
							default:
								isDefault = true;
								System.out.println("Invalid option, please try again.");
								again = 1;
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
		if(!isDefault)
		{
			valid = false;
			while(!valid)
			  {
				  	try
					{
				  		System.out.print("Update a vehicle again? (type 1 to continue, otherwise type any other int to quit): ");
						again = Integer.parseInt(scan.nextLine());	
					    valid = true;
					}
				  	catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be an int.");
						continue;
					}
			  }
		}
		
	   }while(again == 1);
	}
	
	/**
	 * Starts the Vehicle's trip counter chosen by serial number 
	 * from the database by calling makeTrip method from the appropriate class
	 * which sets a value for trip counter and a value for energy consumed from the user.
	 * 
	 * @param scan the reader of user inputs
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @see GasVehicle#setFuelConsumed(Double)
	 * @see GasVehicle#search(Long)
	 * @see GasVehicle#makeTripDB(GasVehicle)
	 * @see ElectricVehicle#setKwPowerConsumed(Double)
	 * @see ElectricVehicle#search(Long)
	 * @see ElectricVehicle#makeTripDB(ElectricVehicle)
	 * @see Vehicle#getSerialNumber()
	 * @see Vehicle#setTripCounter(int)
	 * @see RaiseException
	 * @see java.sql.SQLException
	 * @see java.util.Scanner
	 */
	public static void makeTrip(Scanner scan) throws SQLException, RaiseException
	{
		Long key = 0L;
		int again = 0;
		boolean isDefault = false;
		int option;
		
	   do
	   {
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
							   while(!valid)
								{
									try
									{
										GasVehicle gasV = new GasVehicle();
										System.out.print("Enter the serial number of the vehicle you want to make trip: ");
										key = Long.parseLong(scan.nextLine());
										gasV = GasVehicle.search(key);
										if(GasVehicle.search(key) == null)
										{
											System.out.println("Vehicle NOT found.");
										}
										else
										{
									    	valid = false;
									    	do
											{
												try
												{
													System.out.print("Enter a trip counter : ");
													gasV.setTripCounter(Integer.parseInt(scan.nextLine()));
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
													gasV.setFuelConsumed(Double.parseDouble(scan.nextLine()));
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
											
									    	GasVehicle.makeTripDB(gasV);
									    	System.out.printf("Mileage Efficiency: %.2f\n", gasV.getMilesPerEnergyConsumed());
										}
										
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
							    break;
							
							case 2: 
								while(!valid)
								{
									try
									{
										ElectricVehicle electricV = new ElectricVehicle();
										System.out.print("Enter the serial number of the vehicle you want to make trip: ");
										key = Long.parseLong(scan.nextLine());
										electricV = ElectricVehicle.search(key);
										if(ElectricVehicle.search(key) == null)
										{
											System.out.println("Vehicle NOT found.");
										}
										else
										{
											valid = false;
									    	do
											{
												try
												{
													System.out.print("Enter a trip counter : ");
													electricV.setTripCounter(Integer.parseInt(scan.nextLine()));
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
													electricV.setKwPowerConsumed(Double.parseDouble(scan.nextLine()));
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
											
									    	ElectricVehicle.makeTripDB(electricV);
									    	System.out.printf("Mileage Efficiency: %.2f\n", electricV.getMilesPerEnergyConsumed());
										}
										valid = true;
									}
									catch(NumberFormatException ex)
									{
										System.out.println("Invalid Input, data must be a long.");
										continue;
									}
								}
								break;
								
							default:
								isDefault = true;
								System.out.println("Invalid option, please try again.");
								again = 1;
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
		if(!isDefault)
		{
			valid = false;
			while(!valid)
			  {
				  	try
					{
				  		System.out.print("Make trip again? (type 1 to continue, otherwise type any other int to quit): ");
						again = Integer.parseInt(scan.nextLine());	
					    valid = true;
					}
				  	catch(NumberFormatException ex)
					{
						System.out.println("Invalid Input, data must be an int.");
						continue;
					}
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
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see GasVehicleDB
	 * @see ElectricVehicleDB
	 * @see RaiseException
	 * @see ClassNotFoundException
	 * @see java.io.IOException
	 * @see java.sql.SQLException
	 * @see java.util.Collections
	 * @see java.util.Scanner
	 */
	public static void menu(Scanner scan) throws IOException, ClassNotFoundException, SQLException, RaiseException 
	{
		int option = 0;
		System.out.println("\n----- Welcome to the Vehicles' Fleet Database! -----\n");
		System.out.println("----- Using Database -----\n");
		
		while(true)
		{
			try
			{
				System.out.println("\n----- Menu -----\n");
				System.out.println("1-Add a vehicle into the database. \n2-Remove a vehicle from the database. \n3-Search for a vehicle by serial number. "
						+ "\n4-Test a vehicle (make trip). \n5-Update a vehicle. "
						+ "\n6-List the gas vehicles. \n7-List the electric vehicles. "
						+ "\n8-Sort the gas vehicles by mileage efficiency. \n9-Sort the electric vehicles by mileage efficiency. "
						+ "\n10-Display the gas vehicle having the best efficient mileage "
						+ "\n11-Display the electric vehicle having the best efficient mileage \n12-Exit. \n");
				
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
						update(scan);	
						break;
						
					case 6: 
						System.out.println("\n --------------------");	
						System.out.println(" List of Gasoline Vehicles :\n");	
						for(GasVehicle aVehicle : GasVehicle.getData())
						{
							System.out.println(aVehicle);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 7: 
						System.out.println("\n --------------------");	
						System.out.println(" List of Electric Vehicles :\n");	
						for(ElectricVehicle aVehicle : ElectricVehicle.getData())
						{
							System.out.println(aVehicle);
						}	
						System.out.println(" --------------------\n");	
						break;
						
					case 8: 
						System.out.println("\n --------------------");	
						System.out.println(" List of GasVehicles after sorting by mileage efficiency :\n");
						for(GasVehicle v : GasVehicle.getDataSorted())
						{
							System.out.println(v);
						}	
						System.out.println(" --------------------\n");	
						break;
						
					case 9: 	
						System.out.println("\n --------------------");	
						System.out.println(" List of ElectricVehicles after sorting by mileage efficiency :\n");
						for(ElectricVehicle v : ElectricVehicle.getDataSorted())
						{
							System.out.println(v);
						}		
						System.out.println(" --------------------\n");	
						break;
						
					case 10:
						System.out.println("\n --------------------");	
						System.out.println(" GasVehicle having the best efficient mileage\n");
						System.out.println(GasVehicle.getDataSorted().get(0));
						System.out.println(" --------------------\n");
						break;
					
					case 11:
						System.out.println("\n --------------------");	
						System.out.println(" ElectricVehicle having the best efficient mileage\n");
						System.out.println(ElectricVehicle.getDataSorted().get(0));
						System.out.println(" --------------------\n");
						break;
						
					case 12: 
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
	 * @throws RaiseException if the argument doesn't respect the criteria 
	 * 		   that define the purpose of this variable.
	 * @throws SQLException if a database access error or other errors related happens 
	 * 		   during the execution of the application.
	 * @see ClassNotFoundException
	 * @see java.io.IOException
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, RaiseException
	{
		//Create the database in SQL with vehiclefleetdb.sql before running this application.
		Scanner scan = new Scanner(System.in);
		
		menu(scan);
	}

}

/* CONSOLE OUTPUT

----- Welcome to the Vehicles' Fleet Database! -----

----- Using Database -----


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1111
Connection successful
Enter a made : Mitsubishi
Enter a model : Mirage
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : Kia
Invalid Input, data must be a long.
Enter a serial number : 1222
Enter a made : Kia
Enter a model : Rio
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : MINI
Invalid Input, data must be a long.
Enter a serial number : 1333
Enter a made : MINI
Enter a model : Cooper
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1444
Enter a made : Honda
Enter a model : Fit
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1555
Enter a made : Nissan
Enter a model : Versa
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1666
Enter a made : Toyota
Enter a model : Yaris
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1777
Enter a made : Chevrolet
Enter a model : Spark
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1888
Enter a made : Chrysler
Enter a model : Pacifica
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 1999
Enter a made : Hyundai
Enter a model : Accent
Enter the date of manufacture (yyyyMMdd) : 2021
Invalid Input, date must be in format : yyyyMMdd.
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 11111
Enter a made : Subaru
Enter a model : Impreza
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 12222
Enter a made : Mazda
Enter a model : 3
Enter the date of manufacture (yyyyMMdd) : 20191231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 13333
Enter a made : Acura
Enter a model : ILX
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 14444
Enter a made : Audi
Enter a model : Q3
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 15555
Enter a made : Cadillac
Enter a model : Catera
Enter the date of manufacture (yyyyMMdd) : 19991231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 16666
Enter a made : MercedesBenz
Enter a model : CLA250
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter a serial number : 17777
Enter a made : Jaguar
Enter a model : FPace
Enter the date of manufacture (yyyyMMdd) : 20171231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 6

 --------------------
 List of Gasoline Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1222  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1333  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1444  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1555  -  Made: Nissan  -  Model: Versa  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1666  -  Made: Toyota  -  Model: Yaris  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1777  -  Made: Chevrolet  -  Model: Spark  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1888  -  Made: Chrysler  -  Model: Pacifica  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1999  -  Made: Hyundai  -  Model: Accent  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 11111  -  Made: Subaru  -  Model: Impreza  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 12222  -  Made: Mazda  -  Model: 3  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 13333  -  Made: Acura  -  Model: ILX  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 14444  -  Made: Audi  -  Model: Q3  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 15555  -  Made: Cadillac  -  Model: Catera  -  Date of Manufacture: 1999-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 16666  -  Made: MercedesBenz  -  Model: CLA250  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 17777  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 2
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to remove: 17777
Vehicle removed.
Remove again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 3
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to search: 17777
Vehicle NOT found.
Search again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to search: 14444
GasVehicle [Serial Number: 14444  -  Made: Audi  -  Model: Q3  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
Search again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 5
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to update: 14444
Enter a made : Jaguar
Enter a model : FPace
Enter the date of manufacture (yyyyMMdd) : 20171231
Vehicle updated.
Update a vehicle again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 3
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to search: 14444
GasVehicle [Serial Number: 14444  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
Search again? (type 1 to continue, otherwise type any other int to quit): 6

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 6

 --------------------
 List of Gasoline Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1222  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1333  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1444  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1555  -  Made: Nissan  -  Model: Versa  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1666  -  Made: Toyota  -  Model: Yaris  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1777  -  Made: Chevrolet  -  Model: Spark  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1888  -  Made: Chrysler  -  Model: Pacifica  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 1999  -  Made: Hyundai  -  Model: Accent  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 11111  -  Made: Subaru  -  Model: Impreza  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 12222  -  Made: Mazda  -  Model: 3  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 13333  -  Made: Acura  -  Model: ILX  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 14444  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 15555  -  Made: Cadillac  -  Model: Catera  -  Date of Manufacture: 1999-12-31  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
GasVehicle [Serial Number: 16666  -  Made: MercedesBenz  -  Model: CLA250  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Fuel Consumed: 7.77 - Mileage Efficiency: 12.87]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 4
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1111
Enter a trip counter : 359
Enter fuel consumed : 9.21
Make trip successful.
Mileage Efficiency: 38.98
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1222
Enter a trip counter : 428
Enter fuel consumed : 11.89
Make trip successful.
Mileage Efficiency: 36.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1333
Enter a trip counter : 360
Enter fuel consumed : 11.61
Make trip successful.
Mileage Efficiency: 31.01
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 11
Invalid option, please try again.
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1444
Enter a trip counter : 382
Enter fuel consumed : 10.61
Make trip successful.
Mileage Efficiency: 36.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1555
Enter a trip counter : 324
Enter fuel consumed : 10.8
Make trip successful.
Mileage Efficiency: 30.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1666
Enter a trip counter : 406
Enter fuel consumed : 11.6
Make trip successful.
Mileage Efficiency: 35.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1777
Enter a trip counter : 297
Enter fuel consumed : 9
Make trip successful.
Mileage Efficiency: 33.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1888
Enter a trip counter : 259
Enter fuel consumed : 13.85
Make trip successful.
Mileage Efficiency: 18.70
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 1999
Enter a trip counter : 393
Enter fuel consumed : 11.9
Make trip successful.
Mileage Efficiency: 33.03
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 11111
Enter a trip counter : 422
Enter fuel consumed : 13.19
Make trip successful.
Mileage Efficiency: 31.99
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 12222
Enter a trip counter : 328
Enter fuel consumed : 9.7
Make trip successful.
Mileage Efficiency: 33.81
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 13333
Enter a trip counter : 366
Enter fuel consumed : 11.16
Make trip successful.
Mileage Efficiency: 32.80
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 14444
Enter a trip counter : 268
Enter fuel consumed : 7.4
Make trip successful.
Mileage Efficiency: 36.22
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 15555
Enter a trip counter : 300
Enter fuel consumed : 16.22
Make trip successful.
Mileage Efficiency: 18.50
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 1
Enter the serial number of the vehicle you want to make trip: 16666
Enter a trip counter : 392
Enter fuel consumed : 13.52
Make trip successful.
Mileage Efficiency: 28.99
Make trip again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 6

 --------------------
 List of Gasoline Vehicles :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
GasVehicle [Serial Number: 1222  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1333  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
GasVehicle [Serial Number: 1444  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1555  -  Made: Nissan  -  Model: Versa  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 324 - Fuel Consumed: 10.8 - Mileage Efficiency: 30.00]
GasVehicle [Serial Number: 1666  -  Made: Toyota  -  Model: Yaris  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 406 - Fuel Consumed: 11.6 - Mileage Efficiency: 35.00]
GasVehicle [Serial Number: 1777  -  Made: Chevrolet  -  Model: Spark  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 297 - Fuel Consumed: 9.0 - Mileage Efficiency: 33.00]
GasVehicle [Serial Number: 1888  -  Made: Chrysler  -  Model: Pacifica  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 259 - Fuel Consumed: 13.85 - Mileage Efficiency: 18.70]
GasVehicle [Serial Number: 1999  -  Made: Hyundai  -  Model: Accent  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 393 - Fuel Consumed: 11.9 - Mileage Efficiency: 33.03]
GasVehicle [Serial Number: 11111  -  Made: Subaru  -  Model: Impreza  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 422 - Fuel Consumed: 13.19 - Mileage Efficiency: 31.99]
GasVehicle [Serial Number: 12222  -  Made: Mazda  -  Model: 3  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 328 - Fuel Consumed: 9.7 - Mileage Efficiency: 33.81]
GasVehicle [Serial Number: 13333  -  Made: Acura  -  Model: ILX  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 366 - Fuel Consumed: 11.16 - Mileage Efficiency: 32.80]
GasVehicle [Serial Number: 14444  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 268 - Fuel Consumed: 7.4 - Mileage Efficiency: 36.22]
GasVehicle [Serial Number: 15555  -  Made: Cadillac  -  Model: Catera  -  Date of Manufacture: 1999-12-31  -  Trip Counter: 300 - Fuel Consumed: 16.22 - Mileage Efficiency: 18.50]
GasVehicle [Serial Number: 16666  -  Made: MercedesBenz  -  Model: CLA250  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 392 - Fuel Consumed: 13.52 - Mileage Efficiency: 28.99]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 8

 --------------------
 List of GasVehicles after sorting by mileage efficiency :

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
GasVehicle [Serial Number: 14444  -  Made: Jaguar  -  Model: FPace  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 268 - Fuel Consumed: 7.4 - Mileage Efficiency: 36.22]
GasVehicle [Serial Number: 1444  -  Made: Honda  -  Model: Fit  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 382 - Fuel Consumed: 10.61 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1222  -  Made: Kia  -  Model: Rio  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 428 - Fuel Consumed: 11.89 - Mileage Efficiency: 36.00]
GasVehicle [Serial Number: 1666  -  Made: Toyota  -  Model: Yaris  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 406 - Fuel Consumed: 11.6 - Mileage Efficiency: 35.00]
GasVehicle [Serial Number: 12222  -  Made: Mazda  -  Model: 3  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 328 - Fuel Consumed: 9.7 - Mileage Efficiency: 33.81]
GasVehicle [Serial Number: 1999  -  Made: Hyundai  -  Model: Accent  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 393 - Fuel Consumed: 11.9 - Mileage Efficiency: 33.03]
GasVehicle [Serial Number: 1777  -  Made: Chevrolet  -  Model: Spark  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 297 - Fuel Consumed: 9.0 - Mileage Efficiency: 33.00]
GasVehicle [Serial Number: 13333  -  Made: Acura  -  Model: ILX  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 366 - Fuel Consumed: 11.16 - Mileage Efficiency: 32.80]
GasVehicle [Serial Number: 11111  -  Made: Subaru  -  Model: Impreza  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 422 - Fuel Consumed: 13.19 - Mileage Efficiency: 31.99]
GasVehicle [Serial Number: 1333  -  Made: MINI  -  Model: Cooper  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 360 - Fuel Consumed: 11.61 - Mileage Efficiency: 31.01]
GasVehicle [Serial Number: 1555  -  Made: Nissan  -  Model: Versa  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 324 - Fuel Consumed: 10.8 - Mileage Efficiency: 30.00]
GasVehicle [Serial Number: 16666  -  Made: MercedesBenz  -  Model: CLA250  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 392 - Fuel Consumed: 13.52 - Mileage Efficiency: 28.99]
GasVehicle [Serial Number: 1888  -  Made: Chrysler  -  Model: Pacifica  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 259 - Fuel Consumed: 13.85 - Mileage Efficiency: 18.70]
GasVehicle [Serial Number: 15555  -  Made: Cadillac  -  Model: Catera  -  Date of Manufacture: 1999-12-31  -  Trip Counter: 300 - Fuel Consumed: 16.22 - Mileage Efficiency: 18.50]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 10

 --------------------
 GasVehicle having the best efficient mileage

GasVehicle [Serial Number: 1111  -  Made: Mitsubishi  -  Model: Mirage  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 359 - Fuel Consumed: 9.21 - Mileage Efficiency: 38.98]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2111
Enter a made : Volkswagen
Enter a model : ID4
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2222
Enter a made : Nissan
Enter a model : Leaf
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 12

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2333
Enter a made : Kandi
Enter a model : K27
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2444
Enter a made : Hyundai
Enter a model : IoniqElectric
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2555
Enter a made : BMW
Enter a model : i3
Enter the date of manufacture (yyyyMMdd) : 20210101
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2666
Enter a made : Tesla
Enter a model : X
Enter the date of manufacture (yyyyMMdd) : 20181231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2777
Enter a made : Porsche
Enter a model : TaycanTurbo
Enter the date of manufacture (yyyyMMdd) : 20201231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2888
Enter a made : Lucid
Enter a model : Air
Enter the date of manufacture (yyyyMMdd) : 20220202
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 2999
Enter a made : Scion
Enter a model : iQEV
Enter the date of manufacture (yyyyMMdd) : 20131231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 21111
Enter a made : Chevrolet
Enter a model : BoltEV
Enter the date of manufacture (yyyyMMdd) : 20191231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 22222
Enter a made : Fiat
Enter a model : 500e
Enter the date of manufacture (yyyyMMdd) : 20151231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 23333
Enter a made : Mitsubishi
Enter a model : iMiEV
Enter the date of manufacture (yyyyMMdd) : 20161231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 24444
Enter a made : Polestar
Enter a model : 2
Enter the date of manufacture (yyyyMMdd) : 20220202
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 25555
Enter a made : Rivian
Enter a model : R1S
Enter the date of manufacture (yyyyMMdd) : 20220202
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 26666
Enter a made : Audi
Enter a model : etron
Enter the date of manufacture (yyyyMMdd) : 20191231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 27777
Enter a made : Jaguar
Enter a model : IPace
Enter the date of manufacture (yyyyMMdd) : 20191231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter a serial number : 28888
Enter a made : BYD
Enter a model : e6
Enter the date of manufacture (yyyyMMdd) : 20171231
Vehicle added.
Add another vehicle? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 7

 --------------------
 List of Electric Vehicles :

ElectricVehicle [Serial Number: 2111  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2222  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2333  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2444  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2555  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2666  -  Made: Tesla  -  Model: X  -  Date of Manufacture: 2018-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2777  -  Made: Porsche  -  Model: TaycanTurbo  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2888  -  Made: Lucid  -  Model: Air  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2999  -  Made: Scion  -  Model: iQEV  -  Date of Manufacture: 2013-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 21111  -  Made: Chevrolet  -  Model: BoltEV  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 22222  -  Made: Fiat  -  Model: 500e  -  Date of Manufacture: 2015-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 23333  -  Made: Mitsubishi  -  Model: iMiEV  -  Date of Manufacture: 2016-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 24444  -  Made: Polestar  -  Model: 2  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 25555  -  Made: Rivian  -  Model: R1S  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 26666  -  Made: Audi  -  Model: etron  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 27777  -  Made: Jaguar  -  Model: IPace  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 28888  -  Made: BYD  -  Model: e6  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 2
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to remove: 27777
Vehicle removed.
Remove again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to remove: 28888
Vehicle removed.
Remove again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 3
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to search: 27777
Vehicle NOT found.
Search again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to search: 28888
Vehicle NOT found.
Search again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 5
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to update: 23333
Enter a made : BYD
Enter a model : e6
Enter the date of manufacture (yyyyMMdd) : 20171231
Vehicle updated.
Update a vehicle again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 3
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to search: 23333
ElectricVehicle [Serial Number: 23333  -  Made: BYD  -  Model: e6  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
Search again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 7

 --------------------
 List of Electric Vehicles :

ElectricVehicle [Serial Number: 2111  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2222  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2333  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2444  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2555  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2666  -  Made: Tesla  -  Model: X  -  Date of Manufacture: 2018-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2777  -  Made: Porsche  -  Model: TaycanTurbo  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2888  -  Made: Lucid  -  Model: Air  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 2999  -  Made: Scion  -  Model: iQEV  -  Date of Manufacture: 2013-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 21111  -  Made: Chevrolet  -  Model: BoltEV  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 22222  -  Made: Fiat  -  Model: 500e  -  Date of Manufacture: 2015-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 23333  -  Made: BYD  -  Model: e6  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 24444  -  Made: Polestar  -  Model: 2  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 25555  -  Made: Rivian  -  Model: R1S  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
ElectricVehicle [Serial Number: 26666  -  Made: Audi  -  Model: etron  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 100 - Kilowatt Power Consumed: 2.22 - Mileage Efficiency: 45.05]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 4
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2111
Enter a trip counter : 250
Enter kilowatt power consumed : 2.58
Make trip successful.
Mileage Efficiency: 96.90
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2222
Enter a trip counter : 149
Enter kilowatt power consumed : 1.34
Make trip successful.
Mileage Efficiency: 111.19
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2333
Enter a trip counter : 59
Enter kilowatt power consumed : 0.52
Make trip successful.
Mileage Efficiency: 113.46
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2444
Enter a trip counter : 170
Enter kilowatt power consumed : 1.28
Make trip successful.
Mileage Efficiency: 132.81
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2555
Enter a trip counter : 153
Enter kilowatt power consumed : 1.35
Make trip successful.
Mileage Efficiency: 113.33
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2666
Enter a trip counter : 190
Enter kilowatt power consumed : 2.13
Make trip successful.
Mileage Efficiency: 89.20
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2777
Enter a trip counter : 201
Enter kilowatt power consumed : 2.91
Make trip successful.
Mileage Efficiency: 69.07
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2888
Enter a trip counter : 520
Enter kilowatt power consumed : 4.16
Make trip successful.
Mileage Efficiency: 125.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 2999
Enter a trip counter : 38
Enter kilowatt power consumed : 0.31
Make trip successful.
Mileage Efficiency: 122.58
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 21111
Enter a trip counter : 238
Enter kilowatt power consumed : 2
Make trip successful.
Mileage Efficiency: 119.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 22222
Enter a trip counter : 87
Enter kilowatt power consumed : 0.75
Make trip successful.
Mileage Efficiency: 116.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 23333
Enter a trip counter : 187
Enter kilowatt power consumed : 2.6
Make trip successful.
Mileage Efficiency: 71.92
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 24444
Enter a trip counter : 270
Enter kilowatt power consumed : 2.52
Make trip successful.
Mileage Efficiency: 107.14
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 25555
Enter a trip counter : 316
Enter kilowatt power consumed : 4.58
Make trip successful.
Mileage Efficiency: 69.00
Make trip again? (type 1 to continue, otherwise type any other int to quit): 1
What type of Vehicle? (1-Gasoline Vehicle , 2-Electric Vehicle) : 2
Enter the serial number of the vehicle you want to make trip: 26666
Enter a trip counter : 204
Enter kilowatt power consumed : 2.76
Make trip successful.
Mileage Efficiency: 73.91
Make trip again? (type 1 to continue, otherwise type any other int to quit): 0

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 7

 --------------------
 List of Electric Vehicles :

ElectricVehicle [Serial Number: 2111  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
ElectricVehicle [Serial Number: 2222  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 2333  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 2444  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
ElectricVehicle [Serial Number: 2555  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 153 - Kilowatt Power Consumed: 1.35 - Mileage Efficiency: 113.33]
ElectricVehicle [Serial Number: 2666  -  Made: Tesla  -  Model: X  -  Date of Manufacture: 2018-12-31  -  Trip Counter: 190 - Kilowatt Power Consumed: 2.13 - Mileage Efficiency: 89.20]
ElectricVehicle [Serial Number: 2777  -  Made: Porsche  -  Model: TaycanTurbo  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 201 - Kilowatt Power Consumed: 2.91 - Mileage Efficiency: 69.07]
ElectricVehicle [Serial Number: 2888  -  Made: Lucid  -  Model: Air  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 520 - Kilowatt Power Consumed: 4.16 - Mileage Efficiency: 125.00]
ElectricVehicle [Serial Number: 2999  -  Made: Scion  -  Model: iQEV  -  Date of Manufacture: 2013-12-31  -  Trip Counter: 38 - Kilowatt Power Consumed: 0.31 - Mileage Efficiency: 122.58]
ElectricVehicle [Serial Number: 21111  -  Made: Chevrolet  -  Model: BoltEV  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 238 - Kilowatt Power Consumed: 2.0 - Mileage Efficiency: 119.00]
ElectricVehicle [Serial Number: 22222  -  Made: Fiat  -  Model: 500e  -  Date of Manufacture: 2015-12-31  -  Trip Counter: 87 - Kilowatt Power Consumed: 0.75 - Mileage Efficiency: 116.00]
ElectricVehicle [Serial Number: 23333  -  Made: BYD  -  Model: e6  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 187 - Kilowatt Power Consumed: 2.6 - Mileage Efficiency: 71.92]
ElectricVehicle [Serial Number: 24444  -  Made: Polestar  -  Model: 2  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 270 - Kilowatt Power Consumed: 2.52 - Mileage Efficiency: 107.14]
ElectricVehicle [Serial Number: 25555  -  Made: Rivian  -  Model: R1S  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 316 - Kilowatt Power Consumed: 4.58 - Mileage Efficiency: 69.00]
ElectricVehicle [Serial Number: 26666  -  Made: Audi  -  Model: etron  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 204 - Kilowatt Power Consumed: 2.76 - Mileage Efficiency: 73.91]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 9

 --------------------
 List of ElectricVehicles after sorting by mileage efficiency :

ElectricVehicle [Serial Number: 2444  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
ElectricVehicle [Serial Number: 2888  -  Made: Lucid  -  Model: Air  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 520 - Kilowatt Power Consumed: 4.16 - Mileage Efficiency: 125.00]
ElectricVehicle [Serial Number: 2999  -  Made: Scion  -  Model: iQEV  -  Date of Manufacture: 2013-12-31  -  Trip Counter: 38 - Kilowatt Power Consumed: 0.31 - Mileage Efficiency: 122.58]
ElectricVehicle [Serial Number: 21111  -  Made: Chevrolet  -  Model: BoltEV  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 238 - Kilowatt Power Consumed: 2.0 - Mileage Efficiency: 119.00]
ElectricVehicle [Serial Number: 22222  -  Made: Fiat  -  Model: 500e  -  Date of Manufacture: 2015-12-31  -  Trip Counter: 87 - Kilowatt Power Consumed: 0.75 - Mileage Efficiency: 116.00]
ElectricVehicle [Serial Number: 2333  -  Made: Kandi  -  Model: K27  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 59 - Kilowatt Power Consumed: 0.52 - Mileage Efficiency: 113.46]
ElectricVehicle [Serial Number: 2555  -  Made: BMW  -  Model: i3  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 153 - Kilowatt Power Consumed: 1.35 - Mileage Efficiency: 113.33]
ElectricVehicle [Serial Number: 2222  -  Made: Nissan  -  Model: Leaf  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 149 - Kilowatt Power Consumed: 1.34 - Mileage Efficiency: 111.19]
ElectricVehicle [Serial Number: 24444  -  Made: Polestar  -  Model: 2  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 270 - Kilowatt Power Consumed: 2.52 - Mileage Efficiency: 107.14]
ElectricVehicle [Serial Number: 2111  -  Made: Volkswagen  -  Model: ID4  -  Date of Manufacture: 2021-01-01  -  Trip Counter: 250 - Kilowatt Power Consumed: 2.58 - Mileage Efficiency: 96.90]
ElectricVehicle [Serial Number: 2666  -  Made: Tesla  -  Model: X  -  Date of Manufacture: 2018-12-31  -  Trip Counter: 190 - Kilowatt Power Consumed: 2.13 - Mileage Efficiency: 89.20]
ElectricVehicle [Serial Number: 26666  -  Made: Audi  -  Model: etron  -  Date of Manufacture: 2019-12-31  -  Trip Counter: 204 - Kilowatt Power Consumed: 2.76 - Mileage Efficiency: 73.91]
ElectricVehicle [Serial Number: 23333  -  Made: BYD  -  Model: e6  -  Date of Manufacture: 2017-12-31  -  Trip Counter: 187 - Kilowatt Power Consumed: 2.6 - Mileage Efficiency: 71.92]
ElectricVehicle [Serial Number: 2777  -  Made: Porsche  -  Model: TaycanTurbo  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 201 - Kilowatt Power Consumed: 2.91 - Mileage Efficiency: 69.07]
ElectricVehicle [Serial Number: 25555  -  Made: Rivian  -  Model: R1S  -  Date of Manufacture: 2022-02-02  -  Trip Counter: 316 - Kilowatt Power Consumed: 4.58 - Mileage Efficiency: 69.00]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 11

 --------------------
 ElectricVehicle having the best efficient mileage

ElectricVehicle [Serial Number: 2444  -  Made: Hyundai  -  Model: IoniqElectric  -  Date of Manufacture: 2020-12-31  -  Trip Counter: 170 - Kilowatt Power Consumed: 1.28 - Mileage Efficiency: 132.81]
 --------------------


----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 13
Invalid option, please try again.

----- Menu -----

1-Add a vehicle into the database. 
2-Remove a vehicle from the database. 
3-Search for a vehicle by serial number. 
4-Test a vehicle (make trip). 
5-Update a vehicle. 
6-List the gas vehicles. 
7-List the electric vehicles. 
8-Sort the gas vehicles by mileage efficiency. 
9-Sort the electric vehicles by mileage efficiency. 
10-Display the gas vehicle having the best efficient mileage 
11-Display the electric vehicle having the best efficient mileage 
12-Exit. 


Select an option: 12
The application is now closed.

 */
