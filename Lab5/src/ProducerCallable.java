import java.util.concurrent.Callable;
import java.util.Random;

import static java.lang.Thread.sleep;

class ProducerCallable implements Callable<Object> {
    private int avgTimeToProduce = 1000; //ms
    private long startTime;
    private long workTime;
    private Queue queue;

    public ProducerCallable(int avgTimeToProduce, Queue queue, long startTime, long workTime){
        this.avgTimeToProduce = avgTimeToProduce;
        this.queue = queue;
        this.startTime = startTime;
        this.workTime = workTime;
    }
    @Override
    public Object call() throws Exception {
        int lowTimeLimit = (int) (avgTimeToProduce - (avgTimeToProduce * 0.5));
        int highTimeLimit = (int) (avgTimeToProduce + (avgTimeToProduce * 0.5));
        Random random = new Random();

        while(System.currentTimeMillis() - startTime <= workTime){

            int timeToProduce = random.nextInt((highTimeLimit - lowTimeLimit) + 1) + lowTimeLimit;
            sleep(timeToProduce);
            queue.put(1);
        }

        return null;
    }
}