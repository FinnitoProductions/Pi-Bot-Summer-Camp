package studentcode.commands;

import lib.commandbased.Command;
import lib.devices.TalonSRX.ControlMode;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 9, 2018
 */
public class MoveClawPosition extends Command
{
    private double position;
    
    /**
     * 
     * 
     * @author Finn Frankis
     * @version Aug 9, 2018
     */
    public enum ClawPosition {
        OPEN, CLOSED
    }
    public MoveClawPosition(double position)
    {
        requires(Robot.getClaw());
        this.position = position;
    }
    
    public MoveClawPosition (ClawPosition position)
    {
        this.position = position == ClawPosition.OPEN ? RobotMap.CLAW_OPEN_ANGLE : RobotMap.CLAW_CLOSE_ANGLE;
    }

    /**
    * @return
    */
    @Override
    public boolean isFinished()
    {
        return true;
    }

    /**
    * 
    */
    @Override
    public void initialize()
    {
        Robot.getClaw().getClawTalon().set(ControlMode.Position, position);   
    }

    /**
    * 
    */
    @Override
    public void execute()
    {
    }

    /**
    * 
    */
    @Override
    public void end()
    {
    }
}
