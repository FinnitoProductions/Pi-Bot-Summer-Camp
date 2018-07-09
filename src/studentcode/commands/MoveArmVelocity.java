package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;

/**
 * Moves the servo arm with a given velocity.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class MoveArmVelocity extends Command
{

    public MoveArmVelocity()
    {
        requires (Robot.getArm());
    }
    /**
    * @return true if the command has finished; false otherwise
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
        double outputPercent = 0; // get the keyboard value
        Robot.getArm().getServoArm().set(ControlMode.PercentOutput, outputPercent);
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
