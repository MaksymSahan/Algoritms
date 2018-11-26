import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    private static final String INPUT_TXT = "input.txt";
    private static final String OUTPUT_TXT = "output.txt";
    private static Map<String, Set<String>> mapping = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        checkFiles();
       
        mapping = getInputMap();
        System.out.println(mapping);
        createFinalList();
    }

    private static Map<String, Set<String>> getInputMap() throws IOException {
        Path path = Paths.get(INPUT_TXT);
        Map<String, Set<String>> result = new HashMap<String, Set<String>>();
        List<String> lines = Files.readAllLines(path);
        for (String line: lines) {
            String word1 = line.trim().substring(0, line.indexOf(" "));
            String word2 = line.trim().substring(line.indexOf(" ")).trim();
            if (!result.containsKey(word1)) {
                result.put(word1, new HashSet<>());
            }
            if (!result.containsKey(word2)) {
                result.put(word2, new HashSet<>());
            }
            result.get(word1).add(word2);
        }
        return result;
    }
    
    private static void checkFiles() throws IOException {
        Path path = Paths.get(INPUT_TXT);
        if (Files.notExists(path)) {
            Files.write(path, new ArrayList<String>(), Charset.forName("UTF-8"));
        }
        
        path = Paths.get(OUTPUT_TXT);
        if (Files.notExists(path)) {
            Files.write(path, new ArrayList<String>(), Charset.forName("UTF-8"));
        }
    }
    
    private static void createFinalList() throws IOException {
        List<String> result = new ArrayList<>();
        boolean inProgress = true;
        while (inProgress) {
            inProgress = false;
            for (String doc: mapping.keySet()) {
                if (!result.contains(doc)) {
                    inProgress = true;
                    List<String> related = getRelated(doc, doc);
                    if (related.isEmpty()) {
                        result.add(doc);
                        break;
                    }
                    boolean allRelatedExist = true;
                    for (String relatedDoc: related) {
                        if (!result.contains(relatedDoc)) {
                            allRelatedExist = false;
                            break;
                        }
                    }
                    if (allRelatedExist) {
                        result.add(doc);
                    }
                }
            }
        }
        System.out.println(result);
        String path = "D:\\eclipse-workspace\\Algoritm3\\output.txt";
        FileWriter writer = new FileWriter(path, true);
        writer.write("Â³äïîâ³äü: " + result + System.getProperty("line.separator"));
        writer.close();
    }
    
    private static List<String> getRelated(String origin, String doc) {
        List<String> result = new ArrayList<>();
        if (!mapping.get(doc).isEmpty()) {
            for (String relatedDoc: mapping.get(doc)) {
                List<String> related = getRelated(doc, relatedDoc);
                result.addAll(related);
              
            }
        }
        if (!origin.equals(doc)) {
            result.add(doc);
        }
        return result;
    }
}
