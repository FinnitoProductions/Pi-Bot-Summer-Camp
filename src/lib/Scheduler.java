
package lib;

import java.util.*;

/**
 * @author Finn Frankis
 * @version Jul 8, 2018
 * 
 *          The scheduler is extremely error-safe. Any individual command that
 *          throws an exception or invalid method call should not stop the other
 *          components from running. Therefore errors are frequently just
 *          printed rather than thrown. To force better practices upon the
 *          students this style could be reversed.
 */
public class Scheduler {
	private static Scheduler sch;
	private Set<Command> runningCommands;
	private Stack<Command> toRemove;
	
	private Scheduler() {
		runningCommands = Collections.newSetFromMap(new IdentityHashMap<>());
	}
	
	public static Scheduler getInstance() {
		if(sch == null)
			sch = new Scheduler();
		return sch;
	}
	
	public void add(Command c) {
		if(c == null)
			new IllegalArgumentException("Cannot start a null command").printStackTrace();
		if(runningCommands.add(c))
			new IllegalArgumentException("Command " + c.getClass().getSimpleName() + " is already running").printStackTrace();
		for(Command o : runningCommands)
			if(sharedSubsystem(c, o))
				toRemove.push(o);
		while(!toRemove.isEmpty())
			remove(toRemove.pop(), true);
		try {
			c.initialize();
		} catch(Exception e) {
			System.err.println("Error initializing command " + c.getClass().getSimpleName());
			e.printStackTrace();
		}
	}
	
	public void remove(Command c) {
		remove(c, false);
	}
	
	public void remove(Command c, boolean interrupt) {
		if(c == null) {
			new IllegalArgumentException("Cannot stop a null command").printStackTrace();;
		}
		if(!runningCommands.remove(c))
			new IllegalArgumentException("Command " + c.getClass().getSimpleName() + " is not running").printStackTrace();;
		try {
			if(interrupt)
				c.interrupted();
			c.end();
		} catch(Exception e) {
			System.err.println("Error ending command " + c.getClass().getSimpleName());
			e.printStackTrace();
		}
		for(Subsystem s : c.getRequired())
			if(s.getDefaultCommand() != null)
				add(s.getDefaultCommand());
	}
	
	public boolean isRunning(Command c) {
		return runningCommands.contains(c);
	}
	
	public void run() {
		for(Command c : runningCommands) {
			try {
				c.execute();
				if(c.isFinished())
					toRemove.push(c);
			} catch(Exception e) {
				System.err.println("Error running command " + c.getClass().getSimpleName());
				e.printStackTrace();
			}
		}
		while(!toRemove.isEmpty())
			remove(toRemove.pop(), false);
	}
	
	public void addSubsystem(Subsystem s) {
		add(s.getDefaultCommand());
	}
	
	/**
	 * Checks if two commands both use any one of the same subsystem and
	 * therefore would conflict
	 * 
	 * @param c
	 *            the first command
	 * @param o
	 *            the second command
	 * @return whether they have any shared subsystems
	 */
	public static boolean sharedSubsystem(Command c, Command o) {
		for(Subsystem s : c.getRequired())
			if(o.doesRequire(s))
				return true;
		return false;
	}
}
