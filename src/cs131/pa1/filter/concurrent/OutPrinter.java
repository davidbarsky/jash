package cs131.pa1.filter.concurrent;

import java.util.*;
import java.util.concurrent.*;

public class OutPrinter extends ConcurrentFilter {
	
	private boolean standardError = false;
	
	public OutPrinter() {
		super();
	}

    @Override
    public void run() {
        this.process();
    }
	
	public OutPrinter(String errorMessage) {
		standardError = true;
		input = new LinkedBlockingQueue<String>();
		input.add(errorMessage);
	}
	
	public boolean isStandardError() {
		return standardError;
	}
	
	@Override
	protected String processLine(String line) {
        if (line == DONE) {
            return null;
        } else {
            System.out.println(line);
        }
        return null;
	}
}
