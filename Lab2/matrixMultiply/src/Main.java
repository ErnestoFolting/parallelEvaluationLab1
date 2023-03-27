public class Main {
    public static void main(String[] args) {
       float[][] matrix = MatrixIO.generateMatrix(2000);
        float[][] matrix2 = MatrixIO.generateMatrix(2000);

/*        var before = System.currentTimeMillis();
        ConcurrentMultiplier concurrentAlgo = new ConcurrentMultiplier();
        Result result = concurrentAlgo.multiply(matrix,matrix2);
        var after = System.currentTimeMillis();
        System.out.println(after-before);*/

/*        var before = System.currentTimeMillis();
        StrippedAlgoMultiplier stripped = new StrippedAlgoMultiplier();
        Result result = stripped.multiply(matrix,matrix2);
        var after = System.currentTimeMillis();
        System.out.println(after-before);*/

      var before = System.currentTimeMillis();
        FoxAlgoMultiplier foxAlgo = new FoxAlgoMultiplier();
        Result result = foxAlgo.multiply(matrix,matrix2);
        var after = System.currentTimeMillis();
        System.out.println(after-before);
    }
}