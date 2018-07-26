package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class MoveArmPosition extends Command
{
    private double position;
    public MoveArmPosition(double position)
    {
        requires (Robot.getArm());
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
        Robot.getArm().getServoArm().set(ControlMode.Position, position);
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
