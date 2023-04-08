package Task2;

import java.util.concurrent.ForkJoinPool;

public class StrippedForkJoinMultiplier {
    public Result multiply(float[][] matr1, float[][] matr2) {
        Result result = new Result(matr1.length);
        var matr2Transpose = MatrixHelper.getTranspose(matr2);

        MultiplyDataForTask data = new MultiplyDataForTask(matr1,0,matr1.length-1,0,matr1.length-1,result, matr2Transpose);

        ForkJoinPool pool = new ForkJoinPool();
        MultiplyParallelTask task = new MultiplyParallelTask(data);
        Result getResult = pool.invoke(task);
        return getResult;
    }
}
