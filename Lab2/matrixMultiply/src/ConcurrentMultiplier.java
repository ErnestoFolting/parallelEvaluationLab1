public class ConcurrentMultiplier implements IMatrixMultiplier{
    @Override
    public Result multiply(float[][] matr1, float[][] matr2) {
        Result result = new Result(matr1.length);
        float sum = 0;
        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr1.length; j++) {
                for (int k = 0; k < matr1.length; k++) {
                    sum += matr1[i][k] *matr2[k][j];
                }
                result.resMatrix[i][j] = sum;
                sum = 0;
            }
        }
        return result;
    }
}
