package studentcode.commands;

import lib.Command;
import studentcode.robot.Robot;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class DriveWithVelocity extends Command
{

    public DriveWithVelocity()
    {
//        requires(Robot.getDrivetrain());
    }
    /**
    * @return
    */
    @Override
    public boolean isFinished()
    {
        return false;
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
        double speed = 1; // get keyboard input
        double turn = 0; // get keyboard input
        Robot.getDrivetrain().arcadeDriveVelocity(speed, turn);
    }

    /**
    * 
    */
    @Override
    public void end()
    {
    }

}
