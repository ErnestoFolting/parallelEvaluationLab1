package Task2;

public class MatrixHelper {
    public static float[][] getTranspose(float[][] matr){
        float[][] transposedMatrix = new float[matr.length][matr.length];

        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr.length; j++) {
                transposedMatrix[i][j] = matr[j][i];
            }
        }
        return transposedMatrix;
    }
    public static float[][] getHalfMatrRows(float[][] matrix, boolean isFirstHalf){
        int newRowsCount = matrix.length/2;
        float[][] result = new float[newRowsCount][matrix[0].length];
        if(newRowsCount == 0)System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " + matrix.length);
        for(int i = 0;i<newRowsCount;i++){
            float[]temp = new float[matrix[0].length];
            for(int j=0;j<matrix[0].length;j++){
                temp[j] = isFirstHalf ? matrix[i][j] : matrix[i + newRowsCount][j];
            }
            result[i] = temp;
        }
        return result;
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