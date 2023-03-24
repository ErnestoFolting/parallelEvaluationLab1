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
}
