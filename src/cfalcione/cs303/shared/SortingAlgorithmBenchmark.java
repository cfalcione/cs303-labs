package cfalcione.cs303.shared;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

public class SortingAlgorithmBenchmark {

    public static final int MAX_SIZE = Integer.MAX_VALUE / 2; //arbitrarily chosen
    public static final long ONE_SECOND = 1000000000L;
    public static final double TIMEOUT = 1d;

    public static void benchmark(Collection<SortingAlgorithm<Integer>> algorithms) {
        System.out.println(generateHeaders(algorithms));

        boolean[] hasTimedOut = new boolean[algorithms.size()];

        for (int size = 8; size <= MAX_SIZE; size *= 2) {
            Integer[] array = Helpers.getRandomArray(size);

            benchmark(algorithms, array, hasTimedOut);
        }
    }

    public static void benchmark(Collection<SortingAlgorithm<Integer>> algorithms, Integer[] array, boolean[] hasTimedOut) {
        String[] row = new String[algorithms.size() + 1];
        row[0] = array.length + "";

        int i = 1;
        for (SortingAlgorithm<Integer> algorithm : algorithms) {
            if (hasTimedOut[i - 1]) {
                row[i] = "TIMED_OUT";
                i++;
                continue;
            }

            Integer[] copy = array.clone();
            double time = (double) algorithm.time(copy) / ONE_SECOND; // in seconds
            row[i] = time + "";

            if (time > TIMEOUT) {
                hasTimedOut[i - 1] = true;
            }

            i++;
        }
        String renderedRow = String.join(",", row);
        if (allTimedOut(renderedRow)) return;
        System.out.println(renderedRow);
    }

    public static boolean testSort(Integer[] array) {
        Integer[] sortedCopy = array.clone();
        Arrays.sort(sortedCopy);
        return Helpers.areArraysEqual(array, sortedCopy);
    }

    public static String generateHeaders(Collection<SortingAlgorithm<Integer>> algorithms) {
        String[] results = new String[algorithms.size() + 1];
        results[0] = "Array Size";

        int i = 1;
        for(SortingAlgorithm algorithm : algorithms) {
            results[i] = escapeCommas(algorithm.name());
            i++;
        }
        return String.join(",", results);
    }

    private static boolean allTimedOut(String row) {
        return !Pattern.matches("\\d+,.*\\d.*", row);
    }

    private static String escapeCommas(String input) {
        // TODO
        return input;
    }

}
