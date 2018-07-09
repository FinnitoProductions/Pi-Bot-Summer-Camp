
package studentcode.subsystems;

import lib.Subsystem;
import lib.TalonSRX;
import lib.TalonSRX.ControlMode;
import lib.TalonSRX.DemandType;
import studentcode.commands.*;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Drivetrain extends Subsystem
{
    private static Drivetrain dt;
    private TalonSRX leftTalon;
    private TalonSRX rightTalon;
    
    public Drivetrain()
    {
        leftTalon = new TalonSRX(new int[] 
                {RobotMap.LEFT_MOTOR_ENABLE, 
                        RobotMap.LEFT_MOTOR_FORWARD, 
                        RobotMap.LEFT_MOTOR_BACKWARD});
        rightTalon = new TalonSRX(new int[] 
                {RobotMap.RIGHT_MOTOR_ENABLE, 
                        RobotMap.RIGHT_MOTOR_FORWARD, 
                        RobotMap.RIGHT_MOTOR_BACKWARD});
    }
    
    /**
    * 
    */
    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveWithVelocity());
    }
    
    public void arcadeDriveVelocity(double speed, double turn)
    {
        leftTalon.set(ControlMode.PercentOutput, speed, DemandType.FeedForward, +turn);
        rightTalon.set(ControlMode.PercentOutput, speed, DemandType.FeedForward, -turn);
    }

    public static Drivetrain getInstance()
    {
        if (dt == null)
            dt = new Drivetrain();
        return dt;
    }
}
