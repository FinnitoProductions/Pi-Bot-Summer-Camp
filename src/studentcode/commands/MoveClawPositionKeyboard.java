package studentcode.commands;

import lib.Command;
import lib.ConsoleReader;
import lib.MathUtil;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;
import studentcode.robot.RobotMap.KeyboardCharacters;

/**
 * 
 * @author Finn Frankis
 * @version Jul 27, 2018
 */
public class MoveClawPositionKeyboard extends Command
{
    private double prevTurn; 
    public MoveClawPositionKeyboard()
    {
        prevTurn = RobotMap.CLAW_OPEN_ANGLE;
    }
    public void execute()
    {
        double turn = 0;
        String value = ConsoleReader.getValue();
  
        if (value.equals(KeyboardCharacters.CLAW_OPEN))
        {
            turn = RobotMap.CLAW_OPEN_ANGLE; 
        }
        else if (value.equals(KeyboardCharacters.CLAW_CLOSE))
        {
            turn = RobotMap.CLAW_CLOSE_ANGLE;
        }
        else if (!value.equals(KeyboardCharacters.STOP) && !MathUtil.isNumber(value))
        {
            turn = prevTurn;
        }
        else if (value.equals(KeyboardCharacters.STOP) || 
                (Math.abs(prevTurn - RobotMap.CLAW_OPEN_ANGLE) < 0.1 && 
                        ConsoleReader.getNumExecutesWithoutChange() > 20))
        {
            Robot.getClaw().setPercent(0);
            return;
        }
        else
        {
            try
            {
                turn = Integer.parseInt(value);
            }
            catch (NumberFormatException e)
            {
                turn = prevTurn;
            }
            
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
