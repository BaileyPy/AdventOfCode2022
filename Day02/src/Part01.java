import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Part01 {
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
                    total += 1;
                    switch (elf) {
                        case 'A' -> total += 3;
                        case 'C' -> total += 6;
                    }
                    break;
                case 'Y':
                    total += 2;
                    switch (elf) {
                        case 'A' -> total += 6;
                        case 'B' -> total += 3;
                    }
                    break;
                case 'Z':
                    total += 3;
                    switch (elf) {
                        case 'B' -> total += 6;
                        case 'C' -> total += 3;
                    }
                    break;
            }
        }
        System.out.println(total);
    }
}