package studentcode.robot;

import lib.Scheduler;
import lib.TimedRobot;
import studentcode.subsystems.*;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Robot extends TimedRobot
{
    private static Drivetrain dt;
    private static Arm arm;
    private static Claw claw;
    
    public Robot()
    {
        super(RobotMap.AUTON_TIME_MS, RobotMap.TELEOP_TIME_MS);
    }

    /**
    * 
    */
    @Override
    public void autonomousInit()
    {
        getDrivetrain().talonInit();
        getArm().talonInit();
        getClaw().talonInit();
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
    * Called once when the teleoperated period begins.
    */
    @Override
    public void teleopInit()
    {
    }

    /**
    * Called periodically during the teleoperated period.
    */
    @Override
    public void teleopPeriodic()
    {
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

    public static Claw getClaw()
    {
        return claw;
    }

    /**
    * 
    */
    @Override
    public void robotInit()
    {
        dt = Drivetrain.getInstance();
        arm = Arm.getInstance();
        claw = Claw.getInstance();
    }

    /**
    * 
    */
    @Override
    public void robotPeriodic()
    {
        
    }
 
}
