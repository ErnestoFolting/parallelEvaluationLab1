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
    public static float[][] matrixSimpleMultiply(float[][] matr1,float[][] matr2){
        float[][] result = new float[matr1.length][matr1.length];
        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr1.length; j++) {
                float sum = 0;
                for (int k = 0; k < matr1.length; k++) {
                    sum += matr1[i][k] *matr2[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
    public static float[][] matrixSimpleAdd(float[][] matr1,float[][] matr2){
        float[][] result = new float[matr1.length][matr1.length];
        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr1.length; j++) {
                result[i][j] = matr1[i][j] + matr2[i][j];
            }
        }
        return result;
    }
    public static float[][] getSubmatr(float[][] matrix, int blockSize, int indexI, int indexJ){
        float[][] result = new float[blockSize][blockSize];
        for(int i = blockSize*indexI;i< blockSize*(indexI+1);i++){
            for(int j = blockSize*indexJ;j< blockSize*(indexJ+1);j++){
                result[i%blockSize][j%blockSize] = matrix[i][j];
            }
        }
        return result;
    }
}
