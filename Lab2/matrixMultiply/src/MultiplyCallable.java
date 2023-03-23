import java.util.concurrent.Callable;

public class MultiplyCallable implements Callable<Double> {

    MultiplyDataForTask data;
    public MultiplyCallable(MultiplyDataForTask data){
        this.data=data;
    }
    @Override
    public Double call() {
        return data.multiply();
    }
}
