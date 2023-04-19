package Task4;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        List<String> keyWords = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String line;
        while (true) {
            System.out.print("Enter a keyword (or 'stop' to quit): ");
            line = scanner.nextLine();
            if (line.equals("stop") && keyWords.size() >0) {
                break;
            }
            keyWords.add(line);
        }
        scanner.close();
        System.out.println(keyWords);

        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File("D:\\Education\\parallelEvaluationTechnologies\\Lab4\\src\\Files"));

        Map<String,Double> commonWords = wordCounter.FindCommonWords(folder,keyWords);

        for (Map.Entry<String, Double> entry : commonWords.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println(commonWords.size());
    }
}