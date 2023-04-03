package JournalTask;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Group group1 = new Group(20);
        Group group2 = new Group(30);
        Group group3 = new Group(40);
        List<Group> groupsList = new ArrayList<>();
        groupsList.add(group1);
        groupsList.add(group2);
        groupsList.add(group3);
        Journal journal = new Journal(groupsList);

        AssistantThread assistant1 = new AssistantThread(group1,journal);
        AssistantThread assistant2 = new AssistantThread(group2,journal);
        AssistantThread assistant3 = new AssistantThread(group3,journal);

        TeacherThread teacher1 = new TeacherThread(groupsList,journal);

        assistant1.start();
        assistant2.start();
        assistant3.start();

        teacher1.start();

        try{
            assistant1.join();
            assistant2.join();
            assistant3.join();
            teacher1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(journal.groupMap);
        System.out.println(Journal.marks);
    }
}
