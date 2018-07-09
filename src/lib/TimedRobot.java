package lib;

import studentcode.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public abstract class TimedRobot
{
    private int autonTime;
    private int teleopTime;
    private static TimedRobot instance;
    
    public TimedRobot(int autonTimeMs, int teleopTimeMs)
    {
        this.autonTime = autonTimeMs;
        this.teleopTime = teleopTimeMs;
        instance = new Robot();
    }
    
    public static void main(String[] args)
    {
        instance.run();
        System.exit(0);
    }
    
    public void run()
    {
        instance.autonomousInit();
        long startTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - startTime < autonTime)
                instance.autonomousPeriodic();
            else
                break;
        }
        instance.teleopInit();
        startTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - startTime < teleopTime)
                instance.teleopPeriodic();
            else
                break;
        }
        
    }
    
    public abstract void autonomousInit();
    public abstract void autonomousPeriodic();
    public abstract void teleopInit();
    public abstract void teleopPeriodic();
    
    public TimedRobot getInstance()
    {
        return instance;
    }
}
