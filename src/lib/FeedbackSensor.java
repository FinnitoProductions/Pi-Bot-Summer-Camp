package lib;

/**
 * Represents any type of sensor capable of reading data.
 * @author Finn Frankis
 * @version Jul 22, 2018
 */
public interface FeedbackSensor
{
    public abstract double getPosition();
}
