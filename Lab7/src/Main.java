import mpi.*;
public class Main {
    public static void main(String[] args) {
        MPI.Init(args);
        int tasksNum = MPI.COMM_WORLD.Size();
        int taskID = MPI.COMM_WORLD.Rank();
        System.out.println(tasksNum + " " + taskID);
    }
}