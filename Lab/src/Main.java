import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final String INPUT_TXT = "input.txt";
	private static final String OUTPUT_TXT = "output.txt";
	
	public static void main(String[] args) throws IOException {
		createFilesIfNotExist();
		List<Career> careers = readFromFile();
		int maxExperince = getMaxExperiance(careers);
		System.out.println(maxExperince);
	}
	
	private static List<Career> readFromFile() throws IOException {
		Path path = Paths.get(INPUT_TXT);
		List<String> lines = Files.readAllLines(path);
		int countOfLevels = -1;
		List<Career> parents = new ArrayList<>();
		for (String line: lines) {
			if (countOfLevels == -1) {
				countOfLevels = Integer.parseInt(line);
			} else {
				String[] experiances = line.split(" ");
				List<Career> careers = new ArrayList<>();
				for (int i = 0; i < experiances.length; i++) {
					String experiance = experiances[i];
					Career career = new Career();
					career.setExperience(Integer.parseInt(experiance));
					if (i-1 >= 0) {
						career.getParent().add(parents.get(i-1));
					}
					if (parents.size() > i) {
						career.getParent().add(parents.get(i));
					}
					careers.add(career);
				}
				parents = careers;
			}
		}
		return parents;
	}
	
	private static int getMaxExperiance(List<Career> careers) {
		int max = -1;
		for (Career career: careers) {
			int experiance = career.getExperience();
			if (!career.getParent().isEmpty()) {
				experiance += getMaxExperiance(career.getParent());
			} else {
				return experiance;
			}
			if (experiance > max) {
				max = experiance;
			}
		}
		return max;
	}
	
	private static void createFilesIfNotExist() throws IOException {
		Path path = Paths.get(INPUT_TXT);
		if (Files.notExists(path)) {
			Files.write(path, new ArrayList<String>(), Charset.forName("UTF-8"));
		}
		
		path = Paths.get(OUTPUT_TXT);
		if (Files.notExists(path)) {
			Files.write(path, new ArrayList<String>(), Charset.forName("UTF-8"));
		}
	}
}
