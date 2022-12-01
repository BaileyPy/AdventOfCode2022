import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("docs/day01.txt");
        Scanner myReader = new Scanner(myObj);
        int current = 0;
        int first = 0;
        int second = 0;
        int third = 0;
        int total;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
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
        myReader.close();
        total = first + second + third;
        System.out.println(total);
    }
}