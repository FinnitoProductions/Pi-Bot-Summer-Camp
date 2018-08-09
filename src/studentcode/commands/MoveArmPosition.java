package studentcode.commands;

import lib.commandbased.Command;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class MoveArmPosition extends Command
{
    private double position;
    /**
     * 
     * 
     * @author Finn Frankis
     * @version Aug 9, 2018
     */
    public enum ArmPosition
    {
        RAISED, LOWERED
    }
    public MoveArmPosition(double position)
    {
        requires (Robot.getArm());
        this.position = position;
    }
    
    public MoveArmPosition (ArmPosition position)
    {
        this.position = position == ArmPosition.RAISED ? RobotMap.ARM_OUTTAKE_ANGLE : RobotMap.ARM_INTAKE_ANGLE;
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
    }
    
}
