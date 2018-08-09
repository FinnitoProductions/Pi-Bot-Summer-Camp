package studentcode.commands;

import lib.commandbased.CommandGroup;

/**
 * 
 * @author Finn Frankis
 * @version Aug 9, 2018
 */
public class AutonomousCommand extends CommandGroup {
    public AutonomousCommand() {
        addSequential (new DriveToPosition(50));
    }
}
