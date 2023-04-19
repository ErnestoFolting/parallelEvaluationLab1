package Task1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<HashMap<Integer, Integer>> {
    private final Folder folder;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }

    HashMap<Integer, Integer> mergeMaps(HashMap<Integer,Integer> map1, HashMap<Integer,Integer> map2){
        for (Integer key : map2.keySet()) {
            map1.merge(key, map2.get(key), Integer::sum);
        }
        return map1;
    }

    @Override
    protected HashMap<Integer, Integer> compute() {
        HashMap<Integer, Integer> lengthCount = new HashMap<>();
        List<RecursiveTask<HashMap<Integer, Integer>>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentSearchTask task = new DocumentSearchTask(document);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<HashMap<Integer, Integer>> task : forks) {
            lengthCount =  mergeMaps(lengthCount,task.join());
        }
        return lengthCount;
    }
}