package JournalTask;

import java.util.*;

public class Journal {
    public static int marks = 0;
    Map<Group, Map<Student, List<Integer>>> groupMap = new HashMap<>();
    public Journal(List<Group> groups){
        for (Group group : groups){
            Map<Student, List<Integer>> marks = new HashMap<>();
            for (Student student: group.Students) {
                marks.put(student, Arrays.asList());
            }
            groupMap.put(group,marks);
        }
    }
    public void putMark(Integer mark, Student student, Group group){
        var groupMarks = groupMap.get(group);
        synchronized (groupMarks.get(student)){
            marks++;
            List<Integer> prevList = groupMarks.get(student);
            List<Integer> copy = new ArrayList<>(prevList);
            copy.add(mark);
            groupMarks.put(student,copy);
        }
    }
}
