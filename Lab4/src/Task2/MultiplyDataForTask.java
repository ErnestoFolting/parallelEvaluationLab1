package Task2;

import java.util.List;
public class MultiplyDataForTask{
    public float[][] _rowElements;
    public float[][] _columnElementsTransposed;
    public Result _resultMatrix;
    public int startRowNumber;
    public int finishRowNumber;
    public int startColumnNumber;
    public int finishColumnNumber;

    public MultiplyDataForTask(float[][] rowElements, int startRowNumber, int finishRowNumber, int startColumnNumber, int finishColumnNumber, Result resultMatrix, float[][] columnElementsTransposed){
        this._rowElements = rowElements;
        this._resultMatrix = resultMatrix;
        this.startRowNumber = startRowNumber;
        this.startColumnNumber = startColumnNumber;
        this.finishRowNumber = finishRowNumber;
        this.finishColumnNumber = finishColumnNumber;
        this._columnElementsTransposed = columnElementsTransposed;
    }
    public void bigPartsMultiply(){
        int counterI = 0;
        for(int i =startRowNumber;i<=finishRowNumber;i++){
            int counterJ = 0;
            for(int j = startColumnNumber;j<=finishColumnNumber;j++){
                if(counterJ > _columnElementsTransposed.length - 1) System.out.println("-------WARN---------------" + "START " + startColumnNumber + " finish " + finishColumnNumber + " row start " + startRowNumber + " row finish " + finishRowNumber);
                 multiply(_rowElements[counterI], _columnElementsTransposed[counterJ],i, j);
                counterJ++;
            }
            counterI++;
        }
    }
    private void multiply(float[]row1, float[] row2, int elementI, int elementJ){
        float sum = 0;
        for(int i=0;i<_resultMatrix.getSize();i++){
            sum += row1[i] * row2[i];
        }
        _resultMatrix.resMatrix[elementI][elementJ] = sum;
    }
}