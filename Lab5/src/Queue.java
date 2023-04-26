import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class Queue {
    final Lock _lock = new ReentrantLock();
    final Condition notEmpty = _lock.newCondition();
    final int[] itemsInQueue;
    int currentCount;
    int totalItemsCount;
    public int tookItemsCount = 0;
    public int servedItemsCount = 0;
    public int rejectedItemsCount = 0;
    public int checkCounter = 0;
    public Queue(int itemsCount){
        itemsInQueue = new int[itemsCount];
        totalItemsCount = itemsCount;
        currentCount = 0;
    }
    public int serve()  {
        _lock.lock();
        int item;
        try{
            while(currentCount == 0){
                notEmpty.await();
            }
            item = itemsInQueue[currentCount-1];
            currentCount -=1;
            tookItemsCount++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            _lock.unlock();
        }
        try {
            sleep(150); //time of serving
            servedItemsCount++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public void put(int item) {
        _lock.lock();
        try{
            if(currentCount == totalItemsCount){
                rejectedItemsCount++;
                return;
            }
            itemsInQueue[currentCount] = item;
            currentCount +=1;
            notEmpty.signalAll();
        }finally {
            _lock.unlock();
        }
    }
}