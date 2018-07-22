package studentcode.commands;

import lib.TimedCommand;
import studentcode.robot.Robot;

/**
 * Drives with a given velocity for a given time.
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class DriveWithVelocityTimed extends TimedCommand
{
    private double speed;
    private double turn;
    
    /**
     * Constructs a new DriveWithVelocityTimed.
     * @param speed the speed at which the robot should be driven forward
     * @param turn the speed at which the robot should turn
     * @param time_ms the amount of time (in milliseconds) for which the robot should drive
     */
    public DriveWithVelocityTimed(double speed, double turn, double time_ms)
    {
        super (time_ms/1000);
        requires(Robot.getDrivetrain());
        this.speed = speed;
        this.turn = turn;
    }

    /**
    * Executes the command periodically.
    */
    @Override
    public void execute()
    {   
        Robot.getDrivetrain().arcadeDriveVelocity(speed, turn);
    }

    /**
    * Called when the command ends peacefully.
    */
    @Override
    public void end()
    {
    }

    /**
    * To be called after standard initialization which is necessary for a timed command.
    */
    @Override
    protected void postInitialize()
    {

    }

}
