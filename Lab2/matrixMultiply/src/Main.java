import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<List<Integer>> matrix = MatrixIO.readMatrixFromFile("src/input1.txt");
            List<List<Integer>> matrix2 = MatrixIO.readMatrixFromFile("src/input2.txt");
            MatrixIO.printMatr(matrix);
            MatrixIO.printMatr(matrix2);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}