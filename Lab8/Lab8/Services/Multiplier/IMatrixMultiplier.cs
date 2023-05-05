namespace Lab8.Services.Multiplier
{
    public interface IMatrixMultiplier
    {
        public (float[] firstRow, long executionTime) multiply(int matrixSize, float[,] matrA, float[,] matrB);
    }
}
