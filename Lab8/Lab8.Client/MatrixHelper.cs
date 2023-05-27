namespace Lab8.Client
{
    public class MatrixHelper
    {
        public static float[,] generateMatrix(int size)
        {
            float[,] matrix = new float[size, size];
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    matrix[i, j] = 1;
                }
            }
            return matrix;
        }
    }
}
