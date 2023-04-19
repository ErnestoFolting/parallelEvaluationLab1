package Task3;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class WordCounter {

    public static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Set<String> FindCommonWords(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

    public static Set<String> getUniqueWords(Document document) {
        Set<String> uniqueWords = new HashSet<>();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                var wordLower = word.toLowerCase();
                uniqueWords.add(wordLower);
            }
        }
        return uniqueWords;
    }
}