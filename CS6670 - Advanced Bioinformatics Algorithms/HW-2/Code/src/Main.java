import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static final int NUMBER_OF_PROBLEMS = 25;

    public static void main(String[] args) {
        //int[] input = {2, 2, 3, 3, 4, 5, 6, 7, 8, 10};
        //int[] input = {3, 5, 5, 8, 9, 14, 14, 17, 19, 22};
        //int[] input = {1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 5, 6, 7, 7, 7, 8, 9, 10, 11, 12};
        //List<Integer> listt = IntStream.of(input).boxed().collect(Collectors.toList());

        // Create n number of problems
        ArrayList<List<Integer>> cuts = new ArrayList<>();
        ArrayList<List<Integer>> problems = new ArrayList<>();
        for (int i = 15; i < NUMBER_OF_PROBLEMS; i++) {
            final int[] ints = new Random().ints(1, NUMBER_OF_PROBLEMS + i).distinct().limit(i).toArray();
            List<Integer> list = IntStream.of(ints).boxed().collect(Collectors.toList());
            Collections.sort(list);
            cuts.add(list);
            ArrayList<Integer> fragments = (ArrayList<Integer>) constructFragments(list);
            problems.add(fragments);
        }


        //Apply branchAndBound to each problem and record the time
        Map<Integer, Long> timeConsumed = new HashMap<>();
        for (int i = 0; i < problems.size(); i++) {
            System.out.println("Original Cuts:");
            System.out.print("0, ");
            printArrayList((ArrayList<Integer>) cuts.get(i));

            System.out.println("Fragments: ");
            ArrayList<Integer> currentProblem = (ArrayList<Integer>) problems.get(i);
            printArrayList(currentProblem);
            Stopwatch stopwatch = Stopwatch.createStarted();
            ArrayList<Integer> solution = branchAndBound(currentProblem);
            stopwatch.stop();
            long millis = stopwatch.elapsed(TimeUnit.MILLISECONDS);

            System.out.println("Re-constructed Cuts: ");
            printArrayList(solution);
            int cutpoints = (int) ((1 + Math.sqrt(1 + 8 * problems.get(i).size())) / 2);

            System.out.println("Fragments created from re-constructed Cuts: ");
            solution.remove(0);
            ArrayList<Integer> fragments = (ArrayList<Integer>) constructFragments(solution);
            printArrayList(fragments);
            System.out.println();
            timeConsumed.put(cutpoints, millis);
        }


        //Output the 'Number of cuts' & 'Time elapsed' to txt file
        FileWriter fileWriter;
        BufferedWriter out;
        try {
            fileWriter = new FileWriter("values.txt");
            out = new BufferedWriter(fileWriter);
            for (Map.Entry<Integer, Long> pairs : timeConsumed.entrySet()) {
                out.write(pairs.getKey() + ", " + Math.log(pairs.getValue()) + "\n");
            }
            out.close();
            System.out.println();
            System.out.println("{Cuts, Time Taken} file exported at --> /values.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    private static List<Integer> constructFragments(List<Integer> cuts) {
        List<Integer> fragments = new ArrayList<>();
        fragments.addAll(cuts);
        for (int j = 0; j < cuts.size(); j++) {
            for (int k = j; k < cuts.size() - 1; k++) {
                fragments.add(cuts.get(k + 1) - cuts.get(j));
            }
        }
        Collections.sort(fragments);
        return fragments;
    }

    private static ArrayList<Integer> branchAndBound(List<Integer> input) {
        Multiset<Integer> fragments = HashMultiset.create();
        fragments.addAll(input.stream().collect(Collectors.toList()));

        Set<Integer> solution = new HashSet<>();
        solution.add(0);

        int total_length = Collections.max(input);

        while (fragments.size() > 0) {
            int max = Collections.max(fragments);
            Multiset<Integer> m = newLength(max, solution);

            boolean flag = true;
            for (Multiset.Entry<Integer> entry : m.entrySet()) {
                if (m.count(entry.getElement()) > fragments.count(entry.getElement())) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                solution.add(max);
                Multiset<Integer> m1 = newLength(max, solution);
                for (Multiset.Entry<Integer> entry : m1.entrySet()) {
                    fragments.remove(entry.getElement(), entry.getCount());
                }
            } else {
                solution.add(total_length - max);
                Multiset<Integer> m2 = newLength(total_length - max, solution);
                for (Multiset.Entry<Integer> entry : m2.entrySet()) {
                    fragments.remove(entry.getElement(), entry.getCount());
                }
            }
        }

        ArrayList<Integer> opList = new ArrayList<>();
        opList.addAll(solution);
        Collections.sort(opList);
        return opList;
    }


    public static Multiset<Integer> newLength(int a, Set<Integer> b) {
        Multiset<Integer> m = HashMultiset.create();
        m.addAll(b.stream().map(i -> Math.abs(a - i)).collect(Collectors.toList()));
        return m;
    }

    public static void printArrayList(ArrayList<Integer> list) {
        for (Integer aList : list) {
            System.out.print(aList + ", ");
        }
        System.out.println();
    }
}
