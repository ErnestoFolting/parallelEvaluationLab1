public class LengthCheckerThread extends Thread {

    public int lengthCounter = 0;

    public static double tickTime = 50;

    private Queue _queue;

    private long _startTime;
    private long _workTime;

    public LengthCheckerThread(Queue queue, long startTime, long workTime){
        _queue = queue;
        _startTime = startTime;
        _workTime = workTime;
    }

    @Override
    public void run(){
        while(System.currentTimeMillis() - _startTime <= _workTime){
            try {
                sleep((int)tickTime);
                lengthCounter += _queue.currentCount;
                System.out.print("Served: "+ _queue.servedItemsCount + " Rejected: " + _queue.rejectedItemsCount + " Queue length " + _queue.currentCount);
                if(! (_queue.servedItemsCount == 0 && _queue.rejectedItemsCount == 0)){
                    System.out.println(" Chance of reject " + RoundHelper.myRound((double)_queue.rejectedItemsCount / ((double)_queue.rejectedItemsCount + (double)_queue.servedItemsCount)) );
                }else{
                    System.out.println();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showAvgLength(){
        double ticksCount = _workTime/tickTime;
        System.out.println("Average queue length: " + RoundHelper.myRound(lengthCounter / ticksCount));
    }
}
