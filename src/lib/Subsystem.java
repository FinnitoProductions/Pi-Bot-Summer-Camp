package lib;

/**
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public abstract class Subsystem
{
    Command defaultCommand;
    
    public Subsystem()
    {
    		initDefaultCommand();
    		if(defaultCommand != null) {
    			Scheduler.getInstance().add(defaultCommand);
    		}
    }
    
    public abstract void initDefaultCommand();
    
    public void setDefaultCommand (Command c)
    {
    		if(!c.doesRequire(this))
    			throw new IllegalArgumentException("A subsystem's default command must require it");
        defaultCommand = c;
    }
    
    public Command getDefaultCommand()
    {
        return defaultCommand;
    }
}
