package Task4;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class WordCounter {

    public static String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }
    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    public Map<String,Double> FindCommonWords(Folder folder, List<String> keyWordsToFind) {
        return forkJoinPool.invoke(new FolderSearchTask(folder, keyWordsToFind));
    }

    public static Map.Entry<List<String>,Double> checkMatching(Document document, List<String> keyWordsToMatch) {
        Set<String> uniqueWords = new HashSet<>();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                var wordLower = word.toLowerCase();
                uniqueWords.add(wordLower);
            }
        }
        double prevSize = keyWordsToMatch.size();
        List<String> copy = new ArrayList<>();
        copy.addAll(keyWordsToMatch);
        copy.retainAll(uniqueWords);
        double afterSize = copy.size();

        double matchingValue = Math.round(afterSize/prevSize * 100.0) / 100.0;
        var matching = new AbstractMap.SimpleEntry<List<String>,Double>(copy, matchingValue);
        return matching;
    }
}