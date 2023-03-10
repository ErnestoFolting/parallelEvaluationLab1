import java.util.List;

public class MultiplyThread extends Thread{
    private List<Integer> _rowElements;
    private MultiplySync _multiplySync;
    private int _rowNumber;
    private int _columnNumber;
    public List<Integer> columnElements;
    public boolean finishedWork = false;


    public MultiplyThread(List<Integer> rowElements, List<Integer> columnElements, int rowNumber, int columnNumber){
        this.columnElements = columnElements;
        this._rowElements = rowElements;
    }
    @Override
    public void run(){
        for(int i=0;i<_multiplySync.totalColumnsNumber;i++){
            _multiplySync.evaluateAndChangeColumn(_rowNumber,columnElements,_rowElements,_columnNumber);
        }
    }
}
