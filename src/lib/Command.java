
package lib;

import java.util.Set;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public abstract class Command
{
    private Set<Subsystem> required;
    private boolean isRunning;
    
    public void requires(Subsystem s) { required.add(s); }
    
    public boolean doesRequire(Subsystem s) { return required.contains(s); }
    
    public Set<Subsystem> getRequired() { return required; }
    
    public abstract boolean isFinished();
    
    public abstract void initialize();
    public abstract void execute();
    public abstract void end();
    
    public void interrupted()
    {
        end();
    }
    
    public void cancel()
    {
        end();
    }
    
    public void start()
    {
        Scheduler.getInstance().add(this);
        isRunning = true;
    }
    
    public boolean isRunning()
    {
        return isRunning;
    }
}
