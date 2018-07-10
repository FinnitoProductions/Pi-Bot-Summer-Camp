package lib;

/**
 * @author joel
 *
 */
public abstract class InstantCommand extends Command {
	
	/* (non-Javadoc)
	 * @see lib.Command#isFinished()
	 */
	@Override
	public boolean isFinished() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see lib.Command#execute()
	 */
	@Override
	public void execute() {}
	
	/* (non-Javadoc)
	 * @see lib.Command#end()
	 */
	@Override
	public void end() {}
	
}
