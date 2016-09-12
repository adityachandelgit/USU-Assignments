import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        int n = 1000;
        int[] genome = IntStream.range(0, n).toArray();

        ArrayList<Pair> p = new ArrayList<>();
        for (int m = 1; m <= 250; m++) {
            int e = rand.nextInt(750) + 1;
            int s = rand.nextInt(e) + 1;
            genome = oneFlip(genome, s, e);
            int numFlips = improvedBreakpointReversalSort(genome);
            p.add(new Pair(m, numFlips));
        }
        for(int i = 0; i < p.size(); i++) {
            System.out.println(p.get(i).getKey() + ", " + p.get(i).getValue());
        }
        // I have copied the above comma separated values of 'flips' from console and have used them
        // in creating the scatter plot using plotly, https://plot.ly/plot#
    }

    private static int[] oneFlip(int[] arr, int s, int e) {
        return doReversal(arr, new Pair(s, e));
    }

    private static int improvedBreakpointReversalSort(int[] array) {
        int count = 0;
        while (containsBreakpoint(array)) {
            count++;
            ArrayList<ArrayList<Pair>> d = getBreakPoints(array);
            ArrayList<Pair> inc = d.get(0);
            ArrayList<Pair> dec = d.get(1);
            Pair breakPoint = null;
            if (dec.size() > 0) {
                ArrayList<Pair> merged = new ArrayList<>();
                merged.addAll(inc);
                merged.addAll(dec);
                breakPoint = toReverse(array, merged);
            } else {
                if (inc.size() > 0) {
                    breakPoint = inc.get(0);
                }
            }
            System.out.println(java.util.Arrays.toString(array));
            if (breakPoint != null) {
                System.out.println("Reversal: " + breakPoint.getKey() + ", " + breakPoint.getValue());
            }
            array = doReversal(array, breakPoint);
        }
        System.out.println(java.util.Arrays.toString(array) + " :Sorted");
        return count;
    }

    private static int[] doReversal(int[] array, Pair breakPoint) {
        int[] reversed = new int[array.length];
        System.arraycopy(array, 0, reversed, 0, (int) breakPoint.getKey());
        int temp = (int) breakPoint.getKey();
        for (int i = (int) breakPoint.getValue() - 1; i >= (int) breakPoint.getKey(); i--) {
            reversed[temp] = array[i];
            temp++;
        }
        System.arraycopy(array, (int) breakPoint.getValue(), reversed, (int) breakPoint.getValue(), array.length - (int) breakPoint.getValue());
        System.out.println();
        return reversed;
    }

    private static Pair toReverse(int[] array, ArrayList<Pair> merged) {
        Pair reverse = new Pair(0, null);
        int[] left = new int[merged.size()];
        int[] right = new int[merged.size()];
        for (int i = 0; i < merged.size(); i++) {
            left[i] = (int) merged.get(i).getKey();
            right[i] = (int) merged.get(i).getValue();
        }
        for (int aLeft : left) {
            for (int aRight : right) {
                if (aLeft >= aRight - 1) {
                    continue;
                }
                int breakpointsRemoved = 0;

                if (Math.abs(array[aRight - 1] - array[aLeft - 1]) == 1) {
                    breakpointsRemoved++;
                }
                if (Math.abs(array[aRight] - array[aLeft]) == 1) {
                    breakpointsRemoved++;
                }
                if (breakpointsRemoved > (int) reverse.getKey()) {
                    reverse = new Pair(breakpointsRemoved, new Pair<>(aLeft, aRight));
                }
            }
        }
        return (Pair) reverse.getValue();
    }

    private static ArrayList<ArrayList<Pair>> getBreakPoints(int[] array) {
        ArrayList<ArrayList<Pair>> breakPoints = new ArrayList<>();
        int[] difference = new int[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            difference[i] = array[i + 1] - array[i];
        }
        int start = 0;
        ArrayList<Pair> inc = new ArrayList<>();
        ArrayList<Pair> dec = new ArrayList<>();
        for (int i = 0; i < difference.length; i++) {
            if ((Math.abs(difference[i]) == 1) && (difference[i] == difference[start])) {
                continue;
            }
            if (start > 0) {
                if (difference[start] == 1) {
                    inc.add(new Pair(start, i + 1));
                } else {
                    dec.add(new Pair(start, i + 1));
                }
            }
            start = i + 1;
        }
        breakPoints.add(inc);
        breakPoints.add(dec);
        return breakPoints;
    }

    private static boolean containsBreakpoint(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] + 1 != array[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
