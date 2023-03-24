public class MultiplyRunnable implements Runnable {

    MultiplyDataForTask data;
    public MultiplyRunnable(MultiplyDataForTask data){
        this.data=data;
    }
    @Override
    public void run() {
        data.multiply();
    }
}
