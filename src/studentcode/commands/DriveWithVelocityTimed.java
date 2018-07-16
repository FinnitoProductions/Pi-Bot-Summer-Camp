package studentcode.commands;

import java.util.Scanner;

import lib.Command;
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
    
    public DriveWithVelocityTimed(double speed, double turn, double time_ms)
    {
        super (time_ms/1000);
        requires(Robot.getDrivetrain());
        this.speed = speed;
        this.turn = turn;
    }

    /**
    * 
    */
    @Override
    public void execute()
    {   
        Robot.getDrivetrain().arcadeDriveVelocity(speed, turn);
    }

    /**
    * 
    */
    @Override
    public void end()
    {
    }

    /**
    * 
    */
    @Override
    protected void postInitialize()
    {
        // TODO Auto-generated method stub
        
    }

}
