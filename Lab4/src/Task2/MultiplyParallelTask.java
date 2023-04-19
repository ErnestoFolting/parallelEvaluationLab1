package Task2;

import java.util.concurrent.RecursiveTask;

public class MultiplyParallelTask extends RecursiveTask<Result> {
    private static final int countOfRowsPerIteration = 1;
    public MultiplyDataForTask data;
    public MultiplyParallelTask(MultiplyDataForTask data){
        this.data = data;
    }
    protected Result compute() {
        int rowCount = data.finishRowNumber - data.startRowNumber + 1;
        if (rowCount == countOfRowsPerIteration ||  rowCount % 2 == 1) {
            data.bigPartsMultiply();
            return data._resultMatrix;
        } else {
            int midRow = data.startRowNumber + (data.finishRowNumber- data.startRowNumber) / 2;
            int midColumn = data.startColumnNumber + (data.finishColumnNumber- data.startColumnNumber) / 2;
            float[][] newRows1 = MatrixHelper.getHalfMatrRows(data._rowElements,true);
            float[][] newRows2 = MatrixHelper.getHalfMatrRows(data._rowElements,false);
            float[][] newColumns1 = MatrixHelper.getHalfMatrRows(data._columnElementsTransposed,true);
            float[][] newColumns2 = MatrixHelper.getHalfMatrRows(data._columnElementsTransposed,false);
            MultiplyDataForTask data1 = new MultiplyDataForTask(newRows1,data.startRowNumber,midRow, data.startColumnNumber,midColumn,data._resultMatrix,newColumns1);
            MultiplyDataForTask data2 = new MultiplyDataForTask(newRows1,data.startRowNumber,midRow,midColumn + 1, data.finishColumnNumber, data._resultMatrix,newColumns2);
            MultiplyDataForTask data3 = new MultiplyDataForTask(newRows2,midRow + 1, data.finishRowNumber,data.startColumnNumber,midColumn, data._resultMatrix,newColumns1);
            MultiplyDataForTask data4 = new MultiplyDataForTask(newRows2,midRow + 1, data.finishRowNumber,midColumn + 1, data.finishColumnNumber, data._resultMatrix,newColumns2);
            MultiplyParallelTask first = new MultiplyParallelTask(data1);
            MultiplyParallelTask second = new MultiplyParallelTask(data2);
            MultiplyParallelTask third = new MultiplyParallelTask(data3);
            MultiplyParallelTask fourth = new MultiplyParallelTask(data4);
            invokeAll(first,second,third,fourth);
            first.join();
            second.join();
            third.join();
            fourth.join();
            return data._resultMatrix;
        }
    }
}
