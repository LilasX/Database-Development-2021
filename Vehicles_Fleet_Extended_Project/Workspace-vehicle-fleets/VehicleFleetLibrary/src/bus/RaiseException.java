package bus;

/**
 * Throws an exception which occurs during the execution of a program, 
 * that disrupts the normal flow of the program's instructions. 
 * When an error occurs within a method, the method creates an object 
 * and hands it off to the runtime system. 
 * The object, called an exception object, contains information about the error, 
 * including its type and the state of the program when the error occurred. 
 * Creating an exception object and handing it to the runtime system is called throwing an exception.
 * (https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html)
 * 
 * @author Lilas Guan
 * @version 2021-12-14
 * @see Exception
 */
public class RaiseException extends Exception
{
	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	
	private static String message = "RAISE EXCEPTION : Invalid input!";
	
	/**
	 * Default Constructor of class RaiseException 
	 * which inherits from the constructor of the superclass Exception.
	 * @see Exception
	 */
	public RaiseException()
	{
		super(message) ;
	}
	
	/**
	 * Constructs a new exception with the specified detail message
	 * which inherits from the constructor of the superclass Exception.
	 * @param msg the exception message shown when an error occurs
	 * 		  during the execution of the application.
	 * @see Exception
	 */
	public RaiseException(String msg)
	{
		super (msg);
	}
}

