import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    	boolean correctPathFlag = false;
    	Scanner scan = new Scanner(System.in);
    	while (!correctPathFlag) {
	        System.out.println("Enter absolute or relative file path:");
	        String filePath = scan.nextLine();
	        File testFile = new File(filePath);
	        if (testFile.exists()) {
	            System.out.println("File downloaded");
	            this.file = testFile;
	            correctPathFlag = true;
	            scan.close();
	        } else {
	            System.out.println("The specified file does not exist");
	            this.file = null;
	        }
    	}
        
    }

    public void readFile() throws IOException {
            Scanner scanner = new Scanner(new File(file.getPath()));
            statistics = new TreeMap<>();
            scanner.useDelimiter("[^a-zA-Zà-ÿÀ-ß]+");
            while (scanner.hasNext()) {
                String word = scanner.next();
                Integer count = statistics.get(word);
                if (count == null) {
                    count = 0;
                }
                statistics.put(word, ++count);
            }
            scanner.close();
    }

    public void printMap(){
    	if (statistics.isEmpty()){
            System.out.println("File is empty");
        } else {
            for (String name : statistics.keySet()) {
                Integer value = statistics.get(name);
                System.out.println("key: " + name + " count: " + value + ";");
            }
        }
    }
 

    public void findMaxWords(){
    	ArrayList<String> words = null;
        
            if (statistics.isEmpty()){
                System.out.println("File is empty");
            } else {
            	Integer maxCount = -1;
            	words = new ArrayList<>();
                for (String key : statistics.keySet()) {
                    if (statistics.get(key) > maxCount) {
                        if (!words.isEmpty()) {
                        	words.removeAll(words);
                        }
                        maxCount = statistics.get(key);
                        words.add(key);
                    } else if (statistics.get(key) == maxCount) {
                        maxCount = statistics.get(key);
                        words.add(key);
                    }
                }
                System.out.println("Words occurring most times:");
                for (String s : words) {
					System.out.println("key: " + s +  " count: " + maxCount + ";");
				}
            }
    }

}
