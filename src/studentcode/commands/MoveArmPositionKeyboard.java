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
public class MoveArmPositionKeyboard extends Command
{
    private double prevTurn; 
   
    public void execute()
    {
        double turn = 0;
        String value = ConsoleReader.getValue();
        if (value.equals(KeyboardCharacters.ARM_OUTTAKE))
            turn = RobotMap.ARM_OUTTAKE_ANGLE; 
        else if (value.equals(KeyboardCharacters.ARM_INTAKE))
            turn = RobotMap.ARM_INTAKE_ANGLE;
        else if (!value.equals(KeyboardCharacters.STOP) && value.length() == 1)
            turn = prevTurn;
        else if (value.equals(KeyboardCharacters.STOP))
        {
            Robot.getArm().setPercent(0);
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
