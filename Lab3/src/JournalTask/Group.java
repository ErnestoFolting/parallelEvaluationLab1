package JournalTask;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public List<Student> Students = new ArrayList<>();
    public Group(int countStudent){
        for(int i =0;i<countStudent;i++){
            Students.add(new Student());
        }
    }
}
