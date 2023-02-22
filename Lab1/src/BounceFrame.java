import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {

    private BallCanvas canvas;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");

        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton add100 = new JButton("Add 100");
        JButton buttonStop = new JButton("Stop");
        JButton addRedAndBlue = new JButton("Add 1 red and 1000 blue");
        JButton joinDemo = new JButton("Join Demo");
        JButton consoleDemo = new JButton("Console Demo");
        JButton counterSyncDemo = new JButton("Counter Sync Demo");
        JButton counterLockDemo = new JButton("Counter Lock Demo");


        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
            }
        });
        add100.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<100;i++){
                    Ball b = new Ball(canvas);
                    canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.start();
                }
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        addRedAndBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<1000;i++){
                    Ball b = new Ball(canvas,Color.BLUE, false);
                    canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.setPriority(thread.MIN_PRIORITY);
                    thread.start();
                }
                Ball b = new Ball(canvas, Color.RED, false);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.setPriority(thread.MAX_PRIORITY);
                thread.start();
            }
        });

        joinDemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas,Color.BLACK, true);
                canvas.add(b);
                BallThread prevBallThread = new BallThread(b);
                prevBallThread.start();
                for(int i=0;i<9;i++){
                    Ball currentBall = new Ball(canvas,true);
                    canvas.add(currentBall);
                    BallThreadNext thread = new BallThreadNext(currentBall,prevBallThread);
                    thread.start();
                    prevBallThread = thread;
                }
            }
        });

        consoleDemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsoleSync consoleSync = new ConsoleSync();
                ConsoleWriterThread firstChar = new ConsoleWriterThread('-',consoleSync, true);
                ConsoleWriterThread secondChar = new ConsoleWriterThread('|',consoleSync, false);
                firstChar.start();
                secondChar.start();
            }
        });

        counterSyncDemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Counter counter = new Counter();
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
                System.out.println("After increments and decrements (sync Counter) " + counter.getNumber());
            }
        });
        counterLockDemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CounterLock counter = new CounterLock();
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
                System.out.println("After increments and decrements (lock Counter) " + counter.getNumber());
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(add100);
        buttonPanel.add(addRedAndBlue);
        buttonPanel.add(joinDemo);
        buttonPanel.add(consoleDemo);
        buttonPanel.add(counterSyncDemo);
        buttonPanel.add(counterLockDemo);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}