package studentcode.robot;


import lib.DigitalMotor;
import lib.Encoder;
import lib.Scheduler;
import lib.TimedRobot;
import studentcode.subsystems.*;

public class Robot extends TimedRobot
{
    private DigitalMotor left, right;
    private static Drivetrain dt;
    private static Arm arm;
    private static Claw claw;
    private Encoder leftEn;
    private Encoder rightEn;
    
    public Robot()
    {
        super(RobotMap.AUTON_TIME_MS, RobotMap.TELEOP_TIME_MS);
        dt = Drivetrain.getInstance();
        //arm = Arm.getInstance();
        //claw = Claw.getInstance();
    }

    /**
    * 
    */
    @Override
    public void autonomousInit()
    {
        // TODO Auto-generated method stub
        //leftEn = new Encoder (RobotMap.LEFT_ENCODER_ORANGE, RobotMap.LEFT_ENCODER_BROWN);
        rightEn = new Encoder (RobotMap.RIGHT_ENCODER_ORANGE, RobotMap.RIGHT_ENCODER_BROWN);
    }

    /**
    * 
    */
    @Override
    public void autonomousPeriodic()
    {
        // TODO Auto-generated method stub
        Scheduler.getInstance().run();
        //System.out.println(leftEn.getEncoderCount());
        System.out.println(rightEn.getEncoderCount());
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
        //System.out.println(leftEn.getEncoderCount());
        System.out.println(rightEn.getEncoderCount());
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
 
}
