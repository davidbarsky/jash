package cs131.pa1.filter.concurrent;

public class Pwd extends ConcurrentFilter implements Runnable {
	
	@Override
	public void process() {
		this.output.add(ConcurrentREPL.currentWorkingDirectory);
        this.output.add(DONE);
	}

	@Override
	protected String processLine(String line) {
		// Should never be called, as we have overridden process.
		return null;
	}
}
