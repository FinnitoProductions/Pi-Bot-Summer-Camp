package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Jul 9, 2018
 */
public class MoveClawPosition extends Command
{
    private double position;
    public MoveClawPosition(double position)
    {
        requires(Robot.getClaw());
        this.position = position;
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
