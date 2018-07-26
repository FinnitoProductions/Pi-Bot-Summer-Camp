package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;

/**
 * Drives the robot to a given position.
 * @author Finn Frankis
 * @version Jul 15, 2018
 */
public class DriveToPosition extends Command
{
    private double position;
    public DriveToPosition(double position)
    {
        requires (Robot.getDrivetrain());
        this.position = position;
    }
    /**
    * @return
    */
    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
    * 
    */
    @Override
    protected void initialize()
    {
        Robot.getDrivetrain().getLeftTalon().set(ControlMode.Position, position);
        Robot.getDrivetrain().getRightTalon().set(ControlMode.Position, position);
    }

    /**
    * 
    */
    @Override
    protected void execute()
    {
        // TODO Auto-generated method stub
        
    }

    /**
    * 
    */
    @Override
    protected void end()
    {
        // TODO Auto-generated method stub
        
    }

}
