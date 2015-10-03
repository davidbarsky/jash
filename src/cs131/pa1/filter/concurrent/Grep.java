package cs131.pa1.filter.concurrent;

public class Grep extends ConcurrentFilter {
	
	private String searchPattern;
	
	public Grep(String pattern) throws MissingArgumentException, TooManyArgumentsException{
		
		if (pattern==null) {
			throw new MissingArgumentException();
		}
		
		if (pattern.contains(" ")) {
			throw new TooManyArgumentsException();
		}
		
		this.searchPattern = pattern;
	}

    @Deprecated
    public void run() {
        // implement later
    }
	
	@Override
	protected String processLine(String line) {
		if (line.contains(searchPattern)){
			return line;
		}
		return null;
	}
}
