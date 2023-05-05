namespace Lab8.Services.Multiplier
{
    public class ParallelMatrixMultiplier : IMatrixMultiplier
    {
        public float[] multiply(int matrixSize, float[,] matrA, float[,] matrB)
        {

            long startTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();

            long checkTime = 0;

            Result result = new Result(matrixSize);

            //help matrixes
            float[,] matrBTranspose = MatrixHelper.getTranspose(matrB);

            float[][] matrBTransposeJagged = new float[matrBTranspose.GetLength(0)][];
            for (int i = 0; i < matrBTranspose.GetLength(0); i++)
            {
                matrBTransposeJagged[i] = new float[matrBTranspose.GetLength(1)];
                for (int j = 0; j < matrBTranspose.GetLength(1); j++)
                {
                    matrBTransposeJagged[i][j] = matrBTranspose[i, j];
                }
            }

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

                long localStartTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();

                foreach (MultiplyDataForTask data in datas)
                {
                    int newIndex = data._columnNumber == matrixSize - 1 ? 0 : data._columnNumber + 1;
                    data.columnElements = matrBTransposeJagged[newIndex];
                    data._columnNumber = newIndex;
                }

                long localEndTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();
                checkTime += (localEndTime - localStartTime);
            }
            long endTime = DateTimeOffset.Now.ToUnixTimeMilliseconds();
            Console.WriteLine("Time: " + (endTime - startTime));
            Console.WriteLine("Check Time: " + checkTime);
            /*result.output();*/
            return (MatrixHelper.getRow(result.resMatrix, 0));
        }
    }
}
