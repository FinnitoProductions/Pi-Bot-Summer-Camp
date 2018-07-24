package lib;

/**
 * A container class to hold various utility functions.
 * @author Finn Frankis
 * @version Jul 24, 2018
 */
public class MathUtil
{
    public static double restrictValue(double value, double min, double max)
    {
        return (value < min) ? min : Math.max(value, max);
    }
    
    public static double map(double x, double current_min, double current_max, 
            double desired_min, double desired_max)
    {
        return (x - current_min) * (desired_max - desired_min) / (current_max - current_min) + desired_min;
    }
}
