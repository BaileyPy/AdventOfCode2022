import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {

    public static Monkey[] createMonkeys(File file) throws FileNotFoundException {
        Monkey[] monkeys = new Monkey[8];
        int monkeyCounter = 0;
        List<Integer> startingItems = new ArrayList<Integer>();
        String[] operation = new String[8];
        int test = 0;
        int[] caseTrue = new int[8];
        int[] caseFalse = new int[8];
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (line.isBlank()) {
                monkeys[monkeyCounter] = new Monkey(startingItems, operation, test);
                startingItems.clear();
                monkeyCounter++;
            } else {
                String[] splitLine = line.split("\\s+");
                if (!Objects.equals(splitLine[0], "Monkey")) splitLine = Arrays.copyOfRange(splitLine, 1, splitLine.length);
                switch (splitLine[0]) {
                    case "Starting" -> {
                        for (String item : Arrays.copyOfRange(splitLine, 2, splitLine.length)) {
                            item = item.replaceAll("[ ,]", "");
                            startingItems.add(Integer.parseInt(item));
                        }
                    }
                    case "Operation:" -> operation = Arrays.copyOfRange(splitLine, 4, splitLine.length);
                    case "Test:" -> test = Integer.parseInt(splitLine[splitLine.length - 1]);
                    case "If" -> {
                        if (Objects.equals(splitLine[1], "true:")) caseTrue[monkeyCounter] = Integer.parseInt(splitLine[5]);
                        else caseFalse[monkeyCounter] = Integer.parseInt(splitLine[5]);
                    }
                }
            }
        }
        monkeys[monkeyCounter] = new Monkey(startingItems, operation, test);
        for (int i = 0; i < 8; i++) {
            monkeys[i].passingInit(monkeys, caseTrue[i], caseFalse[i]);
        }
        return monkeys;
    }

    public static long solve(Monkey[] monkeys) {
        int rounds;
        rounds = 20;
        for (int i = 0; i < rounds; i++) {
            for (Monkey monkey : monkeys) {
                monkey.pass();
            }
        }
        long biggest = 0;
        long second = 0;
        for (Monkey monkey : monkeys) {
            if (monkey.inspects > biggest) {
                second = biggest;
                biggest = monkey.inspects;
            } else if (monkey.inspects > second) {
                second = monkey.inspects;
            }
        }
        return biggest * second;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day11.txt");
        Monkey[] monkeys = createMonkeys(file);
        long part01 = solve(monkeys);
        System.out.println(part01);
    }
}

class Monkey {
    Queue<Integer> items = new LinkedList<>();
    int test;
    Monkey trueMonkey;
    Monkey falseMonkey;
    String[] operation;
    int inspects = 0;
    public Monkey(List<Integer> startingItems, String[] operation, int test) {
        this.items.addAll(startingItems);
        this.operation = operation;
        this.test = test;
    }
    public void passingInit(Monkey[] monkeys, int trueMonkey, int falseMonkey) {
        this.trueMonkey = monkeys[trueMonkey];
        this.falseMonkey = monkeys[falseMonkey];
    }

    public void pass() {
        while (!items.isEmpty()) {
            int currentItem = items.poll();
            int operand;
            if (Objects.equals(operation[1], "old")) operand = currentItem;
            else operand = Integer.parseInt(operation[1]);
            inspects++;
            switch (operation[0]) {
                case "+" -> currentItem = currentItem + operand;
                case "*" -> currentItem = currentItem * operand;
            }currentItem /= 3;
            if (currentItem % test == 0) {
                trueMonkey.items.add(currentItem);
            } else {
                falseMonkey.items.add(currentItem);
            }
        }
    }
}