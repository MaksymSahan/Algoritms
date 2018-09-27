import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    static int e = 0;
    static int s = 0;

    public static void main(String[] args) throws IOException {
        List<Shoes> forSelectioSort = new ArrayList<Shoes>();
        List<Shoes> forQuickSort = new ArrayList<Shoes>();
        String path = "D:\\eclipse-workspace\\Algoritm_1\\Shoes.txt";
        Scanner in = new Scanner(new File(path));
        while (in.hasNext()) {
            String param = in.nextLine();
            String[] column = param.split(",");

            forSelectioSort.add(new Shoes(column[0], Double.parseDouble(column[1]), Double.parseDouble(column[2]), column[3]));
            forQuickSort.add(new Shoes(column[0], Double.parseDouble(column[1]), Double.parseDouble(column[2]), column[3]));
        }

        selectionSort(forSelectioSort);

        for (Shoes shoe : forSelectioSort) {
            System.out.println(
                    shoe.getManufacturer() + " " + shoe.getPrice() + " " + shoe.getSize() + " " + shoe.getColor());
        }

        quickSort(forQuickSort);

        for (Shoes shoe : forQuickSort) {
            System.out.println(
                    shoe.getManufacturer() + " " + shoe.getPrice() + " " + shoe.getSize() + " " + shoe.getColor());
        }

    }

    public static List<Shoes> selectionSort(List<Shoes> forSelectioSort) throws IOException {
        int start = (int) System.nanoTime();
        int equalCount = 0;
        int swapCount = 0;
        for (int i = 0; i < forSelectioSort.size() - 1; i++) {
            int k = i;
            for (int j = i + 1; j < forSelectioSort.size(); j++) {
                equalCount++;
                if (forSelectioSort.get(j).getPrice() > forSelectioSort.get(k).getPrice()) {
                    k = j;
                }
            }
            swapCount++;
            Shoes shoe = forSelectioSort.get(i);
            forSelectioSort.set(i, forSelectioSort.get(k));
            forSelectioSort.set(k, shoe);
        }
        int end = (int) System.nanoTime();

        System.out.println("Selection Sort Method:");
        System.out.println("Процес сортування Selection зайняв " + (end - start) + " наносекунд");
        System.out.println("Кількість операцій порівняння = " + equalCount);
        System.out.println("Кількість операцій обміну = " + swapCount);
        String path = "D:\\eclipse-workspace\\Algoritm_1\\SortedShoes.txt";
        FileWriter writer = new FileWriter(path, true);
        int time = end - start;
        writer.write("Selection Sort Method:" + System.getProperty("line.separator") );
        writer.write("Процес сортування Selection зайняв " + time + " наносекунд" + System.getProperty("line.separator"));
        writer.write("Кількість операцій порівняння = " + equalCount + System.getProperty("line.separator"));
        writer.write("Кількість операцій обміну = " + swapCount + System.getProperty("line.separator"));
        for (Shoes shoe : forSelectioSort) {
            String name = shoe.getManufacturer();
            double price = shoe.getPrice();
            double size = shoe.getSize();
            String color = shoe.getColor();
            writer.write(name + " " + price + " " + size + " " + color + System.getProperty("line.separator"));
        }
        writer.write(" " + System.getProperty("line.separator"));
        writer.close();

        return forSelectioSort;

    }
    
    private static void quickSort(List<Shoes> forQuickSort) throws IOException {
        int start = (int) System.nanoTime();
        int equalCountDefault = 0;
        int swapCountDefault = 0;
        int startIndex = 0;
        int endIndex = forQuickSort.size()-1;
        doSort(forQuickSort, startIndex, endIndex, equalCountDefault, swapCountDefault);
        int end = (int) System.nanoTime();
        System.out.println("Quick Sort Method:");
        System.out.println("Процес сортування QuickSort зайняв " + (end - start) + " наносекунд");
        System.out.println("Кількість операцій порівняння = " + e);
        System.out.println("Кількість операцій обмніну = " + s);
        
        String path = "D:\\eclipse-workspace\\Algoritm_1\\SortedShoes.txt";
        FileWriter writer = new FileWriter(path, true);
        int time = end - start;
        writer.write("QuickSort Method:" + System.getProperty("line.separator") );
        writer.write("Процес сортування QuickSort зайняв " + time + " наносекунд" + System.getProperty("line.separator"));
        writer.write("Кількість операцій порівняння = " + e + System.getProperty("line.separator"));
        writer.write("Кількість операцій обміну = " + s + System.getProperty("line.separator"));
        for (Shoes shoe : forQuickSort) {
            String name = shoe.getManufacturer();
            double price = shoe.getPrice();
            double size = shoe.getSize();
            String color = shoe.getColor();
            writer.write(name + " " + price + " " + size + " " + color + System.getProperty("line.separator"));
        }
        writer.write(" " + System.getProperty("line.separator"));
        writer.close();
    }
        
    private static void doSort(List<Shoes> forQuickSort, int startIndex, int endIndex, int equalCountDefault, int swapCountDefault) {
        int equalCount = equalCountDefault;
        int swapCount = swapCountDefault;
        int start = startIndex;
        int end = endIndex;
        if (start >= end)
            return;
        int i = start, j = end;
        int current = i - (i - j) / 2;
        while (i < j) {
            equalCount++;
            while (i < current && (forQuickSort.get(i).getPrice() <= forQuickSort.get(current).getPrice())) {
                i++;
            }
            while (j > current && (forQuickSort.get(current).getPrice() <= forQuickSort.get(j).getPrice())) {
                j--;
            }
            if (i < j) {
                swapCount++;
                Shoes shoe = forQuickSort.get(i);
                forQuickSort.set(i, forQuickSort.get(j));
                forQuickSort.set(j, shoe);
                if (i == current)
                    current = j;
                else if (j == current)
                    current = i;
            }
        }
        doSort(forQuickSort, start, current, equalCount, swapCount);
        doSort(forQuickSort, current+1, end, equalCount, swapCount);
        e = e + equalCount;
        s = s + swapCount;
    }

}
