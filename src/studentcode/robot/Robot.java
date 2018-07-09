package studentcode.robot;

import lib.DigitalMotor;
import lib.Scheduler;
import lib.TimedRobot;
import studentcode.subsystems.*;

public class Robot extends TimedRobot
{
    private DigitalMotor left, right;
    private static Drivetrain dt;
    private static Arm arm;
    
    public Robot()
    {
        super(RobotMap.AUTON_TIME_MS, RobotMap.TELEOP_TIME_MS);
        dt = Drivetrain.getInstance();
        arm = Arm.getInstance();
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
    
    public static Arm getArm()
    {
        return arm;
    }

 
}
