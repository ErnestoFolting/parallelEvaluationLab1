import java.util.List;

public class MultiplyDataForTask{
    private List<Integer> _rowElements;
    private Result _resultMatrix;
    private int _rowNumber;
    private int _columnNumber;
    public List<Integer> columnElements;

    public MultiplyDataForTask(List<Integer> rowElements, List<Integer> columnElements, int rowNumber, int columnNumber,Result resultMatrix){
        this.columnElements = columnElements;
        this._rowElements = rowElements;
        this._resultMatrix = resultMatrix;
    }
}
