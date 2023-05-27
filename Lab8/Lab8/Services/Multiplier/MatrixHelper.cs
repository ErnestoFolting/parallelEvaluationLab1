namespace Lab8.Services.Multiplier
{
    public static class MatrixHelper
    {
        public static float[,] getTranspose(float[,] matr)
        {
            float[,] transposedMatrix = new float[matr.GetLength(0), matr.GetLength(0)];


            for (int i = 0; i < matr.GetLength(0); i++)
            {
                for (int j = 0; j < matr.GetLength(0); j++)
                {
                    transposedMatrix[i,j] = matr[j,i];
                }
            }
            return transposedMatrix;
        }

        public static float[,] matrixSimpleMultiply(float[,] matr1, float[,] matr2)
        {
            float[,] result = new float[matr1.GetLength(0), matr1.GetLength(0)];
            for (int i = 0; i < matr1.GetLength(0); i++)
            {
                for (int j = 0; j < matr1.GetLength(0); j++)
                {
                    float sum = 0;
                    for (int k = 0; k < matr1.GetLength(0); k++)
                    {
                        sum += matr1[i,k] * matr2[k,j];
                    }
                    result[i, j] = sum;
                }
            }
            return result;
        }

        public static float[,] matrixSimpleAdd(float[,] matr1, float[,] matr2)
        {
            float[,] result = new float[matr1.GetLength(0), matr1.GetLength(0)];
            for (int i = 0; i < matr1.GetLength(0); i++)
            {
                for (int j = 0; j < matr1.GetLength(0); j++)
                {
                    result[i,j] = matr1[i,j] + matr2[i,j];
                }
            }
            return result;
        }

        public static float[,] generateMatrix(int size)
        {
            float[,] matrix = new float[size,size];
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    matrix[i,j] = 1;
                }
            }
            return matrix;
        }

        public static void printMatr(float[,] matrixToPrint)
        {
            for (int i = 0; i < matrixToPrint.GetLength(0); i++)
            {
                for (int j = 0; j < matrixToPrint.GetLength(0); j++)
                {
                    Console.Write(matrixToPrint[i,j] + " ");
                }
                Console.WriteLine();
            }
        }
        public static float[] getRow(float[,] matr, int rowIndex)
        {
            float[] row = new float[matr.GetLength(1)];
            Buffer.BlockCopy(matr, 0, row, 0, matr.GetLength(1) * sizeof(float));
            return row;
        }
        public static float[,] readMatrixFromString(string matrixString, int size)
        {
            float[,] result = new float[size, size];

            string[] matrixValues = matrixString.Split(',');

            for (int i = 0; i < matrixValues.Length; i++)
            {
                float value = float.Parse(matrixValues[i]);

                int row = i / size;
                int column = i % size;


                result[row, column] = value;
            }

            return result;
        }
    }
}
