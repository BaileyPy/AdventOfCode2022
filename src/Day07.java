import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

public class Day07 {

    static int part01Counter = 0;
    static int part02Smallest = 0;

    public static Directory createDirectoryTree(Directory top, Scanner reader) {
        Directory current = top;
        // mode 0 for commands. mode 1 for collecting children
        int mode = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] splitLine = line.split(" ");
            if (Objects.equals(splitLine[0], "$")) mode = 0;
            else mode = 1;
            if (mode == 0) {
                if (Objects.equals(splitLine[1], "cd")) {
                    if (Objects.equals(splitLine[2], "..")) current = current.parent;
                    else {
                        for (Item child : current.children) {
                            if (child.getClass() == File_.class) continue;
                            if (Objects.equals(child.name, splitLine[2])) {
                                current = (Directory) child;
                                break;
                            }
                        }
                    }
                }
            } else {
                if (Objects.equals(splitLine[0], "dir")) {
                    Directory newDir = new Directory();
                    newDir.name = splitLine[1];
                    newDir.parent = current;
                    newDir.size = 0;
                    current.children.add(newDir);
                } else {
                    File_ newFile = new File_();
                    newFile.name = splitLine[1];
                    newFile.parent = current;
                    newFile.size = Integer.parseInt(splitLine[0]);
                    current.children.add(newFile);
                }
            }
        }
        return top;
    }

    public static int directorySizeCalculator(Item item) {
        if (item.getClass() == Directory.class) {
            int itemSum = 0;
            for (Item child : ((Directory) item).children) {
                itemSum += directorySizeCalculator(child);
            }
            if (itemSum < 100000) part01Counter += itemSum;
            item.size = itemSum;
        }
        return item.size;
    }

    public static void part02(Item item, int space) {
        if (item.getClass() == Directory.class) {
            for (Item child : ((Directory) item).children) {
                part02(child, space);
            }
            if (item.size > space) {
                if (part02Smallest == 0) part02Smallest = item.size;
                if (part02Smallest > item.size) part02Smallest = item.size;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day07.txt");
        Scanner reader = new Scanner(file);
        Directory outermost = new Directory();
        outermost.name = "/";
        outermost.parent = null;
        outermost.size = 0;
        reader.nextLine();
        Directory directoryTree = createDirectoryTree(outermost, reader);
        // Part 1
        int x = directorySizeCalculator(directoryTree);
        System.out.println(part01Counter);
        // Part 2
        int spaceNeeded =  30000000 - (70000000 - directoryTree.size);
        part02(directoryTree, spaceNeeded);
        System.out.println(part02Smallest);
    }
}

class Item {
    String name;
    Directory parent;
    int size;
}

class Directory extends Item{
    LinkedList<Item> children = new LinkedList<Item>();
}

class File_ extends Item{

}
