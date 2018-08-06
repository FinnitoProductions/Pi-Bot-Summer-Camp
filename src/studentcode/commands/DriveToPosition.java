package studentcode.commands;

import lib.commandbased.Command;
import lib.devices.TalonSRX.ControlMode;
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
        //requires (Robot.getDrivetrain());
        this.position = position;
    }
    /**
    * @return
    */
    @Override
    protected boolean isFinished()
    {
        return false;//Robot.getDrivetrain().getClosedLoopErrorWithin(RobotMap.PID_PRIMARY, RobotMap.POS_ALLOWABLE_ERROR);
    }

    /**
    * 
    */
    @Override
    protected void initialize()
    {
        Robot.getDrivetrain().selectProfileSlots(RobotMap.POS_PID_SLOT, RobotMap.PID_PRIMARY);
        Robot.getDrivetrain().applyToBoth((talon) -> {talon.set(ControlMode.Position, position);});
        
    }

    /**
    * 
    */
    @Override
    protected void execute()
    {
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
