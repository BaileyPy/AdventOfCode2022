import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part02 {
    public static void main(String[] args) throws FileNotFoundException {
        File myObj = new File("C:/Users/Bailey/Documents/AdventOfCode2022/Day02/docs/Day02.txt");
        Scanner myReader = new Scanner(myObj);
        int total = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            char elf = line.charAt(0);
            char opp = line.charAt(2);
            switch (opp) {
                case 'X':
                    // Lose
                    switch (elf) {
                        // opp plays Scissors
                        case 'A' -> total += 3;
                        // opp plays Rock
                        case 'B' -> total += 1;
                        // opp plays Paper
                        case 'C' -> total += 2;
                    }
                    break;
                case 'Y':
                    // Draw
                    total += 3;
                    switch (elf) {
                        case 'A' -> total += 1;
                        case 'B' -> total += 2;
                        case 'C' -> total += 3;
                    }
                    break;
                case 'Z':
                    total += 6;
                    switch (elf) {
                        case 'A' -> total += 2;
                        case 'B' -> total += 3;
                        case 'C' -> total += 1;
                    }
                    break;
            }
        }
        System.out.println(total);
    }
}