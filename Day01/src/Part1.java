import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("docs/day01.txt");
        Scanner myReader = new Scanner(myObj);
        int current = 0;
        int biggest = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (Objects.equals(line, "")) {
                if (current > biggest) {
                    biggest = current;
                }
                current = 0;
            } else {
                current += Integer.parseInt(line);
            }
        }
        myReader.close();
        System.out.println(biggest);
    }
}