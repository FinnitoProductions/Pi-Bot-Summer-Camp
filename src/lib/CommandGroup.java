package lib;

import java.util.ArrayList;

/**
 * @author joel
 *
 */
public abstract class CommandGroup extends Command {
	
	private ArrayList<ArrayList<Command>> commands;
	
	private int index;
	
	public CommandGroup() {
		commands = new ArrayList<ArrayList<Command>>();
		commands.add(new ArrayList<Command>());
	}
	
	protected void addSequential(Command c) {
		if(isRunning())
			throw new IllegalStateException("Cannot add commands while running");
		commands.get(commands.size() - 1).add(c);
		commands.add(new ArrayList<Command>());
	}
	
	protected void addParallel(Command c) {
		if(isRunning())
			throw new IllegalStateException("Cannot add commands while running");
		commands.get(commands.size() - 1).add(c);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.Command#isFinished()
	 */
	@Override
	protected boolean isFinished() {
		return index == commands.size();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.Command#initialize()
	 */
	@Override
	protected void initialize() {
		index = -1;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.Command#execute()
	 */
	@Override
	protected void execute() {
		boolean done = true;
		if(index >= 0 && index < commands.size())
			for(Command c : commands.get(index))
				if(c.isRunning())
					done = false;
		if(done)
			if(++index >= 0 && index < commands.size())
				for(Command c : commands.get(index))
					c.start();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * No cleanup required for standard ending
	 * 
	 * @see lib.Command#end()
	 */
	@Override
	protected void end() {}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.Command#interrupted()
	 */
	@Override
	protected void interrupted() {
		for(Command c : commands.get(index))
			if(c.isRunning())
				c.cancel();
	}
	
}
