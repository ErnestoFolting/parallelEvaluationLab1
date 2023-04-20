import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ModelRunCallable implements Callable<Object> {
    private Queue _queue;
    private long _startTime;
    private long _workTime;
    private int _countConsumers;
    private boolean _liveInfo;
    private int _index;
    public ModelRunCallable(Queue queue, long startTime, long workTime, int countConsumers, boolean liveInfo, int index){
        _queue = queue;
        _startTime = startTime;
        _workTime = workTime;
        _countConsumers = countConsumers;
        _liveInfo = liveInfo;
        _index = index;
    }
    @Override
    public Object call() throws Exception {
        var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(16);
        List<Callable<Object>> callables = new ArrayList<>();
        var prodCallable = (new ProducerCallable(40, _queue, _startTime, _workTime));
        callables.add(prodCallable);

        for(int i =0; i<_countConsumers;i++) {
            callables.add(new ConsumerCallable(_queue, _startTime, _workTime));
        }

        LengthCheckerThread lengthCounter = new LengthCheckerThread(_queue,_startTime,_workTime);

        try{
            if(_liveInfo)lengthCounter.start();
            executor.invokeAll(callables);
            if(_liveInfo)lengthCounter.join();
            double servedItems = _queue.servedItemsCount;
            double rejectedItems = _queue.rejectedItemsCount;
            double chanceOfReject = rejectedItems / (servedItems + rejectedItems);
            System.out.println("RUN " + _index + ": Served " +  servedItems + " Rejected " + rejectedItems + " Chance of reject: " + RoundHelper.myRound(chanceOfReject) );
            if(_liveInfo)lengthCounter.showAvgLength();
        }catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
        executor.shutdown();

        return null;
    }
}
