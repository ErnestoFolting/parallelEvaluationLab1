import java.util.List;

public class Result {
    private List<List<Double>> resMatrix;

    public synchronized void put(int i, int j, double val){
        resMatrix.get(i).set(j,val);
    }
}
