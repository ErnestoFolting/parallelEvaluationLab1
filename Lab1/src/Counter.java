public class Counter implements ICounter {
    private int _number = 0;
    public synchronized void  increment(){
        _number++;
    }
    public void  decrement(){
        synchronized (this){
            _number--;
        }
    }
    public int getNumber(){
        return _number;
    }
}
