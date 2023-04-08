package Task4;

import java.util.*;
import java.util.concurrent.RecursiveTask;

class DocumentSearchTask extends RecursiveTask<Map<String,Double>> {
    private final Document document;
    private final List<String> keyWordsToMatch;

    DocumentSearchTask(Document document, List<String> keyWordsToMatch) {
        super();
        this.document = document;
        this.keyWordsToMatch = keyWordsToMatch;
    }

    @Override
    protected Map<String,Double> compute() {
        Map<String,Double> fileMatching = new HashMap<>();
        var matching = WordCounter.checkMatching(document,keyWordsToMatch);
        String str = document.path;
        if(matching.getValue() != 0){
            str += " Matching words: ";
            for (var el:matching.getKey()) {
                str += el + " ";
            }
        }
        fileMatching.put(str,matching.getValue());
        return fileMatching;
    }
}