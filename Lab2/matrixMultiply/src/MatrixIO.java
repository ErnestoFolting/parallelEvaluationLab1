import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixIO {
    public static List<List<Integer>> readMatrixFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] parts = line.split(" ");
        int numRows = Integer.parseInt(parts[0]);
        int numCols = Integer.parseInt(parts[1]);
        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            line = reader.readLine();
            parts = line.split(" ");
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                row.add(Integer.parseInt(parts[j]));
            }
            matrix.add(row);
        }

        reader.close();
        return matrix;
    }
    public static void printMatr(List<List<Integer>> matrixToPrint){
        for (List<Integer> row : matrixToPrint) {
            for (Integer element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
