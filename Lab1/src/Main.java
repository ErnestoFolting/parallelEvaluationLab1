import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " + Thread.currentThread().getName());

        //Console output
        /* ConsoleSync consoleSync = new ConsoleSync();
        ConsoleWriterThread firstChar = new ConsoleWriterThread('-',consoleSync, true);
        ConsoleWriterThread secondChar = new ConsoleWriterThread('|',consoleSync, false);
        firstChar.start();
        secondChar.start();*/

        //Counter sync
        /*Counter counter = new Counter();
        CounterThread counterThread1 = new CounterThread(false,counter);
        CounterThread counterThread2 = new CounterThread(true, counter);
        counterThread1.start();
        counterThread2.start();*/

        //Counter lock
        /*CounterLock counter = new CounterLock();
        CounterThread counterThread1 = new CounterThread(false,counter);
        CounterThread counterThread2 = new CounterThread(true, counter);
        counterThread1.start();
        counterThread2.start();
        try{
            counterThread1.join();
            counterThread2.join();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(counter.getNumber());*/
    }
}