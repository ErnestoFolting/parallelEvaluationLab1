import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StrippedAlgoMultiplier implements IMatrixMultiplier {
    @Override
    public Result multiply(float[][] matr1, float[][] matr2) {
        Result result = new Result(matr1.length);
        var matr2Transpose = MatrixHelper.getTranspose(matr2);
        var datas = new MultiplyDataForTask[matr1.length];

        for(int i =0;i<result.getSize();i++){
            datas[i] = new MultiplyDataForTask(matr1[i],matr2Transpose[i],i,i,result);
        }

        ExecutorService executor = Executors.newFixedThreadPool(8);
        var tasksToExecute = new ArrayList<Callable<Object>>();

        for(int i =0;i<result.getSize();i++){

            for(int j=0;j< result.getSize();j++){
                tasksToExecute.add(Executors.callable(new MultiplyRunnable(datas[j])));
            }
            try {
                executor.invokeAll(tasksToExecute);
                tasksToExecute.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for(var data: datas){
                int newIndex = data._columnNumber == result.getSize()-1 ? 0 : data._columnNumber + 1;
                data.columnElements = matr2Transpose[newIndex];
                data._columnNumber = newIndex;
            }
        }
        executor.shutdown();
        return result;
    }
}
