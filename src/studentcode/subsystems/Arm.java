package studentcode.subsystems;

import lib.Subsystem;
import lib.TalonSRX;
import lib.TalonSRX.ControlMode;
import studentcode.commands.*;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Arm extends Subsystem
{
    private static Arm arm = new Arm();

    private TalonSRX servoArm;
    
    public Arm()
    {
        servoArm = new TalonSRX(RobotMap.ARM_SERVO_GPIO);
    }

    public void talonInit()
    {
        servoArm.set(ControlMode.PercentOutput, 0);
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
    public TalonSRX getServo()
    {
        return servoArm;
    }
    
    /**
     * Sets the arm to a given position.
     * @param position the position for the servo [0, 1023]
     */
    public void setPosition (double position)
    {
        servoArm.set(ControlMode.Position, position);
    }
    
    /**
     * Sets the arm to a given percentage output.
     * @param pOutput the percentage to which the servo should turn [0, 1]
     */
    public void setArmPercent(double pOutput)
    {
        servoArm.set(ControlMode.PercentOutput, pOutput);
    }


    /**
    * 
    */
    @Override
    public void initDefaultCommand()
    {
        setDefaultCommand(new MoveArmPositionKeyboard());
    }
}
