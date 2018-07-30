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
    }

    /**
    * 
    */
    @Override
    protected void end()
    {
    }

}
