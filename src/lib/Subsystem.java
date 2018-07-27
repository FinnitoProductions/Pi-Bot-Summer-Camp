package lib;

/**
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public abstract class Subsystem
{
    private Command defaultCommand;
    
    public Subsystem()
    {
    		initDefaultCommand();
    		if(defaultCommand != null) {
    			Scheduler.getInstance().add(defaultCommand);
    			System.out.println ("default command " + defaultCommand);
    		}
    }
    
    public abstract void initDefaultCommand();
    
    public void setDefaultCommand (Command c)
    {
    		if(!c.doesRequire(this))
    		    c.requires(this);
//    			throw new IllegalArgumentException("A subsystem's default command must require it");
        defaultCommand = c;
    }
    
    public Command getDefaultCommand()
    {
        return defaultCommand;
    }
}
