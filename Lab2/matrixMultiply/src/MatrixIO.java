import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixIO {
    public static List<List<Double>> readMatrixFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] parts = line.split(" ");
        int numRows = Integer.parseInt(parts[0]);
        int numCols = Integer.parseInt(parts[1]);
        List<List<Double>> matrix = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            line = reader.readLine();
            parts = line.split(" ");
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                row.add(Double.parseDouble(parts[j]));
            }
            matrix.add(row);
        }

        reader.close();
        return matrix;
    }
    public static List<List<Double>> generateMatrix(int size){
        List<List<Double>> matrix = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<Double> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(1.0);
            }
            matrix.add(row);
        }
        return matrix;
    }
    public static void printMatr(List<List<Double>> matrixToPrint){
        for (List<Double> row : matrixToPrint) {
            for (Double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
