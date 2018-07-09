package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Jul 9, 2018
 */
public class MoveClawVelocity extends Command
{
    public MoveClawVelocity()
    {
        requires(Robot.getClaw());
    }

    /**
    * @return
    */
    @Override
    public boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
    * 
    */
    @Override
    public void initialize()
    {
        // TODO Auto-generated method stub
        
    }

    /**
    * 
    */
    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        double actuateSpeed = 0; // get keyboard input
        Robot.getClaw().getClawTalon().set(ControlMode.PercentOutput, actuateSpeed);
    }

    /**
    * 
    */
    @Override
    public void end()
    {
        // TODO Auto-generated method stub
        
    }
}
