import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Day14 {

    public static char[][] environmentBuilder(File file, boolean part2) throws FileNotFoundException {
        char[][] environment = new char[200][1000];
        for (int r = 0; r < 200; r++) {
            for (int c = 0; c < 1000; c++) {
                environment[r][c] = '.';
            }
        }
        int lowestPoint = 0;
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] splitLine = reader.nextLine().split(" -> ");
            for (int i = 0; i < splitLine.length - 1; i++) {
                String[] line1 = splitLine[i].split(",");
                String[] line2 = splitLine[i+1].split(",");
                if (Objects.equals(line1[0], line2[0])) {
                    int col = Integer.parseInt(line1[0]);
                    int row1 = Integer.parseInt(line1[1]);
                    int row2 = Integer.parseInt(line2[1]);
                    if (Math.max(row1, row2) > lowestPoint) lowestPoint = Math.max(row1, row2);
                    if (row1 > row2) {
                        for (int r = row1; r >= row2; r--) environment[r][col] = '#';
                    } else {
                        for (int r = row1; r <= row2; r++) environment[r][col] = '#';
                    }
                } else if (Objects.equals(line1[1], line2[1])) {
                    int row = Integer.parseInt(line1[1]);
                    if (row > lowestPoint) lowestPoint = row;
                    int col1 = Integer.parseInt(line1[0]);
                    int col2 = Integer.parseInt(line2[0]);
                    if (col1 > col2) {
                        for (int c = col1; c >= col2; c--) environment[row][c] = '#';
                    } else {
                        for (int c = col1; c <= col2; c++) environment[row][c] = '#';
                    }
                }
            }
        }
        if (part2) {
            char[] floor = new char[1000];
            Arrays.fill(floor, '#');
            environment[lowestPoint + 2] = floor;
        }
        return environment;
    }

    public static int part01(File file) throws FileNotFoundException {
        char[][] environment = environmentBuilder(file, false);
        boolean falling = true;
        int sandX = 500;
        int sandY = 0;
        int counter = 0;
        while (falling) {
            if (sandY == 199) {
                falling = false;
            } else {
                if (environment[sandY + 1][sandX] == '.') {
                    sandY ++;
                } else if (environment[sandY + 1][sandX - 1] == '.') {
                    sandY ++;
                    sandX --;
                } else if (environment[sandY + 1][sandX + 1] == '.') {
                    sandY ++;
                    sandX ++;
                } else {
                    environment[sandY][sandX] = '+';
                    counter++;
                    sandX = 500;
                    sandY = 0;
                }
            }
        }
        return counter;
    }

    public static int part02(File file) throws FileNotFoundException {
        char[][] environment = environmentBuilder(file, true);
        boolean falling = true;
        int sandX = 500;
        int sandY = 0;
        int counter = 0;
        while (falling) {
            if (environment[sandY + 1][sandX] == '.') {
                sandY ++;
            } else if (environment[sandY + 1][sandX - 1] == '.') {
                sandY ++;
                sandX --;
            } else if (environment[sandY + 1][sandX + 1] == '.') {
                sandY ++;
                sandX ++;
            } else {
                environment[sandY][sandX] = '+';
                counter++;
                if (sandY == 0) {
                    falling = false;
                }
                sandX = 500;
                sandY = 0;
            }
        }
        return counter;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day14.txt");
        int part01 = part01(file);
        System.out.println(part01);
        int part02 = part02(file);
        System.out.println(part02);
    }
}
