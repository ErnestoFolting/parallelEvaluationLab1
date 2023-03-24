public class Main {
    public static void main(String[] args) {
            //List<List<Double>> matrix = MatrixIO.readMatrixFromFile("matrixMultiply/src/input1.txt");
            //List<List<Double>> matrix2 = MatrixIO.readMatrixFromFile("matrixMultiply/src/input2.txt");
            float[][] matrix = MatrixIO.generateMatrix(2000);
            float[][] matrix2 = MatrixIO.generateMatrix(2000);
            var before = System.currentTimeMillis();
            StrippedAlgoMultiplier stripped = new StrippedAlgoMultiplier();
            Result result = stripped.multiply(matrix,matrix2);
/*            ConcurrentMultiplier multiplier = new ConcurrentMultiplier();
            Result result = multiplier.multiply(matrix,matrix2);*/
            var after = System.currentTimeMillis();
            MatrixIO.printMatr(result.resMatrix);
            System.out.println(after-before);
    }
}