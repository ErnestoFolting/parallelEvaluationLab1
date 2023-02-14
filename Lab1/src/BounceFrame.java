import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class BounceFrame extends JFrame {

    private BallCanvas canvas;
    public static final int WIDTH = 650;
    public static final int HEIGHT = 450;

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


        buttonStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas);
                canvas.add(b);

                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " + thread.getName());
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
                    System.out.println("Thread name = " + thread.getName());
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
                    Ball b = new Ball(canvas,false);
                    canvas.add(b);
                    BallThread thread = new BallThread(b);
                    thread.setPriority(thread.MIN_PRIORITY);
                    thread.start();
                    System.out.println("Thread name = " + thread.getName());
                }
                Ball b = new Ball(canvas, true);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.setPriority(thread.MAX_PRIORITY);
                thread.start();
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(add100);
        buttonPanel.add(addRedAndBlue);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}