package studentcode.robot;

import lib.DigitalMotor;
import lib.Scheduler;
import lib.TimedRobot;
import studentcode.subsystems.*;

public class Robot extends TimedRobot
{
    private DigitalMotor left, right;
    private static Drivetrain dt;
    
    public Robot()
    {
        super(RobotMap.AUTON_TIME, 135000);
        dt = Drivetrain.getInstance();
    }

    /**
    * 
    */
    @Override
    public void autonomousInit()
    {
        // TODO Auto-generated method stub
        
    }

    /**
    * 
    */
    @Override
    public void autonomousPeriodic()
    {
        // TODO Auto-generated method stub
        Scheduler.getInstance().run();
    }

    /**
    * 
    */
    @Override
    public void teleopInit()
    {
        // TODO Auto-generated method stub
        
    }

    /**
    * 
    */
    @Override
    public void teleopPeriodic()
    {
        // TODO Auto-generated method stub
        Scheduler.getInstance().run();
    }
    
    public static Drivetrain getDrivetrain()
    {
        return dt;
    }

 
}
