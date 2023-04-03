import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drop {
    final Lock _lock = new ReentrantLock();
    final Condition notFull = _lock.newCondition();
    final Condition notEmpty = _lock.newCondition();
    final int[] items;
    int currentCount;
    int totalItemsCount;
    public Drop(int itemsCount){
        items = new int[itemsCount];
        totalItemsCount = itemsCount;
        currentCount = 0;
    }
    public int take()  {
        _lock.lock();
        try{
            while(currentCount == 0){
                notEmpty.await();
            }
            int item = items[currentCount-1];
            currentCount -=1;
            System.out.println("Take element: " + item + " Current Size: " + currentCount);
            notFull.signalAll();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            _lock.unlock();
        }
    }

    public void put(int item) {
        _lock.lock();
        try{
            while(currentCount == totalItemsCount){
                notFull.await();
            }
            items[currentCount] = item;
            currentCount +=1;
            System.out.println("Put element: " + item + " Current Size: " + currentCount);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            _lock.unlock();
        }
    }
}