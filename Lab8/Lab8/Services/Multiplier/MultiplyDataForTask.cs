namespace Lab8.Services.Multiplier
{
    public class MultiplyDataForTask
    {
        private float[] _rowElements;
        public float[] columnElements;

        private int _rowNumber;
        public int _columnNumber;

        private Result _resultMatrix;


        public MultiplyDataForTask(float[] rowElements, float[] columnElements, int rowNumber, int columnNumber, Result resultMatrix)
        {
            this.columnElements = columnElements;
            this._rowElements = rowElements;
            this._resultMatrix = resultMatrix;
            this._rowNumber = rowNumber;
            this._columnNumber = columnNumber;
        }
        public void multiply()
        {
            float sum = 0;
            for (int i = 0; i < _rowElements.Length; i++)
            {
                sum += _rowElements[i] * columnElements[i];
            }
            _resultMatrix.putElement(_rowNumber, _columnNumber, sum);
        }
    }
}
