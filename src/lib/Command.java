
package lib;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public abstract class Command
{
    private Set<Subsystem> required;
    
    public Command() {
    		required = Collections.newSetFromMap(new IdentityHashMap<>());
    }
    
    public void requires(Subsystem s) { required.add(s); }
    
    public boolean doesRequire(Subsystem s) { return required.contains(s); }
    
    public Set<Subsystem> getRequired() { return required; }
    
    public abstract boolean isFinished();
    
    public abstract void initialize();
    public abstract void execute();
    public abstract void end();
    public void interrupted(){}
    
    public void cancel()
    {
        Scheduler.getInstance().remove(this, true);
    }
    
    public void start()
    {
        Scheduler.getInstance().add(this);
    }
    
    public boolean isRunning()
    {
        return Scheduler.getInstance().isRunning(this);
    }
}
