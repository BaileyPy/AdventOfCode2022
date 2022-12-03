import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Day01 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int current = 0;
        int biggest = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (Objects.equals(line, "")) {
                if (current > biggest) {
                    biggest = current;
                }
                current = 0;
            } else {
                current += Integer.parseInt(line);
            }
        }
        reader.close();
        return biggest;
    }

    public static int part02(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int current = 0;
        int first = 0;
        int second = 0;
        int third = 0;
        int total;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (Objects.equals(line, "")) {
                if (current > first) {
                    third = second;
                    second = first;
                    first = current;
                } else if (current > second) {
                    third = second;
                    second = current;
                } else if (current > third) {
                    third = current;
                }
                current = 0;
            } else {
                current += Integer.parseInt(line);
            }
        }
        reader.close();
        total = first + second + third;
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day01.txt");
        int p1 = part01(file);
        int p2 = part02(file);
        System.out.println(p1);
        System.out.println(p2);
    }
}