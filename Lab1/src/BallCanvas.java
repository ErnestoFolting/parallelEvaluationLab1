import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private JLabel counterLabel = new JLabel("Counter: " + this.counter);
    public int counter = 0;
    public void add(Ball b){
        this.balls.add(b);
    }
    public BallCanvas(){
        this.add(this.counterLabel);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(int i=0; i<balls.size();i++){
            Ball b = balls.get(i);
            if(b.touchPocket(-20,-20,40,40)){
                counter++;
                this.balls.remove(b);
                this.counterLabel.setText("Counter: " + this.counter);
            }else {
                b.draw(g2);
            }
        }
        g2.setColor(Color.black);
        g2.fill(new Ellipse2D.Double(-20,-20,40,40));
    }
}