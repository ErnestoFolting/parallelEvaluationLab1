public class Result {
    public float[][] resMatrix;

    public Result(int size){
        resMatrix = new float[size][size];
        for (int i = 0; i < size; i++) {
            resMatrix[i] = new float[size];
            for(int j=0;j<size;j++){
                resMatrix[i][j] = 0;
            }
        }
    }

    public void putBlock(float[][] block, int blockI, int blockJ, int blockSize){
        for(int i = blockSize*blockI;i< blockSize*(blockI+1);i++){
            for(int j = blockSize*blockJ;j< blockSize*(blockJ+1);j++){
                resMatrix[i][j] = block[i%blockSize][j%blockSize];
            }
        }
    }

    public int getSize() {
        return resMatrix.length;
    }
}

