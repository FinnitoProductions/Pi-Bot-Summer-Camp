
package studentcode.subsystems;

import lib.Subsystem;
import lib.TalonSRX;
import lib.TalonSRX.ControlMode;
import lib.TalonSRX.DemandType;
import lib.TalonSRX.FeedbackDevice;
import studentcode.commands.*;
import studentcode.robot.RobotMap;

/**
 * Represents the drivetrain on the robot.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Drivetrain extends Subsystem
{
    private static Drivetrain dt;
    private TalonSRX leftTalon;
    private TalonSRX rightTalon;
    
    /**
     * Constructs a new Drivetrain.
     */
    public Drivetrain()
    {
        leftTalon = new TalonSRX(RobotMap.LEFT_MOTOR_ENABLE, 
                        RobotMap.LEFT_MOTOR_FORWARD, 
                        RobotMap.LEFT_MOTOR_BACKWARD);
        rightTalon = new TalonSRX(RobotMap.RIGHT_MOTOR_ENABLE, 
                        RobotMap.RIGHT_MOTOR_FORWARD, 
                        RobotMap.RIGHT_MOTOR_BACKWARD);
    }
    
    public void talonInit()
    {
        System.out.println("DT TALON INIT");
        leftTalon.setupEncoder(RobotMap.LEFT_ENCODER_ORANGE, RobotMap.LEFT_ENCODER_BROWN);
        rightTalon.setupEncoder(RobotMap.RIGHT_ENCODER_ORANGE, RobotMap.RIGHT_ENCODER_BROWN);
        
        leftTalon.configSelectedFeedbackSensor(FeedbackDevice.MagneticEncoder, RobotMap.PID_PRIMARY, RobotMap.TIMEOUT);
        rightTalon.configSelectedFeedbackSensor(FeedbackDevice.MagneticEncoder, RobotMap.PID_PRIMARY, RobotMap.TIMEOUT);
    }
    /**
    * Sets up the default command for this subsystem (the one which the subsystem will default to 
    * when no other commands are being sent).
    */
    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveWithVelocityKeyboard());
    }
    
    /**
     * Drives the robot with a given speed and turn.
     * @param speed the speed at which the robot is to be driven
     * @param turn the amount by which the robot should turn
     */
    public void arcadeDriveVelocity(double speed, double turn)
    {
        leftTalon.set(ControlMode.PercentOutput, speed, DemandType.FeedForward, +turn);
        rightTalon.set(ControlMode.PercentOutput, speed, DemandType.FeedForward, -turn);
    }

    /**
     * Gets this singleton instance of Drivetrain.
     * @return the instance of Drivetrain
     */
    public static Drivetrain getInstance()
    {
        if (dt == null)
            dt = new Drivetrain();
        return dt;
    }
    
    /**
     * Gets the left Talon on the Drivetrain.
     * @return the left Talon
     */
    public TalonSRX getLeftTalon()
    {
        return leftTalon;
    }
    
    /**
     * Gets the right Talon on the Drivetrain.
     * @return the right Talon
     */
    public TalonSRX getRightTalon()
    {
        return rightTalon;
    }
}
