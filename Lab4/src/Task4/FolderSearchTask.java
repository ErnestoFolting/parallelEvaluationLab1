package Task4;

import java.util.*;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<Map<String,Double>> {
        private final Folder folder;
    private List<String> keyWordsToMatch;

    FolderSearchTask(Folder folder, List<String> keyWordsToMatch) {
        super();
        this.folder = folder;
        this.keyWordsToMatch = keyWordsToMatch;
    }

    @Override
    protected Map<String,Double> compute() {
        Map<String,Double> matchings = new HashMap<>();
        List<RecursiveTask<Map<String,Double>>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder, keyWordsToMatch);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document,keyWordsToMatch);
            forks.add(task);
            task.fork();
        }
        for(int i =0;i<forks.size();i++){
            matchings.putAll(forks.get(i).join());
        }
        return matchings;
    }
}