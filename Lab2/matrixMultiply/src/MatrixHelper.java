import java.util.ArrayList;
import java.util.List;

public class MatrixHelper {
    public static float[] getColumn(float[][] matr, int columnIndex){
        float[] column = new float[matr.length];
        for (int i = 0; i < matr.length; i++) {
            float[]row = matr[i];
            float element = row[columnIndex];
            column[i] = element;
        }
        return column;
    }
}
