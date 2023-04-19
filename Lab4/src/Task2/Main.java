package Task2;


public class Main {

    public static void main(String[] args) {
        int elCount = 2000;
        float[][]matr1 = new float[elCount][elCount];
        float[][]matr2 = new float[elCount][elCount];
        for(int i =0;i<elCount;i++){
            float[] temp = new float[elCount];
            for(int j=0;j<elCount;j++){
                temp[j] = 1;
            }
            matr1[i] = temp;
            matr2[i] = temp;
        }
        var before = System.currentTimeMillis();
        StrippedForkJoinMultiplier multiplier = new StrippedForkJoinMultiplier();
        var res = multiplier.multiply(matr1,matr2);
        var after = System.currentTimeMillis();

        MatrixHelper.printMatr(res.resMatrix);
        System.out.println(after-before);
    }
}