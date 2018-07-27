package lib;

import java.util.Scanner;

/**
 * Wrapper class to simplify reading from the console.
 * @author Finn Frankis
 * @version Jul 15, 2018
 */
public class ConsoleReader
{
    private static Scanner sc = new Scanner(System.in);
    
    /**
     * Returns the value currently being sent to the console.
     * @return the String value typed into the console; "" if a line is not currently being passed in
     */
    public static String getValue()
    {
        if (sc.hasNextLine())
            return sc.nextLine();
        return "";
    }
    
    /**
     * Waits for a line to be sent to the console.
     * @return the value sent to the console, once it is sent
     */
    public static String getValueHold()
    {
        return sc.nextLine();
    }
}