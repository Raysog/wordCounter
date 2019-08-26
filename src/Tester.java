import java.io.IOException;

/**
 * Created by Ivan on 25.08.2019.
 */
public class Tester {
    public static void main(String[] args) throws IOException {
        Worder w = new Worder();
        w.readFile();
        w.printMap();
        System.out.println();
        w.findMaxWords();
    }
}
