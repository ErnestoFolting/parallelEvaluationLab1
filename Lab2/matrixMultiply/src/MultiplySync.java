import java.beans.Introspector;
import java.util.Collections;
import java.util.List;

public class MultiplySync {
    public int totalColumnsNumber;
    public int numberOfFinished = 0;
    private List<Boolean> _finished = Collections.nCopies(totalColumnsNumber, false);
    private Result _result;
    public MultiplySync(int totalColumnsNumber, Result result){
        this.totalColumnsNumber = totalColumnsNumber;
        this._result = result;
    }
    public synchronized void evaluateAndChangeColumn(int rowNumber, List<Integer> columnList, List<Integer> rowList, int columnNumber) {
        while(_finished.get(columnNumber)){
            try{
                wait();
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
        double sum = 0;
        for(int i =0;i<columnList.size();i++){
            sum += columnList.get(i) * rowList.get(i);
        }
        _result.put(rowNumber, columnNumber, sum);
        numberOfFinished += 1;
        if(numberOfFinished == totalColumnsNumber){
            _finished = Collections.nCopies(totalColumnsNumber, false);
            numberOfFinished = 0;
        }
        notifyAll();
    }
}
