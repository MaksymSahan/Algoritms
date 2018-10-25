import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class main {
    public static void main(String args[]) throws FileNotFoundException, IOException {
        String file = "D:\\eclipse-workspace\\Algoritm_2\\input.txt";
        Board board = new Board(file);
        board.getMinSpace();

    }

}
