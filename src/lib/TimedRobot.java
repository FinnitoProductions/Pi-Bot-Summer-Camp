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
    }
    
    public void run() throws InterruptedException
    {
        Thread initThread, introMessageThread;
        (initThread = new Thread() 
        {
            public void run()
            {
                robotInit();
                autonomousInit();
            }
        }).start();
        
        (introMessageThread = new Thread()
        {
            public void run()
            {
                try
                {
                    System.out.println("Drivers behind the line.");
                    Thread.sleep(900l);
                    System.out.println("Beginning in");
                    Thread.sleep(900l);
                    System.out.println("3...");
                    Thread.sleep(900l);
                    System.out.println("...2...");
                    Thread.sleep(900l);
                    System.out.println("...1");
                    Thread.sleep(900l);
                    System.out.println("POWER UP!");
                    System.out.println("Autonomous period beginning.");
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < autonTime)
                    {
                        autonomousPeriodic();
                        robotPeriodic();
                        Thread.sleep(5l); // pause to allow time for other operations
                    }
                    System.out.println("Teleoperated period beginning.");
                    teleopInit();
                    startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < teleopTime)
                    {
                        teleopPeriodic();
                        robotPeriodic();
                        Thread.sleep(5l); // pause to allow time for other operation
                    }
                    System.out.println("Match complete.");
                    System.exit(0);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
        
    public abstract void autonomousInit();
    public abstract void autonomousPeriodic();
    public abstract void teleopInit();
    public abstract void teleopPeriodic();
    public abstract void robotInit();
    public abstract void robotPeriodic();
    
    private class InitRobot extends Thread
    {
        public void run()
        {
            robotInit();
            autonomousInit();
        }
    }
    
}
