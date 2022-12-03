import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day03 {

    public static int priorityCalculator(Character c) {
        // Not really ASCII :/
        int ascii = c;
        if (ascii >= 97) return ascii - 96;
        else return ascii - 38;
    }

    public static Set<Character> stringToSetOfChars(String s) {
        /*
            Convert String to char[]
            Then convert to Character[]
            THEN convert to HashSet<Character>
         */
        return
                new HashSet<>(Arrays.asList(
                        new String(s.toCharArray()).chars().mapToObj(c -> (char) c).toArray(Character[]::new)
                ));
    }

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int total = 0;
        Set<Character> firstHalf;
        Set<Character> secondHalf;
        int halfway;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            halfway = line.length()/2;
            firstHalf = stringToSetOfChars(line.substring(0,halfway));
            secondHalf = stringToSetOfChars(line.substring(halfway));
            firstHalf.retainAll(secondHalf);
            Character common = firstHalf.iterator().next();
            total += priorityCalculator(common);
        }
        return total;
    }

    public static int part02(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int total = 0;
        int counter = 0;
        Set<Character>[] group = new HashSet[3];
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            group[counter] = stringToSetOfChars(line);
            if (counter != 2) {
                counter ++;
            } else {
                Set<Character> common = group[0];
                common.retainAll(group[1]);
                common.retainAll(group[2]);
                total += priorityCalculator(common.iterator().next());
                group = new HashSet[3];
                counter = 0;
            }
        }
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day03.txt");
        int p1 = part01(file);
        int p2 = part02(file);
        System.out.println(p1);
        System.out.println(p2);
    }
}
