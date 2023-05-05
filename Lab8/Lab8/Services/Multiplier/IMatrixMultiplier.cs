namespace Lab8.Services.Multiplier
{
    public interface IMatrixMultiplier
    {
        public float[] multiply(int matrixSize, float[,] matrA, float[,] matrB);
    }
}
