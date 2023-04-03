package JournalTask;

import java.util.Random;

public class AssistantThread extends Thread {
    private Group _groupToWriteMarks;
    private Journal _journal;
    public AssistantThread(Group groupToWriteMarks, Journal journal){
        _groupToWriteMarks = groupToWriteMarks;
        _journal = journal;
    }
    @Override
    public void run(){
        for (Student student : _groupToWriteMarks.Students) {
            Random rand = new Random();
            _journal.putMark(rand.nextInt(101),student,_groupToWriteMarks);
        }
    }
}
