import java.util.List;
public class MultiplyDataForTask{
    private float[] _rowElements;
    private Result _resultMatrix;
    public int _rowNumber;
    public int _columnNumber;
    public float[] columnElements;

    public MultiplyDataForTask(float[]rowElements, float[] columnElements, int rowNumber, int columnNumber, Result resultMatrix){
        this.columnElements = columnElements;
        this._rowElements = rowElements;
        this._resultMatrix = resultMatrix;
        this._rowNumber = rowNumber;
        this._columnNumber = columnNumber;
    }
    public void multiply(){
        float sum = 0;
        for(int i =0;i<_resultMatrix.getSize();i++){
            sum += _rowElements[i] * columnElements[i];
        }
        _resultMatrix.resMatrix[_rowNumber][_columnNumber] = sum;
    }
}
