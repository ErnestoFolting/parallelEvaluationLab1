package Task3;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws IOException {
        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File("D:\\Education\\parallelEvaluationTechnologies\\Lab4\\src\\Files"));
        Set<String> commonWords = wordCounter.FindCommonWords(folder);
        System.out.println(commonWords);
        System.out.println(commonWords.size());
    }
}