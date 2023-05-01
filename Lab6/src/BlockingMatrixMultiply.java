import mpi.*;

import static java.lang.System.exit;

public class BlockingMatrixMultiply {
    private static final int rowsMatrixA = 2000;
    private static final int columnsMatrixA = 2000;
    private static final int rowsMatrixB = 2000;
    private static final int columnsMatrixB = 2000;
    private static final int FROM_MASTER = 1;
    private static final int FROM_WORKER = 1;

    public static void main(String[] args) throws MPIException {
        float[][] matrixA = MatrixHelper.generateMatrix(rowsMatrixA, 1);
        float[][] matrixB = MatrixHelper.generateMatrix(rowsMatrixB, 1);
        float[][] res = new float[rowsMatrixA][columnsMatrixA];
        double startTime, endTime;

        MPI.Init(args);
        int tasksNum = MPI.COMM_WORLD.Size();
        int taskID = MPI.COMM_WORLD.Rank();

        int workersNum = tasksNum - 1;

        if(tasksNum < 2 || rowsMatrixA < workersNum){
            System.out.println("Need at least two MPI tasks or more rows than workers");
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }

        if(taskID == 0){
            System.out.println("Started with " + tasksNum + " tasks");
            startTime = MPI.Wtime();
            int rowsPerWorker = rowsMatrixA/workersNum;
            int extraRows = rowsMatrixA%workersNum;
            for(int workerIndex = 1; workerIndex<=workersNum; workerIndex++){

                int rowStartIndex = (workerIndex - 1) * rowsPerWorker;
                int rowFinishIndex = rowStartIndex + rowsPerWorker;
                if(workerIndex == workersNum)rowFinishIndex+=extraRows;

                float[][] submatrA = MatrixHelper.getSubmatr(matrixA,rowStartIndex,rowFinishIndex, rowsMatrixB);
                byte[] subMatrABuffer = MatrixHelper.flattenFloatArray(submatrA);
                byte[] matrBBuffer = MatrixHelper.flattenFloatArray(matrixB);
                int submatrAElements = (rowFinishIndex-rowStartIndex + 1) * rowsMatrixA;
                MPI.COMM_WORLD.Send(new int[]{rowStartIndex},0, 1, MPI.INT, workerIndex, FROM_MASTER);
                MPI.COMM_WORLD.Send(new int[]{rowFinishIndex},0, 1, MPI.INT, workerIndex, FROM_MASTER);
                MPI.COMM_WORLD.Send(subMatrABuffer,0, submatrAElements * 4, MPI.BYTE, workerIndex, FROM_MASTER);
                MPI.COMM_WORLD.Send(matrBBuffer,0, rowsMatrixB*columnsMatrixB * 4, MPI.BYTE, workerIndex, FROM_MASTER);
            }

            for(int workerIndex = 1; workerIndex <= workersNum;workerIndex++){
                int[] rowStartIndex = new int[1];
                int[] rowFinishIndex = new int[1];

                MPI.COMM_WORLD.Recv(rowStartIndex,0,1,MPI.INT,workerIndex, FROM_WORKER);
                MPI.COMM_WORLD.Recv(rowFinishIndex,0,1,MPI.INT,workerIndex, FROM_WORKER);

                int resMatrElements = (rowFinishIndex[0]-rowStartIndex[0] + 1) * rowsMatrixA;
                byte[] resMatrBuffer = new byte[resMatrElements*4];

                MPI.COMM_WORLD.Recv(resMatrBuffer,0,resMatrElements * 4, MPI.BYTE,workerIndex, FROM_WORKER);

                float[][] resMatr = MatrixHelper.unflattenFloatArray(resMatrBuffer, rowFinishIndex[0]-rowStartIndex[0],columnsMatrixA);
                MatrixHelper.putPart(resMatr,res,rowStartIndex[0],rowFinishIndex[0]);

            }
            endTime = MPI.Wtime();
            System.out.println("RESULT MATRIX:");
            MatrixHelper.printMatr(res);
            System.out.println(endTime-startTime + " seconds");
        }else{
            int[] rowStartIndex = new int[1];
            int[] rowFinishIndex = new int[1];
            MPI.COMM_WORLD.Recv(rowStartIndex,0,1,MPI.INT,0, FROM_MASTER);
            MPI.COMM_WORLD.Recv(rowFinishIndex,0,1,MPI.INT,0, FROM_MASTER);
            int submatrAElements = (rowFinishIndex[0]-rowStartIndex[0] + 1) * rowsMatrixA;
            byte[] submatrABuffer = new byte[submatrAElements*4];
            byte[] matrBBuffer = new byte[rowsMatrixB * columnsMatrixB * 4];
            MPI.COMM_WORLD.Recv(submatrABuffer,0,submatrAElements * 4, MPI.BYTE,0, FROM_MASTER);
            MPI.COMM_WORLD.Recv(matrBBuffer,0,rowsMatrixB * columnsMatrixB * 4, MPI.BYTE,0, FROM_MASTER);
            System.out.println("Row start: " + rowStartIndex[0] + " Row finish " + rowFinishIndex[0] + " From task " + taskID);


            float[][] matrB = MatrixHelper.unflattenFloatArray(matrBBuffer, rowsMatrixB,columnsMatrixB);
            float[][] subMatrA = MatrixHelper.unflattenFloatArray(submatrABuffer, rowFinishIndex[0]-rowStartIndex[0],columnsMatrixA);
            float[][] matrRes = MatrixHelper.matrixSimpleMultiply(subMatrA,matrB);


            byte[] matrResBuffer = MatrixHelper.flattenFloatArray(matrRes);

            MPI.COMM_WORLD.Send(rowStartIndex,0, 1, MPI.INT, 0, FROM_WORKER);
            MPI.COMM_WORLD.Send(rowFinishIndex,0, 1, MPI.INT, 0, FROM_WORKER);
            MPI.COMM_WORLD.Send(matrResBuffer,0, matrResBuffer.length, MPI.BYTE, 0, FROM_WORKER);
        }



        MPI.Finalize();
    }
}