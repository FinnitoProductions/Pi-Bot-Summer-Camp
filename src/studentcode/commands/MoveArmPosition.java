package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;
import studentcode.subsystems.Arm;

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
    }

    /**
    * 
    */
    @Override
    public void execute()
    {
        Robot.getArm().setPercent(position);
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
