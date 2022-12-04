import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day04 {

    public static int[] stringArrToIntArr(String[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.parseInt(arr[i]);
        }
        return result;
    }

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int total = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            int[] assignments = stringArrToIntArr(line.split("[,-]"));
            if ((assignments[0] >= assignments[2] && assignments[1] <= assignments[3])
                || (assignments[0] <= assignments[2] && assignments[1] >= assignments[3])) {
                total ++;
            }
        }
        return total;
    }

    public static int part02(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int total = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            int[] assignments = stringArrToIntArr(line.split("[,-]"));
            if ((assignments[0] >= assignments[2] && assignments[0] <= assignments[3])
                || (assignments[1] >= assignments[2] && assignments[1] <= assignments[3])
                || (assignments[2] >= assignments[0] && assignments[2] <= assignments[1])
                || (assignments[3] >= assignments[0] && assignments[3] <= assignments[1])) {
                total ++;
            }
        }
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day04.txt");
        int p1 = part01(file);
        int p2 = part02(file);
        System.out.println(p1);
        System.out.println(p2);
    }
}
