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
    public static float[][] generateMatrix(int size){
        float[][] matrix = new float[size][size];
        for (int i = 0; i < size; i++) {
            float[] row = new float[size];
            for (int j = 0; j < size; j++) {
                row[j] = 1;
            }
            matrix[i] = row;
        }
        return matrix;
    }
    public static void printMatr(float[][] matrixToPrint){
        for(int i =0;i<matrixToPrint.length;i++){
            for(int j=0;j<matrixToPrint[i].length;j++){
                System.out.print(matrixToPrint[i][j] + " ");
            }
            System.out.println();
        }
    }
}
