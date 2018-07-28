package studentcode.commands;

import java.time.ZoneId;
import java.util.Scanner;

import lib.Command;
import lib.ConsoleReader;
import lib.TalonSRX.ControlMode;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;
import studentcode.robot.RobotMap.KeyboardCharacters;
import studentcode.subsystems.Arm;

/**
 * 
 * @author Finn Frankis
 * @version Jul 27, 2018
 */
public class MoveClawPositionKeyboard extends Command
{
    private double prevTurn; 
   
    public void execute()
    {
        double turn = 0;
        String value = ConsoleReader.getValue();
        if (value.equals(KeyboardCharacters.CLAW_OPEN))
            turn = RobotMap.CLAW_OPEN_ANGLE; 
        else if (value.equals(KeyboardCharacters.CLAW_CLOSE))
            turn = RobotMap.CLAW_CLOSE_ANGLE;
        else if (!value.equals(KeyboardCharacters.STOP))
            turn = prevTurn;
        else if (value.equals(KeyboardCharacters.STOP))
        {
            Robot.getClaw().setPercent(0);
            return;
        }
        
        Robot.getClaw().setPosition(turn);
        prevTurn = turn;
            
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
