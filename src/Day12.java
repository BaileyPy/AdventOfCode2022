import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Day12 {

    public static char[][] mapGenerator(File file) throws FileNotFoundException {
        char[][] map = new char[41][144];
        Scanner reader = new Scanner(file);
        int rowCounter = 0;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            for (int colCounter = 0; colCounter < map[rowCounter].length; colCounter++) {
                map[rowCounter][colCounter] = line.charAt(colCounter);
            }
            rowCounter++;
        }
        return map;
    }

    public static int Dijkstra(char[][] map) {
        Node[][] nodeMap = new Node[map.length][map[0].length];
        Node goalNode = null;
        PriorityQueue<Node> Q = new PriorityQueue<>(new distComparator());
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                nodeMap[row][col] = new Node(col, row, map[row][col]);
                Q.add(nodeMap[row][col]);
                if (nodeMap[row][col].isGoal) goalNode = nodeMap[row][col];
            }
        }
        nodeMap[0][0].dist = 0;
        while (!Q.isEmpty()) {
            Node u = Q.poll();
            if (u.dist == Integer.MAX_VALUE) break;
            if (u.isGoal) break;
            Node[] neighbours = new Node[4];
            if (u.xPos > 0) neighbours[0] = nodeMap[u.yPos][u.xPos-1];
            if (u.xPos < nodeMap[0].length - 1) neighbours[1] = nodeMap[u.yPos][u.xPos+1];
            if (u.yPos > 0) neighbours[2] = nodeMap[u.yPos-1][u.xPos];
            if (u.yPos < nodeMap.length - 1) neighbours[3] = nodeMap[u.yPos+1][u.xPos];
            for (Node v : neighbours) {
                if (v == null || !u.isValidNeighbour(v)) continue;
                if (u.dist + 1 < v.dist) {
                    v.dist = u.dist + 1;
                    v.previous = u;
                    Q.remove(v);
                    Q.add(v);
                }
            }
        }
        return goalNode.dist;
    }

    public static int part01(char[][] map) {
        return Dijkstra(map);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day12.txt");
        char[][] map = mapGenerator(file);
        int part01 = part01(map.clone());
        System.out.println(part01);
    }
}

class Node {
    int xPos;
    int yPos;
    int dist;
    Node previous;
    char height;
    boolean isGoal;

    public Node(int xPos, int yPos, char height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dist = Integer.MAX_VALUE;
        this.previous = null;
        this.height = height;
        this.isGoal = (height == 'E');
    }

    public boolean isValidNeighbour(Node other) {
        if (other.isGoal) {
            return this.height == 'z';
        }
        return (this.height + 2 > other.height);
    }
}

class distComparator implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.dist, o2.dist);
    }
}