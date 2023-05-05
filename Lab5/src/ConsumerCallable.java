import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

class ConsumerCallable implements Callable<Object> {

    private Queue queue;
    private long startTime;
    private long workTime;


    public ConsumerCallable(Queue queue, long startTime, long workTime){
        this.queue = queue;
        this.startTime = startTime;
        this.workTime = workTime;
    }
    @Override
    public Object call() throws Exception {
        while(System.currentTimeMillis() - startTime <= workTime){
            queue.serve();
        }
        return null;
    }
}