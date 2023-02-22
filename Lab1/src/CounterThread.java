public class CounterThread extends Thread {
    private boolean _isIncrement = true;
    private ICounter _counter;
    public CounterThread(boolean _isIncrement, ICounter counter){
        this._isIncrement = _isIncrement;
        _counter = counter;
    }
    @Override
    public void run(){
        if (_isIncrement){
            for(int i=0;i<100000;i++){
                _counter.increment();
                /*System.out.println(_counter.getNumber());*/
            }
        }else{
            for(int i=0;i<100000;i++){
                _counter.decrement();
                /*System.out.println(_counter.getNumber());*/
            }
        }
    }
}
