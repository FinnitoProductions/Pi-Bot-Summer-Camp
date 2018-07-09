package studentcode.commands;

import lib.Command;
import studentcode.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class MoveArmPosition extends Command
{
    public MoveArmPosition()
    {
        requires (Robot.getArm());
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
