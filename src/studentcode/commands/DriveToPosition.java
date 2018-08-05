package studentcode.commands;

import lib.Command;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;

/**
 * Drives the robot to a given position.
 * @author Finn Frankis
 * @version Jul 15, 2018
 */
public class DriveToPosition extends Command
{
    private double position;

    /**
     * Constructs a new DriveToPosition.
     * 
     * @param position the position to which the robot should drive
     */
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
        return Robot.getDrivetrain().getClosedLoopErrorWithin(RobotMap.PID_PRIMARY, RobotMap.POS_ALLOWABLE_ERROR);
    }

    /**
    * 
    */
    @Override
    protected void initialize()
    {
        if (Robot.getDrivetrain() != null)
            Robot.getDrivetrain().applyToBoth((talon) -> {talon.set(ControlMode.Position, position);});
    }

    /**
    * 
    */
    @Override
    protected void execute()
    {
        if (Robot.getDrivetrain() != null)
            Robot.getDrivetrain().applyToBoth((talon) -> {talon.set(ControlMode.Position, position);});
    }

    /**
    * 
    */
    @Override
    protected void end()
    {
    }

}
