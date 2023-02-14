public class BallThreadNext extends BallThread {
    private Thread prevBallThread;
    public BallThreadNext(Ball ball, BallThread prevBall) {
        super(ball);
        prevBallThread = prevBall;
    }
    @Override
    public void run(){
        try{
            prevBallThread.join();
            super.run();
        }catch(InterruptedException e){
            super.interrupt();
        }
    }
}
