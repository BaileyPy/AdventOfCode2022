import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Day05 {

    public static Stack[] stackGenerator(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        String[] strings = new String[8];
        char[][] matrix = new char[8][9];
        Stack[] stacks = new Stack[9];
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack();
        }
        // Convert "stacks" in file to 9x8 matrix
        for (int row = 0; row < 8; row ++) {
            String line = reader.nextLine();
            for (int col = 0; col <= line.length() / 4; col++) {
                int lineIndex = col * 4 + 1;
                if (line.charAt(lineIndex) != ' ' && line.charAt(lineIndex) != '[' && line.charAt(lineIndex) != ']') {
                    matrix[row][col] = line.charAt(lineIndex);
                }
            }
        }
        // Put matrix data into stacks
        for (int row = 7; row >= 0; row--) {
            char[] line = matrix[row];
            for (int col = 0; col <= 8; col++) {
                // Ignore unknown characters/nulls
                if (Character.isLetter(matrix[row][col])) {
                    stacks[col].push(matrix[row][col]);
                }
            }
        }
        return stacks;
    }

    public static int[][] instructionGenerator(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        int[][] instructions = new int[501][3];
        int counter = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            if (line.charAt(0) == 'm') {
                String[] splitLine = line.split(" ");
                for (int i = 0; i < 3; i++) {
                    instructions[counter][i] = Integer.parseInt(splitLine[i*2+1]);
                }
                counter++;
            }
        }
        return instructions;
    }

    public static String part01(Stack[] stacks, int[][] instructions) {
        String result = "";
        for (int[] instr : instructions) {
            int counter = instr[0];
            while (counter > 0) {
                stacks[instr[2] - 1].push(stacks[instr[1] - 1].pop());
                counter--;
            }
        }
        for (Stack stack : stacks) {
            result += stack.peek();
        }
        return result;
    }

    public static String part02(Stack[] stacks, int[][] instructions) {
        String result = "";
        for (int[] instr : instructions) {
            int counter = instr[0];
            /*
            Temp stack means items move like this when pushed one at a time:
            Stack0: ABC ->
            temp:   CBA ->
            Stack1: ABC
             */
            Stack temp = new Stack();
            while (counter > 0) {
                temp.push(stacks[instr[1] - 1].pop());
                counter--;
            }
            while (!temp.empty()) {
                stacks[instr[2] - 1].push(temp.pop());
            }
        }
        for (Stack stack : stacks) {
            result += stack.peek();
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day05.txt");
        Stack[] stacks = stackGenerator(file);
        int[][] instructions = instructionGenerator(file);
        String part01 = part01(stacks, instructions);
        stacks = stackGenerator(file);
        String part02 = part02(stacks, instructions);
        System.out.println(part01);
        System.out.println(part02);
    }
}
