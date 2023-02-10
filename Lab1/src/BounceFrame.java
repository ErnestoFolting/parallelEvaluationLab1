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

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(add100);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}