package studentcode.commands;

import lib.commandbased.CommandGroup;
import studentcode.commands.MoveArmPosition.ArmPosition;
import studentcode.commands.MoveClawPosition.ClawPosition;

/**
 * 
 * @author Finn Frankis
 * @version Aug 9, 2018
 */
public class AutonomousCommand extends CommandGroup {
    public AutonomousCommand() {
        addSequential (new DriveToPosition(50));
        /*addSequential (new MoveArmPosition(ArmPosition.LOWERED));
        addSequential (new MoveClawPosition(ClawPosition.CLOSED));*/
        //addSequential (new DriveToPosition(-50));
    }
}
