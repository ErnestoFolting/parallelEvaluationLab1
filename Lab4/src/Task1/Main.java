package Task1;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public static double myRound(double val){
        return Math.round(val * 100.0) / 100.0 ;
    }
    public static void main(String[] args) throws IOException {
        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File("D:\\Education\\parallelEvaluationTechnologies\\Lab4\\src\\Files"));
        Map<Integer,Integer> map = wordCounter.countOccurrencesInParallel(folder);

        System.out.println(map);

        double sum =0;
        double wordsCount = 0;
        for (Map.Entry<Integer,Integer> el:map.entrySet()) {
            sum += (el.getKey() * el.getValue());
            wordsCount +=el.getValue();
        }
        double mean = myRound(sum/wordsCount);

        double sum2 = 0;
        for (Map.Entry<Integer,Integer> el:map.entrySet()) {
            sum2 += (Math.pow(el.getKey(),2) * el.getValue());
        }
        double D = myRound(sum2/wordsCount - Math.pow(mean,2));
        double standardDeviation = myRound(Math.sqrt(D)) ;

        System.out.println("mean " + mean);
        System.out.println("D " + D);
        System.out.println("Standard deviation " + standardDeviation);
    }
}