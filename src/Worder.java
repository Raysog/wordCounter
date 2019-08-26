import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Ivan on 25.08.2019.
 */
public class Worder {

    private File file;
    private Map<String, Integer> statistics;

    public Worder() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter absolute or relative file path:");
        String filePath = scan.nextLine();
        File testFile = new File(filePath);
        if (testFile.exists()) {
            System.out.println("File downloaded");
            this.file = testFile;
        } else {
            System.out.println("The specified file does not exist");
            this.file = null;
        }
    }

    public void readFile() throws IOException {
        if(file != null) {
            Scanner scanner = new Scanner(new File(file.getPath()));
            statistics = new TreeMap<>();
            scanner.useDelimiter("[^a-zA-Zа-яА-Я]+");
            while (scanner.hasNext()) {
                String word = scanner.next();
                Integer count = statistics.get(word);
                if (count == null) {
                    count = 0;
                }
                statistics.put(word, ++count);
            }
        }
    }

    public void printMap(){
        if (file != null) {
            if (statistics.isEmpty()) {
                System.out.println("File is empty");
            } else {
                System.out.println("All words: ");
                for (String name : statistics.keySet()) {
                    Integer value = statistics.get(name);
                    System.out.println("key: " + name + " count: " + value + ";");
                }
            }
        }
    }

    private void printMap(Map<String, Integer> printing){
        if(file != null)  {
            if (statistics.isEmpty()){
                System.out.println("File is empty");
            } else {
                for (String name : printing.keySet()) {
                    Integer value = printing.get(name);
                    System.out.println("key: " + name + " count: " + value + ";");
                }
            }
        }
    }

    private void removeAll(Map<String, Integer> removing){
        for (String key : statistics.keySet()) {
            removing.remove(key);
        }
    }

    public Map<String, Integer> findMaxWords(){
        Map<String, Integer> max = null;
        Integer maxCount = -1;
        if(file != null) {
            if (statistics.isEmpty()){
                System.out.println("File is empty");
            } else {
                max = new TreeMap<>();
                for (String key : statistics.keySet()) {
                    if (statistics.get(key) > maxCount) {
                        if (!max.isEmpty()) {
                            removeAll(max);
                        }
                        maxCount = statistics.get(key);
                        max.put(key, statistics.get(key));
                    } else if (statistics.get(key) == maxCount) {
                        maxCount = statistics.get(key);
                        max.put(key, statistics.get(key));
                    }
                }
                System.out.println("Words occurring most times:");
                printMap(max);
            }
        }
        return max;
    }

}
