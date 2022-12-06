import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day06 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        String text = reader.nextLine();
        int pointer = 0;
        boolean found = false;
        while (!found) {
            String substring = text.substring(pointer, pointer+4);
            boolean dupFound = false;
            for (int p1 = pointer; p1 < pointer + 3; p1++) {
                for (int p2 = p1 + 1; p2 < pointer + 4; p2++) {
                    if (text.charAt(p1) == text.charAt(p2)) {
                        dupFound = true;
                        pointer = p1 + 1;
                        break;
                    }
                }
                if (dupFound) break;
            }
            if (!dupFound) found = true;
            else dupFound = false;
        }
        // Position of last character, not the index of the first. For some reason
        return pointer + 4;
    }

    public static int part02(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        String text = reader.nextLine();
        int pointer = 0;
        boolean found = false;
        while (!found) {
            String substring = text.substring(pointer, pointer+14);
            boolean dupFound = false;
            for (int p1 = pointer; p1 < pointer + 13; p1++) {
                for (int p2 = p1 + 1; p2 < pointer + 14; p2++) {
                    if (text.charAt(p1) == text.charAt(p2)) {
                        dupFound = true;
                        pointer = p1 + 1;
                        break;
                    }
                }
                if (dupFound) break;
            }
            if (!dupFound) found = true;
            else dupFound = false;
        }
        // Position of last character, not the index of the first. For some reason
        return pointer + 14;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day06.txt");
        int part01 = part01(file);
        System.out.println(part01);
        int part02 = part02(file);
        System.out.println(part02);
    }
}
