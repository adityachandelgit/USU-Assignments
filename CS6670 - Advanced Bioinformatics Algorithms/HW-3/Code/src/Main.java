import com.google.common.base.Stopwatch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    private static int globalBestScore;
    private static String bestMedianString;
    private static int t;
    private static int n;
    private static int L = 6;
    private static String[] DNA;

    public static void main(String args[]) throws IOException {

        findMedianStringsInActualData();

        /*HashMap<Integer, Long> runTimes = new HashMap<>();
        for(int i = 3; i <= 11; i++) {
            L = i;
            Stopwatch stopwatch = Stopwatch.createStarted();
            findMedianStringInSyntheticData(150, 150, i, 0);
            stopwatch.stop();
            runTimes.put(i, stopwatch.elapsed(TimeUnit.SECONDS));
            System.out.println("Time for Median String of length " + i + ": " + stopwatch.elapsed(TimeUnit.SECONDS) + " seconds");
            System.out.println("-----------------------------------------------" + "\n");

        }

        //Export (L, runtime) to text file
        FileWriter fileWriter;
        BufferedWriter out;
        try {
            fileWriter = new FileWriter("150_dnaLength - 30_Lines - 3-To-11_lengthMedianString - 0_Mutations.txt");
            out = new BufferedWriter(fileWriter);
            for (Map.Entry<Integer, Long> pairs : runTimes.entrySet()) {
                out.write(pairs.getKey() + ", " + pairs.getValue() + "\n");
            }
            out.close();
            System.out.println();
            System.out.println("{Cuts, Time Taken} file exported at --> /values.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //findMedianStringInSyntheticData(200, 50, 5, 1);
    }

    static void findMedianStringInSyntheticData(int dnaLength, int lines, int lengthMedianString, int numberOfMutations) {
        globalBestScore = Integer.MAX_VALUE;
        bestMedianString = "";

        ArrayList<Object> data = syntheticDataGenerator(dnaLength, lines, lengthMedianString, numberOfMutations);
        String originalMotif = (String) data.get(0);
        DNA = (String[]) data.get(1);
        t = DNA.length;
        n = DNA[0].length();

        medianString(lengthMedianString, "");
        System.out.println("Original Median String / Motif: " + originalMotif);
        System.out.println("Found Median String / Motif:    " + bestMedianString);
        System.out.println("Best Score: " + globalBestScore);
        //System.out.println("------------------------------" + "\n");
    }

    static void findMedianStringsInActualData() throws IOException {
        File folder = new File("DNA/");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    globalBestScore = Integer.MAX_VALUE;
                    bestMedianString = "";

                    List<String> list = Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
                    DNA = list.toArray(new String[list.size()]);
                    t = list.size();
                    n = DNA[0].length();

                    System.out.println(file.getName());
                    medianString(L, "");
                    System.out.println("Best Median String: " + bestMedianString);
                    System.out.println("Best Score: " + globalBestScore);
                    System.out.println("------------------------------" + "\n");
                }
            }
        }
    }

    private static void medianString(int sequenceLength, String medianString) {
        //Recursion end condition
        if (sequenceLength == 0) {
            int localScore = score(medianString, DNA);
            if (localScore < globalBestScore) {
                globalBestScore = localScore;
                bestMedianString = medianString;
            }
            return;
        }

        String pattern = "agtc";
        for (int i = 0; i < pattern.length(); i++) {
            //Recursive Call
            medianString(sequenceLength - 1, medianString + pattern.charAt(i));
        }
    }

    private static int score(String word, String DNA[]) {
        int mattrix[][] = new int[t][n - L + 1];
        int i = 0;
        while (i < t) {
            for (int j = 0; j < n - L + 1; j++) {
                mattrix[i][j] = getHammingDistance(word, DNA[i].substring(j, j + L));
            }
            i++;
        }
        int totalDistance = 0;
        int j = 0;
        while (j < t) {
            int minimum = Integer.MAX_VALUE;
            for (int k = 0; k < n - L + 1; k++) {
                if (mattrix[j][k] < minimum) {
                    minimum = mattrix[j][k];
                }
            }
            totalDistance += minimum;
            j++;
        }
        return totalDistance;
    }

    public static int getHammingDistance(String sequence1, String sequence2) {
        char[] s1 = sequence1.toCharArray();
        char[] s2 = sequence2.toCharArray();

        int shorter = Math.min(s1.length, s2.length);
        int longest = Math.max(s1.length, s2.length);

        int result = 0;
        for (int i = 0; i < shorter; i++) {
            if (s1[i] != s2[i]) result++;
        }

        result += longest - shorter;

        return result;
    }

    static ArrayList<Object> syntheticDataGenerator(int dnaLength, int lines, int motifLength, int numberOfMutations) {
        ArrayList<Object> output = new ArrayList<>();
        String[] DNAs = new String[lines];
        final String nucleotides = "actg";
        Random random = new Random();

        //Build Random Motif
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < motifLength; i++) {
            stringBuilder.append(nucleotides.charAt(random.nextInt(nucleotides.length())));
        }
        String motif = stringBuilder.toString();
        output.add(motif);

        //Mutate the Motif
        String mutatedMotif = motif;
        ArrayList<Integer> positionsAlreadyMutated = new ArrayList<>();
        for (int i = 0; i < numberOfMutations; i++) {
            int position = random.nextInt(mutatedMotif.length());
            while (positionsAlreadyMutated.contains(position)) {
                position = random.nextInt(mutatedMotif.length());
            }
            positionsAlreadyMutated.add(position);
            mutatedMotif = changeCharInPosition(position, nucleotides.charAt(random.nextInt(nucleotides.length() - 1)), mutatedMotif);
        }


        //Build DNA and insert mutated motif into it
        for (int i = 0; i < lines; i++) {
            stringBuilder = new StringBuilder();
            for (int j = 0; j < dnaLength; j++) {
                stringBuilder.append(nucleotides.charAt(random.nextInt(nucleotides.length())));
            }
            stringBuilder.insert(random.nextInt(dnaLength - 1), mutatedMotif);
            String dnaWtihMedianString = stringBuilder.toString();
            DNAs[i] = dnaWtihMedianString;
        }

        output.add(DNAs);
        return output;
    }

    public static String changeCharInPosition(int position, char ch, String str) {
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }


}