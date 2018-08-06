package studentcode.subsystems;

import lib.commandbased.Subsystem;
import lib.devices.TalonSRX;
import lib.devices.TalonSRX.ControlMode;
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
    
    /**
     * Initializes the Talon on the claw.
     */
    public void talonInit()
    {
        setPosition(RobotMap.CLAW_OPEN_ANGLE);
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
    public void setPercent(double pOutput)
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
