import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MatrixHelper {
    public static float[][] generateMatrix(int size, int el){
        float[][] matrix = new float[size][size];
        for (int i = 0; i < size; i++) {
            float[] row = new float[size];
            for (int j = 0; j < size; j++) {
                row[j] = el;
            }
            matrix[i] = row;
        }
        return matrix;
    }
    public static void printMatr(float[][] matrixToPrint){
        for(int i =0;i<matrixToPrint.length;i++){
            for(int j=0;j<matrixToPrint[i].length;j++){
                System.out.print(matrixToPrint[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static float[][] getSubmatr(float[][] matrix, int rowStartIndex, int rowFinishIndex, int columnsNumber){
        float[][] result = new float[rowFinishIndex-rowStartIndex + 1][columnsNumber];
        int resultIndex = 0;
        for(int i=rowStartIndex;i<rowFinishIndex;i++){
            float[] temp = new float[columnsNumber];
            for(int j=0;j<columnsNumber;j++){
                temp[j] = matrix[i][j];
            }
            result[resultIndex] = temp;
            resultIndex++;
        }
        return result;
    }

    public static byte[] flattenFloatArray(float[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        ByteBuffer buffer = ByteBuffer.allocate(rows * cols * 4);
        buffer.order(ByteOrder.nativeOrder());
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buffer.putFloat(array[i][j]);
            }
        }
        return buffer.array();
    }

    public static float[][] unflattenFloatArray(byte[] bytes, int rows, int cols) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.nativeOrder());
        float[][] array = new float[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                float fl = buffer.getFloat();
                array[i][j] = fl;
            }
        }
        return array;
    }

    public static float[][] matrixSimpleMultiply(float[][] matr1,float[][] matr2){
        float[][] result = new float[matr1.length][matr2[0].length];
        for (int i = 0; i < matr1.length; i++) {
            for (int j = 0; j < matr1[0].length; j++) {
                float sum = 0;
                for (int k = 0; k < matr1[0].length; k++) {
                    sum += matr1[i][k] *matr2[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static float[][] putPart(float[][]part, float[][] matr, int indexIStart, int indexIFinish){
        float[][] res = matr;
        int partRowIndex= 0;
        for(int i =indexIStart;i<indexIFinish;i++){
            for(int j=0;j<matr[0].length;j++){
                res[i][j] = part[partRowIndex][j];
            }
            partRowIndex++;
        }
        return res;
    }
}
