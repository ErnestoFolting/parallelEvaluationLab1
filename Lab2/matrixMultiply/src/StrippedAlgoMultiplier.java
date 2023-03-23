import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class StrippedAlgoMultiplier implements IMatrixMultiplier {

    @Override
    public Result multiply(float[][] matr1, float[][] matr2) {
        Result result = new Result(matr1.length);
        var datas = new MultiplyDataForTask[matr1.length];

        for(int i =0;i<result.getSize();i++){
            datas[i] = new MultiplyDataForTask(matr1[i],MatrixHelper.getColumn(matr2,i),i,i,result);
        }

        var executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(4);
        var tasksToExecute = new ArrayList<Callable<Object>>();

        for(int i =0;i<result.getSize();i++){
            for(int j=0;j< result.getSize();j++){
                tasksToExecute.add(new MultiplyCallable(datas[j]));
            }
            try {
                executor.invokeAll(tasksToExecute);
                /*for(int k =0;k<tasksToExecute.size();k++){
                    var taskData = tasksToExecute.get(k).data;
                    result.put(taskData._rowNumber, taskData._columnNumber,res.get(k).get());
                }*/
                tasksToExecute.clear();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } /*catch (ExecutionException e) {
                throw new RuntimeException(e);
            }*/

            for(var data: datas){
                int newIndex = getNextIndex(data._columnNumber, result.getSize());
                data.columnElements = MatrixHelper.getColumn(matr2, newIndex);
                data._columnNumber = newIndex;
            }
        }
        executor.shutdown();
        return result;
    }
    private static int getNextIndex(int currentIndex, int size){
        if(currentIndex == size - 1) return 0;
        return currentIndex + 1;
    }
}
