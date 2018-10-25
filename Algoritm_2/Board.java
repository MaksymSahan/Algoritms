import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Board {
    private double listNumber;
    private double height;
    private double width;
    private String input;
    private double minResult;

    private void calculations() throws IOException {
        double columnsNunber;
        double rowsNumber;
        if (height < width) {
            columnsNunber = (Math.sqrt(listNumber * height / width));
            columnsNunber = (Math.round(columnsNunber));
            rowsNumber = listNumber / columnsNunber;
            rowsNumber = Math.ceil(rowsNumber);
        } else {
            rowsNumber = (Math.sqrt(listNumber * width / height));
            rowsNumber = (Math.round(rowsNumber));
            columnsNunber = listNumber / rowsNumber;
            columnsNunber = Math.ceil(columnsNunber);
        }
        minResult= columnsNunber*width;
        if(minResult<rowsNumber*height)
            minResult= rowsNumber*height;
        System.out.println(minResult);
        System.out.println(columnsNunber);
        System.out.println(rowsNumber);
        String path = "D:\\eclipse-workspace\\Algoritm_2\\output.txt";
        FileWriter writer = new FileWriter(path, true);
        writer.write("Â³äïîâ³äü: " + minResult + System.getProperty("line.separator"));
        writer.close();
    }

    private String readFromFile() throws FileNotFoundException, IOException {
        String readedString = new String("");
        try (FileReader fileReader = new FileReader(input)) {
            Scanner scan = new Scanner(fileReader);

            while (scan.hasNextLine()) {
                readedString += scan.nextLine();

            }
        }
        return readedString;

    }

    public void getMinSpace() throws FileNotFoundException, IOException {
        String str = readFromFile();
        listNumber = Integer.parseInt(str.substring(0, str.indexOf(" ")));

        str = str.substring(str.indexOf(" ") + 1);
        width = Integer.parseInt(str.substring(0, str.indexOf(" ")));
        str = str.substring(str.indexOf(" ") + 1);
        height = Integer.parseInt(str);
        calculations();

    }

    public Board(String file) {

        this.input = file;
    }
}