package Task1;

import java.util.HashMap;
import java.util.concurrent.*;

public class WordCounter {

    public static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public HashMap<Integer, Integer> countOccurrencesInParallel(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

    public static HashMap<Integer, Integer> occurrencesCount(Document document) {
        HashMap<Integer, Integer> lengthCount = new HashMap<>();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                int length = word.length();
                if (lengthCount.containsKey(length)) {
                    int count = lengthCount.get(length);
                    lengthCount.put(length, count + 1);
                } else {
                    lengthCount.put(length, 1);
                }
            }
        }
        return lengthCount;
    }
}