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
    
    public TimedRobot(int autonTimeMs, int teleopTimeMs)
    {
        this.autonTime = autonTimeMs;
        this.teleopTime = teleopTimeMs;
    }
    
    // move to Robot.java
    public static void main(String[] args)
    {
        new Robot().run();
        System.exit(0);
    }
    
    public void run()
    {
        autonomousInit();
        long startTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - startTime < autonTime)
            {
                autonomousPeriodic();
                try
                {
                    Thread.sleep(5l);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
                break;
        }
        teleopInit();
        startTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - startTime < teleopTime)
            {
                teleopPeriodic();
                try
                {
                    Thread.sleep(5l);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
                break;
        }
        
    }
    
    public abstract void autonomousInit();
    public abstract void autonomousPeriodic();
    public abstract void teleopInit();
    public abstract void teleopPeriodic();
}
