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
    
    /**
     * Constructs a new Robot.
     */
    public Robot()
    {
        super(RobotMap.AUTON_TIME_MS, RobotMap.TELEOP_TIME_MS);

    }

    /**
    * Called once when the autonomous period begins.
    */
    @Override
    public void autonomousInit()
    {
        getDrivetrain().talonInit();
        getArm().talonInit();
        getClaw().talonInit();
    }

    /**
    * Called periodically during the autonomous period.
    */
    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
        //Robot.getDrivetrain().printBothSensorPositions(RobotMap.PID_PRIMARY);
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
        //Robot.getDrivetrain().printBothSensorPositions(RobotMap.PID_PRIMARY);
    }
    
    /**
     * Gets the Drivetrain.
     * @return the instance of Drivetrain
     */
    public static Drivetrain getDrivetrain()
    {
        return dt;
    }
    
    /**
     * Gets the Arm.
     * @return the instance of Arm
     */
    public static Arm getArm()
    {
        return arm;
    }

    /**
     * Gets the Arm.
     * @return the instance of Claw
     */
    public static Claw getClaw()
    {
        return claw;
    }

    /**
    * Called once when the robot initializes.
    */
    @Override
    public void robotInit()
    {
        dt = Drivetrain.getInstance();
        arm = Arm.getInstance();
        claw = Claw.getInstance();
    }

    /**
    * Called periodically throughout the duration of the robot's being enabled.
    */
    @Override
    public void robotPeriodic()
    {
        
    }
 
}
