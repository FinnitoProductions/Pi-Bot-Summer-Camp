package lib;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public abstract class Subsystem
{
    Command defaultCommand;
    
    public Subsystem()
    {
        Scheduler.getInstance().addSubsystem(this);
    }
    
    public abstract void initDefaultCommand();
    
    public void setDefaultCommand (Command c)
    {
        defaultCommand = c;
    }
    
    public Command getDefaultCommand()
    {
        return defaultCommand;
    }
}
