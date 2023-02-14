public class ConsoleWriterThread extends Thread{

    private char _charToWrite;
    private ConsoleSync _consoleSync;
    private boolean _control;

    public ConsoleWriterThread(char charToWrite,ConsoleSync consoleSync, boolean control){
        this._charToWrite = charToWrite;
        this._consoleSync = consoleSync;
        this._control = control;
    }
    @Override
    public void run(){
        for(int i =0;i<50000;i++){
            _consoleSync.writeAndChangeWriter(_control,_charToWrite);
        }
    }
}
