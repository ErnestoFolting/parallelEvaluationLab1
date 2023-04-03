import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class FoxAlgoMultiplier implements IMatrixMultiplier{
    private static int _blockSize = 250;
    @Override
    public Result multiply(float[][] matr1, float[][] matr2) {
        Result result = new Result(matr1.length);
        int verticallyBlockCount = matr1.length / _blockSize;
        int threadCount = verticallyBlockCount * verticallyBlockCount;
        var executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threadCount);
        var tasksToExecute = new ArrayList<Callable<Object>>();
        var datas = new ArrayList<BlocksThreadData>();
        for(int i=0;i<verticallyBlockCount;i++){
            for(int j=0;j<verticallyBlockCount;j++){
                float[][] block1 = MatrixHelper.getSubmatr(matr1,_blockSize,i,i);
                float[][] block2 = MatrixHelper.getSubmatr(matr2,_blockSize,i,j);
                BlocksThreadData newBlockData = new BlocksThreadData(block1,block2,i,j,i,i);
                datas.add(newBlockData);
            }
        }
        for(int i=0;i<verticallyBlockCount;i++){
            for(int j=0;j<threadCount;j++){
                tasksToExecute.add(new FoxCallable(datas.get(j)));
            }
            try{
                executor.invokeAll(tasksToExecute);
                tasksToExecute.clear();
            }catch (InterruptedException ex){

            }
            for(var data:datas){
                int firstMatrixCurrentJ = data.firstMatrixCurrentJ;
                int secondMatrixCurrentI = data.secondMatrixCurrentI;
                int newFirstMatrixJ = (firstMatrixCurrentJ + 1) % verticallyBlockCount;
                int newSecondMatrixI = (secondMatrixCurrentI + 1) % verticallyBlockCount;
                float[][] newBlock1 = MatrixHelper.getSubmatr(matr1,_blockSize,data.resultIndexI,newFirstMatrixJ);
                float[][] newBlock2 = MatrixHelper.getSubmatr(matr2,_blockSize,newSecondMatrixI,data.resultIndexJ);
                data.block1 = newBlock1;
                data.block2 = newBlock2;
                data.firstMatrixCurrentJ = newFirstMatrixJ;
                data.secondMatrixCurrentI = newSecondMatrixI;
                float[][] prevResBlock = MatrixHelper.getSubmatr(result.resMatrix,_blockSize,data.resultIndexI, data.resultIndexJ);
                float[][] addedCurrentRes = MatrixHelper.matrixSimpleAdd(prevResBlock,data.blockCurrentResult);
                result.putBlock(addedCurrentRes,data.resultIndexI,data.resultIndexJ,_blockSize);
            }
        }
        executor.shutdown();

        return result;
    }
}
class FoxCallable implements Callable<Object> {

    private BlocksThreadData _data;

    public FoxCallable(BlocksThreadData data){
        _data = data;
    }
    @Override
    public Object call() throws Exception {
        _data.multiply();
        return null;
    }
}


class BlocksThreadData{
    public float[][] block1;
    public float[][] block2;
    public int resultIndexI;
    public int resultIndexJ;
    public int firstMatrixCurrentJ;
    public int secondMatrixCurrentI;
    public float[][] blockCurrentResult;
    public BlocksThreadData(float[][]block1,float[][]block2, int i, int j, int currentI, int currentJ){
        this.block1 = block1;
        this.block2 = block2;
        resultIndexI = i;
        resultIndexJ = j;
        this.firstMatrixCurrentJ = currentI;
        this.secondMatrixCurrentI = currentJ;
        this.blockCurrentResult = new float[block1.length][block1.length];
    }
    public void multiply(){
        blockCurrentResult = MatrixHelper.matrixSimpleMultiply(block1,block2);
    }
}
