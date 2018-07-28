package studentcode.subsystems;

import lib.Subsystem;
import lib.TalonSRX;
import lib.TalonSRX.ControlMode;
import studentcode.commands.MoveArmPositionKeyboard;
import studentcode.commands.MoveClawPosition;
import studentcode.commands.MoveClawPositionKeyboard;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 9, 2018
 */
public class Claw extends Subsystem
{
    TalonSRX servoClaw;
    private static Claw claw;
    /**
     * Constructs a new Claw.
     */
    public Claw()
    {
        servoClaw = new TalonSRX(RobotMap.CLAW_SERVO_GPIO);
    }
    
    public static Claw getInstance()
    {
        if (claw == null)
            claw = new Claw();
        return claw;
    }
    
    public TalonSRX getClawTalon()
    {
        return servoClaw;
    }
    
    /**
     * Sets the arm to a given position.
     * @param position the position for the servo [0, 1023]
     */
    public void setPosition (double position)
    {
        servoClaw.set(ControlMode.Position, position);
    }
    
    /**
     * Sets the arm to a given percentage output.
     * @param pOutput the percentage to which the servo should turn [0, 1]
     */
    public void setArmPercent(double pOutput)
    {
        servoClaw.set(ControlMode.PercentOutput, pOutput);
    }


    /**
    * 
    */
    @Override
    public void initDefaultCommand()
    {
        setDefaultCommand(new MoveClawPositionKeyboard());
    }
}
