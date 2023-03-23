import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            //List<List<Double>> matrix = MatrixIO.readMatrixFromFile("matrixMultiply/src/input1.txt");
            //List<List<Double>> matrix2 = MatrixIO.readMatrixFromFile("matrixMultiply/src/input2.txt");
            List<List<Double>> matrix = MatrixIO.generateMatrix(500);
            List<List<Double>> matrix2 = MatrixIO.generateMatrix(500);
            var before = System.currentTimeMillis();
            StrippedAlgoMultiplier stripped = new StrippedAlgoMultiplier();
            Result result = stripped.multiply(matrix,matrix2);
            /*ConcurrentMultiplier multiplier = new ConcurrentMultiplier();
            Result result = multiplier.multiply(matrix,matrix2);*/
            var after = System.currentTimeMillis();
            MatrixIO.printMatr(result.resMatrix);
            System.out.println(after - before);
    }
}