import mpi.MPI;
import mpi.MPIException;

public class GroupMessagingMatrixMultiply {
    private static final int sizeMatrixA = 1600;
    private static final int sizeMatrixB = 1600;

    public static void main(String[] args) throws MPIException {
        float[][] matrixA = new float[sizeMatrixA][sizeMatrixA];
        float[][] matrixB = new float[sizeMatrixB][sizeMatrixB];
        byte[] resByte = new byte[sizeMatrixA * sizeMatrixA * 4];

        MPI.Init(args);

        int processId = MPI.COMM_WORLD.Rank();
        int totalProcesses = MPI.COMM_WORLD.Size();


        if(processId == 0){
            matrixA = MatrixHelper.generateMatrix(sizeMatrixA, 1);
            matrixB = MatrixHelper.generateMatrix(sizeMatrixB, 1);
        }

        byte[] byteMatrA = MatrixHelper.flattenFloatArray(matrixA);
        byte[] byteMatrB = MatrixHelper.flattenFloatArray(matrixB);
        byte[] partMatrAByte = new byte[byteMatrA.length/totalProcesses *4];

        //scatter matrix A parts
        MPI.COMM_WORLD.Scatter(byteMatrA, 0, byteMatrA.length/totalProcesses, MPI.BYTE, partMatrAByte, 0, byteMatrA.length/totalProcesses, MPI.BYTE, 0);

        //broadcast full matrix B
        MPI.COMM_WORLD.Bcast(byteMatrB,0,byteMatrB.length, MPI.BYTE,0);

        float[][] partMatrixAInProcess = MatrixHelper.unflattenFloatArray(partMatrAByte,sizeMatrixA/totalProcesses ,sizeMatrixA);
        float[][] matrixBInProcess = MatrixHelper.unflattenFloatArray(byteMatrB,sizeMatrixB,sizeMatrixB);

        float[][] resInProcess = MatrixHelper.matrixSimpleMultiply(partMatrixAInProcess,matrixBInProcess);

        byte[] resInProcessByte = MatrixHelper.flattenFloatArray(resInProcess);

        MPI.COMM_WORLD.Gather(resInProcessByte,0,resInProcessByte.length,MPI.BYTE, resByte,0,resInProcessByte.length,MPI.BYTE,0);

        if(processId == 0){
            float[][] res = MatrixHelper.unflattenFloatArray(resByte,sizeMatrixA,sizeMatrixA);
            System.out.println(res[0][0]);
        }

        MPI.Finalize();
    }
}
