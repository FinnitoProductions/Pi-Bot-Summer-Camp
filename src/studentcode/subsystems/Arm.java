package studentcode.subsystems;

import lib.Subsystem;
import lib.TalonSRX;
import studentcode.commands.*;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Arm extends Subsystem
{
    private static Arm arm;

    private TalonSRX servoArm;
    
    public Arm()
    {
        servoArm = new TalonSRX(RobotMap.ARM_SERVO_GPIO);
    }

    
    public static Arm getInstance()
    {
        if (arm == null)
            arm = new Arm();
        return arm;
    }
    
    public void moveArmPercentOutput()
    {
        
    }
    public TalonSRX getServoArm()
    {
        return servoArm;
    }


    /**
    * 
    */
    @Override
    public void initDefaultCommand()
    {
        setDefaultCommand(new MoveArmPosition(0.9));
        
    }
}
