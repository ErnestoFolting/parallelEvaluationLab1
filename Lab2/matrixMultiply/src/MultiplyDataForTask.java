import java.util.List;
public class MultiplyDataForTask{
    private List<Double> _rowElements;
    private Result _resultMatrix;
    public int _rowNumber;
    public int _columnNumber;
    public List<Double> columnElements;

    public MultiplyDataForTask(List<Double> rowElements, List<Double> columnElements, int rowNumber, int columnNumber, Result resultMatrix){
        this.columnElements = columnElements;
        this._rowElements = rowElements;
        this._resultMatrix = resultMatrix;
        this._rowNumber = rowNumber;
        this._columnNumber = columnNumber;
    }
    public void multiply(){
        double sum = 0;
        for(int i =0;i<_resultMatrix.getSize();i++){
            sum += _rowElements.get(i) * columnElements.get(i);
        }
        _resultMatrix.put(_rowNumber,_columnNumber,sum);
    }
}
