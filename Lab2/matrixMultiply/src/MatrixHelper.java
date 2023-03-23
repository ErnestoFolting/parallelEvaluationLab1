import java.util.ArrayList;
import java.util.List;

public class MatrixHelper {
    public static List<Double> getColumn(List<List<Double>> matr, int columnIndex){
        List<Double> column = new ArrayList<>();
        for (int i = 0; i < matr.size(); i++) {
            List<Double> row = matr.get(i);
            Double element = row.get(columnIndex);
            column.add(element);
        }
        return column;
    }
}
