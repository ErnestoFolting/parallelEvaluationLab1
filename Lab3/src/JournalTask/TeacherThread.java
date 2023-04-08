package JournalTask;

import java.util.List;
import java.util.Random;

public class TeacherThread extends Thread {
    private Journal _journal;
    private List<Group> _groupsToPutMarks;
    public TeacherThread(List<Group> groupsToPutMarks,Journal journal){
        _journal = journal;
        _groupsToPutMarks = groupsToPutMarks;
    }
    @Override
    public void run(){
        for(int i=0;i<100;i++){
            for (Group group: _groupsToPutMarks) {
                for (Student student : group.Students) {
                    Random rand = new Random();
                    _journal.putMark(rand.nextInt(101),student,group);
                }
            }
        }
    }
}
