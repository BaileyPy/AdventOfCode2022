import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int total = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            char elf = line.charAt(0);
            char opp = line.charAt(2);
            switch (opp) {
                case 'X' -> {
                    total += 1;
                    switch (elf) {
                        case 'A' -> total += 3;
                        case 'C' -> total += 6;
                    }
                }
                case 'Y' -> {
                    total += 2;
                    switch (elf) {
                        case 'A' -> total += 6;
                        case 'B' -> total += 3;
                    }
                }
                case 'Z' -> {
                    total += 3;
                    switch (elf) {
                        case 'B' -> total += 6;
                        case 'C' -> total += 3;
                    }
                }
            }
        }
        return total;
    }

    public static int part02(File file) throws FileNotFoundException {        
        Scanner reader = new Scanner(file);
        int total = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            char elf = line.charAt(0);
            char opp = line.charAt(2);
            switch (opp) {
                case 'X' -> {
                    // Lose
                    switch (elf) {
                        // opp plays Scissors
                        case 'A' -> total += 3;
                        // opp plays Rock
                        case 'B' -> total += 1;
                        // opp plays Paper
                        case 'C' -> total += 2;
                    }
                }
                case 'Y' -> {
                    // Draw
                    total += 3;
                    switch (elf) {
                        case 'A' -> total += 1;
                        case 'B' -> total += 2;
                        case 'C' -> total += 3;
                    }
                }
                case 'Z' -> {
                    total += 6;
                    switch (elf) {
                        case 'A' -> total += 2;
                        case 'B' -> total += 3;
                        case 'C' -> total += 1;
                    }
                }
            }
        }
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day02.txt");
        int p1 = part01(file);
        int p2 = part02(file);
        System.out.println(p1);
        System.out.println(p2);
    }
}
