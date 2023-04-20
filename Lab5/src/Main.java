import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {
        Queue queue1 = new Queue(30); //max 10 elements in queue
        Queue queue2 = new Queue(30); //max 10 elements in queue
        Queue queue3 = new Queue(30); //max 10 elements in queue


        long startTime = System.currentTimeMillis();
        long workTime = 6000;

        var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(16);
        List<Callable<Object>> callables = new ArrayList<>();

        ModelRunCallable runCallable1 = new ModelRunCallable(queue1,startTime,workTime,3,false, 1);
        ModelRunCallable runCallable2 = new ModelRunCallable(queue2,startTime,workTime,3,false, 2);
        ModelRunCallable runCallable3 = new ModelRunCallable(queue3,startTime,workTime,3,false, 3);

        callables.add(runCallable1);
        callables.add(runCallable2);
        callables.add(runCallable3);

        try {
            executor.invokeAll(callables);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }
}