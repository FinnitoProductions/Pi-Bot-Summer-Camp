package lib;

/**
 * Represents any type of sensor capable of reading data.
 * @author Finn Frankis
 * @version Jul 22, 2018
 */
public abstract class FeedbackSensor
{
    private double previousPosition;
    private double previousTime;
   
    public abstract double getPosition();
    
    public double getVelocity()
    {
        double currentPosition = getPosition(), currentTime = System.currentTimeMillis();
        Double deltaT = currentTime - previousTime;
        
        if (previousTime == -1 || deltaT.equals(0))
            return 0;
        
        double velocity = (currentPosition - previousPosition) / (currentTime - previousTime);
        
        previousPosition = currentPosition; 
        previousTime = currentTime;
        
        return velocity;
    }
}
