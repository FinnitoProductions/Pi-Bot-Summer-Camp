package studentcode.subsystems;

import lib.Subsystem;
import lib.TalonSRX;
import studentcode.commands.MoveClawVelocity;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 9, 2018
 */
public class Claw extends Subsystem
{
    TalonSRX claw;
    private static Claw thisClaw;
    /**
     * Constructs a new Claw.
     */
    public Claw()
    {
        claw = new TalonSRX(RobotMap.CLAW_SERVO_GPIO);
    }
    /**
    * 
    */
    @Override
    public void initDefaultCommand()
    {
        setDefaultCommand(new MoveClawVelocity());
    }
    
    public static Claw getInstance()
    {
        if (thisClaw == null)
            thisClaw = new Claw();
        return thisClaw;
    }
    
    public TalonSRX getClawTalon()
    {
        return claw;
    }
}
