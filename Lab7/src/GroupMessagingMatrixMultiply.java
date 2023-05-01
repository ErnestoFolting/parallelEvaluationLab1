import mpi.MPI;
import mpi.MPIException;

public class GroupMessagingMatrixMultiply {
    private static final int sizeMatrixA = 1500;
    private static final int sizeMatrixB = 1500;

    public static void main(String[] args) throws MPIException {
        float[][] matrixA = new float[sizeMatrixA][sizeMatrixA];
        float[][] matrixB = new float[sizeMatrixB][sizeMatrixB];
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        MPI.Init(args);

        int processId = MPI.COMM_WORLD.Rank();
        int totalProcesses = MPI.COMM_WORLD.Size();

        if(processId == 0){
            matrixA = MatrixHelper.generateMatrix(sizeMatrixA, 1);
            matrixB = MatrixHelper.generateMatrix(sizeMatrixB, 1);
            startTime = System.currentTimeMillis();
        }

        int elementsPerProcess = sizeMatrixA / totalProcesses * sizeMatrixB;
        int elementsToAddToLastProcess = (sizeMatrixA % totalProcesses) * sizeMatrixB;

        int[] countsInBytes = new int[totalProcesses];
        for(int i=0;i<totalProcesses;i++){
            countsInBytes[i] = elementsPerProcess  * 4;
            if(i == totalProcesses - 1){
                countsInBytes[i] = (elementsPerProcess + elementsToAddToLastProcess) * 4;
            }
        }

        int[] displs = new int[totalProcesses];
        displs[0] = 0;
        for(int i=1;i<displs.length;i++){
            displs[i] = countsInBytes[i-1] + displs[i-1];
        }

        byte[] byteMatrA = MatrixHelper.flattenFloatArray(matrixA);
        byte[] byteMatrB = MatrixHelper.flattenFloatArray(matrixB);

        int currentCountInBytes = countsInBytes[processId];
        byte[] partMatrAByte = new byte[currentCountInBytes];

        byte[] resByte = new byte[sizeMatrixA * sizeMatrixA * 4];



        //scatter matrix A parts
        MPI.COMM_WORLD.Scatterv(byteMatrA, 0, countsInBytes,displs, MPI.BYTE, partMatrAByte, 0, currentCountInBytes, MPI.BYTE, 0);

        //broadcast full matrix B
        MPI.COMM_WORLD.Bcast(byteMatrB,0,byteMatrB.length, MPI.BYTE,0);

        float[][] partMatrixAInProcess = MatrixHelper.unflattenFloatArray(partMatrAByte, currentCountInBytes/(4*sizeMatrixB),sizeMatrixA);
        float[][] matrixBInProcess = MatrixHelper.unflattenFloatArray(byteMatrB, sizeMatrixB, sizeMatrixB);

        float[][] resInProcess = MatrixHelper.matrixSimpleMultiply(partMatrixAInProcess, matrixBInProcess);

        byte[] resInProcessByte = MatrixHelper.flattenFloatArray(resInProcess);

        MPI.COMM_WORLD.Gatherv(resInProcessByte,0,resInProcessByte.length,MPI.BYTE, resByte,0, countsInBytes,displs,MPI.BYTE,0);

        if(processId == 0){
            float[][] res = MatrixHelper.unflattenFloatArray(resByte,sizeMatrixA,sizeMatrixA);
            endTime = System.currentTimeMillis();
            System.out.println("time " + (endTime-startTime));
            System.out.println(res.length);
        }

        MPI.Finalize();
    }
}
