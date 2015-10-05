package cs131.pa1.filter.concurrent;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import cs131.pa1.filter.Filter;

// THIS IS SEQUENTIAL. YOU MUST MAKE IT CONCURRENT.
public abstract class ConcurrentFilter extends Filter implements Runnable {
	
	protected LinkedBlockingQueue<String> input;
	protected LinkedBlockingQueue<String> output;
	
	@Override
	public void setPrevFilter(Filter prevFilter) {
		prevFilter.setNextFilter(this);
	}
	
	@Override
	public void setNextFilter(Filter nextFilter) {
		if (nextFilter instanceof ConcurrentFilter){
			ConcurrentFilter concurrentNext = (ConcurrentFilter) nextFilter;
			this.next = concurrentNext;
			concurrentNext.prev = this;
			if (this.output == null){
				this.output = new LinkedBlockingQueue<String>();
			}
			concurrentNext.input = this.output;
		} else {
			throw new RuntimeException("Should not attempt to link dissimilar filter types.");
		}
	}

    @Override
    public

	public void process(){
		while (!input.isEmpty()){
			String line = input.poll();
			String processedLine = processLine(line);
			if (processedLine != null){
                try {
                    output.put(processedLine);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
		}	
	}

	@Override
	public boolean isDone() {
        return true;
	}

	protected abstract String processLine(String line);
}
