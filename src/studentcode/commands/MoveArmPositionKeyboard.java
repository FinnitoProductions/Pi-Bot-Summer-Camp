package studentcode.commands;

import lib.commandbased.Command;
import lib.util.ConsoleReader;
import lib.util.MathUtil;
import studentcode.robot.Robot;
import studentcode.robot.RobotMap;
import studentcode.robot.RobotMap.KeyboardCharacters;

/**
 * 
 * @author Finn Frankis
 * @version Jul 27, 2018
 */
public class MoveArmPositionKeyboard extends Command
{
    private double prevTurn; 
   
    public MoveArmPositionKeyboard()
    {
        prevTurn = RobotMap.ARM_INTAKE_ANGLE;
    }
    public void execute()
    {
        double turn = 0;
        String value = ConsoleReader.getValue();
        if (value.equals(KeyboardCharacters.ARM_OUTTAKE))
            turn = RobotMap.ARM_OUTTAKE_ANGLE; 
        else if (value.equals(KeyboardCharacters.ARM_INTAKE))
            turn = RobotMap.ARM_INTAKE_ANGLE;
        else if (!value.equals(KeyboardCharacters.STOP) && !MathUtil.isNumber(value))
            turn = prevTurn;
        else if (value.equals(KeyboardCharacters.STOP))
        {
            Robot.getArm().setPercent(0);
            return;
        }
        Robot.getArm().setPosition(turn);
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
    }

    /**
    * 
    */
    @Override
    protected void end()
    {
    }
}
