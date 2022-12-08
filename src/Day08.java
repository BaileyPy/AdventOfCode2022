import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day08 {

    public static boolean[][] boolMapGenerator(int h, int w) {
        boolean[][] boolMap = new boolean[w][h];
        for (int line = 0; line < h; line++) {
            if (line == 0 || line == h-1) {
                for (int i = 0; i < w; i++) boolMap[line][i] = true;
            }
            else {
                boolMap[line][0] = true;
                boolMap[line][w-1] = true;
            }
        }
        return boolMap;
    }

    public static int part01(File file) throws FileNotFoundException {
        int height = 99;
        // Convert file to 2d matrix, with transposed equivalent for bottom/top view checking
        int[][] intMatrix = new int[height][height];
        int[][] intMatrixTransposed = new int[height][height];
        Scanner reader = new Scanner(file);
        int lineNumber = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (int charNum = 0; charNum < height; charNum++) {
                intMatrix[lineNumber][charNum] = Character.getNumericValue(line.charAt(charNum));
                intMatrixTransposed[charNum][lineNumber] = Character.getNumericValue(line.charAt(charNum));
            }
            lineNumber++;
        }
        // Translate visibility of matrices to boolMap
        boolean[][] boolMap = boolMapGenerator(height, height);
        for (int line = 1; line < height - 1; line++) {
            int maxOfMatrixLeft = intMatrix[line][0];
            int maxOfMatrixRight = intMatrix[line][height - 1];
            // Transposed left/right is equivalent to normal top/bottom view
            int maxOfTransposedLeft = intMatrixTransposed[line][0];
            int maxOfTransposedRight = intMatrixTransposed[line][height - 1];
            for (int col = 1; col < height - 1; col ++) {
                if (intMatrix[line][col] > maxOfMatrixLeft) {
                    boolMap[line][col] = true;
                    maxOfMatrixLeft = intMatrix[line][col];
                }
                if (intMatrix[line][height - 1 - col] > maxOfMatrixRight) {
                    boolMap[line][height - 1 - col] = true;
                    maxOfMatrixRight = intMatrix[line][height - 1 - col];
                }
                if (intMatrixTransposed[line][col] > maxOfTransposedLeft) {
                    boolMap[col][line] = true;
                    maxOfTransposedLeft = intMatrixTransposed[line][col];
                }
                if (intMatrixTransposed[line][height - 1 - col] > maxOfTransposedRight) {
                    boolMap[height - 1 - col][line] = true;
                    maxOfTransposedRight = intMatrixTransposed[line][height - 1 - col];
                }
            }
        }
        // Count number of true values in boolMap
        int total = 0;
        for (boolean[] line : boolMap) {
            for (boolean value : line) {
                total += value ? 1 : 0;
            }
        }
        return total;
    }

    public static int part02(File file) throws FileNotFoundException {
        int height = 99;
        int[][] intMatrix = new int[height][height];
        Scanner reader = new Scanner(file);
        // Convert file to 2d matrix
        int lineNumber = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (int charNum = 0; charNum < height; charNum++) {
                intMatrix[lineNumber][charNum] = Character.getNumericValue(line.charAt(charNum));
            }
            lineNumber++;
        }
        int currentHighest = 0;
        // Look left, right, up, and down from each tree
        // Ignore trees on outer edge as this means multiplying by 0
        for (int row = 1; row < height - 1; row++) {
            for (int col = 1; col < height - 1; col++) {
                int thisTree = intMatrix[row][col];
                // Look left
                int left = 1;
                int currentCol = col - 1;
                int currentTree = intMatrix[row][currentCol];
                while (currentTree < thisTree && currentCol > 0) {
                     currentCol--;
                     left++;
                     currentTree = intMatrix[row][currentCol];
                }
                // Look right
                int right = 1;
                currentCol = col + 1;
                currentTree = intMatrix[row][currentCol];
                while (currentTree < thisTree && currentCol < height - 1) {
                    currentCol++;
                    right++;
                    currentTree = intMatrix[row][currentCol];
                }
                // Look up
                int up = 1;
                int currentRow = row - 1;
                currentTree = intMatrix[currentRow][col];
                while (currentTree < thisTree && currentRow > 0) {
                    currentRow--;
                    up++;
                    currentTree = intMatrix[currentRow][col];
                }
                // Look down
                int down = 1;
                currentRow = row + 1;
                currentTree = intMatrix[currentRow][col];
                while (currentTree < thisTree && currentRow < height - 1) {
                    currentRow++;
                    down++;
                    currentTree = intMatrix[currentRow][col];
                }
                if (up * down * left * right > currentHighest) currentHighest = up * down * left * right;
            }
        }
        return currentHighest;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day08.txt");
        int part01 = part01(file);
        System.out.println(part01);
        int part02 = part02(file);
        System.out.println(part02);
    }
}
