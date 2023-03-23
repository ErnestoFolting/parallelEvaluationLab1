import java.util.concurrent.Callable;

public class MultiplyCallable implements Callable<Object> {

    MultiplyDataForTask data;
    public MultiplyCallable(MultiplyDataForTask data){
        this.data=data;
    }
    @Override
    public Object call() {
        data.multiply();
        return null;
    }
}
