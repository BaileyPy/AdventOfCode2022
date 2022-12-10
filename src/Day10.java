import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Day10 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int cycle = 0;
        int sum = 1;
        int signalSum = 0;
        int cycleToCatch = 20;
        while (reader.hasNextLine()) {
            cycle++;
            if (cycle == cycleToCatch) {
                signalSum += cycleToCatch * sum;
                if (cycleToCatch == 220) break;
                cycleToCatch += 40;
            }
            String line = reader.nextLine();
            String[] splitLine = line.split(" ");
            if (Objects.equals(splitLine[0], "addx")) {
                cycle++;
                if (cycle == cycleToCatch) {
                    signalSum += cycleToCatch * sum;
                    if (cycleToCatch == 220) break;
                    cycleToCatch += 40;
                }
                sum += Integer.parseInt(splitLine[1]);
            }
        }
        return signalSum;
    }

    public static String part02(File file) throws FileNotFoundException {
        int spritePos = 1;
        int cycle = 0;
        String screen = "";
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            cycle++;
            if (cycle % 40 == 0) cycle = 0;
            if (cycle == spritePos || cycle == spritePos + 1 || cycle == spritePos - 1) screen += "#";
            else screen += ".";
            String line = reader.nextLine();
            String[] splitLine = line.split(" ");
            if (Objects.equals(splitLine[0], "addx")) {
                cycle++;
                spritePos += Integer.parseInt(splitLine[1]);
                if (cycle % 40 == 0) cycle = 0;
                if (cycle == spritePos || cycle == spritePos + 1 || cycle == spritePos - 1) screen += "#";
                else screen += ".";
            }
        }
        return screen;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day10.txt");
        int part01 = part01(file);
        System.out.println(part01);
        String part02 = part02(file);
        for (int i = 0; i < 202; i += 40) {
            // Missing first column but still legible enough
            System.out.println(part02.substring(i, i + 39));
        }
    }
}
