import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day16 {

    public static List<Valve> valveGenerator(File file) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        List<Valve> valves = new LinkedList<>();
        while (reader.hasNextLine()) {
            String[] splitString = reader.nextLine().split(" ");
            int rate = Integer.parseInt(splitString[4].substring(5, splitString[4].length() - 1));
            valves.add(new Valve(splitString[1], rate));
        }
        reader = new Scanner(file);
        int counter = 0;
        while (reader.hasNextLine()) {
            String[] splitString = reader.nextLine().split(" ");
            List<Valve> neighbours = new LinkedList<>();
            for (String s : Arrays.copyOfRange(splitString, 9, splitString.length)) {
                s = s.replace(",", "");
                for (Valve v : valves) {
                    if (Objects.equals(v.name, s)) {
                        neighbours.add(v);
                        break;
                    }
                }
            }
            valves.get(counter).neighbourInit(neighbours);
            counter++;
        }
        for (Valve v : valves) {
            if (Objects.equals(v.name, "AA")) return valves;
        }
        return null;
    }

    public static int BFS(Valve valve, List<Valve> turned, int minute, int released, List<Integer> rates, Valve last) {
        if (minute == 31) {
            return released;
        }
        int newReleased = released;
        for (Valve v : turned) {
            newReleased += v.rate;
        }
        List<Integer> BFSed = new LinkedList<>();
        if (!rates.isEmpty()) {
            if (valve.rate == rates.get(rates.size() - 1)) {
                List<Integer> newRates = new LinkedList<>(rates);
                newRates.remove(rates.size() - 1);
                List<Valve> newTurned = new LinkedList<>(turned);
                newTurned.add(valve);
                return BFS(valve, newTurned, minute + 1, newReleased, newRates, valve);
            }
        }
        if (!turned.contains(valve) && valve.rate > 0) {
            List<Integer> newRates = new LinkedList<>(rates);
            newRates.remove((Integer) valve.rate);
            List<Valve> newTurned = new LinkedList<>(turned);
            newTurned.add(valve);
            BFSed.add(BFS(valve, newTurned, minute + 1, newReleased, newRates, valve));
        }
        for (Valve n : valve.neighbours) {
            if (!Objects.equals(n, last) || valve.neighbours.size() == 1) {
                BFSed.add(BFS(n, turned, minute + 1, newReleased, rates, valve));
            }
        }
        return Collections.max(BFSed);
    }

    public static int part01(File file) throws FileNotFoundException {
        List<Valve> valves = valveGenerator(file);
        List<Integer> rates = new LinkedList<>();
        Valve topValve = null;
        for (Valve v : valves) {
            rates.add(v.rate);
            if (Objects.equals(v.name, "AA")) topValve = v;
        }
        rates.sort(null);
        return BFS(topValve, new LinkedList<>(), 1, 0, rates, null);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("docs/Day16.txt");
        int part01 = part01(file);
        System.out.println(part01);
    }
}

class Valve {
    String name;
    int rate;
    List<Valve> neighbours;
    public Valve(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }
    public void neighbourInit(List<Valve> neighbours) {
        this.neighbours = neighbours;
    }
}
