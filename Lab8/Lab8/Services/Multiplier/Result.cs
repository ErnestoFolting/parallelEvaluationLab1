using System.Runtime.CompilerServices;

namespace Lab8.Services.Multiplier
{
    public class Result
    {
        public float[,] resMatrix { get; private set; }
        public Result(int size)
        {
            resMatrix = new float[size,size];
            for(int i = 0; i < size; i++)
            {
                for(int j = 0; j < size; j++)
                {
                    resMatrix[i, j] = 0;
                }
            }
        }
        [MethodImpl(MethodImplOptions.Synchronized)]
        public void putElement(int rowIndex, int columnIndex, float value)
        {
            resMatrix[rowIndex, columnIndex] = value;
        }
        public void output()
        {
            MatrixHelper.printMatr(resMatrix);
        }
    }
}
