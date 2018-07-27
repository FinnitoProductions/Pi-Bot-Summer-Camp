package lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 * Wrapper class to simplify reading from the console.
 * @author Finn Frankis
 * @version Jul 15, 2018
 */
public class ConsoleReader
{
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static String prevValue = "";
    
    /**
     * Returns the value currently being sent to the console.
     * @return the String value typed into the console
     */
    public static String getValue()
    {   
        try
        {
            if (br.ready())
            {
                String currentLine = br.readLine();
                prevValue = currentLine != null ? currentLine : "";
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return prevValue;
    }
}