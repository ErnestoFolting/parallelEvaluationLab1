import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock implements ICounter {
    private int _number = 0;
    private final Lock lock = new ReentrantLock();
    public void  increment(){
        try{
            lock.lock();
            _number++;
        }finally {
            lock.unlock();
        }
    }
    public void  decrement(){
        try{
            lock.lock();
            _number--;
        }finally {
            lock.unlock();
        }
    }
    public int getNumber(){
        return _number;
    }
}
