import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        Integer[] head = {0,0};
        Integer[] tail = {0,0};
        HashSet<List<Integer>> positionHistory = new HashSet<>();
        int counter = 0;
        while (reader.hasNextLine()) {
            counter ++;
            String line = reader.nextLine();
            String[] splitLine = line.split(" ");
            for (int i = 0; i < Integer.parseInt(splitLine[1]); i ++) {
                switch (splitLine[0]) {
                    case "U" -> head[1]++;
                    case "D" -> head[1]--;
                    case "R" -> head[0]++;
                    case "L" -> head[0]--;
                }
                System.out.println(Arrays.toString(head));
                if (head[0] == tail[0]) {
                    // Same column. Move 1 or 0 positions left or right closer to head
                    tail[1] += (head[1] - tail[1]) / 2;
                } else if (head[1] == tail[1]) {
                    tail[0] += (head[0] - tail[0]) / 2;
                } else {
                    // Must move diagonally
                    int xDiff = head[0] - tail[0];
                    int yDiff = head[1] - tail[1];
                    if (!(Math.abs(xDiff) == 1 && Math.abs(yDiff) == 1)) {
                        tail[0] += xDiff >= 1 ? 1 : -1;
                        tail[1] += yDiff >= 1 ? 1 : -1;
                    }
                }
                positionHistory.add(List.of(tail));
            }
        }
        return positionHistory.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day09.txt");
        int part01 = part01(file);
        System.out.println(part01);
    }
}
