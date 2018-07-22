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
    public static void main(String[] args) throws InterruptedException
    {
        new Robot().run();
        System.exit(0);
    }
    
    public void run() throws InterruptedException
    {
        System.out.println("Drivers behind the line.");
        Thread.sleep(1200l);
        System.out.println("Beginning in");
        Thread.sleep(1200l);
        System.out.println("3...");
        Thread.sleep(1200l);
        System.out.println("...2...");
        Thread.sleep(1200l);
        System.out.println("...1");
        Thread.sleep(1200l);
        System.out.println("POWER UP!");
        
        System.out.println("Autonomous period beginning.");
        autonomousInit();
        long startTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - startTime < autonTime)
            {
                autonomousPeriodic();
                Thread.sleep(5l); // pause to allow time for other operations
            }
            else
                break;
        }
        System.out.println("Teleoperated period beginning.");
        teleopInit();
        startTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - startTime < teleopTime)
            {
                teleopPeriodic();
                Thread.sleep(5l); // pause to allow time for other operations
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
