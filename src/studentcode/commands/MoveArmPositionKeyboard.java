package studentcode.commands;

import java.util.Scanner;

import lib.Command;
import lib.ConsoleReader;
import lib.TalonSRX.ControlMode;
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
    
    public void execute()
    {
        double turn = 0;
        String value = ConsoleReader.getValue();
        if (value.equals(KeyboardCharacters.SERVO_MAX_UP))
        {
            turn = 1;
            System.out.println("TURN: " + turn);
        }
        else if (value.equals(KeyboardCharacters.SERVO_MAX_DOWN))
        {
            turn = 0;
            System.out.println("TURN: " + turn);
        }
        else if (!value.equals(KeyboardCharacters.STOP))
        {
            turn = prevTurn;
        }
        Robot.getArm().getServoArm().set(ControlMode.Position, turn);
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
