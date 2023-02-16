public class BallThreadNext extends BallThread {
    private Thread prevBallThread;
    public BallThreadNext(Ball ball, BallThread prevBallThread) {
        super(ball);
        this.prevBallThread = prevBallThread;
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
