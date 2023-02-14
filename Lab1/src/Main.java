import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BounceFrame frame = new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        System.out.println("Thread name = " + Thread.currentThread().getName());
        ConsoleSync consoleSync = new ConsoleSync();
        ConsoleWriterThread firstChar = new ConsoleWriterThread('-',consoleSync, true);
        ConsoleWriterThread secondChar = new ConsoleWriterThread('|',consoleSync, false);
        firstChar.start();
        secondChar.start();
    }
}