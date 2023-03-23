import java.util.ArrayList;
import java.util.List;

public class Result {
    public List<List<Double>> resMatrix;

    public Result(int size){
        resMatrix = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            List<Double> row = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                row.add(0.0);
            }
            resMatrix.add(row);
        }
    }

    public   void put(int i, int j, double val){
        resMatrix.get(i).set(j,val);
    }

    public int getSize() {
        return resMatrix.size();
    }
}
