import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        Integer[] head = {0,0};
        Integer[] tail = {0,0};
        HashSet<List<Integer>> positionHistory = new HashSet<>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] splitLine = line.split(" ");
            for (int i = 0; i < Integer.parseInt(splitLine[1]); i ++) {
                switch (splitLine[0]) {
                    case "U" -> head[1]++;
                    case "D" -> head[1]--;
                    case "R" -> head[0]++;
                    case "L" -> head[0]--;
                }
                if (Objects.equals(head[0], tail[0])) {
                    // Same column. Move 1 or 0 positions left or right closer to head
                    tail[1] += (head[1] - tail[1]) / 2;
                } else if (Objects.equals(head[1], tail[1])) {
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

    public static int part02 (File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        Integer[][] rope = new Integer[10][2];
        for (int i = 0; i < rope.length; i++) rope[i] = new Integer[]{0, 0};
        HashSet<List<Integer>> positionHistory = new HashSet<>();
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] splitLine = line.split(" ");
            for (int i = 0; i < Integer.parseInt(splitLine[1]); i ++) {
                Integer[] head = rope[0];
                Integer[] tail = rope[9];
                switch (splitLine[0]) {
                    case "U" -> head[1]++;
                    case "D" -> head[1]--;
                    case "R" -> head[0]++;
                    case "L" -> head[0]--;
                }
                for (int j = 1; j < rope.length; j++) {
                    Integer[] leader = rope[j-1];
                    Integer[] follower = rope[j];
                    if (Objects.equals(leader[0], follower[0])) {
                        // Same column. Move 1 or 0 positions left or right closer to leader
                        follower[1] += (leader[1] - follower[1]) / 2;
                    } else if (Objects.equals(leader[1], follower[1])) {
                        follower[0] += (leader[0] - follower[0]) / 2;
                    } else {
                        // Must move diagonally
                        int xDiff = leader[0] - follower[0];
                        int yDiff = leader[1] - follower[1];
                        if (!(Math.abs(xDiff) == 1 && Math.abs(yDiff) == 1)) {
                            follower[0] += xDiff >= 1 ? 1 : -1;
                            follower[1] += yDiff >= 1 ? 1 : -1;
                        }
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
        int part02 = part02(file);
        System.out.println(part02);
    }
}
