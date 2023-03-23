import java.util.ArrayList;
import java.util.List;

public class Result {
    public float[][] resMatrix;

    public Result(int size){
        resMatrix = new float[size][size];
        for (int i = 0; i < size; i++) {
            resMatrix[i] = new float[size];
            for(int j=0;j<size;j++){
                resMatrix[i][j] = 0;
            }
        }
    }


    public int getSize() {
        return resMatrix.length;
    }
}
