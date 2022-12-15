import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day15 {

    public static int part01(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        final int LINE = 2000000;
        List<int[]> relevantCoords = new LinkedList<>();
        Set<Integer> relevantBeacons = new HashSet<>();
        while (reader.hasNextLine()) {
            String[] splitLine = reader.nextLine().split(" ");
            int sensorX = Integer.parseInt(splitLine[2].substring(2, splitLine[2].length() - 1));
            int sensorY = Integer.parseInt(splitLine[3].substring(2, splitLine[3].length() - 1));
            int beaconX = Integer.parseInt(splitLine[8].substring(2, splitLine[8].length() - 1));
            int beaconY = Integer.parseInt(splitLine[9].substring(2));
            if (beaconY == LINE) relevantBeacons.add(beaconX);
            int distance = Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);
            if (sensorY < LINE && sensorY + distance > LINE) {
                int[] coords = {sensorX, sensorY, beaconX, beaconY, distance};
                relevantCoords.add(coords);
            } else if (sensorY > LINE && sensorY - distance < LINE) {
                int[] coords = {sensorX, sensorY, beaconX, beaconY, -distance};
                relevantCoords.add(coords);
            }
        }
        Set<Integer> notABeacon = new HashSet<>();
        for (int[] x : relevantCoords) {
            int fieldLineDiff = Math.max((x[1] + x[4]) - LINE, LINE - (x[1] + x[4]));
            for (int i = 0; i <= fieldLineDiff; i++) {
                if (!relevantBeacons.contains(x[0] + i)) {
                    notABeacon.add(x[0] + i);
                }
                if (!relevantBeacons.contains(x[0] - i)) {
                    notABeacon.add(x[0] - i);
                }
            }
        }
        return notABeacon.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day15.txt");
        int part01 = part01(file);
        System.out.println(part01);
    }
}
