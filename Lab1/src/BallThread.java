import com.sun.jdi.ThreadReference;

public class BallThread extends Thread {
    private Ball b;
    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            while(true){
                if(b.touchPocket(-20,-20,40,40))break;
                b.move();
                /*System.out.println("Thread name = " + Thread.currentThread().getName());*/
                Thread.sleep(5);
            }
        } catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}