
package lib;

import java.util.*;

/**
 * 
 * @author Finn Frankis
 * @version Jul 8, 2018
 */
public class Scheduler
{
    private static Scheduler sch;
    private Map<Subsystem, Command> runningCommands;
    
    private Scheduler()
    {
        runningCommands = new HashMap<Subsystem, Command>();
    }
    
    public static Scheduler getInstance() 
    {
        if (sch == null)
            sch = new Scheduler();
        return sch;
    }
    
    public void add(Command c)
    {
        Set<Subsystem> existingSubsystems = runningCommands.keySet();
        for (Subsystem s : c.getRequired())
        {
            if (existingSubsystems.contains(s))
            {
                if (runningCommands.get(s) != null || !runningCommands.get(s).isFinished())
                {
                    runningCommands.get(s).interrupted();
                }
            }
            runningCommands.put(s, c);
        }
        c.initialize();
    }
    
    public void run()
    {
        for (Subsystem s : runningCommands.keySet())
        {
            if (runningCommands.get(s) == null || runningCommands.get(s).isFinished())
                runningCommands.put(s, s.getDefaultCommand());
            else
                runningCommands.get(s).execute();
        }
    }
    
    public void addSubsystem(Subsystem s)
    {
        runningCommands.put(s, null);
    }
}
