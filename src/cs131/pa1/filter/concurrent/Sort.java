package cs131.pa1.filter.concurrent;

import java.util.ArrayList;
import java.util.Collections;

public class Sort extends ConcurrentFilter {

    private ArrayList<String> batchedInput;

    public Sort() {
        this.batchedInput = new ArrayList<String>();
    }

    @Override
    public void process() {
        while(!input.isEmpty()) {
            String line = input.poll();
            if (line == DONE) {
                break;
            } else {
                this.batchedInput.add(line);
            }
        }

        Collections.sort(batchedInput);

        for (String string : batchedInput) {
            try {
                output.put(string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String processLine(String line) {
        return null;
    }
}
