import java.io.Console;

public class ConsoleSync {
    private boolean _isStop = false;
    private int _totalCharsNumber = 0;
    private boolean _permission = true;

    public synchronized boolean getPermisision(){
        return _permission;
    }

    public synchronized boolean getIsStop(){
        return _isStop;
    }
    public synchronized void writeAndChangeWriter(boolean control,char charToWrite){
        while(getPermisision() != control){
            try{
                wait();
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.print(charToWrite);
        _permission = !_permission;
        _totalCharsNumber++;
        if(_totalCharsNumber %100 ==0){
            System.out.println();
        }
        notifyAll();
    }
}
