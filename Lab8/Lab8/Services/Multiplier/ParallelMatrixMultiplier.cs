namespace Lab8.Services.Multiplier
{
    public class ParallelMatrixMultiplier : IMatrixMultiplier
    {
        public (float[] firstRow, long executionTime) multiply(int matrixSize, float[,] matrA, float[,] matrB)
        {
            long startTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();

            Result result = new Result(matrixSize);

            float[,] matrBTranspose = MatrixHelper.getTranspose(matrB);

            MultiplyDataForTask[] datas = new MultiplyDataForTask[matrixSize];
            
            //initialize start data
            for(int i =0;i<matrixSize;i++)
            {
                datas[i] = new MultiplyDataForTask(MatrixHelper.getRow(matrA, i), MatrixHelper.getRow(matrBTranspose, i), i, i, result);
            }

            for(int i = 0; i < matrixSize; i++)
            {
                Task[] tasks = new Task[matrixSize];
                for(int taskId = 0; taskId<matrixSize;taskId++) {
                    int localTaskId = taskId;
                    tasks[taskId] = new Task(() => datas[localTaskId].multiply());
                    tasks[taskId].Start();
                }

                Task.WaitAll(tasks);

                foreach(MultiplyDataForTask data in datas)
                {
                    int newIndex = data._columnNumber == matrixSize - 1 ? 0 : data._columnNumber + 1;
                    data.columnElements = MatrixHelper.getRow(matrBTranspose,newIndex);
                    data._columnNumber = newIndex;
                }
            }
            
            long endTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();
            result.output();
            return (MatrixHelper.getRow(result.resMatrix, 0), endTime-startTime);
        }
    }
}
