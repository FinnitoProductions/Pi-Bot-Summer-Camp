package studentcode.commands;

import lib.Command;
import lib.ConsoleReader;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;
import studentcode.robot.RobotMap.KeyboardCharacters;

/**
 * Drives the robot using input from the keyboard.
 * @author Finn Frankis
 * @version Jul 15, 2018
 */
public class DriveWithVelocityKeyboard extends Command
{
    private boolean isFinished;
    private double prevSpeed;
    private double prevTurn;
    
    public void initialize()
    {
        isFinished = false;
    }
    
    public void execute()
    {
        double speed = 0;
        double turn = 0;
        String value = ConsoleReader.getValue();
        if (value.equals(KeyboardCharacters.FORWARD))
            speed = RobotMap.MAX_SPEED;
        else if (value.equals(KeyboardCharacters.BACKWARD))
            speed = -RobotMap.MAX_SPEED;
        else if (value.equals(KeyboardCharacters.LEFT))
            turn = -RobotMap.MAX_TURN;
        else if (value.equals(KeyboardCharacters.RIGHT))
            turn = RobotMap.MAX_TURN;
        else if (!value.equals(KeyboardCharacters.STOP) && !value.equals(KeyboardCharacters.STOP_DRIVETRAIN))
        {
            // hold previous value if not being told to stop
            speed = prevSpeed;
            turn = prevTurn;
        }
        Robot.getDrivetrain().arcadeDriveVelocity(speed, turn);
        prevSpeed = speed;
        prevTurn = turn;
            
    }
    
    public boolean isFinished()
    {
        return isFinished;
    }
    /**
    * 
    */
    @Override
    protected void end()
    {
    }
}
